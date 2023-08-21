package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.adapter.TaskFinishPjAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MapFinish3Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.fragment_map_finish_3;
    }

    @Override
    protected void initViews() {

        Bundle bundle = getArguments();
        int type = 0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        initRv();

    }

    private void initRv() {
        List<String> list = new ArrayList<>();
        list.add("没有感觉");
        list.add("非常\n" + "非常弱");
        list.add("非常弱");
        list.add("弱");
        list.add("适度");
        list.add("有些强");
        list.add("强");
        list.add("非常强");
        list.add("非常\n非常强");

        TaskFinishPjAdapter mTaskFinishPjAdapter = new TaskFinishPjAdapter(list);
        mTaskFinishPjAdapter.setSelectPos(0);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTaskFinishPjAdapter);

        mTaskFinishPjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTaskFinishPjAdapter.setSelectPos(position);
                mTaskFinishPjAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
