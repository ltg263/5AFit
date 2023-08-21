package com.jxkj.fit_5a.netty_client.server.service;


import io.netty.channel.ChannelFuture;

public interface ConnectService {
    void addConnect(String host, int port, Long userId, String key, ConnectServiceInterface mConnectServiceInterface);

    void removeConnect();

    interface ConnectServiceInterface {
        void ConnectServiceInfo(ChannelFuture future);
    }
}
