package com.jxkj.fit_5a.view.activity.yaling;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.chileaf.fitness.callback.DumbBellCountCallback;
import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TTSUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok_jx;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.StepArcView;
import com.jxkj.fit_5a.conpoment.view.StepArcView_Btn;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;

public class YaLingActivity_2 extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_gs)
    TextView tv_gs;
    @BindView(R.id.tv_geshu)
    TextView tv_geshu;
    @BindView(R.id.tv_geshu_1)
    TextView tv_geshu_1;
    @BindView(R.id.tv_shijian)
    TextView tv_shijian;
    @BindView(R.id.tv_shijian_1)
    TextView tv_shijian_1;
    @BindView(R.id.tv_kaluli)
    TextView tv_kaluli;
    @BindView(R.id.tv_kaluli_1)
    TextView tv_kaluli_1;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv_1)
    ImageView iv_1;
    @BindView(R.id.iv_2)
    ImageView iv_2;
    @BindView(R.id.iv_laba)
    ImageView iv_laba;
    @BindView(R.id.sv)
    StepArcView sv;
    @BindView(R.id.iv_go_img)
    ImageView iv_go_img;
    @BindView(R.id.iv_back_1)
    ImageView iv_back_1;
    @BindView(R.id.rl_shipin)
    RelativeLayout rl_shipin;
    @BindView(R.id.rl_new)
    RelativeLayout rl_new;
    @BindView(R.id.tv_title_z)
    TextView tv_title_z;
    @BindView(R.id.sv_btn)
    StepArcView_Btn sv_btn;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    @BindView(R.id.rl_top)
    RelativeLayout rl_top;
    @BindView(R.id.mp_video_1)
    JzvdStdTikTok_jx mMpVideo;
    @BindView(R.id.mp_video_2)
    JzvdStdTikTok_jx mMpVideo2;
    int type,type_num;//0 定数计时  1定时计数 2自由模式
    static long current_time = 0;
    static long current_count = 0;
    PostUser.SportLogInfo sportLogInfo;
    MediaPlayer mediaPlayer;
    boolean isStart = false;
    boolean isOk = false;
    private Handler heartHandler = new Handler();
    /**
     * 计时器
     */
    private Runnable hearRunable = new Runnable() {
        @Override
        public void run() {
            Log.w("hearRunable","isStart:"+isStart);
//            if(!isStart){
//                return;
//            }
            Log.w("hearRunable","current_time:"+current_time);
            current_time++;
            tv_shijian.setText(StringUtil.getTimeGeShiYw(current_time));
            tv_shijian_1.setText(StringUtil.getTimeGeShiYw(current_time));
            tv_kaluli.setText(getCaloriesNum());
            tv_kaluli_1.setText(getCaloriesNum());
            if(isStart){
                heartHandler.postDelayed(hearRunable, 1000);
            }
            logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(getCaloriesNum(),""+current_count,""+current_time,
                    "0",""+System.currentTimeMillis()));
            if(type == 1 && !isOk){
//                    tv_geshu.setText(current_time+"s");
                sv.setCurrentCount(type_num, (int) current_time,null);
                if(current_time >= type_num ){
                    isStart = false;
                    iv_2.setSelected(!isStart);
                    isOk = true;
                    PopupWindowLanYan.mManager.pause();
                    DialogUtils.showUnificationDialog(YaLingActivity_2.this, "当前模式已完成",
                            "再坚持一下?会让你的训练更高效", "结束训练","再练一会",
                            new DialogUtils.UnificationDialogInterface() {
                                @Override
                                public void bntClickListener(String pos) {
                                    if(pos.equals("1")){
                                        setSportLogInfo();
                                        YaLingActivity_3.intentActivity(YaLingActivity_2.this,sportLogInfo);
                                    }else{
                                        isStart = true;
                                        iv_2.setSelected(!isStart);
                                        heartHandler.postDelayed(hearRunable, 1000);
                                        PopupWindowLanYan.mManager.start();
                                    }
                                }
                            });
                }
            }
        }
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_yaling_2;
    }
    List<PostUser.SportLogInfo.DetailsBean.LogsBean> logs = new ArrayList<>();
   //卡路里消耗量 = (师铃重量X 重次 X 0.029) + (重  0.13)   (分) /60
    private String getCaloriesNum(){
        double yaling_zl = getIntent().getDoubleExtra("yaling_zl",0);
        double userWeiget = Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_WEIGHT, "0"));//体重
        int kll = (int) ((yaling_zl*current_count*0.029)+(userWeiget*0.13)*current_time/3600);
