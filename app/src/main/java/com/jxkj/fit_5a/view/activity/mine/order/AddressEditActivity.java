package com.jxkj.fit_5a.view.activity.mine.order;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.AddressPickTask;
import com.jxkj.fit_5a.entity.AddressData;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressEditActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_righttext)
    TextView tvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView ivRightimg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout rlActionbar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_address_dw)
    TextView tvAddressDw;
    @BindView(R.id.tv_address_ktwz)
    TextView tvAddressKtwz;
    @BindView(R.id.et_detailaddress)
    EditText etDetailaddress;
    @BindView(R.id.iv_szmr)
    ImageView iv_szmr;
    AddressData addressData;
    private int placeId = 0;
    private int flag = 1;
    @Override
    protected int getContentView() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void initViews() {
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        if (getIntent().getBundleExtra("address") != null) {
            tvTitle.setText("修改地址");
            flag = 2;
            addressData = (AddressData) getIntent().getBundleExtra("address").getSerializable("address");
            etName.setText(addressData.getAcceptName());
            etPhone.setText("" + addressData.getMobile());
//            tvAddressKtwz.setText(addressData.getPlace());
            tvAddress.setText("" + addressData.getRegions());
            etDetailaddress.setText(addressData.getLocation());
            if(addressData.getIsDefault().equals("1")){
                iv_szmr.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
            }
        } else {
            flag = 1;
            addressData = new AddressData();
            addressData.setIsDefault("0");
            tvTitle.setText("添加新地址");
        }
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
    public static void startActivity(Context mContext, Bundle mBundle) {
        IntentUtils.getInstence().intent(mContext,AddressEditActivity.class,"address",mBundle);
    }
    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {

            @Override
            public void onAddressInitFailed() {
                ToastUtils.showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    tvAddress.setText(province.getAreaName() +","+ city.getAreaName());
                    addressData.setRegions(province.getAreaName() +","+ city.getAreaName());
                    addressData.setProvinceId(province.getAreaId());
                    addressData.setCityId(city.getAreaId());

                } else {
                    tvAddress.setText(province.getAreaName() +","+ city.getAreaName() +","+ county.getAreaName());
                    addressData.setRegions(province.getAreaName() +","+ city.getAreaName() +","+ county.getAreaName());
                    addressData.setProvinceId(province.getAreaId());
                    addressData.setCityId(city.getAreaId());
                    addressData.setDistrictId(county.getAreaId());
                }
            }
        });
        task.execute("北京", "北京", "北京");
    }


    @OnClick({R.id.btn_save, R.id.iv_back, R.id.tv_address,R.id.tv_address_ktwz,R.id.iv_szmr,R.id.tv_address_dw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_address:
                onAddressPicker();
                break;
            case R.id.tv_address_ktwz:
                break;
            case R.id.tv_address_dw:
//                IntentUtils.getInstence().intent(this, AddressByMapActivity.class);
                break;
            case R.id.iv_szmr:
                if(addressData.getIsDefault().equals("1")){
                    addressData.setIsDefault("0");
                    iv_szmr.setImageDrawable(getResources().getDrawable(R.drawable.wxz));
                }else{
                    addressData.setIsDefault("1");
                    iv_szmr.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                }
                break;
            case R.id.btn_save:
                String consigneeName = etName.getText().toString();
                if (consigneeName == null) {
                    ToastUtils.showShort( "请填写收货人姓名");
                    return;
                }
                addressData.setAcceptName(consigneeName);
//                String ktwz = tvAddressKtwz.getText().toString();
//                if (ktwz == null || placeId == 0) {
//                    ToastUtils.showShort(  "请选择开通位置");
//                    return;
//                }
//                addressData.setPlaceId(placeId);
//                addressData.setPlace(ktwz);
                String phone = etPhone.getText().toString();
                if (phone == null) {
                    ToastUtils.showShort(  "请填写收货人手机号");
                    return;
                }
                if (phone.trim().length() < 11) {
                    ToastUtils.showShort(  "请填写11位手机号");
                    return;
                }
                addressData.setMobile(phone);
                if (addressData.getRegions() == null) {
                    ToastUtils.showShort( "请选择城市");
                    return;
                }
                String address = tvAddressDw.getText().toString();
                if (address == null) {
                    ToastUtils.showShort( "请填写详细收货地址");
                    return;
                }
                addressData.setLocation(address+"-"+etDetailaddress.getText().toString());
//                show();
                if(flag==1){
                    getAddAddress(addressData);
                }else {
                    getUpdateAddress(addressData);
                }
                break;
            default:
        }
    }

    /**
     * 添加地址
     * {
     *   "acceptName": "string",
     *   "cityId": 0,
     *   "default": true,
     *   "districtId": 0,
     *   "lat": 0,
     *   "lng": 0,
     *   "location": "string",
     *   "mobile": "string",
     *   "place": "string",
     *   "placeId": 0,
     *   "provinceId": 0,
     *   "regions": "string",
     *   "streetId": 0
     * }
     */
    private void getAddAddress(AddressData addressData) {
        addressData.setId(null);
        Log.w("-->>","addressData："+addressData.toString());
        RetrofitUtil.getInstance().apiService()
                .getAddAddress(addressData).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("添加成功");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

    }
    /**
     * 更新地址
     */
    private void getUpdateAddress(AddressData addressData) {
        RetrofitUtil.getInstance().apiService()
                .getUpdateAddress(addressData).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("修改成功");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

}
