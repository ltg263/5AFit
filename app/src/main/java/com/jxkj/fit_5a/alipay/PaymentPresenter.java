package com.jxkj.fit_5a.alipay;

import android.app.Activity;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.jxkj.fit_5a.alipay.pay.PayResult;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Function:PaymentPresenter
 * Author  :@author BuJie
 * Date:2018/7/31
 */

public class PaymentPresenter implements PaymentContract.Presenter {
    private PaymentContract.View mView;
    private WeakReference<Activity> mWeakActivity;
    private CompositeSubscription mCompositeSubscription;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    public PaymentPresenter(PaymentContract.View mView, Activity context) {
        this.mView = mView;
        mWeakActivity = new WeakReference<>(context);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        mCompositeSubscription.unsubscribe();
    }


    @Override
    public void doWXPay(PaymentParameterBean paymentParameterBean) {
        if (paymentParameterBean == null) {
            return;
        }
        final String appid = paymentParameterBean.getWxAppid();
        final String partnerId = paymentParameterBean.getPartnerId();
        final String prepayId = paymentParameterBean.getPrepayId();
        final String nonceStr = paymentParameterBean.getNonceStr();
        final String timeStamp = paymentParameterBean.getTimeStamp();
        final String packageValue = paymentParameterBean.getPackageValue();
        final String sign = paymentParameterBean.getSign();
        final String extData = paymentParameterBean.getExtData();
        final IWXAPI api = WXAPIFactory.createWXAPI(mWeakActivity.get(), ConstValues.WX_APP_ID);

        new Thread(new Runnable() {
            @Override
            public void run() {
                PayReq req = new PayReq();
                req.appId = appid;
                req.partnerId = partnerId;
                req.prepayId = prepayId;
                req.packageValue = packageValue;
                req.nonceStr = nonceStr;
                req.timeStamp = timeStamp;
                req.sign = sign;
                api.sendReq(req);
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doAliPay(final PaymentParameterBean paymentParameterBean) {
        if (paymentParameterBean == null) {
            return;
        }
        Subscription subscription = aliPayType(paymentParameterBean).subscribe(new Action1<Message>() {
            @Override
            public void call(Message msg) {
                if (msg == null) {
                    return;
                }
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        /*对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。*/
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    mView.onAliPaySuccess();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    mView.onAliPayFailure();
                }
            }
        });
        mCompositeSubscription.add(subscription);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void doAliAuth(PaymentParameterBean paymentParameterBean) {
        if (paymentParameterBean == null) {
            return;
        }
        Subscription subscription = aliPayType(paymentParameterBean).subscribe(new Action1<Message>() {
            @Override
            public void call(Message message) {
                PayResult payResult = new PayResult((Map<String, String>) message.obj);
                    /*对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。*/
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                    mView.onAliPaySuccess();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                    mView.onAliPayFailure();
                }
            }
        });
        mCompositeSubscription.add(subscription);

    }


    private Observable<Message> aliPayType(PaymentParameterBean paymentParameterBean) {
        if (paymentParameterBean == null) {
            return null;
        }
        final String orderInfo = paymentParameterBean.getOrderInfo();
        Log.d("TAGGG","orderInfo=="+orderInfo);
        return Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                PayTask alipay = new PayTask(mWeakActivity.get());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.obj = result;
                subscriber.onNext(msg);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
