
package com.jxkj.fit_5a.view.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.AdminInspireBean;
import com.jxkj.fit_5a.entity.ProductListBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.entity.TeachingMomentListsBean;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.mine.JiaoXueSpXpActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingDetailsActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeRmkcMainAdapter;
import com.jxkj.fit_5a.view.adapter.HomeShoppingMainAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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

public class HomeThreeFragment_lm extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.rv_rmsp_list)
    RecyclerView mRvRmspList;
    @BindView(R.id.rv_rmkc_list)
    RecyclerView mRvRmkcList;
    @BindView(R.id.rv_cnxh_list)
    RecyclerView mRvCnxhList;
    @BindView(R.id.banner)
    Banner mBanner;

    int page = 1;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    private HomeRmkcMainAdapter mHomeRmkcMainAdapter;
    private HomeDynamicAdapter mHomeDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three_lm;
    }

    @Override
    protected void initViews() {
        initRvUi();
        getAdList();
        getQueryHomePopular();
        getQueryByPublisher();
        query_by_circle_follower();
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                query_by_circle_follower();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getAdList();
                getQueryHomePopular();
                getQueryByPublisher();
                query_by_circle_follower();
            }
        });
    }
    private void initRvUi() {
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvRmspList.setAdapter(mHomeThreeSqAdapter);
        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startActivity(getActivity(),
                        mHomeThreeSqAdapter.getData().get(position).getCircleId(),
                        mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                        mHomeThreeSqAdapter.getData().get(position).getMomentId());
            }
        });
        mHomeRmkcMainAdapter = new HomeRmkcMainAdapter(null);
        mRvRmkcList.setAdapter(mHomeRmkcMainAdapter);
        mHomeRmkcMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TeachingMomentBean data = mHomeRmkcMainAdapter.getData().get(position);
                IntentUtils.getInstence().intent(getActivity(), JiaoXueSpXpActivity.class,
                        "momentId",data.getMomentId()+"",
                        "publisherId",data.getPublisherId()+"");
            }
        });
        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvCnxhList.setAdapter(mHomeDynamicAdapter);
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

    private void getQueryByPublisher() {
        RetrofitUtil.getInstance().apiService()
                .getQueryByPublisher("0", "326",3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0) {
                            mHomeThreeSqAdapter.setNewData(result.getData());
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
                .getQueryHomePopular(1,2)
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
    String momentLocalMinId = "0";
    private void query_by_circle_follower(){
        RetrofitUtil.getInstance().apiService()
                .query_by_circle_follower(momentLocalMinId, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<QueryPopularBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<QueryPopularBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            if(momentLocalMinId.equals("0")){
                                mHomeDynamicAdapter.setNewData(result.getData());
                            }else{
                                mHomeDynamicAdapter.addData(result.getData());
                            }
                            if(result.getData().size()==0){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                momentLocalMinId = result.getData().get(result.getData().size()-1).getMomentId();
                            }
                            if(mHomeDynamicAdapter.getData().size()>0){
//                                tv_not.setVisibility(View.GONE);
//                                mRvList.setVisibility(View.VISIBLE);
                            }else{
//                                tv_not.setVisibility(View.VISIBLE);
//                                mRvList.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
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
}



