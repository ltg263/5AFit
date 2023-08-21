package com.jxkj.fit_5a.entity;

import com.jxkj.fit_5a.base.TaskListBase;

import java.util.List;

public class RewardLogBean {

    /**
     * list : [{"id":1440,"userId":353,"userTaskId":"353_109","round":1,"type":2,"reward":"{\"createTime\":1599720510000,\"explain\":\"燃烧卡路里达到5千卡\",\"hasDel\":0,\"id\":4,\"imgUrl\":\"http://oss.5afit.com/portrait/uYv5QX07ZBe9l0CsbDIxg.png\",\"imgUrlNo\":\"https://oss.5afit.com/medal/sv76jNBkxkBtCw1LMiUjg.png\",\"name\":\"5千卡\",\"sort\":5,\"type\":1}","explain":"活动任务完成，获得勋章\u201c5千卡\u201d.","createTime":"2022-07-28 16:21:02"},{"id":1439,"userId":353,"userTaskId":"353_108","round":1,"type":2,"reward":"{\"createTime\":1599720490000,\"explain\":\"燃烧卡路里达到1千卡\",\"hasDel\":0,\"id\":3,\"imgUrl\":\"https://oss.5afit.com/medal/l4Kzf2Cvby5BLlLT5EQAdA.png\",\"imgUrlNo\":\"https://oss.5afit.com/medal/mOmT15V7bVAoL061sNnmrQ.png\",\"name\":\"1千卡\",\"sort\":6,\"type\":1}","explain":"活动任务完成，获得勋章\u201c1千卡\u201d.","createTime":"2022-07-28 16:18:34"}]
     * totalCount : 2
     */

    private String totalCount;
    private List<ListBean> list;
    private List<TaskListBase.ListBean> finishTask;

    public void setFinishTask(List<TaskListBase.ListBean> finishTask) {
        this.finishTask = finishTask;
    }

    public List<TaskListBase.ListBean> getFinishTask() {
        return finishTask;
    }

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
         * id : 1440
         * userId : 353
         * userTaskId : 353_109
         * round : 1
         * type : 2
         * reward : {"createTime":1599720510000,"explain":"燃烧卡路里达到5千卡","hasDel":0,"id":4,"imgUrl":"http://oss.5afit.com/portrait/uYv5QX07ZBe9l0CsbDIxg.png","imgUrlNo":"https://oss.5afit.com/medal/sv76jNBkxkBtCw1LMiUjg.png","name":"5千卡","sort":5,"type":1}
         * explain : 活动任务完成，获得勋章“5千卡”.
         * createTime : 2022-07-28 16:21:02
         */

        private String id;
        private String userId;
        private String userTaskId;
        private String round;
        private String type;
        private String reward;
        private String explain;
        private String createTime;

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

        public String getUserTaskId() {
            return userTaskId;
        }

        public void setUserTaskId(String userTaskId) {
            this.userTaskId = userTaskId;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
