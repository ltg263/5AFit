package com.jxkj.fit_5a.view.activity.association;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.TopicAllBean;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.fragment.HomeThreeNewFragment;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineTopicActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_head_img)
    ImageView mIvHeadImg;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_renshu)
    TextView mTvRenshu;
    @BindView(R.id.tv_not_data)
    TextView tv_not_data;
    @BindView(R.id.tv_nr)
    TextView mTvNr;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rl_actionbar_1)
    RelativeLayout mRlActionbar1;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_rd)
    TextView mTvRd;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;

    private int page = 1;
    private String topicName;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_topic;
    }

    @Override
    protected void initViews() {
        topicName =  getIntent().getStringExtra("topicName");
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if(isHot){
                    query_hot_by_topic();
                }else{
                    query_by_topic();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if(isHot){
                    query_hot_by_topic();
                }else{
                    query_by_topic();
                }
            }
        });

        getTopicAll();

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            //6
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float fraction = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                mRlActionbar1.setAlpha(fraction);
                mRlActionbar.setAlpha(1 - fraction);

            }
        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvList.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QueryPopularBean data = mHomeThreeSqAdapter.getData().get(position);
                if(mHomeThreeSqAdapter.getData().get(position).getContentType().equals("3")){
                    VideoActivity.startActivity(MineTopicActivity.this,data.getCircleId(),data.getPublisherId(),data.getMomentId());
                }else{
                    AssociationActivity.startActivity(MineTopicActivity.this,data.getCircleId(),data.getPublisherId(),data.getMomentId());
                }
            }
        });


        mHomeThreeSqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_head_img:
                    case R.id.tv_name:
                        UserHomeActivity.startActivity(MineTopicActivity.this,mHomeThreeSqAdapter.getData().get(position).getUser().getUserId()+"");
                        break;
                    case R.id.ll_xh:
                        HomeThreeNewFragment.xihuan(mHomeThreeSqAdapter.getData().get(position),mHomeThreeSqAdapter);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        query_hot_by_topic();
    }
    private void getTopicAll() {
        RetrofitUtil.getInstance().apiService()
                .getTopicDetails(topicName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TopicAllBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TopicAllBean> result) {
                        if (isDataInfoSucceed(result)) {
                            TopicAllBean data = result.getData();
                            mTv.setText("# "+data.getName()+" #");
                            tv_title.setText("# "+data.getName()+" #");

                            mTvRenshu.setText("人数 "+data.getPageviews()+"人 | 动态 "+data.getArticlesCount()+"条");
                            mTvNr.setText(data.getIntroduction());
                            GlideImgLoader.loadImageViewWithCirclr(MineTopicActivity.this,data.getImgUrl(),mIvHeadImg);
                            Glide.with(MineTopicActivity.this).asBitmap().load(data.getImgUrl())
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                            Drawable drawable = new BitmapDrawable(resource);
                                            mRlActionbar.setBackground(drawable);
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
    boolean isHot = true;

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_add_dt,R.id.tv_rd,R.id.tv_sj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_add_dt:
                initPopupWindow();
                break;
            case R.id.tv_rd:
                isHot = true;
                mTvRd.setTextColor(getResources().getColor(R.color.color_333333));
                mTvSj.setTextColor(getResources().getColor(R.color.color_999999));
                page = 1;
                query_hot_by_topic();
                break;
            case R.id.tv_sj:
                isHot = false;
                mTvRd.setTextColor(getResources().getColor(R.color.color_999999));
                mTvSj.setTextColor(getResources().getColor(R.color.color_333333));
                page = 1;
                query_by_topic();
                break;
        }
    }

    PopupWindowTy window;
    List<String> list = new ArrayList<>();
    private void initPopupWindow() {
        list.clear();
        list.add("相册");
        list.add("视频");
        if (window == null) {
            window = new PopupWindowTy(MineTopicActivity.this, list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    int type = 3;
                    if (position == 0) {
                        type = 2;
                    }
                    IntentUtils.getInstence().intent(MineTopicActivity.this, AssociationAddActivity.class,"type",type,"topic",topicName);
                }
            });

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        window.showAtLocation(mTv, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }

    private void query_hot_by_topic() {
        RetrofitUtil.getInstance().apiService()
                .query_hot_by_topic(null,null,topicName,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (isDataInfoSucceed(result)) {
                            refreshLayout.finishLoadMore();
                            refreshLayout.finishRefresh();
                            if (isDataInfoSucceed(result)) {
                                mHomeThreeSqAdapter.setNewData(result.getData());
                                if(result.getData().size() < ConstValues.PAGE_SIZE){
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onComplete() {

                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }
                });
    }

    private void query_by_topic() {
        RetrofitUtil.getInstance().apiService()
                .query_by_topic(null,null,topicName,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                        if (isDataInfoSucceed(result)) {
                            mHomeThreeSqAdapter.setNewData(result.getData());
                            if(result.getData().size() < ConstValues.PAGE_SIZE){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onComplete() {

                        refreshLayout.finishLoadMore();
                        refreshLayout.finishRefresh();
                    }

                });
    }

    public static void startActivity(Context mContext, String topicName) {
        Intent intent = new Intent(mContext, MineTopicActivity.class);
        intent.putExtra("topicName", topicName);
        mContext.startActivity(intent);
    }
}
