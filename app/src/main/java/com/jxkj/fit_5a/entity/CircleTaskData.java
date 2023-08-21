package com.jxkj.fit_5a.entity;

import java.util.List;

public class CircleTaskData {

    /**
     * id : 80_36
     * userId : 80
     * taskId : 36
     * count : 0
     * type : 1
     * name : 圈子任务
     * explain :
     * reward :
     * details :
     * resetTime :
     * resetType : 1
     * hasDisplay : 1
     * status : 1
     * finishTime :
     * expireTime :
     * roundCount : 1
     * img :
     * cycle : 3
     * curCount : 0
     * curRound : 0
     * rewards : [{"id":3,"name":"积分10递增（max-100）","imgUrl":"","explain":"","type":1,"detail":"{\"incrementValue\":10,\"initialValue\":10,\"maxValue\":100}","hasDel":0,"status":1}]
     * speeds : [{"id":698,"round":1,"userTaskId":"80_36","paramId":4,"paramName":"运动时长","unit":"分钟","target":10,"speed":0,"status":1,"updateTime":"2020-12-25 11:58:45"}]
     */

    private boolean isSelect;
    private String id;
    private String userId;
    private String taskId;
    private String count;
    private String type;
    private String name;
    private String explain;
    private String reward;
    private String details;
    private String resetTime;
    private String resetType;
    private String hasDisplay;
    private String status;
    private String finishTime;
    private String expireTime;
    private String roundCount;
    private String img;
    private String cycle;
    private String curCount;
    private String curRound;
    private List<RewardsBean> rewards;
    private List<SpeedsBean> speeds;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getHasDisplay() {
        return hasDisplay;
    }

    public void setHasDisplay(String hasDisplay) {
        this.hasDisplay = hasDisplay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(String roundCount) {
        this.roundCount = roundCount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCurCount() {
        return curCount;
    }

    public void setCurCount(String curCount) {
        this.curCount = curCount;
    }

    public String getCurRound() {
        return curRound;
    }

    public void setCurRound(String curRound) {
        this.curRound = curRound;
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
         * id : 3
         * name : 积分10递增（max-100）
         * imgUrl :
         * explain :
         * type : 1
         * detail : {"incrementValue":10,"initialValue":10,"maxValue":100}
         * hasDel : 0
         * status : 1
         */

        private String id;
        private String name;
        private String imgUrl;
        private String explain;
        private String type;
        private String detail;
        private String hasDel;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return "今日奖励";
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getHasDel() {
            return hasDel;
        }

        public void setHasDel(String hasDel) {
            this.hasDel = hasDel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class SpeedsBean {
        /**
         * id : 698
         * round : 1
         * userTaskId : 80_36
         * paramId : 4
         * paramName : 运动时长
         * unit : 分钟
         * target : 10
         * speed : 0
         * status : 1
         * updateTime : 2020-12-25 11:58:45
         */

        private String id;
        private String round;
        private String userTaskId;
        private String paramId;
        private String paramName;
        private String unit;
        private String target;
        private String speed;
        private String status;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        public String getUserTaskId() {
            return userTaskId;
        }

        public void setUserTaskId(String userTaskId) {
            this.userTaskId = userTaskId;
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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getTarget() {
            if(target.contains(".00")){
                return target.replace(".00","");
            }
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getSpeed() {
            return speed;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
