package com.jxkj.fit_5a.lanya;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class LinkActivity2 extends Activity implements View.OnClickListener {

    private static StringBuffer consoleStrBuff;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date date;
    static int pos = 0;
    private static TextView bleStatusText ,showMsgText;

    public static int Load = 0;
    public static int maxLoad = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link2);

        ConstValues_Ly.CLIENT_ID = 0x00;//Client ID
        ConstValues_Ly.METER_ID = 0x00;//Meter ID

        bleStatusText = (TextView)findViewById(R.id.link_info_text);
        showMsgText= (TextView)findViewById(R.id.show_msg_text);
        findViewById(R.id.link_info_text).setOnClickListener(this);
        findViewById(R.id.send_btn0).setOnClickListener(this);
        findViewById(R.id.send_btn1).setOnClickListener(this);
        findViewById(R.id.send_btn2).setOnClickListener(this);
        findViewById(R.id.send_btn30).setOnClickListener(this);
        findViewById(R.id.send_btn31).setOnClickListener(this);
        findViewById(R.id.send_btn32).setOnClickListener(this);
        findViewById(R.id.send_btn33).setOnClickListener(this);
        findViewById(R.id.send_btn40).setOnClickListener(this);
        findViewById(R.id.send_btn41).setOnClickListener(this);
        findViewById(R.id.send_btn50).setOnClickListener(this);
        findViewById(R.id.send_btn51).setOnClickListener(this);
        findViewById(R.id.send_btn52).setOnClickListener(this);
        findViewById(R.id.send_btn60).setOnClickListener(this);
        findViewById(R.id.send_btn61).setOnClickListener(this);
        findViewById(R.id.send_btn62).setOnClickListener(this);

    }

    public static void appendConsole(String msg) {
        if (consoleStrBuff == null) consoleStrBuff = new StringBuffer();
        if (consoleStrBuff.length() > 1024) consoleStrBuff.delete(0, 200);
        if (date == null) date = new Date();
        date.setTime(System.currentTimeMillis());
        consoleStrBuff.append(dateFormat.format(date) + ":" + msg + "\n");
        showMsgText.setText(consoleStrBuff);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity2.ble4Util.disconnect();
    }
    public static Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99: {
                    //连接
                    if (bleStatusText != null) {
                        bleStatusText.setText(message.obj.toString());
                        Ble4_0Util.OpenA2dp();
                    }
                }
                break;
                case 101: {
                    //接收到数据
                    byte[] resultData = (byte[]) message.obj;
                    DataManage data = new DataManage(resultData);

                    String resultData_0xff = "";
                    for (int i = 0; i < resultData.length; i++) {
                        resultData_0xff += Integer.toHexString(resultData[i] & 0xFF)+",";
                    }
                    if(resultData.length > 2 && Integer.toHexString(resultData[1] & 0xFF).equals("b7")){
                        appendConsole("接收：错误信息");
                        break;
                    }
                    if (Integer.toHexString(resultData[1] & 0xFF).equals("b0")) {
                        Log.w("---》》》", "连接中-------"+resultData_0xff);
                        break;
                    }
                    if (pos == 1 && Integer.toHexString(data.getMessage() & 0xFF).equals("b1")) {
                        ConstValues_Ly.CLIENT_ID = resultData[2];//Client ID
                        ConstValues_Ly.METER_ID = resultData[3];//Meter ID
                        if(maxLoad==0){
                            TimeThreadUtils.sendDataA0();
                            maxLoad = data.getDataPayload().get(0);
                            MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X02));

                            MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A2));
                        }
                        Log.w("---》》》", "最大的阻力-------"+maxLoad);
                        break;
                    }

                    if (pos == 2 && Integer.toHexString(resultData[1] & 0xFF).equals("b2")) {
                        Log.w("---》》》", "A2数据："+data.getDataPayload().toString());
                        ArrayList<Integer> dataList = data.getDataPayload();
                        int timeMinute =  dataList.get(0)-1;//时间-分
                        int timeSecond =  dataList.get(1)-1;//时间-秒
                        String time = ConstValues_Ly.getTime(timeMinute,timeSecond);

                        int speedHi = dataList.get(2)-1;//速度-百十
                        int speedLow = dataList.get(3)-1;//速度-个小数点下一位
                        double speed = ConstValues_Ly.getBaiShiGeX(speedHi,speedLow);

                        int rpmHi = dataList.get(4)-1;//每分钟转数 -千百
                        int rpmLow = dataList.get(5)-1;//每分钟转数 -十个
                        int rpm = ConstValues_Ly.getQianBaiShiGe(rpmHi,rpmLow);

                        int DistanceHi = dataList.get(6)-1;//距离-百十
                        int DistanceLow = dataList.get(7)-1;//距离-个小数点下一位
                        double Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

                        int CaloriesHi = dataList.get(8)-1;// 卡路里 -千,佰
                        int CaloriesLow = dataList.get(9)-1;// 卡路里 -个十
                        double Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

                        int PulseHi = dataList.get(10)-1;//跳动 千,佰
                        int PulseLow = dataList.get(11)-1;//跳动 千,佰 -个十
                        double Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

                        int WattHi = dataList.get(12)-1;//瓦特--佰,拾
                        int WattLow = dataList.get(13)-1;//瓦特--佰,拾个小数点下一位
                        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

                        int load = dataList.get(14)-1;//阻力

                        String Unit ="Stop";
                        if(dataList.get(15)-1==1){
                            Unit ="Start";
                        }
                        Load = load;
                        String re = "A2--->>>:时间："+time+",速度："+speed+",转数："+rpm+",距离："+Distance+",卡路里："+Calories
                                +",脉跳："+Pulse+",瓦特："+Watt+",阻力："+load+",状态："+Unit;
                        Log.w("---》》》", re);
                        appendConsole("接收："+re);
                        break;
                    }
                    if (pos == 3 && Integer.toHexString(resultData[1] & 0xFF).equals("b3")) {
                        appendConsole("接收：A3"+resultData);
                        break;
                    }

                    if (pos == 4 && Integer.toHexString(resultData[1] & 0xFF).equals("b4")) {
                        //
//                        byte[] aa = new byte[]{-16, -76, 56, 1, +++13, 16, 66, 41, 45, 11, 86, 13, 2, 1, 3};
//                        MainActivity2.ble4Util.sendData(aa);
//                        Data0：Client ID
//                        Data1：Meter ID
//                        Data2：Minute Set Byte (分00~99)
//                        Data3：Distance Set Hi Byte (佰,拾 00~99)
//                        Data4：Distance Set Low Byte (個,小數點以下一位 0.0~9.9)
//                        Data5：Calories Set Hi Byte (千,佰 00~99)
//                        Data6：Calories Set Low Byte (拾,個00~99)
//                        Data7：Pulse Set Hi Byte (千,佰 00~99)
//                        Data8：Pulse Set Low Byte (拾,個00~99)
//                        Data9：Watt Set Hi Byte (佰,拾 00~99)
//                        Data10：Watt Set Low Byte (個,小數點以下一位 0.0~9.9)
//                        Data11：0x00：KM  0x01：ML
                        ArrayList<Integer> dataList = data.getDataPayload();

                        int Minute =  dataList.get(0)-1;

                        int DistanceHi = dataList.get(1)-1;
                        int DistanceLow = dataList.get(2)-1;
                        double Distance = ConstValues_Ly.getBaiShiGeX(DistanceHi,DistanceLow);

                        int CaloriesHi = dataList.get(3)-1;
                        int CaloriesLow = dataList.get(4)-1;
                        int Calories = ConstValues_Ly.getQianBaiShiGe(CaloriesHi,CaloriesLow);

                        int PulseHi = dataList.get(5)-1;
                        int PulseLow = dataList.get(6)-1;
                        int Pulse = ConstValues_Ly.getQianBaiShiGe(PulseHi,PulseLow);

                        int WattHi = dataList.get(7)-1;
                        int WattLow = dataList.get(8)-1;
                        double Watt = ConstValues_Ly.getBaiShiGeX(WattHi,WattLow);

                        String Unit ="KM";
                        if(dataList.get(9)-1==1){
                            Unit ="ML";
                        }
                        String re = "B4--->>>:时间："+Minute+",距离："+Distance+",卡路里："+Calories+",脉跳："+Pulse+",瓦特："+Watt+",单元："+Unit;
                        Log.w("---》》》", re);

                        appendConsole("接收："+re);
                        break;
                    }

