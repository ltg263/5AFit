package com.jxkj.fit_5a.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.AdminInspireBean;
import com.jxkj.fit_5a.entity.AnnouncementList;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.entity.TeachingMomentListsBean;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionOneActivity;
import com.jxkj.fit_5a.view.activity.mine.JiaoXueSpActivity;
import com.jxkj.fit_5a.view.activity.home.TaskSignActivity;
import com.jxkj.fit_5a.view.activity.mine.JiaoXueSpXpActivity;
import com.jxkj.fit_5a.view.activity.mine.MineMessageAnnouncementActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.activity.mine.YunDongShuJuActivity;
import com.jxkj.fit_5a.view.adapter.HomeCnxhMainHomeAdapter;
import com.jxkj.fit_5a.view.adapter.HomeRmkcMainAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingMainAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiaosu.view.text.DataSetAdapter;
import com.xiaosu.view.text.VerticalRollingTextView;
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

public class HomeOneFragment extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_message_content)
    VerticalRollingTextView mTvMessageContent;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_rmsp_list)
    RecyclerView mRvRmspList;
    @BindView(R.id.rv_rmkc_list)
    RecyclerView mRvRmkcList;
    @BindView(R.id.rv_cnxh_list)
    RecyclerView mRvCnxhList;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_close)
    View iv_close;
    @BindView(R.id.iv_gosc)
    View iv_gosc;
    private HomeShoppingMainAdapter mHomeShoppingMainAdapter;
    private HomeRmkcMainAdapter mHomeRmkcMainAdapter;
    private HomeCnxhMainHomeAdapter mHomeCnxhMainHomeAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_one;
    }

    @Override
    protected void initViews() {
        initRvUi();
        getAdList();
        getQueryHomePopular();
        getAnnouncementList();
        getProductList(1);
        getProductListCnxh();
        mRlActionbar.setAlpha(1);
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getAdList();
                getQueryHomePopular();
                getAnnouncementList();
                getProductList(1);
                getProductListCnxh();
            }
        });
    }
    private void initRvUi() {
        mHomeShoppingMainAdapter = new HomeShoppingMainAdapter(null);
        mRvRmspList.setAdapter(mHomeShoppingMainAdapter);
        mHomeShoppingMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(getActivity(), mHomeShoppingMainAdapter.getData().get(position).getId());
            }
        });
        mHomeRmkcMainAdapter = new HomeRmkcMainAdapter(null);
        mRvRmkcList.setAdapter(mHomeRmkcMainAdapter);
        mHomeRmkcMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TeachingMomentBean data = mHomeRmkcMainAdapter.getData().get(position);
                IntentUtils.getInstence().intent(getActivity(),JiaoXueSpXpActivity.class,
                        "momentId",data.getMomentId()+"",
                        "publisherId",data.getPublisherId()+"");
            }
        });
        mHomeCnxhMainHomeAdapter = new HomeCnxhMainHomeAdapter(null);
        mRvCnxhList.setAdapter(mHomeCnxhMainHomeAdapter);
        mHomeCnxhMainHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShoppingDetailsActivity.startActivity(getActivity(), mHomeCnxhMainHomeAdapter.getData().get(position).getId());
            }
        });
    }


    @Override
    public void initImmersionBar() {

    }
    public void setDoubleClicked(){
        if(mNestedScrollView!=null && refreshLayout!=null){
            mNestedScrollView.scrollTo(0,0);
            refreshLayout.autoRefresh();
        }
    }

    public static HomeOneFragment newInstance() {
        HomeOneFragment homeFragment = new HomeOneFragment();
        return homeFragment;
    }

    @OnClick({R.id.iv_close,R.id.iv_gosc,R.id.ll_top_1,R.id.rl_actionbar,R.id.ll_top_2,R.id.ll_top_3,R.id.ll_top_4,R.id.ll_top_5,R.id.tv_gdkc,R.id.tv_gdsp,R.id.on_rv_qd,R.id.tv_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                iv_close.setVisibility(View.GONE);
                iv_gosc.setVisibility(View.GONE);
                break;
            case R.id.on_rv_qd:
            case R.id.ll_top_1:
                startActivity(new Intent(getActivity(), TaskSignActivity.class));
                break;
            case R.id.tv_gdkc:
            case R.id.ll_top_2:
                startActivity(new Intent(getActivity(), JiaoXueSpActivity.class));
                break;
            case R.id.ll_top_3:
                startActivity(new Intent(getActivity(), YunDongShuJuActivity.class));
                break;
            case R.id.iv_gosc:
            case R.id.ll_top_4:
            case R.id.tv_gdsp:
                ShoppingActivity.intentStartActivity(getActivity());
                break;
            case R.id.ll_top_5:

                IntentUtils.getInstence().
                        intent(getActivity(), TaskSelectionOneActivity.class, "exercise_type", "");
                break;
            case R.id.tv_message:
                startActivity(new Intent(getActivity(), MineMessageAnnouncementActivity.class));
                break;
            case R.id.rl_actionbar:
                IntentUtils.getInstence().intent(getActivity(),
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[4]);
                break;
        }
    }

    private void getProductListCnxh() {
        RetrofitUtil.getInstance().apiService()
                .getProductList(1,null,null,"2",1,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeCnxhMainHomeAdapter.setNewData(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                    }
                });

    }

    private void getProductList(Integer hasHot) {
        RetrofitUtil.getInstance().apiService()
                .getProductList(hasHot,null,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ProductListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ProductListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeShoppingMainAdapter.setNewData(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                    }
                });

    }
    private void getAdList() {
        RetrofitUtil.getInstance().apiService()
                .getAdList("1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdListData> result) {
                        if (isDataInfoSucceed(result)) {
                            List<AdListData.ListBean> data = result.getData().getList();
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
    private void getQueryHomePopular() {
        RetrofitUtil.getInstance().apiService()
                .getQueryHomePopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TeachingMomentListsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TeachingMomentListsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeRmkcMainAdapter.setNewData(result.getData().getList());
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
    private void getAnnouncementList() {
        RetrofitUtil.getInstance().apiService()
                .getAnnouncementList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AnnouncementList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AnnouncementList> result) {
                        if (isDataInfoSucceed(result)) {
                            List<AnnouncementList.ListBean> data = result.getData().getList();
                            if(data!=null && data.size()>0){
                                mTvMessageContent.setDataSetAdapter(new DataSetAdapter<AnnouncementList.ListBean>(data) {
                                    @Override
                                    protected CharSequence text(AnnouncementList.ListBean s) {
                                        return s.getTitle();
                                    }
                                });
                                mTvMessageContent.run();
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

    private void initBannerOne(List<AdListData.ListBean> data) {
        ArrayList<String> list_path = new ArrayList<>();
        if(data!=null){
            for(int i= 0;i<data.size();i++){
                list_path.add(data.get(i).getImgUrl());
            }
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                IntentUtils.getInstence().intent(getActivity(),JiaoXueSpXpActivity.class,
//                        "momentId",data.get(position).getMomentId()+"",
//                        "publisherId",data.get(position).getPublisherId()+"");
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

    //    ----------------------------------------------------------------------
    private void getAdminInspire() {
        RetrofitUtil.getInstance().apiService()
                .getAdminInspire()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdminInspireBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdminInspireBean> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()!=null && result.getData()!=null){
//                                tv_title.setText(result.getData().getTitle());
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
//    private void getClassificationAll() {
//        RetrofitUtil.getInstance().apiService()
//                .getClassificationAll()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<Result<List<ClassificationAllData>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Result<List<ClassificationAllData>> result) {
//                        if (isDataInfoSucceed(result)) {
//                            List<String> mDataList = new ArrayList<>();
//                            mClassificationAllData =  result.getData();
//                            for(int i = 0;i<result.getData().size();i++){
//                                mDataList.add(result.getData().get(i).getName());
//                            }
//                            initVP();
//                            if(mDataList.size()>4){
//                                MagicIndicatorUtils.initMagicIndicator_1(getActivity(),false,mDataList, mMagicIndicator, mViewPager);
//                            }else{
//                                MagicIndicatorUtils.initMagicIndicator_1(getActivity(),true, mDataList, mMagicIndicator, mViewPager);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }
//
//    List<Fragment> fragments = new ArrayList<>();
//    private List<Fragment> getFragments() {
//        for(int i=0;i<mClassificationAllData.size();i++){
//            GfClassificationFragment fragment = new GfClassificationFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("classificationId",mClassificationAllData.get(i).getClassificationId());
//            fragment.setArguments(bundle);
//            fragments.add(fragment);
//        }
//        return fragments;
//    }
//    private void initVP() {
//        if(mClassificationAllData==null){
//            return;
//        }
//        getFragments();
//        mViewPager.setOffscreenPageLimit(mClassificationAllData.size());
//        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return "";
//            }
//        });
//
//        mViewPager.setCurrentItem(0);
//    }
}



