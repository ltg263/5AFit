package com.jxkj.fit_5a.netty_client.common.message.room.body;


import com.jxkj.fit_5a.netty_client.common._enum.DismissRoomReason;
import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class DismissRoomBody {
    private Integer userId;
    private volatile Long roomId;
    private Byte reason;

    public DismissRoomBody() {

    }

    public DismissRoomBody(Integer userId, Long roomId, Byte reason) {
        this.userId = userId;
        this.roomId = roomId;
        this.reason = reason;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.roomId), BytesUtils.getBytes(this.reason));
    }

    public DismissRoomBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.roomId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
        index += 8;

        this.reason = bytes[index];
    }

    public String getPrintStr() {
        String printStr = "";

        String reasonText = null;
        DismissRoomReason dismissRoomReason = DismissRoomReason.getByReason(reason);
        if (null != dismissRoomReason) {
            reasonText = dismissRoomReason.toString() + "-" + dismissRoomReason.getText();
        } else {
            reasonText = "unknow";
        }
        printStr += "\t\t\t\tid为 " + userId
                + " 的用户解散了id为 " + roomId + "的房间。原因: " + reason + "(" + reasonText + ")";

        return printStr;
    }
}
