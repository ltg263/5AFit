package com.jxkj.fit_5a.view.activity.mine;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.entity.MedalListData;
import com.jxkj.fit_5a.view.adapter.MineWzqAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineWzqActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineWzqAdapter mMineWzqAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_wzq;
    }

    @Override
    protected void initViews() {

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mMineWzqAdapter = new MineWzqAdapter(null);
        mRvList.setAdapter(mMineWzqAdapter);
        mIvBack.setOnClickListener(v -> finish());
        getUserPrize();
    }
    private void getUserPrize() {
        RetrofitUtil.getInstance().apiService()
                .getUserMedalList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<MedalListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<MedalListData> result) {
                        if(isDataInfoSucceed(result)){
                            mMineWzqAdapter.setNewData(result.getData());
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