//        Log.w("getCaloriesNum","kll"+kll);
        return StringUtil.getValue(kll);
    }

    public void setSportLogInfo() {
        sportLogInfo = new PostUser.SportLogInfo();
//        sportLogInfo.setBai(null);
//        sportLogInfo.setMapId(null);
        sportLogInfo.setDuration(""+current_time);//总时间
//        sportLogInfo.setDeviceBrandId("0");//ConstValues_Ly.BRAND_ID
        sportLogInfo.setCalories(StringUtil.getValue(getCaloriesNum()));//k
        sportLogInfo.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL + "");//设备类型ID
        sportLogInfo.setDistance(String.valueOf(current_count));//数量
        sportLogInfo.setEndTimestamp(String.valueOf(System.currentTimeMillis()));//当前时间
        sportLogInfo.setStartTimestamp(String.valueOf(startTimestamp));//开始时间
        sportLogInfo.setProtocolName("QiLiDumbbell");
        sportLogInfo.setCityAdCode(SharedUtils.singleton().get(ConstValues.CITY_AD_CODE,""));
        sportLogInfo.setExtParams(getIntent().getStringExtra("extParams"));//设备类型
        PostUser.SportLogInfo.DetailsBean deleteDatabase = new PostUser.SportLogInfo.DetailsBean();
        deleteDatabase.setLogs(logs);
        sportLogInfo.setDetails(deleteDatabase);
        //训练模式(目前只有HeartRate(心率)、Program(课程)、QuickStart(快速开始)、Racing(竞速模式))
        sportLogInfo.setTrainingMode("Dumbbell");
    }
    long startTimestamp;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        iv_2.setSelected(true);
        mMpVideo2.state = 1 ;
        mTvTitle.setText("哑铃");
        String estParams = getIntent().getStringExtra("extParams");
        if(StringUtil.isNotBlank(estParams)){
            try {
                JSONObject mJSONObject = new JSONObject(estParams);
                mTv.setText(mJSONObject.getString("sportType"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        tv_geshu.setText(String.valueOf(current_count));
        tv_geshu_1.setText(String.valueOf(current_count));
        tv_shijian.setText(StringUtil.getTimeGeShiYw(current_time));
        tv_shijian_1.setText(StringUtil.getTimeGeShiYw(current_time));
        tv_kaluli.setText(getCaloriesNum());
        tv_kaluli_1.setText(getCaloriesNum());
        type = getIntent().getIntExtra("type",0);
        type_num = getIntent().getIntExtra("type_num",0);
        startTimestamp = System.currentTimeMillis();
        sv.setCurrentCount(type_num, 0,null);
        donhuayinyue();
        if(PopupWindowLanYan.mManager!=null) {
            PopupWindowLanYan.mManager.setDumbBellCountCallback(new DumbBellCountCallback() {
                @Override
                public void onDumbBellCountReceived(@NonNull @NotNull BluetoothDevice device, long count) {
                    Log.w("bluetoothDeviceName:", "--->>" + count);
                    if (!isStart) {
                        return;
                    }
                    if (current_count != count) {
                        TTSUtils.getInstance().speak(count + "");
                    }
                    current_count = count;
                    tv_geshu.setText(String.valueOf(count));
                    tv_geshu_1.setText(String.valueOf(count));
                    if (type == 2) {
                        int num = (int) current_count;
                        if (num > 10) {
                            num = num % 10;//个位直接%10
                            if (num == 0) {
                                num = 10;
                            }
                        }
                        sv.setCurrentCount(10, num, null);
                    }
                    if (type == 0 && !isOk) {
                        sv.setCurrentCount(type_num, (int) count, null);
                        if (count >= type_num) {
                            isStart = false;
                            iv_2.setSelected(!isStart);
                            PopupWindowLanYan.mManager.pause();
                            isOk = true;
                            DialogUtils.showUnificationDialog(YaLingActivity_2.this, "当前模式已完成", "再坚持一下?会让你的训练更高效", "结束训练", "再练一会",
                                    new DialogUtils.UnificationDialogInterface() {
                                        @Override
                                        public void bntClickListener(String pos) {
                                            if (pos.equals("1")) {
                                                PopupWindowLanYan.mManager.stop();
                                                setSportLogInfo();
                                                YaLingActivity_3.intentActivity(YaLingActivity_2.this, sportLogInfo);
                                            } else {
                                                isStart = true;
                                                iv_2.setSelected(!isStart);
                                                if(current_time==0){
                                                    heartHandler.postDelayed(hearRunable, 1000);
                                                }
                                                PopupWindowLanYan.mManager.start();
                                            }
                                        }
                                    });
                        }
                    }
                }
            });
        }
        iv_1.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP){
                sv_btn.setStopAnimator();
            }
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                sv_btn.setCurrentCount(100, 100, new StepArcView_Btn.CurrentAngleInterface() {
                    @Override
                    public void complete() {
                        isStart = false;
                        if(current_count < 3 && current_time < 10){
                            ToastUtils.showShort("本次运动的时间或次数过少,无法保存记录");
                            PopupWindowLanYan.mManager.stop();
                            finish();
                            return;
                        }
                        PopupWindowLanYan.mManager.stop();
                        setSportLogInfo();
                        YaLingActivity_3.intentActivity(YaLingActivity_2.this,sportLogInfo);
                    }
                });
            }
            return true;
        });
        mMpVideo.fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
        iv_back_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
        mMpVideo2.fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Log.w("11111onResume","++++++++++++++++++++");
