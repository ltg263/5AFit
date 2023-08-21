package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.view.View;
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
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingHAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingYhhqActivity extends BaseActivity {
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
    @BindView(R.id.tv_jifenzhi)
    TextView mTvJifenzhi;
    @BindView(R.id.iv_jifenzhi)
    ImageView mIvJifenzhi;
    @BindView(R.id.tv_bian)
    ImageView mTvBian;
    @BindView(R.id.view_1)
    View mView1;
    @BindView(R.id.view_2)
    View mView2;
    String type = "优惠好券";
    private HomeShoppingAdapter mHomeShoppingAdapter;
    private HomeShoppingHAdapter mHomeShoppingHAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_yhhq;
    }

    @Override
    protected void initViews() {
        type = getIntent().getStringExtra("type");
        mTvTitle.setText(type);
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(v -> finish());
        initRv();
        getProductList(4);
    }

    private void initRv() {
        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        mRvListAll.setLayoutManager(new GridLayoutManager(this, 2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingYhhqActivity.this, mHomeShoppingAdapter.getData().get(position).getId());
            }
        });
        mHomeShoppingHAdapter = new HomeShoppingHAdapter(null);
        mRvListAllH.setLayoutManager(new LinearLayoutManager(this));
        mRvListAllH.setHasFixedSize(true);
        mRvListAllH.setAdapter(mHomeShoppingHAdapter);

        mHomeShoppingHAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingYhhqActivity.this, mHomeShoppingHAdapter.getData().get(position).getId());
            }
        });
    }


    private void getProductList(Integer sort) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(null,null,sort,"3", 1, 100)
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
        IntentUtils.getInstence().intent(mContext, ShoppingYhhqActivity.class, "type", type);
    }

    @OnClick({R.id.tv_jiage,  R.id.tv_jifenzhi, R.id.tv_bian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jiage:
//                chanxunshangpin(1);
                mIvJiage.setSelected(!mIvJiage.isSelected());
                if(mIvJiage.isSelected()){
                    getProductList(3);
                }else{
                    getProductList(4);
                }
                break;
            case R.id.tv_jifenzhi:
//                chanxunshangpin(2);
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
        }
    }

    private void chanxunshangpin(int i) {
        mTvJiage.setTextColor(getColor(R.color.color_666666));
        mTvJifenzhi.setTextColor(getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);
        switch (i){
            case 1:
                mTvJiage.setTextColor(getColor(R.color.color_000000));
                mView1.setVisibility(View.VISIBLE);
                break;
            case 2:
                mTvJifenzhi.setTextColor(getColor(R.color.color_000000));
                mView2.setVisibility(View.VISIBLE);
                break;
        }
        getProductList(3);
    }
}
