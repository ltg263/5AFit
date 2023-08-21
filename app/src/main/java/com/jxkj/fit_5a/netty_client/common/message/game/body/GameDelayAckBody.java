package com.jxkj.fit_5a.netty_client.common.message.game.body;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class GameDelayAckBody {
    private Long timestamp;

    public GameDelayAckBody() {

    }

    public GameDelayAckBody(Long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.timestamp));
    }

    public GameDelayAckBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;
        this.timestamp = BytesUtils.getLong64(BytesUtils.getBytes1(bytes, index, 8));
        index += 8;
    }

    public String getPrintStr() {
        String printStr = "";

        printStr += "\t\t\t\t获取延迟";

        return printStr;
    }
}
