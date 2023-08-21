package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.PopupWindowSx;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.ProductScListBean;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingHAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingScActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list_all)
    RecyclerView mRvListAll;
    @BindView(R.id.rv_list_all_h)
    RecyclerView mRvListAllH;
    @BindView(R.id.tv_jiage)
    TextView mTvJiage;
    @BindView(R.id.iv_jiage)
    ImageView mIvJiage;
    @BindView(R.id.tv_shoucangsj)
    TextView mTvShoucangsj;
    @BindView(R.id.iv_shoucangsj)
    ImageView mIvShoucangsj;
    @BindView(R.id.tv_jifenzhi)
    TextView mTvJifenzhi;
    @BindView(R.id.iv_jifenzhi)
    ImageView mIvJifenzhi;
    @BindView(R.id.tv_bian)
    ImageView mTvBian;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view_1)
    View mView1;
    @BindView(R.id.view_2)
    View mView2;
    @BindView(R.id.view_3)
    View mView3;
    @BindView(R.id.iv_notdata)
    View iv_notdata;
    String type = "全部商品";
    private HomeShoppingAdapter mHomeShoppingAdapter;
    private HomeShoppingHAdapter mHomeShoppingHAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_sc;
    }

    @Override
    protected void initViews() {
        type = getIntent().getStringExtra("type");
        mTvTitle.setText(type);
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(v -> finish());
        initRv();
        mRvListAllH.setVisibility(View.GONE);
        mRvListAll.setVisibility(View.VISIBLE);
        getProductCollectList(null);
    }

    private void initRv() {
        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        mRvListAll.setLayoutManager(new LinearLayoutManager(this));
        mRvListAll.setLayoutManager(new GridLayoutManager(this, 2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingScActivity.this, mHomeShoppingAdapter.getData().get(position).getId());
            }
        });
        mHomeShoppingHAdapter = new HomeShoppingHAdapter(null);
        mRvListAllH.setLayoutManager(new LinearLayoutManager(this));
        mRvListAllH.setHasFixedSize(true);
        mRvListAllH.setAdapter(mHomeShoppingHAdapter);

        mHomeShoppingHAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingScActivity.this, mHomeShoppingHAdapter.getData().get(position).getId());
            }
        });
    }


    private void getProductCollectList(Integer sort) {
        RetrofitUtil.getInstance().apiService()
                .getProductCollectList(null,sort, 1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductScListBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductScListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            iv_notdata.setVisibility(View.GONE);
                            if(result.getData().getList()==null || result.getData().getList().size()==0){
                                iv_notdata.setVisibility(View.VISIBLE);
                                mRvListAllH.setVisibility(View.GONE);
                                mRvListAll.setVisibility(View.GONE);
                            }else{
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
                                mHomeShoppingAdapter.setNewData(data);
                                mHomeShoppingHAdapter.setNewData(data);
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

    public static void intentStartActivity(Context mContext, String type) {
        IntentUtils.getInstence().intent(mContext, ShoppingScActivity.class, "type", type);
    }

    @OnClick({R.id.tv_jiage, R.id.tv_jifenzhi, R.id.tv_shoucangsj, R.id.tv_bian,R.id.tv_title_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jiage:
                chanxunshangpin(1);
                break;
            case R.id.tv_jifenzhi:
                chanxunshangpin(2);
                break;
            case R.id.tv_shoucangsj:
                chanxunshangpin(3);
                break;
            case R.id.tv_bian:
                mTvBian.setSelected(!mTvBian.isSelected());
                if(mTvBian.isSelected()){
                    mRvListAllH.setVisibility(View.VISIBLE);
                    mRvListAll.setVisibility(View.GONE);
                }else{
                    mRvListAllH.setVisibility(View.GONE);
                    mRvListAll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_title_search:
                IntentUtils.getInstence().intent(this,
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[6]);
                break;
        }
    }
    private void chanxunshangpin(int i) {
        mTvJiage.setTextColor(getColor(R.color.color_666666));
        mTvJifenzhi.setTextColor(getColor(R.color.color_666666));
        mTvShoucangsj.setTextColor(getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);
        mView3.setVisibility(View.INVISIBLE);
        switch (i){
            case 1:
                mTvJiage.setTextColor(getColor(R.color.color_000000));
                mView1.setVisibility(View.VISIBLE);
                mIvJiage.setSelected(!mIvJiage.isSelected());
                if(mIvJiage.isSelected()){
                    getProductCollectList(1);
                }else{
                    getProductCollectList(2);
                }
                break;
            case 2:
                mTvJifenzhi.setTextColor(getColor(R.color.color_000000));
                mView2.setVisibility(View.VISIBLE);
                mIvJifenzhi.setSelected(!mIvJifenzhi.isSelected());
                if(mIvJifenzhi.isSelected()){
                    getProductCollectList(3);
                }else{
                    getProductCollectList(4);
                }
                break;
            case 3:
                mTvShoucangsj.setTextColor(getColor(R.color.color_000000));
                mView3.setVisibility(View.VISIBLE);
                mIvShoucangsj.setSelected(!mIvShoucangsj.isSelected());
                if(mIvShoucangsj.isSelected()){
                    getProductCollectList(3);
                }else{
                    getProductCollectList(4);
                }
                break;
        }
    }
}
