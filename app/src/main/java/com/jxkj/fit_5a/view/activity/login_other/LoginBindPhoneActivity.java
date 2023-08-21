package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.LoginInfo;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginBindPhoneActivity extends BaseActivity {
    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.tv_login)
    TextView tv_login;
    private TimeCounter mTimeCounter;
    String type;

    @Override
    protected int getContentView() {
        return R.layout.activity_login_bind_phone;
    }

    @Override
    protected void initViews() {
        type = getIntent().getStringExtra("type");
        if(StringUtil.isNotBlank(type) && type.equals("0")){
            tv_login.setText("重新绑定");
        }
    }


    @OnClick({R.id.tv_go_yzm, R.id.tv_go_login, R.id.tv_login})
    public void onClick(View view) {
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
            case R.id.tv_go_login:
                String verify = mEtInputYzm.getText().toString();
                String phone = mEtInputSjh.getText().toString();
                String mm = mEtInputMm.getText().toString();
                if(StringUtil.isBlank(verify)||StringUtil.isBlank(phone) || StringUtil.isBlank(mm)){
                    ToastUtils.showShort("填写不能为空");
                    return;
                }
                if(!TextUtils.isEmpty(phone)&&phone.length()==11){
                    userThirdBind(verify,phone,mm);
                }else{
                    ToastUtils.showShort("请输入正确的手机号");
                }
                break;
            case R.id.tv_login:
                finish();
                break;
        }
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
    private void userThirdBind(String verify,String phone,String mm) {
//        if(StringUtil.phone){
//
//        }
        RetrofitUtil.getInstance().apiService()
                .userThirdBind(3,phone,verify,mm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginInfo> result) {
                        if (isDataInfoSucceed(result)) {
                            LoginActivity.saveUserInfo(result.getData());
                            IntentUtils.getInstence().intent(LoginBindPhoneActivity.this, SetUserXbActivity.class,"type",0);
                            finish();
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
