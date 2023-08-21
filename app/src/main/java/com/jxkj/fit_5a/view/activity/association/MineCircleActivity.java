package com.jxkj.fit_5a.view.activity.association;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.BlurringView;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PileAvertView;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;
import com.jxkj.fit_5a.view.activity.mine.MineSetActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
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

public class MineCircleActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.CoordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv_jiaru)
    TextView tv_jiaru;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.tv_join)
    TextView tv_join;
    @BindView(R.id.pile_view_1)
    PileAvertView pile_view_1;
    @BindView(R.id.blurring_view)
    BlurringView mBlurringView;
    @BindView(R.id.rl_11)
    RelativeLayout rl11;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl_actionbar_1)
    RelativeLayout mRlActionbar1;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.iv_head_img)
    ImageView mIvHeadImg;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_renshu)
    TextView mTvRenshu;
    @BindView(R.id.tv_jianjie)
    TextView mTvJianjie;
    @BindView(R.id.tv_renshu_1)
    TextView mTvRenshu1;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.rl1)
    RelativeLayout mRl1;
    @BindView(R.id.rl2)
    RelativeLayout mRl2;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tv_add_dt)
    ImageView mTvAddDt;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String id=null;
    private HomeDynamicAdapter mHomeDynamicAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_circle;
    }

    @Override
    protected void initViews() {
        id = getIntent().getStringExtra("id");
        getCircleDetails();

        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false));
//        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));


        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            //6
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float fraction = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                mRlActionbar1.setAlpha(fraction);
                mRlActionbar.setAlpha(1 - fraction);

            }
        });

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getQuery_popular();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                momentLocalMinId = "0";
                getCircleDetails();
                getQuery_popular();
            }
        });

        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeDynamicAdapter);

        mHomeDynamicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AssociationActivity.startActivity(MineCircleActivity.this,
                        id+"",
                        mHomeDynamicAdapter.getData().get(position).getPublisherId(),
                        mHomeDynamicAdapter.getData().get(position).getMomentId());
            }
        });
        mBlurringView.setBlurredView(mRvList);
        rl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        contentType = "2";
        getQuery_popular();
    }

    @OnClick({R.id.iv_back, R.id.tv_join,R.id.tv_add_dt, R.id.tv_jiaru, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_join:
//                DialogUtils.showDialogHint(this, "确定要退出圈子吗？", false, new DialogUtils.ErrorDialogInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        getCircleQuit();
//                    }
//                });

                DialogUtils.showUnificationDialog(this, "退出圈子","确定要退出圈子吗？", "确定",true,
                        new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                getCircleQuit();
                            }
                        });
                break;
            case R.id.tv_jiaru:
                getCircleJoin(id);
                break;
            case R.id.tv_add_dt:
                initPopupWindow();
                break;
            case R.id.rl1:
                momentLocalMinId = "0";
                contentType = "2";
                getQuery_popular();
                initView(mTv1, mView1);
                break;
            case R.id.rl2:
                momentLocalMinId = "0";
                contentType = "3";
                getQuery_popular();
                initView(mTv2, mView2);
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
            window = new PopupWindowTy(this, list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    int type = 3;
                    if (position == 0) {
                        type = 2;
                    }
//                    CircleAddActivity.startActivity(MineCircleActivity.this,id);
                    AssociationAddActivity.startActivityAddAssociation(
                            MineCircleActivity.this,type,id,data.getName(),data.getImgUrl(),"");
                }
            });

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        window.showAtLocation(mTv, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_666666));
        mTv2.setTextColor(getResources().getColor(R.color.color_666666));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }
    CircleDetailsBean data;
    private void getCircleDetails() {
        RetrofitUtil.getInstance().apiService()
                .getCircleDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<CircleDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CircleDetailsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            data = result.getData();
                            tv_jiaru.setVisibility(View.VISIBLE);
                            rl11.setVisibility(View.VISIBLE);
                            tv_join.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
                            tv_join.setText("+ 关注");
                            tv_join.setVisibility(View.GONE);
                            if (data.isJoin()) {
                                tv_join.setVisibility(View.VISIBLE);
                                tv_join.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
                                tv_join.setText("已加入");
                                tv_jiaru.setVisibility(View.GONE);
                                rl11.setVisibility(View.GONE);
                            }

                            GlideImgLoader.loadImageViewRadius(MineCircleActivity.this,data.getImgUrl(),10,mIvHeadImg);
                            String bgImg = StringUtil.isBlank(data.getBgImg())?data.getImgUrl():data.getBgImg();
                            Glide.with(MineCircleActivity.this).asBitmap().load(bgImg)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                            Drawable drawable = new BitmapDrawable(resource);
                                            mRlActionbar.setBackground(drawable);
                                        }
                                    });
                            tv_title.setText(data.getName());
                            mTv.setText(data.getName());
                            mTvRenshu.setText("人数 "+data.getMemberCount()+"人 | 动态 "+data.getMomentCount()+"条");
                            mTvJianjie.setText(data.getExplain());
                            mTvRenshu1.setText(data.getMemberCount()+"位小伙伴");
                            List<String> urls=new ArrayList<>();
                            urls.clear();
                            if(data.getMembers()!=null){
                                int size = data.getMembers().size();
                                if(size>6){
                                    size = 6;
                                }
                                for(int i=0;i<size;i++){
                                    if(data.getMembers().get(i).getUser()!=null){
                                        urls.add(data.getMembers().get(i).getUser().getAvatar());
                                    }
                                }
                            }
                            pile_view_1.setAvertImages(urls);
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

    private void getCircleJoin(String id) {
        RetrofitUtil.getInstance().apiService()
                .getCircleJoin(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShortToast(MineCircleActivity.this,"加入成功");
                            getCircleDetails();
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
    String momentLocalMinId ="0";
    String contentType ="2";


    private void getQuery_popular() {
        RetrofitUtil.getInstance().apiService()
                .query_by_circle(id, contentType,null,momentLocalMinId, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<QueryPopularBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<QueryPopularBean>> result) {
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
                            tv_not.setVisibility(View.GONE);
                            mRvList.setVisibility(View.VISIBLE);
                        }else{
                            tv_not.setVisibility(View.VISIBLE);
                            mRvList.setVisibility(View.VISIBLE);
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

    private void getCircleQuit() {
        RetrofitUtil.getInstance().apiService()
                .getCircleQuit(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShortToast(MineCircleActivity.this,"退出成功");
                            getCircleDetails();

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
