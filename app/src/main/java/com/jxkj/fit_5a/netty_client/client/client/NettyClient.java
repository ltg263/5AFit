package com.jxkj.fit_5a.netty_client.client.client;


import com.jxkj.fit_5a.netty_client.client.listener.ConnectionListener;
import com.jxkj.fit_5a.netty_client.client.handler.ReconnectHandler;
import com.jxkj.fit_5a.netty_client.network.IChannelHandlerManagement;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Getter;


public class NettyClient {
    @Getter
    private Bootstrap bootstrap;
    @Getter
    private int workerThreadCount;
    @Getter
    private String host;
    @Getter
    private int port;
    @Getter
    private volatile boolean reconnect = true;
    @Getter
    private int attemptsTimes = 0;
    @Getter
    private int intervalExtensionTime = 0;
    @Getter
    private volatile ChannelFuture future;
    private EventLoopGroup workerGroup = null;
    private IChannelHandlerManagement channelHandlerManagement;
    @Getter
    private ConnectionListener connectionListener;
    private ReconnectHandler reconnectHandler;

    private NettyClient() {

    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public int getWorkerThreadCount() {
        return workerThreadCount;
    }

    public void setWorkerThreadCount(int workerThreadCount) {
        this.workerThreadCount = workerThreadCount;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isReconnect() {
        return reconnect;
    }

    public void setReconnect(boolean reconnect) {
        this.reconnect = reconnect;
    }

    public int getAttemptsTimes() {
        return attemptsTimes;
    }

    public void setAttemptsTimes(int attemptsTimes) {
        this.attemptsTimes = attemptsTimes;
    }

//    public int getIntervalExtensionTime() {
//        return intervalExtensionTime;
//    }

    public void setIntervalExtensionTime(int intervalExtensionTime) {
        this.intervalExtensionTime = intervalExtensionTime;
    }

    public ConnectionListener getConnectionListener() {
        return connectionListener;
    }

    public void setConnectionListener(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    public NettyClient(String host, int port, int workerThreadCount, boolean reconnect, int attemptsTimes, int intervalExtensionTime,
                       IChannelHandlerManagement channelHandlerManagement) {
        this(host, port, channelHandlerManagement);

        this.workerThreadCount = workerThreadCount;
        this.reconnect = reconnect;
        this.attemptsTimes = attemptsTimes;
        this.intervalExtensionTime = intervalExtensionTime;
    }

    public NettyClient(String host, int port, IChannelHandlerManagement channelHandlerManagement) {
        this.host = host;
        this.port = port;
        this.channelHandlerManagement = channelHandlerManagement;
    }

    private boolean initBootstrap = false;

    private void initClientBootstrap() {
        this.workerGroup = null == this.workerGroup ? new NioEventLoopGroup(workerThreadCount) : this.workerGroup;
        this.bootstrap = null == this.bootstrap ? new Bootstrap() : this.bootstrap;

        if (!this.initBootstrap) {
            if (0 < this.attemptsTimes) {
                this.reconnectHandler = new ReconnectHandler(this);
                this.connectionListener = new ConnectionListener(this, this.attemptsTimes, this.intervalExtensionTime);
            }

            this.bootstrap.group(this.workerGroup).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            if (0 < attemptsTimes) {
                                ch.pipeline().addLast(reconnectHandler);
                            }
                            ch.pipeline().addLast(channelHandlerManagement.constructChannelHandlers());
                        }
                    });

            this.initBootstrap = true;
        }
    }

    /**
     * 连接
     *
     * @param port 端口
     * @param host host
     * @param sync
     * @return
     */
    public ChannelFuture connectServer(String host, int port, boolean sync) {
        this.host = host;
        this.port = port;
        initClientBootstrap();
        try {
            synchronized (this) {
                this.future = this.bootstrap.connect(host, port);
            }

            if (sync && this.future.isSuccess()) {
                this.future.sync();
            }

            if (0 < this.attemptsTimes) {
                this.future.addListener(this.connectionListener);
            }

            return this.future;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 重新连接
     *
     * @param port 端口
     * @param host host
     * @return
     */
    public ChannelFuture connectServer(String host, int port) {
        return connectServer(host, port, true);
    }

    public ChannelFuture connectServer(boolean sync) {
        return connectServer(this.host, this.port, sync);
    }

    public ChannelFuture connectServer() {
        return connectServer(true);
    }

    public void shutdown() {
        if (null != this.workerGroup) {
            this.workerGroup.shutdownGracefully();
            this.workerGroup = null;

        }

        if (null != this.bootstrap) {
            this.bootstrap = null;
        }

        if (null != this.future) {
            this.future.channel().closeFuture();
            this.future = null;
        }

    }
}
