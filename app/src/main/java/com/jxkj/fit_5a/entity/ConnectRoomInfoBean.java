package com.jxkj.fit_5a.entity;

public class ConnectRoomInfoBean {

    /**
     * connectionPassword : T9E5JZZqvS1
     * roomServerHost : 120.26.32.253
     * roomServerPort : 13001
     */

    private String connectionPassword;
    private String roomServerHost;
    private int roomServerPort;

    public String getConnectionPassword() {
        return connectionPassword;
    }

    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }

    public String getRoomServerHost() {
        return roomServerHost;
    }

    public void setRoomServerHost(String roomServerHost) {
        this.roomServerHost = roomServerHost;
    }

    public int getRoomServerPort() {
        return roomServerPort;
    }

    public void setRoomServerPort(int roomServerPort) {
        this.roomServerPort = roomServerPort;
    }
}
