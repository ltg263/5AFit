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
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean_sc;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean_sc;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity_Gf;
import com.jxkj.fit_5a.view.adapter.GfListAdapter;
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

public class UserScActivity_gf extends BaseActivity {
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
    String localMinId = "0";
    String userId = "";
    private GfListAdapter mGfListAdapter;
    List<QueryPopularMomentBean> data;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("Ta收藏的文章");
        userId = getIntent().getStringExtra("userId");
        if(userId.equals(SharedUtils.getUserId()+"")){
            mTvTitle.setText("我收藏的文章");
        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mGfListAdapter = new GfListAdapter(data);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mGfListAdapter);
        mGfListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity_Gf.startActivityIntent(UserScActivity_gf.this,
                        mGfListAdapter.getData().get(position).getMomentId(),
                        mGfListAdapter.getData().get(position).getPublisherId(),
                        "详情");
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(userId.equals(SharedUtils.getUserId()+"")){
                    getFavoriteQueryOwnA();
                }else{
                    getFavoriteQueryOwn();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                localMinId = "0";
                if(userId.equals(SharedUtils.getUserId()+"")){
                    getFavoriteQueryOwnA();
                }else{
                    getFavoriteQueryOwn();
                }
            }
        });
        if(userId.equals(SharedUtils.getUserId()+"")){
            getFavoriteQueryOwnA();
        }else{
            getFavoriteQueryOwn();
        }
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }


    private void getFavoriteQueryOwnA() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQueryOwn_gf(localMinId,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularMomentBean_sc>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularMomentBean_sc> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            data = new ArrayList<>();
                            for(int i= 0;i<result.getData().size();i++){
                                data.add(result.getData().get(i).getMoment());
                            }
                            if(localMinId.equals("0")){
                                mGfListAdapter.setNewData(data);
                            }else{
                                if(data.size()>0){
                                    mGfListAdapter.addData(data);
                                }
                            }

                            if(result.getData().size()==0 || result.getData().size()<10){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                localMinId = result.getData().get(result.getData().size()-1).getIncrementId();
                            }

                            if(mGfListAdapter.getData().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                            }else{
                                mLvNot.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
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
    private void getFavoriteQueryOwn() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQueryOwn_gf(userId, localMinId,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularMomentBean_sc>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularMomentBean_sc> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            data = new ArrayList<>();
                            for(int i= 0;i<result.getData().size();i++){
                                data.add(result.getData().get(i).getMoment());
                            }
                            if(localMinId.equals("0")){
                                mGfListAdapter.setNewData(data);
                            }else{
                                if(data.size()>0){
                                    mGfListAdapter.addData(data);
                                }
                            }

                            if(result.getData().size()==0 || result.getData().size()<10){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                localMinId = result.getData().get(result.getData().size()-1).getIncrementId();
                            }

                            if(mGfListAdapter.getData().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                            }else{
                                mLvNot.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
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
        Intent intent = new Intent(mContext, UserScActivity_gf.class);
        if(StringUtil.isNotBlank(userId)){
            intent.putExtra("userId", userId);
        }
        mContext.startActivity(intent);
    }
}
