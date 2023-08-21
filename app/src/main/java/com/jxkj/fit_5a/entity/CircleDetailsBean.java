package com.jxkj.fit_5a.entity;

import java.util.List;

public class CircleDetailsBean {

    /**
     * id : 1631697679383000
     * hot : true
     * lastTimestamp : 1631697910754
     * imgUrl : https://5a-fit-oss.nbqichen.com/circle/jPEcK27D1uq7g8fUxQnMg.jpg
     * name : 跑步爱好者
     * deviceType : 3
     * explain : 测试圈子介绍
     * completedCount : 0
     * memberCount : 1
     * momentCount : 1
     * createTimestamp : 1631697679314
     * taskId : 84
     * taskName : 圈子任务
     * taskExplain : 200100
     * taskResetType : 1
     * taskStatus : 1
     * taskCreateTime : 2021-09-15 17:21:20
     * taskDetails :
     * taskExpireTime :
     * taskImg : https://5a-fit-oss.nbqichen.com/circle/SjAQct7GtqSQUOWylM15gw.png
     * members : [{"circleId":1631697679383000,"memberId":145,"user":{"userId":145,"relation":0,"nickName":"阿呆","avatar":"https://5a-fit-oss.nbqichen.com/user/BPNZCWXK9mvsa6LZD0KPg.jpg","gender":1},"isCreator":false,"momentCount":1,"timestamp":1631697726922}]
     * join : false
     */

    private String id;
    private boolean hot;
    private long lastTimestamp;
    private String imgUrl;
    private String bgImg;
    private String name;
    private int deviceType;
    private String explain;
    private int completedCount;
    private int memberCount;
    private int momentCount;
    private long createTimestamp;
    private int taskId;
    private String taskName;
    private String taskExplain;
    private int taskResetType;
    private int taskStatus;
    private String taskCreateTime;
    private String taskDetails;
    private String taskExpireTime;
    private String taskImg;
    private boolean join;
    private List<MembersBean> members;

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getBgImg() {
        return bgImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
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

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getMomentCount() {
        return momentCount;
    }

    public void setMomentCount(int momentCount) {
        this.momentCount = momentCount;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    public int getTaskResetType() {
        return taskResetType;
    }

    public void setTaskResetType(int taskResetType) {
        this.taskResetType = taskResetType;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(String taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskExpireTime() {
        return taskExpireTime;
    }

    public void setTaskExpireTime(String taskExpireTime) {
        this.taskExpireTime = taskExpireTime;
    }

    public String getTaskImg() {
        return taskImg;
    }

    public void setTaskImg(String taskImg) {
        this.taskImg = taskImg;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class MembersBean {
        /**
         * circleId : 1631697679383000
         * memberId : 145
         * user : {"userId":145,"relation":0,"nickName":"阿呆","avatar":"https://5a-fit-oss.nbqichen.com/user/BPNZCWXK9mvsa6LZD0KPg.jpg","gender":1}
         * isCreator : false
         * momentCount : 1
         * timestamp : 1631697726922
         */

        private long circleId;
        private int memberId;
        private UserBean user;
        private boolean isCreator;
        private int momentCount;
        private long timestamp;

        public long getCircleId() {
            return circleId;
        }

        public void setCircleId(long circleId) {
            this.circleId = circleId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public boolean isIsCreator() {
            return isCreator;
        }

        public void setIsCreator(boolean isCreator) {
            this.isCreator = isCreator;
        }

        public int getMomentCount() {
            return momentCount;
        }

        public void setMomentCount(int momentCount) {
            this.momentCount = momentCount;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public static class UserBean {
            /**
             * userId : 145
             * relation : 0
             * nickName : 阿呆
             * avatar : https://5a-fit-oss.nbqichen.com/user/BPNZCWXK9mvsa6LZD0KPg.jpg
             * gender : 1
             */

            private int userId;
            private int relation;
            private String nickName;
            private String avatar;
            private int gender;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getRelation() {
                return relation;
            }

            public void setRelation(int relation) {
                this.relation = relation;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }
        }
    }
}
