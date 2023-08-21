package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HelpListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.view.adapter.MineIssueAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineIssueActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private MineIssueAdapter mMineIssueAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_issue;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("常见问题");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        getInterestList();

        mMineIssueAdapter = new MineIssueAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineIssueAdapter);

        mMineIssueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(MineIssueActivity.this, MineTopicActivity.class));
            }
        });
    }


    @OnClick({R.id.ll_back, R.id.ll_home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_home_search:
                break;
        }
    }


    private void getInterestList() {
        RetrofitUtil.getInstance().apiService()
                .getHelpList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HelpListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HelpListData> result) {
                        if(isDataInfoSucceed(result)){
                            mMineIssueAdapter.setNewData(result.getData().getList());
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
