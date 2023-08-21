package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.RechargeCreateBean;
import com.jxkj.fit_5a.view.adapter.MineJDczSelectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineJinDouCzActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_je)
    TextView tv_je;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_wx)
    ImageView mIvWx;
    @BindView(R.id.iv_zfb)
    ImageView mIvZfb;
    int payType = 1;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_jindou_cz;
    }


    @Override
    protected void initViews() {
        mTvTitle.setText("金豆充值");
//        mTvRighttext.setText("账单");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRv();
    }
    @OnClick({R.id.ll_back,R.id.tv_righttext, R.id.iv_wx, R.id.iv_zfb, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:

                break;
            case R.id.iv_wx:
                if(payType==2){
                    mIvWx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                    mIvZfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                    payType = 1;
                }
                break;
            case R.id.iv_zfb:
                if(payType==1){
                    mIvWx.setImageDrawable(getResources().getDrawable(R.drawable.wxz_1));
                    mIvZfb.setImageDrawable(getResources().getDrawable(R.drawable.wxz_));
                    payType = 2;
                }
                break;
            case R.id.tv_pay:
                if(payType!=1){
                    ToastUtils.showShort("目前只支持微信支付");
                    return;
                }
                postCreateRecharge();
                break;
        }
    }

    private void postCreateRecharge() {
        RetrofitUtil.getInstance().apiService()
                .postCreateRecharge(tv_je.getText().toString(),null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RechargeCreateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RechargeCreateBean> result) {
                        if(isDataInfoSucceed(result)){
                            orderRechargePay(result.getData());
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

    private void orderRechargePay(RechargeCreateBean mRechargeCreateBean) {
        PostUser.RechargeOrder mRechargeOrder = new PostUser.RechargeOrder();
        mRechargeOrder.setOrderId(mRechargeCreateBean.getOrderNo());
        //支付类型1微信2支付宝3（保留）4余额
        mRechargeOrder.setPayType(payType+"");
        mRechargeOrder.setSubPayType("1");
        RetrofitUtil.getInstance().apiService()
                .orderRechargePay(mRechargeOrder)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){

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

    private void initRv() {
        mTvBalance.setText(SharedUtils.singleton().get(ConstValues.MY_BALANCE,""));

        List<String> list = new ArrayList<>();
        list.add("10");
        list.add("30");
        list.add("50");
        list.add("80");
        list.add("100");
        list.add("200");
        MineJDczSelectAdapter mMineJDczSelectAdapter = new MineJDczSelectAdapter(list);
        mRvList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineJDczSelectAdapter);

        mMineJDczSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tv_je.setText(list.get(position));
                mMineJDczSelectAdapter.setCurrPos(position);
                mMineJDczSelectAdapter.notifyDataSetChanged();
            }
        });
    }

}
