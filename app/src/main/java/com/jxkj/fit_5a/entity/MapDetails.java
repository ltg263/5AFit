package com.jxkj.fit_5a.entity;

import java.util.List;

public class MapDetails {

    /**
     * id : 15
     * name : 经典地图
     * imgUrl : 123
     * minLevel : 0
     * distance : 10945
     * info : [[116.385056,39.891994],[116.385415,39.895125],[116.382856,39.897203],[116.382529,39.900465],[116.378812,39.903036],[116.380244,39.905739],[116.38019,39.912066],[116.38901,39.920045],[116.403322,39.920255],[116.413622,39.917214],[116.420831,39.910486],[116.420059,39.906529],[116.417055,39.902836],[116.414565,39.899537],[116.410703,39.897555],[116.402292,39.892353],[116.389846,39.891365]]
     * boxs : [{"id":"1","rewardList":[{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}],"distance":1000,"latlng":[116.38255801979953,39.900175511969294],"having":false,"userBoxLog":null}]
     */

    private String id;
    private String name;
    private String imgUrl;
    private int minLevel;
    private int distance;
    private List<List<Double>> info;
    private List<BoxsBean> boxs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<List<Double>> getInfo() {
        return info;
    }

    public void setInfo(List<List<Double>> info) {
        this.info = info;
    }

    public List<BoxsBean> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<BoxsBean> boxs) {
        this.boxs = boxs;
    }

    public static class BoxsBean {
        /**
         * id : 1
         * rewardList : [{"id":1,"name":"勋章奖励1","imgUrl":"11","explain":"1234","type":2,"detail":"3","rate":0.3}]
         * distance : 1000
         * latlng : [116.38255801979953,39.900175511969294]
         * having : false
         * userBoxLog : null
         */

        private String id;
        private int distance;
        private boolean having;
        private Object userBoxLog;
        private List<RewardListBean> rewardList;
        private List<Double> latlng;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public boolean isHaving() {
            return having;
        }

        public void setHaving(boolean having) {
            this.having = having;
        }

        public Object getUserBoxLog() {
            return userBoxLog;
        }

        public void setUserBoxLog(Object userBoxLog) {
            this.userBoxLog = userBoxLog;
        }

        public List<RewardListBean> getRewardList() {
            return rewardList;
        }

        public void setRewardList(List<RewardListBean> rewardList) {
            this.rewardList = rewardList;
        }

        public List<Double> getLatlng() {
            return latlng;
        }

        public void setLatlng(List<Double> latlng) {
            this.latlng = latlng;
        }

        public static class RewardListBean {
            /**
             * id : 1
             * name : 勋章奖励1
             * imgUrl : 11
             * explain : 1234
             * type : 2
             * detail : 3
             * rate : 0.3
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
}
