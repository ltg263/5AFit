package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.FavoriteQueryList;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.entity.TeachingMomentBeanWc;
import com.jxkj.fit_5a.view.adapter.HomeJiaoXueSpAdapter;
import com.jxkj.fit_5a.view.adapter.PopupWindwSbAdapter;
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

public class UserShiPinScActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
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
    private HomeJiaoXueSpAdapter mHomeJiaoXueSpAdapter;
    List<TeachingMomentBean> data;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("Ta的收藏");
        userId = getIntent().getStringExtra("userId");
        if(userId.equals(SharedUtils.getUserId()+"")){
            mTvTitle.setText("我的收藏");
        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mHomeJiaoXueSpAdapter = new HomeJiaoXueSpAdapter(data);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeJiaoXueSpAdapter);
        mHomeJiaoXueSpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(UserShiPinScActivity.this,JiaoXueSpXpActivity.class,
                        "momentId",mHomeJiaoXueSpAdapter.getData().get(position).getMomentId()+"",
                        "publisherId",mHomeJiaoXueSpAdapter.getData().get(position).getPublisherId()+"");
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(userId.equals(SharedUtils.getUserId()+"")){
                    getFavoriteQueryOwn_sp();
                }else{
                    getFavoriteQuery_sp();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                localMinId = "0";
                if(userId.equals(SharedUtils.getUserId()+"")){
                    getFavoriteQueryOwn_sp();
                }else{
                    getFavoriteQuery_sp();
                }
            }
        });
        if(userId.equals(SharedUtils.getUserId()+"")){
            getFavoriteQueryOwn_sp();
        }else{
            getFavoriteQuery_sp();
        }
    }
    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }


    private void getFavoriteQueryOwn_sp() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQueryOwn_sp(localMinId,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<TeachingMomentBeanWc>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<TeachingMomentBeanWc> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            data = new ArrayList<>();
                            for(int i= 0;i<result.getData().size();i++){
                                data.add(result.getData().get(i).getMoment());
                            }
                            if(localMinId.equals("0")){
                                mHomeJiaoXueSpAdapter.setNewData(data);
                            }else{
                                if(data.size()>0){
                                    mHomeJiaoXueSpAdapter.addData(data);
                                }
                            }

                            if(result.getData().size()==0 || result.getData().size()<10){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                localMinId = result.getData().get(result.getData().size()-1).getIncrementId()+"";
                            }

                            if(mHomeJiaoXueSpAdapter.getData().size()>0){
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
    private void getFavoriteQuery_sp() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQuery_sp(userId, localMinId,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<TeachingMomentBeanWc>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<TeachingMomentBeanWc> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            data = new ArrayList<>();
                            for(int i= 0;i<result.getData().size();i++){
                                data.add(result.getData().get(i).getMoment());
                            }
                            if(localMinId.equals("0")){
                                mHomeJiaoXueSpAdapter.setNewData(data);
                            }else{
                                if(data.size()>0){
                                    mHomeJiaoXueSpAdapter.addData(data);
                                }
                            }

                            if(result.getData().size()==0 || result.getData().size()<10){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                localMinId = result.getData().get(result.getData().size()-1).getIncrementId()+"";
                            }

                            if(mHomeJiaoXueSpAdapter.getData().size()>0){
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
        Intent intent = new Intent(mContext, UserShiPinScActivity.class);
        if(StringUtil.isNotBlank(userId)){
            intent.putExtra("userId", userId);
        }
        mContext.startActivity(intent);
    }
}
