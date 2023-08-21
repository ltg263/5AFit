package com.jxkj.fit_5a.base;

public class BlackListBean {

    /**
     * blUserId : 322
     * user : {"userId":322,"relation":0,"nickName":"JASON","avatar":"https://oss.5afit.com/moment/7240bc85320f5e826ab3eca31e9d184b.jpg","gender":1}
     * timestamp : 1655972946433
     */

    private String blUserId;
    private UserBean user;
    private long timestamp;

    public String getBlUserId() {
        return blUserId;
    }

    public void setBlUserId(String blUserId) {
        this.blUserId = blUserId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class UserBean {
        @Override
        public String toString() {
            return "UserBean{" +
                    "userId='" + userId + '\'' +
                    ", relation='" + relation + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", gender=" + gender +
                    '}';
        }

        /**
         * userId : 322
         * relation : 0
         * nickName : JASON
         * avatar : https://oss.5afit.com/moment/7240bc85320f5e826ab3eca31e9d184b.jpg
         * gender : 1
         */

        private String userId;
        private String relation;
        private String nickName;
        private String avatar;
        private int gender;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
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

    @Override
    public String toString() {
        return "BlackListBean{" +
                "blUserId='" + blUserId + '\'' +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }
}
