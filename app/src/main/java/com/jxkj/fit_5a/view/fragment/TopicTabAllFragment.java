package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.TopicAllActivity;
import com.jxkj.fit_5a.view.adapter.TopicListAdapter;
import com.jxkj.fit_5a.view.adapter.TopicListAdapter_1;
import com.jxkj.fit_5a.view.adapter.TopicListTAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopicTabAllFragment extends BaseFragment {

    @BindView(R.id.rv_list_t)
    RecyclerView mRvListT;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private TopicListTAdapter mTopicListTAdapter;
    private TopicListAdapter_1 mTopicListAdapter_1;
    @Override
    protected int getContentView() {
        return R.layout.fragment_topic_all;
    }

    @Override
    protected void initViews() {
        mTopicListTAdapter = new TopicListTAdapter(null);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvListT.setLayoutManager(llm);
        mRvListT.setHasFixedSize(true);
        mRvListT.setAdapter(mTopicListTAdapter);

        mTopicListTAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mTopicListTAdapter.getData().size(); i++) {
                    mTopicListTAdapter.getData().get(i).setSele(false);
                }
                mTopicListTAdapter.getData().get(position).setSele(true);
                mTopicListTAdapter.notifyDataSetChanged();
                mTopicListAdapter_1.setNewData(mTopicListTAdapter.getData().get(position).getChildren());
            }
        });

        mTopicListAdapter_1 = new TopicListAdapter_1(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTopicListAdapter_1);

        mTopicListAdapter_1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isNotBlank(getActivity().getIntent().getStringExtra("type"))) {
                    Intent intent = new Intent();
                    intent.putExtra("topics", mTopicListAdapter_1.getData().get(position).getName());
                    getActivity().setResult(getActivity().RESULT_OK, intent);
                    getActivity().finish();
                }else{
                    MineTopicActivity.startActivity(getActivity(),mTopicListAdapter_1.getData().get(position).getName());
                }
            }
        });
        getTopicAll();
    }

    private void getTopicAll() {
        RetrofitUtil.getInstance().apiService()
                .getTopicAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<TopicAllBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<TopicAllBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData().size() > 0) {
                                for (int i = 0; i < result.getData().size(); i++) {
                                    result.getData().get(i).setSele(false);
                                }
                                result.getData().get(0).setSele(true);
                                mTopicListTAdapter.setNewData(result.getData());
                                mTopicListAdapter_1.setNewData(mTopicListTAdapter.getData().get(0).getChildren());
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
