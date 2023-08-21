package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserLwSongAdapter extends BaseQuickAdapter<GiftLogListData.ListBean, BaseViewHolder> {
    public UserLwSongAdapter(@Nullable List<GiftLogListData.ListBean> data) {
        super(R.layout.item_user_lw_song, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GiftLogListData.ListBean item) {

        GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
        GlideImageUtils.setGlideImage(mContext,item.getAvatar(),helper.getView(R.id.icon_head));
        helper.setText(R.id.tv_user,item.getNickName()).setText(R.id.tv_time,
                item.getCreateTime()).setText(R.id.tv_worth,"-"+item.getRealPrice());
    }

}
