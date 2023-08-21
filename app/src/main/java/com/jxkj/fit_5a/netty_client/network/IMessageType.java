package com.jxkj.fit_5a.netty_client.network;

public interface IMessageType {
    byte getMsgType();

    String getText();

    IMessageType getByMsgType(byte value);
}
