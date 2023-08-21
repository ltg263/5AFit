package com.jxkj.fit_5a.view.adapter;

import android.content.Context;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2615:09
 */
public class OrderShoppingDetailsAdapter extends BaseQuickAdapter<OrderInfoData.ListBean.ProductListBean, BaseViewHolder> {
    Context activity;
    public OrderShoppingDetailsAdapter(Context activity, @Nullable List<OrderInfoData.ListBean.ProductListBean> data) {
        super(R.layout.item_order_shopping_details, data);
        this.activity = activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderInfoData.ListBean.ProductListBean item) {
        GlideImgLoader.loadImageViewRadius(activity, item.getImgUrl(),10, helper.getView(R.id.iv_img));
        String str = "单价：<font color=\"#FF6666\">"+item.getDeductIntegral()+"积分 + "+item.getPrice()+"元</font>";
        if(Double.valueOf(item.getPrice())==0){
            str = "单价：<font color=\"#FF6666\">"+item.getDeductIntegral()+"积分";
        }

        if (StringUtil.isNotBlank(item.getSkuName())) {
            helper.setText(R.id.tv_spec,"规格："+item.getSkuName());
        }else{
            helper.setText(R.id.tv_spec,"");
        }
        helper.setText(R.id.tv_name,item.getName())
                .setText(R.id.tv_shop_name,Html.fromHtml(str)).setText(R.id.tv_price,"x "+item.getNum());
    }


}
