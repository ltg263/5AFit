package com.jxkj.fit_5a.entity;

import java.util.List;

public class ProductScListBean {

    /**
     * list : [{"id":2,"productId":34,"price":149,"integral":2000,"userId":355,"hasDel":0,"createTime":"2023-03-20 15:36:38","productName":"HEAD海德 智能呼啦圈 NT126","productImg":"https://oss.5afit.com/mall/product/cover/vDj7kYvEJof8nrueBA0nQ.jpg"},{"id":1,"productId":33,"price":128,"integral":1900,"userId":355,"hasDel":0,"createTime":"2023-03-20 15:33:45","productName":"HEAD海德 云际跳绳SR103B","productImg":"https://oss.5afit.com/mall/product/cover/Lu1aLGm0AxKeil4CDaNDOQ.jpg"}]
     * totalCount : 2
     */

    private String totalCount;
    private List<ListBean> list;

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
         * id : 2
         * productId : 34
         * price : 149
         * integral : 2000
         * userId : 355
         * hasDel : 0
         * createTime : 2023-03-20 15:36:38
         * productName : HEAD海德 智能呼啦圈 NT126
         * productImg : https://oss.5afit.com/mall/product/cover/vDj7kYvEJof8nrueBA0nQ.jpg
         */

        private String id;
        private String productId;
        private String price;
        private String integral;
        private String userId;
        private String hasDel;
        private String createTime;
        private String productName;
        private String productImg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }
    }
}
