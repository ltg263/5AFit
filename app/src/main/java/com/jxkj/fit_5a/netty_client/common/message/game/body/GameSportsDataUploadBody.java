package com.jxkj.fit_5a.netty_client.common.message.game.body;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class GameSportsDataUploadBody {
    private Integer size;
    private Integer userId;
    private Integer distance;
    private Long timestamp;
    private Integer speed;
    private Integer isOnline;

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getIsOnline() {
        return isOnline;
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

    public GameSportsDataUploadBody() {

    }

    public GameSportsDataUploadBody(Integer userId, Integer distance, Long timestamp, Integer speed) {
        this.userId = userId;
        this.distance = distance;
        this.timestamp = timestamp;
        this.speed = speed;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.distance), BytesUtils.getBytes(this.timestamp), BytesUtils.getBytes(this.speed));
    }

    public GameSportsDataUploadBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.size = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.distance = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.timestamp = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
        index += 8;

        this.speed = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index +=4;

        this.isOnline = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 1));
    }

    @Override
    public String toString() {
        return "GameSportsDataUploadBody{" +
                "size=" + size +
                ", userId=" + userId +
                ", distance=" + distance +
                ", timestamp=" + timestamp +
                ", speed=" + speed +
                ", isOnline=" + isOnline +
                '}';
    }

    public String getPrintStr() {
        String printStr = "";

//        printStr += "\t\t\t\tid为 " + userId
//                + " 的用户更新了运动数据 " + distance + ", 更新时间为" + timestamp;

        printStr += size +
                ", userId=" + userId +
                ", distance=" + distance +
                ", timestamp=" + timestamp +
                ", speed=" + speed +
                ", isOnline=" + isOnline +
                '}';
        return printStr;
    }
}
