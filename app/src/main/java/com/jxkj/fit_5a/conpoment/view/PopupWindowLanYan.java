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

import androidx.annotation.NonNull;

import com.android.chileaf.DumbbellManager;
import com.android.chileaf.fitness.common.FilterScanCallback;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
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
import no.nordicsemi.android.support.v18.scanner.ScanResult;


public class PopupWindowLanYan extends PopupWindow {

    public static Ble4_0Util ble4Util;
    public static String BleName = "";
    public static String BleAddress = "";
    public static String BleSbName = "";
    public static int outType_Activity = 0;//链接蓝牙的页面
    String sbName;
    Context mcontext;
    BleAdapter bleadapter;
    public static GiveDialogInterface dialogInterface;
    List<BluetoothChannelData> UUidData;
    public PopupWindowLanYan(Activity context, List<BluetoothChannelData> UUidData,String sbName, GiveDialogInterface dialogInterface) {
        super(context);
        this.mcontext = context;
        this.sbName = sbName;
        PopupWindowLanYan.dialogInterface = dialogInterface;
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
                if(ble4Util!=null){
                    ble4Util.stopScan();
                }
                dismiss();
            }
        });
        ble4Util = new Ble4_0Util(context);
        ble4Util.init();
        ListView listView = view.findViewById(R.id.listView);
        Log.w("bluetoothDeviceName:","11sbName"+sbName);
        setListView(context,listView);
        ble4Util.startScan(new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] bytes) {
                if (StringUtil.isNotBlank(bluetoothDevice.getName())){
                    Log.w("bluetoothDeviceName:","--->>"+bluetoothDevice.getName().toLowerCase());
                    for(int i=0;i<ConstValues_Ly.mBleNames.length;i++){
                        if(bluetoothDevice.getName().toLowerCase().contains(ConstValues_Ly.mBleNames[i])){
                            BleAdapter.RSISIMAp.put(bluetoothDevice.getAddress(),rssi);
                            bleadapter.addDevice(bluetoothDevice);
                        }
                    }
                }
            }
        });
    }

    /**
     * Updates the list of not bonded devices.
     *
     * @param results list of results from the scanner
     */
    public static void update(Handler linkHandler,final List<ScanResult> results) {
        for (final ScanResult result : results) {
            String name = result.getDevice().getName();
            int rssi = result.getRssi();
            String address = result.getDevice().getAddress();
            Log.w("bluetoothDeviceName:","name"+name+";rssi:"+rssi);
            if(StringUtil.isNotBlank(name)){
                if(ConstValues_Ly.isYalingsheben(name)){
                    Log.w("bluetoothDeviceName:","链接成功");
                    ConstValues_Ly.isA1 = true;
                    //发送连接成功通知
                    BleAddress = address;
                    BleSbName = name;
                    PopupWindowLanYan.mManager.stopScan();
                    PopupWindowLanYan.mManager.stop();
                    postDeviceProtocolCheck();
//                    Message message = new Message();
//                    message.what = 99;
//                    message.obj = "连接成功";
//                    linkHandler.sendMessage(message);
                }
            }
        }
    }
    public static DumbbellManager mManager;

    public static void mManagerDisconnect() {
        if (null != mManager) {
            mManager.disConnect();
            mManager = null;
            PopupWindowLanYan.BleName = "";
            PopupWindowLanYan.BleSbName = "";
            ConstValues_Ly.METER_ID = 0x00;
            ConstValues_Ly.CLIENT_ID = 0x00;
            ConstValues_Ly.CURRENT_STATE = 0;
            ConstValues_Ly.maxLoad = 0;
            ConstValues_Ly.isA1 = false;
            ConstValues_Ly.DEVICE_TYPE_ID_PARAM = 0;
            ConstValues_Ly.SB_NAME = "";
            Ble4_0Util.initLsData();
        }
    }
    private void setListView(Activity context, ListView listView) {
        bleadapter = new BleAdapter(context);
        listView.setAdapter(bleadapter);
//        String[] FILTER_NAMES = new String[]{"HD211", "DB201"};
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dismiss();
                Log.w("bluetoothDeviceName:","getName"+bleadapter.getDevice(i).getName().toLowerCase());
                ble4Util.setUUidData(UUidData);
                ble4Util.stopScan();
                Log.w("bluetoothDeviceName:","sb22Name"+sbName);

                if(sbName.equals("哑铃") && isYaLingLianJie(context,i)){
                    return;
                }
//                BluetoothDevice mBluetoothDevice = (BluetoothDevice) bleadapter.getItem(i);
//                if(mBluetoothDevice.getName().equals("i-Console+1357") || mBluetoothDevice.getName().equals("HEAD0233") ){
//                }
//                if(mBluetoothDevice.getName().contains("0000fff0")){
//                }
//                ble4Util.setServiceUUid("0000fff0-0000-1000-8000-00805f9b34fb");

                ble4Util.connect(bleadapter.getDevice(i).getAddress(), new BleUtil.CallBack() {
                    @Override
                    public void StateChange(int state, int newState) {
                        String value = null;
                        if (newState == BluetoothGatt.STATE_CONNECTED){
                            BleAddress = bleadapter.getDevice(i).getAddress();
                            BleSbName = bleadapter.getDevice(i).getName();
                            value = "连接成功";
                        } else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                            PopupWindowLanYan.BleName = "";
                            PopupWindowLanYan.BleSbName = "";
                            value = "连接失败";
                        } else if(newState == BluetoothGatt.STATE_CONNECTING){
                            value = "连接设备中";
                        } else if(newState == BluetoothGatt.STATE_DISCONNECTING){
                            PopupWindowLanYan.BleName = "";
                            PopupWindowLanYan.BleSbName = "";
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

    }
    public boolean isYaLingLianJie(Activity context,int i){
        if(ConstValues_Ly.isYalingsheben(bleadapter.getDevice(i).getName())){
            if(!sbName.equals("哑铃")){
                Message message = new Message();
                message.what = 99;
                message.obj = "此设备属于哑铃";
                linkHandler.sendMessage(message);
                return true;
            }
            mManager = DumbbellManager.getInstance(context);
            mManager.setDebug(true);
//                    mManager.setFilterNames(FILTER_NAMES);
            mManager.connect(bleadapter.getDevice(i), false);

            //发送连接成功通知
            Message message = new Message();
            message.what = 99;
            message.obj = "连接设备中";
            linkHandler.sendMessage(message);
            Log.w("bluetoothDeviceName:","mManager"+mManager);
            PopupWindowLanYan.mManager.startScan(new FilterScanCallback() {
                @Override
                public void onFilterScanResults(@NonNull List<ScanResult> results) {
                    if(StringUtil.isBlank(BleSbName)){
                        update(linkHandler,results);
                    }

                }
            });
            return true;
        }
        return false;
    }
    private Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if(message==null && message.obj==null){
                Log.w("---》》》","错误：");
                return false;
            }
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
//        Log.w("---》》》","接收resultData_0xff："+resultData_0xff);
        Log.w("---》》》","接收resultData："+ Arrays.toString(resultData));
//        if(Integer.parseInt(ConstValues_Ly.DEVICE_TYPE_ID_URL) != resultData[2]){
//            Log.w("---》》》","设备类型不符合");
//            ble4Util.disconnect();
//            return;
//        }
        if(resultData.length > 2 && Integer.toHexString(resultData[1] & 0xFF).equals("b7")){
            Log.w("---》》》","接收：错误信息");
            if(ConstValues_Ly.CLIENT_ID ==0){
                ConstValues_Ly.CLIENT_ID = resultData[2];//Client ID
                ConstValues_Ly.METER_ID = resultData[3];//Meter ID
                for(int i=0;i < ConstValues_Ly.METER_ID_S.length;i++){
                    if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[i]){
                        ConstValues_Ly.DEVICE_TYPE_ID_PARAM = ConstValues_Ly.DEVICE_TYPE_IDS[i];
                    }
                }
                postDeviceProtocolCheck();
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
            }
            startBroadcast("b2",data.getDataPayload());
            return;
        }
        if (Integer.toHexString(resultData[1] & 0xFF).equals("b0")) {
            if(ConstValues_Ly.CLIENT_ID ==0){
                ConstValues_Ly.CLIENT_ID = resultData[2];//Client ID
                ConstValues_Ly.METER_ID = resultData[3];//Meter ID
                for(int i=0;i < ConstValues_Ly.METER_ID_S.length;i++){
                    if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[i]){
                        ConstValues_Ly.DEVICE_TYPE_ID_PARAM =ConstValues_Ly.DEVICE_TYPE_IDS[i];
                    }
                }
                postDeviceProtocolCheck();
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
                return;
            }
            PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A1));
            Log.w("---》》》", "连接中-------"+resultData_0xff);
            return;
        }
        if (Integer.toHexString(data.getMessage() & 0xFF).equals("b1")) {
            ConstValues_Ly.isA1 = true;
            if(data.getDataPayload().size()==1){
                Log.w("---》》》", "最大的阻力-------"+data.getDataPayload().get(0));
                ConstValues_Ly.maxLoad =data.getDataPayload().get(0);
            }
            if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[4]){
                ConstValues_Ly.data =data.getDataPayload();
                return;
            }
            if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[1]){
                ConstValues_Ly.wheelDiameter =ConstValues_Ly.getBaiShiGeX(data.getDataPayload().get(0),data.getDataPayload().get(1));
                return;
            }
            return;
        }
        Log.w("---》》》", "开始data.getMessage()"+Integer.toHexString(data.getMessage() & 0xFF));
        if (Integer.toHexString(data.getMessage() & 0xFF).equals("b5")) {
            //f0,b5,38,e7,4,c8
            Log.w("---》》》", "开始-------"+resultData_0xff);
            if(resultData.length==6 && resultData[4] == 4){
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteDataJia(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
            }
            return;
        }

        if (Integer.toHexString(resultData[1] & 0xFF).equals("b2")) {
            if(ConstValues_Ly.CLIENT_ID ==0){
                ConstValues_Ly.CLIENT_ID = resultData[2];//Client ID
                ConstValues_Ly.METER_ID = resultData[3];//Meter ID
                for(int i=0;i < ConstValues_Ly.METER_ID_S.length;i++){
                    if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[i]){
                        ConstValues_Ly.DEVICE_TYPE_ID_PARAM =ConstValues_Ly.DEVICE_TYPE_IDS[i];
                    }
                }
                postDeviceProtocolCheck();
                PopupWindowLanYan.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A0));
            }
            Log.w("---》》》", "A2数据："+data.getDataPayload());
            if(data.getDataPayload()!=null && data.getDataPayload().size()>0){
                startBroadcast("b2",data.getDataPayload());
            }
            return;
        }
    }

    public static void postDeviceProtocolCheck() {
        if(outType_Activity==0){
            initLsData();
            return;
        }
        if(ConstValues_Ly.isYalingsheben(PopupWindowLanYan.BleSbName)){
            if(!PopupWindowLanYan.BleName.contains(PopupWindowLanYan.BleSbName)){
                PopupWindowLanYan.BleName = PopupWindowLanYan.BleName+"-"+PopupWindowLanYan.BleSbName;
            }
            initLsData();
            if(PopupWindowLanYan.dialogInterface!=null){
                PopupWindowLanYan.dialogInterface.btnConfirm("出现UI");
                PopupWindowLanYan.dialogInterface = null;
            }
            return;
        }
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
                        ConstValues_Ly.DEVICE_TYPE_ID_PARAM+"","iconsole",null)
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
                            if(data!=null && data.getDeviceType()!=null){
                                PopupWindowLanYan.BleName = data.getDeviceType().getName()+"-"+PopupWindowLanYan.BleSbName;
                                ConstValues_Ly.SB_NAME = data.getDeviceBrand().getName();
                                ConstValues_Ly.DEVICE_IMG = data.getDeviceType().getImg();
                                ConstValues_Ly.DEVICE_TYPE_ID_URL = data.getDeviceType().getId()+"";
                                ConstValues_Ly.DEVICE_TYPE_ID_PARAM = data.getDeviceType().getParamId();
                                ConstValues_Ly.DEVICE_TYPE_ID = data.getDeviceType().getId();
                                initLsData();
                            }else{
                                ToastUtils.showShort("请求数据丢失：getDeviceType");
                            }
                        }else{
                            if(!PopupWindowLanYan.BleName.contains(PopupWindowLanYan.BleSbName)){
                                PopupWindowLanYan.BleName = PopupWindowLanYan.BleName+"-"+PopupWindowLanYan.BleSbName;
                            }
                            initLsData();
                        }
                        if(PopupWindowLanYan.dialogInterface!=null){
                            PopupWindowLanYan.dialogInterface.btnConfirm("出现UI");
                            PopupWindowLanYan.dialogInterface = null;
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
        Log.w("--->>>","isHave:"+lists.toString());
        Log.w("--->>>","PopupWindowLanYan.BleName:"+PopupWindowLanYan.BleName);
        if(lists!=null){
            for(int i=0;i<lists.size();i++){
                if(lists.get(i).getName().equals(PopupWindowLanYan.BleName)){
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
                mHistoryEquipmentData.setId(ConstValues_Ly.DEVICE_TYPE_ID_PARAM);
                mHistoryEquipmentData.setTime(StringUtil.getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
                mHistoryEquipmentData.setImg(ConstValues_Ly.DEVICE_IMG);
                mHistoryEquipmentData.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL);
                mHistoryEquipmentData.setDeviceModelId(ConstValues_Ly.DEVICE_Model_ID_URL);
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
            mHistoryEquipmentData.setId(ConstValues_Ly.DEVICE_TYPE_ID_PARAM);
            mHistoryEquipmentData.setDeviceTypeId(ConstValues_Ly.DEVICE_TYPE_ID_URL);
            mHistoryEquipmentData.setDeviceModelId(ConstValues_Ly.DEVICE_Model_ID_URL);
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

    public static void startBroadcast_Two(String type){
        Intent intent = new Intent("com.jxkj.fit_5a.view.fragment.HomeTwoFragment");
        //发送广播的数据
        intent.putExtra("type", type);
        MainApplication.getContext().sendBroadcast(intent);
    }

    public static void startBroadcast(String type ,ArrayList<Integer> data){
        //开启广播
        //创建一个意图对象
        Intent intent = new Intent("com.jxkj.fit_5a.view.activity.exercise.RatePatternActivity");
        //发送广播的数据
        intent.putExtra("type", type);
        if(data!=null){
            intent.putIntegerArrayListExtra("data", data);
        }
        //发送
        MainApplication.getContext().sendBroadcast(intent);
    }
//    lists:[HistoryEquipmentData
//    {img='https://oss.5afit.com/device/ZNYtWUJyNIsPh1c5LJU3JQ.png', name='椭圆机-Fit Hi Way0105', time='2022-08-11 16:23:08', state='1', serviceUUid='49535343-fe7d-4ae5-8fa9-9fafd205e455', LyAddress='E8:5D:86:01:5F:92', name_sb='', id=0},
//    {img='https://oss.5afit.com/device/vwQjLAIiNW06y1YKOv4A.png', name='室内单车/健身车-HEAD-S330-0001', time='2022-08-11 09:46:30', state='1', serviceUUid='0000fff0-0000-1000-8000-00805f9b34fb', LyAddress='D4:90:A2:79:78:1C', name_sb='HEAD FITNESS', id=200}]
}
