package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.ProductScListBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.view.adapter.FacilityAddSbAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
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

public class ShoppingSearchActivity extends BaseActivity {
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private HomeShoppingAdapter mHomeShoppingAdapter;
    int page = 1;
    String keyword = null,type = null;

    @Override
    protected int getContentView() {
        return R.layout.activity_jiao_xue_sp_search;
    }

    @OnClick({R.id.ll_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }
    @Override
    protected void initViews() {
        keyword = getIntent().getStringExtra("search");
        type = getIntent().getStringExtra("type");
        tvTopTitle.setText(keyword);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getTeachingMomentQuery();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getTeachingMomentQuery();
            }
        });

        mRvContent.setLayoutManager(new GridLayoutManager(this, 2));
        mRvContent.setHasFixedSize(true);
        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        mRvContent.setAdapter(mHomeShoppingAdapter);
        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingSearchActivity.this,
                        mHomeShoppingAdapter.getData().get(position).getId());
            }
        });
        getTeachingMomentQuery();
    }
    private void getTeachingMomentQuery() {
        if (StringUtil.isNotBlank(type)) {
            getProductCollectList();
            return;
        }
        RetrofitUtil.getInstance().apiService()
                .getProductList(keyword,null,null,null,null,"2",1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<ProductListBean.ListBean> data = result.getData().getList();
                            if(page==1){
                                mHomeShoppingAdapter.setNewData(data);
                            }else{
                                mHomeShoppingAdapter.addData(data);
                            }
                            mRvContent.setVisibility(View.GONE);
                            tv_not.setVisibility(View.VISIBLE);
                            if(mHomeShoppingAdapter.getData().size()>0){
                                mRvContent.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                            }
                            if(data.size() < ConstValues.PAGE_SIZE){
                                mRefreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void getProductCollectList() {
        RetrofitUtil.getInstance().apiService()
                .getProductCollectList(keyword,1,1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductScListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductScListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<ProductListBean.ListBean> data = new ArrayList<>();
                            for(int i=0;i<result.getData().getList().size();i++){
                                ProductListBean.ListBean mListBean = new ProductListBean.ListBean();
                                ProductScListBean.ListBean mProductScListBean = result.getData().getList().get(i);
                                mListBean.setId(mProductScListBean.getProductId());
                                mListBean.setImgUrl(mProductScListBean.getProductImg());
                                mListBean.setName(mProductScListBean.getProductName());
                                mListBean.setDeductIntegral(mProductScListBean.getIntegral());
                                mListBean.setPrice(mProductScListBean.getPrice());
                                mListBean.setSubTitle("");
                                data.add(mListBean);
                            }
                            if(page==1){
                                mHomeShoppingAdapter.setNewData(data);
                            }else{
                                mHomeShoppingAdapter.addData(data);
                            }
                            mRvContent.setVisibility(View.GONE);
                            tv_not.setVisibility(View.VISIBLE);
                            if(mHomeShoppingAdapter.getData().size()>0){
                                mRvContent.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                            }
                            if(data.size() < ConstValues.PAGE_SIZE){
                                mRefreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                });
    }
}
