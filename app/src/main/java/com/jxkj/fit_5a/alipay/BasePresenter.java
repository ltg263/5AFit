package com.jxkj.fit_5a.alipay;

/**
 * Function:BasePresenter
 * Author  :@author BuJie
 * Date:2018/5/27
 */

public interface BasePresenter {
    /**
     * 开始,用于一些任务的开始项
     */
    void start();

    /**
     * 结束,结束某些任务,例如项目中rxJava中subscribe的解绑等
     */
    void stop();
}