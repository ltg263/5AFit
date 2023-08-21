package com.jxkj.fit_5a.view.activity.mine.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.entity.AddressModel;
import com.jxkj.fit_5a.view.adapter.AddressAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class AddressActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_rightimg)
    ImageView ivRightimg;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;
    @BindView(R.id.rl_actionbar)
    RelativeLayout rlActionbar;
    int source = 0;
    int type = 0;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    AddressAdapter mAddressAdapter;
    private List<AddressData> addressDataList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_address;
    }

    @Override
    protected void initViews() {
        source = getIntent().getIntExtra("source", 0);
        type = getIntent().getIntExtra("type", 0);
        initTitle();
        initRv();
    }

    private void initTitle() {
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        tvTitle.setText("收货地址");
    }

    public static void startActivity(Context  mContext, int type) {
        Intent intent = new Intent(mContext, AddressActivity.class);
        intent.putExtra("type",type);//1回调，
        intent.putExtra("source", 1);
        ((Activity)mContext).startActivityForResult(intent, 20);
    }

    private void initRv() {
        rvList.setLayoutManager(new LinearLayoutManager(AddressActivity.this));
        rvList.setHasFixedSize(true);
        mAddressAdapter = new AddressAdapter(addressDataList);
        rvList.setAdapter(mAddressAdapter);
        mAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.w("---,","source:"+source);
                if (source == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("address",addressDataList.get(position));
                    AddressEditActivity.startActivity(view.getContext(),bundle);
                } else {
                    if(type==1){
                        EventBus.getDefault().post(addressDataList.get(position));
                        finish();
                    }
                    if(type==2){
                        //Activity返回时传递数据，也是通过意图对象
                        Intent data = new Intent();
                        //把要传递的数据封装至意图对象中
                        data.putExtra("address", addressDataList.get(position));

                        //当前Activity销毁时，data这个意图就会传递给启动当前Activity的那个Activity
                        setResult(1, data);

                        //销毁当前Activity
                        finish();
                    }
                }
            }
        });
        mAddressAdapter.setOnDeleteClickListener(new AddressAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(int position) {
                getDeleteAddress(addressDataList.get(position).getId()+"");
            }

            @Override
            public void onDefaultClick(int position) {
                getSetDefault(addressDataList.get(position).getId()+"");
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_save:
                AddressEditActivity.startActivity(view.getContext(),null);
                break;
            default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        show(AddressActivity.this,"加载中");
        getUserAddress();
    }

    private void getUserAddress() {
        RetrofitUtil.getInstance().apiService()
                .getUserAddress("1", "100")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AddressModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AddressModel> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()!=null){
                                addressDataList.clear();
                                if(result.getData().getList()!=null){
                                    lv_not.setVisibility(View.GONE);
                                    rvList.setVisibility(View.VISIBLE);
                                    addressDataList.addAll(result.getData().getList());
                                    mAddressAdapter.notifyDataSetChanged();
                                }
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

    /**
     * 设置默认地址
     */
    private void getSetDefault(String id) {
//        show(AddressActivity.this,"加载中");
        RetrofitUtil.getInstance().apiService()
                .getSetDefault(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            getUserAddress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
//                        dismiss();
                    }
                });


    }
    /**
     * 删除地址
     */
    private void getDeleteAddress(String id) {
//        show(AddressActivity.this,"加载中");
        RetrofitUtil.getInstance().apiService()
                .getDeleteAddress(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            getUserAddress();
                        }else{
                            ToastUtils.showShort(result.getMesg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        dismiss();
                    }

                    @Override
                    public void onComplete() {
//                        dismiss();
                    }
                });
//
    }
}
