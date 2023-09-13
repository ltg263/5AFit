package com.jxkj.fit_5a.entity;

public class LoginInfo {

    /**
     * userPermissionBaseDTO : {"id":96,"userNo":"13111111111","nickName":"恍恍惚惚","level":0,"levelNo":"","levelExpireTime":"","avatar":"http://5a-fit-oss.nbqichen.com/user/680DC980255963991B5401665B77B6C6.jpg","gender":2,"status":1,"createTime":"","clientType":0,"loginType":0,"openId":"","uionId":"","sign":"","birthday":"","age":0,"height":193,"weight":71}
     * tokenId : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHQiOiJ7XCJyZWxhdGlvblR5cGVcIjowLFwicmVsYXRpb25JZFwiOjAsXCJzeXNVc2VySWRcIjo3M30iLCJ1c2VySW5mbyI6IntcImFnZVwiOjAsXCJhdmF0YXJcIjpcImh0dHA6Ly81YS1maXQtb3NzLm5icWljaGVuLmNvbS91c2VyLzY4MERDOTgwMjU1OTYzOTkxQjU0MDE2NjVCNzdCNkM2LmpwZ1wiLFwiZ2VuZGVyXCI6MixcImhlaWdodFwiOjE5My4wMCxcImlkXCI6OTYsXCJsZXZlbFwiOjAsXCJsZXZlbE5vXCI6XCJcIixcImxvZ2luVHlwZVwiOjAsXCJuaWNrTmFtZVwiOlwi5oGN5oGN5oOa5oOaXCIsXCJvcGVuSWRcIjpcIlwiLFwic3RhdHVzXCI6MSxcInVzZXJOb1wiOlwiMTMxMTExMTExMTFcIixcIndlaWdodFwiOjcxLjAwfSIsInVzZXJfbmFtZSI6IntcInVzZXJuYW1lVHlwZVwiOjEsXCJ1c2VybmFtZVwiOlwiMTMxMTExMTExMTFcIn0iLCJzY29wZSI6WyJyZWFkIl0sImV4cCI6MTk0NDQ0MzcwNSwianRpIjoiX1hjaFQ5TEZTUzdWR3FnWm5mb212a1hVVDJNIiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQifQ.vsEd7mWjBta-1l_zPu0tpcXtnhhV3XoWU3yzK5V_L2E
     */

    private UserPermissionBaseDTOBean userPermissionBaseDTO;
    private String tokenId;

    public UserPermissionBaseDTOBean getUserPermissionBaseDTO() {
        return userPermissionBaseDTO;
    }

    public void setUserPermissionBaseDTO(UserPermissionBaseDTOBean userPermissionBaseDTO) {
        this.userPermissionBaseDTO = userPermissionBaseDTO;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public static class UserPermissionBaseDTOBean {
        /**
         * id : 96
         * userNo : 13111111111
         * nickName : 恍恍惚惚
         * level : 0
         * levelNo :
         * levelExpireTime :
         * avatar : http://5a-fit-oss.nbqichen.com/user/680DC980255963991B5401665B77B6C6.jpg
         * gender : 2
         * status : 1
         * createTime :
         * clientType : 0
         * loginType : 0
         * openId :
         * uionId :
         * sign :
         * birthday :
         * age : 0
         * userType:
         * height : 193
         * weight : 71
         */

        private int id;
        private String userNo;
        private String nickName;
        private String level;
        private String levelNo;
        private String levelExpireTime;
        private String avatar;
        private String gender;
        private String status;
        private String createTime;
        private String clientType;
        private String loginType;
        private String openId;
        private String uionId;
        private String sign;
        private String birthday;
        private String age;
        private String height;
        private String weight;
        private int userType;

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getUserType() {
            return userType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelNo() {
            return levelNo;
        }

        public void setLevelNo(String levelNo) {
            this.levelNo = levelNo;
        }

        public String getLevelExpireTime() {
            return levelExpireTime;
        }

        public void setLevelExpireTime(String levelExpireTime) {
            this.levelExpireTime = levelExpireTime;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getUionId() {
            return uionId;
        }

        public void setUionId(String uionId) {
            this.uionId = uionId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
