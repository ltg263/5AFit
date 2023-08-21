package com.jxkj.fit_5a.netty_client.common.message;


import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.network.IMessageSubType;
import com.jxkj.fit_5a.netty_client.network.Message;
import io.netty.buffer.ByteBuf;
import lombok.Setter;

public class CustomMessage extends Message {
    public static byte[] head = "5afit".getBytes();

    @Override
    public void setMsgHead(byte[] msgHead) {
        this.msgHead = msgHead;
    }

    @Setter
    private byte[] msgHead;


    public CustomMessage(ByteBuf byteBuf, boolean bCopy) throws Exception {
        super(byteBuf, bCopy);
    }

    public CustomMessage(ByteBuf byteBuf) throws Exception {
        super(byteBuf);
    }

    public CustomMessage(IMessageSubType messageType, byte[] msgBody) {
        super(head, messageType, msgBody);
    }

    public CustomMessage(IMessageSubType messageType) {
        super(head, messageType);
    }

    @Override
    public int getMsgHeadLen() {
        return CustomMessage.head.length;
    }

    @Override
    public byte[] getMsgHead() {
        return CustomMessage.head;
    }

    public String getPrintStr() {
        // 这里随便传入一个值
        return super.getPrintStr(MessageSubType.room_authenticate_nack);
    }

    public static void main(String[] args) {
        byte[] head = "5afit".getBytes();
        System.out.println(head);
    }

}
