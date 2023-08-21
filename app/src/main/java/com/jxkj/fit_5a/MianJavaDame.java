package com.jxkj.fit_5a;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class MianJavaDame {
    Thread thread=new Thread();
    static String newString = "_";//新字符串
    static String oldString = " ";//要被替换的字符串
    static String dir = "C:\\Users\\Administrator\\Downloads\\图标";//文件所在路径，所有文件的根目录
    public static void main(String[] args) {
//        System.out.println("Hello World!11"+ StringUtil.getAgeM(1,32));
        //55, 46, 57, 44, 56, 46, 49, 44, 49, 49, 46, 48, 59, 13, 10
//        byte[] data = new byte[]{55, 46, 57, 44, 56, 46, 49, 44, 49, 49, 46, 48, 59, 13, 10};
        byte[] data = new byte[]{-2, 54, 56, 46, 56, -2, -1, -2, 49, 44, 56, 46, 50, 59, 13, -1};
        int a = 0;
        for(int i = 0;i<data.length;i++){
            if(data[i] == -1){
                a = i;
                break;
            }
        }
        System.out.println("---a"+a);
        System.out.println("---a"+data.length);
        if(a!=data.length-1){
            byte[] da = Arrays.copyOf(data,a+1);
            byte[] da1 = Arrays.copyOfRange(data,a+1,data.length);
            System.out.println("---da"+Arrays.toString(da));
            System.out.println("---da1"+Arrays.toString(da1));
        }
//        if(characteristic!=null && characteristic.getValue()!=null){
//            byte[] value = characteristic.getValue();
//            Log.e("---》》》", "getValue:"+ Arrays.toString(value));
//            byte[] valueNew = new byte[21];
//            if(value.length==19 && value[3] == 56){
//                for(int i = 0;i < value.length;i++){
//                    valueNew[i] = value[i];
//                }
//            }
//            if(value.length==2){
//                valueNew[19] = value[0];
//                valueNew[20] = value[1];
//            }
//            // 处理解释反馈指令
//        }





//        byte[] dataa  = new byte[15];
//        System.arraycopy(data,0,dataa,0,15);
//        System.out.println("Hello World!11"+ Arrays.toString(dataa));
//        data = dataa;
//        System.out.println("Hello World!11a"+ Arrays.toString(data));
//        String str = null;
//        try {
//            str = new String(data,"GB2312");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("str:"+str);
//        int[] test = {10,-200,3,8,9,2,5};
//        Arrays.sort(test); //首先对数组排序
//        int result = Arrays.binarySearch(test, 100); //在数组中搜索是否含有5
//        System.out.println("-->>>:"+result); //这里的结果是 3
//        double a = 313.83333333333337;
//        float b = 1.5f;
////        System.out.println("-->>>:"+(float)a*b); //这里的结果是 3
//        int number = 10;
//        System.out.println("-->>>:"+ Math.floor(1.4));

//        System.out.println("-->>>:"+"getDate"+ (float) 300 / 500.0f);
//        System.out.println("-->>>:"+("0000fff1-0000-1000-8000-00805f9b34fb".indexOf("fff1")));
//        recursiveTraversalFolder(dir);//递归遍历此路径下所有文件夹

//        try {
//
//            RestUtil restUtil = new RestUtil();
//
//            String resultString = restUtil.load(
//                    "https://csms.nbyjdz.com/csms/api/v1/customer/url",
//                    "appKey=39ebd1d0-af86-4147-bb30-f9f5b35f7f1c_1&appUserId=123456&nickname=123");
//
//            System.out.println("resultString---" + resultString);
//        } catch (Exception e) {
//
//            // TODO: handle exception
//
//            System.out.print(e.getMessage());
//
//        }
    }

    public static void recursiveTraversalFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder(file.getAbsolutePath());
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();
                        if (fileName.contains(oldString)) {//文件名包含需要被替换的字符串
                            newName = fileName.replaceAll(oldString, newString);//新名字
                            newDir = new File(parentPath + "/" + newName.toLowerCase());//文件所在文件夹路径+新文件名
                            file.renameTo(newDir);//重命名
                            System.out.println("修改后：" + newDir);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static String getValue(double obj) {
        if(isIntegerForDouble(obj)){
            return String.valueOf((int) obj);
        }
        return String.format("%.2f", obj);
    }

    public static int toInt(byte[] bytes) {
        int number = 0;
        for (int i = 0; i < 4; i++) {
            number += bytes[i] << i * 8;
        }
        return number;
    }
    public static byte[] toBytes(int number){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)number;
        bytes[1] = (byte) (number >> 8);
        bytes[2] = (byte) (number >> 16);
        bytes[3] = (byte) (number >> 24);
        return bytes;
    }

    /**
     * 判断double是否是整数
     * @param obj
     * @return
     */
    public static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;  // 精度范围
        return obj-Math.floor(obj) < eps;
    }


    public static String getBigWeek(int dayofweek) {
//Calendar中1-星期天，2-星期一，3-星期二，4-星期三，5-星期四，6-星期五，7-星期六
        String[] wee = { "", "天", "一", "二", "三", "四", "五", "六" };
        return wee[dayofweek];

    }

    private static void aa(){
        Calendar cal = Calendar.getInstance();
        int flag = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1-flag);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        System.out.println("星期:" + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);

    }

    /**
     * 获取过去7天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals -1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }
    /**
     * 获取过去第几天的日期
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
}

