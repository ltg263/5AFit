package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class PopupWindwSbAdapter extends BaseQuickAdapter<DeviceTypeData.ListBean, BaseViewHolder> {
    public PopupWindwSbAdapter(@Nullable List<DeviceTypeData.ListBean> data) {
        super(R.layout.item_topic_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceTypeData.ListBean item) {
        if(item==null){
            helper.setText(R.id.tv1,"全部").setGone(R.id.view,true);
        }else{
            helper.setText(R.id.tv1,item.getName()).setGone(R.id.view,true);
        }
        if(helper.getLayoutPosition()==mData.size()-1){
            helper.setGone(R.id.view,false);
        }
    }

}
