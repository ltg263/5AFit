package com.jxkj.fit_5a.netty_client.common.message.room.body;

import com.jxkj.fit_5a.netty_client.common._enum.MessageSubType;
import com.jxkj.fit_5a.netty_client.common.constants.RoomConstants;
import com.jxkj.fit_5a.netty_client.common.message.CustomMessage;
import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;
import com.jxkj.fit_5a.netty_client.network.util.CRC16Utils;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class RoomAuthBody {
    private Integer userId;
    private String key;

    public RoomAuthBody(Integer userId, String key) {
        this.userId = userId;
        this.key = key;
    }

    public byte[] constructBytes() throws UnsupportedEncodingException {
        return BytesUtils.concat(BytesUtils.getBytes(this.userId), this.key.getBytes(RoomConstants.charsetName));
    }

    public RoomAuthBody(byte[] bytes) throws UnsupportedEncodingException {
        int index = 0;

        this.userId = BytesUtils.getInt32(BytesUtils.getBytes1(bytes, index, 4));
        index += 4;

        this.key = new String(BytesUtils.getBytes(bytes, index), RoomConstants.charsetName);
    }

    public static void main(String[] args) {
        try {
            CustomMessage customMessage = new CustomMessage(MessageSubType.room_authenticate_req, new RoomAuthBody(74, "xrgW70Qtl274").constructBytes());
            System.out.println(BytesUtils.bytesToHexString(customMessage.constructBytes()));
            System.out.println(CRC16Utils.checkBuf(BytesUtils.getBytes(customMessage.constructBytes(), 5)));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
