package com.jxkj.fit_5a.view.activity.mine;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.entity.ProductBannerList;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.ShoppingRmAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingJfActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.mNsv)
    NestedScrollView mNsv;
    @BindView(R.id.iv_top)
    ImageView iv_top;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_all)
    RecyclerView mRvListAll;
    @BindView(R.id.banner)
    Banner mBanner;
    private ShoppingRmAdapter mShoppingRmAdapter;
    private HomeShoppingAdapter mHomeShoppingAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_jf;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("积分商城");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(v -> finish());
        initRv();
        getBannerList();
//        getProductList(null, 1, 1);
        getProductList(1, null, 2);
        getProductList(null, null, 3);
    }

    private void initRv() {
        mShoppingRmAdapter = new ShoppingRmAdapter(null);
        LinearLayoutManager ms1 = new LinearLayoutManager(this);
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms1);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mShoppingRmAdapter);

        mShoppingRmAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingJfActivity.this, mShoppingRmAdapter.getData().get(position).getId());
            }
        });


        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        mRvListAll.setLayoutManager(new GridLayoutManager(this, 2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(ShoppingJfActivity.this, mHomeShoppingAdapter.getData().get(position).getId());
            }
        });
//        getusable_not_obtained();
    }


    private void getBannerList() {
        RetrofitUtil.getInstance().apiService()
                .getBannerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductBannerList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductBannerList> result) {
                        if (isDataInfoSucceed(result)) {
                            List<ProductBannerList.ListBean> data = result.getData().getList();
                            initBannerOne(data);
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


    private void getProductList(Integer hasHot, Integer hasNew, int type) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(hasHot, hasNew, "2")
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
                            if (type == 1) {
//                                initBannerOne(data);
                            } else if (type == 2) {
                                mShoppingRmAdapter.setNewData(result.getData().getList());
                            } else if (type == 3) {
                                mHomeShoppingAdapter.setNewData(result.getData().getList());
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

    private void initBannerOne(List<ProductBannerList.ListBean> list) {
        if (list == null || list.size() == 0) {

            return;
        }
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list_path.add(list.get(i).getImgUrl());
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(list.get(position).getType()==1){
                    WebViewActivity.startActivityIntent(ShoppingJfActivity.this,
                            list.get(position).getLink(),"积分");
                }else{
                    ShoppingDetailsActivity.startActivity(ShoppingJfActivity.this, list.get(position).getProductId());
                }
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(list_path);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    Dialog dialog;

    private void getusable_not_obtained() {
        RetrofitUtil.getInstance().apiService()
                .getusable_not_obtained()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DiscountUsableNotBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DiscountUsableNotBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() == null || result.getData().getList() == null || result.getData().getList().size() == 0) {
                                return;
                            }
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                                dialog = null;
                            }
                            dialog = DialogUtils.showDiaYouHuiQuan(ShoppingJfActivity.this, result.getData().getList(),
                                    new DialogUtils.DialogInterfaceYhq() {
                                        @Override
                                        public void btnConfirm(int id) {
                                            if (id == -1) {
                                                List<Integer> lists = new ArrayList<>();
                                                for (int i = 0; i < result.getData().getList().size(); i++) {
                                                    lists.add(result.getData().getList().get(i).getId());
                                                }
                                                getPrizeReceives(lists);
                                            } else {
                                                getPrizeReceive(id);
                                            }
                                        }
                                    });
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

    private void getPrizeReceive(int id) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceive(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            getusable_not_obtained();
                            ToastUtils.showShort("领取成功");
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

    private void getPrizeReceives(List<Integer> lists) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceives(lists)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
//                            getusable_not_obtained();
                            dialog.dismiss();
                            ToastUtils.showShort("领取成功");
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


    public static void intentStartActivity(Context mContext) {
        mContext.startActivity(new Intent(mContext, ShoppingJfActivity.class));
    }

    @OnClick({R.id.iv_top,R.id.ll_top_2, R.id.ll_top_3, R.id.ll_top_4, R.id.ll_top_1,R.id.tv_gengduo,R.id.tv_title_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top:
                mNsv.scrollTo(0,0);
                break;
            case R.id.tv_gengduo:
                ShoppingYhhqActivity.intentStartActivity(this,"优惠好券");
                break;
            case R.id.ll_top_1:
                ShoppingAllActivity.intentStartActivity(this,"全部商品");
                break;
            case R.id.ll_top_2:
                ShoppingScActivity.intentStartActivity(this,"我的收藏");
                break;
            case R.id.ll_top_3:
                startActivity(new Intent(this, MineYhqActivity.class));
                break;
            case R.id.ll_top_4:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_JDGZ_URL,"积分规则");
                break;
            case R.id.tv_title_search:
                IntentUtils.getInstence().intent(this,
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[5]);
                break;
        }
    }
}
