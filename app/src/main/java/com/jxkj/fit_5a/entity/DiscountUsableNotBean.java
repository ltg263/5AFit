package com.jxkj.fit_5a.entity;

import java.util.List;

public class DiscountUsableNotBean {

    /**
     * page : 1
     * pageSize : 10
     * total : 7
     * totalCount : 7
     * list : [{"id":23,"productId":0,"productBase":null,"type":1,"validityDays":24,"couponName":"满1000减200","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/jCP0orK69oxsbAIFQmlmKQ.jpg","remark":"","limitAmount":1000,"reliefAmount":200,"details":""},{"id":24,"productId":0,"productBase":null,"type":1,"validityDays":30,"couponName":"满500减50","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/0wgsT89W78OFxUIKQYpQQA.jpg","remark":"","limitAmount":500,"reliefAmount":50,"details":""},{"id":27,"productId":0,"productBase":null,"type":1,"validityDays":80,"couponName":"满300减30","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/Grstw9djEZRPb6ZgPuq3Q.jpg","remark":"","limitAmount":300,"reliefAmount":30,"details":""},{"id":25,"productId":0,"productBase":null,"type":1,"validityDays":50,"couponName":"满200减20","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/FdRC8qic7kD3MzYDnxEbmA.jpg","remark":"","limitAmount":200,"reliefAmount":20,"details":""},{"id":11,"productId":0,"productBase":null,"type":1,"validityDays":12,"couponName":"测试","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/SjAQct7GtqSQUOWylM15gw.png","remark":"","limitAmount":120,"reliefAmount":12,"details":""},{"id":4,"productId":0,"productBase":null,"type":1,"validityDays":-1,"couponName":"测试优惠券","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/SjAQct7GtqSQUOWylM15gw.png","remark":"","limitAmount":100,"reliefAmount":10,"details":""},{"id":26,"productId":0,"productBase":null,"type":1,"validityDays":12,"couponName":"满100减10","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/5gVfUK1cF1lZXeQBxtdbAg.jpg","remark":"","limitAmount":100,"reliefAmount":10,"details":""}]
     */

    private String page;
    private String pageSize;
    private String total;
    private String totalCount;
    private List<ListBean> list;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
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
         * id : 23
         * productId : 0
         * productBase : null
         * type : 1
         * validityDays : 24
         * couponName : 满1000减200
         * imgUrl : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/jCP0orK69oxsbAIFQmlmKQ.jpg
         * remark :
         * limitAmount : 1000
         * reliefAmount : 200
         * details :
         */

        private int id;
        private String productId;
        private Object productBase;
        private String type;
        private String validityDays;
        private String couponName;
        private String imgUrl;
        private String remark;
        private String limitAmount;
        private String reliefAmount;
        private String details;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Object getProductBase() {
            return productBase;
        }

        public void setProductBase(Object productBase) {
            this.productBase = productBase;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValidityDays() {
            return validityDays;
        }

        public void setValidityDays(String validityDays) {
            this.validityDays = validityDays;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLimitAmount() {
            return limitAmount;
        }

        public void setLimitAmount(String limitAmount) {
            this.limitAmount = limitAmount;
        }

        public String getReliefAmount() {
            return reliefAmount;
        }

        public void setReliefAmount(String reliefAmount) {
            this.reliefAmount = reliefAmount;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}
