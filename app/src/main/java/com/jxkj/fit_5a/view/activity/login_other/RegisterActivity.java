package com.jxkj.fit_5a.view.activity.login_other;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RegisterActivity extends BaseActivity {


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
    @BindView(R.id.iv_select)
    ImageView iv_select;

    private TimeCounter mTimeCounter;
    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.tv_go_yzm, R.id.tv_go_register, R.id.ll_go_dl,R.id.iv_select,R.id.tv_info,R.id.tv_ysty})
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
            case R.id.tv_go_register:
//
                userVerifyRegister();
                break;
            case R.id.ll_go_dl:
                finish();
                break;
            case R.id.iv_select:
                iv_select.setSelected(!iv_select.isSelected());
                break;
            case R.id.tv_info:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_XY_URL,"使用协议");
                break;
            case R.id.tv_ysty:
                WebViewActivity.startActivityIntent(this, ConstValues.USER_YSZC_URL,"隐私政策");
                break;
        }
    }

    private void userVerifyRegister() {
        String sjh = mEtInputSjh.getText().toString();
        String yzm = mEtInputYzm.getText().toString();
        String mm = mEtInputMm.getText().toString();
        if(StringUtil.isBlank(sjh) ||StringUtil.isBlank(yzm) ||StringUtil.isBlank(mm)){
            ToastUtils.showShort("填写不完整");
            return;
        }

        if(!iv_select.isSelected()){
            ToastUtils.showShortToast(RegisterActivity.this,"请先阅读并同意《使用协议》和《隐私政策》");
            return;
        }
        show();
        RetrofitUtil.getInstance().apiService()
                .userVerifyRegister(3,sjh,mm,yzm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginInfo> result) {
                        dismiss();
                        if(isDataInfoSucceed(result)){
                            SharedUtils.singleton().put(ConstValues.USER_PASSWORD, mm);
                            LoginActivity.saveUserInfo(result.getData());
                            IntentUtils.getInstence().intent(RegisterActivity.this, SetUserXbActivity.class,"type",0);
                            finish();
                        }
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
