package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

public class GameRoomDetailsBean {

    /**
     * id : 51
     * ownerUserId : 96
     * user : {"userId":96,"nickName":"过云雨雨","avatar":"https://5a-fit-oss.nbqichen.com/moment/5f48dd686322cd59152016641816047b.jpg","gender":1}
     * type : athletics
     * name : 啊啊啊啊
     * limitNumber : 10
     * currentNumber : 1
     * mapId : 614bf982d8d94750c8f12df8
     * sportMapBase : {"id":"614bf982d8d94750c8f12df8","name":"法国旅游景点（室内单车）","difficultyLevel":0,"deviceTypeId":2,"imgUrl":"https://5a-fit-oss.nbqichen.com/sport/tar4y21SRaewQwlv7k5f5Q.png","minLevel":0,"distance":11,"usable":true,"hasDel":false}
     * verification : true
     * createTime : 1640145999713
     * members : [{"userId":96,"nickName":"过云雨雨","avatar":"https://5a-fit-oss.nbqichen.com/moment/5f48dd686322cd59152016641816047b.jpg","gender":1}]
     */

    private String id;
    private String ownerUserId;
    private UserBean user;
    private String type;
    private String name;
    private String limitNumber;
    private String currentNumber;
    private String mapId;
    private SportMapBaseBean sportMapBase;
    private boolean verification;
    private String createTime;
    private List<UserBean> members;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(String limitNumber) {
        this.limitNumber = limitNumber;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public SportMapBaseBean getSportMapBase() {
        return sportMapBase;
    }

    public void setSportMapBase(SportMapBaseBean sportMapBase) {
        this.sportMapBase = sportMapBase;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<UserBean> getMembers() {
        return members;
    }

    public void setMembers(List<UserBean> members) {
        this.members = members;
    }

    public static class UserBean implements Parcelable {

        /**
         * userId : 355
         * relation : 4
         * nickName : 用户355
         * avatar : https://5a-fit-oss.nbqichen.com/moment/df5c91b1f61bd5c9b283db988217dabc.jpg
         * gender : 2
         * sportStats : {"userId":355,"calories":252,"distance":15.619,"duration":3286}
         */

        private String userId;
        private String relation;
        private String nickName;
        private String avatar;
        private String gender;
        private SportStatsBean sportStats;

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

        public SportStatsBean getSportStats() {
            return sportStats;
        }

        public void setSportStats(SportStatsBean sportStats) {
            this.sportStats = sportStats;
        }

        public static class SportStatsBean implements Parcelable {
            /**
             * userId : 355
             * calories : 252
             * distance : 15.619
             * duration : 3286
             */

            private String userId;
            private String calories;
            private String distance;
            private String duration;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getCalories() {
                return calories.replace(".00","");
            }

            public void setCalories(String calories) {
                this.calories = calories;
            }

            public String getDistance() {
                return StringUtil.getValue(distance);
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public long getDuration() {
                return Long.parseLong(duration);
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.userId);
                dest.writeString(this.calories);
                dest.writeString(this.distance);
                dest.writeString(this.duration);
            }

            public void readFromParcel(Parcel source) {
                this.userId = source.readString();
                this.calories = source.readString();
                this.distance = source.readString();
                this.duration = source.readString();
            }

            public SportStatsBean() {
            }

            protected SportStatsBean(Parcel in) {
                this.userId = in.readString();
                this.calories = in.readString();
                this.distance = in.readString();
                this.duration = in.readString();
            }

            public static final Parcelable.Creator<SportStatsBean> CREATOR = new Parcelable.Creator<SportStatsBean>() {
                @Override
                public SportStatsBean createFromParcel(Parcel source) {
                    return new SportStatsBean(source);
                }

                @Override
                public SportStatsBean[] newArray(int size) {
                    return new SportStatsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userId);
            dest.writeString(this.relation);
            dest.writeString(this.nickName);
            dest.writeString(this.avatar);
            dest.writeString(this.gender);
            dest.writeParcelable(this.sportStats, flags);
        }

        public void readFromParcel(Parcel source) {
            this.userId = source.readString();
            this.relation = source.readString();
            this.nickName = source.readString();
            this.avatar = source.readString();
            this.gender = source.readString();
            this.sportStats = source.readParcelable(SportStatsBean.class.getClassLoader());
        }

        public UserBean() {
        }

        protected UserBean(Parcel in) {
            this.userId = in.readString();
            this.relation = in.readString();
            this.nickName = in.readString();
            this.avatar = in.readString();
            this.gender = in.readString();
            this.sportStats = in.readParcelable(SportStatsBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
            @Override
            public UserBean createFromParcel(Parcel source) {
                return new UserBean(source);
            }

            @Override
            public UserBean[] newArray(int size) {
                return new UserBean[size];
            }
        };
    }

    public static class SportMapBaseBean {
        /**
         * id : 614bf982d8d94750c8f12df8
         * name : 法国旅游景点（室内单车）
         * difficultyLevel : 0
         * deviceTypeId : 2
         * imgUrl : https://5a-fit-oss.nbqichen.com/sport/tar4y21SRaewQwlv7k5f5Q.png
         * minLevel : 0
         * distance : 11
         * usable : true
         * hasDel : false
         */

        private String id;
        private String name;
        private String difficultyLevel;
        private String deviceTypeId;
        private String imgUrl;
        private String minLevel;
        private String distance;
        private boolean usable;
        private boolean hasDel;

        private List<GameRoomListBean.ListBean.SportMapBaseBean.BoxsBean> boxs;

        public void setBoxs(List<GameRoomListBean.ListBean.SportMapBaseBean.BoxsBean> boxs) {
            this.boxs = boxs;
        }

        public List<GameRoomListBean.ListBean.SportMapBaseBean.BoxsBean> getBoxs() {
            return boxs;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDifficultyLevel() {
            return difficultyLevel;
        }

        public void setDifficultyLevel(String difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public String getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(String deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public boolean isUsable() {
            return usable;
        }

        public void setUsable(boolean usable) {
            this.usable = usable;
        }

        public boolean isHasDel() {
            return hasDel;
        }

        public void setHasDel(boolean hasDel) {
            this.hasDel = hasDel;
        }
    }
}
