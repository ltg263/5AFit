
package com.jxkj.fit_5a.view.fragment;

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
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.RecommendUser;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.RecommendUserAdapter;
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

public class HomeThreeFragment_gz extends BaseFragment {


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_not)
    RecyclerView rv_list_not;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.ll_not_data)
    NestedScrollView ll_not_data;
    private HomeDynamicAdapter mHomeDynamicAdapter;
    private RecommendUserAdapter mRecommendUserAdapter;
    String momentLocalMinId = "0";
    int page = 1;
    public void setDoubleClicked(){
//        mRvList.smoothScrollToPosition(0);
        mRvList.scrollToPosition(0);
        refreshLayout.autoRefresh();
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three_gz;
    }

    @Override
    protected void initViews() {
        initRvUi();
        getFollowFollowers();
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                query_by_follower();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                momentLocalMinId = "0";
                query_by_follower();
            }
        });
    }
    List<String> followerIds = new ArrayList<>();
    @OnClick({R.id.tv_refresh,R.id.tv_go_gz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                followerIds.clear();
                page++;
                recommend_user();
                break;
            case R.id.tv_go_gz:
                if(followerIds.size()==0){
                    ToastUtils.showShort("请先选择要关注的用户");
                    return;
                }
                followBatch();
                break;
        }
    }
    public void followBatch() {
        Integer[] followerIds_url = new Integer[followerIds.size()];
        for(int i=0;i<followerIds.size();i++){
            followerIds_url[i] = Integer.valueOf(followerIds.get(i).replace(" ",""));
        }
        RetrofitUtil.getInstance().apiService()
                .followBatch(followerIds_url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            momentLocalMinId = "0";
                            refreshLayout.setVisibility(View.VISIBLE);
                            ll_not_data.setVisibility(View.GONE);
                            query_by_follower();
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

    private void initRvUi() {
        mRecommendUserAdapter = new RecommendUserAdapter(null);
        rv_list_not.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv_list_not.setHasFixedSize(true);
        rv_list_not.setAdapter(mRecommendUserAdapter);
        mRecommendUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<RecommendUser> data = mRecommendUserAdapter.getData();
                data.get(position).setSelect(!data.get(position).isSelect());
                mRecommendUserAdapter.notifyDataSetChanged();
                if(data.get(position).isSelect()){
                    followerIds.add(data.get(position).getUserId()+"");
                }else{
                    followerIds.remove(data.get(position).getUserId()+"");
                }
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeDynamicAdapter);
    }
    @Override
    public void initImmersionBar() {

    }

    private void query_by_follower(){
        RetrofitUtil.getInstance().apiService()
                .query_by_follower(momentLocalMinId, ConstValues.PAGE_SIZE)
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
                            if(mHomeDynamicAdapter.getData().size()==0){
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


    private void getFollowFollowers(){
        RetrofitUtil.getInstance().apiService()
                .getFollowFollowers(SharedUtils.getUserId()+"",1,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FollowFansList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowFansList result) {
                        if (result.getCode()==0) {
                            if(result.getData().size()>0){
                                refreshLayout.setVisibility(View.VISIBLE);
                                ll_not_data.setVisibility(View.GONE);
                                query_by_follower();
                            }else{
                                page = 1;
                                recommend_user();
                                refreshLayout.setVisibility(View.GONE);
                                ll_not_data.setVisibility(View.VISIBLE);
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

    private void recommend_user(){
        RetrofitUtil.getInstance().apiService()
                .recommend_user(page,9)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<RecommendUser>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<RecommendUser>> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().size()==0 && page!=1){
                                page = 1;
                                recommend_user();
                                return;
                            }
                            mRecommendUserAdapter.setNewData(result.getData());
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



