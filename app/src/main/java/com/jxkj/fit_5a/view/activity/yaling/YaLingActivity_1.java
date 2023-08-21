package com.jxkj.fit_5a.view.activity.yaling;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceCourseData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.PopupWindowYaLingSb;
import com.jxkj.fit_5a.entity.TrainingCourseData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.YaLingB_1Adapter;
import com.jxkj.fit_5a.view.adapter.YaLingTop_1Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class YaLingActivity_1 extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_list_1)
    RecyclerView mRvList1;
    @BindView(R.id.rv_list_2)
    RecyclerView mRvList2;
    @BindView(R.id.ll_dingshi)
    LinearLayout ll_dingshi;
    @BindView(R.id.ll_dingshijishu)
    LinearLayout ll_dingshijishu;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.tv_hour)
    TextView tv_hour;
    @BindView(R.id.tv_min)
    TextView tv_min;
    @BindView(R.id.tv_yuyinshezhi)
    TextView tv_yuyinshezhi;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.et_yaling_zl)
    EditText et_yaling_zl;
    @BindView(R.id.tv_danwei)
    TextView tv_danwei;
    @BindView(R.id.tv_danwei_lb)
    TextView tv_danwei_lb;
    private YaLingTop_1Adapter mYaLingTop_1Adapter;
    private YaLingB_1Adapter mYaLingB_1Adapter;
    List<String> listTime = new ArrayList<>();
