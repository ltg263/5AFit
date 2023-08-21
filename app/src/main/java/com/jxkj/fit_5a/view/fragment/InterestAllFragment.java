package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.adapter.InterestListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InterestAllFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int id=0;
    private InterestListAdapter mInterestListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_interest_all;
    }

    private int page=1;
    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id",0);
        }

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getCircleQuery();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getCircleQuery();
            }
        });
        mInterestListAdapter = new InterestListAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mInterestListAdapter);

        mInterestListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(getActivity(), MineCircleActivity.class);
                mIntent.putExtra("id",mInterestListAdapter.getData().get(position).getId());
                startActivity(mIntent);
            }
        });
        getCircleQuery();
    }

    private void getCircleQuery() {
        RetrofitUtil.getInstance().apiService()
                .getCircleQuery(id,page,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleQueryJoinedBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleQueryJoinedBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<CircleQueryJoinedBean.ListBean> data = result.getData().getList();
                            if(page==1){
                                mInterestListAdapter.setNewData(data);
                            }else{
                                mInterestListAdapter.addData(data);
                            }
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();

                            int totalPage = StringUtil.getTotalPage(result.getData().getTotal(), ConstValues.PAGE_SIZE);
                            if(mInterestListAdapter.getData().size()>0){
                                refreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                            }else{
                                refreshLayout.setVisibility(View.GONE);
                                mLvNot.setVisibility(View.VISIBLE);
                            }
                            if(totalPage <= page){
                                refreshLayout.finishLoadMoreWithNoMoreData();
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


    @Override
    public void initImmersionBar() {

    }
}
