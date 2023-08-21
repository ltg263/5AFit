package com.jxkj.fit_5a.netty_client.game.service.impl;

import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import com.jxkj.fit_5a.netty_client.game.entity.dto.ClientInfo;
import com.jxkj.fit_5a.netty_client.game.handler.ChannelHandlerManagement;
import com.jxkj.fit_5a.netty_client.game.service.GameConnectService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class GameConnectServiceImpl implements GameConnectService {

    public static ClientInfo clientInfo;

    @Override
    public void addConnect(String host, int port, Long userId, Long roomId,ConnectServiceInterface mConnectServiceInterface) {
        if (null != clientInfo) {
            removeConnect();
        }

        clientInfo = new ClientInfo();
        NettyClient nettyClient = new NettyClient(host, port, 8,
                false, 1, 1,
                new ChannelHandlerManagement(20, 20, 20, userId, roomId));

        clientInfo.setNettyClient(nettyClient);


        ChannelFuture channelFuture = nettyClient.connectServer(true);

        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    clientInfo.setCtx(future.channel());
                }
                mConnectServiceInterface.ConnectServiceInfo(future);
            }
        });
    }

    @Override
    public void removeConnect() {
        if (null != clientInfo) {
            Channel channel = clientInfo.getCtx();
            if (null != channel && channel.isOpen()) {
                ChannelFuture channelFuture = channel.close();
                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {

                        }
                    }
                });
            }
            clientInfo.getNettyClient().shutdown();
            clientInfo = null;
        }
    }
}
