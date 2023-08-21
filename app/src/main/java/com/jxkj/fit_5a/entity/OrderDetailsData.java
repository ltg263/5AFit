package com.jxkj.fit_5a.entity;

import com.jxkj.fit_5a.base.OrderInfoData;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

public class OrderDetailsData {

    /**
     * id : 50
     * userId : 80
     * orderNo : 210107180451007600
     * amount : 200
     * couponId : 0
     * couponAmount : 0
     * disCountamount : 0
     * deliveryAmount : 0
     * deductIntegral : 200
     * realAmount : 0
     * totalNum : 1
     * remarks :
     * payType : 1
     * payTime :
     * deliveryType : 1
     * closeTime :
     * hasClear : 0
     * hasDel : 0
     * status : 2
     * createTime : 2021-01-07 18:04:51
     * expireTime : 2021-01-07 18:34:51
     * deliveryTime :
     * finishTime : 2021-01-07 18:04:51
     * orderType : 2
     * hasExpediting : 0
     * explain :
     * detail : {"orderId":50,"provinceId":0,"cityId":0,"districtId":0,"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111","remark":"","logistics":"","logisticsNo":""}
     * statusStr : 已支付
     * payTypeStr : 微信支付
     * orderTypeStr : 积分商品订单
     * productList : [{"id":46,"orderId":50,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2021-01-07 18:04:51","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}]
     * regions :
     * location :
     * acceptName :
     * mobile :
     */

    private String id;
    private String userId;
    private String orderNo;
    private String amount;
    private String couponId;
    private String couponAmount;
    private String disCountamount;
    private String deliveryAmount;
    private String deductIntegral;
    private String realAmount;
    private String totalNum;
    private String remarks;
    private String payType;
    private String payTime;
    private String deliveryType;
    private String closeTime;
    private String hasClear;
    private String hasDel;
    private String status;
    private String createTime;
    private String expireTime;
    private String deliveryTime;
    private String finishTime;
    private String orderType;
    private String hasExpediting;
    private String explain;
    private DetailBean detail;
    private String statusStr;
    private String payTypeStr;
    private String orderTypeStr;
    private String regions;
    private String location;
    private String acceptName;
    private String mobile;
    private List<OrderInfoData.ListBean.ProductListBean> productList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getDisCountamount() {
        return disCountamount;
    }

    public void setDisCountamount(String disCountamount) {
        this.disCountamount = disCountamount;
    }

    public String getDeliveryAmount() {
        return StringUtil.getValue(deliveryAmount);
    }

    public void setDeliveryAmount(String deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getDeductIntegral() {
        return StringUtil.getValue(deductIntegral);
    }

    public void setDeductIntegral(String deductIntegral) {
        this.deductIntegral = deductIntegral;
    }

    public String getRealAmount() {
        return StringUtil.getValue(realAmount);
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getHasClear() {
        return hasClear;
    }

    public void setHasClear(String hasClear) {
        this.hasClear = hasClear;
    }

    public String getHasDel() {
        return hasDel;
    }

    public void setHasDel(String hasDel) {
        this.hasDel = hasDel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getHasExpediting() {
        return hasExpediting;
    }

    public void setHasExpediting(String hasExpediting) {
        this.hasExpediting = hasExpediting;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderTypeStr() {
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<OrderInfoData.ListBean.ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderInfoData.ListBean.ProductListBean> productList) {
        this.productList = productList;
    }

    public static class DetailBean {
        /**
         * orderId : 50
         * provinceId : 0
         * cityId : 0
         * districtId : 0
         * regions : 广东省,广州市,荔湾区
         * location : 广东省,广州市,荔湾区-110112113114115
         * acceptName : 测试
         * mobile : 13111111111
         * remark :
         * logistics :
         * logisticsNo :
         */

        private String orderId;
        private String provinceId;
        private String cityId;
        private String districtId;
        private String regions;
        private String location;
        private String acceptName;
        private String mobile;
        private String remark;
        private String logistics;
        private String logisticsNo;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getRegions() {
            return regions;
        }

        public void setRegions(String regions) {
            this.regions = regions;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAcceptName() {
            return acceptName;
        }

        public void setAcceptName(String acceptName) {
            this.acceptName = acceptName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLogistics() {
            return logistics;
        }

        public void setLogistics(String logistics) {
            this.logistics = logistics;
        }

        public String getLogisticsNo() {
            return logisticsNo;
        }

        public void setLogisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
        }
    }
}
