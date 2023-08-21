package com.jxkj.fit_5a.netty_client.common.message.game.body;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class GameGiveUpBody {
    private Integer userId;
    private Long roomId;

    public GameGiveUpBody(Integer userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), BytesUtils.getBytes(this.roomId));
    }

    public GameGiveUpBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.roomId = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
    }
}
