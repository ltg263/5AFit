package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindPasswordActivity extends BaseActivity {


    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.et_input_sjh_yes)
    TextView mEtInputSjhYes;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.et_input_mm_yes)
    TextView mEtInputMmYes;
    @BindView(R.id.ll_go_dl)
    LinearLayout mLlGoDl;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    private TimeCounter mTimeCounter;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_find_password;
    }

    @Override
    protected void initViews() {
        if(StringUtil.getLoginUserType().equals("1")){
            iv_icon.setImageResource(R.drawable.icon_login_allog);
        }
    }

    @OnClick({R.id.tv_go_yzm, R.id.tv_go_find, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_yzm:
                String sjh = mEtInputSjh.getText().toString();
                if(!TextUtils.isEmpty(sjh)&&sjh.length()==11){
                    mTimeCounter = new TimeCounter(60 * 1000, 1000, mTvGoYzm);
                    mTimeCounter.start();
                    goGetYzm(sjh);
                }else{
                    ToastUtils.showShort("请输入正确的手机号");
                }
                break;
            case R.id.tv_go_find:
                userVerifyLogin();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void userVerifyLogin() {
        String sjh = mEtInputSjh.getText().toString();
        String yzm = mEtInputYzm.getText().toString();
        String mm = mEtInputMm.getText().toString();
        if(StringUtil.isBlank(sjh) ||StringUtil.isBlank(yzm) ||StringUtil.isBlank(mm)){
            ToastUtils.showShort("填写不完整");
            return;
        }
        show();
        RetrofitUtil.getInstance().apiService()
                .userForgetPassword(mm,sjh,yzm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        dismiss();
                        if(isDataInfoSucceed(result)){
                            startActivity(new Intent(FindPasswordActivity.this, WelcomeLoginActivity.class));
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

    private void goGetYzm(String sjh) {
        RetrofitUtil.getInstance().apiService()
                .getVerifyCode(sjh,0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("发送成功");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimeCounter!=null){
            mTimeCounter.cancel();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mTimeCounter!=null){
            mTimeCounter.cancel();
        }
    }
}
