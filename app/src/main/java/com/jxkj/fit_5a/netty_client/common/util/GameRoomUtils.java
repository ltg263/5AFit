package com.jxkj.fit_5a.netty_client.common.util;


public class GameRoomUtils {
    public static String constructRoomMemberId(Long roomId, Long userId) {
        return roomId.toString().concat("-").concat(userId.toString());
    }
}
