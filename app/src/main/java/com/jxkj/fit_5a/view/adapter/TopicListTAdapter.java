package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.TopicAllBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class TopicListTAdapter extends BaseQuickAdapter<TopicAllBean, BaseViewHolder> {
    public TopicListTAdapter(@Nullable List<TopicAllBean> data) {
        super(R.layout.item_topic_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TopicAllBean item) {
        if(item.isSele()){
            helper.setText(R.id.tv1,item.getName())
                    .setTextColor(R.id.tv1,mContext.getResources().getColor(R.color.color_text_theme));
//                    .setBackgroundColor(R.id.tv1,mContext.getResources().getColor(R.color.color_ffffff));
        }else{
            helper.setText(R.id.tv1,item.getName())
                    .setTextColor(R.id.tv1,mContext.getResources().getColor(R.color.color_333333));
//                    .setBackgroundColor(R.id.tv1,mContext.getResources().getColor(R.color.color_f5f5f5));
        }
    }

}
