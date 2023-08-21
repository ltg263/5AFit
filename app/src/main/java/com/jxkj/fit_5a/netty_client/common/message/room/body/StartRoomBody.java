package com.jxkj.fit_5a.netty_client.common.message.room.body;

import com.jxkj.fit_5a.netty_client.common.util.Ipv4PortUtils;
import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Data
public class StartRoomBody {
    private Integer userId;
    private volatile Long roomId;

    public Long getRoomMemberId() {
        return roomMemberId;
    }

    public void setRoomMemberId(Long roomMemberId) {
        this.roomMemberId = roomMemberId;
    }

    private Long roomMemberId;
    private int ip;
    private int port;
    private long startIimestamp;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getStartIimestamp() {
        return startIimestamp;
    }

    public void setStartIimestamp(long startIimestamp) {
        this.startIimestamp = startIimestamp;
    }

    public StartRoomBody() {

    }

    public StartRoomBody(Integer userId, Long roomId, Long roomMemberId, int ip, int port, long startIimestamp) {
        this.userId = userId;
        this.roomId = roomId;
        this.roomMemberId = roomMemberId;
        this.ip = ip;
        this.port = port;
        this.startIimestamp = startIimestamp;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.roomId), BytesUtils.getBytes(this.roomMemberId),
                BytesUtils.getBytes(this.ip), BytesUtils.getBytes(this.port), BytesUtils.getBytes(this.startIimestamp));
    }

    public StartRoomBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.roomId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
        index += 8;

        this.roomMemberId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
        index += 8;

        this.ip = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.port = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.startIimestamp = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
    }

    public String getPrintStr() {
        String printStr = "";

        printStr += "\t\t\t\tid为 " + userId
                + " 的用户开始了id为 " + roomId + "的房间的游戏。游戏服务器地址为：" + Ipv4PortUtils.intToIp(this.ip) + "-" + this.port + ", 开始时间为" + new Date(this.startIimestamp).toString();

        return printStr;
    }
}
