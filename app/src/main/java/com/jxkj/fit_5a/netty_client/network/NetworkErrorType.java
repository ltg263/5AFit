package com.jxkj.fit_5a.netty_client.network;

import lombok.Getter;

@Getter
public enum NetworkErrorType {
    CHECKSUM_ERROR("010001", "验效和验证错误(%s-%s)"),
    CHECKCRC16_ERROR("010001", "消息类型(%s-%s)\r\n 消息(%s)\r\n 消息bytes(%s)\r\n crc16验证错误(%s-%s)"),

    TCP_NETTY_ERROR("020001", "netty异常"),
    TCP_NETTY_BIND_PORT_FAILD("020002", "netty绑定端口(%s)失败, 该端口已经被绑定"),
    TCP_NETTY_START_FAILD("020003", "netty启动失败(%s)"),
    TCP_NETTY_CONNECT_FAILD("020004", "netty连接失败"),
    TCP_NETTY_MIN_RECONNECTIONS_ERROR("020005", "重连次数最小为1"),


    ;

    /**
     * 错误类型码
     */
    private String sub_code;
    /**
     * 错误类型描述信息
     */
    private String sub_mesg;

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_mesg() {
        return sub_mesg;
    }

    public void setSub_mesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    NetworkErrorType(String sub_code, String sub_mesg) {
        this.sub_code = sub_code;
        this.sub_mesg = sub_mesg;
    }

}
