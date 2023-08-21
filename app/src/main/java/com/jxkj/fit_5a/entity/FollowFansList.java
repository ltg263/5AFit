package com.jxkj.fit_5a.entity;

import java.util.List;

public class FollowFansList {

    /**
     * code :
     * data : [{"fansId":0,"timestamp":0,"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}]
     * mesg :
     * sub_code :
     * sub_mesg :
     * time :
     */

    private int code;
    private String mesg;
    private String sub_code;
    private String sub_mesg;
    private String time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_mesg() {
        return sub_mesg;
    }

    public void setSub_mesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fansId : 0
         * timestamp : 0
         * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
         */

        private int fansId;
        private long timestamp;
        private UserBean user;

        public int getFansId() {
            return fansId;
        }

        public void setFansId(int fansId) {
            this.fansId = fansId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * avatar :
             * gender : 0
             * nickName :
             * relation : 0
             * userId : 0
             */

            private String avatar;
            private int gender;
            private String nickName;
            private int relation;
            private int userId;

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

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getRelation() {
                return relation;
            }

            public void setRelation(int relation) {
                this.relation = relation;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
