package com.jxkj.fit_5a.netty_client.common._enum;

import lombok.Getter;

@Getter
public enum RoomMemberStatus {
    in_motion(1) /*运动中*/,
    given_up(2) /*已放弃*/,
    completed(3) /*已完成*/,
    ;
    private int value;

    RoomMemberStatus() {
        this(Counter.nextValue);
    }

    RoomMemberStatus(int value) {
        this.value = value;
        Counter.nextValue = value + 1;
    }

    public int getValue() {
        return value;
    }

    private static class Counter {
        private static int nextValue = 0;
    }
}
