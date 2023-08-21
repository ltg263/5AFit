package com.jxkj.fit_5a.base;

import java.util.List;

public class GiftLogListData {

    /**
     * list : [{"amount":0,"avatar":"","count":0,"createTime":"","giftId":0,"id":0,"imgUrl":"","name":"","nickName":"","realPrice":0,"scoreType":0,"toUserId":0,"type":0,"userId":0,"worth":0}]
     * totalCount : 0
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
         * amount : 0
         * avatar :
         * count : 0
         * createTime :
         * giftId : 0
         * id : 0
         * imgUrl :
         * name :
         * nickName :
         * realPrice : 0
         * scoreType : 0
         * toUserId : 0
         * type : 0
         * userId : 0
         * worth : 0
         */

        private String amount;
        private String avatar;
        private String count;
        private String createTime;
        private String giftId;
        private String id;
        private String imgUrl;
        private String name;
        private String nickName;
        private String realPrice;
        private String scoreType;
        private String toUserId;
        private String type;
        private String userId;
        private String worth;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

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

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRealPrice() {
            return realPrice.replace(".00","");
        }

        public void setRealPrice(String realPrice) {
            this.realPrice = realPrice;
        }

        public String getScoreType() {
            return scoreType;
        }

        public void setScoreType(String scoreType) {
            this.scoreType = scoreType;
        }

        public String getToUserId() {
            return toUserId;
        }

        public void setToUserId(String toUserId) {
            this.toUserId = toUserId;
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

        public String getWorth() {
            return worth.replace(".00","");
        }

        public void setWorth(String worth) {
            this.worth = worth;
        }
    }
}
