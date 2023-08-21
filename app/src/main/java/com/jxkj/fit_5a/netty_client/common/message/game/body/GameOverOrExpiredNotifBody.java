package com.jxkj.fit_5a.netty_client.common.message.game.body;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;

import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class GameOverOrExpiredNotifBody {
    private Long roomId;

    public GameOverOrExpiredNotifBody(Long roomId) {
        this.roomId = roomId;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.roomId));
    }

    public GameOverOrExpiredNotifBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.roomId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
    }

    public String getPrintStr() {
        String printStr = "";

        printStr += "\t\t\t\t房间为(" + roomId +  ")游戏过期或结束通知 ";

        return printStr;
    }
}
