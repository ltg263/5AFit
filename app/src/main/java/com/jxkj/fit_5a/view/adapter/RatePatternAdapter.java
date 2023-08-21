package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.RatePatternBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class RatePatternAdapter extends BaseQuickAdapter<RatePatternBean, BaseViewHolder> {
    public RatePatternAdapter(@Nullable List<RatePatternBean> data) {
        super(R.layout.item_rate_pattern, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RatePatternBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_v,item.getV());
        if(item.getName().equals("卡路里")){
            helper.setImageResource(R.id.iv_icon,R.drawable.ic_rate_1);
        }else if(item.getName().equals("当前速度")){
            helper.setImageResource(R.id.iv_icon,R.drawable.ic_rate_2);
        }else if(item.getName().equals("当前功率")){
            helper.setImageResource(R.id.iv_icon,R.drawable.ic_rate_3);
        }else if(item.getName().equals("当前段位")){
            helper.setImageResource(R.id.iv_icon,R.drawable.ic_rate_4);
        }else if(item.getName().equals("RPM/SPM")){
            helper.setImageResource(R.id.iv_icon,R.drawable.ic_rate_5);
        }
    }

}
