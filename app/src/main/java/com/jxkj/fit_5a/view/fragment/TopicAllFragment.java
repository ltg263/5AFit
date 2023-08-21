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
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.adapter.TopicListAdapter_1;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class TopicAllFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int id=0;
    private TopicListAdapter_1 mTopicListAdapter;

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
                getAllTopic();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getAllTopic();
            }
        });

        mTopicListAdapter = new TopicListAdapter_1(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTopicListAdapter);

        mTopicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isNotBlank(getActivity().getIntent().getStringExtra("type"))) {
                    Intent intent = new Intent();
                    intent.putExtra("topics", mTopicListAdapter.getData().get(position).getName());
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }else{
                    MineTopicActivity.startActivity(getActivity(),mTopicListAdapter.getData().get(position).getName());
                }
            }
        });
        getAllTopic();
    }

    private void getAllTopic() {
        RetrofitUtil.getInstance().apiService()
                .getAllTopic(null,page,ConstValues.PAGE_SIZE)
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
                            if(page==1){
                                mTopicListAdapter.setNewData(data);
                            }else{
                                mTopicListAdapter.addData(data);
                            }
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();

//                            int totalPage = StringUtil.getTotalPage(result.getData().getTotal(), ConstValues.PAGE_SIZE);
                            int totalPage = 1;
                            if(mTopicListAdapter.getData().size()>0){
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
