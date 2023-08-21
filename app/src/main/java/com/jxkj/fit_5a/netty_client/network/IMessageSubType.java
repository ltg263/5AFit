package com.jxkj.fit_5a.netty_client.network;

public interface IMessageSubType {
    IMessageType getMsgType();

    short getSubMsgType();

    String getText();

    IMessageSubType getByMsgType(byte msgType, short value);
}
