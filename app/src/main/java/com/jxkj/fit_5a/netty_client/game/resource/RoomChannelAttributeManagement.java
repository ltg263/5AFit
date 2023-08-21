package com.jxkj.fit_5a.netty_client.game.resource;

import com.jxkj.fit_5a.netty_client.common._enum.RoomMemberChannelStatus;
import com.jxkj.fit_5a.netty_client.common.constants.RoomConstants;
import com.jxkj.fit_5a.netty_client.common.dto.UserAttrDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;

public class RoomChannelAttributeManagement {

    public static RoomMemberChannelStatus getChannelStatus(ChannelHandlerContext ctx) {
        Attribute<RoomMemberChannelStatus> attr = ctx.channel().attr(RoomConstants.room_channel_auth_status);
        RoomMemberChannelStatus status = attr.get();

        return status;
    }

    public static void setChannelStatus(ChannelHandlerContext ctx, RoomMemberChannelStatus status) {
        Attribute<RoomMemberChannelStatus> attr = ctx.channel().attr(RoomConstants.room_channel_auth_status);

        attr.set(status);
    }

    public static UserAttrDTO getUserInfo(ChannelHandlerContext ctx) {
        Attribute<UserAttrDTO> attr = ctx.channel().attr(RoomConstants.room_channel_user_key);
        UserAttrDTO userAttrDTO = attr.get();

        return userAttrDTO;
    }

    public static Long getUserId(ChannelHandlerContext ctx) {
        Attribute<UserAttrDTO> attr = ctx.channel().attr(RoomConstants.room_channel_user_key);
        UserAttrDTO userAttrDTO = attr.get();

        return null == userAttrDTO ? null : userAttrDTO.getUserId();
    }

    public static void setUserInfo(ChannelHandlerContext ctx, UserAttrDTO userAttrDTO) {
        Attribute<UserAttrDTO> attr = ctx.channel().attr(RoomConstants.room_channel_user_key);

        attr.set(userAttrDTO);
    }
}
