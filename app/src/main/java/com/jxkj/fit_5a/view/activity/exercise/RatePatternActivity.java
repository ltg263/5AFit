package com.jxkj.fit_5a.view.activity.exercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartAnimationType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.StepArcView;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.adapter.TaskFinishListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RatePatternActivity extends BaseActivity {


    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list_bpm)
    RecyclerView mRvListBpm;
    @BindView(R.id.sv)
    StepArcView mSv;
    @BindView(R.id.ll_xl)
    ImageView mLlXl;
    @BindView(R.id.ll_zh)
    ImageView mLlZh;
    @BindView(R.id.iv_go_img)
    ImageView iv_go_img;
    @BindView(R.id.ll_qd)
    LinearLayout mLlQd;
    @BindView(R.id.tv_top_xl)
    TextView mTvTopXl;
    @BindView(R.id.tv_top_qd)
    TextView mTvTopQd;
    @BindView(R.id.tv_top_zh)
    TextView mTvTopZh;
    @BindView(R.id.tv_v)
    TextView tv_v;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_movingTye)
    TextView tv_movingTye;
    @BindView(R.id.tv_distance)
    TextView tv_distance;
    @BindView(R.id.tv_bfb)
    TextView tv_bfb;
    @BindView(R.id.tv_current_xz)
    TextView tv_current_xz;
    @BindView(R.id.tv_ztime)
    TextView tv_ztime;
    @BindView(R.id.tv_kcal)
    TextView tv_kcal;
    @BindView(R.id.tv_rpm)
    TextView tv_rpm;
    @BindView(R.id.tv_load)
    TextView tv_load;
    @BindView(R.id.ll_lat)
    LinearLayout ll_lat;
    @BindView(R.id.ll_zh_top)
    LinearLayout ll_zh_top;
    int loadCurrent = 1;
    int loadMax = ConstValues_Ly.maxLoad;
    private TaskFinishListAdapter mTaskFinishListAdapter;
