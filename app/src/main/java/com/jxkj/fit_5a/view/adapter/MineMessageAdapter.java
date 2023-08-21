package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineMessageAdapter extends BaseQuickAdapter<MessageSubtypeBean, BaseViewHolder> {
    public MineMessageAdapter(@Nullable List<MessageSubtypeBean> data) {
        super(R.layout.item_mine_message, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MessageSubtypeBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getLogoUrl(),helper.getView(R.id.iv));
        helper.setText(R.id.tv1,item.getSubTypeText()).setVisible(R.id.tv_doc,false).setText(R.id.tv2,"暂无新消息");

        if(item.getLastUnreadMessage()!=null){
            helper.setText(R.id.tv2,item.getLastUnreadMessage().getTitle());
        }

        if(Integer.parseInt(item.getUnReadCount())>0){
            helper.setVisible(R.id.tv_doc,true).setText(R.id.tv_doc,item.getUnReadCount()+"");
        }
    }
}
