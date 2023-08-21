package com.jxkj.fit_5a.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class BpmDataBean implements Parcelable {

    String name;
    int startV;
    int endV;
    long time;
    BpmTopData mBpmTopData;

    public void setBpmTopData(BpmTopData bpmTopData) {
        mBpmTopData = bpmTopData;
    }

    public BpmTopData getBpmTopData() {
        return mBpmTopData;
    }

    public BpmDataBean(String name, int startV, int endV, long time) {
        this.name = name;
        this.startV = startV;
        this.endV = endV;
        this.time = time;
    }

    @Override
    public String toString() {
        return "BpmDataBean{" +
                "name='" + name + '\'' +
                ", startV=" + startV +
                ", endV=" + endV +
                ", time=" + time +
                '}';
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartV() {
        return startV;
    }

    public void setStartV(int startV) {
        this.startV = startV;
    }

    public double getEndV() {
        return endV;
    }

    public void setEndV(int endV) {
        this.endV = endV;
    }

    public static class BpmTopData implements Parcelable {

        String calories;//卡路里(单位Cal)
        String distance;//运动距离(单位KM)
        String duration;//运动总时长(单位秒)
        String pjDuration;//平均速度(单位H)
        String maxSpeed;//最大的速度(单位KM/
        String heartRate;//平均心跳
        String watt;//功率
        String load_dx;//段位(最小阻力-最大阻力)
        String heartRate_qj;//心率区间
        String bai;//BAI

//        list.add("卡路里");
//        list.add("运动里程");
//        list.add("运动时间");
//        list.add("功率");
//        list.add("段位");
//        list.add("平均速度");
//        list.add("最快速度");
//        list.add("平均心跳");
//        list.add("心率区间");
//        list.add("BAI");

        public BpmTopData(String calories, String distance, String duration, String pjDuration, String maxSpeed
                , String heartRate, String watt, String load_dx, String heartRate_qj, String bai) {
            this.calories = calories;
            this.distance = distance;
            this.duration = duration;
            this.pjDuration = pjDuration;
            this.maxSpeed = maxSpeed;
            this.heartRate = heartRate;
            this.watt = watt;
            this.load_dx = load_dx;
            this.heartRate_qj = heartRate_qj;
            this.bai = bai;
        }

        public String getWatt() {
            return watt;
        }

        public void setWatt(String watt) {
            this.watt = watt;
        }

        public String getLoad_dx() {
            return load_dx;
        }

        public void setLoad_dx(String load_dx) {
            this.load_dx = load_dx;
        }

        public String getHeartRate_qj() {
            return heartRate_qj;
        }

        public void setHeartRate_qj(String heartRate_qj) {
            this.heartRate_qj = heartRate_qj;
        }

        public String getBai() {
            return bai;
        }

        public void setBai(String bai) {
            this.bai = bai;
        }

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPjDuration() {
            return pjDuration;
        }

        public void setPjDuration(String pjDuration) {
            this.pjDuration = pjDuration;
        }

        public String getMaxSpeed() {
            return maxSpeed;
        }

        public void setMaxSpeed(String maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        public String getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(String heartRate) {
            this.heartRate = heartRate;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.calories);
            dest.writeString(this.distance);
            dest.writeString(this.duration);
            dest.writeString(this.pjDuration);
            dest.writeString(this.maxSpeed);
            dest.writeString(this.heartRate);
            dest.writeString(this.watt);
            dest.writeString(this.load_dx);
            dest.writeString(this.heartRate_qj);
            dest.writeString(this.bai);
        }

        protected BpmTopData(Parcel in) {
            this.calories = in.readString();
            this.distance = in.readString();
            this.duration = in.readString();
            this.pjDuration = in.readString();
            this.maxSpeed = in.readString();
            this.heartRate = in.readString();
            this.watt = in.readString();
            this.load_dx = in.readString();
            this.heartRate_qj = in.readString();
            this.bai = in.readString();
        }

        public static final Creator<BpmTopData> CREATOR = new Creator<BpmTopData>() {
            @Override
            public BpmTopData createFromParcel(Parcel source) {
                return new BpmTopData(source);
            }

            @Override
            public BpmTopData[] newArray(int size) {
                return new BpmTopData[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.startV);
        dest.writeInt(this.endV);
        dest.writeLong(this.time);
        dest.writeParcelable(this.mBpmTopData, flags);
    }

    protected BpmDataBean(Parcel in) {
        this.name = in.readString();
        this.startV = in.readInt();
        this.endV = in.readInt();
        this.time = in.readLong();
        this.mBpmTopData = in.readParcelable(BpmTopData.class.getClassLoader());
    }

    public static final Parcelable.Creator<BpmDataBean> CREATOR = new Parcelable.Creator<BpmDataBean>() {
        @Override
        public BpmDataBean createFromParcel(Parcel source) {
            return new BpmDataBean(source);
        }

        @Override
        public BpmDataBean[] newArray(int size) {
            return new BpmDataBean[size];
        }
    };
}

