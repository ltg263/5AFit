package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.ProductListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeCnxhMainHomeAdapter extends BaseQuickAdapter<ProductListBean.ListBean, BaseViewHolder> {
    public HomeCnxhMainHomeAdapter(@Nullable List<ProductListBean.ListBean> data) {
        super(R.layout.item_home_rmkc_home, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProductListBean.ListBean item) {
        GlideImgLoader.loadImageViewRadius(mContext, item.getShowImgUrl(), 10,helper.getView(R.id.iv_icon_cnxh));
        helper.setGone(R.id.iv_icon,false).setText(R.id.tv_title, item.getName())
        .setVisible(R.id.iv_icon_cnxh,true);
    }

}
