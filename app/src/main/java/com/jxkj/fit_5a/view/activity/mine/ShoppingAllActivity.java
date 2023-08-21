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
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingHAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingAllActivity extends BaseActivity {
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
    @BindView(R.id.tv_zonghe)
    TextView mTvZonghe;
    @BindView(R.id.tv_xiaoliang)
    TextView mTvXiaoliang;
    @BindView(R.id.tv_xinpin)
    TextView mTvXinpin;
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
    @BindView(R.id.view_4)
    View mView4;
    String type = "全部商品";
    private HomeShoppingAdapter mHomeShoppingAdapter;
    private HomeShoppingHAdapter mHomeShoppingHAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_all;
    }

    @Override
    protected void initViews() {
        type = getIntent().getStringExtra("type");
        mTvTitle.setText(type);
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(v -> finish());
        initRv();
        getProductList(null, null);
    }

    private void initRv() {
        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        mRvListAll.setLayoutManager(new GridLayoutManager(this, 2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingAllActivity.this, mHomeShoppingAdapter.getData().get(position).getId());
            }
        });
        mHomeShoppingHAdapter = new HomeShoppingHAdapter(null);
        mRvListAllH.setLayoutManager(new LinearLayoutManager(this));
        mRvListAllH.setHasFixedSize(true);
        mRvListAllH.setAdapter(mHomeShoppingHAdapter);

        mHomeShoppingHAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingAllActivity.this, mHomeShoppingHAdapter.getData().get(position).getId());
            }
        });
    }


    private void getProductList(Integer hasHot,Integer sort) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(null,hasHot,sort,"2", 1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeShoppingAdapter.setNewData(result.getData().getList());
                            mHomeShoppingHAdapter.setNewData(result.getData().getList());
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
        IntentUtils.getInstence().intent(mContext, ShoppingAllActivity.class, "type", type);
    }

    @OnClick({R.id.iv_top,R.id.tv_title_search,R.id.tv_zonghe, R.id.tv_xiaoliang,
            R.id.tv_xinpin, R.id.tv_jifenzhi, R.id.tv_shaixuan, R.id.tv_bian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top:
                mRvListAll.scrollToPosition(0);
                mRvListAllH.scrollToPosition(0);
                break;
            case R.id.tv_zonghe:
                chanxunshangpin(1);
                break;
            case R.id.tv_xiaoliang:
                chanxunshangpin(2);
                break;
            case R.id.tv_xinpin:
                chanxunshangpin(3);
                break;
            case R.id.tv_jifenzhi:
                chanxunshangpin(4);
                break;
            case R.id.tv_shaixuan:
                popupWindw();
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
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[5]);
                break;
        }
    }

    PopupWindowSx window;
    private void popupWindw() {
        window = new PopupWindowSx(this, new PopupWindowSx.GiveDialogInterface() {
            @Override
            public void btnConfirm(String jfd, String jfg, String jgd, String jgg) {
                Integer integralBegin = null, integralEnd = null, priceBegin = null, priceEnd = null;
                if(StringUtil.isNotBlank(jfd)){
                    integralBegin =Integer.valueOf(jfd);
                }
                if(StringUtil.isNotBlank(jfg)){
                    integralEnd =Integer.valueOf(jfg);
                }
                if(StringUtil.isNotBlank(jgd)){
                    priceBegin =Integer.valueOf(jgd);
                }
                if(StringUtil.isNotBlank(jgg)){
                    priceEnd =Integer.valueOf(jgg);
                }
                getProductList(integralBegin, integralEnd, priceBegin, priceEnd);
            }
        });
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setOutsideTouchable(true);
        window.showAtLocation(view, Gravity.BOTTOM,  0, 0);
    }

    private void getProductList(Integer integralBegin,Integer integralEnd,Integer priceBegin,Integer priceEnd) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(null,integralBegin,integralEnd,priceBegin,priceEnd,"2",1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeShoppingAdapter.setNewData(result.getData().getList());
                            mHomeShoppingHAdapter.setNewData(result.getData().getList());
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
    private void chanxunshangpin(int i) {
        mTvZonghe.setTextColor(getColor(R.color.color_666666));
        mTvXiaoliang.setTextColor(getColor(R.color.color_666666));
        mTvXinpin.setTextColor(getColor(R.color.color_666666));
        mTvJifenzhi.setTextColor(getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);
        mView3.setVisibility(View.INVISIBLE);
        mView4.setVisibility(View.INVISIBLE);
        switch (i){
            case 1:
                mTvZonghe.setTextColor(getColor(R.color.color_000000));
                mView1.setVisibility(View.VISIBLE);
                getProductList(null,null);
                break;
            case 2:
                mTvXiaoliang.setTextColor(getColor(R.color.color_000000));
                mView2.setVisibility(View.VISIBLE);
                getProductList(null,1);
                break;
            case 3:
                mTvXinpin.setTextColor(getColor(R.color.color_000000));
                mView3.setVisibility(View.VISIBLE);
                getProductList(null,2);
                break;
            case 4:
                mTvJifenzhi.setTextColor(getColor(R.color.color_000000));
                mView4.setVisibility(View.VISIBLE);
                mIvJifenzhi.setSelected(!mIvJifenzhi.isSelected());
                if(mIvJifenzhi.isSelected()){
                    getProductList(null,3);
                }else{
                    getProductList(null,4);
                }
                break;
        }
    }
}
