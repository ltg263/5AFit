package com.jxkj.fit_5a.view.activity.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.adapter.FacilityManageAdapter;
import com.zkk.view.rulerview.RulerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RateControlActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_lsydsb_list)
    RecyclerView mRvLsydsbList;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.tv_jl)
    TextView mTvJl;
    @BindView(R.id.ruler_weight)
    RulerView mRulerWeight;
    @BindView(R.id.tv_tz)
    TextView mTvTz;
    @BindView(R.id.tv_xlzb)
    TextView mTvXlzb;
    @BindView(R.id.tv_text_top)
    TextView tv_text_top;
    @BindView(R.id.tv_ydsj)
    TextView mTvYdsj;
    @BindView(R.id.tv_text)
    TextView mTvText;
    int maxV = 220;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    private FacilityManageAdapter mFacilityManageAdapter;
    private List<TemplateBean.ListBean> textList = new ArrayList<>();
    private List<BpmDataBean> mBpmDataBeans = new ArrayList<>();
    String movingType = "";
    int age = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_AGE,"0"));
    int bfb5,bfb6,bfb7,bfb8,bfb9,bfb;
    @Override
    protected int getContentView() {
        return R.layout.activity_rate_control;
    }

    @Override
    protected void initViews() {
        getTemplateList();
        mTvTitle.setText("????????????");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
//        mTvRighttext.setText("??????");

        listTimeS.clear();
        listTimeS.add("0h");
        listTimeS.add("1h");
        listTimeS.add("2h");
        listTimeS.add("3h");
        listTimeS.add("4h");
        listTimeS.add("5h");
        listTimeS.add("6h");
        listTimeF.clear();
        listTimeF.add("0min");
        listTimeF.add("5min");
        listTimeF.add("10min");
        listTimeF.add("15min");
        listTimeF.add("20min");
        listTimeF.add("25min");
        listTimeF.add("30min");
        listTimeF.add("35min");
        listTimeF.add("40min");
        listTimeF.add("45min");
        listTimeF.add("50min");
        listTimeF.add("55min");


        bfb5 = (int) ((maxV-age)*0.5);
        bfb6 = (int) ((maxV-age)*0.6);
        bfb7 = (int) ((maxV-age)*0.7);
        bfb8 = (int) ((maxV-age)*0.8);
        bfb9 = (int) ((maxV-age)*0.9);
        bfb  = maxV-age;
        initBpmData();
        initRvUi();
    }

    private void initBpmData() {
        mBpmDataBeans.add(new BpmDataBean("???????????????(0~50%)",0,bfb5,0));
        mBpmDataBeans.add(new BpmDataBean("??????????????????(50~60%)",bfb5,bfb6,0));
        mBpmDataBeans.add(new BpmDataBean("??????????????????(60~70%)",bfb6,bfb7,0));
        mBpmDataBeans.add(new BpmDataBean("????????????????????????(70~80%)",bfb7,bfb8,0));
        mBpmDataBeans.add(new BpmDataBean("????????????????????????(80~90%)",bfb8,bfb9,0));
        mBpmDataBeans.add(new BpmDataBean("??????????????????(90~100%)",bfb9,bfb,0));

        setMovingTye(60);
    }

    private void initRvUi() {
        String str = "???, ???????????????<font color=\"#000000\"><big><big>"+age+"</big></big></font>" +
                "?????????????????????<font color=\"#000000\"><big><big>"+bfb+"</big></big></font>???/min";
        mTvXlzb.setText(Html.fromHtml(str));
        //0h0m?????????????????????
        //(220-11)*131
        String str1 = "<font color=\"#000000\"><big><big>0</big></big></font>" +
                "h<font color=\"#000000\"><big><big>0</big></ /font>m?????????????????????";
        mTvYdsj.setText(Html.fromHtml(str1));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");

        mFacilityManageAdapter = new FacilityManageAdapter(list);

        mRvLsydsbList.setLayoutManager(new LinearLayoutManager(this));
        mRvLsydsbList.setHasFixedSize(true);
        mRvLsydsbList.setAdapter(mFacilityManageAdapter);
        //187
        mRulerWeight.setOnValueChangeListener(value -> {
            setMovingTye((int) Math.ceil(value));
            double ab = value / maxV;
            for (int i = 0; i < textList.size(); i++) {
                TemplateBean.ListBean data = textList.get(i);
                double start = data.getStartInterval();
                double end = data.getEndInterval();
                if (ab >= start && ab <= end) {
                    String str0 = "<font color=\"" + data.getParams().get(0).getColor() + "\">" + data.getParams().get(0).getValue() + "</font>"
                            + (data.getContent().replace("${str}", ""));
                    mTvText.setText(Html.fromHtml(str0));
                }
            }
        });
        /**
         *
         * @param selectorValue ???????????? ???????????? ???????????????????????????????????????????????????
         * @param minValue   ????????????
         * @param maxValue   ???????????????
         * @param per   ????????????  ??? 1:?????? ???2???????????????1.   0.1:?????? ???2???????????????0.1 ???demo??? ??????mPerValue???1  ??????mPerValue ???0.1
         */
        mRulerWeight.setValue(60, 40, 220, 1);
    }

    private void setMovingTye(int value) {
        mTvTz.setText(value+ "");
        if(value>0 && value<bfb5){
            movingType = "???????????????(0~50%)(0bpm~"+bfb5+"bpm)";
            tv_text_top.setText("???????????????(0~50%)(0???/??????~"+bfb5+"???/??????)");
        }else if(value>bfb5 && value<bfb6){
            movingType = "??????????????????(50~60%)("+bfb5+"bpm~"+bfb6+"bpm)";
            tv_text_top.setText("???????????????(50~60%)("+bfb5+"???/??????~"+bfb6+"???/??????)");
        }else if(value>bfb6 && value<bfb7){
            movingType = "??????????????????(60~70%)("+bfb6+"bpm~"+bfb7+"bpm)";
            tv_text_top.setText("???????????????(60~70%)("+bfb6+"???/??????~"+bfb7+"???/??????)");
        }else if(value>bfb7 && value<bfb8){
            movingType = "????????????????????????(70~80%)("+bfb7+"bpm~"+bfb8+"bpm)";
            tv_text_top.setText("???????????????(70~80%)("+bfb7+"???/??????~"+bfb8+"???/??????)");
        }else if(value>bfb8 && value<bfb9){
            movingType = "????????????????????????(80~90%)("+bfb8+"bpm~"+bfb9+"bpm)";
            tv_text_top.setText("???????????????(80~90%)("+bfb8+"???/??????~"+bfb9+"???/??????)");
        }else if(value>bfb9){
            movingType = "??????????????????(90~100%)("+bfb9+"bpm~"+bfb+"bpm)";
            tv_text_top.setText("???????????????(90~100%)("+bfb9+"???/??????~"+bfb+"???/??????)");
        }
    }

    List<String> listTimeS = new ArrayList<>();
    List<String> listTimeF = new ArrayList<>();

    @OnClick({R.id.ll_back,R.id.tv_righttext, R.id.iv_rightimg, R.id.tv_ok, R.id.rl_1, R.id.rl_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
            case R.id.iv_rightimg:
                FacilityAddSbActivity.intentActivity(this);
                break;
            case R.id.tv_ok:
                if(PopupWindowLanYan.ble4Util!=null && PopupWindowLanYan.ble4Util.isConnect()){
                    Intent mIntent = new Intent(this, RatePatternActivity.class);
                    mIntent.putExtra("movingType",movingType);
                    int ZTimeOK =  Integer.parseInt(mTvSj.getText().toString())*60*60+Integer.parseInt(mTvJl.getText().toString())*60;
                    mIntent.putExtra("movingType",movingType);
                    mIntent.putExtra("ZTimeOK",ZTimeOK);
                    mIntent.putParcelableArrayListExtra("mBpmDataBeans", (ArrayList<? extends Parcelable>) mBpmDataBeans);
                    startActivity(mIntent);
                    finish();
                }
                break;
            case R.id.rl_1:
            case R.id.rl_2:
                PickerViewUtils.selectorCustomSf(this, listTimeS,listTimeF, "?????????", mTvSj,mTvJl);
                break;
        }
    }

    public static void intentActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext, RateControlActivity.class));
    }

    private void getTemplateList() {
        RetrofitUtil.getInstance().apiService()
                .getTemplateList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TemplateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TemplateBean> result) {
                        if (isDataInfoSucceed(result)) {
                            textList.addAll(result.getData().getList());
                            double ab = 120.0 / maxV;
                            for (int i = 0; i < textList.size(); i++) {
                                TemplateBean.ListBean data = textList.get(i);
                                double start = data.getStartInterval();
                                double end = data.getEndInterval();
                                if (ab >= start && ab <= end) {
                                    String str0 = "<font color=\"" + data.getParams().get(0).getColor() + "\">"
                                            + data.getParams().get(0).getValue() + "</font>"
                                            + (data.getContent().replace("${str}", ""));
                                    mTvText.setText(Html.fromHtml(str0));
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
}
