package com.jxkj.fit_5a.entity;

import java.util.List;

public class CommentListBean {

    /**
     * list : [{"id":1,"userId":1,"orderId":20,"productId":3,"skuId":0,"skuText":"","score":100,"detail":"121233","imgs":"https://haide.nbqichen.com/haide/upload/83B6F11810C0DC88540741179EB131A2.jpg","status":1,"createTime":"2020-09-27 10:59:28","serviceScore":0,"deliveryScore":0,"nickName":"","avatar":""}]
     * totalCount : 1
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
         * id : 1
         * userId : 1
         * orderId : 20
         * productId : 3
         * skuId : 0
         * skuText :
         * score : 100
         * detail : 121233
         * imgs : https://haide.nbqichen.com/haide/upload/83B6F11810C0DC88540741179EB131A2.jpg
         * status : 1
         * createTime : 2020-09-27 10:59:28
         * serviceScore : 0
         * deliveryScore : 0
         * nickName :
         * avatar :
         */

        private String id;
        private String userId;
        private String orderId;
        private String productId;
        private String skuId;
        private String skuText;
        private String score;
        private String detail;
        private String imgs;
        private String status;
        private String createTime;
        private String serviceScore;
        private String deliveryScore;
        private String nickName;
        private String avatar;

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

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getSkuText() {
            return skuText;
        }

        public void setSkuText(String skuText) {
            this.skuText = skuText;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
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

        public String getServiceScore() {
            return serviceScore;
        }

        public void setServiceScore(String serviceScore) {
            this.serviceScore = serviceScore;
        }

        public String getDeliveryScore() {
            return deliveryScore;
        }

        public void setDeliveryScore(String deliveryScore) {
            this.deliveryScore = deliveryScore;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
