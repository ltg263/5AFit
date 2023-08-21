package com.jxkj.fit_5a.base;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

public class UserInfoData {

    /**
     * balance : 0
     * couponCount : 0
     * giftCount : 0
     * doubleegral : 0
     */

    private String balance;
    private String couponCount;
    private String giftCount;
    private String integral;
    private String integralGrandAmount;
    private String integralThatDayGrandAmount;

    public void setIntegralGrandAmount(String integralGrandAmount) {
        this.integralGrandAmount = integralGrandAmount;
    }

    public void setIntegralThatDayGrandAmount(String integralThatDayGrandAmount) {
        this.integralThatDayGrandAmount = integralThatDayGrandAmount;
    }

    public String getIntegralGrandAmount() {
        return integralGrandAmount;
    }

    public String getIntegralThatDayGrandAmount() {
        return integralThatDayGrandAmount;
    }

    public String getBalance() {
        return StringUtil.getValue(balance);
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCouponCount() {
        return StringUtil.getValue(couponCount);
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    public String getGiftCount() {
        return StringUtil.getValue(giftCount);
    }

    public void setGiftCount(String giftCount) {
        this.giftCount = giftCount;
    }

    public String getIntegral() {
        return StringUtil.getValue(integral);
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
