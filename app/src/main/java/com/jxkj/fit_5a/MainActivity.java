package com.jxkj.fit_5a;


import android.Manifest;
import android.app.Dialog;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.BlackListBean;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TTSUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.entity.ParameterBean;
import com.jxkj.fit_5a.entity.UserReportBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingJfActivity;
import com.jxkj.fit_5a.view.fragment.HomeFourFragment;
import com.jxkj.fit_5a.view.fragment.HomeOneFragment;
import com.jxkj.fit_5a.view.fragment.HomeThreeNewFragment;
import com.jxkj.fit_5a.view.fragment.HomeTwoFragment;
import com.jxkj.fit_5a.view.map.LocationSelectActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.iv_main_1)
    ImageView mIvMain1;
    @BindView(R.id.tv_main_1)
    TextView mTvMain1;
    @BindView(R.id.ll_dialog)
    LinearLayout ll_dialog;
    @BindView(R.id.ll_main_1)
    LinearLayout mLlMain1;
    @BindView(R.id.iv_icon_add)
    ImageView iv_icon_add;
    @BindView(R.id.iv_main_2)
    ImageView mIvMain2;
    @BindView(R.id.tv_main_2)
    TextView mTvMain2;
    @BindView(R.id.ll_main_2)
    LinearLayout mLlMain2;
    @BindView(R.id.iv_main_3)
    ImageView mIvMain3;
    @BindView(R.id.tv_main_3)
    TextView mTvMain3;
    @BindView(R.id.ll_main_3)
    LinearLayout mLlMain3;
    @BindView(R.id.iv_main_4)
    ImageView mIvMain4;
    @BindView(R.id.tv_main_4)
    TextView mTvMain4;
    @BindView(R.id.ll_main_4)
    LinearLayout mLlMain4;
    private Fragment mFragments;
    private HomeOneFragment mHomeOneFragment_1;
    private HomeTwoFragment mHomeTwoFragment;
    private HomeThreeNewFragment mHomeThreeNewFragment;
    private HomeFourFragment mHomeFourFragment;
    private HomeOneFragment mHomeLsFragment;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    public static List<UserReportBean> mUserReportList;
    public static  List<BlackListBean> mBlackListBean;
    public static  List<String> mBlackListId;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        MainApplication.getContext().finishAllActivity(false);
        MainApplication.getContext().addActivity(this);
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5d01dffe");
        TTSUtils.getInstance().init();
        getMapDingWei();
        mHomeOneFragment_1 = HomeOneFragment.newInstance();
        mHomeLsFragment = HomeOneFragment.newInstance();
        mHomeTwoFragment = HomeTwoFragment.newInstance();
        mHomeThreeNewFragment = HomeThreeNewFragment.newInstance();
        mHomeFourFragment = HomeFourFragment.newInstance();

        fragmentManager = getSupportFragmentManager();
        mFragments = mHomeLsFragment;
        fragmentManager.beginTransaction().replace(R.id.fl_content, mHomeLsFragment, "LS").commitAllowingStateLoss();

        howFragment(1,mIvMain1,mTvMain1,mLlMain1);
        switchFragment(mHomeOneFragment_1,"A");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            howFragment(1,mIvMain1,mTvMain1,mLlMain1);
                            switchFragment(mHomeOneFragment_1,"A");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        boolean isCloseDiaLog = SharedUtils.singleton().get("iv_close_dialog",false);
        if(!isCloseDiaLog){
            ll_dialog.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ll_dialog.setVisibility(View.GONE);
                                SharedUtils.singleton().put("iv_close_dialog",true);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        Ble4_0Util.initLsData();
        openLocation();
        getParameterUp();
        getUserReportList();
        HttpRequestUtils.postBlackList(this,null);
        getusable_not_obtained();
    }

    private void getMapDingWei() {

        Log.w("requestCode:", "r:" + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            String[] permissions_12 = new String[]{Manifest.permission.READ_PHONE_STATE};
            // 只包括蓝牙这部分的权限，其余的需要什么权限自己添加
            if(!EasyPermissions.hasPermissions(this, permissions_12)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 200);
                EasyPermissions.requestPermissions(this, "为了您更好使用本应用，请允许应用获取以下权限", 1, permissions_12);
            } else {
                InitLocation();
            }
        } else {
            InitLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.w("requestCode:", "requestCode:"+requestCode );
        if(requestCode==200){
            InitLocation();
        }
    }

    private AMapLocationClient locationClient = null;//定位类
    //开始定位
    private void InitLocation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

        //设置为高精度定位模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位参数
        locationClient.setLocationOption(mLocationOption);
        // 设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation loc) {
                locationClient.stopLocation();
                locationClient.onDestroy();
                if (null != loc) {
                    //解析定位结果
                    SharedUtils.singleton().put(ConstValues.CITY_AD_CODE,loc.getAdCode());
                } else {
                    SharedUtils.singleton().put(ConstValues.CITY_AD_CODE,"330212");
                    Toast.makeText(MainActivity.this, "定位失败，请打开位置权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        locationClient.startLocation();
    }
    private void getUserReportList() {
        RetrofitUtil.getInstance().apiService()
                .getUserReportList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<UserReportBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<UserReportBean>> result) {
                        if(result.getCode()==0){
                            mUserReportList = result.getData();
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

    private void getParameterUp() {
        HttpRequestUtils.getVersionUpdating(this, new HttpRequestUtils.UploadFileInterface() {
            @Override
            public void succeed(String path) {

            }

            @Override
            public void failure() {

            }
        });
        RetrofitUtil.getInstance().apiService()
                .getParameter("home.bg.img")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ParameterBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ParameterBean> result) {
                        if (result.getCode()==0 && result.getData()!=null && StringUtil.isNotBlank(result.getData().getParamValue())) {
                            String paramValue = result.getData().getParamValue();
                            if(result.getData().getType()==2 && StringUtil.isNotBlank(paramValue)){
                                SharedUtilsNot.singletonNotClear().put(ConstValues.IMAGE_URL_FIRST_TYPE,"2");//标记是图片
                                SharedUtilsNot.singletonNotClear().put(ConstValues.IMAGE_URL_FIRST,paramValue);//图片的url
                            }else if(result.getData().getType()==4 && StringUtil.isNotBlank(paramValue)){
                                HttpRequestUtils.getPlayInfo(MainActivity.this,paramValue, new HttpRequestUtils.PlayInfoInterface() {
                                    @Override
                                    public void succeed(Result<VideoPlayInfoBean> result) {
                                        if(result.getCode()==0 && result.getData().getPlayInfoList()!=null){
                                            List<VideoPlayInfoBean.PlayInfoListBean> mBeans = result.getData().getPlayInfoList();
                                            if(mBeans.size()>0){
                                                //视频的第一针图
                                                SharedUtilsNot.singletonNotClear().put(ConstValues.IMAGE_URL_FIRST,result.getData().getVideoBase().getCoverURL());
                                                //视频的url
                                                SharedUtilsNot.singletonNotClear().put(ConstValues.IMAGE_URL_FIRST_PLAY,mBeans.get(0).getPlayURL());
                                                //视频的videoId
                                                SharedUtilsNot.singletonNotClear().put(ConstValues.IMAGE_URL_FIRST_TYPE,paramValue);

                                                HttpRequestUtils.initVideo(MainActivity.this,mBeans.get(0).getPlayURL(),paramValue);
                                            }
                                        }
                                    }
                                });
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


    @OnClick({R.id.ll_main_1, R.id.ll_main_2,R.id.iv_close_dialog, R.id.ll_main_3, R.id.ll_main_4,R.id.iv_icon_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close_dialog:
                ll_dialog.setVisibility(View.GONE);
                SharedUtils.singleton().put("iv_close_dialog",true);
                break;
            case R.id.ll_main_1:
                if(mIvMain1.isSelected() && isDoubleClicked()){
                    mHomeOneFragment_1.setDoubleClicked();
                    return;
                }
                howFragment(1,mIvMain1,mTvMain1,mLlMain1);
                switchFragment(mHomeOneFragment_1,"A");
                break;
            case R.id.ll_main_2:
                howFragment(2,mIvMain2,mTvMain2,mLlMain2);
                switchFragment(mHomeTwoFragment,"B");
                break;
            case R.id.ll_main_3:
                if(mIvMain3.isSelected() && isDoubleClicked()){
                    mHomeThreeNewFragment.setDoubleClicked();
                    return;
                }
                howFragment(3,mIvMain3,mTvMain3,mLlMain3);
                switchFragment(mHomeThreeNewFragment,"C");
                break;
            case R.id.ll_main_4:
                howFragment(4,mIvMain4,mTvMain4,mLlMain4);
                switchFragment(mHomeFourFragment,"D");
                break;
            case R.id.iv_icon_add:
                if(window!=null){
                    window.showAtLocation(iv_icon_add, Gravity.BOTTOM, 0, 0);
                    return;
                }
                initPopupWindow();
                break;
        }
    }

    PopupWindowTy window;

    private void initPopupWindow() {
        List<String> list = new ArrayList<>();
        list.add("发布图文");
        list.add("发布视频");
        if (window == null) {
            window = new PopupWindowTy(this, list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    if (position == 0) {
                        AssociationAddActivity.startActivityAddAssociation(MainActivity.this,2,"","","","");
                    }
                    if (position == 1) {
                        AssociationAddActivity.startActivityAddAssociation(MainActivity.this,3,"","","","");
                    }
                    window.dismiss();
                }
            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        window.showAtLocation(iv_icon_add, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }

    private static final long CLICK_INTERVAL_TIME = 300;
    private static long lastClickTime = 0;

    /**
     * 双击事件
     * @return
     */
    private boolean isDoubleClicked() {
        //获取系统当前毫秒数，从开机到现在的毫秒数(手机睡眠时间不包括在内)
        long currentTimeMillis = SystemClock.uptimeMillis();
        //两次点击间隔时间小于300ms代表双击
        if (currentTimeMillis - lastClickTime < CLICK_INTERVAL_TIME) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }

    public static  boolean isRenWu = false;
    @Override
    protected void onResume() {
        super.onResume();
//        PopupWindowLanYan.outType_Activity = 0;
        Log.w("onResume","isRenWu"+isRenWu);
        if(isRenWu){
            isRenWu = false;
            howFragment(2,mIvMain2,mTvMain2,mLlMain2);
            switchFragment(mHomeTwoFragment,"B");
        }
    }

    private void howFragment(int pos, ImageView iv, TextView tv, LinearLayout ll){
        mIvMain1.setSelected(false);
        mIvMain2.setSelected(false);
        mIvMain3.setSelected(false);
        mIvMain4.setSelected(false);
        GlideImageUtils.setGlideImage(this,R.drawable.icon_main_1_no,mIvMain1);
        GlideImageUtils.setGlideImage(this,R.drawable.icon_main_2_no,mIvMain2);
        GlideImageUtils.setGlideImage(this,R.drawable.icon_main_3_no,mIvMain3);
        GlideImageUtils.setGlideImage(this,R.drawable.icon_main_4_no,mIvMain4);

        mTvMain1.setTextColor(getColor(R.color.color_333333));
        mTvMain2.setTextColor(getColor(R.color.color_333333));
        mTvMain3.setTextColor(getColor(R.color.color_333333));
        mTvMain4.setTextColor(getColor(R.color.color_333333));

        mLlMain1.setBackgroundColor(getColor(R.color.color_ffffff));
        mLlMain2.setBackgroundColor(getColor(R.color.color_ffffff));
        mLlMain3.setBackgroundColor(getColor(R.color.color_ffffff));
        mLlMain4.setBackgroundColor(getColor(R.color.color_ffffff));

        iv.setSelected(true);
        tv.setTextColor(getColor(R.color.color_text_theme));
        ll.setBackground(getDrawable(R.drawable.btn_shape_bj_theme_99));
        if(pos == 1){
            showGifImg(mIvMain1,R.drawable.icon_main_1_yse);
        }else if(pos == 2) {
            showGifImg(mIvMain2,R.drawable.icon_main_2_yse);
        }else if(pos == 3) {
            showGifImg(mIvMain3,R.drawable.icon_main_3_yse);
        }else if(pos == 4) {
            showGifImg(mIvMain4,R.drawable.icon_main_4_yse);
        }
    }
    private void showGifImg(ImageView img_gif,int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            img_gif.setImageResource(resId);
            Drawable drawable = img_gif.getDrawable();
            if(drawable instanceof AnimatedImageDrawable){
                AnimatedImageDrawable mAnimatedImageDrawable = (AnimatedImageDrawable) drawable;
                mAnimatedImageDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                    @Override
                    public void onAnimationStart(Drawable drawable) {
                        super.onAnimationStart(drawable);
                        Log.e("AnimatedImageDrawable", "start");
                    }

                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        Log.e("AnimatedImageDrawable", "stop");
                    }
                });

                mAnimatedImageDrawable.setRepeatCount(0);
                mAnimatedImageDrawable.start();
            }
        }
    }


    /**
     * 切换Fragment
     * <p>(hide、show、add)
     */
    private void switchFragment(Fragment mCurrentFragment, String tag) {
        if (mFragments != mCurrentFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!mCurrentFragment.isAdded() && null == fragmentManager.findFragmentByTag(tag)) {    // 先判断是否被add过
                fragmentTransaction.hide(mFragments).add(R.id.fl_content, mCurrentFragment, tag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中, 并添加已显示存在的fangment唯一标示tag
            } else {
                fragmentTransaction.hide(mFragments).show(mCurrentFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
            mFragments = mCurrentFragment;
        }
    }


    private void openLocation() {
        String[] params = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(MainActivity.this, params)) {//未开启定位权限
            //开启定位权限,200是标识码
            EasyPermissions.requestPermissions(MainActivity.this, "为了您更好使用本应用，请允许应用获取以下权限", 200, params);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Ble4_0Util.initLsData();
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.w("onKeyDown","event:"+keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(!mIvMain1.isSelected()){
                howFragment(1,mIvMain1,mTvMain1,mLlMain1);
                switchFragment(mHomeOneFragment_1,"A");
                return true;
            }
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showShortToast(this,"再按一次后退键退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    Dialog dialog;

    private void getusable_not_obtained() {
        RetrofitUtil.getInstance().apiService()
                .getusable_not_obtained()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DiscountUsableNotBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DiscountUsableNotBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() == null || result.getData().getList() == null || result.getData().getList().size() == 0) {
                                return;
                            }
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                                dialog = null;
                            }
                            dialog = DialogUtils.showDiaYouHuiQuan(MainActivity.this, result.getData().getList(),
                                    new DialogUtils.DialogInterfaceYhq() {
                                        @Override
                                        public void btnConfirm(int id) {
                                            if (id == -1) {
                                                List<Integer> lists = new ArrayList<>();
                                                for (int i = 0; i < result.getData().getList().size(); i++) {
                                                    lists.add(result.getData().getList().get(i).getId());
                                                }
                                                getPrizeReceives(lists);
                                            } else {
                                                getPrizeReceive(id);
                                            }
                                        }
                                    });
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
    private void getPrizeReceives(List<Integer> lists) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceives(lists)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
//                            getusable_not_obtained();
                            dialog.dismiss();
                            ToastUtils.showShort("领取成功");
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
    private void getPrizeReceive(int id) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceive(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            getusable_not_obtained();
                            ToastUtils.showShort("领取成功");
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
}
