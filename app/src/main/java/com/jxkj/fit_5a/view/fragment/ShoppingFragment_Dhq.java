package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.jxkj.fit_5a.entity.ProductBannerList;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.view.activity.mine.MineIntegralActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.adapter.HomeShoppingAdapter;
import com.jxkj.fit_5a.view.adapter.ShoppingRmAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingFragment_Dhq extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_all)
    RecyclerView mRvListAll;
    @BindView(R.id.rl_all)
    RelativeLayout rl_all;
    @BindView(R.id.rl_11)
    RelativeLayout rl_11;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.ll_dhjl)
    LinearLayout ll_dhjl;
    @BindView(R.id.tv_integral)
    TextView tv_integral;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private HomeShoppingAdapter mHomeShoppingAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initViews() {
        initRv();
        getProductList(null,1,1);
//        getProductList(1,null,2);
        getProductList(null,null,3);
        tv_integral.setText(SharedUtils.singleton().get(ConstValues.MY_INTEGRAL,""));
        ll_dhjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MineIntegralActivity.class));
            }
        });
    }



    private void initBannerOne(List<ProductListBean.ListBean> list) {
        if(list==null || list.size()==0){
            rl_11.setVisibility(View.GONE);
            return;
        }
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list_path.add(list.get(i).getImgUrl());
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ShoppingDetailsActivity.startActivity(getActivity(),list.get(position).getId());
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

    private void initRv() {
        mRvList.setVisibility(View.GONE);
        rl_all.setVisibility(View.GONE);
        tv_name.setText("全部好券");
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


    private void getProductList(Integer hasHot,Integer hasNew,int type) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(hasHot,hasNew,"3")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if(isDataInfoSucceed(result)){
                            List<ProductListBean.ListBean> data = result.getData().getList();
                            if(type ==1){
                                initBannerOne(data);
                            }else if(type==3){
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

    @Override
    public void initImmersionBar() {

    }
}
