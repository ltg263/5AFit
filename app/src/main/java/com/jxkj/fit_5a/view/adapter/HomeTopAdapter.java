package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.SportLogStatsBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    SportLogStatsBean.StatsBean stats;
    public HomeTopAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_top, data);
    }
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
        if(item.equals("总卡路里")){
            helper.setText(R.id.tv_v,stats.getTotalCalories()+"kcal")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_1))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }else if(item.equals("平均卡路里")){
            helper.setText(R.id.tv_v,stats.getAvgCalories()+"kcal")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_1))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }else if(item.equals("总里程")){
            helper.setText(R.id.tv_v,stats.getTotalDistance()+"km")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_2))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_1091e7);
        }else if(item.equals("总计时间")){
            helper.setText(R.id.tv_v, StringUtil.getTimeGeShiYw(stats.getTotalDuration()))
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fdf7e5);
        }else if(item.equals("平均时间")){
            helper.setText(R.id.tv_v, StringUtil.getTimeGeShiYw(stats.getAvgDuration()))
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }else if(item.equals("BAI")){
            helper.setText(R.id.tv_v,stats.getBai()+"BAI")
                    .setImageDrawable(R.id.iv_icon,mContext.getResources().getDrawable(R.drawable.ic_home_top_4))
                    .setBackgroundRes(R.id.iv_icon,R.drawable.bj_circle_fde5e5);
        }
    }

    public void setNewData(List<String> list, SportLogStatsBean.StatsBean stats) {
        this.stats = stats;
        setNewData(list);
    }
}
