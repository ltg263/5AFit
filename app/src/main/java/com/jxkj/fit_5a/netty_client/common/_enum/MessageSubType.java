package com.jxkj.fit_5a.netty_client.common._enum;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxkj.fit_5a.netty_client.network.IMessageSubType;
import com.jxkj.fit_5a.netty_client.network.IMessageType;

import lombok.Getter;

@Getter
public enum MessageSubType implements IMessageSubType {
    room_authenticate_notif(MessageType.room, (short) 1, "通知玩家进行认证请求"),
    room_authenticate_req(MessageType.room, "玩家认证请求"),
    room_authenticate_ack(MessageType.room, "玩家认证请求通过"),
    room_authenticate_nack(MessageType.room, "拒绝玩家认证请求"),
    room_join_the_room_notif(MessageType.room, "玩家进入房间通知"),
    room_quit_the_room_notif(MessageType.room, "玩家退出房间通知"),
    room_remove_out_of_the_room_notif(MessageType.room, "玩家被房主移出房间通知"),
    room_dismiss_the_room_notif(MessageType.room, "房主解散房间通知"),
    room_start_game_notif(MessageType.room, "房主开始游戏通知"),

    game_authenticate_notif(MessageType.game, (short) 1, "通知玩家进行认证请求"),
    game_authenticate_req(MessageType.game, "玩家认证请求"),
    game_authenticate_ack(MessageType.game, "玩家认证请求通过"),
    game_authenticate_nack(MessageType.game, "拒绝玩家认证请求"),
    game_acquisition_delay_req(MessageType.game, "获取延迟"),
    game_acquisition_delay_ack(MessageType.game, "回应获取延迟"),
    game_sports_data_upload(MessageType.game, "玩家运动数据上报(收到上报信息后，服务器会主动推送一次game_sports_data_notif)"),
    game_sports_data_req(MessageType.game, "获取玩家运动数据请求"),
    game_sports_data_notif(MessageType.game, "玩家运动数据通知"),
    game_sports_give_up_notif(MessageType.game, "玩家放弃游戏通知"),
    game_sports_complete_notif(MessageType.game, "玩家完成游戏通知"),
    game_sports_expired_or_over_notif(MessageType.game, "游戏结束或过期通知"),
    ;

    private MessageType msgType;
    private short subMsgType;
    private String text;

    MessageSubType(MessageType msgType, String text) {
        this(msgType, Counter.nextValue, text);
    }

    MessageSubType(MessageType msgType, short subMsgType, String text) {
        this.msgType = msgType;
        this.subMsgType = subMsgType;
        this.text = text;
        Counter.nextValue = (short) (subMsgType + 1);
    }

    private static class Counter {
        private static short nextValue = 0;
    }

    @Getter(lombok.AccessLevel.NONE)
    private static JSONArray messageTypeInfo = null;

    public static JSONArray getMessageTypeInfo() {
        if (null == messageTypeInfo) {
            messageTypeInfo = new JSONArray();
            for (MessageSubType e : MessageSubType.values()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", e.toString());
                jsonObject.put("msgType", e.getMsgType().getMsgType());
                jsonObject.put("msgTypeText", e.getMsgType().getText());
                jsonObject.put("subMsgType", e.getSubMsgType());
                jsonObject.put("subMsgTypeText", e.getText());

                messageTypeInfo.add(jsonObject);
            }
        }

        return messageTypeInfo;
    }

    @Override
    public IMessageType getMsgType() {
        return msgType;
    }

    @Override
    public short getSubMsgType() {
        return subMsgType;
    }

    @Override
    public String getText() {
        return text;
    }

    public MessageSubType getByMsgType(byte msgType, short value) {
        for (MessageSubType e : MessageSubType.values()) {
            if (e.getMsgType().getMsgType() == msgType && e.getSubMsgType() == value) {
                return e;
            }
        }

        return null;
    }

    public static MessageSubType getByMsgType1(byte msgType, short value) {
        for (MessageSubType e : MessageSubType.values()) {
            if (e.getMsgType().getMsgType() == msgType && e.getSubMsgType() == value) {
                return e;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(MessageSubType.getMessageTypeInfo());
    }
}
