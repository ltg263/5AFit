package com.jxkj.fit_5a.view.activity.exercise;

import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.chileaf.DumbbellManager;
import com.android.chileaf.fitness.common.FilterScanCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.OnRecyclerItemClickListener;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.SwipeRecyclerView;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.BleAdapter;
import com.jxkj.fit_5a.lanya.BleUtil;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.activity.yaling.YaLingActivity_1;
import com.jxkj.fit_5a.view.adapter.HistoryEquipmentAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import no.nordicsemi.android.support.v18.scanner.ScanResult;

public class HistoryEquipmentActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rv_list)
    SwipeRecyclerView mRvList;
    List<HistoryEquipmentData> lists;
    private HistoryEquipmentAdapter mHistoryEquipmentAdapter;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected int getContentView() {
        return R.layout.activity_history_equipment;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("历史设备");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
        mTvRighttext.setText("新增");

//        SharedHistoryEquipment.singleton().putSharedHistoryEquipment(lists);

        lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        Log.w("--->>>", "lists:" + lists.toString());
        mHistoryEquipmentAdapter = new HistoryEquipmentAdapter(lists);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHistoryEquipmentAdapter);
        mRvList.setRightClickListener(new SwipeRecyclerView.OnRightClickListener() {
            @Override
            public void onRightClick(int position, String id) {
                if (StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
                    ToastUtils.showShort("请先断开当前连接");
                    return;
                }
//                DialogUtils.showDialogHint(HistoryEquipmentActivity.this, "您确定要删除此记录吗？", false, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        mHistoryEquipmentAdapter.remove(position);
//                        SharedHistoryEquipment.singleton().putSharedHistoryEquipment(mHistoryEquipmentAdapter.getData());
//                    }
//                });

                DialogUtils.showUnificationDialog(HistoryEquipmentActivity.this, "提示", "您确定要删除此记录吗？", "确定", true,
                        new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                mHistoryEquipmentAdapter.remove(position);
                                SharedHistoryEquipment.singleton().putSharedHistoryEquipment(mHistoryEquipmentAdapter.getData());
                            }
                        });
            }
        });

        mRvList.addOnItemTouchListener(new OnRecyclerItemClickListener(mRvList) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                Toast.makeText(HistoryEquipmentActivity.this, "123", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                    mItemTouchHelper.startDrag(vh);

                    //获取系统震动服务
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);

                }
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(lists, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(lists, i, i - 1);
                    }
                }
                mHistoryEquipmentAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                myAdapter.notifyItemRemoved(position);
