package com.jxkj.fit_5a.base;

import java.util.List;

public class GiftListData {
    /**
     * list : [{"balance":0,"giftId":0,"imgUrl":"","name":"","realPrice":0,"receiveBalance":0,"type":0,"userId":0,"worth":0}]
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
         * balance : 0
         * giftId : 0
         * imgUrl :
         * name :
         * realPrice : 0
         * receiveBalance : 0
         * type : 0
         * userId : 0
         * worth : 0
         */

        private String balance;
        private String giftId;
        private String imgUrl;
        private String name;
        private String realPrice;
        private String receiveBalance;
        private String type;
        private String userId;
        private String worth;

        public String getBalance() {
            return balance.replace(".00","");
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
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

        public String getRealPrice() {
            return realPrice.replace(".00","");
        }

        public void setRealPrice(String realPrice) {
            this.realPrice = realPrice;
        }

        public String getReceiveBalance() {
            return receiveBalance;
        }

        public void setReceiveBalance(String receiveBalance) {
            this.receiveBalance = receiveBalance;
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
            return worth;
        }

        public void setWorth(String worth) {
            this.worth = worth;
        }
    }
}
