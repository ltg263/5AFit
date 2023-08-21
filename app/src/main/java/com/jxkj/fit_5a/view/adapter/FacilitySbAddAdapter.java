package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class FacilitySbAddAdapter extends BaseQuickAdapter<DeviceData.ListBean, BaseViewHolder> {
    public FacilitySbAddAdapter(@Nullable List<DeviceData.ListBean> data) {
        super(R.layout.item_facility_add_sb, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceData.ListBean item) {
        GlideImgLoader.loadImageViewWithCirclr(mContext,item.getImg(),helper.getView(R.id.iv));
        helper.setText(R.id.tv,item.getName()).addOnClickListener(R.id.iv_select);
        helper.setImageResource(R.id.iv_select,R.drawable.wxz_1);
        if(item.isSelect()){
            helper.setImageResource(R.id.iv_select,R.drawable.wxz_);
        }
    }


}
