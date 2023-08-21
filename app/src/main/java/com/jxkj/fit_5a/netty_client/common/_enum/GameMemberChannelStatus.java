package com.jxkj.fit_5a.netty_client.common._enum;

import lombok.Getter;

@Getter
public enum GameMemberChannelStatus {
    waiting_for_certification(1) /*等待认证*/,
    certified(2) /*认证通过*/,
    ;
    private int value;

    GameMemberChannelStatus() {
        this(Counter.nextValue);
    }

    GameMemberChannelStatus(int value) {
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
