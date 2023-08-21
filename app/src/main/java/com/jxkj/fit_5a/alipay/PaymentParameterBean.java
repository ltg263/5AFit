package com.jxkj.fit_5a.alipay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Function:PaymentParameterBean
 * Author  :@author BuJie
 * Date:2018/7/31
 */

public class PaymentParameterBean implements Parcelable {

    private String aliAppId;
    private String rsa2_private;
    private String rsa_private;

    private String wxAppid;
    private String partnerId;
    private String prepayId;
    private String nonceStr;
    private String timeStamp;
    private String packageValue;
    private String sign;
    private String extData;
    private String orderInfo;

    public PaymentParameterBean() {

    }

    protected PaymentParameterBean(Parcel in) {
        aliAppId = in.readString();
        rsa2_private = in.readString();
        rsa_private = in.readString();
        wxAppid = in.readString();
        partnerId = in.readString();
        prepayId = in.readString();
        nonceStr = in.readString();
        timeStamp = in.readString();
        packageValue = in.readString();
        sign = in.readString();
        extData = in.readString();
        orderInfo = in.readString();
    }

    public static final Creator<PaymentParameterBean> CREATOR = new Creator<PaymentParameterBean>() {
        @Override
        public PaymentParameterBean createFromParcel(Parcel in) {
            return new PaymentParameterBean(in);
        }

        @Override
        public PaymentParameterBean[] newArray(int size) {
            return new PaymentParameterBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aliAppId);
        dest.writeString(rsa2_private);
        dest.writeString(rsa_private);
        dest.writeString(wxAppid);
        dest.writeString(partnerId);
        dest.writeString(prepayId);
        dest.writeString(nonceStr);
        dest.writeString(timeStamp);
        dest.writeString(packageValue);
        dest.writeString(sign);
        dest.writeString(extData);
        dest.writeString(orderInfo);
    }

    public String getAliAppId() {
        return aliAppId;
    }

    public void setAliAppId(String aliAppId) {
        this.aliAppId = aliAppId;
    }

    public String getRsa2_private() {
        return rsa2_private;
    }

    public void setRsa2_private(String rsa2_private) {
        this.rsa2_private = rsa2_private;
    }

    public String getRsa_private() {
        return rsa_private;
    }

    public void setRsa_private(String rsa_private) {
        this.rsa_private = rsa_private;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
