package com.jxkj.fit_5a.netty_client.common.message.room.body;

import com.jxkj.fit_5a.netty_client.common._enum.QuitRoomReason;
import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class QuitRoomBody {
    private Integer userId;
    private volatile Long roomId;
    private Byte reason;

    public QuitRoomBody() {

    }

    public QuitRoomBody(Integer userId, Long roomId, Byte reason) {
        this.userId = userId;
        this.roomId = roomId;
        this.reason = reason;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.roomId), BytesUtils.getBytes(this.reason));
    }

    public QuitRoomBody(byte[] bytes) throws UnsupportedEncodingException {
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
        QuitRoomReason quitRoomReason = QuitRoomReason.getByReason(reason);
        if (null != quitRoomReason) {
            reasonText = quitRoomReason.toString() + "-" + quitRoomReason.getText();
        } else {
            reasonText = "unknow";
        }
        printStr += "\t\t\t\tid为 " + userId
                + " 的用户退出了id为 " + roomId + "的房间。原因: " + reason + "(" + reasonText + ")";

        return printStr;
    }
}
