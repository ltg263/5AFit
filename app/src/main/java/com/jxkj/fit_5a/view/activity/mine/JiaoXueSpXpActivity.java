package com.jxkj.fit_5a.view.activity.mine;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok_jx;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.ExerciseRecordDetailsActivity_xl;
import com.jxkj.fit_5a.view.activity.exercise.TaskFinishActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionOneActivity;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseFinishActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.fragment.HomeTwoFragment;
import com.jxkj.fit_5a.view.search.ShoppingFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JiaoXueSpXpActivity extends BaseActivity {
    @BindView(R.id.rl_actionbar)
    View rl_actionbar;
    @BindView(R.id.mMyNestedScrollView)
    View mMyNestedScrollView;
    @BindView(R.id.ll)
    View ll;
    @BindView(R.id.mNsv)
    NestedScrollView relativeLayout;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_shoucang)
    TextView tv_shoucang;
    @BindView(R.id.tv_shoucang_ok)
    TextView tv_shoucang_ok;
    @BindView(R.id.mp_video_1)
    JzvdStdTikTok_jx mMpVideo;
    @BindView(R.id.tv_title_z)
    TextView mTvTitleZ;
    @BindView(R.id.tv_title_sf)
    TextView mTvTitleF;
    @BindView(R.id.tv_title_all)
    TextView mTvTitleAll;
    @BindView(R.id.tv_productTitle)
    TextView mTvProductTitle;
    @BindView(R.id.flowlayout)
    ShoppingFlowLayout flowLayout;
    String productId = null;
    @Override
    protected int getContentView() {
        return R.layout.activity_jiao_xue_spxq;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.w("11111onResume","++++++++++++++++++++");
            rl_actionbar.setVisibility(View.GONE);
            mMyNestedScrollView.setVisibility(View.GONE);
            ll.setVisibility(View.GONE);
        } else {
            rl_actionbar.setVisibility(View.VISIBLE);
            mMyNestedScrollView.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
            Log.w("11111onResume","---------------------");
        }
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("教学视频");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mIvBack.setOnClickListener(v -> finish());
        queryDeviceTypeLists();
    }

    private void queryDeviceTypeLists() {
        RetrofitUtil.getInstance().apiService()
                .getTeachingMomentDetails(getIntent().getStringExtra("momentId")
                        , getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TeachingMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TeachingMomentBean> result) {
                        if (isDataInfoSucceed(result)) {
                            productId = result.getData().getProductId();
                            mTvTitle.setText(result.getData().getTitle());
                            mTvTitleZ.setText(result.getData().getTitle());
                            mTvTitleF.setText(result.getData().getIntroduction());
                            Log.w("mTvTitleF","mTvTitleFX:"+mTvTitleF.getText());
//                            mTvTitleF.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    onClickTextMax();
//                                }
//                            });
                            if(result.getData().isIsFavorite()){
                                tv_shoucang.setVisibility(View.GONE);
                                tv_shoucang_ok.setVisibility(View.VISIBLE);
                            }
                            if(StringUtil.isNotBlank(result.getData().getProductTitle())){
                                mTvProductTitle.setText(result.getData().getProductTitle());
                            }
                            getPlayInfo(result.getData().getVideoUrl(), result.getData().getCoverImageUrl());
                            setFlowLayout(result.getData().getClassificationInfos());
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


    public void onClickTextMax() {
        //获取省略的字符数，0表示没和省略
        int ellipsisCount = mTvTitleF.getLayout().getEllipsisCount(mTvTitleF.getLineCount() - 1);
        mTvTitleF.getLayout().getEllipsisCount(mTvTitleF.getLineCount() - 1);
        //ellipsisCount>0说明没有显示全部，存在省略部分。
        if (ellipsisCount > 0) {
            //展示全部，按钮设置为点击收起。
            mTvTitleF.setMaxHeight(getResources().getDisplayMetrics().heightPixels);
            mTvTitleAll.setText("收起");
        } else {
            //显示2行，按钮设置为点击显示全部。
            mTvTitleAll.setText("显示全部");
            mTvTitleF.setMaxLines(3);
        }
    }

    private void setFlowLayout(List<TeachingMomentBean.ClassificationInfosBean> classificationInfos) {
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        for (int i = 0; i < classificationInfos.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(19, 5, 19, 5);
            tv.setText(classificationInfos.get(i).getClassificationName());
            tv.setMaxEms(10);
            tv.setSingleLine();
            tv.setBackgroundResource(R.drawable.jb_shape_theme_10);
            tv.setLayoutParams(layoutParams);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            flowLayout.addView(tv, layoutParams);
        }
    }
    private void getPlayInfo(String videoId, String imageUrl) {
        HttpRequestUtils.getPlayInfo(this, videoId, new HttpRequestUtils.PlayInfoInterface() {
            @Override
            public void succeed(Result<VideoPlayInfoBean> result) {
                if (result.getCode() == 0 && result.getData() != null
                        && result.getData().getPlayInfoList() != null && result.getData().getPlayInfoList().size() > 0) {
                    String playUrl = HttpRequestUtils
                            .initVideo(JiaoXueSpXpActivity.this, result.getData().getPlayInfoList()
                                    .get(0).getPlayURL(), videoId);
                    JZDataSource jzDataSource = new JZDataSource(playUrl, "");
                    jzDataSource.looping = true;
                    mMpVideo.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
                    Glide.with(mMpVideo.getContext()).load(imageUrl).into(mMpVideo.posterImageView);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMpVideo.startVideo();
    }

    private Handler mHandler = new Handler();

    public String getImgPath(TaskFinishActivity.Aba aba) {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = getBitmapByView(relativeLayout); // 获取图片
                MapExerciseFinishActivity.savePicture(bmp, "5Afit_img_1.jpg");// 保存图片
                if(bmp!=null){
                    aba.ok();
                }else{
                    ToastUtils.showShort("图片生成失败");
                }
            }
        }, 100);

        return "";
    }
    public final Bitmap getBitmapByView(NestedScrollView scrollView) {
        if (null == scrollView) {
            throw new IllegalArgumentException("parameter can't be null.");
        }
        scrollView.setDrawingCacheEnabled(true);
        int height = 0;
        Bitmap bitmap;
        for (int i = 0, s = scrollView.getChildCount(); i < s; i++) {
            height += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(android.R.drawable.screen_background_light);
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        Log.w("bitmap","bitmap:"+bitmap);
        return bitmap;
    }
    @OnClick({R.id.tv_shoucang,R.id.tv_fenxiang,R.id.tv_shoucang_ok,R.id.tv_productTitle, R.id.tv_ok,R.id.tv_title_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fenxiang:
                getImgPath(new TaskFinishActivity.Aba() {
                    @Override
                    public void ok() {
                        MapExerciseFinishActivity.shareData(JiaoXueSpXpActivity.this,"5Afit_img_1.jpg");
                    }
                });
                break;
            case R.id.tv_title_all:
                onClickTextMax();
                break;
            case R.id.tv_shoucang_ok:
                postTeachingFavoriteCancel();
                break;
            case R.id.tv_shoucang:
                postTeachingFavorite();
                break;
            case R.id.tv_productTitle:
                if(StringUtil.isNotBlank(productId)){
                    if(Integer.parseInt(productId) != 0){
                        ShoppingDetailsActivity.startActivity(this, productId);
                    }
                }
                break;
            case R.id.tv_ok:
                if (HomeTwoFragment.isYdQuanXian(this)) {
                    return;
                }
                if (!ConstValues_Ly.isA1) {
                    ToastUtils.showShort("请先链接设备");
                    return;
                }
                IntentUtils.getInstence().intent(this, MainActivity.class);
                break;
        }
    }
    private void postTeachingFavoriteCancel() {
        RetrofitUtil.getInstance().apiService()
                .postTeachingFavoriteCancel(getIntent().getStringExtra("momentId")
                        , getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            tv_shoucang_ok.setVisibility(View.GONE);
                            tv_shoucang.setVisibility(View.VISIBLE);
                            ToastUtils.showShort("取消成功");
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
    private void postTeachingFavorite() {
        RetrofitUtil.getInstance().apiService()
                .postTeachingFavorite(getIntent().getStringExtra("momentId")
                        , getIntent().getStringExtra("publisherId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            tv_shoucang.setVisibility(View.GONE);
                            tv_shoucang_ok.setVisibility(View.VISIBLE);
                            ToastUtils.showShort("收藏成功");
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
