package com.jxkj.fit_5a.netty_client.common._enum;

import lombok.Getter;

@Getter
public enum QuitRoomReason {
    disconnect((byte) 1, "断开连接"),
    quit("主动退出"),
    ;
    private byte reason;
    private String text;

    public byte getReason() {
        return reason;
    }

    public void setReason(byte reason) {
        this.reason = reason;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    QuitRoomReason(String text) {
        this(Counter.nextValue, text);
    }

    QuitRoomReason(byte reason, String text) {
        this.reason = reason;
        this.text = text;
        Counter.nextValue = (byte) (reason + 1);
    }

    private static class Counter {
        private static byte nextValue = 0;
    }

    public static QuitRoomReason getByReason(byte value) {
        for (QuitRoomReason e : QuitRoomReason.values()) {
            if (e.getReason() == value) {
                return e;
            }
        }

        return null;
    }
}
