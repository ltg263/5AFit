package com.jxkj.fit_5a.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.AppException;
import com.jxkj.fit_5a.conpoment.utils.MyActivityManager;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseFinishActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yw.library.videocache.HttpProxyCacheServer;
import com.yw.library.videocache.file.FileNameGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/8/28.
 */

public class MainApplication extends Application implements Application.ActivityLifecycleCallbacks {
    /**
     * 上下文对象
     */
    public static MainApplication mContext;
    private static Stack<Activity> activityStack;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        AppException appException = AppException.getInstance();
//        appException.init(getApplicationContext());
        registerActivityLifecycleCallbacks(mContext);
        boolean isFirst = SharedUtils.singleton().get(ConstValues.ISNOTFIRST,false);
        if(isFirst){
            initUMShare(this);
        }
    }
    public static void initUMShare(Context mContext) {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(mContext);
        UMShareAPI.get(mContext);
        UMShareAPI.init(mContext,"5ec7be110cafb29140000033");
        PlatformConfig.setWeixin(ConstValues.WX_APP_ID,ConstValues.WX_APP_SECRET);
        LogcatHelper.getInstance(mContext).start();
    }

    /**
     * 全局上下文
     */
    public static MainApplication getContext() {
        return mContext;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity){
        if(activityStack ==null){
            activityStack =new Stack<>();
        }
        if(!activityStack.contains(activity)){
            activityStack.add(activity);
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity(true);
        } catch (Exception e) {
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity(boolean isExitApp) {
        if(activityStack == null){
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                if(!(activityStack.get(i) instanceof MainActivity) || isExitApp){
                    activityStack.get(i).finish();
                }
            }
        }
        activityStack.clear();
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.color_666666);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    private static Map<String, HttpProxyCacheServer> mapVideoIds = new HashMap<>();
    public static HttpProxyCacheServer getProxy(Context context,String videoId) {
        MainApplication app = (MainApplication) context.getApplicationContext();
//        return app.proxy == null ? (app.proxy = app.newProxy(videoId)) : app.proxy;
        if(!mapVideoIds.containsKey(videoId)){
            Log.w("--->>>>>>>>>>>>>","新增videoId："+videoId);
            mapVideoIds.put(videoId,app.newProxy(videoId));
            return app.newProxy(videoId);
        }
        Log.w("--->>>>>>>>>>>>>","获取videoId："+videoId);
        return mapVideoIds.get(videoId);
    }

    private HttpProxyCacheServer newProxy(String videoId) {
        File foder = new File(MapExerciseFinishActivity.initFileRoot(MainApplication.getContext()) + "/5Afit");
        if(foder.exists()){
            return new HttpProxyCacheServer.Builder(this)
                    .cacheDirectory(foder)//缓存路径
                    .maxCacheFilesCount(10)//最大缓存文件数量
                    .maxCacheSize(500 * 1024 * 1024)//最大缓存大小
                    .fileNameGenerator(new MyFileNameGenerator(videoId))
                    .build();
        }else{
            if(foder.mkdirs()){
                return new HttpProxyCacheServer.Builder(this)
                        .cacheDirectory(foder)//缓存路径
                        .maxCacheFilesCount(100)//最大缓存文件数量
                        .maxCacheSize(500 * 1024 * 1024)//最大缓存大小
                        .fileNameGenerator(new MyFileNameGenerator(videoId))
                        .build();
            }
            return null;
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        MyActivityManager.getInstance().setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    class MyFileNameGenerator implements FileNameGenerator {
        String videoId;
        public MyFileNameGenerator(String videoId) {
            this.videoId = videoId;
        }
        public String generate(String url) {
            return videoId;
        }
    }
}
