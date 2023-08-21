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
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.UserGzAdapter;
import com.jxkj.fit_5a.view.adapter.UserListAdapter;
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
public class HomeSearchYhListFragment extends BaseFragment {
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvDtList;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private Bundle bundle;
    private String search = "";
    private UserListAdapter mUserListAdapter;
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

    private void getData() {
        RetrofitUtil.getInstance().apiService()
                .getHomeSearch(search, "user",page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<HomeSearchBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<HomeSearchBean> result) {
                        if (isDataInfoSucceed(result)&&result.getData()!=null) {
                            List<FollowFansList.DataBean.UserBean> data = result.getData().getUsers();
                            if(page==1){
                                mUserListAdapter.setNewData(data);
                            }else{
                                mUserListAdapter.addData(data);
                            }

                            if(mUserListAdapter.getData().size()>0){
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
        mUserListAdapter = new UserListAdapter(null);
        mRvDtList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mUserListAdapter);

        mUserListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserHomeActivity.startActivity(getActivity(),mUserListAdapter.getData().get(position).getUserId()+"");
            }
        });
    }
}
