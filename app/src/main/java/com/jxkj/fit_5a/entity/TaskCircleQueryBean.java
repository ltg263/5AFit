package com.jxkj.fit_5a.entity;

import java.util.List;

public class TaskCircleQueryBean {

    /**
     * list : [{"id":14,"deviceType":5,"deviceTypeStr":"跑步机","paramId":9,"paramName":"运动距离","unit":"KM","target":5,"maxCycle":1,"minCycle":1,"rewardId":3,"rewardDTO":{"id":3,"name":"积分10递增（max-100）","imgUrl":"","explain":"","type":1,"detail":"{\"incrementValue\":10,\"initialValue\":10,\"maxValue\":40}","hasDel":0,"status":1},"expireTime":"2021-07-31 00:00:00"}]
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
         * id : 14
         * deviceType : 5
         * deviceTypeStr : 跑步机
         * paramId : 9
         * paramName : 运动距离
         * unit : KM
         * target : 5
         * maxCycle : 1
         * minCycle : 1
         * rewardId : 3
         * rewardDTO : {"id":3,"name":"积分10递增（max-100）","imgUrl":"","explain":"","type":1,"detail":"{\"incrementValue\":10,\"initialValue\":10,\"maxValue\":40}","hasDel":0,"status":1}
         * expireTime : 2021-07-31 00:00:00
         */

        private int id;
        private int deviceType;
        private String deviceTypeStr;
        private int paramId;
        private String paramName;
        private String unit;
        private int target;
        private int maxCycle;
        private int minCycle;
        private int rewardId;
        private RewardDTOBean rewardDTO;
        private String expireTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceTypeStr() {
            return deviceTypeStr;
        }

        public void setDeviceTypeStr(String deviceTypeStr) {
            this.deviceTypeStr = deviceTypeStr;
        }

        public int getParamId() {
            return paramId;
        }

        public void setParamId(int paramId) {
            this.paramId = paramId;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public int getMaxCycle() {
            return maxCycle;
        }

        public void setMaxCycle(int maxCycle) {
            this.maxCycle = maxCycle;
        }

        public int getMinCycle() {
            return minCycle;
        }

        public void setMinCycle(int minCycle) {
            this.minCycle = minCycle;
        }

        public int getRewardId() {
            return rewardId;
        }

        public void setRewardId(int rewardId) {
            this.rewardId = rewardId;
        }

        public RewardDTOBean getRewardDTO() {
            return rewardDTO;
        }

        public void setRewardDTO(RewardDTOBean rewardDTO) {
            this.rewardDTO = rewardDTO;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public static class RewardDTOBean {
            /**
             * id : 3
             * name : 积分10递增（max-100）
             * imgUrl :
             * explain :
             * type : 1
             * detail : {"incrementValue":10,"initialValue":10,"maxValue":40}
             * hasDel : 0
             * status : 1
             */

            private int id;
            private String name;
            private String imgUrl;
            private String explain;
            private int type;
            private String detail;
            private int hasDel;
            private int status;

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

            public int getHasDel() {
                return hasDel;
            }

            public void setHasDel(int hasDel) {
                this.hasDel = hasDel;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}

