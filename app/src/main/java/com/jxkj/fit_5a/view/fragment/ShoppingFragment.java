package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.view.activity.mine.MineIntegralActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.ShoppingRmAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_all)
    RecyclerView mRvListAll;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.ll_dhjl)
    LinearLayout ll_dhjl;
    @BindView(R.id.tv_integral)
    TextView tv_integral;
    private ShoppingRmAdapter mShoppingRmAdapter;
    private HomeShoppingAdapter mHomeShoppingAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initViews() {
        initBannerOne();
        initRv();
        getProductList(1);
        getProductList(null);
        tv_integral.setText(SharedUtils.singleton().get(ConstValues.MY_INTEGRAL,""));
        ll_dhjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MineIntegralActivity.class));
            }
        });
    }

    private void initBannerOne() {
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list_path.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1096319023,1267316854&fm=26&gp=0.jpg");
//            list_title.add(lists.get(i).getTitle());
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "????????????" + position + "????????????:" + lists.get(position).getId());
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //?????????????????????
        mBanner.setImageLoader(new GlideImageLoader());
        //??????????????????
        mBanner.setImages(list_path);
        //??????banner????????????
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //??????????????????????????????true
        mBanner.isAutoPlay(true);
        //??????????????????
        mBanner.setDelayTime(3000);
        //banner?????????????????????????????????????????????
        mBanner.start();
    }

    private void initRv() {
        mShoppingRmAdapter = new ShoppingRmAdapter(null);
        LinearLayoutManager ms1 = new LinearLayoutManager(getActivity());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms1);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mShoppingRmAdapter);

        mShoppingRmAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(getActivity(), mHomeShoppingAdapter.getData().get(position).getId());
            }
        });


        mHomeShoppingAdapter = new HomeShoppingAdapter(null);
        mRvListAll.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingAdapter);

        mHomeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(getActivity(), mHomeShoppingAdapter.getData().get(position).getId());
            }
        });
    }


    private void getProductList(Integer hasHot) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(hasHot)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if(isDataInfoSucceed(result)){
                            if(hasHot==null){
                                mHomeShoppingAdapter.setNewData(result.getData().getList());
                            }else {
                                mShoppingRmAdapter.setNewData(result.getData().getList());
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

    @Override
    public void initImmersionBar() {

    }
}
