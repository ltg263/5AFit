package com.jxkj.fit_5a.netty_client.common.message.game.body;

import android.os.Parcel;
import android.os.Parcelable;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;
import lombok.Getter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Data
public class GameSportsDataNotifBody {
    @Getter
    public static class SportData implements Parcelable {
        private Integer userId;
        private Integer distance;
        private Long timestamp;
        private Integer speed;
        private Byte isOnline;

        @Override
        public String toString() {
            return "SportData{" +
                    "userId=" + userId +
                    ", distance=" + distance +
                    ", timestamp=" + timestamp +
                    ", speed=" + speed +
                    ", isOnline=" + isOnline +
                    '}';
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getSpeed() {
            return speed;
        }

        public void setSpeed(Integer speed) {
            this.speed = speed;
        }

        public Byte getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(Byte isOnline) {
            this.isOnline = isOnline;
        }

        public SportData(Integer userId, Integer distance, Long timestamp, Integer speed, Byte isOnline) {
            this.userId = userId;
            this.distance = distance;
            this.timestamp = timestamp;
            this.speed = speed;
            this.isOnline = isOnline;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.userId);
            dest.writeValue(this.distance);
            dest.writeValue(this.timestamp);
            dest.writeValue(this.speed);
            dest.writeValue(this.isOnline);
        }

        public void readFromParcel(Parcel source) {
            this.userId = (Integer) source.readValue(Integer.class.getClassLoader());
            this.distance = (Integer) source.readValue(Integer.class.getClassLoader());
            this.timestamp = (Long) source.readValue(Long.class.getClassLoader());
            this.speed = (Integer) source.readValue(Integer.class.getClassLoader());
            this.isOnline = (Byte) source.readValue(Byte.class.getClassLoader());
        }

        protected SportData(Parcel in) {
            this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
            this.distance = (Integer) in.readValue(Integer.class.getClassLoader());
            this.timestamp = (Long) in.readValue(Long.class.getClassLoader());
            this.speed = (Integer) in.readValue(Integer.class.getClassLoader());
            this.isOnline = (Byte) in.readValue(Byte.class.getClassLoader());
        }

        public static final Parcelable.Creator<SportData> CREATOR = new Parcelable.Creator<SportData>() {
            @Override
            public SportData createFromParcel(Parcel source) {
                return new SportData(source);
            }

            @Override
            public SportData[] newArray(int size) {
                return new SportData[size];
            }
        };
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    private Integer size;
    private List<SportData> sportDataList;

    public List<SportData> getSportDataList() {
        return sportDataList;
    }

    public GameSportsDataNotifBody() {

    }

    public GameSportsDataNotifBody(List<SportData> sportDataList) {
        this.size = sportDataList.size();
        this.sportDataList = sportDataList;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        byte[] bytes = BytesUtils.getBytes(this.size);

        for (SportData sportData : sportDataList) {
            bytes = BytesUtils.concat(bytes, BytesUtils.getBytes(sportData.getUserId()), BytesUtils.getBytes(sportData.getDistance()), BytesUtils.getBytes(sportData.getTimestamp()), BytesUtils.getBytes(sportData.getSpeed())
                    , BytesUtils.getBytes(sportData.getIsOnline()));
        }

        return bytes;
    }

    public GameSportsDataNotifBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.size = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.sportDataList = new ArrayList<>();
        int i = 0;
        while (i++ < this.size) {
            int userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
            index += 4;

            int distance = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
            index += 4;

            long timestamp = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
            index += 8;

            int speed = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
            index += 4;

            byte isOnline = bytes[index];
            index += 1;

            sportDataList.add(new SportData(userId, distance, timestamp, speed, isOnline));
        }
    }

    public String getPrintStr() {
        String printStr = "";

        printStr += "\t\t\t\t运动数据通知 ";
        printStr += "\t\t\t\t数量 " + this.size + "\r\n";
        if (null != sportDataList) {
            for (SportData sportData : sportDataList) {
                printStr += "\t\t\t\t\t\t 用户id：" + sportData.getUserId() + ", 当前运动距离：" + sportData.getDistance() + "当前速度："+sportData.getSpeed()+ "当前时间："+sportData.getTimestamp()+", 是否在线：" + sportData.getIsOnline() + "\r\n";
            }
        }

        return printStr;
    }
}
