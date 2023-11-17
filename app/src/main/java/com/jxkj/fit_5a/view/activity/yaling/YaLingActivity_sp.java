package com.jxkj.fit_5a.view.activity.yaling;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.view.adapter.HomeYaLingSpAdapter;
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

public class YaLingActivity_sp extends BaseActivity {
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
    private HomeYaLingSpAdapter mHomeYaLingSpAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("哑铃视频列表");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mHomeYaLingSpAdapter = new HomeYaLingSpAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeYaLingSpAdapter);

        mHomeYaLingSpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("coverImageUrl", mHomeYaLingSpAdapter.getData().get(position).getCoverImageUrl() + "");
                intent.putExtra("videoUrl", mHomeYaLingSpAdapter.getData().get(position).getVideoUrl() + "");
                intent.putExtra("title", mHomeYaLingSpAdapter.getData().get(position).getTitle() + "");
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getFollowFansList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getFollowFansList();
            }
        });
        getFollowFansList();
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    int page = 1;
    private void getFollowFansList(){
        RetrofitUtil.getInstance().apiService()
                .getTeachingMomentQuery(null,-1,10,page,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<TeachingMomentBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TeachingMomentBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            List<TeachingMomentBean> data = result.getData();
                            if(page==1){
                                mHomeYaLingSpAdapter.setNewData(data);
                            }else{
                                mHomeYaLingSpAdapter.addData(data);
                            }
                            if(mHomeYaLingSpAdapter.getData().size()>0){
                                refreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                            }else{
                                refreshLayout.setVisibility(View.GONE);
                                mLvNot.setVisibility(View.VISIBLE);
                            }
                            if(data.size() < ConstValues.PAGE_SIZE){
                                refreshLayout.finishLoadMoreWithNoMoreData();
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
}
