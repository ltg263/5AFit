package com.jxkj.fit_5a.entity;

public class BoxReceiveBean {

    /**
     * id : 617920aad8d9475114863f12
     * userId : 96
     * mapId : 61357290d8d94725ea735f96
     * boxId : 61764a44d8d9472944920fc5
     * datestr : 20211027
     * reward : {"id":7,"name":"奖励5积分","imgUrl":"","explain":"","type":1,"detail":"{\"incrementValue\":5,\"initialValue\":5,\"maxValue\":5}","rate":0.15}
     * createTime : 2021-10-27 17:49:30
     */

    private String id;
    private int userId;
    private String mapId;
    private String boxId;
    private int datestr;
    private RewardBean reward;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public int getDatestr() {
        return datestr;
    }

    public void setDatestr(int datestr) {
        this.datestr = datestr;
    }

    public RewardBean getReward() {
        return reward;
    }

    public void setReward(RewardBean reward) {
        this.reward = reward;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static class RewardBean {
        /**
         * id : 7
         * name : 奖励5积分
         * imgUrl :
         * explain :
         * type : 1
         * detail : {"incrementValue":5,"initialValue":5,"maxValue":5}
         * rate : 0.15
         */

        private int id;
        private String name;
        private String imgUrl;
        private String explain;
        private int type;
        private String detail;
        private double rate;

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }
}