//    int type = 2;//0 定数计时  1定时计数 2自由模式
    JSONObject mJSONObject = new JSONObject();
    int num_yundong_ci = 0;
    String isLBUnit;
    @Override
    protected int getContentView() {
        return R.layout.activity_yaling_1;
    }

    @Override
    protected void initViews() {
        tv_yuyinshezhi.setVisibility(View.VISIBLE);
        mTvTitle.setText("模式选择");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        String zl = SharedUtils.singleton().get("et_yaling_zl","0.0");
        isLBUnit = SharedUtils.singleton().get("isLBUnit","0");
        Log.w("initViews","initViews"+zl+";isLBUnit"+isLBUnit);
        tv_danwei.setBackgroundResource(R.drawable.bj_shape_line_theme_2);
        tv_danwei_lb.setBackgroundResource(R.drawable.bj_shape_line_666_2);
        tv_danwei.setTextColor(getColor(R.color.color_text_theme));
        tv_danwei_lb.setTextColor(getColor(R.color.color_666666));
        if(StringUtil.isNotBlank(isLBUnit) && isLBUnit.equals("1")){
            tv_danwei.setBackgroundResource(R.drawable.bj_shape_line_666_2);
            tv_danwei_lb.setBackgroundResource(R.drawable.bj_shape_line_theme_2);
            tv_danwei.setTextColor(getColor(R.color.color_666666));
            tv_danwei_lb.setTextColor(getColor(R.color.color_text_theme));
        }
        if(Double.parseDouble(zl)>0){
            et_yaling_zl.setText(zl);
        }
        trainingCourseQueryList();
        num_yundong_ci = SharedUtils.singleton().get("num_yundong_ci",0);
        tv_num.setText("运动次数("+num_yundong_ci+"次)");
        if(num_yundong_ci==0){
            num_yundong_ci = 50;
            tv_num.setText("运动次数("+num_yundong_ci+"次)");
        }
        initRvUi();
    }

    private void trainingCourseQueryList() {
        RetrofitUtil.getInstance().apiService()
                .trainingCourseQueryList(ConstValues_Ly.DEVICE_TYPE_ID_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<TrainingCourseData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TrainingCourseData>> result) {
                        if (isDataInfoSucceed(result)) {
                            mYaLingTop_1Adapter.setNewData(result.getData());
                            if(mYaLingTop_1Adapter.getData().size()>0){
                                setExtParams("trainingImage",mYaLingTop_1Adapter.getData().get(0).getBackgroundUrl());
                                setExtParams("trainingInfo",mYaLingTop_1Adapter.getData().get(0).getIntroduct());
                                setExtParams("trainingName",mYaLingTop_1Adapter.getData().get(0).getName());
                                setExtParams("trainingId",mYaLingTop_1Adapter.getData().get(0).getId());
                                setExtParams("sportType",mYaLingB_1Adapter.getData().get(mYaLingB_1Adapter.getPos()));
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

    private void queryDeviceCourseList(String type) {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceCourseList(null,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceCourseData>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceCourseData> result) {
                        if(isDataInfoSucceed(result)){
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
    List<String> listDw = new ArrayList<>();
    private void initRvUi() {
        listDw.add("kg");
        listDw.add("lb");
        mYaLingTop_1Adapter = new YaLingTop_1Adapter(null);
        mRvList1.setLayoutManager(new LinearLayoutManager(this));
        mRvList1.setHasFixedSize(true);
        mRvList1.setAdapter(mYaLingTop_1Adapter);
        mYaLingTop_1Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override//
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mYaLingTop_1Adapter.setPos(position);
                mYaLingTop_1Adapter.notifyDataSetChanged();
                setExtParams("trainingImage",mYaLingTop_1Adapter.getData().get(position).getBackgroundUrl());
                setExtParams("trainingInfo",mYaLingTop_1Adapter.getData().get(position).getIntroduct());
                setExtParams("trainingName",mYaLingTop_1Adapter.getData().get(position).getName());
                setExtParams("trainingId",mYaLingTop_1Adapter.getData().get(position).getId());
            }
        });
        mYaLingTop_1Adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                initPopupWindow(mYaLingTop_1Adapter.getData().get(position).getGifUrl());
                return false;
            }
        });
        List<String> listS = new ArrayList<>();
        listS.add("自由模式");
        listS.add("即时模式");
        listS.add("定数计时");
        mYaLingB_1Adapter = new YaLingB_1Adapter(listS);
        mRvList2.setLayoutManager(new GridLayoutManager(this, 3));
        mRvList2.setHasFixedSize(true);
        mRvList2.setAdapter(mYaLingB_1Adapter);
        mYaLingB_1Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ll_dingshi.setVisibility(View.GONE);
                ll_dingshijishu.setVisibility(View.GONE);
                if(position==1){
                    ll_dingshi.setVisibility(View.VISIBLE);
                }
                if(position==2){
                    ll_dingshijishu.setVisibility(View.VISIBLE);
                }
                mYaLingB_1Adapter.setPos(position);
                mYaLingB_1Adapter.notifyDataSetChanged();
                setExtParams("sportType",mYaLingB_1Adapter.getData().get(position));
            }
        });
    }

    PopupWindowYaLingSb window;
    private void initPopupWindow(String img) {
        window = new PopupWindowYaLingSb(this, img, new PopupWindowYaLingSb.GiveDialogInterface() {
            @Override
            public void btnConfirm() {
                window.dismiss();
            }
        });

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        window.showAtLocation(mRvList1, Gravity.CENTER, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }
    @Override
    public void onBackPressed() {
        DialogUtils.showUnificationDialog(this, "提示", "您确定要断开当前连接吗？", "确定", true,
                new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        PopupWindowLanYan.mManagerDisconnect();
                        finish();
                    }
                });
    }

    int type = 2;//0 定数计时  1定时计数 2自由模式
    @OnClick({R.id.ll_back,R.id.tv_hour,R.id.tv_min, R.id.tv_ok,R.id.tv_yuyinshezhi,R.id.tv_num,R.id.tv_danwei,R.id.tv_danwei_lb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.tv_yuyinshezhi:
                IntentUtils.getInstence().intent(this,YaLingActivity_sz.class);
                break;
            case R.id.tv_num:
                listTime.clear();
                for(int i=1;i<11;i++){
                    listTime.add((i * 5) +" 次");
                }
                listTime.add("自定义");
                PickerViewUtils.selectorCustom(this, listTime, "运动次数",
                        new PickerViewUtils.ConditionInterfacd() {
                            @Override
                            public void setIndex(int pos) {
                                String num = listTime.get(pos).replace(" 次","");
                                if(num.equals("自定义")){
                                    DialogUtils.showUnificationDialogEditText(YaLingActivity_1.this, "输入次数",
                                            "请输入设定的次数(1-9999)",new DialogUtils.UnificationDialogInterface() {
                                                @Override
                                                public void bntClickListener(String str) {
                                                    int num = Integer.parseInt(str);
                                                    if(num < 1 || num > 9999){
                                                        DialogUtils.showUnificationDialog(YaLingActivity_1.this,
                                                                "提示","请输入规定的次数(1-9999)", "确定",false,null);
                                                        return;
                                                    }
                                                    num_yundong_ci = num;
                                                    tv_num.setText("运动次数("+num+"次)");
                                                }
                                            });
                                    return;
                                }
                                num_yundong_ci = Integer.parseInt(num);
                                tv_num.setText("运动次数("+num+"次)");
                            }
                        });
                break;
            case R.id.tv_hour:
                listTime.clear();
                for(int i=0;i<24;i++){
                    listTime.add(i +" hour");
                }
                PickerViewUtils.selectorCustom(this, listTime, "运动时间(小时)", tv_hour);
                break;
            case R.id.tv_min:
                listTime.clear();//1,2,5,10,20,30,40,50,60,90,120,180
                listTime.add("1 min");
                listTime.add("2 min");
                listTime.add("5 min");
                listTime.add("10 min");
                listTime.add("20 min");
                listTime.add("30 min");
                listTime.add("40 min");
                listTime.add("50 min");
                listTime.add("60 min");
                listTime.add("90 min");
                listTime.add("120 min");
                listTime.add("180 min");
                PickerViewUtils.selectorCustom(this, listTime, "运动时间(分)", tv_min);
                break;
            case R.id.tv_ok:
                String mYalingZl = et_yaling_zl.getText().toString();
                if(StringUtil.isBlank(mYalingZl) || Double.parseDouble(mYalingZl) == 0){
                    ToastUtils.showShort("请输入哑铃的重量");
                    return;
                }
//                isLBUnit = "0";
//                if(tv_danwei.getText().toString().equals("lb")){
//                    isLBUnit = "1";
//                }
                if(isLBUnit.endsWith("0") && Double.parseDouble(mYalingZl)>80){
                    ToastUtils.showShort("哑铃最大80KG");
                    return;
                }
                if(isLBUnit.endsWith("1") && Double.parseDouble(mYalingZl)>180){
                    ToastUtils.showShort("哑铃最大180LB");
                    return;
                }
                setExtParams("isLBUnit",isLBUnit);
                setExtParams("dumbWeight",mYalingZl);
                if(mYaLingB_1Adapter.getPos()==0){
                    type = 2;
                }else if(mYaLingB_1Adapter.getPos()==1){
                    type = 1;
                }else if(mYaLingB_1Adapter.getPos()==2){
                    type = 0;
                }
                if(type==0){
//                    initPopupWindow();
                    setExtParams("targetCount",num_yundong_ci+"");
                    SharedUtils.singleton().put("num_yundong_ci",num_yundong_ci);
                    YaLingActivity_2.intentActivity(YaLingActivity_1.this,type,num_yundong_ci,Double.parseDouble(mYalingZl),isLBUnit, mJSONObject.toString());
                }else if(type==1){
                    String hour = tv_hour.getText().toString().replace(" hour","");
                    String min = tv_min.getText().toString().replace(" min","");
                    int time = (Integer.parseInt(hour)*60 + Integer.parseInt(min)) * 60;//秒
                    if(time<60){
                        ToastUtils.showShort("请选择运动时长");
                        return;
                    }
                    setExtParams("targetTime",time+"");
                    YaLingActivity_2.intentActivity(YaLingActivity_1.this,type,time,Double.parseDouble(mYalingZl), isLBUnit,mJSONObject.toString());
                }else{
                    YaLingActivity_2.intentActivity(YaLingActivity_1.this,type,0,Double.parseDouble(mYalingZl), isLBUnit,mJSONObject.toString());
                }
                break;
            case R.id.tv_danwei:
//                PickerViewUtils.selectorCustom(this, listDw, "重量单位",
//                        new PickerViewUtils.ConditionInterfacd() {
//                            @Override
//                            public void setIndex(int pos) {
//                                tv_danwei.setText(listDw.get(pos));
//                            }
//                        });
                isLBUnit = "0";
                tv_danwei.setBackgroundResource(R.drawable.bj_shape_line_theme_2);
                tv_danwei_lb.setBackgroundResource(R.drawable.bj_shape_line_666_2);
                tv_danwei.setTextColor(getColor(R.color.color_text_theme));
                tv_danwei_lb.setTextColor(getColor(R.color.color_666666));
                break;
            case R.id.tv_danwei_lb:
                isLBUnit = "1";
                tv_danwei.setBackgroundResource(R.drawable.bj_shape_line_666_2);
                tv_danwei_lb.setBackgroundResource(R.drawable.bj_shape_line_theme_2);
                tv_danwei.setTextColor(getColor(R.color.color_666666));
                tv_danwei_lb.setTextColor(getColor(R.color.color_text_theme));
                break;
        }
    }
    public void setExtParams(String k,String v) {
        try {
            mJSONObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//    PopupWindowTy mWindowTy;
//    private void initPopupWindow() {
//        listTime.clear();
//        for(int i=1;i<11;i++){
//            listTime.add((i * 5) +" 次");
//        }
//        listTime.add("自定义");
//        if (mWindowTy == null) {
//            mWindowTy = new PopupWindowTy(this, listTime, new PopupWindowTy.GiveDialogInterface() {
//                @Override
//                public void btnConfirm(int position) {
//                    String num = listTime.get(position);
//                    if(num.equals("自定义")){
//                        return;
//                    }
//                    YaLingActivity_2.intentActivity(YaLingActivity_1.this,type,0);
//                }
//            });
//
//            mWindowTy.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        }
//        mWindowTy.showAtLocation(tv_ok, Gravity.TOP, 0, 0); // 设置layout在PopupWindow中显示的位置
//    }
    public static void intentActivity(Context mContext) {

        mContext.startActivity(new Intent(mContext, YaLingActivity_1.class));
//        mContext.startActivity(new Intent(mContext,FacilityManageActivity.class));
    }

}
