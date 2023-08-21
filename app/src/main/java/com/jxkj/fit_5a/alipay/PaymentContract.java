package com.jxkj.fit_5a.alipay;



/**
 *
 * 页面控制器
 * Author  :@author BuJie
 * Date:2018/5/27
 */

public interface PaymentContract {

    interface Presenter extends BasePresenter {
        /**
         * 微信支付
         */
        void doWXPay(PaymentParameterBean paymentParameterBean);

        /**
         * 支付宝支付
         */
        void doAliPay(PaymentParameterBean paymentParameterBean);

        /**
         * 支付宝支付
         */
        void doAliAuth(PaymentParameterBean paymentParameterBean);

    }

    interface View extends BaseView<Presenter> {
        /**
         * 微信支付成功
         */
        void onWXPaySuccess();

        /**
         * 支付宝登录成功
         */
        void onAliPaySuccess();

        /**
         * 微信支付失败
         */
        void onWxPayFailure();

        /**
         * 支付宝登录失败
         */
        void onAliPayFailure();
    }

}