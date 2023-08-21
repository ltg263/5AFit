package com.jxkj.fit_5a.view.activity.mine.order;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.OrderDetailsData;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.adapter.OrderShoppingDetailsAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//

public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_zt)
    TextView mTvZt;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_order_1)
    TextView mTvOrder1;
    @BindView(R.id.tv_order_2)
    TextView mTvOrder2;
    @BindView(R.id.tv_order_3)
    TextView mTvOrder3;
    @BindView(R.id.tv_order_4)
    TextView mTvOrder4;//下单时间
    @BindView(R.id.tv_order_5)
    TextView mTvOrder5;//支付时间
    @BindView(R.id.tv_order_6)
    TextView mTvOrder6;//发货时间
    @BindView(R.id.tv_order_7)
    TextView mTvOrder7;//关闭时间
    @BindView(R.id.tv_order_8)
    TextView mTvOrder8;//完成时间
    @BindView(R.id.tv_order_9)
    TextView mTvOrder9;//完成时间
    @BindView(R.id.bnt0)
    TextView btn0;
    @BindView(R.id.bnt1)
    TextView btn1;
    @BindView(R.id.bnt2)
    TextView btn2;
    String id = "";
    private OrderShoppingDetailsAdapter mOrderShoppingDetailsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initViews() {

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mOrderShoppingDetailsAdapter = new OrderShoppingDetailsAdapter(this, null);//item.getProducts()
        mRvList.setAdapter(mOrderShoppingDetailsAdapter);
        id = getIntent().getStringExtra("id");
        getOrderDetails();
    }


    private void getOrderDetails() {
        RetrofitUtil.getInstance().apiService()
                .getOrderDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OrderDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OrderDetailsData> result) {
                        if (isDataInfoSucceed(result)) {
                            initUi(result.getData());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
//单个订单状态1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
    private void initUi(OrderDetailsData data) {
        mOrderShoppingDetailsAdapter.setNewData(data.getProductList());
        mTvName.setText("收件人："+data.getDetail().getAcceptName()+"  "+data.getDetail().getMobile());
        mTvAddress.setText(data.getDetail().getLocation());
        mTvOrder1.setText("订单编号："+data.getOrderNo());
        mTvOrder2.setText("支付方式："+data.getPayTypeStr());
        mTvOrder3.setText("商品信息：共"+data.getTotalNum()+"件商品"+data.getDeductIntegral()+"积分+ ￥"+data.getRealAmount()+" 运费￥"+data.getDeliveryAmount());
        mTvOrder4.setText("下单时间："+data.getCreateTime());
        if(StringUtil.isNotBlank(data.getPayTime())){
            mTvOrder5.setVisibility(View.VISIBLE);
            mTvOrder5.setText("支付时间："+data.getPayTime());
        }
        if(StringUtil.isNotBlank(data.getDeliveryTime())){
            mTvOrder6.setVisibility(View.VISIBLE);
            mTvOrder6.setText("发货时间："+data.getDeliveryTime());
        }
        if(StringUtil.isNotBlank(data.getCloseTime())){
            mTvOrder7.setVisibility(View.VISIBLE);
            mTvOrder7.setText("关闭时间："+data.getCloseTime());
        }
        if(StringUtil.isNotBlank(data.getFinishTime())){
            mTvOrder8.setVisibility(View.VISIBLE);
            mTvOrder8.setText("完成时间："+data.getFinishTime());
        }
        if(StringUtil.isNotBlank(data.getExpireTime())){
            mTvOrder9.setVisibility(View.VISIBLE);
            mTvOrder9.setText("过期时间："+data.getExpireTime());
        }
        btn0.setVisibility(View.GONE);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        //1,待支付;2,待发货;3,待收货;4,待评价;5,已完成;6,已取消;7,已过期;8,已结束
        switch (data.getStatus()) {
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
    }


    @OnClick({R.id.iv_back, R.id.bnt1, R.id.bnt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bnt1:
                setOnclick(btn1.getText().toString());
                break;
            case R.id.bnt2:
                setOnclick(btn2.getText().toString());
                break;
        }
    }

    private void setOnclick(String strType) {
        switch (strType){
            case "联系商家":
                break;
            case "取消订单":
//                DialogUtils.showDialogHint(OrderDetailsActivity.this, "您确定要取消此订单吗？", false, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        getCancelOrder();
//                    }
//                });

                DialogUtils.showUnificationDialog(this, "提示", "您确定要取消此订单吗", "确定", true, new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        getCancelOrder();
                    }
                });
                break;
            case "去支付":

                break;
            case "确认收货":
//                DialogUtils.showDialogHint(OrderDetailsActivity.this, "您确定完成此订单吗？", false, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        postFinishOrder();
//                    }
//                });
                DialogUtils.showUnificationDialog(this, "提示", "您确定完成此订单吗", "确定", true, new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        postFinishOrder();
                    }
                });
                break;
            case "去评价":
                Intent mIntent = new Intent(OrderDetailsActivity.this, MineOrderEvaluateGoodsActivity.class);
                mIntent.putExtra("ProductListBean", mOrderShoppingDetailsAdapter.getData().get(0));
                startActivity(mIntent);
                break;
            case "提醒发货":
                getAgainOrder();
                break;
            case "删除订单":
//                DialogUtils.showDialogHint(OrderDetailsActivity.this, "您确定要删除此订单吗？", false, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        postDelete();
//                    }
//                });
                DialogUtils.showUnificationDialog(this, "提示", "您确定要删除此订单吗", "确定", true, new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        postDelete();
                    }
                });
                break;
            case "申请退款":

                break;
        }
    }

    private void postFinishOrder() {
        RetrofitUtil.getInstance().apiService()
                .postFinishOrder(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            OrderListFragment.isRefresh = true;
                            getOrderDetails();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
    private void getAgainOrder() {
        RetrofitUtil.getInstance().apiService()
                .postExpediting(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("已通知卖家");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
    private void postDelete() {
        RetrofitUtil.getInstance().apiService()
                .postDelete(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            OrderListFragment.isRefresh = true;
                            OrderDetailsActivity.this.finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    private void getCancelOrder() {
        RetrofitUtil.getInstance().apiService()
                .postCancelOrder(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            OrderListFragment.isRefresh = true;
                            getOrderDetails();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
}
