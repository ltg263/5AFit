package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhuXiaoActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_zhuxiao;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("注 销");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
    }

    @OnClick({R.id.tv_ok, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                DialogUtils.showUnificationDialog(this, "注销账号","您确定要注销账号吗？", "注销",true,
                        new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                zhuxiaozhangh();
                            }
                        });
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void zhuxiaozhangh() {
        DialogUtils.showUnificationDialogZx(this,new DialogUtils.UnificationDialogInterface() {
            @Override
            public void bntClickListener(String str) {
                if(!str.equals("-1")){
                    userCancellation(str);
                }
            }
        });

    }

    private void userCancellation(String str) {
        show();
        RetrofitUtil.getInstance().apiService()
                .userCancellation(SharedUtils.singleton().get(ConstValues.USER_PHONE,""),str)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        dismiss();
                        if(result.getCode()==0){
                                if(ThirdLoginUtils.mTencent!=null){
                                    ThirdLoginUtils.mTencent.logout(ZhuXiaoActivity.this);
                                }
                                startActivity(new Intent(ZhuXiaoActivity.this, LoginActivity.class));
                                MainApplication.getContext().AppExit();
                                SharedUtils.singleton().clear();
                                finish();
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
