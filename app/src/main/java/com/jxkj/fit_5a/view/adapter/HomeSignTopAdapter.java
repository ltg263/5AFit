package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.SignLogData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeSignTopAdapter extends BaseQuickAdapter<SignLogData.ListBean, BaseViewHolder> {
    public HomeSignTopAdapter(@Nullable List<SignLogData.ListBean> data) {
        super(R.layout.item_home_sign_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SignLogData.ListBean item) {
        helper.setText(R.id.tv_day,item.getSj()).setText(R.id.tv,item.getIntegral().replace(".00",""));
        helper.setBackgroundRes(R.id.rl,R.drawable.jb_shape_theme_top_1);
        if(item.isSig()){
            helper.setBackgroundRes(R.id.rl,R.drawable.jb_shape_theme_top);
        }

    }

}
