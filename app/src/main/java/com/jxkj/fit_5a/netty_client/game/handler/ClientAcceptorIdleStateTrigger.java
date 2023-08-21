package com.jxkj.fit_5a.netty_client.game.handler;

import com.jxkj.fit_5a.netty_client.game.utils.SendMessageUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetSocketAddress;


@ChannelHandler.Sharable
public class ClientAcceptorIdleStateTrigger extends ChannelInboundHandlerAdapter {
    private int distance = 1;
    private Long userId;

    public ClientAcceptorIdleStateTrigger(Long userId) {
        this.userId = userId;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();

            if (event.state() == IdleState.READER_IDLE) {
//				System.out.println("hardware客户端("+remoteAddress.getAddress().toString()+":" + remoteAddress.getPort() + ")很久没有向hardware服务器("+
//				localAddress.getAddress().toString()+":" + localAddress.getPort() +")发送心跳包, 类型：read idle");

//				ctx.channel().close();
            } else if (event.state() == IdleState.WRITER_IDLE) {
//                SendMessageUtils.sendSportDatasUploadReq(ctx, userId, distance++, System.currentTimeMillis(), distance++);
            } else if (event.state() == IdleState.ALL_IDLE) {

            }

        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
