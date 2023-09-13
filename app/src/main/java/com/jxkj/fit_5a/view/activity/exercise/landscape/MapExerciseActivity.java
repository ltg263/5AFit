package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StatusBarUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.conpoment.view.RobotView;
import com.jxkj.fit_5a.conpoment.view.RobotViewSmall;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.entity.BoxReceiveBean;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZMediaSystem;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

/**
 *
 */
public class MapExerciseActivity extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.iv_go_img)
    ImageView iv_go_img;
    @BindView(R.id.iv_img)
    RobotView iv_img;
    @BindView(R.id.iv_img_)
    RobotViewSmall iv_img_;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.rl_tv_time)
    RelativeLayout rl_tv_time;
    @BindView(R.id.tv_quan)
    TextView tv_quan;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.mp_video)
    JzvdStdTikTok mMpVideo;
    boolean isSuo = true;
    String mapId;
    private MyReceiver mMyReceiver;
    int loadCurrent = 1;
    int loadMax = ConstValues_Ly.maxLoad;

    int maxV = 220;
    int bfb5,bfb6,bfb7,bfb8,bfb9,bfb;
    int age = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_AGE,"0"));
    private ArrayList<BpmDataBean> mBpmDataBeans = new ArrayList<>();
    private int time_z=0;
    private byte[] loadLists;
    double userWeiget = 75;
    public Bitmap mBitmapBoxOpen,mBitmapBoxColex,mBitmapRed;
    MediaPlayer mediaPlayer;
    public static void intentStartActivity(Context mContext, String mapId) {
        //权限判断，如果没有权限就请求权限
        if(!EasyPermissions.hasPermissions(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            EasyPermissions.requestPermissions((Activity) mContext, "为了您更好使用本应用，请允许应用获取以下权限", 1, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return;
        }
        Intent intent = new Intent(mContext, MapExerciseActivity.class);
        intent.putExtra("mapId", mapId);
        mContext.startActivity(intent);
    }

    public static void intentStartActivityKc(Context mContext, int time, byte[] loadLists, String mapId) {
        //权限判断，如果没有权限就请求权限
        if(!EasyPermissions.hasPermissions(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            EasyPermissions.requestPermissions((Activity) mContext, "为了您更好使用本应用，请允许应用获取以下权限", 1, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return;
        }
        Intent intent = new Intent(mContext, MapExerciseActivity.class);
        intent.putExtra("mapId", mapId);
        intent.putExtra("time", time);
        intent.putExtra("loadLists",loadLists);
        mContext.startActivity(intent);
    }
    View view;
    boolean isStart_56 = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去掉信息栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒
        setContentView(R.layout.activity_landscape_map_exercise);
        ButterKnife.bind(this);
//        mBitmapHeadImg = getImgBitmap();
        view = View.inflate(this, R.layout.item_img_head, null);
        RoundImageView image_iv =  view.findViewById(R.id.image_iv);
        Glide.with(this).load(SharedUtils.singleton().get(ConstValues.USER_IMG,"")).into(image_iv);
        mBitmapBoxOpen = getBoxBitmap();
        mBitmapBoxColex = getBoxBitmap_G();
        mBitmapRed = getImgBitmapRed();
        mediaPlayer = MediaPlayer.create(this, R.raw.ready_go);
        mediaPlayer.setLooping(false);//设置为循环播放
        mediaPlayer.start();
        iv_go_img.setVisibility(View.VISIBLE);
        mMpVideo.setMap(true);
        userWeiget = Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_WEIGHT, "0"));
        if(mMpVideo.mediaInterface==null){
            mMpVideo.mediaInterface = new JZMediaSystem(mMpVideo);
        }
//        GlideImageLoader.loadOneTimeGif(this, R.drawable.ic_yundong_go, iv_go_img, new GlideImageLoader.GifListener() {
//            @Override
//            public void gifPlayComplete() {
//                iv_go_img.setVisibility(View.GONE);
//                iv_go_img.setImageResource(R.drawable.icon);
//            }
//        });

        GlideImageLoader.loadOneTimeGif(this, R.drawable.ic_yundong_go, iv_go_img, new GlideImageLoader.GifListener() {
            @Override
            public void gifPlayComplete() {
                iv_go_img.setVisibility(View.GONE);
                iv_go_img.setImageResource(R.drawable.icon);

                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                } else if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
//                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]) {
                    isStart_56 = true;
                    duration = 1;
//                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5));
//                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2]) {
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]) {
//                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                    DialogUtils.showUnificationDialog(MapExerciseActivity.this, "提示","请按设备Start/开始按钮来开始", "确定",false,null);
//                    DialogUtils.showDialogHint(MapExerciseActivity_Zx.this, "请按设备Start/开始按钮来开始", true, null);
                }
                startTimestamp = System.currentTimeMillis();

                /**
                 * 广播动态注册
                 */
                mMyReceiver = new MyReceiver();//集成广播的类
                IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
                registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive

            }
        });

