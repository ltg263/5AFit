package com.jxkj.fit_5a.view.activity.mine;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.BlackListBean;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.view.adapter.UserHmdAdapter;
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

public class UserHmdActivity extends BaseActivity {
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
    private UserHmdAdapter mUserHmdAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("黑名单列表");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mUserHmdAdapter = new UserHmdAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mUserHmdAdapter);

        mUserHmdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                UserHomeActivity.startActivity(UserHmdActivity.this,mUserHmdAdapter.getData().get(position).getUser().getUserId()+"");
            }
        });
        mUserHmdAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                postRemoveBlackList(mUserHmdAdapter.getData().get(position).getBlUserId());
            }
        });
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getBlackList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                getBlackList();
            }
        });
        getBlackList();
    }


    private void postRemoveBlackList(String blUserId) {
        RetrofitUtil.getInstance().apiService()
                .postRemoveBlackList(blUserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.getCode()==0) {
                            getBlackList();
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

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    int page = 1;
    private void getBlackList(){
        HttpRequestUtils.postBlackList(this,new HttpRequestUtils.LoginInterface() {
            @Override
            public void succeed(String path) {
                if(path.equals("0")){
                    Log.w("mBlackListBean","MainActivity.mBlackListBean:"+MainActivity.mBlackListBean.size());
                    mUserHmdAdapter.setNewData(MainActivity.mBlackListBean);
                    if(mUserHmdAdapter.getData().size()>0){
                        refreshLayout.setVisibility(View.VISIBLE);
                        mLvNot.setVisibility(View.GONE);
                    }else{
                        refreshLayout.setVisibility(View.GONE);
                        mLvNot.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        if(true){
            return;
        }
        RetrofitUtil.getInstance().apiService()
//                .getBlackList(userId,page, ConstValues.PAGE_SIZE)
                .getBlackList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<BlackListBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<BlackListBean>> result) {
                        if (result.getCode()==0) {
                            List<BlackListBean> data = result.getData();
                            if(page ==1){
                                mUserHmdAdapter.setNewData(data);
                            }else{
                                mUserHmdAdapter.addData(data);
                            }
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadMore();

                            if(mUserHmdAdapter.getData().size()>0){
                                refreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                            }else{
                                refreshLayout.setVisibility(View.GONE);
                                mLvNot.setVisibility(View.VISIBLE);
                            }
                            if(data.size()<ConstValues.PAGE_SIZE){
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
}
