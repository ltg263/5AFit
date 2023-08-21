package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.entity.AnnouncementList;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.adapter.MineMessageAnnouncementAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineMessageAnnouncementActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MineMessageAnnouncementAdapter mMineMessageAnnouncementAdapter;


    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("公 告");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mMineMessageAnnouncementAdapter = new MineMessageAnnouncementAdapter(null);
        mRvList.setAdapter(mMineMessageAnnouncementAdapter);
        mMineMessageAnnouncementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getAnnouncementUrl(mMineMessageAnnouncementAdapter.getData().get(position).getId(),mMineMessageAnnouncementAdapter.getData().get(position).getTitle());
            }
        });
        initRv();
    }

    private void initRv() {
        RetrofitUtil.getInstance().apiService()
                .getAnnouncementList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AnnouncementList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AnnouncementList> result) {
                        if (isDataInfoSucceed(result)) {
                            mMineMessageAnnouncementAdapter.setNewData(result.getData().getList());
                            if(mMineMessageAnnouncementAdapter.getData().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
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
    private void getAnnouncementUrl(String id, String title) {
        RetrofitUtil.getInstance().apiService()
                .getAnnouncementUrl(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> result) {
                        if (isDataInfoSucceed(result)) {
                            WebViewActivity.startActivityIntent(MineMessageAnnouncementActivity.this,
                                    result.getData(),title);
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
