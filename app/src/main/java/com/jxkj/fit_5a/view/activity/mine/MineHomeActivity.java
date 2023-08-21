package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.UserTopAdapter;
import com.jxkj.fit_5a.view.fragment.HomeThreeNewFragment;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineHomeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.rv_qz_list)
    RecyclerView mRvQzList;
    @BindView(R.id.rv_dt_list)
    RecyclerView mRvDtList;
    @BindView(R.id.rv_dt_list_sp)
    RecyclerView mRvDtListSp;

    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rl_actionbar_1)
    RelativeLayout mRlActionbar1;
    @BindView(R.id.iv_head)
    RoundImageView mIvHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_gz_zt)
    TextView mTvGzZt;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_gz)
    TextView mTvGz;
    @BindView(R.id.ll_gz_on)
    LinearLayout mLlGzOn;
    @BindView(R.id.tv_fs)
    TextView mTvFs;
    @BindView(R.id.ll_fs_on)
    LinearLayout mLlFsOn;
    @BindView(R.id.tv_sc)
    TextView mTvSc;
    @BindView(R.id.ll_sc_on)
    LinearLayout mLlScOn;
    @BindView(R.id.tv_lw)
    TextView mTvLw;
    @BindView(R.id.ll_lw_on)
    LinearLayout mLlLwOn;
    @BindView(R.id.rl1)
    RelativeLayout mRl1;
    @BindView(R.id.rl2)
    RelativeLayout mRl2;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    private HomeDynamicAdapter mHomeDynamicAdapter;
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    private UserTopAdapter mUserTopAdapter;
    String momentLocalMinId = "0";
    int contentType = 2;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_home;
    }

    @Override
    protected void initViews() {
        initRv();
    }

    private void initRv() {

        mRvDtList.setVisibility(View.VISIBLE);
        mRvDtListSp.setVisibility(View.GONE);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            //6
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float fraction = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                mRlActionbar1.setAlpha(fraction);
                mRlActionbar.setAlpha(1 - fraction);

            }
        });

        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false));
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getQueryByPublisher();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getUserProfileOwn();
                getCircleQueryJoined();
                momentLocalMinId = "0";
                getQueryByPublisher();
            }
        });

        mUserTopAdapter = new UserTopAdapter(null);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvQzList.setLayoutManager(ms);
        mRvQzList.setHasFixedSize(true);
        mRvQzList.setAdapter(mUserTopAdapter);

        mUserTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(MineHomeActivity.this, MineCircleActivity.class);
                mIntent.putExtra("id",mUserTopAdapter.getData().get(position).getId());
                startActivity(mIntent);
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvDtList.setLayoutManager(new LinearLayoutManager(this));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mHomeDynamicAdapter);


        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvDtListSp.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvDtListSp.setHasFixedSize(true);
        mRvDtListSp.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoActivity.startActivity(MineHomeActivity.this,
                        mHomeThreeSqAdapter.getData().get(position).getCircleId(),
                        mHomeThreeSqAdapter.getData().get(position).getPublisherId(),
                        mHomeThreeSqAdapter.getData().get(position).getMomentId());
            }
        });


        mHomeThreeSqAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_head_img:
                    case R.id.tv_name:
                        UserHomeActivity.startActivity(MineHomeActivity.this, mHomeThreeSqAdapter.getData().get(position).getUser().getUserId() + "");
                        break;
                    case R.id.ll_xh:
                        HomeThreeNewFragment.xihuan(mHomeThreeSqAdapter.getData().get(position), mHomeThreeSqAdapter);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserProfileOwn();
        getCircleQueryJoined();
        getQueryByPublisher();
    }

    String userId;

    @OnClick({R.id.iv_back, R.id.tv_state,R.id.rl1, R.id.rl2, R.id.ll_gz_on, R.id.ll_fs_on, R.id.ll_lw_on, R.id.ll_sc_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_state:
                startActivity(new Intent(this, MineInfoActivity.class));
                break;
            case R.id.ll_gz_on:
                UserGzActivity.startActivity(this, SharedUtils.getUserId() + "");
                break;
            case R.id.ll_fs_on:
                UserFsActivity.startActivity(this, SharedUtils.getUserId() + "");
                break;
            case R.id.ll_lw_on:
                if(true){
                    ToastUtils.showShort("功能开发中敬请期待");
                    return;
                }
                startActivity(new Intent(this, MineGiftActivity.class));
                break;
            case R.id.ll_sc_on:
                UserScActivity.startActivity(this, SharedUtils.getUserId() + "");
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                mRvDtList.setVisibility(View.VISIBLE);
                mRvDtListSp.setVisibility(View.GONE);
                momentLocalMinId = "0";
                contentType = 2;
                getQueryByPublisher();
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                mRvDtList.setVisibility(View.GONE);
                mRvDtListSp.setVisibility(View.VISIBLE);
                momentLocalMinId = "0";
                contentType = 3;
                getQueryByPublisher();
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_999999));
        mTv2.setTextColor(getResources().getColor(R.color.color_999999));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        v.setVisibility(View.VISIBLE);
