package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.SportLogDetailBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ExerciseRecordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    SportLogDetailBean stats;
    public ExerciseRecordAdapter(@Nullable List<String> data) {
        super(R.layout.item_exercise_record, data);
    }

    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
        if(item.equals("卡路里")){
            helper.setText(R.id.tv_v,stats.getCalories()+"kcal")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_1));
        }else if(item.equals("路程")){
            helper.setText(R.id.tv_v,StringUtil.getValue(stats.getDistance())+"km")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_2));
        }else if(item.equals("时间")){
            helper.setText(R.id.tv_v,StringUtil.getTimeGeShi(Long.valueOf(stats.getDuration())))
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_3));
        }else if(item.equals("平均速度")){
            helper.setText(R.id.tv_v,StringUtil.getValue(stats.getAvgSpeed())+"km/h")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_4));
        }else if(item.equals("平均浆频")){
            helper.setText(R.id.tv_v,StringUtil.getValue(stats.getAvgSpeed())+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_4));
        }else if(item.equals("最大速度")){
            helper.setText(R.id.tv_v,StringUtil.getValue(stats.getMaxSpeed())+"km/h")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_5));
        }else if(item.equals("最大浆频")){
            helper.setText(R.id.tv_v,StringUtil.getValue(stats.getMaxSpeed())+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_5));
        }else if(item.equals("平均心跳")){
            helper.setText(R.id.tv_v,stats.getAvgHeartRate()+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_6));
        }else if(item.equals("功率")){
            helper.setText(R.id.tv_v,StringUtil.getValue(stats.getAvgWatt())+"w")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_7));
        }else if(item.equals("强度")){
            if(stats.getMaxIntensity().equals(stats.getMinIntensity())){
                helper.setText(R.id.tv_v,stats.getMaxIntensity())
                        .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_8));
            }else{
                helper.setText(R.id.tv_v,stats.getMinIntensity()+"-"+stats.getMaxIntensity())
                        .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_8));
            }
        }else if(item.equals("心率区间")){
            helper.setText(R.id.tv_v,stats.getMinHeartRate()+"%-"+stats.getMaxHeartRate()+"%")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_9));
        }
    }

    public void setNewData(List<String> list, SportLogDetailBean stats) {
        this.stats = stats;
        setNewData(list);
    }
}
