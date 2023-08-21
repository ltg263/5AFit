package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HistoryEquipmentAdapter extends BaseQuickAdapter<HistoryEquipmentData, BaseViewHolder>{

    public HistoryEquipmentAdapter(@Nullable List<HistoryEquipmentData> data) {
        super(R.layout.item_history_equipment, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HistoryEquipmentData item) {
        helper.setText(R.id.tv_user_name,item.getName())
                .setText(R.id.tv_time,"上次连接时间:"+item.getTime()).setText(R.id.tv_user_name_f,item.getName_sb())
                .addOnClickListener(R.id.tv_ygz).addOnClickListener(R.id.ll_hidden);
        GlideImgLoader.loadImageViewRadius(mContext,item.getImg(),10,helper.getView(R.id.iv_head_img));
        if(item.getState().equals("0")){
            helper.setText(R.id.tv_ygz,"已连接");
        }else{
            helper.setText(R.id.tv_ygz,"开始连接");
        }
    }
}
