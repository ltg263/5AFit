package com.jxkj.fit_5a.netty_client.server.handler;

import android.content.Intent;
import android.util.Log;

import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common._enum.MessageType;
import com.jxkj.fit_5a.netty_client.common._enum.RoomMemberChannelStatus;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.common.message.room.body.StartRoomBody;
import com.jxkj.fit_5a.netty_client.common.util.Ipv4PortUtils;
import com.jxkj.fit_5a.netty_client.game.service.GameConnectService;
import com.jxkj.fit_5a.netty_client.game.service.impl.GameConnectServiceImpl;
import com.jxkj.fit_5a.netty_client.server.resource.RoomChannelAttributeManagement;
import com.jxkj.fit_5a.netty_client.server.utils.SendMessageUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.Getter;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;


@ChannelHandler.Sharable
public class ClientAuthHandler extends ChannelInboundHandlerAdapter {
    @Getter
    private Long userId;
    @Getter
    private String key;

    public ClientAuthHandler(Long userId, String key) {
        this.userId = userId;
        this.key = key;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel失效处理,客户端下线或者强制退出等任何情况都触发这个方法
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();


        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();


        RoomChannelAttributeManagement.setChannelStatus(ctx, RoomMemberChannelStatus.waiting_for_certification);


        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object request) throws Exception {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        InetSocketAddress localAddress = (InetSocketAddress) ctx.channel().localAddress();

        try {
            ByteBuf byteBuf = (ByteBuf) request;

            CustomMessage customMessage = new CustomMessage(byteBuf);

            // print
            System.out.println("房间服务message:" + customMessage.getPrintStr());
            RoomMemberChannelStatus status = RoomChannelAttributeManagement.getChannelStatus(ctx);
            byte messageType = customMessage.getMsgType();
            short subMsgType = customMessage.getSubMsgType();
            if (messageType == MessageType.room.getMsgType()) {
                if (status == RoomMemberChannelStatus.waiting_for_certification) {
                    if (subMsgType == MessageSubType.room_authenticate_notif.getSubMsgType()) {
                        SendMessageUtils.sendAuthenticateReq(ctx, userId, key);
                    } else if (subMsgType == MessageSubType.room_authenticate_ack.getSubMsgType()) {
                        // print
                        RoomChannelAttributeManagement.setChannelStatus(ctx, RoomMemberChannelStatus.certified);
                    } else if (subMsgType == MessageSubType.room_authenticate_nack.getSubMsgType()) {
                        // print

                    } else {
                        // 一旦消息类型错误就断开用户的连接
                        ctx.channel().close();
                    }
                } else if (status == RoomMemberChannelStatus.certified) {
                    MessageSubType messageSubType = MessageSubType.game_acquisition_delay_req.getByMsgType(messageType, subMsgType);
                    if (null != messageSubType) {
                        switch (messageSubType) {
                            case room_join_the_room_notif://玩家进入房间通知
                                sendBroadcastRoomNotif("room_join_the_room_notif",null);
                                break;
                            case room_quit_the_room_notif://玩家退出房间通知
                                sendBroadcastRoomNotif("room_quit_the_room_notif",null);
                                break;
                            case room_dismiss_the_room_notif://房主解散房间通知
                                sendBroadcastRoomNotif("room_dismiss_the_room_notif",null);
                                ctx.fireChannelRead(customMessage);
                                break;
                            case room_start_game_notif://房主开始游戏通知
                                roomStartGameNotif(customMessage);
                                break;
                            case room_remove_out_of_the_room_notif:
                                ctx.fireChannelRead(customMessage);
                                break;
                            default:

                                break;
                        }
                    } else {

                    }
                } else {
                    // 一旦消息类型错误就断开用户的连接
                    ctx.channel().close();
                }
            } else {
                // 一旦消息类型错误就断开用户的连接
                ctx.channel().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(request);
        }
    }


    private void roomStartGameNotif(CustomMessage customMessage) {

        StartRoomBody body = null;
        try {
            body = new StartRoomBody(customMessage.getMsgBody());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        GameConnectServiceImpl gameConnectService = new GameConnectServiceImpl();
        StartRoomBody finalBody = body;
        gameConnectService.addConnect(Ipv4PortUtils.intToIp(body.getIp()), body.getPort(), this.userId, body.getRoomId(), new GameConnectService.ConnectServiceInterface() {
            @Override
            public void ConnectServiceInfo(ChannelFuture future) {
                Log.w("---》》","future.channel():"+future.channel());
                sendBroadcastRoomNotif("room_start_game_notif",finalBody.getRoomMemberId()+"");

            }
        });
    }

    private void sendBroadcastRoomNotif(String notif_type,String roomMemberId) {
        Intent intent = new Intent("com.jxkj.fit_5a.view.activity.exercise.landscape.CreateRoomMineActivity");  //这里的action要一致。
        intent.putExtra("notif_type", notif_type);
        intent.putExtra("roomMemberId", roomMemberId);
        MainApplication.getContext().sendBroadcast(intent);
    }

}
