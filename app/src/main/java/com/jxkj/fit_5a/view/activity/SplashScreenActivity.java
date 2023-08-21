package com.jxkj.fit_5a.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.conpoment.utils.StatusBarUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;
import com.jxkj.fit_5a.view.activity.login_other.SetUserXbActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZMediaSystem;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SplashScreenActivity  extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_start_one)
    TextView tv_start_one;
    @BindView(R.id.tv_start)
    TextView tv_start;
    @BindView(R.id.rl_actionbar)
    RelativeLayout rl_actionbar;
    @BindView(R.id.mp_video)
    JzvdStdTikTok mMpVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去掉信息栏
        HttpRequestUtils.okGo(this);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.splashscreen);
//        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        ButterKnife.bind(this);
        initViews();

        SharedUtils.singleton().put("yuyinbobao_numliang", 5);
    }

    boolean isTg = false;
    private void initViews() {
        mBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        initBanner();
        if(StringUtil.isNotBlank(SharedUtils.getToken()) && isLogin()){
            getUserDetail();
        }
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTg = true;
                if(mMpVideo.getVisibility()==View.VISIBLE){
                    mMpVideo.setPlayState();
                }
                startUi();
            }
        });

        tv_start_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUi();
            }
        });

    }

    public boolean isLogin(){
        if (!SharedUtils.singleton().get(ConstValues.ISLOGIN,true)){
            return false;
        }
        return true;
    }
    private void startUi() {
        SharedUtilsNot.singletonNotClear().put(ConstValues.ISNOTFIRST,true);
        if (StringUtil.isBlank(SharedUtils.getToken()) || !isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        if(Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_HEIGHT,"0"))==0
            || Double.parseDouble(SharedUtils.singleton().get(ConstValues.USER_WEIGHT,"0"))==0){
            IntentUtils.getInstence().intent(this, SetUserXbActivity.class,"type",0);
        }else{
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
    CountDownTimer mCountDownTimer;
    private void initBanner() {
        boolean isFirst = SharedUtilsNot.singletonNotClear().get(ConstValues.ISNOTFIRST,false);
        if(!isFirst){
            List<Integer> list = new ArrayList<>();
            list.add(R.mipmap.icon_yindao_1);
            list.add(R.mipmap.icon_yindao_2);
            list.add(R.mipmap.icon_yindao_3);
//            rl_actionbar.setBackgroundResource(R.mipmap.icon_yindao_1);
            //底部提示语  快速咨询为超链
            SpannableString str = new SpannableString(getString(R.string.home_ts));
            //设置属性
            str.setSpan(new MyCheckTextView(this,0), 61, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new MyCheckTextView(this,1), 68, 74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            final TextView message = new TextView(this);
            Linkify.addLinks(str, Linkify.WEB_URLS);
            message.setText(str);
            message.setPadding(30,10,30,10);
            message.setMovementMethod(LinkMovementMethod.getInstance());


//            DialogUtils.showUnificationDialog(this, "亲爱的5AFit用户",message, "不同意","同意",
//                    new DialogUtils.UnificationDialogInterface() {
//                        @Override
//                            if(pos.equals("0")){
//                                initBannerDh(list);
//                            }
//                        }
//                    });

            AlertDialog doalog =new AlertDialog.Builder(this)
                    .setTitle("亲爱的5AFit用户")
//                    .setMessage(str)
                    .setView(message)
                    .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainApplication.initUMShare(SplashScreenActivity.this);
                            initBannerDh(list);
                        }
                    })
                    .setNegativeButton("不同意", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            finish();
                        }
                    }).create();
            doalog.setCanceledOnTouchOutside(false);
            doalog.show();
            return;
        }
        tv_start.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isTg){
                    if(mMpVideo.getVisibility()==View.VISIBLE){
                        mMpVideo.setPlayState();
                    }
                    startUi();
                }
            }
        },5000);

        mCountDownTimer = new CountDownTimer(6 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_start.setText(millisUntilFinished/1000+" 跳过");
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer.start();

        String imageUrl = SharedUtilsNot.singletonNotClear().get(ConstValues.IMAGE_URL_FIRST,"");
        String imageUrlFType = SharedUtilsNot.singletonNotClear().get(ConstValues.IMAGE_URL_FIRST_TYPE,"");
        Log.w("imageUrlF","imageUrlF:"+imageUrl);
        Log.w("imageUrlF","imageUrlFType:"+imageUrlFType);
        if(StringUtil.isNotBlank(imageUrlFType)){
            if(imageUrlFType.equals("2")){
                List<String> list = new ArrayList<>();
                list.add(imageUrl);
                initBannerDh(list);
            }else{
                mBanner.setAlpha(0);
                mMpVideo.setVisibility(View.VISIBLE);
                mMpVideo.setMap(true);
                if(mMpVideo.mediaInterface==null){
                    mMpVideo.mediaInterface = new JZMediaSystem(mMpVideo);
                }
                mMpVideo.mediaInterface.setVolume(0f,0f);
                Glide.with(SplashScreenActivity.this).load(imageUrl).into(mMpVideo.posterImageView);

                String playUrl = HttpRequestUtils.initVideo(SplashScreenActivity.this
                        ,SharedUtilsNot.singletonNotClear().get(ConstValues.IMAGE_URL_FIRST_PLAY,""),imageUrlFType);

                mMpVideo.setUp(playUrl, MyVideoPlayer.PLAY_STATE_EXERCISE, MyVideoPlayer.STATE_NORMAL);
                mMpVideo.setPlayState();
            }
        }else{
            List<Integer> list = new ArrayList<>();
            list.add(R.mipmap.icon_yindao_0);
            initBannerDh(list);
        }
    }

    private void initBannerDh(List<?> list) {
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Log.i("tag", "你点了第" + position + "张轮播图:" + titles.get(position));
            }
        });
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==2) {
                    tv_start_one.setVisibility(View.VISIBLE);
                    mBanner.isAutoPlay(false);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.w("tag", "onPageScrollStateChanged当前下标：" + position);
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置图片加载器
        mBanner.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View imageView) {
                if(list.size()>1){
                    ((ImageView) imageView).setScaleType(ImageView.ScaleType.FIT_CENTER);
                }else{
                    ((ImageView) imageView).setScaleType(ImageView.ScaleType.FIT_XY);
                }
                Glide.with(context).load(path).into((ImageView) imageView);
            }

            @Override
            public View createImageView(Context context) {
                return null;
            }
        });
        //设置图片集合
        mBanner.setImages(list);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    private void getUserDetail() {
        RetrofitUtil.getInstance().apiService()
                .getUserDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserDetailData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserDetailData> result) {
                        if(result.getCode()==21001
                                && result.getMesg().equals("无效token")
                                && isLogin()){
                            HttpRequestUtils.userVerifyLogin();
                        }

                        if (result.getCode() == 0&& result.getData()!=null) {
                            SharedUtils.singleton().put(ConstValues.USER_PHONE,result.getData().getUserNo());
                            SharedUtils.singleton().put(ConstValues.USERID,result.getData().getId());
                            SharedUtils.singleton().put(ConstValues.USER_NAME,result.getData().getNickName());
                            SharedUtils.singleton().put(ConstValues.USER_IMG,result.getData().getAvatar());
                            SharedUtils.singleton().put(ConstValues.USER_AGE,result.getData().getAge());
                            SharedUtils.singleton().put(ConstValues.USER_GENDER,result.getData().getGender());
                            SharedUtils.singleton().put(ConstValues.USER_HEIGHT,result.getData().getHeight());
                            SharedUtils.singleton().put(ConstValues.USER_WEIGHT,result.getData().getWeight());
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCountDownTimer!=null){
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mCountDownTimer!=null){
            mCountDownTimer.cancel();
        }
    }
    public class MyCheckTextView extends ClickableSpan {
        private Context mContext;
        private int pos;
        public MyCheckTextView(Context mContext,int pos) {
            this.pos = pos;
            this.mContext = mContext;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if(pos==0){
                WebViewActivity.startActivityIntent(mContext,ConstValues.USER_XY_URL,"使用协议");
            }else{
                WebViewActivity.startActivityIntent(mContext,ConstValues.USER_YSZC_URL,"隐私政策");
            }
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
//        设置文本颜色
            ds.setColor(Color.parseColor("#2044FF"));
//         超链接形式的下划线，false 表示不显示下划
            ds.setUnderlineText(false);

        }
    }

}
