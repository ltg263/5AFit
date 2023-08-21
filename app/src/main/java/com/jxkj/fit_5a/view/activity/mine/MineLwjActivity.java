package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.UserWalletData;
import com.jxkj.fit_5a.entity.WalletListBean;
import com.jxkj.fit_5a.view.adapter.MineJinDouAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineLwjActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_lwj)
    TextView tv_lwj;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineJinDouAdapter mMineJinDouAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_lwj;
    }

    @Override
    protected void initViews() {
        tvTitle.setText("礼物金");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mMineJinDouAdapter = new MineJinDouAdapter(null);
        mRvList.setAdapter(mMineJinDouAdapter);
        mIvBack.setOnClickListener(v -> finish());
        getUserWalletAll();
        getWalletList();
    }
    private void getUserWalletAll() {
        RetrofitUtil.getInstance().apiService()
                .getUserWalletAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserWalletData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserWalletData> result) {
                        if (isDataInfoSucceed(result)) {
                            UserWalletData data = result.getData();
                            if(data!=null){
                                tv_lwj.setText(data.getEncourage().getTotalAmount().replace(".00",""));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getWalletList() {
        tv_not.setVisibility(View.VISIBLE);
        mRvList.setVisibility(View.GONE);
        RetrofitUtil.getInstance().apiService()
                .getWalletList(null,null,1,3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<WalletListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<WalletListBean> result) {
                        if(isDataInfoSucceed(result)){
                            mMineJinDouAdapter.setNewData(result.getData().getList());
                            if(mMineJinDouAdapter.getData().size()>0){
                                tv_not.setVisibility(View.GONE);
                                mRvList.setVisibility(View.VISIBLE);
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
}
