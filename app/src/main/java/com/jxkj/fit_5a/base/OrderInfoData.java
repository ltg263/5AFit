package com.jxkj.fit_5a.base;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

public class OrderInfoData implements Serializable {

    /**
     * list : [{"id":50,"userId":80,"orderNo":"210107180451007600","amount":200,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":200,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 18:04:51","expireTime":"2021-01-07 18:34:51","deliveryTime":"","finishTime":"2021-01-07 18:04:51","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[{"id":46,"orderId":50,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2021-01-07 18:04:51","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":49,"userId":80,"orderNo":"210107180450006463","amount":200,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":200,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 18:04:50","expireTime":"2021-01-07 18:34:50","deliveryTime":"","finishTime":"2021-01-07 18:04:50","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[{"id":45,"orderId":49,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2021-01-07 18:04:50","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":48,"userId":80,"orderNo":"210107180448005715","amount":200,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":200,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 18:04:49","expireTime":"2021-01-07 18:34:49","deliveryTime":"","finishTime":"2021-01-07 18:04:49","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[{"id":44,"orderId":48,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2021-01-07 18:04:49","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":47,"userId":80,"orderNo":"210107180438004161","amount":200,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":200,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 18:04:39","expireTime":"2021-01-07 18:34:39","deliveryTime":"","finishTime":"2021-01-07 18:04:39","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[{"id":43,"orderId":47,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2021-01-07 18:04:39","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":46,"userId":80,"orderNo":"210107175343002138","amount":120,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":120,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 17:53:44","expireTime":"2021-01-07 18:23:44","deliveryTime":"","finishTime":"2021-01-07 17:53:44","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[{"id":42,"orderId":46,"type":0,"hasSku":0,"productId":3,"skuId":8,"imgUrl":"https://haide.nbqichen.com/haide/upload/887C27F0CA135ADCF20EF8686D1162D4.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":120,"realNum":0,"createTime":"2021-01-07 17:53:44","skuName":"颜色:黑色;容量:20ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":45,"userId":80,"orderNo":"210107174328001526","amount":120,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":120,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 17:43:29","expireTime":"2021-01-07 18:13:29","deliveryTime":"","finishTime":"2021-01-07 17:43:59","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[{"id":41,"orderId":45,"type":0,"hasSku":0,"productId":3,"skuId":8,"imgUrl":"https://haide.nbqichen.com/haide/upload/887C27F0CA135ADCF20EF8686D1162D4.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":120,"realNum":0,"createTime":"2021-01-07 17:43:43","skuName":"颜色:黑色;容量:20ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":41,"userId":80,"orderNo":"210107170900001930","amount":120,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":120,"realAmount":0,"totalNum":1,"remarks":"","payType":1,"payTime":"","deliveryType":1,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2021-01-07 17:09:00","expireTime":"2021-01-07 17:39:00","deliveryTime":"","finishTime":"","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"微信支付","orderTypeStr":"积分商品订单","productList":[],"regions":"广东省,广州市,荔湾区","location":"广东省,广州市,荔湾区-110112113114115","acceptName":"测试","mobile":"13111111111"},{"id":29,"userId":1,"orderNo":"200927165628002281","amount":200,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":200,"realAmount":20,"totalNum":2,"remarks":"","payType":0,"payTime":"","deliveryType":0,"closeTime":"","hasClear":0,"hasDel":0,"status":7,"createTime":"2020-09-27 16:56:28","expireTime":"2020-09-27 17:26:28","deliveryTime":"","finishTime":"","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已超时","payTypeStr":"","orderTypeStr":"积分商品订单","productList":[{"id":28,"orderId":29,"type":0,"hasSku":0,"productId":9,"skuId":0,"imgUrl":"https://haide.nbqichen.com/haide/upload/CD7CDFF7FB7C0BC5579AD6CEF6C285B4.jpg","name":"海德椭圆机E130","num":2,"price":10,"disPrice":12,"deductIntegral":100,"realNum":0,"createTime":"2020-09-27 16:56:28","skuName":"","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"浙江省 温州市 鹿城区","location":"浙江省 温州市 鹿城区鄞州信息科技孵化园408-409-410-411-412-412","acceptName":"狗不是真的人","mobile":"15168531988"},{"id":28,"userId":1,"orderNo":"200927165145001192","amount":300,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":300,"realAmount":30,"totalNum":3,"remarks":"","payType":0,"payTime":"","deliveryType":0,"closeTime":"","hasClear":0,"hasDel":0,"status":2,"createTime":"2020-09-27 16:51:45","expireTime":"2020-09-27 17:21:45","deliveryTime":"","finishTime":"2020-09-27 16:57:52","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已支付","payTypeStr":"","orderTypeStr":"积分商品订单","productList":[{"id":27,"orderId":28,"type":0,"hasSku":0,"productId":9,"skuId":0,"imgUrl":"https://haide.nbqichen.com/haide/upload/CD7CDFF7FB7C0BC5579AD6CEF6C285B4.jpg","name":"海德椭圆机E130","num":3,"price":10,"disPrice":12,"deductIntegral":100,"realNum":0,"createTime":"2020-09-27 16:51:46","skuName":"","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"浙江省 温州市 鹿城区","location":"浙江省 温州市 鹿城区鄞州信息科技孵化园408-409-410-411-412-412","acceptName":"狗不是真的人","mobile":"15168531988"},{"id":26,"userId":1,"orderNo":"200927090436007742","amount":600,"couponId":0,"couponAmount":0,"disCountamount":0,"deliveryAmount":0,"deductIntegral":600,"realAmount":480,"totalNum":3,"remarks":"测试备注","payType":0,"payTime":"","deliveryType":0,"closeTime":"","hasClear":0,"hasDel":0,"status":9,"createTime":"2020-09-27 09:04:37","expireTime":"2020-09-27 09:34:37","deliveryTime":"","finishTime":"2020-09-27 09:50:57","orderType":2,"hasExpediting":0,"explain":"","detail":null,"statusStr":"已申请售后","payTypeStr":"","orderTypeStr":"积分商品订单","productList":[{"id":25,"orderId":26,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":3,"price":160,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2020-09-27 09:04:37","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}],"regions":"浙江省 温州市 鹿城区","location":"浙江省 温州市 鹿城区鄞州信息科技孵化园408-409-410-411-412-412","acceptName":"狗不是真的人","mobile":"15168531988"}]
     * totalCount : 27
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
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
         * detail : null
         * statusStr : 已支付
         * payTypeStr : 微信支付
         * orderTypeStr : 积分商品订单
         * productList : [{"id":46,"orderId":50,"type":0,"hasSku":0,"productId":3,"skuId":4,"imgUrl":"https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png","name":"海德肌肉贴KT06","num":1,"price":0,"disPrice":300,"deductIntegral":200,"realNum":0,"createTime":"2021-01-07 18:04:51","skuName":"颜色:橘色;容量:10ml","sumAmount":0,"stock":0,"sumIntegral":0,"count":0}]
         * regions : 广东省,广州市,荔湾区
         * location : 广东省,广州市,荔湾区-110112113114115
         * acceptName : 测试
         * mobile : 13111111111
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
        private Object detail;
        private String statusStr;
        private String payTypeStr;
        private String orderTypeStr;
        private String regions;
        private String location;
        private String acceptName;
        private String mobile;
        private List<ProductListBean> productList;

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
            return deliveryAmount;
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

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
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

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean implements Serializable{
            /**
             * id : 46
             * orderId : 50
             * type : 0
             * hasSku : 0
             * productId : 3
             * skuId : 4
             * imgUrl : https://haide.nbqichen.com/haide/upload/C0D68C805ACAC73538A941E4CBD3B544.png
             * name : 海德肌肉贴KT06
             * num : 1
             * price : 0
             * disPrice : 300
             * deductIntegral : 200
             * realNum : 0
             * createTime : 2021-01-07 18:04:51
             * skuName : 颜色:橘色;容量:10ml
             * sumAmount : 0
             * stock : 0
             * sumIntegral : 0
             * count : 0
             */

