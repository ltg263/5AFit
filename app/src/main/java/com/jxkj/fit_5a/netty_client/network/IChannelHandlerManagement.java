package com.jxkj.fit_5a.netty_client.network;

import io.netty.channel.ChannelHandler;

public interface IChannelHandlerManagement {
    // 管理
    ChannelHandler[] constructChannelHandlers();
}
