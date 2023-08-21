package com.jxkj.fit_5a.view.activity.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.MyNestedScrollView;
import com.jxkj.fit_5a.conpoment.view.MyWebView;
import com.jxkj.fit_5a.conpoment.view.PileAvertView;
import com.jxkj.fit_5a.conpoment.view.SoftKeyboardInputDialog;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.QueryPopularMomentDetailsBean;
import com.jxkj.fit_5a.view.adapter.MineCommentAdapter_gf;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WebViewActivity_Gf extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.web)
    MyWebView mWeb;
    @BindView(R.id.iv_shoucang)
    ImageView mIvShoucang;
    @BindView(R.id.tv_shoucang)
    TextView mTvShoucang;
    @BindView(R.id.iv_xihuan)
    ImageView mIvXihuan;
    @BindView(R.id.tv_xihuan)
    TextView mTvXihuan;
    @BindView(R.id.mMyNestedScrollView)
    MyNestedScrollView mMMyNestedScrollView;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.tv_liuyan)
    TextView mTvLiuyan;
    @BindView(R.id.ll_r)
    LinearLayout mLlR;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.pile_view_1)
    PileAvertView mPileView1;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.ali_pl)
    TextView mAliPl;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    @BindView(R.id.tv_rd)
    TextView mTvRd;
    @BindView(R.id.tv_browse_num)
    TextView mTvBrowseNum;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    @BindView(R.id.refreshLayout_gf)
    SmartRefreshLayout mRefreshLayout;

    int page = 1;
    String momentId,publisherId;
    String commentOrderType = "lately";
    private QueryPopularMomentDetailsBean data;
    private MineCommentAdapter_gf mMineCommentAdapter_gf;

    @Override
    protected int getContentView() {
        return R.layout.activity_webview_gf;
    }

    @Override
    protected void initViews() {
        setWebViewClient();
        momentId = getIntent().getStringExtra("momentId");
        publisherId = getIntent().getStringExtra("publisherId");
        String url = ConstValues.USER_HANDE_GF+"momentId="+momentId +"&publisherId="+publisherId;
        Log.w("url", "url:" + url);
        String title = getIntent().getStringExtra("title");

        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getQueryPopularMomentDetails();
            }
        });


        mTvTitle.setText(title);
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWeb.canGoBack()) {
                    mWeb.goBack();
                } else {
                    finish();
                }
            }
        });
        mWeb.loadUrl(url);
        initLy();
    }
    private void getQueryPopularMomentDetails() {
        RetrofitUtil.getInstance().apiService()
                .getQueryPopularMomentDetails(momentId,publisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<QueryPopularMomentDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<QueryPopularMomentDetailsBean> result) {
                        if (isDataInfoSucceed(result) && result.getData()!=null) {
                            data = result.getData();
                            mTvXihuan.setText(data.getLikeCount() + "");
                            mTvShoucang.setText(data.getFavoriteCount() + "");
                            mTvLiuyan.setText(data.getCommentCount() + "");
                            mLlR.setVisibility(View.GONE);
                            if (data.getLikeCount() != 0) {
                                mLlR.setVisibility(View.VISIBLE);
                                tv_price.setText(data.getLikeCount() + "人赞过");
                                List<QueryPopularMomentDetailsBean.MomentLikesBean> mList = data.getMomentLikes();
                                List<String> urls = new ArrayList<>();
                                for (int i = 0; i < mList.size(); i++) {
                                    urls.add(mList.get(i).getUser().getAvatar());
                                }
                                mPileView1.setAvertImages(urls);
                            }
                            getCommentMoment();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                    }
                });
    }

    private void initLy() {

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mMineCommentAdapter_gf = new MineCommentAdapter_gf(null);
        mMineCommentAdapter_gf.setContentType(2);
        mRvList.setAdapter(mMineCommentAdapter_gf);
        mMineCommentAdapter_gf.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mMineCommentAdapter_gf.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_liuyan2:
                        CommentMomentBean data = mMineCommentAdapter_gf.getData().get(position);
                        showSoftInputFromWindow(data.getCommentId() + "", "回复@" + data.getUser().getNickName() + ":");
                        break;
                }
            }
        });
        getQueryPopularMomentDetails();
