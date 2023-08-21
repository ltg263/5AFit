package com.jxkj.fit_5a.netty_client.server.service.impl;


import android.util.Log;

import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import com.jxkj.fit_5a.netty_client.server.entity.dto.ClientInfo;
import com.jxkj.fit_5a.netty_client.server.handler.ChannelHandlerManagement;
import com.jxkj.fit_5a.netty_client.server.service.ConnectService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;


public class ConnectServiceImpl implements ConnectService {
    private ClientInfo clientInfo;

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    @Override
    public void addConnect(String host, int port, Long userId, String key,ConnectServiceInterface mConnectServiceSuccess) {

        clientInfo = new ClientInfo();
        clientInfo.setUserId(userId);
        NettyClient nettyClient = new NettyClient(host, port, 8,
                false, 1, 1,
                new ChannelHandlerManagement(20, 20, 20, userId, key));

        clientInfo.setNettyClient(nettyClient);

        ChannelFuture channelFuture = nettyClient.connectServer(true);

        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                Log.d("ConnectServiceImpl", "operationComplete: "+future.toString());
                if (future.isSuccess()) {
                    clientInfo.setCtx(future.channel());
                }
                mConnectServiceSuccess.ConnectServiceInfo(future);
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
