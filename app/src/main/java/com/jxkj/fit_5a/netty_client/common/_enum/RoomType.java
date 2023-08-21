package com.jxkj.fit_5a.netty_client.common._enum;

import lombok.Getter;

@Getter
public enum RoomType {
    athletics(1) /*竞技*/,
    quicks(2) /*快速开始*/,
    ;
    private int value;

    RoomType() {
        this(Counter.nextValue);
    }

    RoomType(int value) {
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
