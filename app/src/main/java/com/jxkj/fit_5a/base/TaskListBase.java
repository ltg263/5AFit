package com.jxkj.fit_5a.base;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

public class TaskListBase {

    /**
     * list : [{"count":0,"curCount":0,"curRound":0,"cycle":0,"expireTime":"","explain":"","finishTime":"","hasDisplay":0,"id":"","img":"","name":"","resetTime":"","resetType":0,"rewards":[{"detail":"","explain":"","hasDel":0,"id":0,"imgUrl":"","name":"","status":0,"type":0}],"roundCount":0,"speeds":[{"id":0,"paramId":0,"paramName":"","round":0,"speed":0,"status":0,"target":0,"unit":"","updateTime":"","userTaskId":""}],"status":0,"taskId":0,"type":0,"userId":0}]
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
         * count : 0
         * curCount : 0
         * curRound : 0
         * cycle : 0
         * expireTime :
         * explain :
         * finishTime :
         * hasDisplay : 0
         * id :
         * img :
         * name :
         * resetTime :
         * resetType : 0
         * rewards : [{"detail":"","explain":"","hasDel":0,"id":0,"imgUrl":"","name":"","status":0,"type":0}]
         * roundCount : 0
         * speeds : [{"id":0,"paramId":0,"paramName":"","round":0,"speed":0,"status":0,"target":0,"unit":"","updateTime":"","userTaskId":""}]
         * status : 0
         * taskId : 0
         * type : 0
         * userId : 0
         */

        private String count;
        private String reward;
        private double curCount;
        private String curRound;
        private String cycle;
        private String expireTime;
        private String explain;
        private String finishTime;
        private String hasDisplay;
        private String id;
        private String img;
        private String name;
        private String resetTime;
        private String resetType;
        private String roundCount;
        private int status;
        private String taskId;
        private String type;
        private String userId;
        private List<RewardsBean> rewards;
        private List<SpeedsBean> speeds;

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getReward() {
            return reward;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public double getCurCount() {
            return curCount;
        }

        public void setCurCount(double curCount) {
            this.curCount = curCount;
        }

        public String getCurRound() {
            return curRound;
        }

        public void setCurRound(String curRound) {
            this.curRound = curRound;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getHasDisplay() {
            return hasDisplay;
        }

        public void setHasDisplay(String hasDisplay) {
            this.hasDisplay = hasDisplay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getResetTime() {
            return resetTime;
        }

        public void setResetTime(String resetTime) {
            this.resetTime = resetTime;
        }

        public String getResetType() {
            return resetType;
        }

        public void setResetType(String resetType) {
            this.resetType = resetType;
        }

        public String getRoundCount() {
            return roundCount;
        }

        public void setRoundCount(String roundCount) {
            this.roundCount = roundCount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
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

        public List<RewardsBean> getRewards() {
            return rewards;
        }

        public void setRewards(List<RewardsBean> rewards) {
            this.rewards = rewards;
        }

        public List<SpeedsBean> getSpeeds() {
            return speeds;
        }

        public void setSpeeds(List<SpeedsBean> speeds) {
            this.speeds = speeds;
        }

        public static class RewardsBean {
            /**
             * detail :
             * explain :
             * hasDel : 0
             * id : 0
             * imgUrl :
             * name :
             * status : 0
             * type : 0
             */

            private String detail;
            private String explain;
            private String hasDel;
            private String id;
            private String imgUrl;
            private String name;
            private String status;
            private String type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getHasDel() {
                return hasDel;
            }

            public void setHasDel(String hasDel) {
                this.hasDel = hasDel;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class SpeedsBean {
            /**
             * id : 0
             * paramId : 0
             * paramName :
             * round : 0
             * speed : 0
             * status : 0
             * target : 0
             * unit :
             * updateTime :
             * userTaskId :
             */

            private String id;
            private String paramId;
            private String paramName;
            private String round;
            private String speed;
            private String status;
            private String target;
            private String unit;
            private String updateTime;
            private String userTaskId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParamId() {
                return paramId;
            }

            public void setParamId(String paramId) {
                this.paramId = paramId;
            }

            public String getParamName() {
                return paramName;
            }

            public void setParamName(String paramName) {
                this.paramName = paramName;
            }

            public String getRound() {
                return round;
            }

            public void setRound(String round) {
                this.round = round;
            }

            public String getSpeed() {
                return StringUtil.isDouble(Double.parseDouble(speed));
            }

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTarget() {
                return StringUtil.isDouble(Double.parseDouble(target));
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserTaskId() {
                return userTaskId;
            }

            public void setUserTaskId(String userTaskId) {
                this.userTaskId = userTaskId;
            }
        }
    }
}
