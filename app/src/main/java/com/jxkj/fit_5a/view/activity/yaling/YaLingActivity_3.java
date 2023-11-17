package com.jxkj.fit_5a.view.activity.yaling;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.SportLogDetailBean;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseFinishActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class YaLingActivity_3 extends BaseActivity {
    @BindView(R.id.mNsv)
    NestedScrollView relativeLayout;

    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_trainingName)
    TextView tv_trainingName;
    @BindView(R.id.tv_sportType)
    TextView tv_sportType;
    @BindView(R.id.tv_kll_v)
    TextView tv_kll_v;
    @BindView(R.id.tv_sj_v)
    TextView tv_sj_v;
    @BindView(R.id.tv_zhongliang_v)
    TextView tv_zhongliang_v;
    @BindView(R.id.tv_num_v)
    TextView tv_num_v;

    @Override
    protected int getContentView() {
        return R.layout.activity_yaling_3;
    }

    @Override
    protected void initViews() {
        geSportLogDetails();
    }
    private void geSportLogDetails() {
        show();
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<SportLogDetailBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.geSportLogDetails_al(getIntent().getStringExtra("data_id"));
        }else {
            mObservable = mApiService.geSportLogDetails(getIntent().getStringExtra("data_id"));
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SportLogDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SportLogDetailBean> result) {
                        initData(result.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();

                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    private void initData(SportLogDetailBean data) {
        tv_num.setText(StringUtil.getValue(data.getDistance()));
        tv_num_v.setText(StringUtil.getValue(data.getDistance()));
        tv_kll_v.setText(data.getCalories());
        tv_sj_v.setText(StringUtil.getTimeGeShiYw(Long.parseLong(data.getDuration())));
        String estParams = data.getExtParams();
        if(StringUtil.isNotBlank(estParams)){
            try {
                JSONObject mJSONObject = new JSONObject(estParams);
                tv_trainingName.setText(mJSONObject.getString("trainingName"));
                tv_sportType.setText(mJSONObject.getString("sportType"));
                tv_zhongliang_v.setText(mJSONObject.getString("dumbWeight"));
                if(StringUtil.isNotBlank(mJSONObject.getString("isLBUnit"))){
                    String isLBUnit = mJSONObject.getString("isLBUnit");
                    if(isLBUnit.equals("1")){
                        isLBUnit = "lb";
                    }else{
                        isLBUnit = "kg";
                    }
                    tv_zhongliang_v.setText(mJSONObject.getString("dumbWeight")+isLBUnit);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_go_ffhd, R.id.tv_go_xxyx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_go_xxyx:
                finish();
                break;
            case R.id.tv_go_ffhd:
                getImgPath(new Aba() {
                    @Override
                    public void ok() {
                        MapExerciseFinishActivity.shareData(YaLingActivity_3.this, "5Afit_img_1.jpg");
                    }
                });
                break;
        }
    }

    private Handler mHandler = new Handler();

    public String getImgPath(Aba aba) {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = getBitmapByView(relativeLayout); // 获取图片
                MapExerciseFinishActivity.savePicture(bmp, "5Afit_img_1.jpg");// 保存图片
                if (bmp != null) {
                    aba.ok();
                } else {
                    ToastUtils.showShort("图片生成失败");
                }
            }
        }, 100);

        return "";
    }


    public interface Aba {
        void ok();
    }

    /**
     * 截取scrollview的屏幕
     *
     * @param scrollView
     * @return
     */
    public Bitmap getBitmapByView(NestedScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();//获取scrollView的屏幕高度
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);//把创建的bitmap放到画布中去
        scrollView.draw(canvas);//绘制canvas 画布
        return bitmap;
    }

    //0 定数计时  1定时计数 2自由模式
    public static void intentActivity(BaseActivity mContext, PostUser.SportLogInfo sportLogInfo) {
        mContext.show();
        HttpRequestUtils.psotUserSportLog(sportLogInfo, new HttpRequestUtils.LoginInterface() {
            @Override
            public void succeed(String data_id) {
                mContext.dismiss();
                Log.w("data_id","data_id"+data_id);
                if(StringUtil.isNotBlank(data_id)){
                    Intent mIntent = new Intent(mContext, YaLingActivity_3.class);
                    mIntent.putExtra("data_id",data_id);
                    mContext.startActivity(mIntent);
                    mContext.finish();
                }else {
                    mContext.finish();
                }

            }
        });
    }
    //0 定数计时  1定时计数 2自由模式
    public static void intentActivityXd(Activity mContext, String data_id) {
        Intent mIntent = new Intent(mContext, YaLingActivity_3.class);
        mIntent.putExtra("data_id",data_id);
        mContext.startActivity(mIntent);
    }

}
