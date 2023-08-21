package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.entity.WalletDetailsBean;
import com.jxkj.fit_5a.view.activity.home.TaskSignActivity;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.adapter.MineRwzxAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineRwzxActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.tv_jifen_num)
    TextView mTvJifenNum;
    private MineRwzxAdapter mMineRwzxAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_rwzx;
    }

    @Override
    protected void initViews() {
        initRv();
//        getWalletDetails();
        mTvJifenNum.setText(SharedUtils.singleton().get(ConstValues.MY_INTEGRAL,""));
        addUserSignLog();
        getUserTaskList();
    }
    private void initRv() {
        mMineRwzxAdapter = new MineRwzxAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineRwzxAdapter);

        mMineRwzxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainActivity.isRenWu = true;
                IntentUtils.getInstence().intent(MineRwzxActivity.this, MainActivity.class);
            }
        });
    }

    @OnClick({R.id.iv_back,R.id.ll_go_1,R.id.tv_right_text,R.id.ll_go_2,R.id.ll_go_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_go_1:
                startActivity(new Intent(this, TaskSignActivity.class));
                break;
            case R.id.tv_right_text:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_JDGZ_URL,"积分规则");
                break;
            case R.id.ll_go_2:

                break;
            case R.id.ll_go_3:

                break;
        }
    }

    private void getUserTaskList() {
        RetrofitUtil.getInstance().apiService()
                .getUserTaskList(null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TaskListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TaskListBase> result) {
                        if(isDataInfoSucceed(result)){
                            mMineRwzxAdapter.setNewData(result.getData().getList());
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
    private void getWalletDetails() {
        RetrofitUtil.getInstance().apiService()
                .getWalletDetails(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<WalletDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<WalletDetailsBean> result) {
                        if(isDataInfoSucceed(result)){
                            mTvJifenNum.setText(result.getData().getBalance());
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
    private void addUserSignLog() {
        RetrofitUtil.getInstance().apiService()
                .addUserSignLog()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<Boolean> result) {
                        if(isDataInfoSucceed(result)){
                            tv_state.setText("去签到");
                            if(result.getData()){
                                tv_state.setText("已签到");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ToastUtils.showShort("系统异常"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
