package com.jxkj.fit_5a.alipay;

/**
 * Function:BaseView<T>
 * Author  :@author BuJie
 * Date:2018/5/27
 */

public interface BaseView<T> {

    /**
     * 显示进度,一般用于网络请求中的进度显示,可以在该窗口处理进度的显示方式,例如
     * 显示进度框等
     */
    void showProgress();

    /**
     * 隐藏进度,用于控制网络中进度页面的隐藏,自行处理
     */
    void dismissProgress();

}
