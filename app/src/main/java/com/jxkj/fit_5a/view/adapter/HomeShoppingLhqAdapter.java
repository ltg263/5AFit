package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeShoppingLhqAdapter extends BaseQuickAdapter<DiscountUsableNotBean.ListBean, BaseViewHolder> {
    public HomeShoppingLhqAdapter(@Nullable List<DiscountUsableNotBean.ListBean> data) {
        super(R.layout.item_home_commodity_lhq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DiscountUsableNotBean.ListBean item) {
        GlideImageUtils.setGlideImage(mContext, item.getImgUrl(), helper.getView(R.id.iv_shop_pic));
        helper.setText(R.id.tv_shop_title, item.getCouponName()).setText(R.id.tv_jf, item.getValidityDays()+"")
                .setText(R.id.tv_shop_yxq, "有效期：" + item.getValidityDays()+"天");
    }

}