//            rl_top.setVisibility(View.VISIBLE);
//            ll_top.setVisibility(View.GONE);
//        } else {
//            rl_top.setVisibility(View.GONE);
//            ll_top.setVisibility(View.VISIBLE);
//            Log.w("11111onResume","---------------------");
//        }
//    }

    private void donhuayinyue() {
        if(isBoFANG){
            isBoFANG = false;
            mediaPlayer = MediaPlayer.create(this, R.raw.ready_go);
            mediaPlayer.setLooping(false);//设置为循环播放
            mediaPlayer.start();


            GlideImageLoader.loadOneTimeGif(this, R.drawable.ic_yundong_go_h, iv_go_img, new GlideImageLoader.GifListener() {
                @Override
                public void gifPlayComplete() {
                    iv_go_img.setVisibility(View.GONE);
                    iv_go_img.setImageResource(R.drawable.icon);
                }
            });
        }else{
            iv_go_img.setVisibility(View.GONE);
            iv_go_img.setImageResource(R.drawable.icon);
        }

//        Glide.with(this).load(R.drawable.ic_yundong_go_h).listener(new RequestListener() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
//                return false;
//            }
//            @Override
//            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
//                if (resource instanceof GifDrawable) {
//                    //加载一次
//                    ((GifDrawable)resource).setLoopCount(1);
//                }
//                return false;
//            }
//        }).into(iv_go_img);

        if(PopupWindowLanYan.mManager==null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isStart = true;
                            iv_2.setSelected(!isStart);
                            heartHandler.postDelayed(hearRunable, 1000);
                            PopupWindowLanYan.mManager.start();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @OnClick({R.id.ll_back, R.id.iv_2,R.id.iv_1,R.id.iv_laba,R.id.tv_title_a,R.id.rl_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_laba:
                boolean isXiang = SharedUtils.singleton().get("yuyinbobao",true);
                if(isXiang){
                    SharedUtils.singleton().put("yuyinbobao",false);
                    iv_laba.setImageResource(R.mipmap.ic_ydxq_13);
                }else{
                    SharedUtils.singleton().put("yuyinbobao",true);
                    iv_laba.setImageResource(R.mipmap.ic_ydxq_12);
                }
                break;
            case R.id.ll_back:
            case R.id.iv_1:
                if(current_count < 3 && current_time < 10){
                    DialogUtils.showUnificationDialog(YaLingActivity_2.this, "确定要结束当前模式吗?","本次运动的时间或次数过少,无法\n保存记录,再坚持一下吧？", "结束训练","再练一会",
                            new DialogUtils.UnificationDialogInterface() {
                                @Override
                                public void bntClickListener(String pos) {
                                    if(pos.equals("1")){
                                        isStart = false;
                                        PopupWindowLanYan.mManager.stop();
                                        finish();
                                    }
                                }
                            });
                    return;
                }
                DialogUtils.showUnificationDialog(this, "提示", "您确定结束当前模式吗？", "确定", true,
                        new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                isStart = false;
                                PopupWindowLanYan.mManager.stop();
                                setSportLogInfo();
                                YaLingActivity_3.intentActivity(YaLingActivity_2.this,sportLogInfo);
                            }
                        });
                break;
            case R.id.rl_ok:
            case R.id.tv_title_a:
                Intent intent = new Intent(YaLingActivity_2.this, YaLingActivity_sp.class);
                startActivityForResult(intent,0);
                break;
            case R.id.iv_2:
                isStart = !isStart;
                iv_2.setSelected(!isStart);
                if(isStart){
                    heartHandler.postDelayed(hearRunable, 1000);
                    PopupWindowLanYan.mManager.start();
                }else{
                    PopupWindowLanYan.mManager.pause();
                }
                break;
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("imageUrl",imageUrl);
        outState.putString("videoId",videoId);
        outState.putString("title",title);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.w("+++","onRestoreInstanceState");
        imageUrl = savedInstanceState.getString("imageUrl");
        videoId = savedInstanceState.getString("videoId");
        title = savedInstanceState.getString("title");

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 设备处于横屏状态
            // 在此处添加横屏状态的逻辑处理代码
            rl_top.setVisibility(View.VISIBLE);
            ll_top.setVisibility(View.GONE);
            getPlayInfo(videoId,imageUrl,title,mMpVideo2);
//            mMpVideo2.setScreenFullscreen();
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 设备处于竖屏状态
            // 在此处添加竖屏状态的逻辑处理代码
            rl_top.setVisibility(View.GONE);
            ll_top.setVisibility(View.VISIBLE);
            getPlayInfo(videoId,imageUrl,title,mMpVideo);
        }
    }


    String imageUrl,videoId,title;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode==0 && data!=null) {
            getPlayInfo(data.getStringExtra("videoUrl"),data.getStringExtra("coverImageUrl"),
                    data.getStringExtra("title"),mMpVideo);
        }
    }
    private void getPlayInfo(String videoId, String imageUrl, String title, JzvdStdTikTok_jx mV) {
        Log.w("getPlayInfo","videoId"+videoId);
        Log.w("getPlayInfo","imageUrl"+imageUrl);
        this.imageUrl = imageUrl;
        this.videoId = videoId;
        this.title = title;
        tv_title_z.setText(title);
        HttpRequestUtils.getPlayInfo(this, videoId, new HttpRequestUtils.PlayInfoInterface() {
            @Override
            public void succeed(Result<VideoPlayInfoBean> result) {
                if (result.getCode() == 0 && result.getData() != null
                        && result.getData().getPlayInfoList() != null && result.getData().getPlayInfoList().size() > 0) {
                    String playUrl = HttpRequestUtils
                            .initVideo(YaLingActivity_2.this, result.getData().getPlayInfoList()
                                    .get(0).getPlayURL(), videoId);
                    JZDataSource jzDataSource = new JZDataSource(playUrl, "");
                    jzDataSource.looping = true;
                    mV.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
                    Glide.with(mV.getContext()).load(imageUrl).into(mV.posterImageView);
                    rl_shipin.setVisibility(View.VISIBLE);
                    rl_new.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(mMpVideo2!=null && mMpVideo2.jzDataSource!=null){
                mMpVideo2.startVideo();
            }
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if(mMpVideo!=null && mMpVideo.jzDataSource!=null){
                mMpVideo.startVideo();
            }
        }
    }
    public static boolean isBoFANG = true;
    //0 定数计时  1定时计数 2自由模式
    public static void intentActivity(Context mContext,int type,int type_num,double yaling_zl,String isLBUnit,String extParams) {
        SharedUtils.singleton().put("et_yaling_zl",yaling_zl+"");
        SharedUtils.singleton().put("isLBUnit",isLBUnit);
        Log.w("initViews","initViews"+yaling_zl+";isLBUnit"+isLBUnit);
        String zl = SharedUtils.singleton().get("et_yaling_zl","0.0");
        String isLBUnit1 = SharedUtils.singleton().get("isLBUnit","");
        Log.w("initViews","initViews"+zl+";isLBUnit"+isLBUnit1);
        if(isLBUnit.equals("1")){
            yaling_zl =  yaling_zl*2.205d;
        }
        Intent mIntent = new Intent(mContext, YaLingActivity_2.class);
        mIntent.putExtra("type",type);
        mIntent.putExtra("type_num",type_num);
        mIntent.putExtra("yaling_zl",yaling_zl);
        mIntent.putExtra("extParams",extParams);
        Log.w("extParams","extParams:"+extParams);
        isBoFANG = true;
        current_time = 0;
        current_count = 0;
        if(PopupWindowLanYan.mManager!=null){
            PopupWindowLanYan.mManager.stop();
        }
        mContext.startActivity(mIntent);
//        mContext.startActivity(new Intent(mContext,FacilityManageActivity.class));
    }

}
