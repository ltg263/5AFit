package com.jxkj.fit_5a.view.activity.exercise;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.entity.CircleTaskData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.HomeTwoTaskSelect;
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

public class TaskSelectionOneActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.banner)
    Banner mBanner;
    private HomeTwoTaskSelect mHomeTwoTaskSelect;
    private String exercise_type;


    @Override
    protected int getContentView() {
        return R.layout.activity_task_selection_one;
    }

    @Override
    protected void initViews() {
        exercise_type = getIntent().getStringExtra("exercise_type");
        mHomeTwoTaskSelect = new HomeTwoTaskSelect(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeTwoTaskSelect);
        mHomeTwoTaskSelect.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CircleTaskData> data = mHomeTwoTaskSelect.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setSelect(false);
                }
                data.get(position).setSelect(true);
                mHomeTwoTaskSelect.notifyDataSetChanged();
            }
        });
        initBannerOne();
        getCircleTaskList();
    }

    @OnClick({R.id.iv_back,R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                IntentUtils.getInstence().intent(this,
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[4]);
                break;
        }
    }
    private void initBannerOne() {
        ArrayList<String> list_path = new ArrayList<>();
        list_path.add("https://oss.5afit.com/tcYcVPO4CuWZ4vh0OUH4Mw.jpeg");
        list_path.add("https://oss.5afit.com/tcYcVPO4CuWZ4vh0OUH4Mw.jpeg");
        list_path.add("https://oss.5afit.com/tcYcVPO4CuWZ4vh0OUH4Mw.jpeg");
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
    private void getCircleTaskList() {
        RetrofitUtil.getInstance().apiService()
                .getCircleTaskList(null,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CircleTaskData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CircleTaskData> result) {
                        if(isDataInfoSucceed(result)){
                            mHomeTwoTaskSelect.setNewData(result.getData());
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
}
