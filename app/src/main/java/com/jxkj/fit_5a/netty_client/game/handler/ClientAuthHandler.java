package com.jxkj.fit_5a.netty_client.game.handler;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.view.RobotView_Zx;
import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common._enum.MessageType;
import com.jxkj.fit_5a.netty_client.common._enum.RoomMemberChannelStatus;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.common.message.game.body.GameSportsDataNotifBody;
import com.jxkj.fit_5a.netty_client.common.message.game.body.GameSportsDataUploadBody;
import com.jxkj.fit_5a.netty_client.game.resource.RoomChannelAttributeManagement;
import com.jxkj.fit_5a.netty_client.game.utils.SendMessageUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.Getter;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


@ChannelHandler.Sharable
public class ClientAuthHandler extends ChannelInboundHandlerAdapter {
    @Getter
    private Long userId;

    @Getter
    private Long roomId;

    public ClientAuthHandler(Long userId, Long roomId) {
        this.userId = userId;
        this.roomId = roomId;
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
            System.out.println("游戏服务message:" + customMessage.getPrintStr());
            RoomMemberChannelStatus status = RoomChannelAttributeManagement.getChannelStatus(ctx);
            byte messageType = customMessage.getMsgType();
            short subMsgType = customMessage.getSubMsgType();
            if (messageType == MessageType.game.getMsgType()) {
                if (status == RoomMemberChannelStatus.waiting_for_certification) {
                    if (subMsgType == MessageSubType.game_authenticate_notif.getSubMsgType()) {
                        Log.w("this.roomId","this.roomId"+this.roomId);
                        Log.w("this.roomId","this.userId"+this.userId);
                        SendMessageUtils.sendAuthenticateReq(ctx, this.userId, this.roomId);
                    } else if (subMsgType == MessageSubType.game_authenticate_ack.getSubMsgType()) {
                        RoomChannelAttributeManagement.setChannelStatus(ctx, RoomMemberChannelStatus.certified);
                        sendBroadcastGameAuthenticate("game_authenticate_ack");
                    } else if (subMsgType == MessageSubType.room_authenticate_nack.getSubMsgType()) {
                        sendBroadcastGameAuthenticate("game_authenticate_nack");
                    } else {
                        // 一旦消息类型错误就断开用户的连接
                        ctx.channel().close();
                    }
                } else if (status == RoomMemberChannelStatus.certified) {
                    MessageSubType messageSubType = MessageSubType.game_acquisition_delay_req.getByMsgType(messageType, subMsgType);
                    if (null != messageSubType) {
                        switch (messageSubType) {
                            case game_sports_data_notif:
                                ctx.fireChannelRead(customMessage);
                                sendBroadcastRoomNotif(customMessage);
                                break;
                            case game_authenticate_nack:
                                ctx.fireChannelRead(messageSubType);
                                sendBroadcastGameAuthenticate("game_authenticate_nack");
                                break;
                            case game_authenticate_ack:
                                ctx.fireChannelRead(messageSubType);
                                sendBroadcastGameAuthenticate("game_authenticate_ack");
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

    private void sendBroadcastGameAuthenticate(String messageSubType) {
        Intent intent = new Intent("com.jxkj.fit_5a.view.activity.exercise.landscape.CreateRoomMineActivity");  //这里的action要一致。
        intent.putExtra("notif_type",messageSubType);
        MainApplication.getContext().sendBroadcast(intent);
    }
    private void sendBroadcastRoomNotif(CustomMessage notif_type) {
        GameSportsDataNotifBody mGameSportsDataUploadBody = null;
        try {
            mGameSportsDataUploadBody = new GameSportsDataNotifBody(notif_type.getMsgBody());
            System.out.println("mGameSportsDataUploadBody:" + mGameSportsDataUploadBody.getPrintStr());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<GameSportsDataNotifBody.SportData> sportDataList = mGameSportsDataUploadBody.getSportDataList();
        if(mGameSportsDataUploadBody.getSize()>0){
            Intent intent = new Intent("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");  //这里的action要一致。
            intent.putExtra("type","-1");
            intent.putExtra("size",mGameSportsDataUploadBody.getSize());
            intent.putParcelableArrayListExtra("sportDataList", (ArrayList<? extends Parcelable>) sportDataList);
            System.out.println("发送广播:");
            MainApplication.getContext().sendBroadcast(intent);
        }
    }

}
