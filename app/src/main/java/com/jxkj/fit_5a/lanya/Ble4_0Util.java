package com.jxkj.fit_5a.lanya;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.conpoment.utils.MyActivityManager;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BluetoothChannelData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Ble4_0Util implements BleUtil {

//    private String serviceUUid = "0000fff0-0000-1000-8000-00805f9b34fb";        // 服务uuid
//    private String readUUID = "0000fff1-0000-1000-8000-00805f9b34fb";            // 读数据uuid
//    private String writeUUID = "0000fff2-0000-1000-8000-00805f9b34fb";    // 写数据uuid

//    private String serviceUUid = "49535343-fe7d-4ae5-8fa9-9fafd205e455";        // 服务uuid
//    private String readUUID = "49535343-1e4d-4bd9-ba61-23c647249616";            // 读数据uuid
//    private String writeUUID = "49535343-8841-43f4-a8d4-ecbe34729bb3";    // 写数据uuid

    private List<String> serviceUUid;// 服务uuid
    private List<String> readUUID; // 读数据uuid
    private List<String> writeUUID; // 写数据uuid

    private boolean isLianJieZhong = false;
    private String[] uuidStr = new String[3];

    private Activity context;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGattService gattServiceMain;
    private BluetoothGattCharacteristic mDevWriteCharacteristic; // 写服务
    private BluetoothGattCharacteristic mDevReadCharacteristic; // 读服务
    private BluetoothAdapter.LeScanCallback leScanCallback;
    private BluetoothDevice curConnectDev;
    int newStates = -1;

    public void setUUidData(List<BluetoothChannelData> UUidData) {
        serviceUUid = new ArrayList<>();
        readUUID = new ArrayList<>();
        writeUUID = new ArrayList<>();
        for (int i = 0; i < UUidData.size(); i++) {
            serviceUUid.add(UUidData.get(i).getServiceUuid().toLowerCase());
            if (UUidData.get(i).getServiceUuid().length() < 6) {
                readUUID.add(UUidData.get(i).getCharacteristicRead().toLowerCase());
                writeUUID.add(UUidData.get(i).getCharacteristicWrite().toLowerCase());
            } else {
                readUUID.add(UUidData.get(i).getCharacteristicWrite().toLowerCase());
                writeUUID.add(UUidData.get(i).getCharacteristicRead().toLowerCase());
            }
        }
    }

    public void setUuidStr(String[] uuidStr) {
        serviceUUid = new ArrayList<>();
        readUUID = new ArrayList<>();
        writeUUID = new ArrayList<>();

        serviceUUid.add(uuidStr[0]);
        readUUID.add(uuidStr[1]);
        writeUUID.add(uuidStr[2]);
    }

    public Ble4_0Util(Activity context) {
        this.context = context;
    }

    public BluetoothDevice getCurConnectDev() {
        return curConnectDev;
    }

    @Override
    public boolean init() {
        //不支持 蓝牙
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return false;
        }
        final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);

        mBluetoothAdapter = bluetoothManager.getAdapter();
        //不支持 蓝牙
        if (mBluetoothAdapter == null) return false;

        //没有打开蓝牙
        if (!mBluetoothAdapter.isEnabled()) mBluetoothAdapter.enable();

        return true;
    }

    public boolean isConnect() {
        return mBluetoothGatt != null;
    }

    public String[] getUuidStr() {
        return uuidStr;
    }

    //     //判断手机厂商
