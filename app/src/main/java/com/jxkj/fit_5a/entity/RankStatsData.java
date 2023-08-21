package com.jxkj.fit_5a.entity;

import java.util.List;

public class RankStatsData {

    /**
     * id :
     * userId : 1
     * ranking : 0
     * dateStamp : 0
     * likeCount : 0
     * calories : 0
     * lastTimestamp : 0
     * updateTime :
     * user : {"userId":1,"relation":4,"nickName":"daixiping","avatar":"https://avatar.csdnimg.cn/C/5/9/2_ltg263.jpg","gender":1}
     * caloriesRankingList : [{"id":"20210401_74","userId":74,"ranking":1,"dateStamp":20210401,"likeCount":3,"calories":78,"lastTimestamp":1618300109064,"updateTime":"2021-04-13 15:50:18","user":{"userId":74,"relation":0,"nickName":"yw2180","avatar":"https://api.iconsole.plus/v2/picture/7122ed87032bb2930760448fb470580e0a91df363d51227340c6a5965a075df79ac3e981545beb65b1152f68221927cb.jpg","gender":1},"caloriesRankingList":[],"like":false},{"id":"20210401_137","userId":137,"ranking":2,"dateStamp":20210401,"likeCount":0,"calories":64,"lastTimestamp":1618380853321,"updateTime":"2021-04-14 14:16:03","user":{"userId":137,"relation":0,"nickName":"","avatar":"13123","gender":1},"caloriesRankingList":[],"like":false},{"id":"20210401_129","userId":129,"ranking":3,"dateStamp":20210401,"likeCount":0,"calories":39,"lastTimestamp":1618464887143,"updateTime":"2021-04-15 13:43:33","user":{"userId":129,"relation":0,"nickName":"台灣大香腸","avatar":"13123","gender":1},"caloriesRankingList":[],"like":false},{"id":"20210401_128","userId":128,"ranking":4,"dateStamp":20210401,"likeCount":0,"calories":15,"lastTimestamp":1618637557397,"updateTime":"2021-04-17 13:34:57","user":{"userId":128,"relation":0,"nickName":"1111","avatar":"13123","gender":2},"caloriesRankingList":[],"like":false},{"id":"20210401_117","userId":117,"ranking":5,"dateStamp":20210401,"likeCount":0,"calories":2,"lastTimestamp":1617690213479,"updateTime":"2021-04-06 14:25:27","user":{"userId":117,"relation":0,"nickName":"JASON","avatar":"https://api.iconsole.plus/v2/picture/95518afb54a0d2ae80b3f32d6acac07c623773400e94c91942b569d64b7f81fbf9a21ae168579482be3e4f2bd8e0334c.jpg","gender":1},"caloriesRankingList":[],"like":false},{"id":"20210401_145","userId":145,"ranking":6,"dateStamp":20210401,"likeCount":0,"calories":2,"lastTimestamp":1618560780900,"updateTime":"2021-04-16 16:13:50","user":{"userId":145,"relation":0,"nickName":"请修改昵称_15166778800","avatar":"https://5a-fit-oss.nbqichen.com/user/2fmS3z0p29WmwtwXzpUA.jpg","gender":1},"caloriesRankingList":[],"like":false}]
     * like : false
     */

    private String id;
    private String userId;
    private int ranking;
    private String dateStamp;
    private String likeCount;
    private String calories;
    private String lastTimestamp;
    private String updateTime;
    private UserBean user;
    private boolean like;
    private List<CaloriesRankingListBean> caloriesRankingList;

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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(String lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public List<CaloriesRankingListBean> getCaloriesRankingList() {
        return caloriesRankingList;
    }

    public void setCaloriesRankingList(List<CaloriesRankingListBean> caloriesRankingList) {
        this.caloriesRankingList = caloriesRankingList;
    }

    public static class UserBean {
        /**
         * userId : 1
         * relation : 4
         * nickName : daixiping
         * avatar : https://avatar.csdnimg.cn/C/5/9/2_ltg263.jpg
         * gender : 1
         */

        private String userId;
        private String relation;
        private String nickName;
        private String avatar;
        private String gender;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static class CaloriesRankingListBean {
        /**
         * id : 20210401_74
         * userId : 74
         * ranking : 1
         * dateStamp : 20210401
         * likeCount : 3
         * calories : 78
         * lastTimestamp : 1618300109064
         * updateTime : 2021-04-13 15:50:18
         * user : {"userId":74,"relation":0,"nickName":"yw2180","avatar":"https://api.iconsole.plus/v2/picture/7122ed87032bb2930760448fb470580e0a91df363d51227340c6a5965a075df79ac3e981545beb65b1152f68221927cb.jpg","gender":1}
         * caloriesRankingList : []
         * like : false
         */

        private String id;
        private String userId;
        private String ranking;
        private String dateStamp;
        private String likeCount;
        private String calories;
        private long lastTimestamp;
        private String updateTime;
        private UserBeanX user;
        private boolean like;
        private List<?> caloriesRankingList;

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

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getDateStamp() {
            return dateStamp;
        }

        public void setDateStamp(String dateStamp) {
            this.dateStamp = dateStamp;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public long getLastTimestamp() {
            return lastTimestamp;
        }

        public void setLastTimestamp(long lastTimestamp) {
            this.lastTimestamp = lastTimestamp;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public boolean isLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public List<?> getCaloriesRankingList() {
            return caloriesRankingList;
        }

        public void setCaloriesRankingList(List<?> caloriesRankingList) {
            this.caloriesRankingList = caloriesRankingList;
        }

        public static class UserBeanX {
            /**
             * userId : 74
             * relation : 0
             * nickName : yw2180
             * avatar : https://api.iconsole.plus/v2/picture/7122ed87032bb2930760448fb470580e0a91df363d51227340c6a5965a075df79ac3e981545beb65b1152f68221927cb.jpg
             * gender : 1
             */

            private String userId;
            private String relation;
            private String nickName;
            private String avatar;
            private String gender;

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

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }
    }
}
