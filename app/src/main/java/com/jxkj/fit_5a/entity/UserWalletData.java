package com.jxkj.fit_5a.entity;

public class UserWalletData {

    /**
     * userId : 96
     * balance : {"id":"96_1","type":1,"totalAmount":0,"balance":0,"freeAmount":0,"grandAmount":0,"thatDayGrandAmount":0}
     * integral : {"id":"96_2","type":2,"totalAmount":40,"balance":40,"freeAmount":0,"grandAmount":60,"thatDayGrandAmount":20}
     * encourage : {"id":"96_3","type":3,"totalAmount":0,"balance":0,"freeAmount":0,"grandAmount":0,"thatDayGrandAmount":0}
     */

    private int userId;
    private BalanceBean balance;
    private BalanceBean integral;
    private BalanceBean encourage;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BalanceBean getBalance() {
        return balance;
    }

    public void setBalance(BalanceBean balance) {
        this.balance = balance;
    }

    public BalanceBean getIntegral() {
        return integral;
    }

    public void setIntegral(BalanceBean integral) {
        this.integral = integral;
    }

    public BalanceBean getEncourage() {
        return encourage;
    }

    public void setEncourage(BalanceBean encourage) {
        this.encourage = encourage;
    }

    public static class BalanceBean {
        /**
         * id : 96_1
         * type : 1
         * totalAmount : 0
         * balance : 0
         * freeAmount : 0
         * grandAmount : 0
         * thatDayGrandAmount : 0
         */

        private String id;
        private String type;
        private String totalAmount;
        private String balance;
        private String freeAmount;
        private String grandAmount;
        private String thatDayGrandAmount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFreeAmount() {
            return freeAmount;
        }

        public void setFreeAmount(String freeAmount) {
            this.freeAmount = freeAmount;
        }

        public String getGrandAmount() {
            return grandAmount;
        }

        public void setGrandAmount(String grandAmount) {
            this.grandAmount = grandAmount;
        }

        public String getThatDayGrandAmount() {
            return thatDayGrandAmount;
        }

        public void setThatDayGrandAmount(String thatDayGrandAmount) {
            this.thatDayGrandAmount = thatDayGrandAmount;
        }
    }
}
