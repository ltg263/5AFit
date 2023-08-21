package com.jxkj.fit_5a.entity;

import java.util.List;

public class NotObtainedBean {

    /**
     * page : 1
     * pageSize : 10
     * total : 2
     * totalCount : 2
     * list : [{"id":34,"productId":10,"productBase":{"id":10,"name":"运动手环","subTitle":"123123","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/product/GOqzL780v9183NPmjoAEQ.jpg","price":50,"disPrice":1200,"deductIntegral":100},"type":2,"validityDays":100,"couponName":"15元运动手环抵用券","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/AiUDp1FAjZx5UppOLDWiw.jpg","remark":"","limitAmount":0,"reliefAmount":15,"details":""},{"id":33,"productId":10,"productBase":{"id":10,"name":"运动手环","subTitle":"123123","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/product/GOqzL780v9183NPmjoAEQ.jpg","price":50,"disPrice":1200,"deductIntegral":100},"type":2,"validityDays":100,"couponName":"10元运动手环抵用券","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/AiUDp1FAjZx5UppOLDWiw.jpg","remark":"","limitAmount":0,"reliefAmount":10,"details":""}]
     */

    private int page;
    private int pageSize;
    private int total;
    private int totalCount;
    private List<ListBean> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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
         * id : 34
         * productId : 10
         * productBase : {"id":10,"name":"运动手环","subTitle":"123123","imgUrl":"http://5a-fit.oss-cn-hangzhou.aliyuncs.com/product/GOqzL780v9183NPmjoAEQ.jpg","price":50,"disPrice":1200,"deductIntegral":100}
         * type : 2
         * validityDays : 100
         * couponName : 15元运动手环抵用券
         * imgUrl : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/portrait/AiUDp1FAjZx5UppOLDWiw.jpg
         * remark :
         * limitAmount : 0
         * reliefAmount : 15
         * details :
         */

        private int id;
        private int productId;
        private ProductBaseBean productBase;
        private int type;
        private int validityDays;
        private String couponName;
        private String imgUrl;
        private String remark;
        private int limitAmount;
        private int reliefAmount;
        private String details;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public ProductBaseBean getProductBase() {
            return productBase;
        }

        public void setProductBase(ProductBaseBean productBase) {
            this.productBase = productBase;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getValidityDays() {
            return validityDays;
        }

        public void setValidityDays(int validityDays) {
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

        public int getLimitAmount() {
            return limitAmount;
        }

        public void setLimitAmount(int limitAmount) {
            this.limitAmount = limitAmount;
        }

        public int getReliefAmount() {
            return reliefAmount;
        }

        public void setReliefAmount(int reliefAmount) {
            this.reliefAmount = reliefAmount;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public static class ProductBaseBean {
            /**
             * id : 10
             * name : 运动手环
             * subTitle : 123123
             * imgUrl : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/product/GOqzL780v9183NPmjoAEQ.jpg
             * price : 50
             * disPrice : 1200
             * deductIntegral : 100
             */

            private int id;
            private String name;
            private String subTitle;
            private String imgUrl;
            private int price;
            private int disPrice;
            private int deductIntegral;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getDisPrice() {
                return disPrice;
            }

            public void setDisPrice(int disPrice) {
                this.disPrice = disPrice;
            }

            public int getDeductIntegral() {
                return deductIntegral;
            }

            public void setDeductIntegral(int deductIntegral) {
                this.deductIntegral = deductIntegral;
            }
        }
    }
}
