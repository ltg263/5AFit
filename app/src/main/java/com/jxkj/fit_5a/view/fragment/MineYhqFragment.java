package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
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
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.view.adapter.MineYhqAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineYhqFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_dh)
    LinearLayout ll_dh;

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine_yhq;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        int type = 0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initRv(type);
    }
    int page = 1;

    MineYhqAdapter mBaseQuickAdapter;
    private void initRv(int type) {

        if (type == 0) {
            ll_dh.setVisibility(View.GONE);
            mBaseQuickAdapter = new MineYhqAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else if (type == 1) {
            ll_dh.setVisibility(View.GONE);
            mBaseQuickAdapter = new MineYhqAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            ll_dh.setVisibility(View.GONE);
            mBaseQuickAdapter = new MineYhqAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mBaseQuickAdapter);
        getUserPrize(type);
        mBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getUserPrize(type);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getUserPrize(type);
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
    private void getUserPrize(int type) {
        if(type == 0){
            type = 1;
        }else if(type ==1){
            type = 2;
        }else{
            type =3;
        }
        RetrofitUtil.getInstance().apiService()
                .getUserPrizeList(type,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<PrizeListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<PrizeListData> result) {
                        if(isDataInfoSucceed(result)){
                            List<PrizeListData.ListBean> data = result.getData().getList();
                            if(page ==1){
                                mBaseQuickAdapter.setNewData(data);
                            }else{
                                mBaseQuickAdapter.addData(data);
                            }
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();

                            if(mBaseQuickAdapter.getData().size()>0){
                                refreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                            }else{
                                refreshLayout.setVisibility(View.GONE);
                                mLvNot.setVisibility(View.VISIBLE);
                            }
                            if(data.size()<ConstValues.PAGE_SIZE){
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
