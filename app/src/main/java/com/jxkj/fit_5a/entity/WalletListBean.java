package com.jxkj.fit_5a.entity;

import java.util.List;

public class WalletListBean {

    /**
     * list : [{"id":1,"walletId":"1_1","userId":1,"type":1,"inOrOut":1,"scene":5,"aboutId":0,"amount":100,"status":2,"remark":"用户冻结金额入账100","createTime":"2020-09-02 10:45:10","thawTime":"2020-09-03 10:45:10","paymentTime":"2020-09-03 10:31:40"},{"id":18,"walletId":"1_1","userId":1,"type":1,"inOrOut":1,"scene":5,"aboutId":0,"amount":100,"status":2,"remark":"用户冻结金额入账100","createTime":"2020-09-02 11:25:53","thawTime":"2020-09-03 11:25:53","paymentTime":"2020-09-03 10:31:40"}]
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
         * id : 1
         * walletId : 1_1
         * userId : 1
         * type : 1
         * inOrOut : 1
         * scene : 5
         * aboutId : 0
         * amount : 100
         * status : 2
         * remark : 用户冻结金额入账100
         * createTime : 2020-09-02 10:45:10
         * thawTime : 2020-09-03 10:45:10
         * paymentTime : 2020-09-03 10:31:40
         */

        private String id;
        private String walletId;
        private String userId;
        private String type;
        private String inOrOut;
        private String scene;
        private String aboutId;
        private String amount;
        private String status;
        private String remark;
        private String createTime;
        private String thawTime;
        private String paymentTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWalletId() {
            return walletId;
        }

        public void setWalletId(String walletId) {
            this.walletId = walletId;
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

        public String getInOrOut() {
            return inOrOut;
        }

        public void setInOrOut(String inOrOut) {
            this.inOrOut = inOrOut;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public String getAboutId() {
            return aboutId;
        }

        public void setAboutId(String aboutId) {
            this.aboutId = aboutId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getThawTime() {
            return thawTime;
        }

        public void setThawTime(String thawTime) {
            this.thawTime = thawTime;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }
    }
}
