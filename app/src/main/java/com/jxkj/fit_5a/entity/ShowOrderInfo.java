package com.jxkj.fit_5a.entity;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

public class ShowOrderInfo {

    /**
     * balance : 0
     * discount : 0
     * discountAmount : 0
     * groupId : 0
     * integralFlag : 0
     * integralRate : 0
     * orderFlag : 0
     * orderProducts : [{"count":0,"createTime":"","deductIntegral":0,"disPrice":0,"hasSku":0,"id":0,"imgUrl":"","name":"","num":0,"orderId":0,"price":0,"productId":0,"realNum":0,"skuId":0,"skuName":"","stock":0,"sumAmount":0,"sumIntegral":0,"type":0}]
     * orderType : 0
     * realAmount : 0
     * redList : [{"couponName":"","createTime":"","details":"","expireDate":"","id":0,"imgUrl":"","limitAmount":0,"productBase":{"deductIntegral":0,"disPrice":0,"id":0,"imgUrl":"","name":"","price":0,"subTitle":""},"productId":0,"reliefAmount":0,"remark":"","source":0,"type":0,"userId":0}]
     * remark :
     * totalAmount : 0
     * totalDelivery : 0
     * totalIntegral : 0
     * totalQuantity : 0
     * useableIntegral : 0
     * userAddress : {"acceptName":"","cityId":0,"createTime":"","districtId":0,"hasDel":0,"id":0,"isDefult":0,"location":"","mobile":"","provinceId":0,"regions":"","userId":0}
     * userId : 0
     */

    private String balance;
    private String discount;
    private String discountAmount;
    private String groupId;
    private String integralFlag;
    private String integralRate;
    private String orderFlag;
    private String orderType;
    private String realAmount;
    private String remark;
    private String totalAmount;
    private String totalDelivery;
    private String totalIntegral;
    private String totalQuantity;
    private String useableIntegral;
    private UserAddressBean userAddress;
    private String userId;
    private List<OrderProductsBean> orderProducts;
    private List<RedListBean> redList;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIntegralFlag() {
        return integralFlag;
    }

    public void setIntegralFlag(String integralFlag) {
        this.integralFlag = integralFlag;
    }

    public String getIntegralRate() {
        return integralRate;
    }

    public void setIntegralRate(String integralRate) {
        this.integralRate = integralRate;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRealAmount() {
        return StringUtil.getValue(realAmount);
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalDelivery() {
        return totalDelivery;
    }

    public void setTotalDelivery(String totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public String getTotalIntegral() {
        return StringUtil.getValue(totalIntegral);
    }

    public void setTotalIntegral(String totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getUseableIntegral() {
        return useableIntegral;
    }

    public void setUseableIntegral(String useableIntegral) {
        this.useableIntegral = useableIntegral;
    }

    public UserAddressBean getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressBean userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderProductsBean> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductsBean> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<RedListBean> getRedList() {
        return redList;
    }

    public void setRedList(List<RedListBean> redList) {
        this.redList = redList;
    }

    public static class UserAddressBean {
        /**
         * acceptName :
         * cityId : 0
         * createTime :
         * districtId : 0
         * hasDel : 0
         * id : 0
         * isDefult : 0
         * location :
         * mobile :
         * provinceId : 0
         * regions :
         * userId : 0
         */

        private String acceptName;
        private String cityId;
        private String createTime;
        private String districtId;
        private String hasDel;
        private String id;
        private String isDefult;
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

        public String getIsDefult() {
            return isDefult;
        }

        public void setIsDefult(String isDefult) {
            this.isDefult = isDefult;
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

    public static class OrderProductsBean {
        /**
         * count : 0
         * createTime :
         * deductIntegral : 0
         * disPrice : 0
         * hasSku : 0
         * id : 0
         * imgUrl :
         * name :
         * num : 0
         * orderId : 0
         * price : 0
         * productId : 0
         * realNum : 0
         * skuId : 0
         * skuName :
         * stock : 0
         * sumAmount : 0
         * sumIntegral : 0
         * type : 0
         */

        private String count;
        private String createTime;
        private String deductIntegral;
        private String disPrice;
        private String hasSku;
        private String id;
        private String imgUrl;
        private String name;
        private String num;
        private String orderId;
        private String price;
        private String productId;
        private String realNum;
        private String skuId;
        private String skuName;
        private String stock;
        private String sumAmount;
        private String sumIntegral;
        private String type;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeductIntegral() {
            return StringUtil.getValue(deductIntegral);
        }

        public void setDeductIntegral(String deductIntegral) {
            this.deductIntegral = deductIntegral;
        }

        public String getDisPrice() {
            return disPrice;
        }

        public void setDisPrice(String disPrice) {
            this.disPrice = disPrice;
        }

        public String getHasSku() {
            return hasSku;
        }

        public void setHasSku(String hasSku) {
            this.hasSku = hasSku;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPrice() {
            return StringUtil.getValue(price);
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getRealNum() {
            return realNum;
        }

        public void setRealNum(String realNum) {
            this.realNum = realNum;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSumAmount() {
            return sumAmount;
        }

        public void setSumAmount(String sumAmount) {
            this.sumAmount = sumAmount;
        }

        public String getSumIntegral() {
            return sumIntegral;
        }

        public void setSumIntegral(String sumIntegral) {
            this.sumIntegral = sumIntegral;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class RedListBean {
        /**
         * couponName :
         * createTime :
         * details :
         * expireDate :
         * id : 0
         * imgUrl :
         * limitAmount : 0
         * productBase : {"deductIntegral":0,"disPrice":0,"id":0,"imgUrl":"","name":"","price":0,"subTitle":""}
         * productId : 0
         * reliefAmount : 0
         * remark :
         * source : 0
         * type : 0
         * userId : 0
         */

        private String couponName;
        private String createTime;
        private String details;
        private String expireDate;
        private String id;
        private String imgUrl;
        private String limitAmount;
        private ProductBaseBean productBase;
        private String productId;
        private String reliefAmount;
        private String remark;
        private String source;
        private String type;
        private String userId;

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLimitAmount() {
            return limitAmount;
        }

        public void setLimitAmount(String limitAmount) {
            this.limitAmount = limitAmount;
        }

        public ProductBaseBean getProductBase() {
            return productBase;
        }

        public void setProductBase(ProductBaseBean productBase) {
            this.productBase = productBase;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getReliefAmount() {
            return reliefAmount;
        }

        public void setReliefAmount(String reliefAmount) {
            this.reliefAmount = reliefAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static class ProductBaseBean {
            /**
             * deductIntegral : 0
             * disPrice : 0
             * id : 0
             * imgUrl :
             * name :
             * price : 0
             * subTitle :
             */

            private String deductIntegral;
            private String disPrice;
            private String id;
            private String imgUrl;
            private String name;
            private String price;
            private String subTitle;

            public String getDeductIntegral() {
                return deductIntegral;
            }

            public void setDeductIntegral(String deductIntegral) {
                this.deductIntegral = deductIntegral;
            }

            public String getDisPrice() {
                return disPrice;
            }

            public void setDisPrice(String disPrice) {
                this.disPrice = disPrice;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }
        }
    }
}
