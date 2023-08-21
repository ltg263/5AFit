package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MapFinishDataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    BpmDataBean.BpmTopData stats;
    public MapFinishDataAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_top, data);
    }
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
        if(item.equals("卡路里")){
            helper.setText(R.id.tv_v,stats.getCalories()+"kcal")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_1))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("运动里程")){
            helper.setText(R.id.tv_v,stats.getDistance()+"km")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_2))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("运动时间")){
            helper.setText(R.id.tv_v,StringUtil.getTimeGeShi(Long.parseLong(stats.getDuration())))
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_3))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("功率")){
            helper.setText(R.id.tv_v, stats.getWatt()+"W")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_7))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("段位")){
            helper.setText(R.id.tv_v, stats.getLoad_dx())
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_8))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("平均速度")){
            helper.setText(R.id.tv_v,stats.getPjDuration()+"km/h")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("最快速度")){
            helper.setText(R.id.tv_v,stats.getMaxSpeed()+"km/h")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("平均心跳")){
            helper.setText(R.id.tv_v,stats.getHeartRate()+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_9))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("心率区间")){
            helper.setText(R.id.tv_v,stats.getHeartRate_qj()+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_9))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("BAI")){
            helper.setText(R.id.tv_v,stats.getBai()+"BAI")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_10))
                    .setBackgroundRes(R.id.iv_icon,0);
        }else if(item.equals("平均浆频")){
            double v = Double.parseDouble(stats.getPjDuration());
            helper.setText(R.id.tv_v,((int)v)+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("最快浆频")){
            double v = Double.parseDouble(stats.getMaxSpeed());
            helper.setText(R.id.tv_v,((int)v)+"次/分钟")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.mipmap.ic_ydxq_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }
    }

    public void setNewData(List<String> list,BpmDataBean.BpmTopData stats) {
        this.stats = stats;
        setNewData(list);
    }
}
