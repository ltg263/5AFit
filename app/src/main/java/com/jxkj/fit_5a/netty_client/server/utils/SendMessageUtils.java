package com.jxkj.fit_5a.netty_client.server.utils;

import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.common.message.room.body.RoomAuthBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;


public class SendMessageUtils {
    public static void sendAuthenticateReq(ChannelHandlerContext ctx, Long userId, String key) {
        RoomAuthBody body = new RoomAuthBody(userId.intValue(), key);
        CustomMessage responseMsg = null;
        try {
            responseMsg = new CustomMessage(MessageSubType.room_authenticate_req, body.constructBytes());
            responseMsg.sendMsg(ctx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void sendAuthenticateReq(Channel channel, Long userId, String key) {
        RoomAuthBody body = new RoomAuthBody(userId.intValue(), key);
        CustomMessage responseMsg = null;
        try {
            responseMsg = new CustomMessage(MessageSubType.room_authenticate_req, body.constructBytes());
            responseMsg.sendMsg(channel);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
