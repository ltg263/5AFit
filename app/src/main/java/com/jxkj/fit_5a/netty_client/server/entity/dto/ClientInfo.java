package com.jxkj.fit_5a.netty_client.server.entity.dto;


import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ClientInfo {
    private Channel ctx;
    private NettyClient nettyClient;
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCtx(Channel ctx) {
        this.ctx = ctx;
    }

    public void setNettyClient(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    public Channel getCtx() {
        return ctx;
    }

    public NettyClient getNettyClient() {
        return nettyClient;
    }
}
