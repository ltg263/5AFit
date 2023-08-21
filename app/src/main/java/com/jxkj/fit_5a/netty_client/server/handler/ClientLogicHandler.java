package com.jxkj.fit_5a.netty_client.server.handler;


import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common._enum.MessageType;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.common.message.room.body.*;
import com.jxkj.fit_5a.netty_client.common.util.Ipv4PortUtils;
import com.jxkj.fit_5a.netty_client.game.service.GameConnectService;
import com.jxkj.fit_5a.netty_client.game.service.impl.GameConnectServiceImpl;

import io.netty.channel.ChannelFuture;
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
            if (messageType == MessageType.room.getMsgType()) {
                if (MessageSubType.room_join_the_room_notif.getSubMsgType() == subMsgType) {
                    JoinRoomBody body = new JoinRoomBody(msgBody);

                } else if (MessageSubType.room_quit_the_room_notif.getSubMsgType() == subMsgType) {
                    QuitRoomBody body = new QuitRoomBody(msgBody);

                } else if (MessageSubType.room_remove_out_of_the_room_notif.getSubMsgType() == subMsgType) {
                    RemoveOutOfRoomBody body = new RemoveOutOfRoomBody(msgBody);

                } else if (MessageSubType.room_dismiss_the_room_notif.getSubMsgType() == subMsgType) {
                    DismissRoomBody body = new DismissRoomBody(msgBody);

                } else if (MessageSubType.room_start_game_notif.getSubMsgType() == subMsgType) {
                    StartRoomBody body = new StartRoomBody(msgBody);

                    GameConnectService gameConnectService = new GameConnectServiceImpl();
                    gameConnectService.addConnect(Ipv4PortUtils.intToIp(body.getIp()), body.getPort(), this.userId, body.getRoomId(), new GameConnectService.ConnectServiceInterface() {
                        @Override
                        public void ConnectServiceInfo(ChannelFuture future) {

                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
