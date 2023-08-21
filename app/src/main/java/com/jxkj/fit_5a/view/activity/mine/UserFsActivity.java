package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.view.adapter.UserGzAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserFsActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String userId = "";
    private UserGzAdapter mUserGzAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("Ta的粉丝");
        userId = getIntent().getStringExtra("userId");
        if(userId.equals(SharedUtils.getUserId()+"")){
            mTvTitle.setText("我的粉丝");
        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mUserGzAdapter = new UserGzAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mUserGzAdapter);

        mUserGzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserHomeActivity.startActivity(UserFsActivity.this,mUserGzAdapter.getData().get(position).getUser().getUserId()+"");
            }
        });


        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getFollowFansList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getFollowFansList();
            }
        });
        getFollowFansList();
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    int page = 1;
    private void getFollowFansList(){
        RetrofitUtil.getInstance().apiService()
                .getFollowFansList(userId,page,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FollowFansList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowFansList result) {
                        if (result.getCode()==0) {
                            List<FollowFansList.DataBean> data = result.getData();
                            if(page ==1){
                                mUserGzAdapter.setNewData(data);
                            }else{
                                mUserGzAdapter.addData(data);
                            }
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();

                            if(mUserGzAdapter.getData().size()>0){
                                refreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                            }else{
                                refreshLayout.setVisibility(View.GONE);
                                mLvNot.setVisibility(View.VISIBLE);
                            }
                            if(data.size()< ConstValues.PAGE_SIZE){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }
    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, UserFsActivity.class);
        intent.putExtra("userId", userId);
        mContext.startActivity(intent);
    }

}
