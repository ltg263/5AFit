package com.jxkj.fit_5a.netty_client.network.util;

/**
 * @author shenbiao
 * @date 2018/8/15
 */
public class BytesUtils {
    public static byte getByte(int arg, int digits) {
        int offset = (digits - 1) * 8;

        return (byte) (0xFF & (arg >> offset));
    }

    public static byte getByteByIntOffset(int aInt, int offset) {
        int bInt = aInt >> (15 - offset);

        return (byte) (0X01 & bInt);
    }

    public static byte[] concat(byte[] arg1, byte[] arg2) {
        int arg1len = arg1.length;
        int arg2len = arg2.length;
        byte[] bytes = new byte[arg1len + arg2len];

        for (int index = 0; index < arg1len + arg2len; index++) {
            if (index < arg1len) {
                bytes[index] = arg1[index];
            } else {
                bytes[index] = arg2[index - arg1len];
            }
        }

        return bytes;
    }

    public static byte[] concat(byte[]... args) {
        int bytesLen = 0;

        for (byte[] arg : args) {
            bytesLen += arg.length;
        }

        int index = 0;
        byte[] bytes = new byte[bytesLen];
        for (byte[] arg : args) {
            for (byte item : arg) {
                bytes[index++] = item;
            }
        }

        return bytes;
    }

    public static void copyBytes(byte[] src, byte[] dst, int dstStartIndex) {
        int srcLen = src.length;
        for (int index = 0; index < srcLen; index++) {
            dst[dstStartIndex++] = src[index];
        }
    }

    public static byte[] getBytes(byte[] bytes, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return null;
        }
        int length = bytes.length;
        endIndex = endIndex > length ? length : endIndex;
        byte[] newBytes = new byte[endIndex - startIndex];
        for (int index = startIndex, i = 0; index < endIndex; index++) {
            newBytes[i++] = bytes[index];
        }

        return newBytes;
    }

    public static byte[] getBytes1(byte[] bytes, int startIndex, int len) {
        int length = bytes.length;
        len = startIndex + len > length ? length - startIndex : len;
        int endIndex = startIndex + len;
        byte[] newBytes = new byte[len];
        for (int index = startIndex, i = 0; index < endIndex; index++) {
            newBytes[i++] = bytes[index];
        }

        return newBytes;
    }

    public static byte[] getBytes(byte[] bytes, int startIndex) {
        int length = bytes.length;
        byte[] newBytes = new byte[length - startIndex];
        for (int index = startIndex, i = 0; index < length; index++) {
            newBytes[i++] = bytes[index];
        }

        return newBytes;
    }

/*	public static byte[] hexStringToBytes(String str) {
		int length = str.length();
		byte[] newBytes = new byte[length / 2];
		for (int index = 0, i = 0; index < length; index += 2) {
			newBytes[i++] = Byte.parseByte(str.substring(index, index + 2), 16);
		}

		return newBytes;
	}*/

    public static byte[] hex2byte(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return bytes;
    }

    public static String bytesToHexString(byte byte1) {
        byte[] arg = new byte[1];
        arg[0] = byte1;
        return bytesToHexString(arg);
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }

            stringBuilder.append(hv);
            if (i < length - 1) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString().toUpperCase();
    }

    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
        }
    }

    public static byte[] getBytes(byte arg) {
        byte[] bytes = new byte[1];
        bytes[0] = arg;

        return bytes;
    }

    public static short getShort(byte high, byte low) {
        short andValue = 0xFF;
        short num = (short) (((high & andValue) << 8) | (andValue & low));
        return num;
    }

    public static Short getShort(byte[] bytes) {
        if (2 != bytes.length) {
            return null;
        }

        return getShort(bytes[0], bytes[1]);
    }

    public static byte[] getBytes(short arg) {
        byte andValue = (byte) 0xFF;
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (andValue & (arg >> 8));
        bytes[1] = (byte) (andValue & arg);

        return bytes;
    }

    public static int getInt8(byte high) {
        int num = (int) ((high & 0x000000FF));
        return num;
    }

    public static int getInt16(byte high, byte low) {
        int andValue = 0xFF;
        int num = (int) (((high & andValue) << 8) | (andValue & low));
        return num;
    }

    public static Integer getInt16(byte[] bytes) {
        if (2 != bytes.length) {
            return null;
        }

        return getInt16(bytes[0], bytes[1]);
    }

    public static int getInt32(byte high1, byte high2, byte low1, byte low2) {
        int andValue = 0xFF;
        int num = (int) (((high1 & andValue) << 24) | ((high2 & andValue) << 16) | ((low1 & andValue) << 8) | (andValue & low2));
        return num;
    }

    public static Integer getInt32(byte[] bytes) {
        if (4 != bytes.length) {
            return null;
        }

        return getInt32(bytes[0], bytes[1], bytes[2], bytes[3]);
    }

    public static byte[] getBytes(int arg) {
        byte andValue = (byte) 0xFF;
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (andValue & (arg >> 24));
        bytes[1] = (byte) (andValue & (arg >> 16));
        bytes[2] = (byte) (andValue & (arg >> 8));
        bytes[3] = (byte) (andValue & arg);

        return bytes;
    }

    public static long getLong64(byte high1, byte high2, byte high3, byte high4, byte low1, byte low2, byte low3, byte low4) {
        long andValue = 0xFF;
        long num = (long) (((high1 & andValue) << 56) | ((high2 & andValue) << 48) | ((high3 & andValue) << 40) | ((high4 & andValue) << 32)
                | ((low1 & andValue) << 24) | ((low2 & andValue) << 16) | ((low3 & andValue) << 8) | (andValue & low4));

        return num;
    }

    public static Long getLong64(byte[] bytes) {
        if (8 != bytes.length) {
            return null;
        }

        return getLong64(bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    public static byte[] getBytes(long arg) {
        byte andValue = (byte) 0xFF;
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (andValue & (arg >> 56));
        bytes[1] = (byte) (andValue & (arg >> 48));
        bytes[2] = (byte) (andValue & (arg >> 40));
        bytes[3] = (byte) (andValue & (arg >> 32));
        bytes[4] = (byte) (andValue & (arg >> 24));
        bytes[5] = (byte) (andValue & (arg >> 16));
        bytes[6] = (byte) (andValue & (arg >> 8));
        bytes[7] = (byte) (andValue & arg);

        return bytes;
    }


    public static void main(String[] args) {
        long a = 0x12121212121212FFL;
        System.out.println(a);

        byte[] bytes = getBytes(a);
        for (int i = 0; i < 8; i++) {
            System.out.println(i + ": " + bytes[i]);
        }

        long b = getLong64(bytes);

        System.out.println(b);
    }

}