//                    if (pos == 0) {
//                        ConstValues_Ly.CLIENT_ID = resultData[2];
//                        ConstValues_Ly.METER_ID = resultData[3];
//                        byte[] sendData = {ConstValues_Ly.START, ConstValues_Ly.MESSAGE_A0, ConstValues_Ly.CLIENT_ID,
//                                ConstValues_Ly.METER_ID, ConstValues_Ly.getCHECKSUM(ConstValues_Ly.MESSAGE_A0)};
//                        MainActivity2.ble4Util.sendData(sendData);
//                    }
                }
                break;
            }
            return false;
        }
    });

    @Override
    public void onClick(View view) {
        if(!isSendData()){
            return;
        }
        switch (view.getId()) {
            case R.id.link_info_text:
                if (bleStatusText.getText().toString().indexOf("成功") < 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LinkActivity2.this);
                    builder.setTitle("提示");
                    builder.setMessage("断开当前连接？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity2.ble4Util.disconnect();
                            LinkActivity2.this.finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
                break;
            case R.id.send_btn0:
                pos = 0;
                break;
            case R.id.send_btn1:
                pos = 1;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A1));
                break;
            case R.id.send_btn2:
                pos = 2;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A2));
                break;
            case R.id.send_btn30:
                pos = 3;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0XF0));
                break;
            case R.id.send_btn31:
                pos = 3;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X01));
                break;
            case R.id.send_btn32:
                pos = 3;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X02));
                break;
            case R.id.send_btn33:
                pos = 3;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A3, (byte) 0X03));
                break;
            case R.id.send_btn40:
                pos = 4;
