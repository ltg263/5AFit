package com.jxkj.fit_5a.entity;

import java.util.List;

public class GameCompleteBean {


    /**
     * gameRankId : 
     * gameRankings : [{"calories":0,"createTime":"","distance":0,"id":"","ranking":0,"roomId":0,"sportLogId":"","timeCost":0,"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0},"userId":0}]
     * ranking : 0
     */

    private String gameRankId;
    private String ranking;
    private List<GameRankingsBean> gameRankings;

    public String getGameRankId() {
        return gameRankId;
    }

    public void setGameRankId(String gameRankId) {
        this.gameRankId = gameRankId;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<GameRankingsBean> getGameRankings() {
        return gameRankings;
    }

    public void setGameRankings(List<GameRankingsBean> gameRankings) {
        this.gameRankings = gameRankings;
    }

    public static class GameRankingsBean {
        /**
         * calories : 0
         * createTime : 
         * distance : 0
         * id : 
         * ranking : 0
         * roomId : 0
         * sportLogId : 
         * timeCost : 0
         * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
         * userId : 0
         */

        private String calories;
        private String createTime;
        private String distance;
        private String id;
        private String ranking;
        private String roomId;
        private String sportLogId;
        private String timeCost;
        private UserBean user;
        private String userId;

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getSportLogId() {
            return sportLogId;
        }

        public void setSportLogId(String sportLogId) {
            this.sportLogId = sportLogId;
        }

        public String getTimeCost() {
            return timeCost;
        }

        public void setTimeCost(String timeCost) {
            this.timeCost = timeCost;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
            private String gender;
            private String nickName;
            private String relation;
            private String userId;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
