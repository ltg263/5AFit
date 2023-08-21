package com.jxkj.fit_5a.netty_client.network;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

public interface IMessage {
    int getMsgHeadLen();

    byte[] getMsgHead();

    void setMsgHead(byte[] msgHead);

    byte getMsgType();

    short getSubMsgType();

    byte getChecksum();

    int getMsgBodyLen();

    byte[] getMsgBody();

    byte[] getCrc16();

    byte[] constructBytes();

    String getPrintStr();

    ChannelFuture sendMsg(ChannelHandlerContext ctx);
}
