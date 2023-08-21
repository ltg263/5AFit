package com.jxkj.fit_5a.netty_client.common.message.room.body;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class JoinRoomBody {
    private Integer userId;
    private volatile Long roomId;

    public JoinRoomBody() {

    }

    public JoinRoomBody(Integer userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.roomId));
    }

    public JoinRoomBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.roomId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
    }

    public String getPrintStr() {
        String printStr = "";

        printStr += "\t\t\t\tid为 " + userId
                + " 的用户加入了id为 " + roomId + "的房间。";

        return printStr;
    }
}
