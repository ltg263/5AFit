package com.jxkj.fit_5a.alipay.pay;

/**
 * 作者: Created by wangyu on 2019/2/15.
 */

public class AliError {
    public static final String SUCCESS_9000 = "9000";
    public static final String ERROR_8000 = "8000";
    public static final String ERROR_4000 = "4000";
    public static final String ERROR_5000 = "5000";
    public static final String ERROR_6001 = "6001";
    public static final String ERROR_6002 = "6002";
    public static final String ERROR_6004 = "6004";


    public static final String ERROR_9000_MSG = "订单支付成功";
    public static final String ERROR_8000_MSG = "正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态";
    public static final String ERROR_4000_MSG = "订单支付失败";
    public static final String ERROR_5000_MSG = "重复请求";
    public static final String ERROR_6001_MSG = "用户中途取消";
    public static final String ERROR_6002_MSG = "网络连接出错";
    public static final String ERROR_6004_MSG = "支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态";
    public static final String ERROR_NULK_MSG = "其它支付错误";


}