//                Data0：Client ID
//                Data1：Meter ID
                //02:00
//                Data2：Minute Set Byte (分00~99) 03

                //156
//                Data3：Distance Set Hi Byte (佰,拾 00~99) 10
//                Data4：Distance Set Low Byte (個,小數點以下一位 0.0~9.9)3d
                //40
//                Data5：Calories Set Hi Byte (千,佰 00~99)01
//                Data6：Calories Set Low Byte (拾,個00~99)29
                //85
//                Data7：Pulse Set Hi Byte (千,佰 00~99)01
//                Data8：Pulse Set Low Byte (拾,個00~99)56
                //120
//                Data9：Watt Set Hi Byte (佰,拾 00~99)0d
//                Data10：Watt Set Low Byte (個,小數點以下一位 0.0~9.9)01

//                Data11：0x00：KM  0x01：ML  01

//              Time= 02:00
//              Distance=156
//              Calories=40
//              Pulse=85
//              Watt=120
//              UNIT=KM  :[-16, -92, 56, 1, 13, 16, 66, 41, 45, 11, 86, 13, 2, 1, -13]
//              UNIT=KM  :[-16, -76, 56, 1, 13, 16, 66, 41, 45, 11, 86, 13, 2, 1, 3]
                byte[] data_km =  ConstValues_Ly.getA4Data(12,156.5,4044,1085,120.1,"KM");
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A4, data_km));
                break;
            case R.id.send_btn41:
                pos = 4;
                byte[] data_ml =  ConstValues_Ly.getA4Data(86,156,40,85,120,"ML");
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A4, data_ml));
                break;
            case R.id.send_btn50:
                pos = 5;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x01));
                break;
            case R.id.send_btn51:
                pos = 5;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x02));
                break;
            case R.id.send_btn52:
                pos = 5;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A5, (byte) 0x03));
                break;
            case R.id.send_btn60:
                pos = 6;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A6, (byte)(Load+1)));
                break;
            case R.id.send_btn61:
                pos = 6;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A6, (byte)(Load-1)));
                break;
            case R.id.send_btn62:
                pos = 6;
                MainActivity2.ble4Util.sendData(ConstValues_Ly.getByteData(ConstValues_Ly.MESSAGE_A6, (byte)(28)));
                break;
        }
    }
    private boolean isSendData() {
//        if (pos != 0 && ConstValues_Ly.CLIENT_ID == 0 && ConstValues_Ly.METER_ID == 0) {
//            Toast.makeText(LinkActivity2.this, "先进行连接", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        String stateStr = bleStatusText.getText().toString();
        if (stateStr.indexOf("成功") < 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LinkActivity2.this);
            builder.setTitle("提示");
            builder.setMessage("当前设备未连接成功，无法发送");
            builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
            return false;
        }
        return true;
    }
}
