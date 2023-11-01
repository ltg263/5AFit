package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.UserImgBitmap;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GuideViewComponent;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.conpoment.utils.StatusBarUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName_Zx;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok;
import com.jxkj.fit_5a.conpoment.view.LoadDialog;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.conpoment.view.RobotViewSmall;
import com.jxkj.fit_5a.conpoment.view.RobotView_Zx;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.entity.BoxReceiveBean;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.GameCompleteBean;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.netty_client.common.message.game.body.GameSportsDataNotifBody;
import com.jxkj.fit_5a.netty_client.game.service.impl.GameConnectServiceImpl;
import com.jxkj.fit_5a.netty_client.game.utils.SendMessageUtils;
import com.jxkj.fit_5a.view.adapter.UserLwMineAdapter_Zx;
import com.jxkj.fit_5a.view.adapter.ZxYunDongPaiMingAdapter;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.ArrayList;
import java.util.Comparator;
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

/**
 *
 */
public class MapExerciseActivity_Zx extends Activity{

    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_11)
    ImageView mIv11;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.iv_go_img)
    ImageView iv_go_img;
    @BindView(R.id.rv_user_head)
    RecyclerView rv_user_head;
    @BindView(R.id.rv_user_lw)
    RecyclerView rv_user_lw;
    @BindView(R.id.iv_img)
    RobotView_Zx iv_img;
    @BindView(R.id.iv_img_)
    RobotViewSmall iv_img_;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_new_user)
    TextView tv_new_user;
    @BindView(R.id.tv_pmc)
    TextView tv_pmc;
    @BindView(R.id.tv_pmc_dw)
    TextView tv_pmc_dw;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.rl_tv_time)
    RelativeLayout rl_tv_time;
    @BindView(R.id.tv_quan)
    TextView tv_quan;
    @BindView(R.id.mp_video)
    JzvdStdTikTok mMpVideo;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.ll_5)
    LinearLayout ll_5;
    boolean isSuo = true;
    boolean isFirstShow = false;
    String mapId;
    String roomId;
    String roomMemberId;
    private MyReceiver mMyReceiver;
    int loadCurrent = 1;
    int loadMax = ConstValues_Ly.maxLoad;
    double userWeiget = 75;
    int maxV = 220;
    int bfb5,bfb6,bfb7,bfb8,bfb9,bfb;
    int age = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_AGE,"0"));
    private ArrayList<BpmDataBean> mBpmDataBeans = new ArrayList<>();

    ZxYunDongPaiMingAdapter mZxYunDongPaiMingAdapter;
    UserLwMineAdapter_Zx mUserLwMineAdapter;
    public Bitmap mBitmapBoxOpen,mBitmapBoxColex,mBitmapRed;
    MediaPlayer mediaPlayer;
    boolean isStart_56 = false;
    public static void intentStartActivity(Context mContext, MapDetailsBean mMapDetailsBean_zx,
                                           String roomId, String roomMemberId) {
        Intent intent = new Intent(mContext, MapExerciseActivity_Zx.class);
        intent.putExtra("mMapDetailsBean_zx", mMapDetailsBean_zx);
        intent.putExtra("roomId", roomId);
        intent.putExtra("roomMemberId", roomMemberId);
        mContext.startActivity(intent);
    }

    private void gameGivenUp() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.gameGivenUp_al(roomMemberId);
        }else {
            mObservable = mApiService.gameGivenUp(roomMemberId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0 || result.getCode() == 40010) {
                            iv_img.setAlpha(1.0f);
                            mMpVideo.setSilence(true);
                            mMpVideo.mediaInterface.setVolume(0f, 0f);
                            removeConnectService();
                            finish();
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去掉信息栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒

        userWeiget = Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_WEIGHT, "0"));
        StyleKitName_Zx.mPathMeasure = null;
        RobotViewSmall.mPathMeasure = null;
        RobotViewSmall.mCurrentPosition = null;
        RobotView_Zx.listUserAnimator.clear();
        RobotView_Zx.userIds_ts.clear();
        StyleKitName_Zx.mCurrentPosition.clear();
        rlWidth = UIUtil.dip2px(this, 50);
        setContentView(R.layout.activity_landscape_map_exercise_zx);
        mBitmapBoxOpen = getBoxBitmap();
        mBitmapBoxColex = getBoxBitmap_G();
        mBitmapRed = getImgBitmapRed();
        ButterKnife.bind(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.ready_go);
        mediaPlayer.setLooping(false);//设置为循环播放
        mediaPlayer.start();
        iv_go_img.setVisibility(View.VISIBLE);
        rv_user_lw.setVisibility(View.GONE);
        rv_user_lw.setAnimation(AnimationUtils.makeOutAnimation(MapExerciseActivity_Zx.this, false));

        mTvTime.setText("00:00");

        mMpVideo.setMap(true);
        if(mMpVideo.mediaInterface==null){
            mMpVideo.mediaInterface = new JZMediaSystem(mMpVideo);
        }
        mMpVideo.mediaInterface.setVolume(0f,0f);

        initViews();
        initBpmData();
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
                    duration = 0;
                    isStart_56 = true;
                } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2]) {
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                } else if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]) {
//                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                    DialogUtils.showUnificationDialog(MapExerciseActivity_Zx.this, "提示","请按设备Start/开始按钮来开始", "确定",false,null);
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
        mZxYunDongPaiMingAdapter = new ZxYunDongPaiMingAdapter(null);
        rv_user_head.setLayoutManager(new LinearLayoutManager(this));
        rv_user_head.setAdapter(mZxYunDongPaiMingAdapter);

        mZxYunDongPaiMingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mUserLwMineAdapter = new UserLwMineAdapter_Zx(null);
        rv_user_lw.setLayoutManager(new LinearLayoutManager(this));
        rv_user_lw.setAdapter(mUserLwMineAdapter);

        mUserLwMineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GiftListData.ListBean m = mUserLwMineAdapter.getData().get(position);
                //来源1金豆2背包
                if(Integer.parseInt(m.getBalance())>0){
                    postUserGiftGive("2",m.getGiftId());
                    return;
                }

                DialogUtils.showUnificationDialog(MapExerciseActivity_Zx.this, "提示",
                        "此礼物背包数量不足\n确定直接购买并赠送吗？", "确定",true,
                        new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                postUserGiftGive("1",m.getGiftId());
                            }
                        });
            }
        });
        getUserGiftList();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            boolean isFirstExercise = SharedUtilsNot.singletonNotClear()
                                    .get(GuideViewComponent.isFirstExercise,false);
                            Log.w("isFirstExercise","isFirstExercise"+isFirstExercise);
                            if(!isFirstExercise){
                                isFirstShow = true;
                                if (!isSuo) {
                                    isSuo = true;
                                    PopupWindowTopicUtils_Map.isTop = true;
                                    rl_tv_time.setVisibility(View.VISIBLE);
                                    if (window != null && window.isShowing()) {
                                        window.dismiss();
                                    }
                                }
                                new GuideViewComponent(MapExerciseActivity_Zx.this, ll_5, 2,new GuideViewComponent.GuideViewComponentListener() {
                                    @Override
                                    public void onClickView() {
                                        new GuideViewComponent(MapExerciseActivity_Zx.this, mLl, 4, new GuideViewComponent.GuideViewComponentListener() {
                                            @Override
                                            public void onClickView() {
                                                isFirstShow = false;
                                                SharedUtilsNot.singletonNotClear().put(GuideViewComponent.isFirstExercise,true);
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.w("onWindowFocusChanged","onWindowFocusChanged"+hasFocus);
        if(hasFocus && window!=null){
            showWindowStye();
        }
    }
    int toUserId = 0;
    private void postUserGiftGive(String scoreType,String giftId) {
        if(toUserId==0){
            ToastUtils.showShort("请选择用户");
            return;
        }//	来源1金豆2背包
        PostUser.UserGiftLogFormDTO mUserGiftLogFormDTO = new PostUser.UserGiftLogFormDTO("1",giftId,scoreType,toUserId+"");
        RetrofitUtil.getInstance().apiService()
                .postUserGiftGive(mUserGiftLogFormDTO)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            ToastUtils.showShort("赠送成功");
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

    private void getUserGiftList() {
        RetrofitUtil.getInstance().apiService()
                .getUserGiftList(true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GiftListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GiftListData> result) {
                        if(result.getCode()==0){
                            if(result.getData().getList().size()>0){
                                mUserLwMineAdapter.setNewData(result.getData().getList());
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
    MapDetailsBean mMapDetailsBean_zx;

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        roomId = getIntent().getStringExtra("roomId");
        roomMemberId = getIntent().getStringExtra("roomMemberId");
        mMapDetailsBean_zx = getIntent().getParcelableExtra("mMapDetailsBean_zx");
        setGameRoomUserInfo();
        boolean isQuicks = true;
        if(mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0])){
            tv_1.setText("里程");
            isQuicks = true;
        }else{
            tv_1.setText("排名");
            isQuicks = false;
        }
        if (window == null) {
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity_Zx.this,isQuicks,type -> {
                if(isFirstShow){
                    return;
                }
                if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4]){
                    return;
                }
                if (type == 0) {
                    if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0]
                            || ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]) {
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                    }else if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]){
                        isStart_56 = false;
                    }
                } else if (type == 1) {
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
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();
                        if(rv_user_lw.getVisibility()==View.VISIBLE){
                            rv_user_lw.setVisibility(View.GONE);
                            rv_user_lw.setAnimation(AnimationUtils.makeOutAnimation(MapExerciseActivity_Zx.this, false));
                        }
                        Log.e("onTouch", "mPosX:"+mPosX+";mPosY:"+mPosY);
                        break;
                    // 移动
                    case MotionEvent.ACTION_MOVE:
                        Log.e("onTouch", "mCurrentPosX:"+mCurrentPosX+";mCurrentPosY:"+mCurrentPosY);
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();
                        if (mCurrentPosX - mPosX > 0 && Math.abs(mCurrentPosY - mPosY) < 10) {
                            Log.e("onTouch", "向右");
                        }else if (mCurrentPosX - mPosX < 0 && Math.abs(mCurrentPosY - mPosY) < 10) {
                            Log.e("onTouch", "向左");
                        }else if (mCurrentPosY - mPosY > 0 && Math.abs(mCurrentPosX - mPosX) < 10) {
                            Log.e("onTouch", "向下");
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
                            Log.e("onTouch", "向上");
                            if (isSuo) {
                                showWindowStye();
                            }
                        }
                        break;
                    // 拿起
                    case MotionEvent.ACTION_UP:
                        Log.e("onTouch", "mPosX:"+mPosX+";mPosY:"+mPosY);
                        Log.e("onTouch", "mPosX:"+mPosX+";mPosY:"+mPosY);
                        if(mPosX == mCurrentPosX && mPosY == mCurrentPosY){
                            //功能开发中敬请期待
//                            imgOnClickView();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    private void imgOnClickView(){
        for(int i=0;i<mImgBitmaps.size();i++){
            float x = StyleKitName_Zx.mCurrentPosition.get(i)[0];
            float y = StyleKitName_Zx.mCurrentPosition.get(i)[1];
            Log.w("RobotView_Zx","RobotView_Zx.userIds_ts:"+mImgBitmaps.toString());
            if((x-20<mPosX && x+20>mPosX) && (y-80<mPosY &&  y+10>mPosY)){
                if(mImgBitmaps.get(i).getUserId().equals(SharedUtils.getUserId()+"") || !RobotView_Zx.userIds_ts.contains(mImgBitmaps.get(i).getUserId())){
                    Log.w("RobotView_Zx","失败的id:"+mImgBitmaps.get(i).getUserId());
                }else{
                    Log.w("RobotView_Zx","成功的id:"+mImgBitmaps.get(i).getUserId());
                    showDistancePopup(mImgBitmaps.get(i).getUserId(),
                            StyleKitName_Zx.mCurrentPosition.get(i)[0],StyleKitName_Zx.mCurrentPosition.get(i)[1]);
                }
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(2000);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if(distancePopWindow!=null && distancePopWindow.getPopupWindow()!=null && distancePopWindow.getPopupWindow().isShowing()){
//                                        distancePopWindow.dissmiss();
//                                    }
//                                }
//                            });
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
                return;
            }
        }
    }
    private CustomPopWindow distancePopWindow;
    int rlWidth = 0;
    private void showDistancePopup(String userId,float x,float y) {
        toUserId = Integer.parseInt(userId);
        if(distancePopWindow!=null){
            distancePopWindow.showAsDropDown(iv_img, (int)x-rlWidth, (int)y);
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.popup_window_zasl, null, false);
        view.findViewById(R.id.ll_dianzan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        view.findViewById(R.id.ll_guanzhu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFirstShow){
                    return;
                }
                HttpRequestUtils.postfollow(toUserId+"", new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        if(path.equals("0")){
                            ToastUtils.showShort("关注成功");
                        }
                        distancePopWindow.dissmiss();
                    }
                });
            }
        });
        view.findViewById(R.id.ll_songli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFirstShow){
                    return;
                }
                rv_user_lw.setVisibility(View.VISIBLE);
                rv_user_lw.setAnimation(AnimationUtils.makeInAnimation(MapExerciseActivity_Zx.this, true));
                if(rv_user_head.getVisibility()==View.VISIBLE){
                    rv_user_head.setVisibility(View.GONE);
                    rv_user_head.setAnimation(AnimationUtils.makeOutAnimation(MapExerciseActivity_Zx.this, false));
                }
                distancePopWindow.dissmiss();
            }
        });
        //创建并显示popWindow
        distancePopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        rbDistance.setChecked(false);
                    }
                })
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create();//创建PopupWindow
        distancePopWindow.showAsDropDown(iv_img, (int)x-rlWidth, (int)y);//显示PopupWindow
    }


    @OnClick({R.id.iv_1,R.id.iv_shang,R.id.iv_11,R.id.iv_2,R.id.iv_3, R.id.iv_4})
    public void onViewClicked(View view) {
        if(isFirstShow){
            return;
        }
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
            case R.id.iv_11:
                if(rv_user_head.getVisibility()==View.GONE){
                    mIv11.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_yd_12));
                    rv_user_head.setVisibility(View.VISIBLE);
                    rv_user_head.setAnimation(AnimationUtils.makeInAnimation(this, true));
                    if(rv_user_lw.getVisibility()==View.VISIBLE){
                        rv_user_lw.setVisibility(View.GONE);
                        rv_user_lw.setAnimation(AnimationUtils.makeOutAnimation(MapExerciseActivity_Zx.this, false));
                    }
                }else{
                    mIv11.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_yd_121));
                    rv_user_head.setAnimation(AnimationUtils.makeOutAnimation(MapExerciseActivity_Zx.this, false));
                    rv_user_head.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_shang:
                showWindowStye();
                break;
            case R.id.iv_3:
                if(StringUtil.isNotBlank(realSceneUrl)){
                    if(iv_img.getAlpha()==1.0f){
                        iv_img.setAlpha(0.0f);
                        mMpVideo.setSilence(false);
                        mMpVideo.mediaInterface.setVolume(1f, 1f);
                        mRl.setBackgroundResource(R.drawable.bj_circle_fff_4_tm);
                        mIv3.setImageResource(R.mipmap.ic_map_pingmian);
                    }else {
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
            case R.id.iv_2:
                if(duration<=0){
                    return;
                }
                if (!isSuo) {
                    isSuo = true;
                    PopupWindowTopicUtils_Map.isTop = true;
                    rl_tv_time.setVisibility(View.VISIBLE);
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

    List<UserImgBitmap> mImgBitmaps = new ArrayList<>();

    public void getImgBitmap(int pos) {
        if(userMembersImg.size()<=pos){
            if (mMapDetailsBean_zx.getInfo() != null && mMapDetailsBean_zx.getInfo().size() > 0) {
                iv_img.setData(mBoxs,mBitmapBoxOpen,mMapDetailsBean_zx.getInfo(), mImgBitmaps,mBitmapBoxColex,distance,mMapDetailsBean_zx.getParam());
                iv_img_.setData(mBitmapRed,distance,
                        mMapDetailsBean_zx.getInfo(), mMapDetailsBean_zx.getParam());
            }
            return;
        }
        View view = View.inflate(this, R.layout.item_img_head, null);
        RoundImageView image_iv =  view.findViewById(R.id.image_iv);
        ImageView image_user =  view.findViewById(R.id.image_user);
        if(userMembersImg.get(pos).getUserId().equals(SharedUtils.getUserId()+"")){
            image_user.setVisibility(View.VISIBLE);
        }else{
            image_user.setVisibility(View.INVISIBLE);
        }
        if(StringUtil.isNotBlank(userMembersImg.get(pos).getAvatar()) && userMembersImg.get(pos).getAvatar().contains("http")){
            Glide.with(this).load(userMembersImg.get(pos).getAvatar()).into(image_iv);
        }else{
            Glide.with(this).load(R.mipmap.icon_app_logo).into(image_iv);
        }
        RobotView_Zx.listUserAnimator.add(new RobotView_Zx.ValueUserAnimator(iv_img, userMembersImg.get(pos).getUserId()));
//        GlideImageUtils.loadGlideRequestListener(this, userMembersImg.get(pos).getAvatar(), image_iv, new GlideImageUtils.GlideRequestListener() {
//            @Override
//            public void glideComplete() {
//                view.destroyDrawingCache();
//                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//                view.setBackgroundColor(Color.TRANSPARENT);
//                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//                view.setDrawingCacheEnabled(true);
//                mImgBitmaps.add(new UserImgBitmap(userMembersImg.get(pos).getUserId(),view.getDrawingCache()));
//                int i = pos+1;
//                getImgBitmap(i);
//            }
//        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.destroyDrawingCache();
                            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                            view.setBackgroundColor(Color.TRANSPARENT);
                            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                            view.setDrawingCacheEnabled(true);
                            mImgBitmaps.add(new UserImgBitmap(userMembersImg.get(pos).getUserId(),view.getDrawingCache()));
                            int i = pos+1;
                            getImgBitmap(i);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
    List<MapDetailsBean.UserBean> userMembersImg;
    String realSceneUrl;
    private void setGameRoomUserInfo() {
        if(mMapDetailsBean_zx==null){
            ToastUtils.showShort("数据有问题");
            if(StringUtil.isNotBlank(roomMemberId)){
                gameGivenUp();
                return;
            }
            removeConnectService();
            iv_img.setAlpha(1.0f);
            mMpVideo.setSilence(true);
            mMpVideo.mediaInterface.setVolume(0f, 0f);
            finish();
            return;
        }
        mapId = mMapDetailsBean_zx.getId();
        userMembersImg = mMapDetailsBean_zx.getUserMembers();
        if(userMembersImg!=null && userMembersImg.size()>0){
            getImgBitmap(0);
        }

        distance = Double.parseDouble(mMapDetailsBean_zx.getDistance());//0.5km
        mTvDistance.setText(distance+"km");
        mBoxs = mMapDetailsBean_zx.getBoxs();
        GlideImageUtils.setGlideImage(MapExerciseActivity_Zx.this, mMapDetailsBean_zx.getImgUrl(),iv_img);


        realSceneUrl = mMapDetailsBean_zx.getRealSceneUrl();
        if(StringUtil.isNotBlank(realSceneUrl)){
            HttpRequestUtils.getPlayInfo(MapExerciseActivity_Zx.this,realSceneUrl, new HttpRequestUtils.PlayInfoInterface() {

                @Override
                public void succeed(Result<VideoPlayInfoBean> result) {
                    if(result.getCode()==0 && result.getData().getPlayInfoList()!=null){
                        List<VideoPlayInfoBean.PlayInfoListBean> mBeans = result.getData().getPlayInfoList();
                        if(mBeans.size()>0){
                            Glide.with(MapExerciseActivity_Zx.this).load(result.getData().getVideoBase().getCoverURL()).into(mMpVideo.posterImageView);
                            String playUrl = HttpRequestUtils.initVideo(MapExerciseActivity_Zx.this,mBeans.get(0).getPlayURL(),realSceneUrl);
                            mMpVideo.setUp(playUrl, MyVideoPlayer.PLAY_STATE_EXERCISE, MyVideoPlayer.STATE_NORMAL);
                            mMpVideo.setSilence(true);
                        }
                    }
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)){
            outRoom();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    boolean isOutApp = false;
    private void outRoom() {
        if(mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0])){
//            DialogUtils.showDialogOutRoom(this, new DialogUtils.DialogLyInterface() {
//                @Override
//                public void btnConfirm() {
//                    isOutApp = true;
//                    if(postUserOkDistance!=0){
//                        psotUserSportLog();
//                    }else{
//                        gameGivenUp();
//                    }
//                }
//            });
            DialogUtils.showUnificationDialog(this, "结束运动","您确定要结束运动吗？", "结束",true,
                     new DialogUtils.UnificationDialogInterface() {
                        @Override
                        public void bntClickListener(String pos) {
                            isOutApp = true;
                            if(postUserOkDistance!=0){
                                psotUserSportLog();
                            }else{
                                gameGivenUp();
                            }
                        }
                    });
            return;
        }

        DialogUtils.showUnificationDialog(this, "结束运动","您确定要结束运动吗？", "结束",true,
                 new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        isOutApp = true;
                        if(window!=null && window.isShowing()){
                            window.dismiss();
                        }
                        if(StringUtil.isNotBlank(roomMemberId)){
                            gameGivenUp();
                        }else{
                            iv_img.setAlpha(1.0f);
                            mMpVideo.setSilence(true);
                            mMpVideo.mediaInterface.setVolume(0f, 0f);
                            gameRoomQuit(roomId);
                        }
                    }
                });
//        DialogUtils.showDialogOutRoom_zx(this,"结束运动","您确定要结束运动吗？", new DialogUtils.EditTextDialogInterface() {
//            @Override
//            public void btnConfirm(String string) {
//                isOutApp = true;
//                if(window!=null && window.isShowing()){
//                    window.dismiss();
//                }
//                if(string.equals("1")){
//                    if(StringUtil.isNotBlank(roomMemberId)){
//                        gameGivenUp();
//                    }else{
//                        gameRoomQuit(roomId);
//                    }
//                }else{
//                    finish();
//                }
//            }
//        });
    }

    /**
     * 退出房间
     */
    private void gameRoomQuit(String roomId) {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.gameRoomQuit_al(roomId);
        }else {
            mObservable = mApiService.gameRoomQuit(roomId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            removeConnectService();
                            startActivity(new Intent(MapExerciseActivity_Zx.this, MainActivity.class));
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

    private void removeConnectService() {
        StyleKitName_Zx.mPathMeasure = null;
        if(RobotView_Zx.listUserAnimator!=null){
            for(int i=0;i<RobotView_Zx.listUserAnimator.size();i++){
                RobotView_Zx.ValueUserAnimator mValueUserAnimator = RobotView_Zx.listUserAnimator.get(i);
                mValueUserAnimator.setState(1);
            }
            RobotView_Zx.listUserAnimator.clear();
        }
        if(RobotView_Zx.listUserAnimator!=null){
            StyleKitName_Zx.mCurrentPosition.clear();
        }
        if (mMyReceiver != null) {
            unregisterReceiver(mMyReceiver);
            mMyReceiver =null;
        }

        if(MotorPatternActivity.gameConnectService!=null){
            MotorPatternActivity.gameConnectService.removeConnect();
            MotorPatternActivity.gameConnectService = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeConnectService();
    }
    double postUserOkDistance = 0.0;
    long postUserOkDuration = 0;
    private void psotUserSportLog() {
        String pjDuration = String.valueOf((int)(postUserOkDistance / (postUserOkDuration/60d/60d) *100)/100d);
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

        PostUser.SportLogInfo sportLogInfo = new PostUser.SportLogInfo();
        sportLogInfo.setBai(StringUtil.getBai_V(logs));
        sportLogInfo.setMapId(mapId);
        sportLogInfo.setDeviceBrandId(ConstValues_Ly.BRAND_ID);
        sportLogInfo.setCalories(String.valueOf(Calories));
        sportLogInfo.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL + "");
        sportLogInfo.setDistance(String.valueOf(postUserOkDistance));
        sportLogInfo.setDuration(String.valueOf(postUserOkDuration));
        sportLogInfo.setEndTimestamp(String.valueOf(System.currentTimeMillis()));
        sportLogInfo.setStartTimestamp(String.valueOf(startTimestamp));
        sportLogInfo.setProtocolName("iconsole");
        sportLogInfo.setProtocolDeviceBrandParamId(null);
        sportLogInfo.setHeartRateSource("2");//1=器材;2=藍牙心跳;3=Apple Watch
        if(mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0])){
            sportLogInfo.setTrainingMode("QuickStart");//训练模式(目前只有HeartRate(心率)、Program(课程)、QuickStart(快速开始)、Racing(竞速模式))
        }else{
            sportLogInfo.setTrainingMode("Racing");
        }
        PostUser.SportLogInfo.DetailsBean deleteDatabase = new PostUser.SportLogInfo.DetailsBean();
        deleteDatabase.setLogs(logs);
        sportLogInfo.setDetails(deleteDatabase);

        Log.w("MaxSpeed:","MaxSpeed"+MaxSpeed);
        mBpmDataBeans.get(0).setBpmTopData(
                new BpmDataBean.BpmTopData(String.valueOf(Calories), String.valueOf(postUserOkDistance),
                        postUserOkDuration + "", pjDuration,
                        String.valueOf(MaxSpeed), "0",Watt+"",load_dx,"0",StringUtil.getBai_V(logs)));

        HttpRequestUtils.psotUserSportLog(sportLogInfo, new HttpRequestUtils.LoginInterface() {
            @Override
            public void succeed(String data_id) {
                Log.w("data_id","data_id"+data_id);
                if(StringUtil.isNotBlank(data_id)){
                    gamComplete(data_id);
                    return;
                }
                gameGivenUp();
            }
        });
    }

    private void gamComplete(String sportLogId) {
        show("加载中...");
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<GameCompleteBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.gamComplete_al(Calories+"",postUserOkDistance+"",roomMemberId,sportLogId);
        }else {
            mObservable = mApiService.gamComplete(Calories+"",postUserOkDistance+"",roomMemberId,sportLogId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GameCompleteBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GameCompleteBean> result) {
                        dismiss();
                        if(result.getCode()==0) {
                            //极速模式
                            List<GameCompleteBean.GameRankingsBean> mRankingsBeans = null;
                            if(mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0])) {
                                iv_img.setAlpha(1.0f);
                                mMpVideo.setSilence(true);
                                mMpVideo.mediaInterface.setVolume(0f, 0f);
                                Intent mIntent = new Intent(MapExerciseActivity_Zx.this, MapExerciseFinishActivity.class);
                                mIntent.putParcelableArrayListExtra("mBpmDataBeans", mBpmDataBeans);
                                mIntent.putParcelableArrayListExtra("logs",logs);
                                mIntent.putExtra("data_id",sportLogId);
                                startActivity(mIntent);
                                finish();
//                                mRankingsBeans = new ArrayList<>();
//                                List<MapDetailsBean_zx.UserBean> mData = mZxYunDongPaiMingAdapter.getData();
//                                for(int i = 0;i<mData.size();i++){
//                                    GameCompleteBean.GameRankingsBean mRankingsBean = new GameCompleteBean.GameRankingsBean();
//                                    mRankingsBean.setDistance((Double.parseDouble(mData.get(i).getDistance()))+"");
//                                    mRankingsBean.setRanking((i+1)+"");
//                                    mRankingsBean.setCalories("0");
//                                    GameCompleteBean.GameRankingsBean.UserBean mUserBean = new GameCompleteBean.GameRankingsBean.UserBean();
//                                    mUserBean.setAvatar(mData.get(i).getAvatar());
//                                    mUserBean.setNickName(mData.get(i).getNickName());
//                                    mRankingsBean.setUser(mUserBean);
//                                    mRankingsBeans.add(mRankingsBean);
//                                }
//                                DialogUtils.showDialogyundong_zx(MapExerciseActivity_Zx.this,MotorPatternActivity.ROOM_TYPE[0],
//                                        mRankingsBeans,tv_pmc.getText().toString(),new DialogUtils.EditTextDialogInterface() {
//                                            @Override
//                                            public void btnConfirm(String string) {
//                                                finish();
//                                            }
//                                        });
                            }else{
                                mRankingsBeans = result.getData().getGameRankings();
                                DialogUtils.showDialogyundong_zx(MapExerciseActivity_Zx.this,MotorPatternActivity.ROOM_TYPE[1],
                                        mRankingsBeans,
                                        result.getData().getRanking(),new DialogUtils.EditTextDialogInterface() {
                                    @Override
                                    public void btnConfirm(String string) {
                                        iv_img.setAlpha(1.0f);
                                        mMpVideo.setSilence(true);
                                        mMpVideo.mediaInterface.setVolume(0f, 0f);
                                        if(string.equals("1")){
                                            Intent mIntent = new Intent(MapExerciseActivity_Zx.this, MapExerciseFinishActivity.class);
                                            mIntent.putParcelableArrayListExtra("mBpmDataBeans", mBpmDataBeans);
                                            mIntent.putParcelableArrayListExtra("logs",logs);
                                            mIntent.putExtra("data_id",sportLogId);
                                            startActivity(mIntent);
                                        }
                                        finish();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

    }

    boolean isGJinru = true;
    int size = 0;//房间人数
    public class MyReceiver extends BroadcastReceiver {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra("type");
            if(type.equals("-2")){
                isOutApp = true;
//                DialogUtils.showDialogHint(MapExerciseActivity_Zx.this, "您的设备已断开连接\n请重新连接", true, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        startActivity(new Intent(MapExerciseActivity_Zx.this, MainActivity.class));
//                        if(mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0]) && StringUtil.isNotBlank(roomMemberId)) {
//                            gameGivenUp();
//                        }else{
//                            removeConnectService();
//                        }
//
//                    }
//                });
                DialogUtils.showUnificationDialog(MapExerciseActivity_Zx.this, "提示", "您的设备已断开连接\n请重新连接", "确定", false, new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        startActivity(new Intent(MapExerciseActivity_Zx.this, MainActivity.class));
                        if(mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0]) && StringUtil.isNotBlank(roomMemberId)) {
                            gameGivenUp();
                        }else{
                            removeConnectService();
                        }
                    }
                });
                return;
            }

            if (type.equals("b2")) {
                ArrayList<Integer> dataList = intent.getIntegerArrayListExtra("data");
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[0] && dataList.size() == 16) {
                    /**
                     * 脚踏椭圆机
                     */
                    setData1(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1] && dataList.size() == 14) {
                    /**
                     * 手划椭圆机
                     */
                    setData56(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3] && dataList.size() == 18) {
                    /**
                     * 划船机
                     */
                    setData26(dataList);
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[4] && dataList.size() == 14) {
                    /**
                     * 跑步机
                     */
                    setData46(dataList);
                }
//                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[2] && dataList.size() == 14) {
//                    //健腹轮
//                }
            }
            if(type.equals("-1")){
                /**
                 * 收到推送的数据
                 */
                List<GameSportsDataNotifBody.SportData> sportDataList = intent.getParcelableArrayListExtra("sportDataList");

                /**
                 * 如果发现人员数量有变动重新获取房间详情
                 */
                if(size != intent.getIntExtra("size",0)){
                    size = intent.getIntExtra("size",0);
                    List<Integer> userIds = new ArrayList<>();
                    for(int i=0;i<sportDataList.size();i++){
                        userIds.add(sportDataList.get(i).getUserId());
                    }
                    getUserBases(userIds);
                }
                /**
                 * 收到的在线人员数据
                 */
                List<MapDetailsBean.UserBean> mData = mMapDetailsBean_zx.getUserMembers();
                /**
                 * 用于排名的人员数据
                 */
                List<MapDetailsBean.UserBean> mUserZxInfos = new ArrayList<>();
                /**
                 * 在线人的userId
                 */
                RobotView_Zx.userIds_ts.clear();
                //用户id：80, 当前运动距离：0当前速度：0当前时间：1640849572569, 是否在线：1
                for(int i= 0;i<sportDataList.size();i++){

                    for(int j=0;j<mData.size();j++){
                        if((sportDataList.get(i).getUserId()+"").equals(mData.get(j).getUserId())){// && sportDataList.get(i).getIsOnline()==1
                            mData.get(j).setTimestamp(sportDataList.get(i).getTimestamp());
                            mData.get(j).setDistance((sportDataList.get(i).getDistance()/10000d)+"");
                            mUserZxInfos.add(mData.get(j));
                            RobotView_Zx.userIds_ts.add(mData.get(j).getUserId());
                        }
                    }
                    for(int j = 0;j<RobotView_Zx.listUserAnimator.size();j++){
                        if(String.valueOf(sportDataList.get(i).getUserId()).equals(RobotView_Zx.listUserAnimator.get(j).getUserId())){
                            if(isGJinru && sportDataList.get(i).getDistance()>0){//首次进入已在跑人员位置信息
                                StyleKitName_Zx.mPathMeasure.getPosTan((float) (StyleKitName_Zx.mPathMeasure.getLength()/(distance*10000/(sportDataList.get(i).getDistance() %(distance*10000)))),
                                        StyleKitName_Zx.mCurrentPosition.get(j), null);
                                RobotView_Zx.listUserAnimator.get(j).setLastAnimtionValue((float) (StyleKitName_Zx.mPathMeasure.getLength()/(distance*10000/(sportDataList.get(i).getDistance()%(distance*10000)))));
                                iv_img.postInvalidate();
                            }

                            if(sportDataList.get(i).getSpeed()==0 && ConstValues_Ly.METER_ID != ConstValues_Ly.METER_ID_S[3]){
                                RobotView_Zx.listUserAnimator.get(j).setState(1);
                                iv_img.postInvalidate();
                                if (String.valueOf(sportDataList.get(i).getUserId()).equals(SharedUtils.getUserId() + "")) {
                                    iv_img_.setState(1);
                                    if(StringUtil.isNotBlank(realSceneUrl)){
                                        if(mMpVideo.isPlay()){//暂停
                                            mMpVideo.setPlayState();
                                        }
                                    }
                                }
                            }else{
                                if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]){//划船机
                                    if (String.valueOf(sportDataList.get(i).getUserId()).equals(SharedUtils.getUserId() + "")) {
                                        postUserOkDistance = sportDataList.get(i).getDistance() / 10000d;
                                        postUserOkDuration = sportDataList.get(i).getTimestamp();
                                        Log.w("---->>>","realSceneUrl:"+realSceneUrl+"sportDataList.get(i).getSpeed():"+sportDataList.get(i).getSpeed());
                                        if(StringUtil.isNotBlank(realSceneUrl)){
                                            if(sportDataList.get(i).getSpeed()>0){
                                                if(!mMpVideo.isPlay()){//播放
                                                    mMpVideo.setPlayState();
                                                }
                                            }else{
                                                if(mMpVideo.isPlay()){//暂停
                                                    mMpVideo.setPlayState();
                                                }
                                            }
                                        }
                                    }
                                    if (!isOutApp
                                            && String.valueOf(sportDataList.get(i).getUserId()).equals(SharedUtils.getUserId() + "")
                                            && mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[1])
                                            && sportDataList.get(i).getDistance() >= (distance * 10000)) {
                                        if (RobotView_Zx.listUserAnimator.get(j).getLastAnimtionValue() != 0) {
                                            RobotView_Zx.listUserAnimator.get(j).setRedHcj(StyleKitName_Zx.mPathMeasure.getLength(), j,-2);
                                        } else {
                                            isOutApp = true;
                                            psotUserSportLog();
                                        }
                                    } else {
                                        int quanNum = 1;
                                        if (mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0])) {
                                            tv_quan.setVisibility(View.VISIBLE);
                                            quanNum = (int) Math.ceil((sportDataList.get(i).getDistance()) / (distance * 10000d));
                                            if (sportDataList.get(i).getDistance() % (distance * 10000) == 0) {
                                                quanNum = quanNum + 1;
                                            }
                                            if (sportDataList.get(i).getUserId() == SharedUtils.getUserId()) {
                                                tv_quan.setText("第" + quanNum + "圈");//500-500000
                                            }
                                        }
                                        RobotView_Zx.listUserAnimator.get(j).setRedHcj((float) (StyleKitName_Zx.mPathMeasure.getLength()
                                                / (distance * 10000 / (sportDataList.get(i).getDistance() % (distance * 10000)))), j ,quanNum);
                                    }
                                }else {
                                    RobotView_Zx.listUserAnimator.get(j).setState(0);
                                    iv_img_.setState(0);
                                    long time = (long) ((distance * 10000 - (sportDataList.get(i).getDistance() % (distance * 10000)))//剩余距离分米
                                            / sportDataList.get(i).getSpeed()//每小时/分米
                                            * 60d * 60d * 1000d);//所用的毫秒
                                    if (String.valueOf(sportDataList.get(i).getUserId()).equals(SharedUtils.getUserId() + "")) {
                                        postUserOkDistance = sportDataList.get(i).getDistance() / 10000d;
                                        postUserOkDuration = sportDataList.get(i).getTimestamp();
                                        if(StringUtil.isNotBlank(realSceneUrl)){
                                            if(!mMpVideo.isPlay()){//播放
                                                mMpVideo.setPlayState();
                                            }
                                        }
                                    }
                                    if (!isOutApp
                                            && String.valueOf(sportDataList.get(i).getUserId()).equals(SharedUtils.getUserId() + "")
                                            && mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[1])
                                            && sportDataList.get(i).getDistance() >= (distance * 10000)) {
                                        if (RobotView_Zx.listUserAnimator.get(j).getLastAnimtionValue() != 0) {
                                            RobotView_Zx.listUserAnimator.get(j).setRed(1001, j, 1);
                                            iv_img_.setRed(1001,1);
                                        } else {
                                            RobotView_Zx.listUserAnimator.get(j).setState(1);
                                            iv_img_.setState(1);
                                            isOutApp = true;
                                            psotUserSportLog();
                                        }
                                    } else {
                                        int quanNum = 0;
                                        if (mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[0])) {
                                            tv_quan.setVisibility(View.VISIBLE);
                                            quanNum = (int) Math.ceil((sportDataList.get(i).getDistance()) / (distance * 10000d));
                                            if (sportDataList.get(i).getDistance() % (distance * 10000) == 0) {
                                                quanNum = quanNum + 1;
                                            }
                                            if (sportDataList.get(i).getUserId() == SharedUtils.getUserId()) {
                                                tv_quan.setText("第" + quanNum + "圈");//500-500000
                                            }
                                        }
                                        RobotView_Zx.listUserAnimator.get(j).setRed(time, j, quanNum);
                                        if(String.valueOf(sportDataList.get(i).getUserId()).equals(SharedUtils.getUserId() + "")){
                                            iv_img_.setRed(time,quanNum);
                                        }
                                    }
                                }
                            }
                        }
                        if(i==sportDataList.size()-1 && j==RobotView_Zx.listUserAnimator.size()-1){
                            isGJinru = false;
                        }
                    }
                }
                for(int i= 0;i < mUserZxInfos.size();i++){
                    for(int j = 0;j<RobotView_Zx.listUserAnimator.size();j++){
                        if(mUserZxInfos.get(i).getUserId().equals(RobotView_Zx.listUserAnimator.get(j).getUserId())){
                            mUserZxInfos.get(i).setLastAnimtionValue(RobotView_Zx.listUserAnimator.get(j).getLastAnimtionValue());
                        }
                    }
                }
                mUserZxInfos.sort(new Comparator<MapDetailsBean.UserBean>() {
                    @Override
                    public int compare(MapDetailsBean.UserBean sportData, MapDetailsBean.UserBean t1) {
                        if(Double.parseDouble(sportData.getDistance()) > Double.parseDouble(t1.getDistance())){
                            return -1;
                        }
                        if(Double.parseDouble(sportData.getDistance()) == Double.parseDouble(t1.getDistance())){
                            if(sportData.getLastAnimtionValue()>t1.getLastAnimtionValue()){
                                return -1;
                            }
                        }
                        return 0;
                    }
                });
                for(int i = 0; i<mUserZxInfos.size();i++){
                    if(mUserZxInfos.get(i).getUserId().equals(SharedUtils.getUserId()+"")){
                        tv_pmc.setText(String.valueOf(i+1));
                        if(i==0){
                            tv_pmc_dw.setText("st");
                        }else if(i==1){
                            tv_pmc_dw.setText("nd");
                        }else if(i==2){
                            tv_pmc_dw.setText("rd");
                        }else{
                            tv_pmc_dw.setText("th");
                        }
                        mTvTime.setText(StringUtil.getTimeToYMD(mUserZxInfos.get(i).getTimestamp()*1000,"mm:ss"));
                    }
                    if(!mZxYunDongPaiMingAdapter.getData().contains(mUserZxInfos.get(i))){
                        new_user_show_time = 0;
                        tv_new_user.setText(mUserZxInfos.get(i).getNickName()+"已加入房间");
                    }
                }
                if(new_user_show_time==0 && tv_new_user.getVisibility()==View.INVISIBLE){
                    tv_new_user.setVisibility(View.VISIBLE);
                }
                if(new_user_show_time>10 && tv_new_user.getVisibility()==View.VISIBLE){
                    tv_new_user.setVisibility(View.INVISIBLE);
                }
                if(new_user_show_time<12){
                    new_user_show_time++;
                }
                mZxYunDongPaiMingAdapter.setNewData(mUserZxInfos);
            }
        }
    }
    int new_user_show_time = 0;
    Double distance = 0.0;//总距离
    double userOkDistance_56 = 0;//用户已完成
    double userOkDistance = 0;//用户已完成
    double userOkDistance_new=0.0;//首次距离
    long duration = 0;
    int Calories;
    double Calories_56;
    int Pulse;
    long startTimestamp;
    double Watt = 0;

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
        userOkDistance = ConstValues_Ly.getBaiShiGeX(DistanceHi, DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi, CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi, PulseLow);
        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        Watt = ConstValues_Ly.getBaiShiGeX(WattHi, WattLow);

        loadCurrent = dataList.get(14);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(15);
        String Unit = "Stop";

        if (dataList.get(15) == 1) {
            window.setIvSelect(false);
            Unit = "Start";
        }
        // 时间   速度  转数  距离  卡路里   心率  功率    阻力  状态
        String re = "setData1--->>>:时间：" + ZTime + ",速度：" + speed + ",转数：" + rpm + ",距离：" + userOkDistance + ",卡路里：" + Calories+ ",脉跳：" + Pulse + ",瓦特：" + Watt + ",阻力：" + loadCurrent + ",状态：" + Unit;
        Log.w("---》》》", re+";isOutApp:"+isOutApp);
        if(isOutApp){
            return;
        }

        if (Unit.equals("Stop")) {
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
//            return;
            speed = 0;
            window.setIvSelect(true);
        }
        window.setTextLoad(loadCurrent+"/"+loadMax);

        SendMessageUtils.sendSportDatasUploadReq(GameConnectServiceImpl.clientInfo.getCtx(),
                (long)SharedUtils.getUserId(),(int)(userOkDistance*10000),duration,(int)(speed*10000));
        getUserBoxReceive(userOkDistance);
        setBpmDataBeanTime(Pulse);
        window.setTextViewStr(userOkDistance+"", speed + "", ZTime, Calories + "", Watt + "", Pulse + "","0");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(userOkDistance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm),String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
    }


    private void getUserBoxReceive(double userDistance){
        if(mBoxs==null){
            return;
        }
        for(int i =0;i<mBoxs.size();i++){
            if(!mBoxs.get(i).getReceiveInfo().isHaving() && userDistance>=mBoxs.get(i).getDistance()){
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
                            GlideImageLoader.loadOneTimeGif(MapExerciseActivity_Zx.this, R.drawable.ic_baoxiang,
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
    boolean isNewActvity = true;
    double speed_zdy;

    private void setData56(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
//        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
//        speed_zdy = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int rpm1Hi = dataList.get(4);//每分钟转数 -千百
        int rpm1Low = dataList.get(5);//每分钟转数 -十个
        int rpm1 = ConstValues_Ly.getQianBaiShiGe(rpm1Hi,rpm1Low);

        int rpm2Hi = dataList.get(6);//每分钟转数 -千百
        int rpm2Low = dataList.get(7);//每分钟转数 -十个
        int rpm2 = ConstValues_Ly.getQianBaiShiGe(rpm2Hi,rpm2Low);

        int DistanceHi = dataList.get(8);//距离-百十
        int DistanceLow = dataList.get(9);//距离-个小数点下一位
//        if(userOkDistance_new==0){
//            userOkDistance_new = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);
//        }
//        userOkDistance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(10);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(11);// 卡路里 -个十
//        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(12);//跳动 千,佰
        int PulseLow = dataList.get(13);//跳动 千,佰 -个十
        Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        double perimeter = ConstValues_Ly.wheelDiameter * 2.54/*inch*/ * Math.PI/*PI*/ / 100000.0/*cm to km*/;
        speed_zdy = perimeter * 60/*minutes of hour*/ * rpm1;//km/h
        Log.w("---》》》", "A2--->>>setData56:时间：距离："+speed_zdy/3600d);
        speed_zdy = Double.parseDouble(StringUtil.getValue(speed_zdy));

        if(isStart_56){
            duration++;
            ZTime = StringUtil.getTimeToYMD(duration*1000,"mm:ss");
        }else{
            speed_zdy = 0;
        }

        userOkDistance_56 += speed_zdy/3600d;
        userOkDistance = Double.parseDouble(StringUtil.getValue(userOkDistance_56));
        Calories_56 += speed_zdy * userWeiget *1.038* (1/3600d);
        Calories = (int) Calories_56;


        String re = "setData56--->>>:时间："+ZTime+",速度："+speed_zdy+",转数1："+rpm1+",转数2："+rpm2+",距离："+userOkDistance+",卡路里："+Calories
                +",脉跳："+Pulse;
        Log.w("---》》》", re);
        if(isOutApp){
            return;
        }

        SendMessageUtils.sendSportDatasUploadReq(GameConnectServiceImpl.clientInfo.getCtx(),
                (long)SharedUtils.getUserId(),(int)(userOkDistance*10000),duration,(int)(speed_zdy*10000));
        window.setTextViewStr(userOkDistance + "", speed_zdy + "", StringUtil.getTimeToYMD(duration*1000,"mm:ss"),
                Calories + "", 0 + "", Pulse + "","0");


        if(!isStart_56){
            window.setIvSelect(true);
            return;
        }
        window.setIvSelect(false);

        getUserBoxReceive(userOkDistance);


        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(userOkDistance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm1),String.valueOf(speed_zdy),null,String.valueOf(System.currentTimeMillis()),null));

        setBpmDataBeanTime(Pulse);
    }

    int stroke= 0;
    private void setData26(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;

        int strokeHi = dataList.get(2);
        int strokeLow = dataList.get(3);
        stroke = ConstValues_Ly.getQianBaiShiGe(strokeHi,strokeLow);

        int spmHi = dataList.get(4);
        int spmLow = dataList.get(5);
        speed_zdy = ConstValues_Ly.getQianBaiShiGe(spmHi,spmLow);

        int DistanceHi = dataList.get(6);
        int DistanceLow = dataList.get(7);
        if(userOkDistance_new==0){
            userOkDistance_new = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);
        }
        userOkDistance = ConstValues_Ly.getQianBaiShiGe(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

//        timeMinute1 =  dataList.get(14);//时间-分
        timeMinute1 =  dataList.get(15);//时间-秒
//        int duration1 = timeMinute * 60 + timeSecond;
//        String time1 = ConstValues_Ly.getTime(timeMinute1,timeSecond1);

        loadCurrent = dataList.get(16);//阻力
        String Unit ="Stop";
        if(dataList.get(17)==1){
            window.setIvSelect(false);
            Unit ="Start";
        }
        if(Unit.equals("Stop")){
            window.setIvSelect(true);
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
//            return;
        }

        //[15, 20, 0, 51, 0, 0, 2, 90, 0, 16, 0, 0, 0, 0, 0, 0, 1, 1]
        String re = "A2--->>>:时间："+StringUtil.getTimeToYMD(duration*1000,"mm:ss")+",划桨次数："+stroke+",浆频："+speed_zdy+",距离："+userOkDistance+"米,卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",speed_zdy："+speed_zdy+",状态："+Unit;
        Log.w("---》》》", re);
        if(isOutApp){
            return;
        }

        SendMessageUtils.sendSportDatasUploadReq(GameConnectServiceImpl.clientInfo.getCtx(),
                (long)SharedUtils.getUserId(),(int)(userOkDistance*10),duration,(int)speed_zdy);

        iv_img_.setRedHcj((float) (RobotViewSmall.mPathMeasure.getLength()/ (distance * 10000 / (userOkDistance*10 % (distance * 10000)))));

        window.setTextViewStr(userOkDistance/1000 + "", timeMinute1+"", StringUtil.getTimeToYMD(duration*1000,"mm:ss"),
                Calories + "", Watt + "", Pulse + "",stroke+"");
        window.setTextSpeed("浆频");

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(userOkDistance/1000d),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(stroke),speed_zdy+"",null,String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));

        window.setTextLoad(loadCurrent+"/"+loadMax);
        getUserBoxReceive(userOkDistance/1000d);
        setBpmDataBeanTime(Pulse);
        return;
    }

    int timeMinute1 =0;
    double userOkDistance_ls26 =0.0;
    /**
     * 跑步机
     * @param dataList
     */
    private void setData46(ArrayList<Integer> dataList) {
        int timeSecond =  dataList.get(0);//时间-秒
        int timeMinute =  dataList.get(1);//时间-分
//        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        double DistanceHi = dataList.get(2);
        double DistanceLow = dataList.get(3);


        if(userOkDistance_new==0){
            userOkDistance_new = DistanceHi+DistanceLow/100d;
        }

        userOkDistance = DistanceHi+DistanceLow/100d;

        int CaloriesHi = dataList.get(4);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(5);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(6);//跳动 千,佰
        int PulseLow = dataList.get(7);//跳动 千,佰 -个十
        Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int speedHi = dataList.get(8);//速度-百十
        int speedLow = dataList.get(9);//速度-个小数点下一位
        speed_zdy = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int Incline = dataList.get(10);

        ConstValues_Ly.CURRENT_STATE = dataList.get(11);
        String Unit ="None";
        if(dataList.get(11)==2){
            Unit ="Start";
            window.setIvSelect(false);
        }else if(dataList.get(11)==1){
            Unit ="Stop";
            window.setIvSelect(true);
        }else if(dataList.get(11)==3){
            Unit ="pause";
            window.setIvSelect(true);
        }
        //[52, 11, 0, 25, 0, 14, 0, 0, 0, 13, 3, 2, 3, 1]
        String re = "setData46--->>>:时间："+ZTime+",距离："+userOkDistance+",坡度："+Incline+",卡路里："+Calories+",脉跳："+Pulse+",速度："+speed_zdy+",状态："+Unit;
        Log.w("---》》》", re);

        if(isOutApp){
            return;
        }

        if(isNewActvity){
            isNewActvity = false;
            setData46Js();
        }
        setBpmDataBeanTime(Pulse);
//        window.setTextViewStr(userOkDistance + "", speed + "", ZTime, Calories + "", 0 + "", Pulse + "",Incline+"");
        window.setTextTvWatt(Incline+"","扬升");
        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(userOkDistance),null,
                String.valueOf(Pulse),String.valueOf(Incline),null,null,
                null,String.valueOf(speed_zdy),null,String.valueOf(System.currentTimeMillis()),null));
        return;
    }

    private void setData46Js() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!isOutApp){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(isOutApp){
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            duration++;
                            if(userOkDistance_new > 0  && userOkDistance - userOkDistance_new >= 0){
                                userOkDistance = userOkDistance-userOkDistance_new;
                            }

                            if(userOkDistance < 0){
                                userOkDistance = 0;
                            }
                            userOkDistance = Double.parseDouble(String.format("%.2f", userOkDistance));

                            if(userOkDistance_ls26 < userOkDistance){
                                userOkDistance_ls26 = userOkDistance;
                            }

                            SendMessageUtils.sendSportDatasUploadReq(GameConnectServiceImpl.clientInfo.getCtx(),
                                    (long)SharedUtils.getUserId(),(int)(userOkDistance_ls26*10000),duration,(int)(speed_zdy*10000));
                            //userId:96;distance:3000;timestamp:25000;speed:149000
                            getUserBoxReceive(userOkDistance);
                            window.setTextViewStr(userOkDistance + "", speed_zdy + "", StringUtil.getTimeToYMD(duration*1000,"mm:ss"),
                                    Calories + "", 0 + "", Pulse + "","0");
                        }
                    });
                }
            }
        }).start();
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

    private LoadDialog dialog;
    private void show(String str) {
        if(dialog==null){
            dialog = new LoadDialog(this, str);
        }

        if (!isFinishing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }


    private void getUserBases(List<Integer> userIds) {
        RetrofitUtil.getInstance().apiService()
                .getUserBases(userIds)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<UserOwnInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<UserOwnInfo>> result) {
                        if (result.getCode() == 0) {
                            addUserMembersInfo(result.getData());
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

    private void addUserMembersInfo(List<UserOwnInfo> mData) {
        for(int i=0;i<mData.size();i++){
            boolean isContains = false;
            for(int j = 0;j<userMembersImg.size();j++){
                if(mData.get(i).getUserId().equals(userMembersImg.get(j).getUserId())){
                    isContains = true;
                }
            }
            if(!isContains){
                MapDetailsBean.UserBean mUserBean = new MapDetailsBean.UserBean();
                mUserBean.setNickName(mData.get(i).getNickName());
                mUserBean.setGender(mData.get(i).getGender());
                mUserBean.setAvatar(mData.get(i).getAvatar());
                mUserBean.setUserId(mData.get(i).getUserId());
                userMembersImg.add(0,mUserBean);
                getImgBitmap(mUserBean);
            }
        }
    }

    public void getImgBitmap(MapDetailsBean.UserBean mUserBean) {
        View view = View.inflate(this, R.layout.item_img_head, null);
        RoundImageView image_iv =  view.findViewById(R.id.image_iv);
        Glide.with(this).load(mUserBean.getAvatar()).into(image_iv);
        RobotView_Zx.listUserAnimator.add(new RobotView_Zx.ValueUserAnimator(iv_img, mUserBean.getUserId()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.destroyDrawingCache();
                            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                            view.setBackgroundColor(Color.TRANSPARENT);
                            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                            view.setDrawingCacheEnabled(true);
                            iv_img.addData(new UserImgBitmap(mUserBean.getUserId(),view.getDrawingCache()));
                            isGJinru = true;
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isOutApp && mMapDetailsBean_zx.getType().equals(MotorPatternActivity.ROOM_TYPE[1])){
            return;
        }
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
