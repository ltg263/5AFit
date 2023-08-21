package com.jxkj.fit_5a.netty_client.game.utils;

import android.util.Log;

import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.common.message.game.body.GameAuthBody;
import com.jxkj.fit_5a.netty_client.common.message.game.body.GameSportsDataUploadBody;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;


public class SendMessageUtils {
    public static void sendAuthenticateReq(ChannelHandlerContext ctx, Long userId, Long roomId) {
        GameAuthBody body = new GameAuthBody(userId.intValue(), roomId);
        CustomMessage responseMsg = null;
        try {
            responseMsg = new CustomMessage(MessageSubType.game_authenticate_req, body.constructBytes());
            responseMsg.sendMsg(ctx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void sendSportDatasUploadReq(ChannelHandlerContext ctx, Long userId, Integer distance, Long timestamp, Integer speed) {
        GameSportsDataUploadBody body = new GameSportsDataUploadBody(userId.intValue(), distance, timestamp, speed);
        CustomMessage responseMsg = null;
        try {
            responseMsg = new CustomMessage(MessageSubType.game_sports_data_upload, body.constructBytes());
            responseMsg.sendMsg(ctx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void sendAuthenticateReq(Channel ctx, Long userId, Long roomId) {
        GameAuthBody body = new GameAuthBody(userId.intValue(), roomId);
        CustomMessage responseMsg = null;
        try {
            responseMsg = new CustomMessage(MessageSubType.game_authenticate_req, body.constructBytes());
            responseMsg.sendMsg(ctx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param ctx
     * @param userId
     * @param distance
     * @param timestamp
     * @param speed
     */
    public static void sendSportDatasUploadReq(Channel ctx, Long userId, Integer distance, Long timestamp, Integer speed) {
        Log.w("---》》》","userId:"+userId.intValue()+";distance:"+distance+";timestamp:"+timestamp+";speed:"+speed);
        GameSportsDataUploadBody body = new GameSportsDataUploadBody(userId.intValue(), distance, timestamp, speed);
        CustomMessage responseMsg = null;
        try {
            responseMsg = new CustomMessage(MessageSubType.game_sports_data_upload, body.constructBytes());
            responseMsg.sendMsg(ctx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