//        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    private void getUserProfileOwn() {
        RetrofitUtil.getInstance().apiService()
                .getUserProfileOwn()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserOwnInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserOwnInfo> userOwnInfoResult) {
                        if (isDataInfoSucceed(userOwnInfoResult)) {
                            initUi(userOwnInfoResult.getData());
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

    private void initUi(UserOwnInfo data) {
        userId = data.getUserId();
        GlideImageUtils.setGlideImage(this, data.getAvatar(), mIvHead);
        mTvName.setText(data.getNickName());
        mTvState.setText(data.getExplain());
        mTvGz.setText(data.getFollowCount());
        mTvFs.setText(data.getFansCount());
        mTvSc.setText(data.getFavoriteCount());
        mTvLw.setText(data.getGiftCount());
        mTvGzZt.setVisibility(View.INVISIBLE);
        if(StringUtil.isNotBlank(data.getMedalCount()) && Integer.parseInt(data.getMedalCount())>0){
            mTvGzZt.setVisibility(View.VISIBLE);
            mTvGzZt.setText(data.getMedalCount()+"勋章");
        }
        if(StringUtil.isNotBlank(data.getBackImg())){
            Glide.with(this)
                    .asBitmap()
                    .load(data.getBackImg())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(resource);
                            mRlActionbar.setBackground(drawable);
                        }

                    });
        }else{
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.icon_home_beijing)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(resource);
                            mRlActionbar.setBackground(drawable);
                        }

                    });
        }
//        if(data.getRelation()==4){
//
//        }

    }


    private void getCircleQueryJoined() {
        RetrofitUtil.getInstance().apiService()
                .getCircleQueryJoinedOwn(1, 100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleQueryJoinedBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleQueryJoinedBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mUserTopAdapter.setNewData(result.getData().getList());
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

    private void getQueryByPublisher() {
        RetrofitUtil.getInstance().apiService()
                .getQueryByPublisherOwn(momentLocalMinId, contentType, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0) {
                            if (contentType == 2) {
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
//                                if(mHomeDynamicAdapter.getData().size()>0){
//                                    tv_not.setVisibility(View.GONE);
//                                    mRvList.setVisibility(View.VISIBLE);
//                                }else{
//                                    tv_not.setVisibility(View.VISIBLE);
//                                    mRvList.setVisibility(View.GONE);
//                                }
                            }
                            if (contentType == 3) {
                                if(momentLocalMinId.equals("0")){
                                    mHomeThreeSqAdapter.setNewData(result.getData());
                                }else{
                                    mHomeThreeSqAdapter.addData(result.getData());
                                }
                                if(result.getData().size()==0){
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }else{
                                    momentLocalMinId = result.getData().get(result.getData().size()-1).getMomentId();
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
}