//130528199003037903
    int currentPos = 1;
    long time = 0;
    private List<Byte> mData1 = new ArrayList<>();
    private List<Byte> mData2 = new ArrayList<>();
    private String movingType;
    private int ZTimeOK;
    private ArrayList<BpmDataBean> mBpmDataBeans;

    double userWeiget = 75;
    MediaPlayer mediaPlayer;
    @Override
    protected int getContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒
        return R.layout.activity_rate_pattern;
    }

    @Override
    protected void initViews() {
        if(PopupWindowLanYan.ble4Util==null || !PopupWindowLanYan.ble4Util.isConnect()){
            ToastUtils.showShort("请先链接设备");
            finish();
            return;
        }
        initAAChar();
        movingType = getIntent().getStringExtra("movingType");
        ZTimeOK = getIntent().getIntExtra("ZTimeOK",0);
        if(ZTimeOK>0){
            isXl = true;
        }
        tv_movingTye.setText(movingType);
//        GlideImgLoader.setGlideImage(this,);
        mediaPlayer = MediaPlayer.create(this, R.raw.ready_go);
        mediaPlayer.setLooping(false);//设置为循环播放
        mediaPlayer.start();

        userWeiget = Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_WEIGHT, "0"));
        Glide.with(this).load(R.drawable.ic_yundong_go_h).listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }
            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载一次
                    ((GifDrawable)resource).setLoopCount(1);
                }
                return false;
            }
        }).into(iv_go_img);
        mBpmDataBeans = getIntent().getParcelableArrayListExtra("mBpmDataBeans");
        if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]) ){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1]){
            ll_lat.setVisibility(View.GONE);
            isStart_56 = true;
            duration = 1;
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2]){
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
            ll_lat.setVisibility(View.GONE);
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]){
            ll_lat.setVisibility(View.GONE);
//            DialogUtils.showDialogHint(this, "请按设备Start/开始按钮来开始", true, null);
            DialogUtils.showUnificationDialog(this, "提示","请按设备Start/开始按钮来开始", "确定",false,null);
//            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
        }
        startTimestamp = System.currentTimeMillis();
        time = getIntent().getLongExtra("time",0);
        if(time>0){
            byte[] data =  getIntent().getByteArrayExtra("data");
            TimeThreadUtils.sendDataA6(time,data);
        }
        mSv.setCurrentCount(100, 0);
        mTaskFinishListAdapter = new TaskFinishListAdapter(mBpmDataBeans);
        mRvListBpm.setLayoutManager(new LinearLayoutManager(this));
        mRvListBpm.setHasFixedSize(true);
        mRvListBpm.setAdapter(mTaskFinishListAdapter);

        mTaskFinishListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        /**
         * 广播动态注册
         */
        mMyReceiver = new MyReceiver();//集成广播的类
        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");// 创建IntentFilter对象
        registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive

    }
    MyReceiver mMyReceiver;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMyReceiver!=null){
            unregisterReceiver(mMyReceiver);
        }
    }
    private AAOptions aaOptions;
    private AAChartModel aaChartModel;
    private void initAAChar() {
        aaChartModel = configureChartModel();
        if (aaOptions == null) {
            aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        }
        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
    }


    private AAChartModel configureChartModel() {
        return new AAChartModel()
                .chartType(AAChartType.Areaspline)
                .title("")
                .yAxisTitle("")
                .yAxisLabelsEnabled(false)
                .xAxisLabelsEnabled(false)
                .yAxisGridLineWidth(0f)
                .xAxisGridLineWidth(0f)
                .xAxisVisible(false)
                .legendEnabled(false)
                .markerRadius(0f)
                .touchEventEnabled(true)
                .markerSymbol(AAChartSymbolType.Circle)
                .scrollablePlotArea(
                        new AAScrollablePlotArea()
                                .minWidth(3000)
                                .scrollPositionX(1f)
                )
                .series(getDataList(mData1,mData2));
    }

    private AASeriesElement[] getDataList(List<Byte> data1,List<Byte> data2){
        if(currentPos==1){
            AASeriesElement element = new AASeriesElement()
                    .name("心率")
                    .lineWidth(1f)
                    .color("#FFB300")
                    .data(data1.toArray());
            return new AASeriesElement[]{element};
        }
        if(currentPos==2){
            AASeriesElement element = new AASeriesElement()
                    .name("速度")
                    .lineWidth(1f)
                    .color("#00A2FF")
                    .data(data2.toArray());
            return  new AASeriesElement[]{element};
        }
        if(currentPos==3){
            AASeriesElement element1 = new AASeriesElement()
                    .name("心率")
                    .lineWidth(1f)
                    .fillOpacity(0.5f)
                    .color("#FFB300")
                    .data(data1.toArray());

            AASeriesElement element2 = new AASeriesElement()
                    .name("速度")
                    .lineWidth(1f)
                    .fillOpacity(0.5f)
                    .color("#00A2FF")
                    .data(data2.toArray());

            return new AASeriesElement[]{element1,element2};
        }
        return null;
    }
    boolean isStart_56 = false;
    @OnClick({R.id.iv_back,R.id.iv_jian,R.id.iv_jia, R.id.view,R.id.tv_top_xl, R.id.tv_top_qd, R.id.tv_top_zh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_jian:
                if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                        && loadCurrent>1){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent-1)));
                }
                break;
            case R.id.iv_jia:
                if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])
                        && loadCurrent<loadMax){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6, (byte)(loadCurrent+1)));
                }
                break;
            case R.id.view:
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0]
                        || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]){
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                }
                if (ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]) {
                    isStart_56 = false;
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]){
//                    DialogUtils.showDialogHint(this, "请按设备Stop/结束按钮来结束", true, null);
                    DialogUtils.showUnificationDialog(this, "提示","请按设备Stop/结束按钮来结束", "确定",false,null);
                    return;
                }
                DialogUtils.showDialogStartYd(this, new DialogUtils.DialogInterfaceS() {
                    @Override
                    public void btnType(int pos) {
                        if (pos == 2) {
                            startTaskFinishActivity();
                        }else{
                            if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0]
                                    || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]){
                                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                            }
                            if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1]){
                                isStart_56 = true;
                            }
                            dismiss();
                        }
                    }
                });
                break;
            case R.id.tv_top_xl:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_text_theme));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_666666));
                mLlXl.setVisibility(View.VISIBLE);
                mLlZh.setVisibility(View.INVISIBLE);
                mLlQd.setVisibility(View.INVISIBLE);
                currentPos = 1;
                aaChartModel.series(getDataList(mData1,mData2));
                mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
                break;
            case R.id.tv_top_qd:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_text_theme));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_666666));
                mLlXl.setVisibility(View.INVISIBLE);
                mLlZh.setVisibility(View.INVISIBLE);
                mLlQd.setVisibility(View.VISIBLE);
                currentPos = 2;
                aaChartModel.series(getDataList(mData1,mData2));
                mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
                break;
            case R.id.tv_top_zh:
                mTvTopXl.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopQd.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopZh.setTextColor(getResources().getColor(R.color.color_text_theme));
                mLlXl.setVisibility(View.INVISIBLE);
                mLlZh.setVisibility(View.VISIBLE);
                mLlQd.setVisibility(View.INVISIBLE);
                currentPos = 3;
                aaChartModel.series(getDataList(mData1,mData2));
                mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
                break;
        }
    }

    private void startTaskFinishActivity() {
        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
//        Intent mIntent = new Intent(this, TaskFinishActivity.class);
        String pjDuration = String.valueOf((int)(Distance / (duration/60d/60d) *100)/100d);
        int heartRate = 0;
        double MaxSpeed = 0;
        int load_D = 0;
        int load_X =  Integer.valueOf(logs.get(0).getResistanceLevel());
        for (int i = 0; i < logs.size(); i++) {
            if (Double.valueOf(logs.get(i).getSpeed()) > MaxSpeed) {
                MaxSpeed = Double.valueOf(logs.get(i).getSpeed());
            }
            if(load_D<Integer.valueOf(logs.get(i).getResistanceLevel())){
                load_D = Integer.valueOf(logs.get(i).getResistanceLevel());
            }
            if(load_X>Integer.valueOf(logs.get(i).getResistanceLevel())){
                load_X = Integer.valueOf(logs.get(i).getResistanceLevel());
            }
        }
        String load_dx = load_X+"-"+load_D;
        if(load_X==load_D){
            load_dx = load_D+"";
        }

        mBpmDataBeans.get(0).setBpmTopData(
                new BpmDataBean.BpmTopData(String.valueOf(Calories), String.valueOf(Distance),
                        duration + "", pjDuration, String.valueOf(MaxSpeed), String.valueOf(heartRate),"--",load_dx,"--", StringUtil.getBai_V(logs)));
//        mIntent.putParcelableArrayListExtra("mBpmDataBeans",mBpmDataBeans);
        PostUser.SportLogInfo sportLogInfo= new PostUser.SportLogInfo();
        sportLogInfo.setBai(StringUtil.getBai_V(logs));
        sportLogInfo.setDeviceBrandId(ConstValues_Ly.BRAND_ID);
        sportLogInfo.setCalories(String.valueOf(Calories));
        sportLogInfo.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL+"");
        sportLogInfo.setDistance(String.valueOf(Distance));
        sportLogInfo.setDuration(String.valueOf(duration));
        sportLogInfo.setEndTimestamp(String.valueOf(System.currentTimeMillis()));
        sportLogInfo.setStartTimestamp(String.valueOf(startTimestamp));
        sportLogInfo.setProtocolDeviceBrandParamId(11+""+'1');
        sportLogInfo.setHeartRateSource("2");//1=器材;2=藍牙心跳;3=Apple Watch
        sportLogInfo.setTrainingMode("HeartRate");//目前只有HeartRate(心率)、Program(课程)
        sportLogInfo.setProtocolName("iconsole");
        PostUser.SportLogInfo.DetailsBean deleteDatabase = new PostUser.SportLogInfo.DetailsBean();
        deleteDatabase.setLogs(logs);
        sportLogInfo.setDetails(deleteDatabase);
        HttpRequestUtils.psotUserSportLog(sportLogInfo, new HttpRequestUtils.LoginInterface() {
            @Override
            public void succeed(String data_id) {
//                mIntent.putExtra("data_id",data_id);
//                startActivity(mIntent);
                if(StringUtil.isNotBlank(data_id)){
                    IntentUtils.getInstence().intent(
                            RatePatternActivity.this, ExerciseRecordDetailsActivity_xl.class,
                            "id",data_id);
                }
                finish();
            }
        });
        PopupWindowLanYan.ble4Util.disconnect();
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("MyTag", "onReceive: "+intent.getStringExtra("type"));
            if(intent.getStringExtra("type").equals("b2")){
                ArrayList<Integer> dataList = intent.getIntegerArrayListExtra("data");
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] && dataList.size()==16){
                    setData1(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1] && dataList.size()==14){
                    setData56(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3] && dataList.size()==18){
                    setData26(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4] && dataList.size()==14){
                    setData46(dataList);
                }
                if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2] && dataList.size()==14){
                    //健腹轮
                }
            }
        }
    }
    int Calories;
    double Distance_56;
    double Distance;
    int duration;
    long startTimestamp;

    List<PostUser.SportLogInfo.DetailsBean.LogsBean> logs = new ArrayList<>();
    private void setData1(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

        int rpmHi = dataList.get(4);//每分钟转数 -千百
        int rpmLow = dataList.get(5);//每分钟转数 -十个
        int rpm = ConstValues_Ly.getQianBaiShiGe(rpmHi,rpmLow);

        int DistanceHi = dataList.get(6);//距离-百十
        int DistanceLow = dataList.get(7);//距离-个小数点下一位
        Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);
        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

        loadCurrent = dataList.get(14);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(15);
        String Unit ="Stop";
        if(dataList.get(15)==1){
            Unit ="Start";
        }

        String re = "A2--->>>:时间："+ZTime+",速度："+speed+",转数："+rpm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",阻力："+loadCurrent+",状态："+Unit;
        Log.w("---》》》", re);
        if(Unit.equals("Stop")){
            return;
        }
        mData1.add((byte) Pulse);
        mData2.add((byte) speed);

        setTextViewString(Calories+"",rpm+"",Pulse+"",speed+"",Distance+"",loadCurrent+"/"+loadMax,ZTime);

        setBpmDataBeanTime(Pulse);


        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm),String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));

        return;
    }
    double speed;
    private void setData56(ArrayList<Integer> dataList) {
//        int timeMinute =  dataList.get(0);//时间-分
//        int timeSecond =  dataList.get(1);//时间-秒
//        duration = timeMinute*60+timeSecond;
//        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int speedHi = dataList.get(2);//速度-百十
        int speedLow = dataList.get(3);//速度-个小数点下一位
//        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

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


        String ZTime = null;
        double perimeter = ConstValues_Ly.wheelDiameter * 2.54/*inch*/ * Math.PI/*PI*/ / 100000.0/*cm to km*/;
        speed = perimeter * 60/*minutes of hour*/ * rpm1;//km/h
        Log.w("---》》》", "A2--->>>setData56:时间：距离："+speed/3600d);
        speed = Double.parseDouble(StringUtil.getValue(speed));
        if(isStart_56){
            duration++;
            ZTime = StringUtil.getTimeToYMD(duration*1000,"mm:ss");
        }else{
            speed = 0;
        }

        Distance_56 += speed/3600d;
        Distance = Double.parseDouble(StringUtil.getValue(Distance_56));
        Calories += (int) (speed * userWeiget *1.038* (1/3600d));
        //75KG 20KM/H 距离9公里 时间1小时
        if(!isStart_56){
            return;
        }
        String re = "A2--->>>:时间："+ZTime+",速度："+speed+",转数1："+rpm1+",转数2："+rpm2+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse;
        Log.w("---》》》", re);

        mData2.add((byte) speed);
        mData1.add((byte) Pulse);
        setTextViewString(Calories+"","0",Pulse+"",speed+"",Distance+"",loadCurrent+"/"+loadMax,ZTime);
        setBpmDataBeanTime(Pulse);

        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(rpm1),String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),null));
        return;
    }

    private void setData26(ArrayList<Integer> dataList) {
        int timeMinute =  dataList.get(0);//时间-分
        int timeSecond =  dataList.get(1);//时间-秒
        duration = timeMinute*60+timeSecond;
        String ZTime = ConstValues_Ly.getTime(timeMinute,timeSecond);

        int strokeHi = dataList.get(2);
        int strokeLow = dataList.get(3);
        double stroke = ConstValues_Ly.getQianBaiShiGe(strokeHi,strokeLow);

        int spmHi = dataList.get(4);
        int spmLow = dataList.get(5);
        int spm = ConstValues_Ly.getQianBaiShiGe(spmHi,spmLow);

        int DistanceHi = dataList.get(6);
        int DistanceLow = dataList.get(7);
        Distance = ConstValues_Ly.getQianBaiShiGe(DistanceHi,DistanceLow);

        int CaloriesHi = dataList.get(8);// 卡路里 -千,佰
        int CaloriesLow = dataList.get(9);// 卡路里 -个十
        Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

        int PulseHi = dataList.get(10);//跳动 千,佰
        int PulseLow = dataList.get(11);//跳动 千,佰 -个十
        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

        int WattHi = dataList.get(12);//瓦特--佰,拾
        int WattLow = dataList.get(13);//瓦特--佰,拾个小数点下一位
        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

        int timeMinute1 =  dataList.get(14);//时间-分
        int timeSecond1 =  dataList.get(15);//时间-秒
//        int duration1 = timeMinute * 60 + timeSecond;
        String time1 = ConstValues_Ly.getTime(timeMinute1,timeSecond1);

        loadCurrent = dataList.get(16);//阻力
        ConstValues_Ly.CURRENT_STATE = dataList.get(17);
        String Unit ="Stop";
        if(dataList.get(17)==1){
            Unit ="Start";
        }

        String re = "A2--->>>:时间："+ZTime+",行程："+stroke+",spm："+spm+",距离："+Distance+",卡路里："+Calories
                +",脉跳："+Pulse+",瓦特："+Watt+",time1："+time1+",状态："+Unit;
        Log.w("---》》》", re);
        if(Unit.equals("Stop")){
            return;
        }
        mData1.add((byte) Pulse);
        mData2.add((byte) stroke);
        setTextViewString(Calories+"",0+"",Pulse+"",0+"",Distance+"",loadCurrent+"/"+loadMax,ZTime);
        setBpmDataBeanTime(Pulse);


        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),null,String.valueOf(loadCurrent),String.valueOf(loadCurrent),
                String.valueOf(stroke),null,null,String.valueOf(System.currentTimeMillis()),String.valueOf(Watt)));
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
            Unit ="Start";
        }else if(dataList.get(11)==1){
            Unit ="Stop";
        }else if(dataList.get(11)==3){
            Unit ="pause";
        }
        //[52, 11, 0, 25, 0, 14, 0, 0, 0, 13, 3, 2, 3, 1]
        String re = "A2--->>>:时间："+ZTime+",距离："+Distance+",坡度："+Incline+",卡路里："+Calories+",脉跳："+Pulse+",速度："+speed+",状态："+Unit;
        Log.w("---》》》", re);
        if(Unit.equals("Stop") && duration!=0){
            startTaskFinishActivity();
            return;
        }
        mData1.add((byte) Pulse);
        mData2.add((byte) speed);

        setTextViewString(Calories+"",0+"",Pulse+"",speed+"",Distance+"",loadCurrent+"/"+loadMax,ZTime);
        setBpmDataBeanTime(Pulse);


        logs.add(new PostUser.SportLogInfo.DetailsBean.LogsBean(String.valueOf(Calories),String.valueOf(Distance),null,
                String.valueOf(Pulse),String.valueOf(Incline),null,null,
                null,String.valueOf(speed),null,String.valueOf(System.currentTimeMillis()),null));
        return;
    }
    private void setTextViewString(String kcal,String rpm,String Pulse,String speed,String distance,String load,String time){
        if(currentPos==1){
            String str1 = "即时心跳：<font color=\"#FFB300\"><big><big>"+Pulse+"</big></big></font>bpm";
            tv_current_xz.setText(Html.fromHtml(str1));
            tv_current_xz.setVisibility(View.VISIBLE);
            ll_zh_top.setVisibility(View.GONE);
        }else if(currentPos==2){
            String str1 = "即时速度：<font color=\"#00A2FF\"><big><big>"+speed+"</big></big></font>km/h";
            tv_current_xz.setText(Html.fromHtml(str1));
            tv_current_xz.setVisibility(View.VISIBLE);
            ll_zh_top.setVisibility(View.GONE);
        }else{
            ll_zh_top.setVisibility(View.VISIBLE);
            tv_current_xz.setVisibility(View.GONE);
        }
        tv_kcal.setText(kcal);
        tv_rpm.setText(rpm);
        tv_distance.setText(distance);
        tv_load.setText(load);
        tv_time.setText(time);

        aaChartModel.scrollablePlotArea(
                new AAScrollablePlotArea()
                        .minWidth(mData1.size()*30)
                        .scrollPositionX(1f)

        ).series(getDataList(mData1,mData2))
                .animationDuration(0)
                .animationType(AAChartAnimationType.BouncePast);
        mAAChartView.aa_drawChartWithChartOptions(AAOptionsConstructor.configureChartOptions(aaChartModel));
    }

    long durationLs = 0;
    boolean isXl = false;

    private void setBpmDataBeanTime(int pulse){
        mSv.setCurrentCount(getIntent().getIntExtra("bfb",0), pulse,tv_bfb);
        if(duration>=ZTimeOK && isXl){
            isXl = false;
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
//            DialogUtils.showDialogHintYunDong(this, new DialogUtils.DialogInterfaceYhq() {
//                @Override
//                public void btnConfirm(int type) {
//                    if(type ==0){
//                        startTaskFinishActivity();
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
                                startTaskFinishActivity();
                            }else{
                                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                            }
                        }
                    });
            return;
        }
        if(durationLs==duration){
            return;
        }
        durationLs = duration;
        mTaskFinishListAdapter.setZtime(Integer.valueOf(duration));
        tv_ztime.setText("总时间："+StringUtil.getTimeGeShi(duration));
        mTaskFinishListAdapter.setNewData(mBpmDataBeans);

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

}
