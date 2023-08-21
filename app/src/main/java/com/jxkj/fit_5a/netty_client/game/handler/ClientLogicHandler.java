package com.jxkj.fit_5a.netty_client.game.handler;


import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common._enum.MessageType;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.common.message.game.body.GameSportsDataNotifBody;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Getter;

import java.net.InetSocketAddress;


@ChannelHandler.Sharable
public class ClientLogicHandler extends ChannelInboundHandlerAdapter {
    @Getter
    private Long userId;

    public ClientLogicHandler(Long userId) {
        this.userId = userId;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object request) throws Exception {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();

        try {
            CustomMessage customMessage = (CustomMessage) request;


            byte[] msgBody = customMessage.getMsgBody();
            byte messageType = customMessage.getMsgType();
            short subMsgType = customMessage.getSubMsgType();
            if (messageType == MessageType.game.getMsgType()) {
                if (MessageSubType.game_sports_data_notif.getSubMsgType() == subMsgType) {
                    GameSportsDataNotifBody body = new GameSportsDataNotifBody(msgBody);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
