package com.jxkj.fit_5a.entity;

import java.util.List;

public class ProductBannerList {

    /**
     * list : [{"id":2,"imgUrl":"https://t7.baidu.com/it/u=2204874366,1447142724&fm=193&f=GIF","status":1,"hasDel":0,"createTime":"2021-08-27 17:48:04"},{"id":1,"imgUrl":"https://t7.baidu.com/it/u=2204874366,1447142724&fm=193&f=GIF","status":1,"hasDel":0,"createTime":"2021-08-27 17:40:38"}]
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
         * imgUrl : https://t7.baidu.com/it/u=2204874366,1447142724&fm=193&f=GIF
         * status : 1
         * hasDel : 0
         * createTime : 2021-08-27 17:48:04
         */

        private int type;
        private String id;
        private String imgUrl;
        private String status;
        private String hasDel;
        private String productId;
        private String link;
        private String createTime;

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductId() {
            return productId;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    }
}
