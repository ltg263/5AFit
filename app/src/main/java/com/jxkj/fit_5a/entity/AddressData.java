package com.jxkj.fit_5a.entity;

import java.io.Serializable;

public class AddressData implements Serializable {

    /**
     * acceptName : 呆
     * cityId : 330200
     * createTime : 2021-04-09 09:27:42
     * districtId : 330203
     * hasDel : 0
     * id : 23
     * isDefault : 1
     * location : 信息科技孵化园408
     * mobile : 15168531988
     * provinceId : 330000
     * regions : 浙江省 宁波市 海曙区
     * userId : 145
     */

    private String acceptName;
    private String cityId;
    private String createTime;
    private String districtId;
    private String hasDel;
    private String id;
    private String isDefault;
    private String location;
    private String mobile;
    private String provinceId;
    private String regions;
    private String userId;

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getHasDel() {
        return hasDel;
    }

    public void setHasDel(String hasDel) {
        this.hasDel = hasDel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
