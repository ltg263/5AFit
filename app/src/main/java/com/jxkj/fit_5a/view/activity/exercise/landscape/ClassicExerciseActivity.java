package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassicExerciseActivity extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.ll)
    LinearLayout mLl;
    boolean isSuo = false;
    String mapId;
    String boxId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_classic_exercise);
        ButterKnife.bind(this);
        initViews();
    }
    PopupWindowTopicUtils window;
    private void initViews() {
        mapId = getIntent().getStringExtra("mapId");
        getMapDetails();
//        DialogUtils.showDialogClass(ClassicExerciseActivity.this, 1, new DialogUtils.DialogLyInterface() {
//            @Override
//            public void btnConfirm() {
//
//            }
//        });

        mIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIv3.getVisibility() == View.VISIBLE){
                    mIv3.setVisibility(View.GONE);
                    mIv4.setVisibility(View.GONE);
                }else{
                    mIv3.setVisibility(View.VISIBLE);
                    mIv4.setVisibility(View.VISIBLE);
                }
            }
        });
        mIv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSuo){
                    isSuo = false;
                    mIv2.setImageDrawable(ClassicExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_9));
                    if(window!=null && window.isShowing()){
                        return;
                    }
                    window = new PopupWindowTopicUtils(ClassicExerciseActivity.this, (topicId, str) -> {
                    });
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置
                }else{
                    isSuo = true;
                    mIv2.setImageDrawable(ClassicExerciseActivity.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                    if(window!=null && window.isShowing()){
                        window.dismiss();
                    }
                }
            }
        });
        mIv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mIv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogUtils.showDialogOutRoom(ClassicExerciseActivity.this, new DialogUtils.DialogLyInterface() {
//                    @Override
//                    public void btnConfirm() {
//                        gameGivenUp();
//                    }
//
//                });

                DialogUtils.showUnificationDialog(ClassicExerciseActivity.this, "结束运动","您确定要结束运动吗？", "结束",true,
                         new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                gameGivenUp();
                            }
                        });
            }
        });
    }

    public static void intentStartActivity(Context mContext,String mapId) {
        Intent intent = new Intent(mContext, ClassicExerciseActivity.class);
        intent.putExtra("mapId",mapId);
        mContext.startActivity(intent);
    }


    private void gameGivenUp() {
        RetrofitUtil.getInstance().apiService()
                .gameGivenUp(getIntent().getStringExtra("mapId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0) {
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
    private void getMapDetails() {
        RetrofitUtil.getInstance().apiService()
                .getMapDetails(mapId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if(result.getCode()==0){
//                            List<MapDetailsBean.BoxsBean> boxs = result.getData().getBoxs();
//                            if(boxs!=null && boxs.size()>0){
//                                MapDetailsBean.BoxsBean.UserBoxLogBean userBoxLog = boxs.get(0).getUserBoxLog();
//                                if(userBoxLog!=null && StringUtil.isNotBlank(userBoxLog.getBoxId())){
//                                    boxId = userBoxLog.getBoxId();
//                                }
//                            }
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
