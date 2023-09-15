package com.jxkj.fit_5a.view.adapter;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.DeviceTypeCoachData;

import java.util.List;

public class PopupWindowAdapter_jl extends BaseQuickAdapter<DeviceTypeCoachData, BaseViewHolder> {

    int selePos = 0;

    @SuppressLint("NotifyDataSetChanged")
    public void setSelePos(int selePos) {
        this.selePos = selePos;
        notifyDataSetChanged();
    }

    public PopupWindowAdapter_jl(List<DeviceTypeCoachData> data) {
        super(R.layout.item_spsx_user, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,DeviceTypeCoachData item) {
        helper.setGone(R.id.ll_select_yes,false).setGone(R.id.ll_select_no,true)
                .setText(R.id.tv_name,item.getCoachName()).setText(R.id.tv_name_no,item.getCoachName());
        GlideImageUtils.setGlideImage(mContext,item.getAvatar(),helper.getView(R.id.iv_img));
        GlideImageUtils.setGlideImage(mContext,item.getAvatar(),helper.getView(R.id.iv_img_no));
        if(helper.getLayoutPosition()==selePos){
            helper.setGone(R.id.ll_select_yes,true).setGone(R.id.ll_select_no,false);
        }
    }

}
