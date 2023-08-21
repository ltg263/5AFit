package com.jxkj.fit_5a.netty_client.common.dto;

import lombok.Data;

@Data
public class GameUserAttrDTO {
    private Integer userId;
    private Long roomId;

    public GameUserAttrDTO(Long roomId, Integer userId) {
        this.roomId = roomId;
        this.userId = userId;
    }
}
