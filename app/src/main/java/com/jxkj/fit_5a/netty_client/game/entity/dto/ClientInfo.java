package com.jxkj.fit_5a.netty_client.game.entity.dto;

import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ClientInfo {
    private Channel ctx;
    private NettyClient nettyClient;

    public Channel getCtx() {
        return ctx;
    }

    public void setCtx(Channel ctx) {
        this.ctx = ctx;
    }

    public NettyClient getNettyClient() {
        return nettyClient;
    }

    public void setNettyClient(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }
}
