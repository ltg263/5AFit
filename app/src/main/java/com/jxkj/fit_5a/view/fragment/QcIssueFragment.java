package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.adapter.MineIssueAdapter;
import com.jxkj.fit_5a.view.adapter.MineIssueQcTopAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QcIssueFragment extends BaseFragment {
    @BindView(R.id.rv_list_top)
    RecyclerView mRvListTop;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout lv_not;

    private Bundle bundle;

    @Override
    protected int getContentView() {
        return R.layout.fragment_qc_list;
    }

    private int type = 0;

    @Override
    protected void initViews() {
        lv_not.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
        bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }
        });
        initRv();
    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        MineIssueQcTopAdapter mMineIssueQcTopAdapter = new MineIssueQcTopAdapter(null);
        mRvListTop.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRvListTop.setHasFixedSize(true);
        mRvListTop.setAdapter(mMineIssueQcTopAdapter);

        mMineIssueQcTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(MineIssueActivity.this, MineTopicActivity.class));
            }
        });

        MineIssueAdapter mMineIssueAdapter = new MineIssueAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineIssueAdapter);

        mMineIssueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(MineIssueActivity.this, MineTopicActivity.class));
            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