//                datas.remove(position);
            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });

        mItemTouchHelper.attachToRecyclerView(mRvList);


        mHistoryEquipmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_ygz:
                        if (mHistoryEquipmentAdapter.getData().get(position).getState().equals("1")) {
                            if (StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
                                ToastUtils.showShort("请先断开当前连接");
                                return;
                            }
                            goLianJie(HistoryEquipmentActivity.this, mHistoryEquipmentAdapter.getData().get(position), new GoLianJieInterface() {
                                @Override
                                public void bntClickListener(String tvStr, String imgStr) {
                                    if(ConstValues_Ly.isYalingsheben(PopupWindowLanYan.BleName)){
                                        YaLingActivity_1.intentActivity(HistoryEquipmentActivity.this);
                                    }
                                    finish();
                                }
                            });

                        } else {
//                            DialogUtils.showDialogHint(HistoryEquipmentActivity.this, "您确定要断开当前连接吗？", false, new DialogUtils.ErrorDialogInterface() {
//                                @Override
//                                public void btnConfirm() {
//                                    PopupWindowLanYan.ble4Util.disconnect();
//                                    mHistoryEquipmentAdapter.setNewData(SharedHistoryEquipment.singleton().getSharedHistoryEquipment());
//                                }
//                            });

                            DialogUtils.showUnificationDialog(HistoryEquipmentActivity.this, "提示", "您确定要断开当前连接吗？", "确定", true,
                                    new DialogUtils.UnificationDialogInterface() {
                                        @Override
                                        public void bntClickListener(String pos) {
                                            if(ConstValues_Ly.isYalingsheben(mHistoryEquipmentAdapter.getData().get(position).getName())){
                                                PopupWindowLanYan.mManagerDisconnect();
                                                mHistoryEquipmentAdapter.setNewData(SharedHistoryEquipment.singleton().getSharedHistoryEquipment());
                                                return;
                                            }
                                            PopupWindowLanYan.ble4Util.disconnect();
                                            mHistoryEquipmentAdapter.setNewData(SharedHistoryEquipment.singleton().getSharedHistoryEquipment());
                                        }
                                    });
                        }
                        break;
                    case R.id.ll_hidden:

                        break;
                }

            }
        });
    }

    static boolean isSouSuo = false;
    static BaseActivity mBaseActivityC = null;

    public static GoLianJieInterface mGoLianJieInterfaceC;

    public interface GoLianJieInterface {
        /**
         * 确定
         */
        void bntClickListener(String tvStr, String imgStr);
    }

    public static void goLianJie(BaseActivity mBaseActivity, HistoryEquipmentData historyEquipmentData, GoLianJieInterface mGoLianJieInterface) {
        PopupWindowLanYan.outType_Activity = 0;
        mBaseActivityC = mBaseActivity;
        mGoLianJieInterfaceC = mGoLianJieInterface;
        Log.w("historyEquipmentData", "historyEquipmentData:" + historyEquipmentData.toString());
        ConstValues_Ly.BRAND_ID = historyEquipmentData.getBrandId();
        PopupWindowLanYan.ble4Util = new Ble4_0Util(mBaseActivity);
        PopupWindowLanYan.ble4Util.init();
        String[] uuidData = new String[3];
        uuidData[0] = historyEquipmentData.getServiceUUid();
        uuidData[1] = historyEquipmentData.getReadUUID();
        uuidData[2] = historyEquipmentData.getWriteUUID();
        Log.w("historyEquipmentData", "uuidData:" + Arrays.toString(uuidData));
        mBaseActivity.show("设备搜索...");
        isSouSuo = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    if (isSouSuo) {
                        mBaseActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBaseActivity.dismiss();
                                ToastUtils.showShort("未搜索到相关设备");
                                PopupWindowLanYan.ble4Util.stopScan();
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        PopupWindowLanYan.ble4Util.startScan(new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Log.w("historyEquipmentData", "device:" + device.getAddress());
                Log.w("historyEquipmentData", "historyEquipmentData.getName():" + historyEquipmentData.getName());
                if (!device.getAddress().equals(historyEquipmentData.getLyAddress())) {
                    return;
                }
                isSouSuo = false;
                mBaseActivity.dismiss();
                PopupWindowLanYan.ble4Util.setUuidStr(uuidData);
                PopupWindowLanYan.ble4Util.stopScan();
                if(ConstValues_Ly.isYalingsheben(historyEquipmentData.getName())){
                    PopupWindowLanYan.mManager = DumbbellManager.getInstance(mBaseActivity);
                    PopupWindowLanYan.mManager.setDebug(true);
//                    mManager.setFilterNames(FILTER_NAMES);
                    PopupWindowLanYan.mManager.connect(device, false);

                    //发送连接成功通知
                    mBaseActivity.show("蓝牙连接中...");
                    Message message = new Message();
                    message.what = 99;
                    message.obj = "连接设备中";
                    linkHandler.sendMessage(message);
                    PopupWindowLanYan.mManager.startScan(new FilterScanCallback() {
                        @Override
                        public void onFilterScanResults(@NonNull List<ScanResult> results) {
                            Log.w("bluetoothDeviceName:","onFilterScanResults");
                            if (StringUtil.isBlank(PopupWindowLanYan.BleSbName)) {
                                if(PopupWindowLanYan.mManager!=null){
                                    update(mBaseActivity,historyEquipmentData,linkHandler);
                                }
                            }

                        }
                    });

                } else {

                    PopupWindowLanYan.ble4Util.connect(historyEquipmentData.getLyAddress(), new BleUtil.CallBack() {
                        @Override
                        public void StateChange(int state, int newState) {
                            String value = null;
                            PopupWindowLanYan.BleName = "";
                            if (newState == BluetoothGatt.STATE_CONNECTED) {
                                mBaseActivity.dismiss();
                                PopupWindowLanYan.BleName = historyEquipmentData.getName();
                                ConstValues_Ly.DEVICE_IMG = historyEquipmentData.getImg();
                                ConstValues_Ly.DEVICE_Model_ID_URL = historyEquipmentData.getDeviceModelId();
                                ConstValues_Ly.DEVICE_TYPE_ID_URL = historyEquipmentData.getDeviceTypeId();
                                value = "连接成功";
                            } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                                mBaseActivity.dismiss();
                                value = "连接失败";
                            } else if (newState == BluetoothGatt.STATE_CONNECTING) {
                                mBaseActivity.show("蓝牙连接中...");
                                value = "连接设备中";
                            } else if (newState == BluetoothGatt.STATE_DISCONNECTING) {
                                value = "断开连接中";
                            }

                            if (linkHandler != null && value != null) {
                                //发送连接成功通知
                                Message message = new Message();
                                message.what = 99;
                                message.obj = value;
                                linkHandler.sendMessage(message);
                            }
//                        String finalValue = value;
//                        mBaseActivity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                        ToastUtils.showShort(finalValue);
//                            }
//                        });
                        }

                        @Override
                        public void ReadValue(byte[] value) {
                            mBaseActivity.dismiss();
                            if (linkHandler != null) {
                                Message message = new Message();
                                message.what = 101;
                                message.obj = value;
                                linkHandler.sendMessage(message);
                            }

                        }
                    });
                }
            }
        });
    }
    public static void update(BaseActivity mBaseActivity,HistoryEquipmentData historyEquipmentData,Handler linkHandler) {
        Log.w("bluetoothDeviceName:","链接成功");
        //发送连接成功通知
        mBaseActivity.dismiss();
        ConstValues_Ly.isA1 = true;
        PopupWindowLanYan.BleSbName = historyEquipmentData.getName();
        PopupWindowLanYan.BleName = historyEquipmentData.getName();
        ConstValues_Ly.DEVICE_IMG = historyEquipmentData.getImg();
        ConstValues_Ly.DEVICE_Model_ID_URL = historyEquipmentData.getDeviceModelId();
        ConstValues_Ly.DEVICE_TYPE_ID_URL = historyEquipmentData.getDeviceTypeId();
        PopupWindowLanYan.postDeviceProtocolCheck();
        PopupWindowLanYan.mManager.stopScan();
        PopupWindowLanYan.mManager.stop();
        Message message = new Message();
        message.what = 99;
        message.obj = "连接成功";
        linkHandler.sendMessage(message);
    }
    private static Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99:
                    //连接
                    Ble4_0Util.OpenA2dp();
                    if (message.obj.toString().equals("连接成功")) {
                        TimeThreadUtils.sendDataA0();
                        if (mBaseActivityC instanceof HistoryEquipmentActivity) {
//                            mBaseActivityC.finish();
                            mBaseActivityC = null;
                        }
                        if (StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
                            if (mGoLianJieInterfaceC != null) {
                                mGoLianJieInterfaceC.bntClickListener(PopupWindowLanYan.BleName, ConstValues_Ly.DEVICE_IMG);
                            }
                        }
                    }
                    break;
                case 101:
//                    dialogInterface.btnConfirm(message.obj.toString());
                    byte[] resultData = (byte[]) message.obj;
                    if (resultData.length > 4) {
                        PopupWindowLanYan.setData(resultData);
                    } else {
                        Log.w("---》》》", "错误：" + Arrays.toString(resultData));
                    }
                    break;
            }
            return false;
        }
    });

    @OnClick({R.id.ll_back, R.id.tv_righttext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
                if (StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
                    ToastUtils.showShort("请先断开当前连接");
                    return;
                }
                FacilityAddSbActivity.intentActivity(this);
                break;
        }
    }
}
