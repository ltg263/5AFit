package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceData;
import com.jxkj.fit_5a.base.DeviceDrandData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeThreadUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BluetoothChannelData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.activity.yaling.YaLingActivity_1;
import com.jxkj.fit_5a.view.adapter.FacilityAddAdapter;
import com.jxkj.fit_5a.view.adapter.FacilitySbAddAdapter;
import com.jxkj.fit_5a.view.fragment.HomeTwoFragment;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class FacilityAddPpActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
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
    String sbName;//设备名称
    String sbName_pp;//设备品牌
    String sbName_xh;//设备型号
    private List<BluetoothChannelData> UUidData;

    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_pp;
    }

    @Override
    protected void initViews() {
        PopupWindowLanYan.mManager = null;
        Bundle bundle = getIntent().getBundleExtra("bundle");
        ConstValues_Ly.DEVICE_TYPE_ID_URL =  bundle.getString("id");//设备类型id
        sbName = bundle.getString("name");
        mTvTitle.setText(sbName);
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        getBluetoothChannel();
        queryDeviceBrandLists();
        initRvUiXh();
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false));
//        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                queryDeviceModelLists(ConstValues_Ly.BRAND_ID);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getBluetoothChannel();
                queryDeviceBrandLists();
            }
        });
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

    public static void initPopupWindw(BaseActivity mBaseActivity,List<BluetoothChannelData> UUidData,String sbName,View view) {
        PopupWindowLanYan.outType_Activity = 1;
        PopupWindowLanYan window;
//        if(window==null){
            window = new PopupWindowLanYan(mBaseActivity,UUidData,sbName, new PopupWindowLanYan.GiveDialogInterface() {
                @Override
                public void btnConfirm(String str) {
                    Log.w("123456","str"+str);
                    if(str.equals("连接设备中")){
                        PopupWindowLanYan.BleName = sbName;
                        mBaseActivity.show("蓝牙连接中...");
                        return;
                    }
                    if(str.equals("连接成功")){
                        TimeThreadUtils.sendDataA0();
                        return;
                    }
                    mBaseActivity.dismiss();
                    if(str.equals("出现UI")){
                        showDialogUi(mBaseActivity,str,sbName);
                    }else {
                        ToastUtils.showShort(str);
                    }
                }
            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        }
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
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
    int page = 1;
    private void queryDeviceModelLists(String id) {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceModelLists(id,ConstValues_Ly.DEVICE_TYPE_ID_URL,page, ConstValues.PAGE_SIZE)
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
                                if(page ==1){
                                    mFacilitySbAddAdapter.setNewData(result.getData().getList());
                                }else {
                                    mFacilitySbAddAdapter.addData(result.getData().getList());
                                }
                                int totalPage = StringUtil.getTotalPage(result.getData().getTotalCount(), ConstValues.PAGE_SIZE);
                                if(totalPage <= page){
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
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
                DeviceData.ListBean data = mFacilitySbAddAdapter.getData().get(position);
                queryInstructions_for_use_url(data.getId(),data.getName());
            }
        });
        mFacilitySbAddAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
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

    private void queryInstructions_for_use_url(int id,String name) {
        RetrofitUtil.getInstance().apiService()
                .queryInstructions_for_use_url(id+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> result) {
                        if(isDataInfoSucceed(result) && result.getData()!=null) {
                            WebViewActivity.startActivityIntent(FacilityAddPpActivity.this,
                                    result.getData(),name);
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

    private void initRvUi() {
        if(list==null || list.size()==0){
            return;
        }
        list.get(0).setSelect(true);
        queryDeviceModelLists(list.get(0).getId()+"");
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
                queryDeviceModelLists(list.get(position).getId()+"");
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
                if("收起全部".equals(tv_show.getText().toString())){
                    tv_show.setText("展示全部");
                    mFacilityAddAdapter.setNewData(list.subList(0,length));
                }else{
                    mFacilityAddAdapter.setNewData(list);
                    tv_show.setText("收起全部");
                }
                break;
            case R.id.ll_connect:
                if(StringUtil.isBlank(ConstValues_Ly.BRAND_ID)){
                    ToastUtils.showShort("请选择设备品牌");
                    return;
                }
                if(UUidData==null){
                    ToastUtils.showShort("获取蓝牙通道失败");
                    return;
                }
                initPopupWindw(this,UUidData,sbName,mTv);
                break;
        }
    }

    private static void showDialogUi(BaseActivity mBaseActivity,String str,String sbName) {
//        mIv.setVisibility(View.VISIBLE);
//        mTv.setVisibility(View.VISIBLE);
//        mIvD.setVisibility(View.GONE);
//        ConstValues_Ly.SB_NAME = sbName+"-"+sbName_pp+"-"+sbName_xh;
//            TimeThreadUtils.sendDataA0();
        if(mBaseActivity instanceof MainActivity){
            if(ConstValues_Ly.isYalingsheben(PopupWindowLanYan.BleName)){
                YaLingActivity_1.intentActivity(mBaseActivity);
            }else{
                HomeTwoFragment mHomeTwoFragment = (HomeTwoFragment) ((MainActivity) mBaseActivity).getSupportFragmentManager().findFragmentByTag("B");
                if(mHomeTwoFragment!=null){
                    mHomeTwoFragment.onResume();
                }
            }
            return;
        }
        DialogUtils.showUnificationDialog(mBaseActivity, "设备连接","您的设备连接已成功~", "去运动",false,
                new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
//                            PopupWindowLanYan.BleName = sbName+"-"+PopupWindowLanYan.BleSbName;
//                            PopupWindowLanYan.BleName = sbName;
                        if(ConstValues_Ly.isYalingsheben(PopupWindowLanYan.BleName)){
                            mBaseActivity.finish();
                            YaLingActivity_1.intentActivity(mBaseActivity);
                        }else{
                            mBaseActivity.finish();
                            mBaseActivity.startActivity(new Intent(mBaseActivity, MainActivity.class));
                        }
                    }
                });
//        DialogUtils.showDialogLyState(FacilityAddPpActivity.this, sbName, str, new DialogUtils.DialogLyInterface() {
//            @Override
//            public void btnConfirm() {
//                if(str.equals("连接成功")){
//                    PopupWindowLanYan.BleName = sbName+"-"+PopupWindowLanYan.BleSbName;
//                    dismiss();
//                    startActivity(new Intent(FacilityAddPpActivity.this, MainActivity.class));
//                }
//            }
//        });
    }
}
