package com.jxkj.fit_5a.view.activity.mine.order;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.alipay.PaymentContract;
import com.jxkj.fit_5a.alipay.PaymentParameterBean;
import com.jxkj.fit_5a.alipay.PaymentPresenter;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ParamData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowSy;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.CreateOrderBean;
import com.jxkj.fit_5a.entity.PostOrderInfo;
import com.jxkj.fit_5a.entity.ShowOrderInfo;
import com.jxkj.fit_5a.view.adapter.OrderAffirmAdapter;
import com.jxkj.fit_5a.wxapi.MessageEvent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderAffirmActivity extends BaseActivity implements PaymentContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_syjf)
    TextView tv_syjf;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv_youhui)
    TextView tv_youhui;
    @BindView(R.id.tv_totalDelivery)
    TextView tv_totalDelivery;
    @BindView(R.id.iv_wx)
    ImageView iv_wx;
    @BindView(R.id.iv_zfb)
    ImageView iv_zfb;
    @BindView(R.id.tv_levelMessage)
    EditText tv_levelMessage;
    @BindView(R.id.ll_youhui)
    LinearLayout mLlYouhui;
    @BindView(R.id.tv_jd)
    TextView mTvJd;
    @BindView(R.id.tv_jd_y)
    TextView tv_jd_y;
    @BindView(R.id.iv_jd)
    ImageView mIvJd;
    @BindView(R.id.ll_jd)
    LinearLayout mLlJd;
    @BindView(R.id.tv_jf)
    TextView mTvJf;
    @BindView(R.id.ll_jf)
    LinearLayout mLlJf;
    private OrderAffirmAdapter mOrderAffirmAdapter;
    private PostOrderInfo info;
    private PaymentPresenter paymentPresenter;
    private int payType = 1;
    private String payJd = "0";
    private boolean isJinDou = false;
    private boolean isYouHuiQuan = false;
    private double youHuiQuanNum = 0.0;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_affirm;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("确认订单");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        initRv();

        info = (PostOrderInfo) getIntent().getParcelableExtra("info");
        Log.w("info:", "OrderAffirmActivity:" + info.toString());
        EventBus.getDefault().register(this);
        paymentPresenter = new PaymentPresenter(this, OrderAffirmActivity.this);
        postShowOrderInfo();
    }

    private void initRv() {
        mOrderAffirmAdapter = new OrderAffirmAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mOrderAffirmAdapter);
    }


    @OnClick({R.id.ll_back, R.id.rl_address, R.id.iv_zfb, R.id.iv_wx, R.id.tv_pay, R.id.tv_youhui, R.id.iv_jd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_address:
                AddressActivity.startActivity(OrderAffirmActivity.this, 2);
                break;
            case R.id.tv_youhui:
                if(tv_youhui.getText().toString().equals("暂无可用优惠券")){
                    return;
                }
                popupWindw();
                break;
            case R.id.iv_zfb:
                payType = 2;
                iv_wx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                iv_zfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                break;
            case R.id.iv_wx:
                payType = 1;
                iv_wx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                iv_zfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                break;
            case R.id.tv_pay:
                if (Double.valueOf(data.getUseableIntegral()) >= Double.valueOf(data.getTotalIntegral())) {
                    postcreateOrder();
                    return;
                }
                ToastUtils.showShort("积分不足");
                break;
            case R.id.iv_jd:
                setValueJb();
                break;
        }
    }

    List<ShowOrderInfo.RedListBean> listYhqs;
    PopupWindowSy window;

    private void popupWindw() {
        window = new PopupWindowSy(this, listYhqs, new PopupWindowSy.GiveDialogInterface() {
            @Override
            public void btnConfirm(ShowOrderInfo.RedListBean bean) {
                isYouHuiQuan = true;
                youHuiQuanNum = Double.valueOf(bean.getReliefAmount());
                tv_youhui.setText(bean.getCouponName());
                info.setRedId(bean.getId());
                setValueYhq();
            }
        });
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setOutsideTouchable(true);
        window.showAtLocation(mTvName, Gravity.BOTTOM, 0, 0);
    }

    private void setValueYhq(){
        if (isJinDou) {
            mIvJd.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
            double yzf = Double.valueOf(data.getRealAmount())-youHuiQuanNum - Double.valueOf(data.getBalance());
            if (yzf > 0) {
                tv_syjf.setText(StringUtil.getValue(data.getTotalIntegral()) + "+￥" + StringUtil.getValue(yzf));
                payJd = data.getBalance();
            } else if (yzf < 0) {
                payJd = StringUtil.getValue(Double.valueOf(data.getRealAmount())-Double.valueOf(youHuiQuanNum));
                tv_syjf.setText(data.getTotalIntegral());
            } else {
                payJd = data.getBalance();
                tv_syjf.setText(data.getTotalIntegral());
            }
            payJd = StringUtil.getValue(payJd);
            tv_jd_y.setText("");
            if(Double.valueOf(payJd)>0){
                tv_jd_y.setText("使用" + payJd + "个金豆抵扣￥" + payJd);
            }
        } else {
            payJd = "";
            tv_jd_y.setText("");
            mIvJd.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
            tv_syjf.setText(data.getTotalIntegral() + "+￥" + StringUtil.getValue(Double.valueOf(data.getRealAmount())-youHuiQuanNum));
        }
    }

    private void setValueJb(){
        if (!isJinDou) {
            isJinDou = true;
            mIvJd.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
            double yzf = Double.valueOf(data.getRealAmount()) - Double.valueOf(data.getBalance());
            if(isYouHuiQuan){
                yzf = Double.valueOf(data.getRealAmount())-youHuiQuanNum - Double.valueOf(data.getBalance());
            }
            if (yzf > 0) {
                tv_syjf.setText(StringUtil.getValue(data.getTotalIntegral()) + "+￥" + StringUtil.getValue(yzf));
                payJd = data.getBalance();
            } else if (yzf < 0) {
                payJd = StringUtil.getValue(Double.valueOf(data.getRealAmount())-Double.valueOf(youHuiQuanNum));
                tv_syjf.setText(data.getTotalIntegral());
            } else {
                payJd = data.getBalance();
                tv_syjf.setText(data.getTotalIntegral());
            }
            payJd = StringUtil.getValue(payJd);
            tv_jd_y.setText("使用" + payJd + "个金豆抵扣￥" + payJd);
        } else {
            isJinDou = false;
            payJd = data.getRealAmount();
            tv_jd_y.setText("");
            mIvJd.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
            tv_syjf.setText(data.getTotalIntegral() + "+￥" + data.getRealAmount());
            if(isYouHuiQuan){ //100-10-200
                tv_syjf.setText(data.getTotalIntegral() + "+￥" + StringUtil.getValue(Double.valueOf(data.getRealAmount())-youHuiQuanNum));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20) {
            if (resultCode == 1) {
                AddressData addressData = (AddressData) data.getSerializableExtra("address");
                if (addressData == null) {
                    info.setAddressId("");
                    mTvName.setText("");
                    mTvAddress.setText("请选择收货地址");
                } else {
                    info.setAddressId(addressData.getId());
                    mTvName.setText("收件人：" + addressData.getAcceptName() + "  电话：" + addressData.getMobile());
                    mTvAddress.setText(addressData.getLocation());
                }
            }
        }

    }

    private void postcreateOrder() {
        info.setLevelMessage(tv_levelMessage.getText().toString());
        info.setIntegralFlag("1");
        if(isJinDou){
            info.setBalanceFlag("1");
        }else{
            info.setBalanceFlag("0");
        }
        Log.w("info", "info" + info.toString());
        RetrofitUtil.getInstance().apiService()
                .postcreateOrder(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CreateOrderBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CreateOrderBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getStatus().equals("1")){
//                                ToastUtils.showShort("创建成功");
                                appPay(result.getData().getDeductIntegral(),result.getData().getId());
                            }else{
                                ToastUtils.showShort("支付成功");
                                IntentUtils.getInstence().intent(OrderAffirmActivity.this, OrderActivity.class, "type", 0);
                                dismiss();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    ShowOrderInfo data;

    private void postShowOrderInfo() {
        show();
        RetrofitUtil.getInstance().apiService()
                .postShowOrderInfo(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ShowOrderInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ShowOrderInfo> result) {
                        if (isDataInfoSucceed(result)) {
                            data = result.getData();
                            if (data.getUserAddress() != null) {
                                mTvName.setText("收件人：" + data.getUserAddress().getAcceptName() + "  电话：" + data.getUserAddress().getMobile());
                                mTvAddress.setText(data.getUserAddress().getLocation());
                                info.setAddressId(data.getUserAddress().getId());
                            }
                            if (data.getOrderProducts() != null) {
                                mOrderAffirmAdapter.setNewData(data.getOrderProducts());
                            }
//                            tv_totalDelivery.setText(StringUtil.getValue());
                            tv1.setText(data.getTotalQuantity() + "件商品");
                            if (Double.valueOf(data.getRealAmount()) != 0) {
                                tv11.setText(data.getTotalIntegral() + "积分" + "+￥" + data.getRealAmount());
                                tv_syjf.setText(data.getTotalIntegral() + "+￥" + data.getRealAmount());
                            } else {
                                tv11.setText(data.getTotalIntegral() + "积分");
                                tv_syjf.setText(data.getTotalIntegral());
                            }

                            if (data.getRedList() == null || data.getRedList().size() == 0) {
                                tv_youhui.setText("暂无可用优惠券");
                            } else {
                                listYhqs = data.getRedList();
                                tv_youhui.setText("有" + data.getRedList().size() + "张优惠券");
                            }
                            if (Double.valueOf(data.getBalance()) != 0) {
                                mTvJd.setText("使用金豆(剩余" + data.getBalance() + "个)");
                                mIvJd.setVisibility(View.VISIBLE);
                            }
                            if (Double.valueOf(data.getUseableIntegral()) != 0) {
                                mTvJf.setText("剩余积分：" + data.getUseableIntegral());
                            }

                        } else {
                            OrderAffirmActivity.this.finish();
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

    /**
     * 支付
     * orderId	订单Id
     * wxPayType	支付类型：1,小程序;2,公众号;3,app；4，扫码
     * payType	支付方式:1,支付宝支付;2,微信支付;3,银行卡支付;4,余额支付
     * orderType(订单类型:1,普通订单；2，积分订单；)
     *
     * @param id
     */
    private void appPay(String deductIntegral,String id) {
        show();
        RetrofitUtil.getInstance().apiService()
                .getOrderPayInfo(deductIntegral, id, payType + "", info.getRedId(), "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ParamData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ParamData> result) {
                        if (isDataInfoSucceed(result)) {
                            if (payType == 2) {
//                                appPayZfb(result.getData().getParam());
                            } else {
                                appPayWx(result.getData());
                            }
                        } else {
                            ToastUtils.showShort(result.getMesg());
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

    private void appPayWx(ParamData mPayModel) {
        boolean flag = UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN);
        if (flag) {
            PaymentParameterBean mPaymentParameterBean = new PaymentParameterBean();
            mPaymentParameterBean.setWxAppid(mPayModel.getAppid());
            mPaymentParameterBean.setPartnerId(mPayModel.getPartnerid());
            mPaymentParameterBean.setPrepayId(mPayModel.getPrepayid());
            mPaymentParameterBean.setNonceStr(mPayModel.getNoncestr());
            mPaymentParameterBean.setTimeStamp(mPayModel.getTimestamp());
            mPaymentParameterBean.setPackageValue(mPayModel.getPackageX());
            mPaymentParameterBean.setSign(mPayModel.getSign());
            paymentPresenter.doWXPay(mPaymentParameterBean);
        } else {
            ToastUtils.showShortToast(this, "您没有安装微信客户端!");
        }
    }

    private void appPayZfb(String data) {
        PaymentParameterBean mPaymentParameterBean1 = new PaymentParameterBean();
        mPaymentParameterBean1.setOrderInfo(data);
        paymentPresenter.doAliPay(mPaymentParameterBean1);
    }


//    private boolean flag = false;


    /*支付结果的回调*/
    private void paymentResultCallback(boolean flag) {
        if (flag) {
            ToastUtils.showShort("支付成功");
//            IntentUtils.getInstence().intent(this, OrderActivity.class, "position", 0);
        } else {
            ToastUtils.showShort("支付失败");
        }
        IntentUtils.getInstence().intent(this, OrderActivity.class);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        if (paymentPresenter != null) {
            paymentPresenter.stop();
        }

    }

    /**
     * 用于处理微信结果的回调,结果来自{@link }
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        String originClass = event.getOriginClass();
        if (StringUtil.isBlank(originClass)) {
            return;
        }
        switch (originClass) {
            case "WXPayEntryActivity":
                //事件延迟显得更顺滑
                paymentResultCallback(event.isBooleanFlag());
                break;
            default:
                break;
        }


    }

    @Override
    public void showProgress() {
        show();
    }

    @Override
    public void dismissProgress() {
        dismiss();
    }

    @Override
    public void onWXPaySuccess() {
//        ActivityCollector.getAppManager().finishotherActivity(MainActivity.activity, ShoppingPaymentActivity.this);
//        IntentUtils.getInstence().intent(ShoppingPaymentActivity.this, MyOrderActivity.class, "position", 0);
        finish();

    }

    @Override
    public void onAliPaySuccess() {
        paymentResultCallback(true);
    }

    @Override
    public void onWxPayFailure() {
        ToastUtils.showShort("支付未成功!");
//        ActivityCollector.getAppManager().finishotherActivity(MainActivity.activity, ShoppingPaymentActivity.this);
//        IntentUtils.getInstence().intent(ShoppingPaymentActivity.this, MyOrderActivity.class, "position", 1);
        finish();
    }

    @Override
    public void onAliPayFailure() {
        ToastUtils.showShort("支付未成功!");
        paymentResultCallback(false);
    }
}
