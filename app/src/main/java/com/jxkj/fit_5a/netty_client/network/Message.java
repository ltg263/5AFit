package com.jxkj.fit_5a.netty_client.network;

import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import com.jxkj.fit_5a.netty_client.network.util.CRC16Utils;
import com.jxkj.fit_5a.netty_client.network.util.ChecksumUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public abstract class Message implements IMessage {
    private byte msgType;
    private short subMsgType;
    private byte checksum;
    private int msgBodyLen;
    private byte[] msgBody;
    private byte[] crc16;

    private Message() {

    }

    @Override
    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    @Override
    public short getSubMsgType() {
        return subMsgType;
    }

    public void setSubMsgType(short subMsgType) {
        this.subMsgType = subMsgType;
    }

    @Override
    public byte getChecksum() {
        return checksum;
    }

    public void setChecksum(byte checksum) {
        this.checksum = checksum;
    }

    @Override
    public int getMsgBodyLen() {
        return msgBodyLen;
    }

    public void setMsgBodyLen(int msgBodyLen) {
        this.msgBodyLen = msgBodyLen;
    }

    @Override
    public byte[] getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(byte[] msgBody) {
        this.msgBody = msgBody;
    }

    @Override
    public byte[] getCrc16() {
        return crc16;
    }

    public void setCrc16(byte[] crc16) {
        this.crc16 = crc16;
    }

    @Override
    public byte[] constructBytes() {
        return BytesUtils.concat(this.getMsgHead(), BytesUtils.getBytes(this.msgType), BytesUtils.getBytes(this.subMsgType),
                BytesUtils.getBytes(this.checksum), BytesUtils.getBytes(this.msgBodyLen), msgBody, this.crc16);
    }

    public void newMessage(byte[] bytes) throws Exception {
        int index = 0;

        int headLen = getMsgHeadLen();
        this.setMsgHead(BytesUtils.getBytes1(bytes, index, headLen));
        index += headLen;

        this.msgType = bytes[index++];

        this.subMsgType = BytesUtils.getShort(bytes[index++], bytes[index++]);

        int checksum = ChecksumUtils.getChecksum(BytesUtils.getBytes(bytes, 0, index));
        this.checksum = bytes[index++];
        if (checksum != this.checksum) {
            throw new Exception(String.format(NetworkErrorType.CHECKSUM_ERROR.getSub_mesg(), this.checksum + "", checksum + ""));
        }

        this.msgBodyLen = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        byte[] msgBodyLenBytes = BytesUtils.getBytes(this.msgBodyLen);
        index += msgBodyLenBytes.length;

        this.msgBody = BytesUtils.getBytes1(bytes, index, this.msgBodyLen);
        index += this.msgBodyLen;

        this.crc16 = BytesUtils.getBytes1(bytes, index, 2);
        if (!CRC16Utils.checkBuf(BytesUtils.concat(BytesUtils.getBytes(this.msgType),
                BytesUtils.getBytes(this.subMsgType), BytesUtils.getBytes(this.checksum), msgBodyLenBytes, msgBody, this.crc16))) {
            byte[] crc16 = CRC16Utils.getCrc16Bytes(BytesUtils.concat(BytesUtils.getBytes(this.msgType),
                    BytesUtils.getBytes(this.subMsgType), BytesUtils.getBytes(this.checksum), msgBodyLenBytes, msgBody));

            CRC16Utils.checkBuf(BytesUtils.concat(BytesUtils.getBytes(this.msgType),
                    BytesUtils.getBytes(this.subMsgType), BytesUtils.getBytes(this.checksum), msgBodyLenBytes, msgBody, this.crc16));

            throw new Exception(String.format(NetworkErrorType.CHECKCRC16_ERROR.getSub_mesg(), this.msgType, this.subMsgType, this.getPrintStr(null), BytesUtils.bytesToHexString(bytes), BytesUtils.bytesToHexString(this.crc16), BytesUtils.bytesToHexString(crc16)));
        }
    }

    public Message(ByteBuf byteBuf, boolean bCopy) throws Exception {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        if (bCopy) {
            ByteBuf resultCopy = byteBuf.copy();
            try {
                resultCopy.readBytes(bytes);
            } finally {
                resultCopy.release();
            }
        } else {
            byteBuf.readBytes(bytes);
        }

        newMessage(bytes);
    }

    public Message(ByteBuf byteBuf) throws Exception {
        this(byteBuf, false);
    }

    public Message(byte[] msgHead, IMessageSubType messageType, byte[] msgBody) {
        this.setMsgHead(msgHead);

        IMessageType aa = messageType.getMsgType();
        Object CC = aa.getText();
        Object bb = aa.getMsgType();
        this.msgType = messageType.getMsgType().getMsgType();
        byte[] msgTypeBytes = BytesUtils.getBytes(this.msgType);

        this.subMsgType = messageType.getSubMsgType();
        byte[] subMsgTypeBytes = BytesUtils.getBytes(this.subMsgType);

        this.checksum = ChecksumUtils.getChecksum(BytesUtils.concat(msgHead, msgTypeBytes, subMsgTypeBytes));
        byte[] checksumBytes = BytesUtils.getBytes(this.checksum);

        this.msgBodyLen = msgBody.length;
        byte[] msgBodyLenBytes = BytesUtils.getBytes(this.msgBodyLen);

        this.msgBody = msgBody;

        this.crc16 = CRC16Utils.getCrc16Bytes(BytesUtils.concat(msgTypeBytes, subMsgTypeBytes, checksumBytes, msgBodyLenBytes, msgBody));
    }

    public Message(byte[] msgHead, IMessageSubType messageType) {
        this(msgHead, messageType, new byte[1]);
    }

    public ChannelFuture sendMsg(ChannelHandlerContext ctx) {
        byte[] bytes = constructBytes();
        ByteBuf msg = ctx.alloc().buffer(bytes.length);
        msg.writeBytes(bytes);
        return ctx.writeAndFlush(msg);
    }

    public ChannelFuture sendMsg(Channel channel) {
        byte[] bytes = constructBytes();
        ByteBuf msg = channel.alloc().buffer(bytes.length);
        msg.writeBytes(bytes);
        return channel.writeAndFlush(msg);
    }


    public String getPrintStr(IMessageSubType messageSubType) {
        String printStr = "";

        try {
            printStr += "\t\t\t\tmsgHead: " + BytesUtils.bytesToHexString(getMsgHead())
                    + "(" + new String(getMsgHead(), "UTF-8") + ")\r\n";

            String msgTypeText = null;
            String msgSubTypeText = null;

            if (null != messageSubType) {
                IMessageSubType messageSubType1 = messageSubType.getByMsgType(this.msgType, this.subMsgType);
                if (null != messageSubType1) {
                    msgTypeText = messageSubType1.getMsgType().toString() + "-" + messageSubType1.getMsgType().getText();
                    msgSubTypeText = messageSubType1.toString() + "-" + messageSubType1.getText();
                } else {
                    msgTypeText = "unknow";
                    msgSubTypeText = "unknow";
                }
            }

            printStr += "\t\t\t\tmsgType: " + this.msgType + (null != msgTypeText ? "(" + msgTypeText + ")" : "") + "\r\n";

            printStr += "\t\t\t\tsubMsgType: " + this.subMsgType + (null != msgSubTypeText ? "(" + msgSubTypeText + ")" : "") + "\r\n";

            printStr += "\t\t\t\tchecksum: " + this.checksum + "\r\n";

            printStr += "\t\t\t\tmsgBodyLen: " + this.msgBodyLen + "\r\n";

            printStr += "\t\t\t\tmsgBody: " + BytesUtils.bytesToHexString(this.msgBody)
                    + "(" + new String(this.msgBody, "UTF-8") + ")\r\n";

            printStr += "\t\t\t\tcrc16: " + BytesUtils.bytesToHexString(this.crc16) + "\r\n";

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return printStr;
    }

}
