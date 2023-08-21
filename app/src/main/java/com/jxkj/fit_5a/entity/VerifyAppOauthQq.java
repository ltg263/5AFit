package com.jxkj.fit_5a.entity;


public class VerifyAppOauthQq {

    /**
     * tokenId :
     * bindFlag : false
     * infoFlag : true
     * userBase : {"id":0,"userNo":"","nickName":"🍃Happy ✔","level":0,"levelNo":"","levelExpireTime":"","avatar":"http://thirdqq.qlogo.cn/g?b=oidb&k=yB0Ozib9tibVgGPfibo08TemQ&s=100&t=1555870063","gender":0,"status":0,"createTime":"","clientType":4,"loginType":0,"openId":"120D967737C16FCD162E5FDFC2050D07","uionId":"120D967737C16FCD162E5FDFC2050D07","sign":"","birthday":"","age":0,"height":0,"weight":0}
     * thirdLoginBindInfo : {"loginType":13,"thirdName":"qqweb","thirdUserId":"120D967737C16FCD162E5FDFC2050D07"}
     */

    private String tokenId;
    private boolean bindFlag;
    private boolean infoFlag;
    private UserBaseBean userBase;
    private String thirdLoginBindInfo;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public boolean isBindFlag() {
        return bindFlag;
    }

    public void setBindFlag(boolean bindFlag) {
        this.bindFlag = bindFlag;
    }

    public boolean isInfoFlag() {
        return infoFlag;
    }

    public void setInfoFlag(boolean infoFlag) {
        this.infoFlag = infoFlag;
    }

    public UserBaseBean getUserBase() {
        return userBase;
    }

    public void setUserBase(UserBaseBean userBase) {
        this.userBase = userBase;
    }

    public String getThirdLoginBindInfo() {
        return thirdLoginBindInfo;
    }

    public void setThirdLoginBindInfo(String thirdLoginBindInfo) {
        this.thirdLoginBindInfo = thirdLoginBindInfo;
    }

    public static class UserBaseBean {
        /**
         * id : 0
         * userNo :
         * nickName : 🍃Happy ✔
         * level : 0
         * levelNo :
         * levelExpireTime :
         * avatar : http://thirdqq.qlogo.cn/g?b=oidb&k=yB0Ozib9tibVgGPfibo08TemQ&s=100&t=1555870063
         * gender : 0
         * status : 0
         * createTime :
         * clientType : 4
         * loginType : 0
         * openId : 120D967737C16FCD162E5FDFC2050D07
         * uionId : 120D967737C16FCD162E5FDFC2050D07
         * sign :
         * birthday :
         * age : 0
         * height : 0
         * weight : 0
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
