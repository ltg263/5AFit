package com.jxkj.fit_5a.netty_client.common._enum;

import lombok.Getter;

@Getter
public enum RoomStatus {
    started(1) /*已开始*/,
    over(2) /*已结束*/,
    ;
    private int value;

    RoomStatus() {
        this(Counter.nextValue);
    }

    RoomStatus(int value) {
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
