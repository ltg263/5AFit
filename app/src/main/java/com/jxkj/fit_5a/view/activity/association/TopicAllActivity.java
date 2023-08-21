package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.view.adapter.TopicListAdapter;
import com.jxkj.fit_5a.view.adapter.TopicListTAdapter;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopicAllActivity extends BaseActivity {
    @BindView(R.id.rv_list_t)
    RecyclerView mRvListT;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    private TopicListAdapter mTopicListAdapter;
    private TopicListTAdapter mTopicListTAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_topic_all;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("全部话题");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTopicListTAdapter = new TopicListTAdapter(null);
        mRvListT.setLayoutManager(new LinearLayoutManager(this));
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
                mTopicListAdapter.setNewData(mTopicListTAdapter.getData().get(position).getChildren());
            }
        });

        mTopicListAdapter = new TopicListAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mTopicListAdapter);

        mTopicListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isNotBlank(getIntent().getStringExtra("type"))) {
                    Intent intent = new Intent();
                    intent.putExtra("topics", mTopicListAdapter.getData().get(position).getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    MineTopicActivity.startActivity(TopicAllActivity.this,mTopicListAdapter.getData().get(position).getName());
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
                                mTopicListAdapter.setNewData(mTopicListTAdapter.getData().get(0).getChildren());
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
}
