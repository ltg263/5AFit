package com.jxkj.fit_5a.netty_client.common.util;


import com.jxkj.fit_5a.netty_client.network.util.BytesUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author shenbiao
 * @date 2018/8/15
 */
public class Ipv4PortUtils {

    private final static int INADDRSZ = 4;

    /**
     * 把IP,PORT地址转化为字节数组
     *
     * @param ipPortStr
     * @return byte[]
     */
    public static byte[] ipPortToBytes(String ipPortStr) {
        String[] ipPort = ipPortStr.split(":");
        if (2 != ipPort.length) {
            throw new IllegalArgumentException(ipPortStr + " is Invalid format");
        }

        byte[] ipPortByte = new byte[6];

        String ipStr = ipPort[0];
        String portStr = ipPort[1];
        byte[] ip = Ipv4PortUtils.ipToBytesByReg(ipStr);

        byte[] portBytes = BytesUtils.getBytes(Integer.valueOf(portStr));

        ipPortByte[0] = ip[0];
        ipPortByte[1] = ip[1];
        ipPortByte[2] = ip[3];
        ipPortByte[3] = ip[3];
        ipPortByte[4] = portBytes[3];
        ipPortByte[5] = portBytes[4];

        return ipPortByte;
    }

    /**
     * 把IP地址转化为字节数组
     *
     * @param ipAddr
     * @return byte[]
     */
    public static byte[] ipToBytesByInet(String ipAddr) {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 把IP地址转化为int
     *
     * @param ipAddr
     * @return int
     */
    public static byte[] ipToBytesByReg(String ipAddr) {
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 字节数组转化为IP
     *
     * @param bytes
     * @return int
     */
    public static String bytesToIp(byte[] bytes) {
        return new StringBuffer().append(bytes[0] & 0xFF).append('.').append(bytes[1] & 0xFF).append('.')
                .append(bytes[2] & 0xFF).append('.').append(bytes[3] & 0xFF).toString();
    }

    /**
     * 根据位运算把 byte[] -> int
     *
     * @param bytes
     * @return int
     */
    public static int bytesToInt(byte[] bytes) {
        int addr = bytes[3] & 0xFF;
        addr |= ((bytes[2] << 8) & 0xFF00);
        addr |= ((bytes[1] << 16) & 0xFF0000);
        addr |= ((bytes[0] << 24) & 0xFF000000);
        return addr;
    }

    /**
     * 把IP地址转化为int
     *
     * @param ipAddr
     * @return int
     */
    public static int ipToInt(String ipAddr) {
        try {
            return bytesToInt(ipToBytesByInet(ipAddr));
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * ipInt -> byte[]
     *
     * @param ipInt
     * @return byte[]
     */
    public static byte[] intToBytes(int ipInt) {
        byte[] ipAddr = new byte[INADDRSZ];
        ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
        ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
        ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
        ipAddr[3] = (byte) (ipInt & 0xFF);
        return ipAddr;
    }

    /**
     * 把int->ip地址
     *
     * @param ipInt
     * @return String
     */
    public static String intToIp(int ipInt) {
        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.').append((ipInt >> 16) & 0xff).append('.')
                .append((ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff)).toString();
    }

    /**
     * 把192.168.1.1/24 转化为int数组范围
     *
     * @param ipAndMask
     * @return int[]
     */
    public static int[] getIPIntScope(String ipAndMask) {
        String[] ipArr = ipAndMask.split("/");
        if (ipArr.length != 2) {
            throw new IllegalArgumentException("invalid ipAndMask with: " + ipAndMask);
        }
        int netMask = Integer.valueOf(ipArr[1].trim());
        if (netMask < 0 || netMask > 31) {
            throw new IllegalArgumentException("invalid ipAndMask with: " + ipAndMask);
        }
        int ipInt = Ipv4PortUtils.ipToInt(ipArr[0]);
        int netIP = ipInt & (0xFFFFFFFF << (32 - netMask));
        int hostScope = (0xFFFFFFFF >>> netMask);
        return new int[]{netIP, netIP + hostScope};
    }

    /**
     * 把192.168.1.1/24 转化为IP数组范围
     *
     * @param ipAndMask
     * @return String[]
     */
    public static String[] getIPAddrScope(String ipAndMask) {
        int[] ipIntArr = Ipv4PortUtils.getIPIntScope(ipAndMask);
        return new String[]{Ipv4PortUtils.intToIp(ipIntArr[0]), Ipv4PortUtils.intToIp(ipIntArr[0])};
    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     *
     * @param ipAddr ipAddr
     * @param mask   mask
     * @return int[]
     */
    public static int[] getIPIntScope(String ipAddr, String mask) {
        int ipInt;
        int netMaskInt = 0, ipcount = 0;
        try {
            ipInt = Ipv4PortUtils.ipToInt(ipAddr);
            if (null == mask || "".equals(mask)) {
                return new int[]{ipInt, ipInt};
            }
            netMaskInt = Ipv4PortUtils.ipToInt(mask);
            ipcount = Ipv4PortUtils.ipToInt("255.255.255.255") - netMaskInt;
            int netIP = ipInt & netMaskInt;
            int hostScope = netIP + ipcount;
            return new int[]{netIP, hostScope};
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid ip scope express  ip:" + ipAddr + "  mask:" + mask);
        }
    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     *
     * @param ipAddr ipAddr
     * @param mask   mask
     * @return String[]
     */
    public static String[] getIPStrScope(String ipAddr, String mask) {
        int[] ipIntArr = Ipv4PortUtils.getIPIntScope(ipAddr, mask);
        return new String[]{Ipv4PortUtils.intToIp(ipIntArr[0]), Ipv4PortUtils.intToIp(ipIntArr[0])};
    }

    public static String getLocalHostStr() {
        String host = null;
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            host = ip4.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return host;
    }

    public static int getLocalHostInt() {
        return ipToInt(getLocalHostStr());
    }

}
