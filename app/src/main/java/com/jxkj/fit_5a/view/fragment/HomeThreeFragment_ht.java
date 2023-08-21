
package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.TopicTabsActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeTop_HtAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeThreeFragment_ht extends BaseFragment {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;
    private HomeThreeTop_HtAdapter mHomeThreeTopAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;
    int page = SharedUtilsNot.singletonNotClear().get(ConstValues.USER_THREE_HT_PAGE+SharedUtils.getUserId(),1);

    public void setDoubleClicked(){
        mRvList.scrollToPosition(0);
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
                mAppBarLayout.setExpanded(true, true);
            }
            refreshLayout.autoRefresh();
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three_ht;
    }

    @Override
    protected void initViews() {
        initRvUi();
        getMomentQueryPopular();
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getMomentQueryPopular();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page++;
                getMomentQueryPopular();
            }
        });
    }

    private void initRvUi() {
        mHomeThreeTopAdapter = new HomeThreeTop_HtAdapter(null);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(llm);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeThreeTopAdapter);
        mHomeThreeTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeTopAdapter.getData().get(position)==null){
                    startActivity(new Intent(getActivity(), TopicTabsActivity.class));
                }else{
                    MineTopicActivity.startActivity(getActivity(),mHomeThreeTopAdapter.getData().get(position).getName());
                }
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeDynamicAdapter);
        hot_tpoic();
    }
    @Override
    public void initImmersionBar() {

    }

    private void hot_tpoic(){
        RetrofitUtil.getInstance().apiService()
                .hot_tpoic(1, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<HotTopicBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<HotTopicBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            List<HotTopicBean> data = result.getData();
                            data.add(0,null);
                            mHomeThreeTopAdapter.setNewData(data);
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

    private void getMomentQueryPopular(){
        RetrofitUtil.getInstance().apiService()
                .getMomentQueryPopular(page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            if(refreshLayout.getState() == RefreshState.None || refreshLayout.getState() == RefreshState.Refreshing
                                    || refreshLayout.getState() == RefreshState.RefreshFinish){
                                if(result.getData().size()==0){
                                    if(page!=1){
                                        page = 1;
                                        getMomentQueryPopular();
                                    }
                                }else{
                                    refreshLayout.setNoMoreData(false);
                                    mHomeDynamicAdapter.setNewData(result.getData());
                                }
                            }else{
                                mHomeDynamicAdapter.addData(result.getData());
                            }
                            if(result.getData().size()==0 || result.getData().size()<ConstValues.PAGE_SIZE){
                                refreshLayout.finishLoadMoreWithNoMoreData();

                                SharedUtilsNot.singletonNotClear().put(ConstValues.USER_THREE_HT_PAGE+SharedUtils.getUserId(),1);
                            }else{

                                SharedUtilsNot.singletonNotClear().put(ConstValues.USER_THREE_HT_PAGE+SharedUtils.getUserId(),page+1);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }

}



