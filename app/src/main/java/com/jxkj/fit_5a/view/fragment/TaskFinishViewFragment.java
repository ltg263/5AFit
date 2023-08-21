package com.jxkj.fit_5a.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.view.adapter.TaskFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskFinishViewFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private TaskFragmentAdapter mTaskFragmentAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_task_finish_view;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mTaskFragmentAdapter = new TaskFragmentAdapter(list);

        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTaskFragmentAdapter);

        mTaskFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initImmersionBar() {

    }
}
