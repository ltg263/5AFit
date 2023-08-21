package com.jxkj.fit_5a.netty_client.common._enum;

import lombok.Getter;

@Getter
public enum DismissRoomReason {
    disconnect((byte) 1, "断开连接"),
    dismiss("主动解散"),
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

    DismissRoomReason(String text) {
        this(Counter.nextValue, text);
    }

    DismissRoomReason(byte reason, String text) {
        this.reason = reason;
        this.text = text;
        Counter.nextValue = (byte) (reason + 1);
    }

    private static class Counter {
        private static byte nextValue = 0;
    }

    public static DismissRoomReason getByReason(byte value) {
        for (DismissRoomReason e : DismissRoomReason.values()) {
            if (e.getReason() == value) {
                return e;
            }
        }

        return null;
    }
}
