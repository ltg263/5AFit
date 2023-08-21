package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity_Gf;
import com.jxkj.fit_5a.view.adapter.GfListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GfClassificationFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private Bundle bundle;
    int classificationId;
    int page = 1;
    private GfListAdapter mGfListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_gf_classification_list;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        bundle = getArguments();
        initList();
        if (bundle != null) {
            classificationId = bundle.getInt("classificationId");
            getQueryPopularMoment();
        }

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getQueryPopularMoment();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getQueryPopularMoment();
            }
        });
    }

    private void initList() {
        mGfListAdapter = new GfListAdapter(null);
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setHasFixedSize(true);
        rv_list.setAdapter(mGfListAdapter);

        mGfListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity_Gf.startActivityIntent(getActivity(),
                        mGfListAdapter.getData().get(position).getMomentId(),
                        mGfListAdapter.getData().get(position).getPublisherId(),
                        "详情");
            }
        });;
    }
    private void getQueryPopularMoment() {
        RetrofitUtil.getInstance().apiService()
                .getQueryPopularMoment(classificationId,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<QueryPopularMomentBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<QueryPopularMomentBean>> result) {
                        if (isDataInfoSucceed(result) && result.getData()!=null) {
                            List<QueryPopularMomentBean> data = result.getData();
                            if(page==1){
                                mGfListAdapter.setNewData(data);
                            }else{
                                mGfListAdapter.addData(data);
                            }

                            if(mGfListAdapter.getData().size()>0){
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                            }else{
                                mRefreshLayout.setVisibility(View.GONE);
                                tv_not.setVisibility(View.VISIBLE);
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
    @Override
    public void initImmersionBar() {

    }
}
