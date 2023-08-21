package com.jxkj.fit_5a.netty_client.server.service.entity.dto;


import com.jxkj.fit_5a.netty_client.client.client.NettyClient;
import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ClientInfo {
    private Channel ctx;
    private NettyClient nettyClient;
    private String userId;
}
