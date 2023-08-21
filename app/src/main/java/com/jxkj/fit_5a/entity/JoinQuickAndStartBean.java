package com.jxkj.fit_5a.entity;

import java.util.List;

public class JoinQuickAndStartBean {

    /**
     * createTime : 0
     * currentNumber : 0
     * deviceTypeId : 0
     * id : 0
     * limitNumber : 0
     * mapId : 
     * members : [{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}]
     * name : 
     * ownerUserId : 0
     * sportMapBase : {"deviceTypeId":0,"difficultyLevel":0,"distance":0,"hasDel":true,"id":"","imgUrl":"","minLevel":0,"name":"","usable":true}
     * startGameInfo : {"ip":0,"port":0,"printStr":"","roomId":0,"roomMemberId":0,"startIimestamp":0,"userId":0}
     * status : 
     * type : 
     * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
     * verification : true
     */

    private String createTime;
    private String currentNumber;
    private String deviceTypeId;
    private String id;
    private String limitNumber;
    private String mapId;
    private String name;
    private String ownerUserId;
    private SportMapBaseBean sportMapBase;
    private StartGameInfoBean startGameInfo;
    private String status;
    private String type;
    private UserBean user;
    private boolean verification;
    private List<UserBean> members;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(String limitNumber) {
        this.limitNumber = limitNumber;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public SportMapBaseBean getSportMapBase() {
        return sportMapBase;
    }

    public void setSportMapBase(SportMapBaseBean sportMapBase) {
        this.sportMapBase = sportMapBase;
    }

    public StartGameInfoBean getStartGameInfo() {
        return startGameInfo;
    }

    public void setStartGameInfo(StartGameInfoBean startGameInfo) {
        this.startGameInfo = startGameInfo;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public List<UserBean> getMembers() {
        return members;
    }

    public void setMembers(List<UserBean> members) {
        this.members = members;
    }

    public static class SportMapBaseBean {
        /**
         * deviceTypeId : 0
         * difficultyLevel : 0
         * distance : 0
         * hasDel : true
         * id : 
         * imgUrl : 
         * minLevel : 0
         * name : 
         * usable : true
         */

        private String deviceTypeId;
        private String difficultyLevel;
        private String distance;
        private boolean hasDel;
        private String id;
        private String imgUrl;
        private String minLevel;
        private String name;
        private boolean usable;

        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(String deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public void setDifficultyLevel(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public boolean isHasDel() {
            return hasDel;
        }

        public void setHasDel(boolean hasDel) {
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

        public String getMinLevel() {
            return minLevel;
        }

        public void setMinLevel(String minLevel) {
            this.minLevel = minLevel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isUsable() {
            return usable;
        }

        public void setUsable(boolean usable) {
            this.usable = usable;
        }
    }

    public static class StartGameInfoBean {
        /**
         * ip : 0
         * port : 0
         * printStr : 
         * roomId : 0
         * roomMemberId : 0
         * startIimestamp : 0
         * userId : 0
         */

        private String host;
        private String port;
        private String printStr;
        private String roomId;
        private String roomMemberId;
        private String startIimestamp;
        private String userId;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getPrintStr() {
            return printStr;
        }

        public void setPrintStr(String printStr) {
            this.printStr = printStr;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomMemberId() {
            return roomMemberId;
        }

        public void setRoomMemberId(String roomMemberId) {
            this.roomMemberId = roomMemberId;
        }

        public String getStartIimestamp() {
            return startIimestamp;
        }

        public void setStartIimestamp(String startIimestamp) {
            this.startIimestamp = startIimestamp;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
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
