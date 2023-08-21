package com.jxkj.fit_5a.view.adapter;

import android.annotation.SuppressLint;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.view.activity.mine.order.OrderDetailsActivity;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2712:25
 * 订单状态1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
 */
public class OrderListAdapter extends BaseQuickAdapter<OrderInfoData.ListBean, BaseViewHolder> {
    public OrderListAdapter(@Nullable List<OrderInfoData.ListBean> data) {
        super(R.layout.item_order_list, data);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderInfoData.ListBean item) {

        helper.setText(R.id.tv_order_shop_name, item.getOrderNo());
        helper.setText(R.id.tv_status, item.getStatusStr());
//        helper.setText(R.id.tv_time,item.getCreateTime());
        String deliveryAmount = "";
        if(Double.valueOf(item.getDeliveryAmount())!=0){
            deliveryAmount = " （含￥"+item.getDeliveryAmount()+"运费）";
        }
        String str = "共计<font color=\"#FF6666\">1</font>件："+item.getDeductIntegral()+"积分 + ￥"+item.getRealAmount()+deliveryAmount;
        helper.setText(R.id.tv_jg_num, Html.fromHtml(str));
        RecyclerView recyclerView = helper.getView(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        OrderShoppingAdapter mOrderShoppingAdapter = new OrderShoppingAdapter(recyclerView.getContext(), item.getProductList());//item.getProducts()
        recyclerView.setAdapter(mOrderShoppingAdapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //模拟父控件的点击
                    IntentUtils.getInstence().intent(recyclerView.getContext(), OrderDetailsActivity.class, "id", item.getId());
                }
                return false;
            }
        });
//        helper.setText(R.id.tv_price, "￥" + item.getRealAmount());
        TextView btn0 = helper.getView(R.id.btn0);
        TextView btn1 = helper.getView(R.id.btn1);
        TextView btn2 = helper.getView(R.id.btn2);
        btn0.setVisibility(View.GONE);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        //1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
        switch (item.getStatus()) {
            case "1"://待支付
//                btn0.setVisibility(View.VISIBLE);
//                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("取消订单");
                btn2.setVisibility(View.VISIBLE);
                btn2.setText("去支付");
                break;
            case "2"://待发货
//                btn0.setVisibility(View.VISIBLE);
//                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("提醒发货");
                break;
            case "3"://待收货
//                btn0.setVisibility(View.VISIBLE);
//                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("查看物流");
                btn2.setVisibility(View.VISIBLE);
                btn2.setText("确认收货");
                break;
            case "4"://待评价
//                btn0.setVisibility(View.VISIBLE);
//                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("删除订单");
                btn2.setVisibility(View.VISIBLE);
                btn2.setText("去评价");
                break;
            case "5"://已完成
                btn0.setVisibility(View.VISIBLE);
                btn0.setText("申请退款");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("删除订单");
                break;
            case "6"://已取消
            case "7"://已过期
            case "8"://已超时
//                btn0.setVisibility(View.VISIBLE);
//                btn0.setText("联系商家");
                btn1.setVisibility(View.VISIBLE);
                btn1.setText("删除订单");
                break;
            default:
                btn0.setVisibility(View.GONE);
        }
//        btn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setIntentType(btn0.getText().toString(),helper.getLayoutPosition());
//            }
//        });
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setIntentType(btn1.getText().toString(),helper.getLayoutPosition());
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setIntentType(btn2.getText().toString(),helper.getLayoutPosition());
//            }
//        });

    }

    private void setIntentType(String strType,int pos) {
        switch (strType){
            case "联系商家":
                myOrderAdapterListener.setLxcj(pos);
                break;
            case "取消订单":
                myOrderAdapterListener.setQxdd(pos);
                break;
            case "去支付":
                myOrderAdapterListener.setQzf(pos);
                break;
            case "确认收货":
                myOrderAdapterListener.setQrsh(pos);
                break;
            case "去评价":
                myOrderAdapterListener.setQpj(pos);
                break;
            case "提醒发货":
                myOrderAdapterListener.setZlyd(pos);
                break;
            case "删除订单":
                myOrderAdapterListener.setSc(pos);
                break;
            case "申请退款":
                myOrderAdapterListener.setSqtk(pos);
                break;
        }
    }

    private MyOrderAdapterListener myOrderAdapterListener;

    public MyOrderAdapterListener getMyOrderAdapterListener() {
        return myOrderAdapterListener;
    }

    public void setMyOrderAdapterListener(MyOrderAdapterListener myOrderAdapterListener) {
        this.myOrderAdapterListener = myOrderAdapterListener;
    }

    public interface MyOrderAdapterListener {

        void setLxcj(int position);
        void setQxdd(int position);
        void setQzf(int position);
        void setQrsh(int position);
        void setQpj(int position);
        void setZlyd(int position);
        void setSc(int position);
        void setSqtk(int position);

    }
}
