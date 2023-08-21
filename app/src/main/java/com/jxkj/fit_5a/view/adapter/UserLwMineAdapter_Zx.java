package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserLwMineAdapter_Zx extends BaseQuickAdapter<GiftListData.ListBean, BaseViewHolder> {
    public UserLwMineAdapter_Zx(@Nullable List<GiftListData.ListBean> data) {
        super(R.layout.item_user_lw_mine_zx, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GiftListData.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_realPrice,item.getRealPrice()).setText(R.id.tv_balance,"×"+item.getBalance());
    }

}
