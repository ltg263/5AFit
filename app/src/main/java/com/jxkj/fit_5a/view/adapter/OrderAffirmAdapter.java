package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.ShowOrderInfo;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2615:09
 * 305.2+76.30+1920
 *
 */
public class OrderAffirmAdapter extends BaseQuickAdapter<ShowOrderInfo.OrderProductsBean, BaseViewHolder> {
    public OrderAffirmAdapter( @Nullable List<ShowOrderInfo.OrderProductsBean> data) {
        super(R.layout.item_order_affirm, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShowOrderInfo.OrderProductsBean item) {
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
        helper.setText(R.id.tv_name,item.getName()).setText(R.id.tv_spec,item.getSkuName())
                .setText(R.id.tv_shop_name,item.getDeductIntegral()+"+ ￥"+item.getPrice())
        .setText(R.id.tv_price,"x "+item.getNum());
        if (Double.parseDouble(item.getPrice())==0){
            helper.setText(R.id.tv_shop_name,item.getDeductIntegral());
        }

    }


}
