package com.jxkj.fit_5a.netty_client.network.util;

/**
 * @author shenbiao
 * @date 2018/8/15
 */
public class ChecksumUtils {

    public static String getChecksumStr(String checkMsgStr) {
        byte[] checkMsg = BytesUtils.hex2byte(checkMsgStr);

        return getChecksumStr(checkMsg);
    }

    public static byte getChecksum(String checkMsgStr) {
        byte[] checkMsg = BytesUtils.hex2byte(checkMsgStr);

        return getChecksum(checkMsg);
    }

    public static String getChecksumStr(byte[] checkMsg) {
        byte checksum8 = getChecksum(checkMsg);

        return BytesUtils.bytesToHexString(checksum8);
    }

    public static byte getChecksum(byte[] checkMsg) {
        int sum = 0;
        for (byte item : checkMsg) {
            sum += BytesUtils.getInt8(item);
        }

        int mo = sum % 256;

        return BytesUtils.getByte(mo, 1);
    }

    public static void main(String[] args) {
        int i = 112414141;
        System.out.println(i % 256);
        System.out.println(i & 0xFF);
    }
}
