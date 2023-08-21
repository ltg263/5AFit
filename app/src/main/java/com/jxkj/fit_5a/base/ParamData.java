package com.jxkj.fit_5a.base;

import com.google.gson.annotations.SerializedName;

/**
 * author : LiuJie
 * date   : 2020/6/1510:37
 */
public class ParamData {

    /**
     * package : Sign=WXPay
     * packageValue : Sign=WXPay
     * appid : wxdc42a7cf4a99be02
     * sign : 47212C8605D95C5C384A4796863AF891
     * partnerid : 1585233261
     * prepayid : wx14153109142037c00f10d98309dc810000
     * noncestr : Viquyv4BvdqX5WJi6Ky9oh6DewDfvKgD
     * timestamp : 1618385469
     */

    @SerializedName("package")
    private String packageX;
    private String packageValue;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
