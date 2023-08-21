package com.jxkj.fit_5a.lanya;


import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.view.activity.yaling.YaLingActivity_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConstValues_Ly {
    /**
     * 将一个整形化为十六进制，并以字符串的形式返回
     */
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String[] mBleNames = {"ibike","console","i-rower","fit","5afit","db201","head","hd211","5afit-stag"};

    public static boolean isYalingsheben(String name){
        if(name.toLowerCase().contains("hd211")
                || name.toLowerCase().contains("db201")
                || name.toLowerCase().contains("5afit-stag")){
            return true;
        }
        return false;
    };
    public static byte START = (byte) 0xF0;//Start

    public static ArrayList<Integer> data;//跑步机时
    public static boolean isA1 = false;
    public static Integer maxLoad = 0;
    public static double wheelDiameter = 0.0;

    //Message動車錶
    public static byte MESSAGE_A0 = (byte) 0xA0;//Host Connect Check 连接检查
    public static byte MESSAGE_A1 = (byte) 0xA1;//Host Ask Max Load 问最大负载
    public static byte MESSAGE_A2 = (byte) 0xA2;//Host Ask Sport Data 问运动数据
    public static byte MESSAGE_A3 = (byte) 0xA3;//Host Send Sport Mode 发送的运动模式
    public static byte MESSAGE_A4 = (byte) 0xA4;//Host Send Sport Set Data 发送运动集数据
    public static byte MESSAGE_A5 = (byte) 0xA5;//Host Send Key Command 发送关键命令
    public static byte MESSAGE_A6 = (byte) 0xA6;//Host Send Ride Load 发送骑负载
    public static byte MESSAGE_A8 = (byte) 0xA8;//Host Send Ride WATT 发送骑瓦特

    public static int CURRENT_STATE = 0;

    public static byte CLIENT_ID = 0x00;//Client ID
    public static byte METER_ID = 0x00;//Meter ID

    public static String BRAND_ID = null;//	品牌id
    public static int DEVICE_TYPE_ID_PARAM = 0;//设备类型id
    public static int DEVICE_TYPE_ID = 0;//设备类型id
    public static String DEVICE_IMG = "";//设备的图片
    public static String DEVICE_TYPE_ID_URL = "0";//设备类型id
    public static String DEVICE_Model_ID_URL = "0";//设备型号id
    /**
     * 脚踏车/椭圆机(iC+ Protocol V1.3.1)
     * 手动脚踏车/椭圆机(iB+ Protocol(手動車錶) V1.2)
     * 腹健轮(健腹輪(AB Roller Wheel) Protocol_V1.6_0XB4 (2))
     * 滑雪机(iRower Protocol(手動電動划船器同一版通協) V1.1)
     * 跑步机(iR+ Protocol(電跑).pdf)
     */
    public static byte[] METER_ID_S = new byte[]{1,-56,-76,-25,-45};//0X00+1  0XC8  0XB4  0XE6+1  0XD2+1
    public static String[] METER_NAME_S = new String[]{"脚踏车/椭圆机","手动脚踏车/椭圆机","腹健轮","划船机","跑步机"};//0X00+1  0XC8  0XB4  0XE6+1
    public static String SB_NAME = "";//选择的设备名称=设备名称+设备品牌+设备型号
    public static int[] DEVICE_TYPE_IDS = new int[]{0, 200, 180, 230, 210};//0X00+1  0XC8  0XB4  0XE6+1  0XD2+1


    /**
     * [-16, -79, 56, 1, 33, -5]
     * @return Checksum
     */
    public static byte getCHECKSUM(byte[] strData) {
        byte Checksum = 0;
        for(int i = 2;i<strData.length-1; i++ ){
            Checksum+=strData[i];
        }
        return Checksum;
    }

    /**
     * @param message
     * @return Checksum
     */
    public static byte getCHECKSUM(byte message) {
        return (byte) (ConstValues_Ly.START + message + ConstValues_Ly.CLIENT_ID + ConstValues_Ly.METER_ID);
    }

    public static byte getCHECKSUM(byte message, byte data) {
        return (byte) (ConstValues_Ly.START + message + ConstValues_Ly.CLIENT_ID + ConstValues_Ly.METER_ID + data);
    }

    public static byte getCHECKSUM(byte message, byte[] data) {
        byte data_z = 0;
        for (int i = 0; i < data.length; i++) {
            data_z += data[i];
        }
        return (byte) (ConstValues_Ly.START + message + ConstValues_Ly.CLIENT_ID + ConstValues_Ly.METER_ID + data_z);
    }


    public static byte[] getByteData(byte message) {
        return new byte[]{START, message, CLIENT_ID, METER_ID, getCHECKSUM(message)};
    }

    public static byte[] getByteData(byte message, byte data) {
        return new byte[]{START, message, CLIENT_ID, METER_ID,  data, getCHECKSUM(message, data)};
    }

    public static byte[] getByteDataJia(byte message, byte data) {
        return new byte[]{START, message, CLIENT_ID, METER_ID, (byte) (data+1), getCHECKSUM(message, (byte) (data+1))};
    }

    public static byte[] getByteData(byte message, byte[] data) {
        List<Byte> lists = new ArrayList<>();
        lists.add(START);
        lists.add(message);
        lists.add(CLIENT_ID);
        lists.add(METER_ID);
        for (int i = 0; i < data.length; i++) {
            lists.add(data[i]);
        }
        lists.add(getCHECKSUM(message, data));
        return listTobyte1(lists);
    }

    /**
     * 转换成的byte数组
     */
    private static byte[] listTobyte1(List<Byte> list) {
        if (list == null || list.size() < 0)
            return null;

        byte[] bytes = new byte[list.size()];
        int i = 0;
        Iterator<Byte> iterator = list.iterator();
        while (iterator.hasNext()) {
            bytes[i] = iterator.next();
            i++;
        }
        return bytes;
    }

    /**
     *
     * @param time 时间
     * @param distance 距离
     * @param calories 卡路里
     * @param pulse 脉冲
     * @param watt 脉冲
     * @param unit 单位
     * @return
     */
    public static byte[] getA4Data(double time, double distance, double calories, double pulse, double watt, String unit){

        byte data2 = (byte) (time+1);

        byte data3 = getBaiShi(String.valueOf(distance));
        byte data4 = getGeXiao(String.valueOf(distance));

        byte data5 = getQianBai(String.valueOf(calories));
        byte data6 = getShiGe(String.valueOf(calories));

        byte data7 = getQianBai(String.valueOf(pulse));
        byte data8 = getShiGe(String.valueOf(pulse));

        byte data9 = getBaiShi(String.valueOf(watt));
        byte data10 =getGeXiao(String.valueOf(watt));

        byte data11 = 2;//0x01
        if(unit.equals("KM")){
            data11 = 1;//0x00
        }

        byte[] data = {data2,data3,data4,data5,data6,data7,data8,data9,data10,data11};

        return data;
    }

    /**
     * 获取个十百小数点
     * @param a
     * @param b
     * @return
     */
    public static String getTime(int a, int b){
        if(b<10){
            return a+":0"+b;
        }
        return a+":"+b;
    }
    /**
     * 获取个十百小数点
     * @param a
     * @param b
     * @return
     */
    public static double getBaiShiGeX(int a, int b){
        return Double.valueOf(String.valueOf(a)+(b/10d));
    }

    /**
     * 获取个十百千
     * @param a
     * @param b
     * @return
     */
    public static int getQianBaiShiGe(int a, int b){
        if(b<10){
            return Integer.valueOf(a+"0"+b);
        }
        return Integer.valueOf(a+""+b);
    }

    /**
     * 获取千和百的位置
     * @return
     */
    public static byte getQianBai(String str){
        String intNumber = "000"+str.substring(0,str.indexOf("."));
        return (byte) (Byte.valueOf(intNumber.substring(intNumber.length()-4,intNumber.length()-2))+1);
    }

    /**
     * 获取百和十的位置
     * @return
     */
    public static byte getBaiShi(String str){
        String intNumber = "000"+str.substring(0,str.indexOf("."));
        return (byte) (Byte.valueOf(intNumber.substring(intNumber.length()-3,intNumber.length()-1))+1);
    }

    /**
     * 获取十和个的位置
     * @return
     */
    public static byte getShiGe(String str){
        String intNumber = "000"+str.substring(0,str.indexOf("."));
        return (byte) (Byte.valueOf(intNumber.substring(intNumber.length()-2))+1);
    }

    /**
     * 個,小數點以下一位
     * @return
     */
    public static byte getGeXiao(String str){
        String s = str.substring(str.indexOf(".") - 1, str.indexOf(".") + 2);
        return (byte) (Byte.valueOf(deleteCharString(s,'.'))+1);
    }

    public static String deleteCharString(String sourceString, char chElemData) {
        String deleteString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }
        }
        return deleteString;
    }

    /**
     * int 转byte
     * @param
     * @return
     */
    public static String byteToHex(int n) {
        n = n+1;
        if (n < 0) {
            n = n + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return "0x"+hexArray[d1] + hexArray[d2];
    }
}
