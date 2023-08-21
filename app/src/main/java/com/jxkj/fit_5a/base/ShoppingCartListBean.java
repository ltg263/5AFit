package com.jxkj.fit_5a.base;

import java.util.List;

public class ShoppingCartListBean {

    /**
     * list : [{"id":7,"userId":355,"type":0,"productId":34,"skuId":70,"num":3,"createTime":"2023-03-21 14:14:08","salePrice":149,"saleIntegral":2000,"productName":"HEAD海德 智能呼啦圈 NT126","price":149,"imgUrl":"https://oss.5afit.com/mall/product/sku/cover/7KxjfkbGbMrfrVlHI9yQGg.jpg","skuName":"规格:白色"}]
     * totalCount : 1
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
         * id : 7
         * userId : 355
         * type : 0
         * productId : 34
         * skuId : 70
         * num : 3
         * createTime : 2023-03-21 14:14:08
         * salePrice : 149
         * saleIntegral : 2000
         * productName : HEAD海德 智能呼啦圈 NT126
         * price : 149
         * imgUrl : https://oss.5afit.com/mall/product/sku/cover/7KxjfkbGbMrfrVlHI9yQGg.jpg
         * skuName : 规格:白色
         */

        private boolean selected;
        private String id;
        private String userId;
        private String type;
        private String productId;
        private String skuId;
        private String num;
        private String createTime;
        private String salePrice;
        private String saleIntegral;
        private String productName;
        private String price;
        private String imgUrl;
        private String skuName;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public String getSaleIntegral() {
            return saleIntegral;
        }

        public void setSaleIntegral(String saleIntegral) {
            this.saleIntegral = saleIntegral;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }
    }
}
    
