package com.jxkj.fit_5a.view.activity.exercise;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AADataLabels;
import com.jxkj.fit_5a.AAChartCoreLib.AAOptionsModel.AAPie;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseFinishActivity;
import com.jxkj.fit_5a.view.adapter.TaskFinishListAdapter;
import com.jxkj.fit_5a.view.adapter.TaskFinishPjAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TaskFinishActivity extends BaseActivity {
    @BindView(R.id.mNsv)
    NestedScrollView relativeLayout;
    @BindView(R.id.AAChartView)
    AAChartView mAAChartView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_xl)
    RecyclerView mRvListXl;
    @BindView(R.id.ll_txt)
    LinearLayout mLlTet;
    @BindView(R.id.tv_xz)
    TextView mTvXz;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_5)
    TextView mTv5;
    @BindView(R.id.tv_6)
    TextView mTv6;
    @BindView(R.id.tv_ztime)
    TextView tv_ztime;
    @BindView(R.id.vertical_progressbar1)
    ProgressBar mVerticalProgressbar1;
    @BindView(R.id.vertical_progressbar2)
    ProgressBar mVerticalProgressbar2;
    @BindView(R.id.vertical_progressbar3)
    ProgressBar mVerticalProgressbar3;
    @BindView(R.id.vertical_progressbar4)
    ProgressBar mVerticalProgressbar4;
    @BindView(R.id.vertical_progressbar5)
    ProgressBar mVerticalProgressbar5;
    @BindView(R.id.vertical_progressbar6)
    ProgressBar mVerticalProgressbar6;
    @BindView(R.id.tv_bfb1)
    TextView mTvBfb1;
    @BindView(R.id.tv_sj1)
    TextView mTvSj1;
    @BindView(R.id.tv_bfbb1)
    TextView mTvBfbb1;
    @BindView(R.id.tv_bfb2)
    TextView mTvBfb2;
    @BindView(R.id.tv_sj2)
    TextView mTvSj2;
    @BindView(R.id.tv_bfbb2)
    TextView mTvBfbb2;
    @BindView(R.id.tv_bfb3)
    TextView mTvBfb3;
    @BindView(R.id.tv_sj3)
    TextView mTvSj3;
    @BindView(R.id.tv_bfbb3)
    TextView mTvBfbb3;
    @BindView(R.id.tv_bfb4)
    TextView mTvBfb4;
    @BindView(R.id.tv_sj4)
    TextView mTvSj4;
    @BindView(R.id.tv_bfbb4)
    TextView mTvBfbb4;
    @BindView(R.id.tv_bfb5)
    TextView mTvBfb5;
    @BindView(R.id.tv_sj5)
    TextView mTvSj5;
    @BindView(R.id.tv_bfbb5)
    TextView mTvBfbb5;
    @BindView(R.id.tv_bfb6)
    TextView mTvBfb6;
    @BindView(R.id.tv_sj6)
    TextView mTvSj6;
    @BindView(R.id.tv_bfbb6)
    TextView mTvBfbb6;
    private AAChartModel aaChartModel;
    private ArrayList<BpmDataBean> mBpmDataBeans;

    @Override
    protected int getContentView() {
        return R.layout.activity_task_finish;
    }

    @Override
    protected void initViews() {
        mBpmDataBeans = getIntent().getParcelableArrayListExtra("mBpmDataBeans");
        initTopData();
        initRv();
        aaChartModel = configurePieChart();
        mAAChartView.aa_drawChartWithChartModel(aaChartModel);
    }

    int Ztime = 0;

    private void initTopData() {
        BpmDataBean.BpmTopData mBpmTopData = mBpmDataBeans.get(0).getBpmTopData();
        Ztime = Integer.parseInt(mBpmTopData.getDuration());
        mTv1.setText(mBpmTopData.getCalories() + "kcal");
        mTv2.setText(mBpmTopData.getDistance() + "km");
        mTv3.setText(StringUtil.getTimeGeShi(Ztime));
        mTv4.setText(mBpmTopData.getPjDuration() + "km/h");
        mTv5.setText(mBpmTopData.getMaxSpeed() + "km/h");
        mTv6.setText(mBpmTopData.getHeartRate() + "次/分");
        tv_ztime.setText("总时间：" + StringUtil.getTimeGeShi(Ztime));
        DecimalFormat df = new DecimalFormat("######0.00");
        mVerticalProgressbar1.setProgress((int) (Double.parseDouble(df.format(mBpmDataBeans.get(0).getTime() / Ztime)) * 100));
        mVerticalProgressbar2.setProgress((int) (Double.parseDouble(df.format(mBpmDataBeans.get(1).getTime() / Ztime)) * 100));
        mVerticalProgressbar3.setProgress((int) (Double.parseDouble(df.format(mBpmDataBeans.get(3).getTime() / Ztime)) * 100));
        mVerticalProgressbar4.setProgress((int) (Double.parseDouble(df.format(mBpmDataBeans.get(4).getTime() / Ztime)) * 100));
        mVerticalProgressbar5.setProgress((int) (Double.parseDouble(df.format(mBpmDataBeans.get(5).getTime() / Ztime)) * 100));
        mVerticalProgressbar6.setProgress((int) (Double.parseDouble(df.format(mBpmDataBeans.get(5).getTime() / Ztime)) * 100));

        mTvBfb1.setText((int) (Double.parseDouble(df.format(mBpmDataBeans.get(0).getTime() / Ztime)) * 100)+"%");
        mTvBfb2.setText((int) (Double.parseDouble(df.format(mBpmDataBeans.get(1).getTime() / Ztime)) * 100)+"%");
        mTvBfb3.setText((int) (Double.parseDouble(df.format(mBpmDataBeans.get(2).getTime() / Ztime)) * 100)+"%");
        mTvBfb4.setText((int) (Double.parseDouble(df.format(mBpmDataBeans.get(3).getTime() / Ztime)) * 100)+"%");
        mTvBfb5.setText((int) (Double.parseDouble(df.format(mBpmDataBeans.get(4).getTime() / Ztime)) * 100)+"%");
        mTvBfb6.setText((int) (Double.parseDouble(df.format(mBpmDataBeans.get(5).getTime() / Ztime)) * 100)+"%");

        mTvSj1.setText(StringUtil.getTimeGeShi(mBpmDataBeans.get(0).getTime()));
        mTvSj2.setText(StringUtil.getTimeGeShi(mBpmDataBeans.get(1).getTime()));
        mTvSj3.setText(StringUtil.getTimeGeShi(mBpmDataBeans.get(2).getTime()));
        mTvSj4.setText(StringUtil.getTimeGeShi(mBpmDataBeans.get(3).getTime()));
        mTvSj5.setText(StringUtil.getTimeGeShi(mBpmDataBeans.get(4).getTime()));
        mTvSj6.setText(StringUtil.getTimeGeShi(mBpmDataBeans.get(5).getTime()));

        mTvBfbb1.setText("0~50%");
        mTvBfbb2.setText("50~60%");
        mTvBfbb3.setText("60~70%");
        mTvBfbb4.setText("70~80%");
        mTvBfbb5.setText("80~90%");
        mTvBfbb6.setText("90~100%");
    }

    AAChartModel configurePieChart() {
        return new AAChartModel()
                .chartType(AAChartType.Pie)
                .title("")
                .yAxisTitle("")
                .backgroundColor("#ffffff")
                .dataLabelsEnabled(true)//是否直接显示扇形图数据
                .legendEnabled(false)
                .series(new AAPie[]{
                                new AAPie()
                                        .name("心率分析")
                                        .innerSize("20%")
                                        .size(150f)
                                        .dataLabels(new AADataLabels()
                                                .enabled(true)
                                                .useHTML(true)
                                                .distance(5f)
                                                .format("<b>{point.name}</b>: <br> {point.percentage:.1f} %"))
                                        .data(new Object[][]{
                                        {mBpmDataBeans.get(0).getName(), mBpmDataBeans.get(0).getTime()},
                                        {mBpmDataBeans.get(1).getName(), mBpmDataBeans.get(1).getTime()},
                                        {mBpmDataBeans.get(2).getName(), mBpmDataBeans.get(2).getTime()},
                                        {mBpmDataBeans.get(3).getName(), mBpmDataBeans.get(3).getTime()},
                                        {mBpmDataBeans.get(4).getName(), mBpmDataBeans.get(4).getTime()},
                                        {mBpmDataBeans.get(5).getName(), mBpmDataBeans.get(5).getTime()},
                                })
                                ,
                        }
                );
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("没有感觉");
        list.add("非常\n" + "非常弱");
        list.add("非常弱");
        list.add("弱");
        list.add("适度");
        list.add("有些强");
        list.add("强");
        list.add("非常强");
        list.add("非常\n非常强");

        TaskFinishPjAdapter mTaskFinishPjAdapter = new TaskFinishPjAdapter(list);
        mTaskFinishPjAdapter.setSelectPos(0);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTaskFinishPjAdapter);

        mTaskFinishPjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTaskFinishPjAdapter.setSelectPos(position);
                mTaskFinishPjAdapter.notifyDataSetChanged();

            }
        });


        TaskFinishListAdapter mTaskFinishListAdapter = new TaskFinishListAdapter(mBpmDataBeans, Ztime);
        mRvListXl.setLayoutManager(new LinearLayoutManager(this));
        mRvListXl.setHasFixedSize(true);
        mRvListXl.setAdapter(mTaskFinishListAdapter);

        mTaskFinishListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }


    @OnClick({R.id.iv_back, R.id.tv_go_ffhd, R.id.tv_go_xxyx, R.id.tv_xz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_go_xxyx:
                finish();
                break;
            case R.id.tv_go_ffhd:
                getImgPath(new Aba() {
                    @Override
                    public void ok() {
                        MapExerciseFinishActivity.shareData(TaskFinishActivity.this, "5Afit_img_1.jpg");
                    }
                });
                break;
            case R.id.tv_xz:
                String strXz = mTvXz.getText().toString();
                if (strXz.equals("条形图")) {
                    mTvXz.setText("饼状图");
                    mAAChartView.setVisibility(View.VISIBLE);
                    mLlTet.setVisibility(View.GONE);
                } else {
                    mTvXz.setText("条形图");
                    mAAChartView.setVisibility(View.GONE);
                    mLlTet.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private Handler mHandler = new Handler();

    public String getImgPath(Aba aba) {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = getBitmapByView(relativeLayout); // 获取图片
                MapExerciseFinishActivity.savePicture(bmp, "5Afit_img_1.jpg");// 保存图片
                if (bmp != null) {
                    aba.ok();
                } else {
                    ToastUtils.showShort("图片生成失败");
                }
            }
        }, 100);

        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public interface Aba {
        void ok();
    }

    /**
     * 截取scrollview的屏幕
     *
     * @param scrollView
     * @return
     */
    public Bitmap getBitmapByView(NestedScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();//获取scrollView的屏幕高度
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);//把创建的bitmap放到画布中去
        scrollView.draw(canvas);//绘制canvas 画布
        return bitmap;
    }
}
