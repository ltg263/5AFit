package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.BluetoothChannelData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.FacilityAddSbAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FacilityAddSbActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.rv_all_list)
    RecyclerView mRvAllList;
    private FacilityAddSbAdapter mFacilityAddSbAdapter;
    String type = "";
    @Override
    protected int getContentView() {
        return R.layout.activity_facility_add_sb;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("新增设备");
        type = getIntent().getStringExtra("type");
        if(type==null){
            type = "";
        }
        if(type.equals("00")){
            mTvTitle.setText("心率");
            mTv.setText("心率设备分类");
        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        queryDeviceTypeLists();
    }

    private void queryDeviceTypeLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceTypeLists(type.equals("00")?2:1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceTypeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceTypeData> result) {
                        if(isDataInfoSucceed(result)){
                            initRvUi(result.getData().getList());
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
    private void initRvUi(List<DeviceTypeData.ListBean> list) {

        mFacilityAddSbAdapter = new FacilityAddSbAdapter(list);

        mRvAllList.setLayoutManager(new LinearLayoutManager(this));
        mRvAllList.setHasFixedSize(true);
        mRvAllList.setAdapter(mFacilityAddSbAdapter);

        mFacilityAddSbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(type.equals("00")){
                    return;
                }

                list.get(position).getName();
                list.get(position).getId();
                Bundle mBundle = new Bundle();
                mBundle.putString("name",list.get(position).getName());
                mBundle.putString("id",list.get(position).getId()+"");

//                PopupWindowLanYan.BleName = list.get(position).getName();
                ConstValues_Ly.DEVICE_IMG = list.get(position).getImg();
//                IntentUtils.getInstence().intent(FacilityAddSbActivity.this, FacilityAddPpActivity.class,"bundle",mBundle);
                ConstValues_Ly.DEVICE_TYPE_ID_URL = list.get(position).getId()+"";
                getBluetoothChannel(FacilityAddSbActivity.this,list.get(position).getName(),mTv);
            }
        });
    }
    public static void getBluetoothChannel(Context mContext,String sbName ,TextView mTv) {
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
                        if(result.getCode()==0){
                            if(result.getData()!=null && result.getData().size()>0){
                                FacilityAddPpActivity.initPopupWindw((BaseActivity) mContext,result.getData(),sbName,mTv);
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

    @OnClick({R.id.ll_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_ok:
                break;
        }
    }

    public static void intentActivity(Context mContext){
        mContext.startActivity(new Intent(mContext, FacilityAddSbActivity.class));
    }
}
