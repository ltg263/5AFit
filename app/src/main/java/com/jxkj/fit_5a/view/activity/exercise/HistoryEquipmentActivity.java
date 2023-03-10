package com.jxkj.fit_5a.view.activity.exercise;

import android.app.Service;
import android.bluetooth.BluetoothGatt;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.OnRecyclerItemClickListener;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.SwipeRecyclerView;
import com.jxkj.fit_5a.lanya.Ble4_0Util;
import com.jxkj.fit_5a.lanya.BleUtil;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.adapter.HistoryEquipmentAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        mTvTitle.setText("????????????");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
        mTvRighttext.setText("??????");
        
//        SharedHistoryEquipment.singleton().putSharedHistoryEquipment(lists);

        lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
        mHistoryEquipmentAdapter = new HistoryEquipmentAdapter(lists);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHistoryEquipmentAdapter);
        mRvList.setRightClickListener(new SwipeRecyclerView.OnRightClickListener() {
            @Override
            public void onRightClick(int position, String id) {
                if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
                    ToastUtils.showShort("????????????????????????");
                    return;
                }
                DialogUtils.showDialogHint(HistoryEquipmentActivity.this, "?????????????????????????????????", false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
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
                //??????????????????????????????????????????????????????????????????
                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                    mItemTouchHelper.startDrag(vh);

                    //????????????????????????
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//??????70??????
                    vib.vibrate(70);

                }
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * ???????????????????????? ?????????????????????????????? ????????????????????????RecyclerView????????????UP???DOWN?????????????????????RecyclerView??????????????????LEFT???RIGHT
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
                //??????????????????viewHolder???Position
                int fromPosition = viewHolder.getAdapterPosition();
                //????????????????????????item???viewHolder
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
             * ??????????????????
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * ????????????Item?????????????????????
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
             * ???????????????????????????
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
                switch (view.getId()){
                    case R.id.tv_ygz:
                        if(mHistoryEquipmentAdapter.getData().get(position).getState().equals("1")){
                            if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
                                ToastUtils.showShort("????????????????????????");
                                return;
                            }
                            goLianJie(mHistoryEquipmentAdapter.getData().get(position));

                        }else{
                            DialogUtils.showDialogHint(HistoryEquipmentActivity.this, "????????????????????????????????????", false, new DialogUtils.ErrorDialogInterface() {
                                @Override
                                public void btnConfirm() {
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

    private void goLianJie(HistoryEquipmentData historyEquipmentData) {
        Log.w("historyEquipmentData","historyEquipmentData:"+historyEquipmentData.toString());
        ConstValues_Ly.BRAND_ID = historyEquipmentData.getBrandId();
        PopupWindowLanYan.ble4Util = new Ble4_0Util(this);
        PopupWindowLanYan.ble4Util.init();
        String[] uuidData = new String[3];
        uuidData[0] = historyEquipmentData.getServiceUUid();
        uuidData[1] = historyEquipmentData.getReadUUID();
        uuidData[2] = historyEquipmentData.getWriteUUID();
        PopupWindowLanYan.ble4Util.setUuidStr(uuidData);
        PopupWindowLanYan.ble4Util.stopScan();
        PopupWindowLanYan.ble4Util.connect(historyEquipmentData.getLyAddress(), new BleUtil.CallBack() {
            @Override
            public void StateChange(int state, int newState) {
                String value = null;
                PopupWindowLanYan.BleName = "";
                if (newState == BluetoothGatt.STATE_CONNECTED){
                    dismiss();
                    PopupWindowLanYan.BleName = historyEquipmentData.getName();
                    ConstValues_Ly.DEVICE_TYPE_ID_URL = historyEquipmentData.getDeviceTypeId();
                    value = "????????????";
                } else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                    dismiss();
                    value = "????????????";
                } else if(newState == BluetoothGatt.STATE_CONNECTING){
                    show("???????????????...");
                    value = "???????????????";
                } else if(newState == BluetoothGatt.STATE_DISCONNECTING){
                    value = "???????????????";
                }

                if (linkHandler != null && value != null){
                    //????????????????????????
                    Message message = new Message();
                    message.what = 99;
                    message.obj = value;
                    linkHandler.sendMessage(message);
                }
                ToastUtils.showShort(value);
            }

            @Override
            public void ReadValue(byte[] value) {
                dismiss();
                if (linkHandler != null){
                    Message message = new Message();
                    message.what = 101;
                    message.obj = value;
                    linkHandler.sendMessage(message);
                }

            }
        });
    }

    private Handler linkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 99:
                    //??????
                    Ble4_0Util.OpenA2dp();
                    if(message.obj.toString().equals("????????????")){
                        TimeThreadUtils.sendDataA2();
                        finish();
                    }
                    break;
                case 101:
//                    dialogInterface.btnConfirm(message.obj.toString());
                    byte[] resultData = (byte[]) message.obj;
                    if(resultData.length>4){
                        PopupWindowLanYan.setData(resultData);
                    }else{
                        Log.w("---?????????","?????????"+ Arrays.toString(resultData));
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
                if(StringUtil.isNotBlank(PopupWindowLanYan.BleName)){
                    ToastUtils.showShort("????????????????????????");
                    return;
                }
                FacilityAddSbActivity.intentActivity(this);
                break;
        }
    }
}
