package com.jxkj.fit_5a.netty_client.common.constants;


import com.jxkj.fit_5a.netty_client.common._enum.GameMemberChannelStatus;
import com.jxkj.fit_5a.netty_client.common._enum.RoomMemberChannelStatus;
import com.jxkj.fit_5a.netty_client.common.dto.GameUserAttrDTO;
import com.jxkj.fit_5a.netty_client.common.dto.UserAttrDTO;
import io.netty.util.AttributeKey;

public class RoomConstants {
    public static final String charsetName = "UTF-8";

    public static final int room_success = 1; // 房间人员相关操作成功
    public static final int room_fail = -1; // 房间人员相关操作失败

    public static final AttributeKey<UserAttrDTO> room_channel_user_key = AttributeKey.valueOf("room.channel.user.key");
    public static final AttributeKey<RoomMemberChannelStatus> room_channel_auth_status = AttributeKey.valueOf("room.channel.auth.status");


    public static final AttributeKey<GameUserAttrDTO> game_channel_user_key = AttributeKey.valueOf("game.channel.user.key");
    public static final AttributeKey<GameMemberChannelStatus> game_channel_auth_status = AttributeKey.valueOf("game.channel.auth.status");

}
