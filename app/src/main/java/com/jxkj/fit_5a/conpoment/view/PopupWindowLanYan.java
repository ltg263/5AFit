package com.jxkj.fit_5a.conpoment.view;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BluetoothChannelData;
import com.jxkj.fit_5a.entity.DeviceProtocolCheckData;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.BleAdapter;
import com.jxkj.fit_5a.lanya.BleUtil;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.lanya.DataManage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PopupWindowLanYan extends PopupWindow {

    public static Ble4_0Util ble4Util;
    public static String BleName = "";
    public static String BleAddress = "";
    Context mcontext;
    BleAdapter bleadapter;
    GiveDialogInterface dialogInterface;
    List<BluetoothChannelData> UUidData;
    public PopupWindowLanYan(Activity context, List<BluetoothChannelData> UUidData, GiveDialogInterface dialogInterface) {
        super(context);
        this.mcontext = context;
        this.dialogInterface = dialogInterface;
        this.UUidData = UUidData;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_exercise_lanya, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x7f000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ble4Util = new Ble4_0Util(context);
        ListView listView = view.findViewById(R.id.listView);
        setListView(context,listView);

        ble4Util.startScan(new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] bytes) {
                if (bluetoothDevice.getName() != null && bluetoothDevice.getName().length() > 0){
                    BleAdapter.RSISIMAp.put(bluetoothDevice.getAddress(),rssi);
                    bleadapter.addDevice(bluetoothDevice);
                }
            }
        });
    }

    private void setListView(Activity context, ListView listView) {
        bleadapter = new BleAdapter(context);
        listView.setAdapter(bleadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dismiss();

                ble4Util.setUUidData(UUidData);
//                BluetoothDevice mBluetoothDevice = (BluetoothDevice) bleadapter.getItem(i);
//                if(mBluetoothDevice.getName().equals("i-Console+1357") || mBluetoothDevice.getName().equals("HEAD0233") ){
//                }
//                if(mBluetoothDevice.getName().contains("0000fff0")){
//                }
//                ble4Util.setServiceUUid("0000fff0-0000-1000-8000-00805f9b34fb");
                ble4Util.stopScan();

                ble4Util.connect(bleadapter.getDevice(i).getAddress(), new BleUtil.CallBack() {
                    @Override
                    public void StateChange(int state, int newState) {
                        String value = null;
                        if (newState == BluetoothGatt.STATE_CONNECTED){
                            BleAddress = bleadapter.getDevice(i).getAddress();
                            value = "连接成功";
                        } else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                            value = "连接失败";
                        } else if(newState == BluetoothGatt.STATE_CONNECTING){
                            value = "连接设备中";
                        } else if(newState == BluetoothGatt.STATE_DISCONNECTING){
                            value = "断开连接中";
                        }
                        if (linkHandler != null && value != null){
                            //发送连接成功通知
                            Message message = new Message();
                            message.what = 99;
                            message.obj = value;
                            linkHandler.sendMessage(message);
                        }
                    }

                    @Override
                    public void ReadValue(byte[] value) {
                        if (linkHandler != null){
                            Message message = new Message();
                            message.what = 101;
                            message.obj = value;
                            linkHandler.sendMessage(message);
                        }
                    }
                });
//                Intent intent = new Intent(MainActivity2.this,LinkActivity2.class);
//                startActivity(intent);
            }
        });

        ble4Util.init();
    }
    private Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99:
                    //连接
                    Ble4_0Util.OpenA2dp();
                    Log.w("---》》》","连接："+ ble4Util.isConnect());
                    dialogInterface.btnConfirm(message.obj.toString());
                    break;
                case 101:
