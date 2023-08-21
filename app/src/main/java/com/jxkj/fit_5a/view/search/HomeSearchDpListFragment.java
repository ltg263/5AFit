package com.jxkj.fit_5a.view.search;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.HomeSearchBean;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/6/116:57
 */
public class HomeSearchDpListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvDtList;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private Bundle bundle;
    private String search = "";
    private HomeDynamicAdapter mHomeDynamicAdapter;
    int page = 1;

    @Override
    protected int getContentView() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("search");
        }
        initList();
        getData();
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getData();
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
//内容类型(1:文本;2:图文;3:视频
    private void getData() {
        RetrofitUtil.getInstance().apiService()
                .getHomeSearch(search, "moment",page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HomeSearchBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HomeSearchBean> result) {
                        if (isDataInfoSucceed(result) && result.getData()!=null) {
                            List<QueryPopularBean> data = result.getData().getMoments();
                            if(page==1){
                                mHomeDynamicAdapter.setNewData(data);
                            }else{
                                mHomeDynamicAdapter.addData(data);
                            }

                            if(mHomeDynamicAdapter.getData().size()>0){
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                lv_not.setVisibility(View.GONE);
                            }else{
                                mRefreshLayout.setVisibility(View.GONE);
                                lv_not.setVisibility(View.VISIBLE);
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

    private void initList() {
        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvDtList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mHomeDynamicAdapter);
    }
}
