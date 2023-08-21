package com.jxkj.fit_5a.base;

import java.io.Serializable;

/**
 * author : LiuJie
 * date   : 2020/6/1014:24
 */
public class PatientListData implements Serializable {
    /**
     * id	客户id
     * portraitUri	头像
     * nickname	昵称
     * realName	真实姓名
     * gender	性别：1，男；2，女
     * address	地址
     * provinceId	省id
     * cityId	市id
     * districtId	区id
     * conditionName	病情名称
     * billingSum	总开单量
     */
    private String id = "6";
    private String portraitUri;
    private String nickname;
    private String realName;
    private int gender;
    private String address = "";
    private String conditionName = "";
    private String billingSum;
    private String age = "";

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getBillingSum() {
        return billingSum;
    }

    public void setBillingSum(String billingSum) {
        this.billingSum = billingSum;
    }
}
