package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeRmkcMainAdapter extends BaseQuickAdapter<TeachingMomentBean, BaseViewHolder> {
    public HomeRmkcMainAdapter(@Nullable List<TeachingMomentBean> data) {
        super(R.layout.item_home_rmkc, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TeachingMomentBean item) {
        GlideImgLoader.loadImageViewRadius(mContext, item.getCoverImageUrl(), 10,helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title, item.getTitle());
    }

}
