package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.CommunityHomeInfoBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.RecommendUser;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.InterestTabsActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeTopAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeThreeFragment_qz extends BaseFragment {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    private HomeThreeTopAdapter mHomeThreeTopAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;
    String momentLocalMinId = "0";
    int page_top = 1;

    public void setDoubleClicked(){
        mNestedScrollView.smoothScrollTo(0,refreshLayout.getTop());
        refreshLayout.autoRefresh();
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three_qz;
    }

    @Override
    protected void initViews() {
        initRvUi();
        query_by_circle_follower();
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                query_by_circle_follower();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                momentLocalMinId = "0";
                query_by_circle_follower();
            }
        });
    }

    private void initRvUi() {
        mHomeThreeTopAdapter = new HomeThreeTopAdapter(null);
        mRvTopList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeThreeTopAdapter);
        mHomeThreeTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mHomeThreeTopAdapter.getData().get(position)==null){
                    startActivity(new Intent(getActivity(), InterestTabsActivity.class));
                }else{
                    Intent mIntent = new Intent(getActivity(), MineCircleActivity.class);
                    mIntent.putExtra("id",mHomeThreeTopAdapter.getData().get(position).getId());
                    startActivity(mIntent);
                }
            }
        });
        recommend_circle();

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeDynamicAdapter);
    }
    @Override
    public void initImmersionBar() {

    }


    @OnClick({R.id.tv_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                page_top++;
                recommend_circle();
                break;
        }
    }

    private void recommend_circle(){
        RetrofitUtil.getInstance().apiService()
                .recommend_circle(page_top,3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<CircleDetailsBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<CircleDetailsBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            List<CircleDetailsBean> data = result.getData();
                            if(data.size()==0 && page_top!=1){
                                page_top = 1;
                                recommend_circle();
                                return;
                            }
                            data.add(null);
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

    private void query_by_circle_follower(){
        RetrofitUtil.getInstance().apiService()
                .query_by_circle_follower(momentLocalMinId, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<QueryPopularBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<QueryPopularBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            if(momentLocalMinId.equals("0")){
                                mHomeDynamicAdapter.setNewData(result.getData());
                            }else{
                                mHomeDynamicAdapter.addData(result.getData());
                            }
                            if(result.getData().size()==0){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                momentLocalMinId = result.getData().get(result.getData().size()-1).getMomentId();
                            }
                            if(mHomeDynamicAdapter.getData().size()>0){
                                tv_not.setVisibility(View.GONE);
                                mRvList.setVisibility(View.VISIBLE);
                            }else{
                                tv_not.setVisibility(View.VISIBLE);
                                mRvList.setVisibility(View.GONE);
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



