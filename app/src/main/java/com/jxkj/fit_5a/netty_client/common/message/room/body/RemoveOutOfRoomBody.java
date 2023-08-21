package com.jxkj.fit_5a.netty_client.common.message.room.body;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class RemoveOutOfRoomBody {
    private Integer userId;
    private volatile Long roomId;
    private Integer memberUserId; // 被移除的成员

    public RemoveOutOfRoomBody() {

    }

    public RemoveOutOfRoomBody(Integer userId, Long roomId, Integer memberUserId) {
        this.userId = userId;
        this.roomId = roomId;
        this.memberUserId = memberUserId;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.roomId), BytesUtils.getBytes(this.memberUserId));
    }

    public RemoveOutOfRoomBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.roomId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
        index += 8;

        this.memberUserId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        ;
    }

    public String getPrintStr() {
        String printStr = "";

        printStr += "\t\t\t\tid为 " + userId
                + " 的用户将id为 " + memberUserId + " 踢出了id为 " + roomId + "的房间。";

        return printStr;
    }
}
