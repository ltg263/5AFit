package com.jxkj.fit_5a.netty_client.common._enum;

import com.jxkj.fit_5a.netty_client.network.IMessageType;
import lombok.Getter;

@Getter
public enum MessageType implements IMessageType {
    room((byte) 1, "游戏房间消息"),
    game("游戏内消息"),
    ;
    private byte msgType;
    private String text;

    MessageType(String text) {
        this(Counter.nextValue, text);
    }

    MessageType(byte msgType, String text) {
        this.msgType = msgType;
        this.text = text;
        Counter.nextValue = (byte) (msgType + 1);
    }

    private static class Counter {
        private static byte nextValue = 0;
    }

    @Override
    public byte getMsgType() {
        return msgType;
    }

    @Override
    public String getText() {
        return text;
    }

    public MessageType getByMsgType(byte value) {
        for (MessageType e : MessageType.values()) {
            if (e.getMsgType() == value) {
                return e;
            }
        }

        return null;
    }
}
