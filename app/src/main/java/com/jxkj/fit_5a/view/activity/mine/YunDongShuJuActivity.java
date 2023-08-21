package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AATooltip;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.AdminInspireBean;
import com.jxkj.fit_5a.entity.AnnouncementList;
import com.jxkj.fit_5a.entity.SportLogStatsBean;
import com.jxkj.fit_5a.view.adapter.HomeCnxhMainAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTopAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiaosu.view.text.DataSetAdapter;
import com.xiaosu.view.text.VerticalRollingTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YunDongShuJuActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.m_CusSeekBar)
    SeekBar mSeekBar;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_top_jyz)
    TextView mTvTopJyz;
    @BindView(R.id.tv_title_jl)
    TextView tv_title;
    @BindView(R.id.iv_top)
    ImageView iv_top;
    @BindView(R.id.tv_top_jyy)
    TextView mTvTopJyy;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_cnxh_list)
    RecyclerView mRvRmhdList;
    @BindView(R.id.iv_y)
    ImageView mIvY;
    @BindView(R.id.iv_z)
    ImageView mIvZ;
    @BindView(R.id.tvPop)
    TextView tvPop;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private HomeTopAdapter mHomeTopAdapter;
    private HomeCnxhMainAdapter mHomeCnxhMainAdapter;
    private AAChartModel aaChartModel;
    private boolean isDay7 = true;
    String[] str7, str30;

    @Override
    protected int getContentView() {
        return R.layout.activity_yun_dong_shuju;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("运动数据");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mIvBack.setOnClickListener(v -> finish());
        str7 = StringUtil.getDays(7, "dd").toArray(new String[7]);
        str30 = StringUtil.getDays(StringUtil.getMonthLastDay(), "dd").toArray(new String[StringUtil.getMonthLastDay()]);
        initRvUi();
        getSportLogStats(null);
        getAdminInspire();
        mRlActionbar.setAlpha(1);
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getSportLogStats(null);
                getAdminInspire();
            }
        });
    }

    private void initRvUi() {
        mHomeTopAdapter = new HomeTopAdapter(null);
        LinearLayoutManager ms = new LinearLayoutManager(YunDongShuJuActivity.this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(ms);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTopAdapter);

        mHomeTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(FacilityAddPpActivity.this, FacilityAddPpActivity.class));
            }
        });

        mHomeCnxhMainAdapter = new HomeCnxhMainAdapter(null);
        mRvRmhdList.setAdapter(mHomeCnxhMainAdapter);
        mHomeCnxhMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    /**
     * 填充数据
     */
    private void initData(int bai) {
        mSeekBar.setEnabled(false);
        mSeekBar.setMax(180);
        mSeekBar.setProgress(bai);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                YunDongShuJuActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        aabbcvc(bai);
                    }
                });
            }
        }).start();
    }

    private void aabbcvc(int progress) {
        Log.w("aabbcvc","progress:"+progress);
        //设置文本显示
        tvPop.setText(String.valueOf(progress));

        //获取文本宽度
        float textWidth = tvPop.getWidth();

        //获取seekbar最左端的x位置
        float left = mSeekBar.getLeft();

        //进度条的刻度值
        float max =Math.abs(mSeekBar.getMax());

        //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
        float thumb = dip2px(YunDongShuJuActivity.this,15);

        //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
        float average = (((float) mSeekBar.getWidth())-2*thumb)/max;

        //int to float
        float currentProgress = progress;
        if(progress>180){
            currentProgress = 180;
        }

        //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
        float pox = left - textWidth/2 +thumb + average * currentProgress;
        tvPop.setX(pox);
    }

    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void getAdminInspire() {
        RetrofitUtil.getInstance().apiService()
                .getAdminInspire()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdminInspireBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdminInspireBean> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()!=null && result.getData()!=null){
                                tv_title.setText(result.getData().getTitle());
                                if(YunDongShuJuActivity.this!=null){
                                    Glide.with(YunDongShuJuActivity.this).load(result.getData().getBackImg()).into(iv_top);
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


    @OnClick({ R.id.tv_top_jyz,R.id.tv_top_jyy, })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_jyz:
                mIvY.setVisibility(View.INVISIBLE);
                mIvZ.setVisibility(View.VISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.black));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.color_666666));
                isDay7 = true;
                getSportLogStats(null);
                break;
            case R.id.tv_top_jyy:
                mIvY.setVisibility(View.VISIBLE);
                mIvZ.setVisibility(View.INVISIBLE);
                mTvTopJyz.setTextColor(getResources().getColor(R.color.color_666666));
                mTvTopJyy.setTextColor(getResources().getColor(R.color.black));
                isDay7 = false;
                getSportLogStats(null);
                break;
        }
    }
    private void getSportLogStats(String deviceType) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        if (isDay7) {
            calendar.add(Calendar.DAY_OF_YEAR, -7);
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, -StringUtil.getMonthLastDay());
        }

        RetrofitUtil.getInstance().apiService()
                .getSportLogStats(String.valueOf(calendar.getTime().getTime()), String.valueOf(System.currentTimeMillis()), deviceType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogStatsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogStatsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<SportLogStatsBean.ListBean> lists = result.getData().getList();
                            initAAChar(lists);
                            List<String> list = new ArrayList<>();
                            list.add("总卡路里");
                            list.add("总里程");
                            list.add("总计时间");
                            list.add("平均时间");
//                            list.add("BAI");
                            mHomeTopAdapter.setNewData(list, result.getData().getStats());
                            initData((int) result.getData().getStats().getBai());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                    }
                });

    }

    private AAOptions aaOptions;

    private void initAAChar(List<SportLogStatsBean.ListBean> lists) {
        AAChartModel aaChartModel = configureChartModel(lists);
//        if (aaOptions == null) {
        AATooltip aaTooltip = new AATooltip()
                .useHTML(true)
                .formatter("function () {\n" +
                        "function getDay(day){\n" +
                        "    var today = new Date();\n" +
                        "    var targetday_milliseconds=today.getTime() + 1000*60*60*24;\n" +
                        "    today.setTime(targetday_milliseconds);\n" +
                        "    var tYear = today.getFullYear();\n" +
                        "    var tMonth = today.getMonth();\n" +
                        "    tMonth = doHandleMonth(tMonth + 1);\n" +
                        "     if(new Date().getTime() < new Date(tYear+\"-\"+tMonth+\"-\"+day).getTime()){\n" +
                        "        tMonth = doHandleMonth(today.getMonth());\n" +
                        "     }\n" +
                        "    day = doHandleMonth(day);\n" +
                        "    return tYear+\"-\"+tMonth+\"-\"+day;\n" +
                        "}\n" +
                        "function doHandleMonth(month){\n" +
                        "    var m = month;\n" +
                        "    if(month.toString().length == 1){\n" +
                        "     m = \"0\" + month;\n" +
                        "    }\n" +
                        "    return m;\n" +
                        "}" +
                        "        var h = Math.floor(this.points[0].y / 3600) < 10 ? '0'+Math.floor(this.points[0].y / 3600) : Math.floor(this.points[0].y / 3600);\n" +
                        "        var m = Math.floor((this.points[0].y / 60 % 60)) < 10 ? '0' + Math.floor((this.points[0].y / 60 % 60)) : Math.floor((this.points[0].y / 60 % 60));\n" +
                        "        var s = Math.floor((this.points[0].y % 60)) < 10 ? '0' + Math.floor((this.points[0].y % 60)) : Math.floor((this.points[0].y % 60));\n" +
                        "        var str =  '';\n" +
                        "        if(h == \"00\"){\n" +
                        "            str = m + '分' + s +'秒';\n" +
                        "        }else{" +
                        "            str = h + '时' + m + '分' + s +'秒';\n" +
                        "        }\n" +
                        "        str = this.points[0].y+'秒';\n" +
                        "        str = str.replace('.','分');\n"+
                        "        var s0 = '' + '<b>' +  getDay(this.x) + '</b>' + '<br/>';\n" +
                        "        var colorDot1 = '<span style=\\\"' + 'color:#FFB300; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var colorDot2 = '<span style=\\\"' + 'color:#FFA1A1; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var colorDot3 = '<span style=\\\"' + 'color:#A1DFFF; font-size:13px\\\"' + '>◉</span> ';\n" +
                        "        var s1 = colorDot1 + this.points[0].series.name + ': ' + str + '<br/>';\n" +
                        "        var s2 = colorDot2 + this.points[1].series.name + ': ' + this.points[1].y + 'kcal' + '<br/>';\n" +
                        "        var s3 = colorDot3 + this.points[2].series.name + ': ' + this.points[2].y + 'km';\n" +
                        "        s0 += (s1 + s2+ s3);\n" +
                        "        return s0;\n" +
                        "    }");
        aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        aaOptions.tooltip(aaTooltip);
//        }
        mAAChartView.aa_drawChartWithChartOptions(aaOptions);
    }

    private AAChartModel configureChartModel(List<SportLogStatsBean.ListBean> lists) {
        String[] str;
        if (!isDay7) {
            str = str30;
            aaChartModel = new AAChartModel()
                    .chartType(AAChartType.Areaspline)
                    .title("")
                    .yAxisTitle("")
                    .yAxisLabelsEnabled(false)
                    .categories(str)
                    .yAxisGridLineWidth(0f)
                    .xAxisGridLineWidth(0f)
                    .legendEnabled(false)
                    .xAxisReversed(true)
                    .markerSymbolStyle(AAChartSymbolStyleType.InnerBlank)
                    .gradientColorEnable(true)
                    .markerRadius(0f)
                    .markerSymbol(AAChartSymbolType.Circle)
                    .scrollablePlotArea(
                            new AAScrollablePlotArea()
                                    .minWidth(str.length*30)
                                    .scrollPositionX(0f)
                    )
                    .series(configureTheStyleForDifferentTypeChart(lists));
        } else {
            str = str7;
            aaChartModel = new AAChartModel()
                    .chartType(AAChartType.Areaspline)
                    .title("")
                    .yAxisTitle("")
                    .yAxisLabelsEnabled(false)
                    .categories(str)
                    .yAxisGridLineWidth(0f)
                    .xAxisGridLineWidth(0f)
                    .legendEnabled(false)
                    .xAxisReversed(true)
                    .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)
                    .gradientColorEnable(true)
                    .markerRadius(0f)
                    .markerSymbol(AAChartSymbolType.Circle)
                    .scrollablePlotArea(
                            new AAScrollablePlotArea()
                                    .scrollPositionX(1f)
                    )
                    .series(configureTheStyleForDifferentTypeChart(lists));
        }
        return aaChartModel;
    }

    private AASeriesElement[] configureTheStyleForDifferentTypeChart(List<SportLogStatsBean.ListBean> lists) {
        ArrayList<String> a = StringUtil.getDays(7, "yyyyMMdd");
        if (!isDay7) {
            a.clear();
            a = StringUtil.getDays(StringUtil.getMonthLastDay(), "yyyyMMdd");
        }
        Object[] ydsc = new Object[a.size()];
        Object[] kll = new Object[a.size()];
        Object[] zlc = new Object[a.size()];
        for (int i = 0; i < a.size(); i++) {
            if (lists == null || lists.size() == 0) {
                ydsc[i] = 0;
                kll[i] = 0;
                zlc[i] = 0;
                continue;
            }
            for (int j = 0; j < lists.size(); j++) {
                ydsc[i] = 0;
                kll[i] = 0;
                zlc[i] = 0;
                if (a.get(i).equals(lists.get(j).getDatestr())) {
                    ydsc[i] = StringUtil.getTimeFenMiao(lists.get(j).getTotalDuration());
                    kll[i] = lists.get(j).getTotalCalories();
                    zlc[i] = lists.get(j).getTotalDistance();
                    break;
                }

            }
        }
        AASeriesElement element1 = new AASeriesElement()
                .name("运动时长")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#FFB300")
                .data(ydsc);

        AASeriesElement element2 = new AASeriesElement()
                .name("卡路里")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#FFA1A1")
                .data(kll);

        AASeriesElement element3 = new AASeriesElement()
                .name("总里程")
                .lineWidth(1f)
                .fillOpacity(0.5f)
                .color("#A1DFFF")
                .data(zlc);

        return new AASeriesElement[]{element1, element2, element3};
    }
}



