package com.jxkj.fit_5a.netty_client.client.handler;


import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import com.jxkj.fit_5a.netty_client.client.listener.ConnectionListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


@ChannelHandler.Sharable
public class ReconnectHandler extends ChannelInboundHandlerAdapter {
    private NettyClient nettyClient;
    private boolean reconnect;

    @Setter
    private volatile boolean isInit = true;


    public ReconnectHandler(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();


        this.isInit = true;

        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();

        if (!this.nettyClient.isReconnect()) {
            return;
        }

        synchronized (this.nettyClient) {
            if (this.isInit) {
                this.isInit = false;

                if (0 < this.nettyClient.getAttemptsTimes()) {
                    int currentTimes = 1;
                    ConnectionListener connectionListener = this.nettyClient.getConnectionListener();
                    connectionListener.setCurrentTimes(currentTimes);
                    // 重连的间隔时间会越来越长
                    int timeout = connectionListener.getIntervalExtensionTime() * currentTimes;
                    final EventLoop loop = ctx.channel().eventLoop();
                    loop.schedule(new Runnable() {
                        @Override
                        public void run() {

                            nettyClient.connectServer();
                        }
                    }, timeout, TimeUnit.SECONDS);
                }
            }
        }

        // 注意里面会调用fireChannelInactive
        super.channelInactive(ctx);
    }
}
