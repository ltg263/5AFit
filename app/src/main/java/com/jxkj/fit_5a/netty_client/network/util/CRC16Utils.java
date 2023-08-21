package com.jxkj.fit_5a.netty_client.network.util;

/**
 * @author shenbiao
 * @date 2018/8/15
 */
public class CRC16Utils {
    static final String HEXES = "0123456789ABCDEF";


    static int alex_crc16(byte[] buf, int len) {
        int i, j;
        int c, crc = 0xFFFF;
        for (i = 0; i < len; i++) {
            c = buf[i] & 0x00FF;
            crc ^= c;
            for (j = 0; j < 8; j++) {
                if ((crc & 0x0001) != 0) {
                    crc >>= 1;
                    crc ^= 0xA001;
                } else
                    crc >>= 1;
            }
        }
        return (crc);
    }

    private static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    public static byte[] HexString2Buf(String src) {
        int len = src.length();
        byte[] ret = new byte[len / 2 + 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < len; i += 2) {
            ret[i / 2] = uniteBytes(tmp[i], tmp[i + 1]);
        }
        return ret;
    }

    public static byte[] getCrc16Buf(String toSend) {
        byte[] bb = HexString2Buf(toSend);
        int ri = alex_crc16(bb, bb.length - 2);
        bb[bb.length - 2] = (byte) ((0xff00 & ri) >> 8);
        bb[bb.length - 1] = (byte) (0xff & ri);
        return bb;
    }


    public static byte[] getCrc16Buf(byte[] bb) {
        int ri = alex_crc16(bb, bb.length - 2);
        bb[bb.length - 2] = (byte) ((0xff00 & ri) >> 8);
        bb[bb.length - 1] = (byte) (0xff & ri);
        return bb;
    }

    public static byte[] getCrc16Bytes(byte[] bb) {
        byte[] crc = new byte[2];
        int ri = alex_crc16(bb, bb.length);
        crc[0] = (byte) ((0xff00 & ri) >> 8);
        crc[1] = (byte) (0xff & ri);
        return crc;
    }

    public static String getBufHexStr(byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static boolean checkBuf(byte[] bb) {
        int ri = alex_crc16(bb, bb.length - 2);
        byte[] crc = new byte[2];
        crc[0] = (byte) ((0xff00 & ri) >> 8);
        crc[1] = (byte) (0xff & ri);
        if (bb[bb.length - 1] == crc[1] && bb[bb.length - 2] == crc[0])
            return true;
        return false;
    }
}
