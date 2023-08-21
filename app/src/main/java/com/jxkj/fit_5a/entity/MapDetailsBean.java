package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class MapDetailsBean implements Parcelable {

    /**
     * id : 16
     * name : 绕湖一圈
     * imgUrl : http://5a-fit.oss-cn-hangzhou.aliyuncs.com/sport/WcEXC07ksKEmtBtM7IrCw.jpg
     * minLevel : 1
     * distance : 12000
     * realSceneUrl :c7937f962be54022b10afaf61db40e73
     * info : [[572,480],[936,188],[1264,112],[1512,232],[1412,368],[1664,456],[1688,588],[1436,684],[1164,772],[1028,960],[1008,1148],[1260,1240],[1228,1420],[928,1444],[520,1472],[344,1364],[216,1108],[248,844],[404,572]]
     * boxs : []
     */

    private String id;
    private String name;
    private String type;
    private String imgUrl;
    private String minLevel;
    private String distance;
    private String realSceneUrl;
    private ParamBean param;
    private List<Float[]> info;
    private List<BoxsBean> boxs;
    private List<UserBean> userMembers;

    public void setRealSceneUrl(String realSceneUrl) {
        this.realSceneUrl = realSceneUrl;
    }

    public String getRealSceneUrl() {
        return realSceneUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUserMembers(List<UserBean> userMembers) {
        this.userMembers = userMembers;
    }

    public List<UserBean> getUserMembers() {
        return userMembers;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public ParamBean getParam() {
        return param;
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

    public List<Float[]> getInfo() {
        return info;
    }

    public void setInfo(List<Float[]> info) {
        this.info = info;
    }

    public List<BoxsBean> getBoxs() {
        return boxs;
    }

    public void setBoxs(List<BoxsBean> boxs) {
        this.boxs = boxs;
    }

    public static class ParamBean implements Parcelable {
        float width;
        float height;
        float referenceWidth;
        float referenceHeight;

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public float getReferenceWidth() {
            return referenceWidth;
        }

        public void setReferenceWidth(float referenceWidth) {
            this.referenceWidth = referenceWidth;
        }

        public float getReferenceHeight() {
            return referenceHeight;
        }

        public void setReferenceHeight(float referenceHeight) {
            this.referenceHeight = referenceHeight;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(this.width);
            dest.writeFloat(this.height);
            dest.writeFloat(this.referenceWidth);
            dest.writeFloat(this.referenceHeight);
        }

        public void readFromParcel(Parcel source) {
            this.width = source.readFloat();
            this.height = source.readFloat();
            this.referenceWidth = source.readFloat();
            this.referenceHeight = source.readFloat();
        }

        public ParamBean() {
        }

        protected ParamBean(Parcel in) {
            this.width = in.readFloat();
            this.height = in.readFloat();
            this.referenceWidth = in.readFloat();
            this.referenceHeight = in.readFloat();
        }

        public static final Creator<ParamBean> CREATOR = new Creator<ParamBean>() {
            @Override
            public ParamBean createFromParcel(Parcel source) {
                return new ParamBean(source);
            }

            @Override
            public ParamBean[] newArray(int size) {
                return new ParamBean[size];
            }
        };
    }
    public static class BoxsBean implements Parcelable {

        /**
         * distance : 0.1
         * latlng : []
         * sportBox : {"id":"61761e2dd8d94750c8f12dfa","name":"测试宝箱（奖励1积分）","rewardList":[{"id":6,"name":"奖励1积分","imgUrl":"","explain":"","type":1,"detail":"{\"incrementValue\":1,\"initialValue\":1,\"maxValue\":1}","rate":0.3}],"probArray":[1],"aliasArray":[null],"hasDel":false}
         * sportBoxId : 61761e2dd8d94750c8f12dfa
         * receiveInfo : {"having":false,"userBoxLog":null}
         */

        private double distance;
        private BoxsBean.SportBoxBean sportBox;
        private String sportBoxId;
        private BoxsBean.ReceiveInfoBean receiveInfo;
        private List<Float> latlng;

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public BoxsBean.SportBoxBean getSportBox() {
            return sportBox;
        }

        public void setSportBox(BoxsBean.SportBoxBean sportBox) {
            this.sportBox = sportBox;
        }

        public String getSportBoxId() {
            return sportBoxId;
        }

        public void setSportBoxId(String sportBoxId) {
            this.sportBoxId = sportBoxId;
        }

        public BoxsBean.ReceiveInfoBean getReceiveInfo() {
            return receiveInfo;
        }

        public void setReceiveInfo(BoxsBean.ReceiveInfoBean receiveInfo) {
            this.receiveInfo = receiveInfo;
        }

        public List<Float> getLatlng() {
            return latlng;
        }

        public void setLatlng(List<Float> latlng) {
            this.latlng = latlng;
        }

        public static class SportBoxBean implements Parcelable {
            /**
             * id : 61761e2dd8d94750c8f12dfa
             * name : 测试宝箱（奖励1积分）
             * rewardList : [{"id":6,"name":"奖励1积分","imgUrl":"","explain":"","type":1,"detail":"{\"incrementValue\":1,\"initialValue\":1,\"maxValue\":1}","rate":0.3}]
             * probArray : [1]
             * aliasArray : [null]
             * hasDel : false
             */

            private String id;
            private String name;
            private boolean hasDel;
            private List<BoxsBean.SportBoxBean.RewardListBean> rewardList;
            private List<Integer> probArray;


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

            public boolean isHasDel() {
                return hasDel;
            }

            public void setHasDel(boolean hasDel) {
                this.hasDel = hasDel;
            }

            public List<BoxsBean.SportBoxBean.RewardListBean> getRewardList() {
                return rewardList;
            }

            public void setRewardList(List<BoxsBean.SportBoxBean.RewardListBean> rewardList) {
                this.rewardList = rewardList;
            }

            public List<Integer> getProbArray() {
                return probArray;
            }

            public void setProbArray(List<Integer> probArray) {
                this.probArray = probArray;
            }


            public static class RewardListBean implements Parcelable {
                /**
                 * id : 6
                 * name : 奖励1积分
                 * imgUrl :
                 * explain :
                 * type : 1
                 * detail : {"incrementValue":1,"initialValue":1,"maxValue":1}
                 * rate : 0.3
                 */

                private int id;
                private String name;
                private String imgUrl;
                private String explain;
                private int type;
                private String detail;
                private String rate;


                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getExplain() {
                    return explain;
                }

                public void setExplain(String explain) {
                    this.explain = explain;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.name);
                    dest.writeString(this.imgUrl);
                    dest.writeString(this.explain);
                    dest.writeInt(this.type);
                    dest.writeString(this.detail);
                    dest.writeString(this.rate);
                }

                public void readFromParcel(Parcel source) {
                    this.id = source.readInt();
                    this.name = source.readString();
                    this.imgUrl = source.readString();
                    this.explain = source.readString();
                    this.type = source.readInt();
                    this.detail = source.readString();
                    this.rate = source.readString();
                }

                public RewardListBean() {
                }

                protected RewardListBean(Parcel in) {
                    this.id = in.readInt();
                    this.name = in.readString();
                    this.imgUrl = in.readString();
                    this.explain = in.readString();
                    this.type = in.readInt();
                    this.detail = in.readString();
                    this.rate = in.readString();
                }

                public static final Creator<BoxsBean.SportBoxBean.RewardListBean> CREATOR = new Creator<BoxsBean.SportBoxBean.RewardListBean>() {
                    @Override
                    public BoxsBean.SportBoxBean.RewardListBean createFromParcel(Parcel source) {
                        return new BoxsBean.SportBoxBean.RewardListBean(source);
                    }

                    @Override
                    public BoxsBean.SportBoxBean.RewardListBean[] newArray(int size) {
                        return new BoxsBean.SportBoxBean.RewardListBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.name);
                dest.writeByte(this.hasDel ? (byte) 1 : (byte) 0);
                dest.writeTypedList(this.rewardList);
                dest.writeList(this.probArray);
            }

            public void readFromParcel(Parcel source) {
                this.id = source.readString();
                this.name = source.readString();
                this.hasDel = source.readByte() != 0;
                this.rewardList = source.createTypedArrayList(BoxsBean.SportBoxBean.RewardListBean.CREATOR);
                this.probArray = new ArrayList<Integer>();
                source.readList(this.probArray, Integer.class.getClassLoader());
            }

            public SportBoxBean() {
            }

            protected SportBoxBean(Parcel in) {
                this.id = in.readString();
                this.name = in.readString();
                this.hasDel = in.readByte() != 0;
                this.rewardList = in.createTypedArrayList(BoxsBean.SportBoxBean.RewardListBean.CREATOR);
                this.probArray = new ArrayList<Integer>();
                in.readList(this.probArray, Integer.class.getClassLoader());
            }

            public static final Creator<BoxsBean.SportBoxBean> CREATOR = new Creator<BoxsBean.SportBoxBean>() {
                @Override
                public BoxsBean.SportBoxBean createFromParcel(Parcel source) {
                    return new BoxsBean.SportBoxBean(source);
                }

                @Override
                public BoxsBean.SportBoxBean[] newArray(int size) {
                    return new BoxsBean.SportBoxBean[size];
                }
            };
        }

        public static class ReceiveInfoBean implements Parcelable {
            /**
             * having : false
             */

            private boolean having;

            public boolean isHaving() {
                return having;
            }

            public void setHaving(boolean having) {
                this.having = having;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeByte(this.having ? (byte) 1 : (byte) 0);
            }

            public void readFromParcel(Parcel source) {
                this.having = source.readByte() != 0;
            }

            public ReceiveInfoBean() {
            }

            protected ReceiveInfoBean(Parcel in) {
                this.having = in.readByte() != 0;
            }

            public static final Creator<BoxsBean.ReceiveInfoBean> CREATOR = new Creator<BoxsBean.ReceiveInfoBean>() {
                @Override
                public BoxsBean.ReceiveInfoBean createFromParcel(Parcel source) {
                    return new BoxsBean.ReceiveInfoBean(source);
                }

                @Override
                public BoxsBean.ReceiveInfoBean[] newArray(int size) {
                    return new BoxsBean.ReceiveInfoBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.distance);
            dest.writeParcelable(this.sportBox, flags);
            dest.writeString(this.sportBoxId);
            dest.writeParcelable(this.receiveInfo, flags);
            dest.writeList(this.latlng);
        }

        public void readFromParcel(Parcel source) {
            this.distance = source.readDouble();
            this.sportBox = source.readParcelable(BoxsBean.SportBoxBean.class.getClassLoader());
            this.sportBoxId = source.readString();
            this.receiveInfo = source.readParcelable(BoxsBean.ReceiveInfoBean.class.getClassLoader());
            this.latlng = new ArrayList<Float>();
            source.readList(this.latlng, Float.class.getClassLoader());
        }

        public BoxsBean() {
        }

        protected BoxsBean(Parcel in) {
            this.distance = in.readDouble();
            this.sportBox = in.readParcelable(BoxsBean.SportBoxBean.class.getClassLoader());
            this.sportBoxId = in.readString();
            this.receiveInfo = in.readParcelable(BoxsBean.ReceiveInfoBean.class.getClassLoader());
            this.latlng = new ArrayList<Float>();
            in.readList(this.latlng, Float.class.getClassLoader());
        }

        public static final Creator<BoxsBean> CREATOR = new Creator<BoxsBean>() {
            @Override
            public BoxsBean createFromParcel(Parcel source) {
                return new BoxsBean(source);
            }

            @Override
            public BoxsBean[] newArray(int size) {
                return new BoxsBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.imgUrl);
        dest.writeString(this.minLevel);
        dest.writeString(this.distance);
        dest.writeString(this.realSceneUrl);
        dest.writeParcelable(this.param, flags);
        dest.writeList(this.info);
        dest.writeTypedList(this.boxs);
        dest.writeTypedList(this.userMembers);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.type = source.readString();
        this.imgUrl = source.readString();
        this.minLevel = source.readString();
        this.distance = source.readString();
        this.realSceneUrl = source.readString();
        this.param = source.readParcelable(ParamBean.class.getClassLoader());
        this.info = new ArrayList<Float[]>();
        source.readList(this.info, Float[].class.getClassLoader());
        this.boxs = source.createTypedArrayList(BoxsBean.CREATOR);
        this.userMembers = source.createTypedArrayList(UserBean.CREATOR);
    }

    public MapDetailsBean() {
    }

    protected MapDetailsBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.imgUrl = in.readString();
        this.minLevel = in.readString();
        this.distance = in.readString();
        this.realSceneUrl = in.readString();
        this.param = in.readParcelable(ParamBean.class.getClassLoader());
        this.info = new ArrayList<Float[]>();
        in.readList(this.info, Float[].class.getClassLoader());
        this.boxs = in.createTypedArrayList(BoxsBean.CREATOR);
        this.userMembers = in.createTypedArrayList(UserBean.CREATOR);
    }

    public static final Creator<MapDetailsBean> CREATOR = new Creator<MapDetailsBean>() {
        @Override
        public MapDetailsBean createFromParcel(Parcel source) {
            return new MapDetailsBean(source);
        }

        @Override
        public MapDetailsBean[] newArray(int size) {
            return new MapDetailsBean[size];
        }
    };

    public static class UserBean implements Parcelable {
        /**
         * userId : 96
         * nickName : 过云雨雨
         * avatar : https://5a-fit-oss.nbqichen.com/moment/5f48dd686322cd59152016641816047b.jpg
         * gender : 1
         */

        private String userId;
        private String nickName;
        private String avatar;
        private float lastAnimtionValue;
        private String distance="0";
        private Long timestamp=0L;

        public void setLastAnimtionValue(float lastAnimtionValue) {
            this.lastAnimtionValue = lastAnimtionValue;
        }

        public float getLastAnimtionValue() {
            return lastAnimtionValue;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getDistance() {
            return distance;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "userId='" + userId + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", gender='" + gender + '\'' +
                    '}';
        }

        private String gender;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userId);
            dest.writeString(this.nickName);
            dest.writeString(this.avatar);
            dest.writeString(this.gender);
        }

        public void readFromParcel(Parcel source) {
            this.userId = source.readString();
            this.nickName = source.readString();
            this.avatar = source.readString();
            this.gender = source.readString();
        }

        public UserBean() {
        }

        protected UserBean(Parcel in) {
            this.userId = in.readString();
            this.nickName = in.readString();
            this.avatar = in.readString();
            this.gender = in.readString();
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
}