//                    dialogInterface.btnConfirm(message.obj.toString());
                    byte[] resultData = (byte[]) message.obj;
                    if(resultData.length>4){
                        setData(resultData);
                    }else{
                        Log.w("---》》》","错误："+ Arrays.toString(resultData));
                    }
                    break;
            }
            return false;
        }
    });


    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(String str);
    }

    public static void setData(byte[] resultData) {
        DataManage data = new DataManage(resultData);

        String resultData_0xff = "";
        for (int i = 0; i < resultData.length; i++) {
            resultData_0xff += Integer.toHexString(resultData[i] & 0xFF)+",";
        }
        Log.w("---》》》","接收resultData_0xff："+resultData_0xff);
        Log.w("---》》》","接收resultData："+ Arrays.toString(resultData));
        if(resultData.length > 2 && Integer.toHexString(resultData[1] & 0xFF).equals("b7")){
            Log.w("---》》》","接收：错误信息");
            if(ConstValues_Ly.CLIENT_ID ==0){
                ConstValues_Ly.CLIENT_ID = resultData[2];//Client ID
                ConstValues_Ly.METER_ID = resultData[3];//Meter ID
                for(int i=0;i < ConstValues_Ly.METER_ID_S.length;i++){
                    if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[i]){
                        ConstValues_Ly.DEVICE_TYPE_ID =ConstValues_Ly.DEVICE_TYPE_IDS[i];
                    }
                }
                postDeviceProtocolCheck();
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
            }
            startBroadcast("b2",data.getDataPayload());
            return;
        }
        if (Integer.toHexString(resultData[1] & 0xFF).equals("b0")) {
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A1));
            Log.w("---》》》", "连接中-------"+resultData_0xff);
            return;
        }
        if (Integer.toHexString(data.getMessage() & 0xFF).equals("b1")) {
            Log.w("---》》》", "最大的阻力-------"+data.getDataPayload().get(0));
            ConstValues_Ly.maxLoad =data.getDataPayload().get(0);
            if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]){
                ConstValues_Ly.data =data.getDataPayload();
            }
            return;
        }
        if (Integer.toHexString(data.getMessage() & 0xFF).equals("b5")) {
            Log.w("---》》》", "开始-------"+resultData_0xff);
            return;
        }

        if (Integer.toHexString(resultData[1] & 0xFF).equals("b2")) {
            Log.w("---》》》", "A2数据："+data.getDataPayload().toString());
            if(ConstValues_Ly.CLIENT_ID ==0){
                ConstValues_Ly.CLIENT_ID = resultData[2];//Client ID
                ConstValues_Ly.METER_ID = resultData[3];//Meter ID
                for(int i=0;i < ConstValues_Ly.METER_ID_S.length;i++){
                    if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[i]){
                        ConstValues_Ly.DEVICE_TYPE_ID =ConstValues_Ly.DEVICE_TYPE_IDS[i];
                    }
                }
                postDeviceProtocolCheck();
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
            }
            startBroadcast("b2",data.getDataPayload());
            return;
        }
    }

    //deviceBrandId=56&deviceBrandParamId=55&deviceModelId=21&deviceTypeId=6&deviceTypeParamId=230&protocolName=iconsole
    public static void postDeviceProtocolCheck() {
        int deviceBrandParamId = ConstValues_Ly.CLIENT_ID;
        int deviceTypeParamId = ConstValues_Ly.METER_ID;
        if((ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[0] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3])  || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]){
            deviceBrandParamId--;
            deviceTypeParamId--;
        }else if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1] || ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[2]){

        }
        RetrofitUtil.getInstance().apiService()
                .postDeviceProtocolCheck(ConstValues_Ly.BRAND_ID, deviceBrandParamId+"",
                        ConstValues_Ly.DEVICE_Model_ID_URL,ConstValues_Ly.DEVICE_TYPE_ID_URL,
                        ConstValues_Ly.DEVICE_TYPE_ID+"","iconsole",null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceProtocolCheckData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceProtocolCheckData> result) {
                        if(result.getCode()==0 && result.getData()!=null && !result.getData().isResult()){
                            DeviceProtocolCheckData.DeviceBrandTypeModelBean data = result.getData().getDeviceBrandTypeModel();
                            if(data!=null){
                                PopupWindowLanYan.BleName = data.getDeviceType().getName();
                                ConstValues_Ly.SB_NAME = data.getDeviceBrand().getName();
                                ConstValues_Ly.DEVICE_IMG = data.getDeviceType().getImg();
                                ConstValues_Ly.DEVICE_TYPE_ID = data.getDeviceType().getParamId();
                                initLsData();
                            }
                        }else{
                            initLsData();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });


    }

    private static void initLsData() {
        boolean isHave = false;
        List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        if(lists!=null){
            for(int i=0;i<lists.size();i++){
                if(lists.get(i).getId()==ConstValues_Ly.DEVICE_TYPE_ID){
                    isHave = true;
                    lists.get(i).setTime(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
                    lists.get(i).setState("0");
                    break;
                }
            }
            Log.w("--->>>","isHave:"+isHave);
            if(!isHave){
                HistoryEquipmentData mHistoryEquipmentData = new HistoryEquipmentData();
                mHistoryEquipmentData.setState("0");
                mHistoryEquipmentData.setName(PopupWindowLanYan.BleName);
                mHistoryEquipmentData.setId(ConstValues_Ly.DEVICE_TYPE_ID);
                mHistoryEquipmentData.setTime(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
                mHistoryEquipmentData.setImg(ConstValues_Ly.DEVICE_IMG);
                mHistoryEquipmentData.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL);
                mHistoryEquipmentData.setBrandId(ConstValues_Ly.BRAND_ID);
                mHistoryEquipmentData.setServiceUUid(ble4Util.getUuidStr()[0]);
                mHistoryEquipmentData.setReadUUID(ble4Util.getUuidStr()[1]);
                mHistoryEquipmentData.setWriteUUID(ble4Util.getUuidStr()[2]);
                mHistoryEquipmentData.setLyAddress(BleAddress);
                mHistoryEquipmentData.setName_sb(ConstValues_Ly.SB_NAME);
                lists.add(mHistoryEquipmentData);
            }
        }else{
            lists = new ArrayList<>();
            HistoryEquipmentData mHistoryEquipmentData = new HistoryEquipmentData();
            mHistoryEquipmentData.setState("0");
            mHistoryEquipmentData.setName(PopupWindowLanYan.BleName);
            mHistoryEquipmentData.setId(ConstValues_Ly.DEVICE_TYPE_ID);
            mHistoryEquipmentData.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL);
            mHistoryEquipmentData.setTime(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
            mHistoryEquipmentData.setImg(ConstValues_Ly.DEVICE_IMG);
            mHistoryEquipmentData.setBrandId(ConstValues_Ly.BRAND_ID);
            mHistoryEquipmentData.setServiceUUid(ble4Util.getUuidStr()[0]);
            mHistoryEquipmentData.setReadUUID(ble4Util.getUuidStr()[1]);
            mHistoryEquipmentData.setWriteUUID(ble4Util.getUuidStr()[2]);
            mHistoryEquipmentData.setLyAddress(BleAddress);
            mHistoryEquipmentData.setName_sb(ConstValues_Ly.SB_NAME);
            lists.add(mHistoryEquipmentData);
        }
        SharedHistoryEquipment.singleton().putSharedHistoryEquipment(lists);
    }

    private static void startBroadcast(String type ,ArrayList<Integer> data){
        //开启广播
        //创建一个意图对象
        Intent intent = new Intent("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");
        //发送广播的数据
        intent.putExtra("type", type);
        intent.putIntegerArrayListExtra("data", data);
        //发送
        MainApplication.getContext().sendBroadcast(intent);
    }
}
