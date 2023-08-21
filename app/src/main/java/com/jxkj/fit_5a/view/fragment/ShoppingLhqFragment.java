package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.view.activity.mine.MineIntegralActivity;
import com.jxkj.fit_5a.view.adapter.HomeShoppingLhqAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingLhqFragment extends BaseFragment {
    @BindView(R.id.rv_list_all)
    RecyclerView mRvListAll;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_integral)
    TextView tv_integral;
    @BindView(R.id.ll_dhjl)
    LinearLayout ll_dhjl;
    private HomeShoppingLhqAdapter mHomeShoppingLhqAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_shopping_lhq;
    }

    @Override
    protected void initViews() {
        initBannerOne();
        initRv();
        getProductList();
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
//                Log.i("tag", "你点了第" + position + "张轮播图:" + lists.get(position).getId());
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

        mHomeShoppingLhqAdapter = new HomeShoppingLhqAdapter(null);
        mRvListAll.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRvListAll.setHasFixedSize(true);
        mRvListAll.setAdapter(mHomeShoppingLhqAdapter);

        mHomeShoppingLhqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }


    private void getProductList() {
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
                        if(isDataInfoSucceed(result)){
                            mHomeShoppingLhqAdapter.setNewData(result.getData().getList());
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
