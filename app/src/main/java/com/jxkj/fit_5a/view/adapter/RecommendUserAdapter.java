package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.RecommendUser;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class RecommendUserAdapter extends BaseQuickAdapter<RecommendUser, BaseViewHolder> {
    public RecommendUserAdapter(@Nullable List<RecommendUser> data) {
        super(R.layout.item_img, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecommendUser item) {
        GlideImageUtils.setGlideImage(mContext,item.getAvatar(),helper.getView(R.id.iv_img));
        helper.setVisible(R.id.iv_select,false).setBackgroundColor(R.id.rl,
                mContext.getResources().getColor(R.color.transparent)).setText(R.id.tv_title,item.getNickName());

        if(item.isSelect()){
            helper.setVisible(R.id.iv_select,true);
            helper.setVisible(R.id.iv_select,true).setBackgroundRes(R.id.rl,R.drawable.bj_circle_line_theme);
        }
    }

}
