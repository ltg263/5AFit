package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.view.adapter.FacilityAddSbAdapter;
import com.jxkj.fit_5a.view.adapter.HomeJiaoXueSpAdapter;
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

public class JiaoXueSpSearchActivity extends BaseActivity {
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.rv_sb_list)
    RecyclerView mRvSbList;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private HomeJiaoXueSpAdapter mHomeJiaoXueSpAdapter;
    private FacilityAddSbAdapter mFacilityAddSbAdapter;
    int page = 1;
    String keyword = null;
    int deviceTypeId;
    @Override
    protected int getContentView() {
        return R.layout.activity_jiao_xue_sp_search;
    }

    @OnClick({R.id.ll_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }
    @Override
    protected void initViews() {
        keyword = getIntent().getStringExtra("search");
        tvTopTitle.setText(keyword);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getTeachingMomentQuery();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getTeachingMomentQuery();
            }
        });


        mFacilityAddSbAdapter = new FacilityAddSbAdapter(null);
        mFacilityAddSbAdapter.setFang(true);
        mRvSbList.setAdapter(mFacilityAddSbAdapter);
        mFacilityAddSbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                deviceTypeId = mFacilityAddSbAdapter.getData().get(position).getId();
                getTeachingMomentQuery();
            }
        });

        mHomeJiaoXueSpAdapter = new HomeJiaoXueSpAdapter(null);
        mRvContent.setAdapter(mHomeJiaoXueSpAdapter);
        mHomeJiaoXueSpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(JiaoXueSpSearchActivity.this,JiaoXueSpXpActivity.class,
                        "momentId",mHomeJiaoXueSpAdapter.getData().get(position).getMomentId()+"",
                        "publisherId",mHomeJiaoXueSpAdapter.getData().get(position).getPublisherId()+"");
            }
        });
        getTeachingMomentQuery();
    }
    private void getTeachingMomentQuery() {
        RetrofitUtil.getInstance().apiService()
                .getTeachingMomentQuery(keyword,-1,null,page,ConstValues.PAGE_SIZE)
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
                                mHomeJiaoXueSpAdapter.setNewData(data);
                            }else{
                                mHomeJiaoXueSpAdapter.addData(data);
                            }
                            mRvContent.setVisibility(View.GONE);
                            tv_not.setVisibility(View.VISIBLE);
                            if(mHomeJiaoXueSpAdapter.getData().size()>0){
                                mRvContent.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                            }
                            if(data.size() < ConstValues.PAGE_SIZE){
                                mRefreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                });
    }
}
