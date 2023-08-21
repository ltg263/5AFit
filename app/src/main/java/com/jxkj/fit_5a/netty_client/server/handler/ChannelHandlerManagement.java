package com.jxkj.fit_5a.netty_client.server.handler;


import com.jxkj.fit_5a.netty_client.network.IChannelHandlerManagement;
import io.netty.channel.ChannelHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

public class ChannelHandlerManagement implements IChannelHandlerManagement {
    @Getter
    @Setter
    private int readerIdletime;                                //读操作空闲时间
    @Getter
    @Setter
    private int writerIdletime;                                //写操作空闲时间
    @Getter
    @Setter
    private int allIdletime;                                    //读写全部空闲时间

    private ClientAuthHandler clientAuthHandler;
    private ClientAcceptorIdleStateTrigger clientAcceptorIdleStateTrigger = new ClientAcceptorIdleStateTrigger();
    private ClientLogicHandler clientLogicHandler;

    public ChannelHandlerManagement(int readerIdletime, int writerIdletime, int allIdletime, Long userId, String key) {
        this.clientAuthHandler = new ClientAuthHandler(userId, key);
        this.clientLogicHandler = new ClientLogicHandler(userId);
        this.readerIdletime = readerIdletime;
        this.writerIdletime = writerIdletime;
        this.allIdletime = allIdletime;
    }

    @Override
    public ChannelHandler[] constructChannelHandlers() {
        return new ChannelHandler[]{
                clientAuthHandler,
                new IdleStateHandler(this.readerIdletime, this.writerIdletime, this.allIdletime, TimeUnit.SECONDS),
                clientAcceptorIdleStateTrigger,
                clientLogicHandler
        };
    }
}
