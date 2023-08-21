package com.jxkj.fit_5a.netty_client.common.dto;

import lombok.Data;

@Data
public class UserAttrDTO {
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public UserAttrDTO(Long userId) {
        this.userId = userId;
    }
}
