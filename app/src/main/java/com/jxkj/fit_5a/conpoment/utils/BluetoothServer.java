package com.jxkj.fit_5a.conpoment.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import cn.forward.androids.utils.LogUtil;

/**
 * 作者： litongge
 * 时间： 2019/6/13 9:11
 * 邮箱；ltg263@126.com
 * 描述：蓝牙服务接收者和发送的的工具类
 */
public class BluetoothServer {
    //这条是蓝牙串口通用的UUID，不要更改
    public static UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //开始连接
    public static String START_LINK = "0xF0";

    private BluetoothAdapter mBluetoothAdapter;
    private Activity activity;
    private BluetoothDevice device;
    private BluetoothSocket clientSocket;
    private OutputStream os;//输出流
    private String bluetoothAddress;


    /**
     * 创建蓝牙服务对象并开启蓝牙
     * @param activity
     * @param bluetoothAddress
     */
    public BluetoothServer(Activity activity,String bluetoothAddress){
        this.activity=activity;
        this.bluetoothAddress = bluetoothAddress;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //开启蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            //隐式打开
            if(mBluetoothAdapter.enable()){//开启成功
                mBluetoothAdapter.startDiscovery();
            }
        } else {
            mBluetoothAdapter.startDiscovery();
        }
    }

    public BluetoothAdapter getmBluetoothAdapter() {
        return mBluetoothAdapter;
    }

    /**
     * 蓝牙  发送者
     * @param msg
     * @param bluetoothInterface
     */
    public void sendMessage_text(String msg, final BluetoothInterface bluetoothInterface) {
        //主动连接蓝牙服务端
        try {
            //判断当前是否正在搜索
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            try {
                if (device == null) {
                    //获得远程设备
                    device = mBluetoothAdapter.getRemoteDevice(bluetoothAddress);
                }
                if (clientSocket == null) {
                    //创建客户端蓝牙Socket
//                    clientSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                    clientSocket =(BluetoothSocket) device.getClass().getDeclaredMethod("createRfcommSocket",new Class[]{int.class})
                            .invoke(device,1);
                    //开始连接蓝牙，如果没有配对则弹出对话框提示我们进行配对
                    mBluetoothAdapter.cancelDiscovery();
                    if (!clientSocket.isConnected()) {
                        clientSocket.connect();
                    }
                    //获得输出流（客户端指向服务端输出文本）
                    os = clientSocket.getOutputStream();
                }
            } catch (Exception e) {
                Log.w("TAG:","E:"+e.toString());
//                closeSocket();
                    if (msg.equals(START_LINK)) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bluetoothInterface.error();
                        }
                    });
                }
            }
            if (os != null) {
                if (msg.equals(START_LINK)) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bluetoothInterface.succeed(null);
                        }
                    });
                }
                //往服务端写信息
                LogUtil.w("msg:" + msg);
                os.write(msg.getBytes("utf-8"));
            }
        } catch (Exception e) {
//            closeSocket();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    bluetoothInterface.error();
                }
            });
            LogUtil.w("Exception:" + e.toString());
        }
    }


    public interface BluetoothInterface{
        void error();
        void succeed(Message msg);
    }

    /**
     * 关闭服务
     */
    public void closeSocket() {
        if (clientSocket != null) {
            try {
                clientSocket.close();
                clientSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}