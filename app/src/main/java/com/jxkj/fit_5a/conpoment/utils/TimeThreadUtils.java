package com.jxkj.fit_5a.conpoment.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;


public class TimeThreadUtils {

    public static void sendDataA0() {
        new Thread(new ThreadShowA0()).start();
    }

    public static void sendDataA2() {
        new Thread(new ThreadShowA2()).start();
    }

    public static void sendDataA6(long time,byte[] load) {
        System.out.println("receive....time："+time);
        new Thread(new ThreadShowA6(time,load)).start();
    }


    static class ThreadShowA0 implements Runnable {
        int pos = 1;
        // handler类接收数据
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if(pos==5 && ConstValues_Ly.CLIENT_ID==0){
                        ToastUtils.showShort("设备故障已断开");
                        PopupWindowLanYan.ble4Util.disconnect();
                        return;
                    }
                    if(ConstValues_Ly.CLIENT_ID==0){
                        PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
                    }else{
                        sendDataA2();
                    }
                    System.out.println("receive....ThreadShowA0");
                }
            }
        };
        @Override
        public void run() {
            while (PopupWindowLanYan.ble4Util.isConnect() && ConstValues_Ly.CLIENT_ID==0) {
                try {
                    Thread.sleep(5000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    pos++;
                    System.out.println("send...");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }

    static class ThreadShowA2 implements Runnable {
        int pos = 1;
        // handler类接收数据
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A2));
                    if(pos==5 && ConstValues_Ly.CLIENT_ID==0){
                        ToastUtils.showShort("设备故障已断开");
                        PopupWindowLanYan.ble4Util.disconnect();
                    }
                    System.out.println("receive....ThreadShowA2");
                }
            }
        };
        @Override
        public void run() {
            Log.w("---》》》","---》》》"+PopupWindowLanYan.ble4Util.isConnect());
            while (PopupWindowLanYan.ble4Util.isConnect()) {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    pos++;
                    System.out.println("send...");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }

    static class ThreadShowA6 implements Runnable {
        int pos = 0;
        long time;
        byte[] load;

        public ThreadShowA6(long time, byte[] load) {
            this.time=time;
            this.load = load;
        }
        // handler类接收数据
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1 && load.length>pos) {
                    System.out.println("receive....");
                    PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A6,load[pos]));
                    pos++;
                }
            }
        };

        @Override
        public void run() {
            while (PopupWindowLanYan.ble4Util.isConnect() && load.length>pos && ConstValues_Ly.CURRENT_STATE==1) {
                try {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    System.out.println("receive....");
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }
}
