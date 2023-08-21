package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AAChartView;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.jxkj.fit_5a.AAChartCoreLib.AAChartEnum.AAChartType;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceCourseData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class CourseSelectionAdapter extends BaseQuickAdapter<DeviceCourseData.ListBean, BaseViewHolder> {
    public CourseSelectionAdapter(@Nullable List<DeviceCourseData.ListBean> data) {
        super(R.layout.item_course_selection, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceCourseData.ListBean item) {
        helper.setText(R.id.tv_name, item.getName()).setText(R.id.tv_sub_title, item.getIntroduct());
        AAChartView mAAChartView = helper.getView(R.id.AAChartView);
        AAChartView AAChartView1 = helper.getView(R.id.AAChartView1);

        mAAChartView.setOnClick(false);
        AAChartView1.setOnClick(false);
        AAChartView1.setIsClearBackgroundColor(true);

//        AAChartModel aaChartModel = configureChartModel();
//        if (aaOptions == null) {
//            aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
//        }


        List<DeviceCourseData.ListBean.DetailsBean.ResistancesBean> resistances;
        if (item.getDetails() != null && item.getDetails().getResistances() != null) {
            resistances = item.getDetails().getResistances();
            Byte[] data = new Byte[10];
            for (int i = 0; i < resistances.size(); i++) {
                data[i] = Byte.valueOf(resistances.get(i).getValue());
            }
            mAAChartView.aa_drawChartWithChartModel(configureColorfulColumnChart(data, AAChartType.Column, "#FFD275"));
            AAChartView1.aa_drawChartWithChartModel(configureColorfulColumnChart(data, AAChartType.Spline, "#FF9933"));
        }

    }

    AAChartModel configureColorfulColumnChart(Byte[] data, String type, String color) {
        return new AAChartModel()
                .chartType(type)
                .title("")
                .subtitle("")
                .xAxisVisible(false)
                .yAxisVisible(false)
                .xAxisLabelsEnabled(false)
                .yAxisLabelsEnabled(false)
                .legendEnabled(false)
                .yAxisMax(10f)
                .yAxisGridLineWidth(0f)
                .markerRadius(0f)
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("")
                                .lineWidth(2f)
                                .color(color)
                                .data(data)
                });
    }
}
