package com.jxkj.fit_5a.conpoment.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

public class BluetoothConnectThread extends Thread {

    private static final UUID BluetoothUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;

    private boolean connected = false;
    private Object lock = new Object();

    //蓝牙连接回调接口
    private BluetoothConnectCallback connectCallback;

    public BluetoothConnectThread(BluetoothDevice device,
                                  BluetoothConnectCallback callback) {
        try {
            bluetoothDevice = device;
            bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(BluetoothUUID);
            connectCallback = callback;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (bluetoothSocket != null) {
            if (connected) {
                cancel2();
                connected = false;
            }
        }

        new Thread() {
            @Override
            public void run() {
                connect();
                if (connected) {
                    if (connectCallback != null){
                        connectCallback.connectSuccess(bluetoothSocket);
                    }
                }
            }
        }.start();

    }

    public void connect() {
        try {
            synchronized (lock) {
                bluetoothSocket.connect();
                connected = true;
            }
        } catch (Exception connectException) {
            connectException.printStackTrace();
            cancel();
            try {
                Method m;
                m = bluetoothDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                bluetoothSocket = (BluetoothSocket) m.invoke(bluetoothDevice, Integer.valueOf(1));
                bluetoothSocket.connect();
                connected = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                if (connectCallback != null){
                    connectCallback.connectFailed(ex.getMessage());
                }
            }
        }
    }

    public void cancel() {
        try {
            synchronized (lock) {
                if (connected) {
                    bluetoothSocket.close();
                    connected = false;
                }
            }
        } catch (IOException e) {
        }
    }

    public void cancel2() {
        try {
            synchronized (lock) {
                bluetoothSocket.close();
                connected = false;
            }
        } catch (IOException e) {
        }
    }

    public interface BluetoothConnectCallback {

        void connectSuccess(BluetoothSocket socket);

        void connectFailed(String errorMsg);

        void connectCancel();
    }
}