//    public String checkPhoneFirm() {
//        String phoneState = Build.BRAND.toLowerCase(); //获取手机厂商
//        if (phoneState.equals("huawei") || phoneState.equals("honor"))
//            return PhoneConstant.IS_HUAWEI;
//        else if (phoneState.equals("xiaomi") && Build.BRAND != null)
//            return PhoneConstant.IS_XIAOMI;
//        else if (phoneState.equals("oppo") && Build.BRAND != null)
//            return PhoneConstant.IS_OPPO;
//        else if (phoneState.equals("vivo") && Build.BRAND != null)
//            return PhoneConstant.IS_VIVO;
//        else if (phoneState.equals("meizu") && Build.BRAND != null)
//            return PhoneConstant.IS_MEIZU;
//        else if (phoneState.equals("samsung") && Build.BRAND != null)
//            return PhoneConstant.IS_SAMSUNG;
//        else if (phoneState.equals("letv") && Build.BRAND != null)
//            return PhoneConstant.IS_LETV;
//        else if (phoneState.equals("smartisan") && Build.BRAND != null)
//            return PhoneConstant.IS_SMARTISAN;
//
//        return "";
//    }
    @Override
    public boolean connect(String blemac, final CallBack callback) {
        callback.StateChange(0, BluetoothGatt.STATE_CONNECTING);
        curConnectDev = mBluetoothAdapter.getRemoteDevice(blemac);
        if (curConnectDev == null) {
            Log.e("BLE", "蓝牙" + blemac + "未找到");
            return false;
        }
        //已连接 先断开连接
        if (null != mBluetoothGatt) {
            mBluetoothGatt.close();
            mBluetoothGatt = null;
        }
        //不能拿到 名称和 蓝牙mac的按假连接处理
        if (null == curConnectDev.getName() && null == curConnectDev.getAddress()) {
            return false;
        }
        Log.w("---》》》:","----------");
        isLianJieZhong = true;
        //连接蓝牙
        mBluetoothGatt = curConnectDev.connectGatt(context, false, new BluetoothGattCallback() {
            @Override
            public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                super.onPhyUpdate(gatt, txPhy, rxPhy, status);
            }

            @Override
            public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
                super.onPhyRead(gatt, txPhy, rxPhy, status);
            }

            @Override
            public void onConnectionStateChange(final BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                isScuu = false;
                Log.w("---》》》","onConnectionStateChange"+newState+"；isLianJieZhong："+isLianJieZhong);
//                if(!isLianJieZhong){
//                    return;
//                }
                newStates = newState;
                if (newState == BluetoothGatt.STATE_CONNECTED) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //设置接收数据长度，默认20
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        String phoneState = "";
                        if(Build.BRAND != null){
                            phoneState = Build.BRAND.toLowerCase(); //获取手机厂商
                        }

                        boolean a  = false;
                        Log.w("---》》》","phoneState:"+phoneState);
                        if (phoneState.equals("xiaomi")) {
                            a = mBluetoothGatt.requestMtu(1024);
                        }else{
                            a = mBluetoothGatt.requestMtu(512);
                        }
                        String model = Build.MODEL;
                        Log.e("---》》》","requestMtu"+a);
                        Log.e("---》》》","phoneState"+phoneState);
                    }
                }
                if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                    if(StringUtil.isBlank(PopupWindowLanYan.BleName)){
                        callback.StateChange(status, newStates);
                    }else{
                        Log.e("onConnectionStateChange","蓝牙断开连接");
                    }
                    disconnect();
                }
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
                Log.w("---》》》","onServicesDiscovered");
                List<BluetoothGattService> serviceList = gatt.getServices();
                for (BluetoothGattService gattService : serviceList) {
                    if(!isCurrentUuid(gattService.getUuid().toString(),serviceUUid)){
                        continue;
                    }
                    gattServiceMain = gattService;
                    uuidStr[0] = gattServiceMain.getUuid().toString();
                    Log.w("---》》》gattServiceMain:",gattService.getUuid().toString());
                    List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
                    for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                        if (mDevReadCharacteristic == null && isCurrentUuid(gattCharacteristic.getUuid().toString(),readUUID)) {
                            mDevReadCharacteristic = gattCharacteristic;
                            uuidStr[1] = mDevReadCharacteristic.getUuid().toString();
                            Log.w("---》》》","mDevReadCharacteristic:"+ mDevReadCharacteristic.getUuid().toString());
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    mBluetoothGatt.readCharacteristic(mDevReadCharacteristic);
                                }
                            }).start();
                            mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true);

                            List<BluetoothGattDescriptor> descriptorlist = gattCharacteristic.getDescriptors();

                            lp:
                            for (BluetoothGattDescriptor descriptor : descriptorlist) {
                                Log.w("---》》》","escriptor.getUuid()"+descriptor.getUuid());
                                Log.w("---》》》","escriptor.getUuid()"+(descriptor.getUuid().toString().contains("")));
                                if (descriptor.getUuid().toString().contains("")) {
                                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                    mBluetoothGatt.writeDescriptor(descriptor);
                                    break lp;
                                }
                            }
                        }
                        Log.e("---》》》", " gattCharacteristic.getProperties()："+ gattCharacteristic.getProperties());
                        if (mDevWriteCharacteristic == null && (gattCharacteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE
                                || gattCharacteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE
                                || gattCharacteristic.getProperties() == 12
                                || gattCharacteristic.getProperties() == 14)
                                && isCurrentUuid(gattCharacteristic.getUuid().toString(),writeUUID)) {
                            mDevWriteCharacteristic = gattCharacteristic;
                            uuidStr[2] = mDevWriteCharacteristic.getUuid().toString();
                            Log.e("---》》》", "连接成功"+isConnect());
                            isScuu = true;
                            callback.StateChange(status, newStates);
                        }
                    }
                }
            }

            @Override
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);
                Log.e("---》》》", "getValueonCharacteristicRead:"+ Arrays.toString(characteristic.getValue()));
            }

            @Override
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicWrite(gatt, characteristic, status);
            }
            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
                if(characteristic!=null && characteristic.getValue()!=null){
                    byte[] value = characteristic.getValue();
                    Log.e("---》》》", "getValue拼接前:"+ Arrays.toString(value));
                    if(value.length==19 && value[2] == 56){
                        SharedUtils.singleton().put("characteristic",Arrays.toString(value));
                        return;
                    }
                    if(value.length==2){
                        String valueStr = SharedUtils.singleton().get("characteristic","");
                        if(StringUtil.isBlank(valueStr)){
                            return;
                        }
                        valueStr = valueStr.replace("[","").replace(" ","").replace("]","");
                        String[] valueS = valueStr.split(",");
                        if(valueS.length!=19){
                            return;
                        }
                        byte[] v = new byte[21];
                        for (int i = 0;i<valueS.length;i++){
                            v[i]= Byte.parseByte(valueS[i]);
                        }
                        v[19] = value[0];
                        v[20] = value[1];
                        // 处理解释反馈指令
                        Log.e("---》》》", "getValue拼接后:"+ Arrays.toString(v));
                        callback.ReadValue(v);
                        return;
                    }
                    callback.ReadValue(value);
                }
            }

            @Override
            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorRead(gatt, descriptor, status);
            }

            @Override
            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorWrite(gatt, descriptor, status);
            }

            @Override
            public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
                super.onReliableWriteCompleted(gatt, status);
            }

            @Override
            public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
                super.onReadRemoteRssi(gatt, rssi, status);
            }

            @Override
            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                super.onMtuChanged(gatt, mtu, status);
                if (BluetoothGatt.GATT_SUCCESS == status && !isScuu) {
                    Log.e("---》》》","onMtuChanged success MTU = " + mtu+";isScuu:"+isScuu);
                    gatt.discoverServices();
                } else {
                    Log.e("---》》》","onMtuChanged fail MTU = " + mtu+";isScuu:"+isScuu);
                }
            }
        });

        return true;
    }
    boolean isScuu = false;

    private boolean isCurrentUuid(String uuid,List<String> uuidList){
        for(int i=0;i<uuidList.size();i++){
            if (uuid.contains(uuidList.get(i))) {
                return true;
            }
        }
        return false;
    }
    private final BluetoothProfile.ServiceListener disconnectProfileServiceListener = new BluetoothProfile.ServiceListener() {
        @Override
        public void onServiceDisconnected(int profile) {

        }

        @Override
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            try {
                Log.d("---》》》", "proxy:" +proxy);
                if (profile == BluetoothProfile.HEADSET) {
                    //使用HEADSET的协议断开蓝牙设备（使用了反射技术调用断开的方法）
                    BluetoothHeadset bluetoothHeadset = (BluetoothHeadset) proxy;
                    boolean isDisConnect = false;
                    try {
                        Method connect = bluetoothHeadset.getClass().getDeclaredMethod("disconnect", BluetoothDevice.class);
                        connect.setAccessible(true);
                        isDisConnect = (boolean) connect.invoke(bluetoothHeadset, curConnectDev);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("---》》》", "isDisConnect:" + (isDisConnect ? "断开通话成功" : "断开通话失败") + curConnectDev.getName());
                }
                if (profile == BluetoothProfile.A2DP) {
                    //使用A2DP的协议断开蓝牙设备（使用了反射技术调用断开的方法）
                    BluetoothA2dp bluetoothA2dp = (BluetoothA2dp) proxy;
                    boolean isDisConnect = false;
                    try {
                        Method connect = bluetoothA2dp.getClass().getDeclaredMethod("disconnect", BluetoothDevice.class);
                        connect.setAccessible(true);
                        isDisConnect = (boolean) connect.invoke(bluetoothA2dp, mBluetoothGatt);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("---》》》", "isDisConnect:" + (isDisConnect ? "断开音频成功" : "断开音频失败") + curConnectDev.getName());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public boolean disconnect() {
        Log.w("---》》》","---》》》"+mBluetoothGatt);
        if (null != mBluetoothGatt) {

            //获取A2DP代理对象
            mBluetoothAdapter.getProfileProxy(context, disconnectProfileServiceListener, BluetoothProfile.HEADSET);
            //获取HEADSET代理对象
            mBluetoothAdapter.getProfileProxy(context, disconnectProfileServiceListener, BluetoothProfile.A2DP);

            mBluetoothGatt.disconnect();
            mBluetoothGatt.close();
            mBluetoothGatt = null;
            PopupWindowLanYan.BleName = "";
            ConstValues_Ly.METER_ID = 0x00;
            ConstValues_Ly.CLIENT_ID = 0x00;
            ConstValues_Ly.CURRENT_STATE = 0;
            ConstValues_Ly.maxLoad = 0;
            ConstValues_Ly.isA1 = false;
            ConstValues_Ly.DEVICE_TYPE_ID_PARAM = 0;
            ConstValues_Ly.SB_NAME = "";
            initLsData();
            PopupWindowLanYan.startBroadcast("-2",null);
            Log.w("---》》》","MainActivity");
            if(MyActivityManager.getInstance().getCurrentActivity() instanceof MainActivity){
                Log.w("---》》》","MainActivity");
                PopupWindowLanYan.startBroadcast_Two("-2");
            }
        }
        mDevWriteCharacteristic = null;
        return true;
    }
    public static void initLsData() {
        SharedHistoryEquipment.singleton()
                .putSharedHistoryEquipment(SharedHistoryEquipment.singleton().getSharedHistoryEquipment());
    }

    @Override
    public boolean close() {
        mBluetoothAdapter = null;
        return true;
    }

    @Override
    public boolean send(String str) {
        if (mDevWriteCharacteristic == null) {
            getService();
        }
        return sendStrToDev(str);
    }


    public boolean send(byte[] byteCmd) {
        if (mDevWriteCharacteristic == null) {
            getService();
        }
        return sendByteToDev(byteCmd);
    }

    public void sendData(byte[] sendData) {
        if(mBluetoothGatt==null){
            return;
        }
        Log.e("---》》》", "sendData:" + Arrays.toString(sendData));
        if (sendData.length > 0) {
            send(sendData);
//            ToastUtils.showShort("指令已发送");
        } else {
            ToastUtils.showShortToast(context,"发送指令不能为空！");
        }
    }

    private boolean getService() {
        if (null == mBluetoothGatt) {
            return false;
        }
        if (mDevWriteCharacteristic != null) {
            return true;
        }
        if (gattServiceMain == null) return false;

        //获取写的特征值
        List<BluetoothGattCharacteristic> characteristicList = gattServiceMain.getCharacteristics();
        for (BluetoothGattCharacteristic characteristic : characteristicList) {
            Log.w("---》》》","writeUUID:"+characteristic.getUuid().toString());
            if ((characteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE
                    || characteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE
                    || characteristic.getProperties() == BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) &&
                        isCurrentUuid(characteristic.getUuid().toString(),writeUUID)) {
//                    characteristic.getUuid().toString().indexOf(writeUUID) >= 0) {
                mDevWriteCharacteristic = characteristic;
                break;
            }
        }

        return mDevWriteCharacteristic == null;
    }


    private boolean sendStrToDev(String str) {
        if (mDevWriteCharacteristic == null) {
            return false;
        }
        byte[] value = new byte[20];
        value[0] = (byte) 0x00;
        mDevWriteCharacteristic.setValue(value[0], BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mDevWriteCharacteristic.setValue(str);
        return mBluetoothGatt.writeCharacteristic(mDevWriteCharacteristic);
    }

    private boolean sendByteToDev(byte[] byteCmd) {
        if (mDevWriteCharacteristic == null) {
            return false;
        }
        mDevWriteCharacteristic.setValue(byteCmd);
        return mBluetoothGatt.writeCharacteristic(mDevWriteCharacteristic);
    }

    @Override
    public boolean startScan(BluetoothAdapter.LeScanCallback callBack) {

        if (mBluetoothAdapter == null) {
            return false;
        }
        if (mBluetoothAdapter.isDiscovering()) {
            return false;
        }

        mBluetoothAdapter.startLeScan(callBack);
        this.leScanCallback = callBack;
        return true;
    }

    public void setLianJieZhong(boolean lianJieZhong) {
        isLianJieZhong = lianJieZhong;
    }

    @Override
    public boolean stopScan() {
        if (this.leScanCallback == null) {
            return false;
        }
        mBluetoothAdapter.stopLeScan(this.leScanCallback);
        this.leScanCallback = null;
        return true;
    }

    public static void OpenA2dp() {

        BluetoothProfile.ServiceListener bs = new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                Log.w("Kavenir", "onServiceConnected");
                List<BluetoothDevice> bluetoothDevices = proxy.getConnectedDevices();
                if (bluetoothDevices.size() > 0) {
                    Log.w("Kavenir", "onServiceConnected==" + bluetoothDevices.get(0).getName());
                    if (profile == BluetoothProfile.HEADSET) {
                        BluetoothHeadset bh = (BluetoothHeadset) proxy;
                        if (bh.getConnectionState(bluetoothDevices.get(0)) == BluetoothProfile.STATE_CONNECTED) {
                            try {
                                bh.getClass().getMethod("connect", BluetoothDevice.class).invoke(bh, bluetoothDevices.get(0));
                                Log.w("Kavenir", "onServiceConnected==" + "headset通道");
                            } catch (Exception e) {
                            }
                        }
                    } else if (profile == BluetoothProfile.A2DP) {
                        BluetoothA2dp a2dp = (BluetoothA2dp) proxy;
                        if (a2dp.getConnectionState(bluetoothDevices.get(0)) == BluetoothProfile.STATE_CONNECTED) {
                            try {
                                a2dp.getClass().getMethod("connect", BluetoothDevice.class).invoke(a2dp, bluetoothDevices.get(0));
                                Log.w("Kavenir", "onServiceConnected==" + "a2dp通道");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onServiceDisconnected(int profile) {
                Log.e("Kavenir", "未连接");
            }
        };

    }
}
