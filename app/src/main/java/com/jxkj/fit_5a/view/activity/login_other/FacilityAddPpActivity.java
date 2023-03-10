package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BluetoothChannelData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.FacilityAddAdapter;
import com.jxkj.fit_5a.view.adapter.FacilitySbAddAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class FacilityAddPpActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    @BindView(R.id.rv_sbxh_list)
    RecyclerView mRvSbxhList;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_show)
    TextView tv_show;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_d)
    ImageView mIvD;
    private FacilityAddAdapter mFacilityAddAdapter;
    private FacilitySbAddAdapter mFacilitySbAddAdapter;
    String sbName;//????????????
    String sbName_pp;//????????????
    String sbName_xh;//????????????
    private List<BluetoothChannelData> UUidData;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_pp;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        ConstValues_Ly.DEVICE_TYPE_ID_URL =  bundle.getString("id");//????????????id
        sbName = bundle.getString("name");
        PopupWindowLanYan.BleName = sbName;
        mTvTitle.setText(sbName);
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        getBluetoothChannel();
        queryDeviceBrandLists();
        initRvUiXh();
    }

    private void getBluetoothChannel() {
        RetrofitUtil.getInstance().apiService()
                .getBluetoothChannel(ConstValues_Ly.DEVICE_TYPE_ID_URL ,"iconsole")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<BluetoothChannelData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<BluetoothChannelData> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()!=null && result.getData().size()>0){
                                UUidData = result.getData();
                            }
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

    PopupWindowLanYan window;
    private void initPopupWindw() {

        if(window==null){
            window = new PopupWindowLanYan(this,UUidData, new PopupWindowLanYan.GiveDialogInterface() {
                @Override
                public void btnConfirm(String str) {
                    if(str.equals("???????????????")){
                        show("???????????????...");
                        return;
                    }
                    dismiss();
                    showDialogUi(str);
                }
            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        window.showAtLocation(mTv, Gravity.BOTTOM, 0, 0); // ??????layout???PopupWindow??????????????????
    }

    List<DeviceDrandData.ListBean> list;
    private void queryDeviceBrandLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceBrandLists("1","1000")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceDrandData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceDrandData> result) {
                        if(isDataInfoSucceed(result)){
                            list = result.getData().getList();
                            initRvUi();
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
    private void queryDeviceModelLists(int id) {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceModelLists(String.valueOf(id),ConstValues_Ly.DEVICE_TYPE_ID_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceData> result) {
                        if(isDataInfoSucceed(result) && result.getData()!=null){
                            List<DeviceData.ListBean> listData = result.getData().getList();
                            if(listData!=null && listData.size()>0){
                                listData.get(0).setSelect(true);
                                sbName_xh = listData.get(0).getName();
                                ConstValues_Ly.DEVICE_Model_ID_URL = listData.get(0).getId()+"";
                                ConstValues_Ly.BRAND_ID = list.get(0).getId()+"";
                                mFacilitySbAddAdapter.setNewData(result.getData().getList());
                            }
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


    private void initRvUiXh() {
        mFacilitySbAddAdapter = new FacilitySbAddAdapter(null);
        mRvSbxhList.setLayoutManager(new LinearLayoutManager(this));
        mRvSbxhList.setHasFixedSize(true);
        mRvSbxhList.setAdapter(mFacilitySbAddAdapter);

        mFacilitySbAddAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<DeviceData.ListBean> data = mFacilitySbAddAdapter.getData();
                for(int i = 0; i< data.size(); i++){
                    data.get(i).setSelect(false);
                }
                data.get(position).setSelect(true);
                ConstValues_Ly.DEVICE_Model_ID_URL = data.get(position).getId()+"";
                sbName_xh = data.get(position).getName();
                mFacilitySbAddAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRvUi() {
        if(list==null || list.size()==0){
            return;
        }
        list.get(0).setSelect(true);
        queryDeviceModelLists(list.get(0).getId());
        ConstValues_Ly.SB_NAME = list.get(0).getName();
        tv_name.setText("("+list.get(0).getName()+")");
        int length = list.size()<9?list.size():9;
        mFacilityAddAdapter = new FacilityAddAdapter(list.subList(0,length));
        mRvAllList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mFacilityAddAdapter);

        mFacilityAddAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for(int i= 0;i<list.size();i++){
                    list.get(i).setSelect(false);
                }
                list.get(position).setSelect(true);
                mFacilityAddAdapter.notifyDataSetChanged();
                sbName_pp = list.get(position).getName();
                tv_name.setText("("+sbName_pp+")");
                ConstValues_Ly.BRAND_ID = list.get(position).getId()+"";
                queryDeviceModelLists(list.get(position).getId());
            }
        });
    }
    @OnClick({R.id.ll_back, R.id.ll_connect,R.id.tv_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_show:
                int length = list.size()<9?list.size():9;
                if("????????????".equals(tv_show.getText().toString())){
                    tv_show.setText("????????????");
                    mFacilityAddAdapter.setNewData(list.subList(0,length));
                }else{
                    mFacilityAddAdapter.setNewData(list);
                    tv_show.setText("????????????");
                }
                break;
            case R.id.ll_connect:
                if(StringUtil.isBlank(ConstValues_Ly.BRAND_ID)){
                    ToastUtils.showShort("?????????????????????");
                    return;
                }
                if(UUidData==null){
                    ToastUtils.showShort("????????????????????????");
                    return;
                }
                initPopupWindw();
                break;
        }
    }

    private void showDialogUi(String str) {
        mIv.setVisibility(View.VISIBLE);
        mTv.setVisibility(View.VISIBLE);
        mIvD.setVisibility(View.GONE);
//        ConstValues_Ly.SB_NAME = sbName+"-"+sbName_pp+"-"+sbName_xh;
        if(str.equals("????????????")){
            TimeThreadUtils.sendDataA2();
        }
        DialogUtils.showDialogLyState(FacilityAddPpActivity.this, PopupWindowLanYan.BleName, str, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                if(str.equals("????????????")){
                    dismiss();
                    startActivity(new Intent(FacilityAddPpActivity.this, MainActivity.class));
                }
            }
        });
    }
}
