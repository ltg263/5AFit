package com.jxkj.fit_5a.netty_client.game.service;


import io.netty.channel.ChannelFuture;

public interface GameConnectService {

    void addConnect(String host, int port, Long userId, Long roomId,ConnectServiceInterface mConnectServiceInterface);

    void removeConnect();

    interface ConnectServiceInterface {
        void ConnectServiceInfo(ChannelFuture future);
    }
}
