package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.AnnouncementList;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineMessageAnnouncementAdapter extends BaseQuickAdapter<AnnouncementList.ListBean, BaseViewHolder> {
    public MineMessageAnnouncementAdapter(@Nullable List<AnnouncementList.ListBean> data) {
        super(R.layout.item_mine_message_announcement, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AnnouncementList.ListBean item) {
        helper.setText(R.id.tv1,item.getTitle()).setText(R.id.tv2, StringUtil.getTimeToYMD(item.getCreateTime(),"yyyy-MM-dd HH:mm"));
    }
}
