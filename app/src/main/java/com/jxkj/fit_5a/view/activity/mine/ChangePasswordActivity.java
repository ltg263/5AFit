package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordActivity extends BaseActivity {


    @BindView(R.id.et_input_sjh)
    TextView mEtInputSjh;
    @BindView(R.id.et_input_sjh_yes)
    TextView mEtInputSjhYes;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.et_input_mm_yes)
    TextView mEtInputMmYes;
    @BindView(R.id.et_input_mm1)
    EditText mEtInputMm1;
    @BindView(R.id.et_input_mm_yes1)
    TextView mEtInputMmYes1;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_change_password;
    }

    @Override
    protected void initViews() {
        mEtInputSjh.setText(SharedUtils.singleton().get(ConstValues.USER_PHONE,""));
    }

    @OnClick({R.id.tv_go_find, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_find:
                userVerifyLogin();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void userVerifyLogin() {
        String mm = mEtInputMm.getText().toString();
        String mm1 = mEtInputMm1.getText().toString();
        if(StringUtil.isBlank(mm) ||StringUtil.isBlank(mm1)){
            ToastUtils.showShort("填写不完整");
            return;
        }
        if(mm.equals(mm1)){
            ToastUtils.showShort("旧密码与新密码相同");
            return;
        }
        show();
        RetrofitUtil.getInstance().apiService()
                .userChangePassword(mm,mm1)
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
                            ChangePasswordActivity.this.finish();
                            ToastUtils.showShort("修改成功");
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
