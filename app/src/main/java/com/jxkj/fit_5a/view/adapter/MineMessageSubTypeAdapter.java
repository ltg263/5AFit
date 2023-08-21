package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.AnnouncementList;
import com.jxkj.fit_5a.entity.LastUnreadMessageBeanList;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineMessageSubTypeAdapter extends BaseQuickAdapter<MessageSubtypeBean.LastUnreadMessageBean, BaseViewHolder> {
    public MineMessageSubTypeAdapter(@Nullable List<MessageSubtypeBean.LastUnreadMessageBean> data) {
        super(R.layout.item_mine_message_announcement, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MessageSubtypeBean.LastUnreadMessageBean item) {
        helper.setText(R.id.tv1,item.getTitle()).setText(R.id.tv2,
                StringUtil.getTimeToYMD(Long.valueOf(item.getCreateTime()),"yyyy-MM-dd HH:mm"))
                .setVisible(R.id.tv_doc,false);
        if(item.getReadStatus().equals("1")){
            helper.setVisible(R.id.tv_doc,true);
        }
    }
}
