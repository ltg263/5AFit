package com.jxkj.fit_5a.entity;

import java.util.List;

public class GameRoomListBean {

    /**
     * list : [{"createTime":0,"currentNumber":0,"id":0,"limitNumber":0,"mapId":"","name":"","ownerUserId":0,"sportMapBase":{"deviceTypeId":0,"difficultyLevel":0,"distance":0,"hasDel":true,"id":"","imgUrl":"","minLevel":0,"name":"","usable":true},"type":"","user":{"avatar":"","gender":0,"nickName":"","userId":0},"verification":true}]
     * page : 0
     * pageSize : 0
     * total : 0
     * totalCount : 0
     */

    private int page;
    private int pageSize;
    private int total;
    private int totalCount;
    private List<ListBean> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
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
         * createTime : 0
         * currentNumber : 0
         * id : 0
         * limitNumber : 0
         * mapId :
         * name :
         * ownerUserId : 0
         * sportMapBase : {"deviceTypeId":0,"difficultyLevel":0,"distance":0,"hasDel":true,"id":"","imgUrl":"","minLevel":0,"name":"","usable":true}
         * type :
         * user : {"avatar":"","gender":0,"nickName":"","userId":0}
         * verification : true
         */

        private String createTime;
        private String currentNumber;
        private String id;
        private String limitNumber;
        private String mapId;
        private String name;
        private String ownerUserId;
        private SportMapBaseBean sportMapBase;
        private String type;
        private String status;
        private UserBean user;
        private boolean verification;

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

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
            public static class BoxsBean{
                String distance;

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public String getDistance() {
                    return distance;
                }
            }
            private String deviceTypeId;
            private String difficultyLevel;
            private String distance;
            private boolean hasDel;
            private String id;
            private String imgUrl;
            private String minLevel;
            private String name;
            private boolean usable;
            private List<BoxsBean> boxs;

            public void setBoxs(List<BoxsBean> boxs) {
                this.boxs = boxs;
            }

            public List<BoxsBean> getBoxs() {
                return boxs;
            }

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

        public static class UserBean {
            /**
             * avatar :
             * gender : 0
             * nickName :
             * userId : 0
             */

            private String avatar;
            private String gender;
            private String nickName;
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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
