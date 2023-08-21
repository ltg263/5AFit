package com.jxkj.fit_5a.netty_client.client.listener;


import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import com.jxkj.fit_5a.netty_client.network.NetworkErrorType;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;


public class ConnectionListener implements ChannelFutureListener {
    private NettyClient nettyClient;

    private int attemptsTimes = 0;

    @Setter
    private volatile int currentTimes = 0;

    public int getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(int currentTimes) {
        this.currentTimes = currentTimes;
    }

    public void setAttemptsTimes(int attemptsTimes) {
        this.attemptsTimes = attemptsTimes;
    }


    @Getter
    private int intervalExtensionTime = 0;

    public void setIntervalExtensionTime(int intervalExtensionTime) {
        this.intervalExtensionTime = intervalExtensionTime;
    }

    public int getIntervalExtensionTime() {
        return intervalExtensionTime;
    }

    public ConnectionListener(NettyClient nettyClient, int attemptsTimes) {

        this.nettyClient = nettyClient;
        this.attemptsTimes = attemptsTimes;
    }

    public ConnectionListener(NettyClient nettyClient, int attemptsTimes, int intervalExtensionTime) {
        this(nettyClient, attemptsTimes);
        this.intervalExtensionTime = intervalExtensionTime;
    }

    public int getRemainingTimes() {
        return this.attemptsTimes - this.currentTimes;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        boolean succeed = channelFuture.isSuccess();

        if (!succeed) {
            synchronized (this) {
                if (this.attemptsTimes > this.currentTimes) {
                    this.currentTimes++;

                    // 重连的间隔时间会越来越长
                    int timeout = this.intervalExtensionTime * this.currentTimes;
                    final EventLoop loop = channelFuture.channel().eventLoop();
                    loop.schedule(new Runnable() {
                        @Override
                        public void run() {

                            nettyClient.connectServer();
                        }
                    }, timeout, TimeUnit.SECONDS);
                } else {
                    throw new Exception(NetworkErrorType.TCP_NETTY_ERROR.getSub_code());
                }
            }
        } else {

        }
    }
}