//        postBrows();
    }

    private void getCommentMoment() {
        HttpRequestUtils.getCommentMoment_gf(commentOrderType,  momentId, publisherId, page, ConstValues.PAGE_SIZE,
                new HttpRequestUtils.ResultInterface() {
                    @Override
                    public void succeed(ResultList<CommentMomentBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (page == 1) {
                                mMineCommentAdapter_gf.setNewData(result.getData());
                            } else {
                                mMineCommentAdapter_gf.addData(result.getData());
                            }
                            if(mMineCommentAdapter_gf.getData().size()>0){
                                mAliPl.setVisibility(View.VISIBLE);
                                mRvList.setVisibility(View.VISIBLE);
                                mLl.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                                if (result.getData().size() < 10) {
                                    mAliPl.setVisibility(View.GONE);
                                }
                            }else{
                                mAliPl.setVisibility(View.GONE);
                                mRvList.setVisibility(View.GONE);
                                mLl.setVisibility(View.GONE);
                                tv_not.setVisibility(View.VISIBLE);
                            }
//                            if(StringUtil.isNotBlank(ly)){
//                                ly = "";
//                                mMMyNestedScrollView.smoothScrollTo(0,ll1.getTop());
//                            }
                        }
                    }
                });
    }

    SoftKeyboardInputDialog dialog_input;
    /**
     * EditText获取焦点并显示软键盘
     */
    public void showSoftInputFromWindow(String commentId, String context2) {
        //使用自定义的dialog
        dialog_input = new SoftKeyboardInputDialog(this, context2, R.style.DialogTheme,
                new SoftKeyboardInputDialog.DialogInterfaceKey() {
                    @Override
                    public void strContext(String context) {
                        liuyan(context, commentId);
                    }
                });
        dialog_input.show();


        /** ====  ps:如果想按返回键关闭软键盘的同时也关闭dialog，那可以写如下代码：
         监听布局宽高变化，进而退出dialog（软键盘弹出布局变小，退出则恢复，以此来判断键盘弹出或退出）

         该activity的布局一定要有ScrollView控件 或 滚动的控件(ps：控件可无内容，只为实现布局因软键盘状态而大小变化)，
         或在AndroidManifest.xml对应<activity>里加入：android:windowSoftInputMode="adjustResize"，
         或有RecyclerView，否则软键盘弹出或隐藏布局大小没有变化！！
         */
        ll_top.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                if (bottom > oldBottom) {
                    dialog_input.dismiss();
                    ll_top.removeOnLayoutChangeListener(this);//移除监听，避免不需要时还在监听
                }
            }
        });
    }
    private void liuyan(String context, String commentId) {
        show();
        int contentType = data.isVideo()?3:2;
        HttpRequestUtils.postCommentMoment_gf(context, data.getMomentId(), data.getPublisherId() + "",
                commentId, contentType,new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        if (!path.equals("0")) {
                            ToastUtils.showShortToast(WebViewActivity_Gf.this,"留言失败");
                            dismiss();
                            return;
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dismiss();
                                        dialog_input.dismiss();
                                        page = 1;
                                        getQueryPopularMomentDetails();
                                        getCommentMoment();
                                    }
                                });
                            }
                        }).start();
                    }
                });
    }
    @Override
    public void onBackPressed() {
        if (mWeb.canGoBack()) {
            mWeb.goBack();
        } else {
            finish();
        }
    }

    public static void startActivityIntent(Context mContext, String momentId, String publisherId,String title) {
        Intent mIntent = new Intent(mContext, WebViewActivity_Gf.class);
        mIntent.putExtra("momentId", momentId);
        mIntent.putExtra("publisherId", publisherId);
        mIntent.putExtra("title", title);
        mContext.startActivity(mIntent);
    }

    private void setWebViewClient() {
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js交互，可以点击网页中按钮链接
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js可以打开新的页面
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(false);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage
    }


    @OnClick({R.id.ll_shoucang, R.id.ll_xihuan, R.id.ll_liuyan,R.id.ss,R.id.ali_pl,R.id.tv_sj,R.id.tv_rd})
    public void onClick(View view) {
        if(data==null){
            return;
        }
        switch (view.getId()) {
            case R.id.ll_shoucang:
                shoucang();
                break;
            case R.id.ll_xihuan:
                xihuan();
                break;
            case R.id.ll_liuyan:
                mMMyNestedScrollView.smoothScrollTo(0,ll1.getTop());
                break;
            case R.id.ss:
                showSoftInputFromWindow(null, "");
                break;
            case R.id.ali_pl:
                page++;
                getCommentMoment();
                break;
            case R.id.tv_rd:
                mTvRd.setTextColor(getResources().getColor(R.color.color_333333));
                mTvSj.setTextColor(getResources().getColor(R.color.color_999999));
                commentOrderType = "hot";
                page = 1;
                getCommentMoment();
                break;
            case R.id.tv_sj:
                mTvRd.setTextColor(getResources().getColor(R.color.color_999999));
                mTvSj.setTextColor(getResources().getColor(R.color.color_333333));
                commentOrderType = "lately";
                page = 1;
                getCommentMoment();
                break;
        }
    }
    private void shoucang() {
        if (data.isIsFavorite()) {
            HttpRequestUtils.postFavoritCancel_gf(data.getMomentId(), new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if (path.equals("0")) {
                        mTvShoucang.setText((data.getFavoriteCount() - 1) + "");
                        mIvShoucang.setImageDrawable(getResources().getDrawable(R.drawable.icon_share_sc_d));
                        data.setIsFavorite(false);
                        data.setFavoriteCount(data.getFavoriteCount() - 1);
                    }
                }
            });
        } else {
            HttpRequestUtils.postFavorit_gf( data.getMomentId(),
                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            if (path.equals("0")) {
                                mTvShoucang.setText((data.getFavoriteCount() + 1) + "");
                                mIvShoucang.setImageDrawable(getResources().getDrawable(R.drawable.icon_share_sc_dx));
                                data.setIsFavorite(true);
                                data.setFavoriteCount(data.getFavoriteCount() + 1);
                            }
                        }
                    });
        }
    }
    private void xihuan() {
        if (data.isIsLike()) {
            HttpRequestUtils.postLikeCancel_gf( data.getMomentId() + "", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if (path.equals("0")) {
                        data.setIsLike(false);
                        data.setLikeCount(data.getLikeCount() - 1);
                        mTvXihuan.setText(String.valueOf(data.getLikeCount()));
                        mIvXihuan.setImageResource(R.drawable.icon_xin_99_d);
                    }
                }
            });
        } else {
            HttpRequestUtils.postLike_gf(data.getMomentId() + "", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                public void succeed(String path) {
                    dismiss();
                    if (path.equals("0")) {
                        data.setIsLike(true);
                        data.setLikeCount(data.getLikeCount() + 1);
                        mTvXihuan.setText(String.valueOf(data.getLikeCount()));
                        mIvXihuan.setImageResource(R.drawable.ic_celect_xh_yes);
                    }
                }
            });
        }
    }
}