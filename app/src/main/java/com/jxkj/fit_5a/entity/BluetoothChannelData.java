package com.jxkj.fit_5a.entity;

public class BluetoothChannelData {

    /**
     * id : 2
     * protocolName : iconsole
     * serviceUuid : 49535343-FE7D-4AE5-8FA9-9FAFD205E455
     * characteristicRead : 49535343-8841-43F4-A8D4-ECBE34729BB3
     * characteristicWrite : 49535343-1E4D-4BD9-BA61-23C647249616
     */

    private int id;
    private String protocolName;
    private String serviceUuid;
    private String characteristicRead;
    private String characteristicWrite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getServiceUuid() {
        return serviceUuid;
    }

    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }

    public String getCharacteristicRead() {
        return characteristicRead;
    }

    public void setCharacteristicRead(String characteristicRead) {
        this.characteristicRead = characteristicRead;
    }

    public String getCharacteristicWrite() {
        return characteristicWrite;
    }

    public void setCharacteristicWrite(String characteristicWrite) {
        this.characteristicWrite = characteristicWrite;
    }
}
