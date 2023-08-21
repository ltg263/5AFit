package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.RewardLogBean;
import com.jxkj.fit_5a.view.activity.exercise.ExerciseRecordDetailsActivity;
import com.jxkj.fit_5a.view.fragment.MapFinish1Fragment;
import com.jxkj.fit_5a.view.fragment.MapFinish2Fragment;
import com.jxkj.fit_5a.view.fragment.MapFinish3Fragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapExerciseFinishActivity extends BaseActivity {
    @BindView(R.id.rl_actionbar)
    RelativeLayout relativeLayout;
    @BindView(R.id.iv_zuo)
    ImageView mIvZuo;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_you)
    ImageView mIvYou;
    private ArrayList<BpmDataBean> mBpmDataBeans;
    private ArrayList<PostUser.SportLogInfo.DetailsBean.LogsBean> logs;
    private String data_id;
    @Override
    protected int getContentView() {
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_map_exercise_finish;
    }

    @Override
    protected void initViews() {
        if(PopupWindowLanYan.ble4Util!=null){
            PopupWindowLanYan.ble4Util.disconnect();
        }
        mIvZuo.setVisibility(View.INVISIBLE);
        mBpmDataBeans = getIntent().getParcelableArrayListExtra("mBpmDataBeans");
        logs = getIntent().getParcelableArrayListExtra("logs");
        data_id = getIntent().getStringExtra("data_id");
        Log.w("data_id","data_id"+data_id);
        initVP();
        getRewardLogQuery();
    }
    String beginCreateTime = null;
    private void getRewardLogQuery() {

        List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        for(int i=0;i<lists.size();i++){
            if(PopupWindowLanYan.BleName.equals(lists.get(i).getName())){
                beginCreateTime = lists.get(i).getTime();
            }
        }
        RetrofitUtil.getInstance().apiService()
                .getRewardLogQuery(2,beginCreateTime,true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RewardLogBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RewardLogBean> result) {
                        if(isDataInfoSucceed(result)){
                            List<RewardLogBean.ListBean> lists = result.getData().getList();
                            List<TaskListBase.ListBean> listFinishTask = result.getData().getFinishTask();
                            int pos = 0;
                            if(listFinishTask!=null && listFinishTask.size()>0){
                                DialogUtils.showDialogRw_get(MapExerciseFinishActivity.this, listFinishTask, new DialogUtils.DialogLyInterface() {
                                    @Override
                                    public void btnConfirm() {
                                        if(lists!=null && lists.size()>0){
                                            showDialogWzq_get(pos,lists);
                                        }
                                    }
                                });
                            }else{
                                if(lists!=null && lists.size()>0){
                                    showDialogWzq_get(pos,lists);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void showDialogWzq_get(int pos, List<RewardLogBean.ListBean> lists) {
        DialogUtils.showDialogWzq_get(this, lists.get(pos), new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                if(pos<lists.size()-1){
                    int index = pos+1;
                    showDialogWzq_get(index,lists);
                }
            }
        });
    }
    private void initVP() {
        if(mBpmDataBeans!=null){
            getFragments();
        }
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIvZuo.setVisibility(View.VISIBLE);
                mIvYou.setVisibility(View.VISIBLE);
                if (position == 0) {
                    mIvZuo.setVisibility(View.INVISIBLE);
                }
                if (position == 2) {
                    mIvYou.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });

        mViewPager.setCurrentItem(0);
    }

    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments() {
        MapFinish1Fragment fragment = new MapFinish1Fragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("mBpmDataBeans", mBpmDataBeans);
        fragment.setArguments(bundle);
        fragments.add(fragment);

        MapFinish2Fragment fragment1 = new MapFinish2Fragment();
        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("mBpmDataBeans", mBpmDataBeans);
        bundle1.putParcelableArrayList("logs", logs);
        fragment1.setArguments(bundle1);
        fragments.add(fragment1);

        MapFinish3Fragment fragment2 = new MapFinish3Fragment();
        Bundle bundle2 = new Bundle();
//        bundle1.putString("search",search);
        fragment2.setArguments(bundle2);
        fragments.add(fragment2);
        return fragments;
    }

    @OnClick({R.id.iv_1,R.id.tv_end, R.id.tv_ok, R.id.iv_zuo, R.id.iv_you})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
            case R.id.tv_end:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            case R.id.tv_ok:
                if(StringUtil.isNotBlank(data_id)){
                    IntentUtils.getInstence().intent(
                            this, ExerciseRecordDetailsActivity.class,
                            "id",data_id);
                    finish();
                    return;
                }
                ToastUtils.showShort("数据生成失败");
//                getImgPath(new Aba() {
//                    @Override
//                    public void ok()    shareData(MapExerciseFinishActivity.this,"5Afit_img.jpg");{
//
//                    }
//                });
                break;
            case R.id.iv_zuo:
                if (mViewPager.getCurrentItem() > 0) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                }
                break;
            case R.id.iv_you:
                if (mViewPager.getCurrentItem() < 3) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }
                break;
        }
    }

    public static void shareData(Activity mContext, String name){

        String path = initFileRoot(mContext) + "/5Afit/"+name;
        Log.w("path","path"+path);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fis==null){
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fis);
        new ShareAction(mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        ToastUtils.showShort("分享成功");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        ToastUtils.showShort("分享失败");
                    }
                }).open();
    }

    public static void savePicture(Bitmap bm, String fileName) {
        if (null == bm) {
            return;
        }
        File foder = new File(initFileRoot(MainApplication.getContext()) + "/5Afit");
        if (!foder.exists()) {
            if(foder.mkdirs()){
                Log.w("----","123456");
            }else{
                Log.w("----","78946");
            }
        }
        File myCaptureFile = new File(foder, fileName);
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            //压缩保存到本地
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();
    }

    public static String initFileRoot(Context context) {
        String environmentFileRoot = null;//文件根路径
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//sd卡是否可用
            int currentapiVersion=android.os.Build.VERSION.SDK_INT;//手机系统版本号
            Log.e("FileHelp","SDK_INT::"+currentapiVersion);
            if (currentapiVersion<android.os.Build.VERSION_CODES.Q){
                environmentFileRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
            }else {
                File external = context.getExternalFilesDir(null);
                if (external != null) {
                    environmentFileRoot =  external.getAbsolutePath();
                }
            }
        }else {
            environmentFileRoot= context.getFilesDir().getAbsolutePath();
        }
        Log.e("FileHelp","environmentFileRoot::"+environmentFileRoot);
        return environmentFileRoot;
    }
}
