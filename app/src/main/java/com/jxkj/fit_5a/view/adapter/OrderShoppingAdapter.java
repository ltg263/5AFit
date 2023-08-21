package com.jxkj.fit_5a.view.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

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
public class OrderShoppingAdapter extends BaseQuickAdapter<OrderInfoData.ListBean.ProductListBean, BaseViewHolder> {
    Context activity;
    public OrderShoppingAdapter(Context activity, @Nullable List<OrderInfoData.ListBean.ProductListBean> data) {
        super(R.layout.item_order_shopping, data);
        this.activity = activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderInfoData.ListBean.ProductListBean item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_shop_name = helper.getView(R.id.tv_shop_name);
        TextView tv_spec = helper.getView(R.id.tv_spec);
        TextView tv_price = helper.getView(R.id.tv_price);
        tv_name.setText(item.getName());
        String str = "单价：<font color=\"#FF6666\">"+item.getDeductIntegral()+"积分 + ￥"+item.getPrice()+"</font>";
        tv_shop_name.setText(Html.fromHtml(str));
        tv_price.setText("×"+item.getNum());
        if (StringUtil.isNotBlank(item.getSkuName())) {
            tv_spec.setText(item.getSkuName());
        }else{
            tv_spec.setText("");
        }
        GlideImgLoader.loadImageViewRadius(activity, item.getImgUrl(),10, iv_img);


    }


}
