package com.jxkj.fit_5a.base;

public class HistoryEquipmentData {
    String img;
    String name_sb;
    String name;
    String time;
    String state;
    String serviceUUid;
    String readUUID;
    String writeUUID;
    String LyAddress;
    String brandId;
    int id;
    String deviceTypeId;
    String deviceModelId;

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public String getReadUUID() {
        return readUUID;
    }

    public void setReadUUID(String readUUID) {
        this.readUUID = readUUID;
    }

    public String getWriteUUID() {
        return writeUUID;
    }

    public void setWriteUUID(String writeUUID) {
        this.writeUUID = writeUUID;
    }

    public String getName_sb() {
        return name_sb;
    }

    public void setName_sb(String name_sb) {
        this.name_sb = name_sb;
    }

    public String getLyAddress() {
        return LyAddress;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setLyAddress(String lyAddress) {
        LyAddress = lyAddress;
    }

    public void setServiceUUid(String serviceUUid) {
        this.serviceUUid = serviceUUid;
    }

    public String getServiceUUid() {
        return serviceUUid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HistoryEquipmentData{" +
                "img='" + img + '\'' +
                ", name_sb='" + name_sb + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", serviceUUid='" + serviceUUid + '\'' +
                ", readUUID='" + readUUID + '\'' +
                ", writeUUID='" + writeUUID + '\'' +
                ", LyAddress='" + LyAddress + '\'' +
                ", brandId='" + brandId + '\'' +
                ", id=" + id +
                ", deviceTypeId='" + deviceTypeId + '\'' +
                ", deviceModelId='" + deviceModelId + '\'' +
                '}';
    }
}
