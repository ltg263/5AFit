package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.HotTopicBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class TopicListAdapter extends BaseQuickAdapter<HotTopicBean, BaseViewHolder> {
    public TopicListAdapter(@Nullable List<HotTopicBean> data) {
        super(R.layout.item_topic_all, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotTopicBean item) {
        helper.setText(R.id.tv_1,item.getName()).setText(R.id.tv_2,item.getIntroduction());
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.iv));
    }

}
