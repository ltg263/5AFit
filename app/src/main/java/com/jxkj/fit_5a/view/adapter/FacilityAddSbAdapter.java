package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class FacilityAddSbAdapter extends BaseQuickAdapter<DeviceTypeData.ListBean, BaseViewHolder> {

    boolean isFang = false;
    public FacilityAddSbAdapter(@Nullable List<DeviceTypeData.ListBean> data) {
        super(R.layout.item_facility_manage, data);
    }

    public void setFang(boolean fang) {
        isFang = fang;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceTypeData.ListBean item) {
        if(isFang){
            helper.setGone(R.id.ll_chang,false)
                    .setGone(R.id.ll_fang,true)
                    .setText(R.id.tv_name_1,item.getName());
            GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv_img_1));
            return;
        }
        helper.setVisible(R.id.tv_ljzt,false)
                .setVisible(R.id.iv_1,false)
                .setVisible(R.id.iv_2,true)
                .setText(R.id.tv_name,item.getName())
                .setText(R.id.tv_sub_title,item.getSubTitle());
        GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv_img));
    }

}