//        if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]){
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
//        } else if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
//        } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]) {
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5));
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
//        } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2]) {
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
//        } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]) {
//            DialogUtils.showUnificationDialog(this, "提示","请按设备Start/开始按钮来开始", "确定",false,null);
//        }

        startTimestamp = System.currentTimeMillis();
        StyleKitName.mPathMeasure = null;
        StyleKitName.mCurrentPosition = null;
        StyleKitName.mPathMeasure = null;
        RobotViewSmall.mCurrentPosition = null;
        /**
         * 广播动态注册
         */
//        mMyReceiver = new MyReceiver();//集成广播的类
//        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
//        registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive

        initViews();
        initBpmData();
    }

    private void initBpmData() {
        bfb5 = (int) ((maxV-age)*0.5);
        bfb6 = (int) ((maxV-age)*0.6);
        bfb7 = (int) ((maxV-age)*0.7);
        bfb8 = (int) ((maxV-age)*0.8);
        bfb9 = (int) ((maxV-age)*0.9);
        bfb  = maxV-age;
        mBpmDataBeans.add(new BpmDataBean("非运动区间(0~50%)",0,bfb5,0));
        mBpmDataBeans.add(new BpmDataBean("热身心率区间(50~60%)",bfb5,bfb6,0));
        mBpmDataBeans.add(new BpmDataBean("燃脂心率区间(60~70%)",bfb6,bfb7,0));
        mBpmDataBeans.add(new BpmDataBean("有氧耐力心率区间(70~80%)",bfb7,bfb8,0));
        mBpmDataBeans.add(new BpmDataBean("无氧耐力心率区间(80~90%)",bfb8,bfb9,0));
        mBpmDataBeans.add(new BpmDataBean("极限心率区间(90~100%)",bfb9,bfb,0));
    }

    PopupWindowTopicUtils_Map window;

    private float mPosX;
    private float mPosY;
    private float mCurrentPosX;
    private float mCurrentPosY;

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        mapId = getIntent().getStringExtra("mapId");
        time_z = getIntent().getIntExtra("time",0);
        loadLists = getIntent().getByteArrayExtra("loadLists");
        if(StringUtil.isNotBlank(mapId)){
            getMapDetails();
        }else{
            getMapRandomDetails();
        }
        if(time_z!=0){
            time_z=time_z*60;
        }
        if(loadLists!=null && loadLists.length>0){
            loadCurrent = loadLists[0];
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, loadLists[0]));
        }

        if (window == null) {
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity.this, true,type -> {
                if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]){
//                    DialogUtils.showDialogHint(this, "请按设备Start/开始按钮来开始", true, null);
                    DialogUtils.showUnificationDialog(this, "提示","请按设备Start/开始按钮来开始", "确定",false,null);
                    return;
                }
                if (type == 0) {
                    iv_img.setState(type);
                    iv_img_.setState(type);
                    if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                    }else if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]){
                        isStart_56 = false;
                    }
                } else if (type == 1) {
                    iv_img.setState(type);
                    iv_img_.setState(type);
                    if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                    }else if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]){
                        isStart_56 = true;
                    }
                } else if (type == 2) {
                    if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                            && loadCurrent<loadMax){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent+1)));

                    }
                }else if (type == 3) {
                    if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                            && loadCurrent>1){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent-1)));
                    }
                }

            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        iv_img.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // 按下
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    // 移动
                    case MotionEvent.ACTION_MOVE:
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();
                        if (mCurrentPosX - mPosX > 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向右");
                        else if (mCurrentPosX - mPosX < 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向左");
                        else if (mCurrentPosY - mPosY > 0 && Math.abs(mCurrentPosX - mPosX) < 10) {
                            Log.e("", "向下");
                            if (!isSuo) {
                                isSuo = true;
                                PopupWindowTopicUtils_Map.isTop = true;
                                rl_tv_time.setVisibility(View.VISIBLE);
//                                mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                                if (window != null && window.isShowing()) {
                                    window.dismiss();
                                }
                            }
                        } else if (mCurrentPosY - mPosY < 0 && Math.abs(mCurrentPosX - mPosX) < 10) {
                            Log.e("", "向上");
                            if (isSuo) {
                                showWindowStye();
                            }
                        }
                        break;
                    // 拿起
                    case MotionEvent.ACTION_UP:

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && window!=null){
            showWindowStye();
        }
    }

    @OnClick({R.id.iv_1, R.id.iv_2,R.id.iv_3, R.id.iv_4,R.id.iv_shang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                if (mIv3.getVisibility() == View.VISIBLE) {
                    mIv2.setVisibility(View.GONE);
//                    mIv2.setAnimation(AnimationUtils.makeOutAnimation(this, true));
                    mIv3.setVisibility(View.GONE);
//                    mIv3.setAnimation(AnimationUtils.makeOutAnimation(this, true));
                    mIv4.setVisibility(View.GONE);
//                    mIv4.setAnimation(AnimationUtils.makeOutAnimation(this, true));
                    mIv1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_yd_12));
                } else {
                    mIv2.setVisibility(View.VISIBLE);
//                    mIv2.setAnimation(AnimationUtils.makeInAnimation(this, true));
                    mIv3.setVisibility(View.VISIBLE);
//                    mIv3.setAnimation(AnimationUtils.makeInAnimation(this, true));
                    mIv4.setVisibility(View.VISIBLE);
//                    mIv4.setAnimation(AnimationUtils.makeInAnimation(this, true));
                    mIv1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_yd_121));
                    isSuo = true;
                    PopupWindowTopicUtils_Map.isTop = true;
                    rl_tv_time.setVisibility(View.VISIBLE);
                    if (window != null && window.isShowing()) {
                        window.dismiss();
                    }
                }
                break;
            case R.id.iv_2:
                if(duration<=0){
                    return;
                }
                if (!isSuo) {
                    isSuo = true;
                    PopupWindowTopicUtils_Map.isTop = true;
                    rl_tv_time.setVisibility(View.VISIBLE);
//                                mIv2.setImageDrawable(MapExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                    if (window != null && window.isShowing()) {
                        window.dismiss();
                    }
                }
                isMaxMap = !isMaxMap;
                iv_img.setType(isMaxMap);
                iv_img.setOnClickAnimationMap();
                if(isMaxMap){
                    mIv2.setImageResource(R.mipmap.ic_map_zoom_out);
                }else{
                    mIv2.setImageResource(R.mipmap.ic_map_zoom_in);
                }
                break;
            case R.id.iv_3:
                if(StringUtil.isNotBlank(realSceneUrl)){
                    if(iv_img.getAlpha()==1.0f){
                        iv_img.setAlpha(0.0f);
                        mMpVideo.setSilence(false);
                        mMpVideo.mediaInterface.setVolume(1f, 1f);
                        mRl.setBackgroundResource(R.drawable.bj_circle_fff_4_tm);
                        mIv3.setImageResource(R.mipmap.ic_map_pingmian);
                    }else{
                        iv_img.setAlpha(1.0f);
                        mMpVideo.setSilence(true);
                        mMpVideo.mediaInterface.setVolume(0f, 0f);
                        mRl.setBackgroundResource(R.drawable.bj_circle_fff_4_tm);
                        mIv3.setImageResource(R.mipmap.ic_map_shipin);
                    }
                }else{
                    ToastUtils.showShort("此地图无实景图");
                }
                break;
            case R.id.iv_shang:
                showWindowStye();
                break;
            case R.id.iv_4:
                outRoom();
                break;
        }
    }

    private void showWindowStye() {
        isSuo = false;
        PopupWindowTopicUtils_Map.isTop = true;

        mIv2.setVisibility(View.GONE);
//                    mIv2.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        mIv3.setVisibility(View.GONE);
//                    mIv3.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        mIv4.setVisibility(View.GONE);
//                    mIv4.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        mIv1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_yd_12));

        window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
        rl_tv_time.setVisibility(View.GONE);
    }

    boolean isMaxMap = false;
    private List<MapDetailsBean.BoxsBean> mBoxs;

    private void getMapDetails() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapDetailsBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getMapDetails_al(mapId);
        }else {
            mObservable = mApiService.getMapDetails(mapId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        setMapDetailsInfo(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
    private void getMapRandomDetails() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapDetailsBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getMapRandomDetails_al(ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }else {
            mObservable = mApiService.getMapRandomDetails(ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        setMapDetailsInfo(result);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    String realSceneUrl;
    private void setMapDetailsInfo(Result<MapDetailsBean> result) {
        if (result.getCode() == 0 && result.getData() != null) {
            mapId = result.getData().getId();
            distance = Double.parseDouble(result.getData().getDistance());
            mBoxs = result.getData().getBoxs();
            mTvDistance.setText(distance + "km");
//                            if (Double.valueOf(distance) > 1000) {
//                                mTvDistance.setText(StringUtil.getValue(Double.valueOf(distance) / 1000d) + "km");
//                            }
            GlideImageUtils.setGlideImage(MapExerciseActivity.this, result.getData().getImgUrl(),iv_img);
            if (result.getData().getInfo() != null && result.getData().getInfo().size() > 0) {
                iv_img.setData(mBoxs,mBitmapBoxOpen,result.getData().getInfo(), getImgBitmap(),mBitmapBoxColex,
                        Double.parseDouble(result.getData().getDistance()),result.getData().getParam());
                iv_img_.setData(mBitmapRed,Double.parseDouble(result.getData().getDistance()),
                        result.getData().getInfo(), result.getData().getParam());
            }
            realSceneUrl = result.getData().getRealSceneUrl();
            if(StringUtil.isNotBlank(realSceneUrl)){
                HttpRequestUtils.getPlayInfo(MapExerciseActivity.this,realSceneUrl, new HttpRequestUtils.PlayInfoInterface() {

                    @Override
                    public void succeed(Result<VideoPlayInfoBean> result) {
                        if(result.getCode()==0 && result.getData().getPlayInfoList()!=null){
                            List<VideoPlayInfoBean.PlayInfoListBean> mBeans = result.getData().getPlayInfoList();
                            if(mBeans.size()>0){
                                Glide.with(MapExerciseActivity.this).load(result.getData().getVideoBase().getCoverURL()).into(mMpVideo.posterImageView);
                                String playUrl = HttpRequestUtils.initVideo(MapExerciseActivity.this,mBeans.get(0).getPlayURL(),realSceneUrl);
                                mMpVideo.setUp(playUrl, MyVideoPlayer.PLAY_STATE_EXERCISE, MyVideoPlayer.STATE_NORMAL);
                                mMpVideo.setSilence(true);
                            }
                        }
                    }
                });
            }
        }else{
//            DialogUtils.showDialogHint(MapExerciseActivity.this, result.getMesg(), true,
//                    new DialogUtils.ErrorDialogInterface() {
//                        @Override
//                        public void btnConfirm() {
//                            finish();
//                        }
//                    });
            DialogUtils.showUnificationDialog(this, "提示", result.getMesg(), "确定", false, new DialogUtils.UnificationDialogInterface() {
                @Override
                public void bntClickListener(String pos) {
                    finish();
                }
            });
        }
    }

    public Bitmap getImgBitmap() {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.setBackgroundColor(Color.TRANSPARENT);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache();
    }
    public Bitmap getImgBitmapRed() {
        View view = View.inflate(this, R.layout.item_img_head_red, null);
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.setBackgroundColor(Color.TRANSPARENT);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache(true);
    }
    public Bitmap getBoxBitmap() {
        View view = View.inflate(this, R.layout.item_img_box, null);
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.setBackgroundColor(Color.TRANSPARENT);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache(true);
    }
    public Bitmap getBoxBitmap_G() {
        View view = View.inflate(this, R.layout.item_img_box_g, null);
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.setBackgroundColor(Color.TRANSPARENT);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)){
            outRoom();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    boolean isOutRoom = false;
    private void outRoom() {
//        DialogUtils.showDialogOutRoom(MapExerciseActivity.this, new DialogUtils.DialogLyInterface() {
//            @Override
//            public void btnConfirm() {
//                psotUserSportLog();
//            }
//        });
        DialogUtils.showUnificationDialog(MapExerciseActivity.this, "结束运动","您确定要结束运动吗？", "结束",true,
                 new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        psotUserSportLog();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMyReceiver != null) {
            unregisterReceiver(mMyReceiver);
        }
    }

    private void psotUserSportLog() {
        isOutRoom = true;
        if(duration==0 || Calories==0){
            PopupWindowLanYan.ble4Util.disconnect();
            finish();
            return;
        }
        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
        Intent mIntent = new Intent(this, MapExerciseFinishActivity.class);
        String str = String.valueOf(Distance / duration * 60d * 60d);
        String pjDuration = String.valueOf((int)(Distance / (duration/60d/60d) *100)/100d);
//        if (str.contains(".")) {
//            pjDuration = str.format("%.2f");
//        }
        double MaxSpeed = Double.parseDouble(logs.get(0).getSpeed());
        int load_D = 0;
        int load_X =  Integer.parseInt(logs.get(0).getResistanceLevel());
        for (int i = 0; i < logs.size(); i++) {

            if (StringUtil.isNotBlank(logs.get(i).getSpeed()) && Double.parseDouble(logs.get(i).getSpeed()) > MaxSpeed) {
                MaxSpeed = Double.parseDouble(logs.get(i).getSpeed());
            }
            if(StringUtil.isNotBlank(logs.get(i).getResistanceLevel()) && load_D<Integer.parseInt(logs.get(i).getResistanceLevel())){
                load_D = Integer.parseInt(logs.get(i).getResistanceLevel());
            }
            if(StringUtil.isNotBlank(logs.get(i).getResistanceLevel()) && load_X>Integer.parseInt(logs.get(i).getResistanceLevel())){
                load_X = Integer.parseInt(logs.get(i).getResistanceLevel());
            }
        }
        String load_dx = load_X+"-"+load_D;
        if(load_X==load_D){
            load_dx = load_D+"";
        }

        Log.w("MaxSpeed:","MaxSpeed"+MaxSpeed);
        mBpmDataBeans.get(0).setBpmTopData(
                new BpmDataBean.BpmTopData(String.valueOf(Calories), String.valueOf(Distance),
                        duration + "", pjDuration, String.valueOf(MaxSpeed),
                        "0",Watt+"",load_dx,"0",StringUtil.getBai_V(logs)));
        mIntent.putParcelableArrayListExtra("mBpmDataBeans", mBpmDataBeans);
        mIntent.putParcelableArrayListExtra("logs",logs);

        PostUser.SportLogInfo sportLogInfo = new PostUser.SportLogInfo();

        sportLogInfo.setBai(StringUtil.getBai_V(logs));
        sportLogInfo.setMapId(mapId);
        sportLogInfo.setDeviceBrandId(ConstValues_Ly.BRAND_ID);
        sportLogInfo.setCalories(String.valueOf(Calories));
        sportLogInfo.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL + "");
        sportLogInfo.setDistance(String.valueOf(Distance));
        sportLogInfo.setDuration(String.valueOf(duration));
        sportLogInfo.setEndTimestamp(String.valueOf(System.currentTimeMillis()));
        sportLogInfo.setStartTimestamp(String.valueOf(startTimestamp));
        sportLogInfo.setProtocolName("iconsole");
        sportLogInfo.setProtocolDeviceBrandParamId(null);
        sportLogInfo.setHeartRateSource("2");//1=器材;2=藍牙心跳;3=Apple Watch
        sportLogInfo.setTrainingMode("QuickStart");//目前只有HeartRate(心率)、Program(课程)
        PostUser.SportLogInfo.DetailsBean deleteDatabase = new PostUser.SportLogInfo.DetailsBean();
        deleteDatabase.setLogs(logs);
        sportLogInfo.setDetails(deleteDatabase);
        HttpRequestUtils.psotUserSportLog(sportLogInfo, new HttpRequestUtils.LoginInterface() {
            @Override
            public void succeed(String data_id) {
                Log.w("data_id","data_id"+data_id);
                mIntent.putExtra("data_id",data_id);
                startActivity(mIntent);
                iv_img.setCancel();
                iv_img_.setCancel();
                iv_img = null;
                iv_img_ = null;
                finish();
                PopupWindowLanYan.ble4Util.disconnect();
            }
        });
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("MyTag", "onReceive: " + intent.getStringExtra("type"));
            if (intent.getStringExtra("type").equals("b2")) {
                ArrayList<Integer> dataList = intent.getIntegerArrayListExtra("data");
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0] && dataList.size() == 16) {
                    setData1(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1] && dataList.size() == 14) {
                    setData56(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3] && dataList.size() == 18) {
                    setData26(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4] && dataList.size() == 14) {
                    setData46(dataList);
                }
//                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2] && dataList.size() == 14) {
//                    //健腹轮
//                }
            }
        }
    }

    Double distance = 0.0;//总距离
    double Distance_56;
    double Distance;
    long duration;
    int Calories;
    double Calories_56;
    long startTimestamp;
    double Watt;

    ArrayList<PostUser.SportLogInfo.DetailsBean.LogsBean> logs = new ArrayList<>();
    private void setData1(ArrayList<Integer> dataList) {
        int timeMinute = dataList.get(0);//时间-分
        int timeSecond = dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute, timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi, speedLow);

        int rpmHi = dataList.get(4);//每分钟转数 -千百
        int rpmLow = dataList.get(5);//每分钟转数 -十个
        int rpm = ConstValues_Ly.getQianBaiShiGe(rpmHi, rpmLow);

        int DistanceHi = dataList.get(6);//距离-百十
        int DistanceLow = dataList.get(7);//距离-个小数点下一位
        Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi, DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi, CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi, PulseLow);
        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        Watt = ConstValues_Ly.getBaiShiGeX(WattHi, WattLow);

        loadCurrent = dataList.get(14);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(15);
        String Unit = "Stop";
        if (dataList.get(15) == 1) {
            Unit = "Start";
        }
        // 时间   速度  转数  距离  卡路里   心率  功率    阻力  状态
        String re = "A2--->>>:setData1 时间：" + ZTime + ",速度：" + speed + ",转数：" + rpm + ",距离：" + Distance + ",卡路里：" + Calories+ ",脉跳：" + Pulse + ",瓦特：" + Watt + ",阻力：" + loadCurrent + ",状态：" + Unit;
        Log.w("---》》》", re);

        if(StringUtil.isNotBlank(realSceneUrl)){
            if (speed>0) {//播放
                if(!mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }else{//暂停
                if(mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }
        }
        if (Unit.equals("Stop")) {
            window.setIvSelect(true);
            iv_img.setState(1);
            iv_img_.setState(1);
            return;
        }
        window.setTextLoad(loadCurrent+"/"+loadMax);
        window.setIvSelect(false);
        String str = ""+duration;

        if (speed != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil((Distance * 1000d)/(distance * 1000d));
            if(Distance % distance==0){
                quanNum++;
            }
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText("第"+quanNum+"圈");//500-500000
            iv_img.setRed(Math.round((distance * 1000d -(Distance * 1000d-distance*1000*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
            iv_img_.setRed(Math.round((distance * 1000d -(Distance * 1000d-distance*1000*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (speed == 0) {
                iv_img.setState(1);
                iv_img_.setState(1);
            } else {
                iv_img.setState(0);
                iv_img_.setState(0);
            }
        }
        getUserBoxReceive(Distance);
        setLoad(duration);
        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance+"", speed + "", ZTime, Calories + "", Watt + "", Pulse + "","0");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm),String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
    }
    private void getUserBoxReceive(double distance){
        if(mBoxs==null){
            return;
        }
        for(int i =0;i<mBoxs.size();i++){
            if(!mBoxs.get(i).getReceiveInfo().isHaving() && distance>=mBoxs.get(i).getDistance()){
                getBoxReceive(i);
            }
        }
    }

    private void getBoxReceive(int pos) {
        String sportBoxId = mBoxs.get(pos).getSportBoxId();
        mBoxs.get(pos).getReceiveInfo().setHaving(true);
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<BoxReceiveBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getBoxReceive_al(sportBoxId,mapId);
        }else {
            mObservable = mApiService.getBoxReceive(sportBoxId,mapId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<BoxReceiveBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<BoxReceiveBean> result) {
                        if(result.getCode()==0){
                            iv_go_img.setVisibility(View.VISIBLE);
                            GlideImageLoader.loadOneTimeGif(MapExerciseActivity.this, R.drawable.ic_baoxiang,
                                    iv_go_img, new GlideImageLoader.GifListener() {
                                        @Override
                                        public void gifPlayComplete() {
                                            iv_go_img.setVisibility(View.GONE);
                                            iv_go_img.setImageResource(R.drawable.icon);
                                            if(result.getData().getReward()!=null
                                                    && StringUtil.isNotBlank(result.getData().getReward().getName())){
                                                ToastUtils.showShort(result.getData().getReward().getName());
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
    String ZTime = "00:00";
    private void setData56(ArrayList<Integer> dataList) {
//        int timeMinute =  dataList.get(0);//时间-分
//        int timeSecond =  dataList.get(1);//时间-秒

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int rpm1Hi = dataList.get(4);//每分钟转数 -千百
        int rpm1Low = dataList.get(5);//每分钟转数 -十个

        int rpm1 = ConstValues_Ly.getQianBaiShiGe(rpm1Hi,rpm1Low);
        int rpm2Hi = dataList.get(6);//每分钟转数 -千百
        int rpm2Low = dataList.get(7);//每分钟转数 -十个
        int rpm2 = ConstValues_Ly.getQianBaiShiGe(rpm2Hi,rpm2Low);

        int DistanceHi = dataList.get(8);//距离-百十
        int DistanceLow = dataList.get(9);//距离-个小数点下一位
//        Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(10);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(11);// 卡路里 -个十
//        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(12);//跳动 千,佰
        int PulseLow = dataList.get(13);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        double perimeter = ConstValues_Ly.wheelDiameter * 2.54/*inch*/ * Math.PI/*PI*/ / 100000.0/*cm to km*/;
        speed = perimeter * 60/*minutes of hour*/ * rpm1;//km/h
//        Log.w("---》》》", "A2--->>>setData56:时间：距离："+speed/3600d);
        speed = Double.parseDouble(StringUtil.getValue(speed));

        if(isStart_56){
            duration++;
            ZTime = StringUtil.getTimeToYMD(duration*1000,"mm:ss");
        }else{
            speed = 0;
        }

        Distance_56 += speed/3600d;
        Distance = Double.parseDouble(StringUtil.getValue(Distance_56));

        Calories_56 += speed * userWeiget *1.038* (1/3600d);
        Calories = (int) Calories_56;
        String re = "A2--->>>setData56:时间："+duration+",速度："+speed+",转数1："+rpm1+",转数2："+rpm2+",距离："+Distance+",卡路里："+Calories+",脉跳："+Pulse;
        Log.w("---》》》", re);

        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance+ "", speed + "", ZTime, Calories + "", 0 + "", Pulse + "","0");


        if(StringUtil.isNotBlank(realSceneUrl)){
            if (speed>0) {//播放
                if(!mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }else{//暂停
                if(mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }
        }
        if(!isStart_56){
            window.setIvSelect(true);
            iv_img.setState(1);
            iv_img_.setState(1);
            return;
        }

        String str = ""+duration;
        window.setIvSelect(false);
        if (speed != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil((Distance * 1000d)/(distance* 1000d));
            if(Distance % distance==0){
                quanNum++;
            }
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText("第"+quanNum+"圈");
            iv_img.setRed(Math.round((distance* 1000d -(Distance * 1000d-distance*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
            iv_img_.setRed(Math.round((distance* 1000d -(Distance * 1000d-distance*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (speed == 0) {
                iv_img_.setState(1);
                iv_img.setState(1);
            } else {
                iv_img_.setState(0);
                iv_img.setState(0);
            }
        }

        getUserBoxReceive(Distance);
        setLoad(duration);
        setBpmDataBeanTime(Pulse);
        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm1),String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),null));
    }


    private void setData26(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int strokeHi = dataList.get(2);
        int strokeLow = dataList.get(3);
        int stroke = ConstValues_Ly.getQianBaiShiGe(strokeHi,strokeLow);

        int spmHi = dataList.get(4);
        int spmLow = dataList.get(5);
        int spm = ConstValues_Ly.getQianBaiShiGe(spmHi,spmLow);

        int DistanceHi = dataList.get(6);
        int DistanceLow = dataList.get(7);
        Distance = ConstValues_Ly.getQianBaiShiGe(DistanceHi,DistanceLow)/1000d;

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

//        int timeMinute1 =  dataList.get(14);//时间-分
        int timeMinute1 =  dataList.get(15);//时间-秒
//        int duration1 = timeMinute * 60 + timeSecond;
//        String time1 = ConstValues_Ly.getTime(timeMinute1,timeSecond1);

        loadCurrent = dataList.get(16);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(17);
        String Unit ="Stop";
        if(dataList.get(17)==1){
            Unit ="Start";
        }


        String re = "A2--->>>: setData26 时间："+ZTime+",行程："+stroke+",spm："+spm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",timeMinute1："+timeMinute1+",状态："+Unit;
        Log.w("---》》》", re);

        if(StringUtil.isNotBlank(realSceneUrl)){
            if (timeMinute1>0) {//播放
                if(!mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }else{//暂停
                if(mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }
        }
        if(Unit.equals("Stop")){
            window.setIvSelect(true);
            return;
        }
        window.setTextLoad(loadCurrent+"/"+loadMax);
        window.setIvSelect(false);

        int quanNum = (int) Math.ceil(Distance/(distance*1000d));
        if(Distance % distance==0){
            quanNum++;
        }
        if(quanNum==0){
            quanNum =1;
        }
        tv_quan.setText("第"+quanNum+"圈");

        window.setTextViewStr(Distance + "", timeMinute1+"", ZTime,
                Calories + "", Watt + "", Pulse + "",stroke+"");

        iv_img.setRedHcj((float) (StyleKitName.mPathMeasure.getLength()/ (distance * 10000 / (Distance*10000d % (distance * 10000)))),quanNum);
        iv_img_.setRedHcj((float) (RobotViewSmall.mPathMeasure.getLength()/ (distance * 10000 / (Distance*10000d % (distance * 10000)))));
        getUserBoxReceive(Distance);
        setLoad(duration);
        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextSpeed("浆频");


        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(stroke),timeMinute1+"",null,String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
        return;
    }
    private void setData46(ArrayList<Integer> dataList) {
        int timeSecond =  dataList.get(0);//时间-秒
        int timeMinute =  dataList.get(1);//时间-分
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int DistanceHi = dataList.get(2);
        int DistanceLow = dataList.get(3);
        Distance = DistanceHi+DistanceLow/100d;
        Distance = Double.valueOf(String.format("%.2f", Distance));

        int CaloriesHi = dataList.get(4);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(5);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(6);//跳动 千,佰
        int PulseLow = dataList.get(7);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int speedHi = dataList.get(8);//速度-百十
        int speedLow = dataList.get(9);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int Incline = dataList.get(10);

        ConstValues_Ly.CURRENT_STATE = dataList.get(11);
        String Unit ="None";
        if(dataList.get(11)==2){
            window.setIvSelect(false);
            Unit ="Start";
        }else if(dataList.get(11)==1){
            window.setIvSelect(true);
            Unit ="Stop";
        }else if(dataList.get(11)==3){
            window.setIvSelect(true);
            Unit ="pause";
        }
        //[52, 11, 0, 25, 0, 14, 0, 0, 0, 13, 3, 2, 3, 1]
        String re = "A2--->>>: setData46 时间："+ZTime+",距离："+Distance+",坡度："+Incline+",卡路里："+Calories+",脉跳："+Pulse+",速度："+speed+",状态："+Unit;
        Log.w("---》》》", re);
        if(StringUtil.isNotBlank(realSceneUrl)){
            if (speed>0) {//播放
                if(!mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }else{//暂停
                if(mMpVideo.isPlay()){
                    mMpVideo.setPlayState();
                }
            }
        }
        if(Unit.equals("Stop") && duration!=0){
            psotUserSportLog();
            return;
        }
//        window.setTextLoad(loadCurrent+"/"+loadMax);
        String str = ""+duration;

        if (speed != 0 && (str.substring(str.length() - 1).equals("5") || str.substring(str.length() - 1).equals("0"))) {
            int quanNum = (int) Math.ceil((Distance * 1000d)/(distance* 1000d));
            if(Distance % distance==0){
                quanNum++;
            }
            if(quanNum==0){
                quanNum =1;
            }
            tv_quan.setText("第"+quanNum+"圈");
            iv_img.setRed(Math.round((distance* 1000d -(Distance * 1000d-distance* 1000d*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
            iv_img_.setRed(Math.round((distance* 1000d -(Distance * 1000d-distance* 1000d*(quanNum-1))) / (speed * 1000d) * 60d * 60d * 1000d),quanNum);
        } else {
            if (speed == 0) {
                iv_img.setState(1);
                iv_img_.setState(1);
            } else {
                iv_img.setState(0);
                iv_img_.setState(0);
            }
        }

        getUserBoxReceive(Distance);
        setLoad(duration);
        setBpmDataBeanTime(Pulse);
        mTvTime.setText(ZTime);
        window.setTextViewStr(Distance + "", speed + "", ZTime, Calories + "", 0 + "", Pulse + "",Incline+"");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),String.valueOf(Incline),null,null,
                null,String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),null));
        return;
    }
    boolean isXl = true;
    private void setLoad(long duration){

        if(time_z!=0 && duration>=time_z && isXl){
            isXl = false;
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
//            DialogUtils.showDialogHintYunDong(this, new DialogUtils.DialogInterfaceYhq() {
//                @Override
//                public void btnConfirm(int type) {
//                    if(type ==0){
//                        psotUserSportLog();
//                    }else{
//                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
//                    }
//                }
//            });
            DialogUtils.showUnificationDialog(this, "运动提示","已达成预设运动时间\n是否继续运动？", "停止运动","继续运动",
                     new DialogUtils.UnificationDialogInterface() {
                        @Override
                        public void bntClickListener(String pos) {
                            if(pos.equals("1")){
                                psotUserSportLog();
                            }else{
                                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                            }
                        }
                    });
            return;
        }
        if(duration%5!=0){
            Log.w("setLoad","不是5的倍数");
            return;
        }
        if(loadLists==null){
            return;
        }
        if(loadLists.length==1){
            if(loadCurrent>loadLists[0]){
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent-1)));
            }
            if(loadCurrent<loadLists[0]){
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent+1)));
            }
            if(loadCurrent == loadLists[0]){
                loadLists = null;
            }
            return;
        }
        int time_index = (int) duration/(time_z/10);
        if(time_index<10){
            if(loadCurrent>loadLists[time_index]){
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent-1)));
            }
            if(loadCurrent<loadLists[time_index]){
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent+1)));
            }
        }

    }
    private void setBpmDataBeanTime(int pulse){
        Log.w("-->>","mBpmDataBeans"+mBpmDataBeans.toString());
        if(pulse>=mBpmDataBeans.get(0).getStartV() && pulse<mBpmDataBeans.get(0).getEndV()){
            mBpmDataBeans.get(0).setTime(mBpmDataBeans.get(0).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(1).getStartV() && pulse<mBpmDataBeans.get(1).getEndV()){
            mBpmDataBeans.get(1).setTime(mBpmDataBeans.get(1).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(2).getStartV() && pulse<mBpmDataBeans.get(2).getEndV()){
            mBpmDataBeans.get(2).setTime(mBpmDataBeans.get(2).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(3).getStartV() && pulse<mBpmDataBeans.get(3).getEndV()){
            mBpmDataBeans.get(3).setTime(mBpmDataBeans.get(3).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(4).getStartV() && pulse<mBpmDataBeans.get(4).getEndV()){
            mBpmDataBeans.get(4).setTime(mBpmDataBeans.get(4).getTime()+1);
            return;
        }
        if(pulse>mBpmDataBeans.get(5).getStartV() && pulse<mBpmDataBeans.get(5).getEndV()){
            mBpmDataBeans.get(5).setTime(mBpmDataBeans.get(5).getTime()+1);
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isOutRoom){
            return;
        }
        iv_img.setState(1);
        iv_img_.setState(1);
        if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]
                || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
        }else if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]){
            isStart_56 = false;
        }

        if(StringUtil.isNotBlank(realSceneUrl)){
            if(iv_img.getAlpha()==0.0f){
                mMpVideo.setSilence(false);
                mMpVideo.mediaInterface.setVolume(1f, 1f);
            }else{
                mMpVideo.setSilence(true);
                mMpVideo.mediaInterface.setVolume(0f, 0f);
            }
        }else{
//            ToastUtils.showShort("此地图无实景图");
        }
    }
}