            private String id;
            private String orderId;
            private String type;
            private String hasSku;
            private String productId;
            private String skuId;
            private String imgUrl;
            private String name;
            private String num;
            private String price;
            private String disPrice;
            private String deductIntegral;
            private String realNum;
            private String createTime;
            private String skuName;
            private String sumAmount;
            private String stock;
            private String sumIntegral;
            private String count;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHasSku() {
                return hasSku;
            }

            public void setHasSku(String hasSku) {
                this.hasSku = hasSku;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
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

            public String getPrice() {
                return StringUtil.getValue(price);
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDisPrice() {
                return disPrice;
            }

            public void setDisPrice(String disPrice) {
                this.disPrice = disPrice;
            }

            public String getDeductIntegral() {
                return StringUtil.getValue(deductIntegral);
            }

            public void setDeductIntegral(String deductIntegral) {
                this.deductIntegral = deductIntegral;
            }

            public String getRealNum() {
                return realNum;
            }

            public void setRealNum(String realNum) {
                this.realNum = realNum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getSkuName() {
                return skuName;
            }

            public void setSkuName(String skuName) {
                this.skuName = skuName;
            }

            public String getSumAmount() {
                return sumAmount;
            }

            public void setSumAmount(String sumAmount) {
                this.sumAmount = sumAmount;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getSumIntegral() {
                return sumIntegral;
            }

            public void setSumIntegral(String sumIntegral) {
                this.sumIntegral = sumIntegral;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
