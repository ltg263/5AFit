package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.TimeCounter;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.VerifyAppOauthQq;
import com.jxkj.fit_5a.entity.WxAccessTokenBean;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.wxapi.WXEntryActivity;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity{

    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.et_input_sjh_yes)
    TextView mEtInputSjhYes;
    @BindView(R.id.et_input_mm)
    EditText mEtInputMm;
    @BindView(R.id.et_input_mm_yes)
    TextView mEtInputMmYes;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.tv_login_yzm)
    TextView mTvLoginYzm;
    @BindView(R.id.tv_login_wjmm)
    TextView mTvLoginWjmm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.ll3)
    LinearLayout mLl3;
    @BindView(R.id.iv_select)
    ImageView iv_select;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.iv_login_al)
    ImageView iv_login_al;
    int loginType = 2;//密码登录
    private TimeCounter mTimeCounter;

    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginType = 2;
        mTvLoginYzm.setText("密码登录");
        mLl2.setVisibility(View.INVISIBLE);
        mLl3.setVisibility(View.VISIBLE);
        mTvLoginWjmm.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.iv_login_al,R.id.tv_login_yzm, R.id.tv_login_wjmm, R.id.iv_login_wx,R.id.iv_login_qq,
            R.id.iv_iconsole,R.id.tv_go_login, R.id.ll_go_zc,R.id.tv_go_yzm,R.id.iv_select,R.id.tv_info,R.id.tv_ysty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_al:
//                if(StringUtil.getLoginUserType().equals("1")){
//                    iv_icon.setImageResource(R.mipmap.ic_launcher);
//                    iv_login_al.setImageResource(R.drawable.icon_login_al);
//                    SharedUtils.singleton().put(ConstValues.LOGIN_USER_TYPE,"0");
//                }else{
//                    iv_icon.setImageResource(R.drawable.icon_login_allog);
//                    iv_login_al.setImageResource(R.drawable.icon_login_5a);
//                    SharedUtils.singleton().put(ConstValues.LOGIN_USER_TYPE,"1");
//                }
                break;
            case R.id.tv_login_yzm:
                if(loginType==1){
                    loginType = 2;
                    mTvLoginYzm.setText("密码登录");
                    mLl2.setVisibility(View.INVISIBLE);
                    mLl3.setVisibility(View.VISIBLE);
                    mTvLoginWjmm.setVisibility(View.INVISIBLE);
                }else{
                    loginType = 1;
                    mTvLoginYzm.setText("验证码登录");
                    mLl2.setVisibility(View.VISIBLE);
                    mLl3.setVisibility(View.INVISIBLE);
                    mTvLoginWjmm.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_login_wx:
                if(!iv_select.isSelected()){
                    ToastUtils.showShortToast(LoginActivity.this,"请先阅读并同意《用户协议》和《隐私政策》");
                    return;
                }
                createWXAPI();
                break;
            case R.id.iv_login_qq:
                if(!iv_select.isSelected()){
                    ToastUtils.showShortToast(LoginActivity.this,"请先阅读并同意《用户协议》和《隐私政策》");
                    return;
                }
                if(!ThirdLoginUtils.mTencent.isQQInstalled(this)){
                    ToastUtils.showShort("未安装QQ");
                    return;
                }
                show();
                ThirdLoginUtils.onClickLoginQQweb(this, new ThirdLoginUtils.ThirdLoginInterface() {
                    @Override
                    public void loginInterface(String token) {
                        if(StringUtil.isBlank(token)){
                            dismiss();
                            return;
                        }
                        postVerifyAppOauth(token);
                    }

                    @Override
                    public void onCancel() {
                        dismiss();
                    }
                });
                break;
            case R.id.iv_iconsole:
                if(!iv_select.isSelected()){
                    ToastUtils.showShortToast(LoginActivity.this,"请先阅读并同意《用户协议》和《隐私政策》");
                    return;
                }
                startIconsoleApp();
                break;
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
            case R.id.tv_login_wjmm:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
            case R.id.tv_go_login:
                userVerifyLogin();
                break;
            case R.id.ll_go_zc:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
    @Override

    public void onResume() {
        super.onResume();
        //这里的判断是为了区分如果不是不是从WXEntryActivity页面销毁重启的，不走下面的代码
        if (WXEntryActivity.resp != null) {
            if (WXEntryActivity.resp.getType() == 1) {
                String code = ((SendAuth.Resp) WXEntryActivity.resp).code;
                show();
                getWx_access_token(code);
            }
        }

//        if(StringUtil.getLoginUserType().equals("1")){
//            iv_icon.setImageResource(R.drawable.icon_login_allog);
//            iv_login_al.setImageResource(R.drawable.icon_login_al);
//        }else{
//            iv_icon.setImageResource(R.mipmap.ic_launcher);
//            iv_login_al.setImageResource(R.drawable.icon_login_5a);
//        }
    }

    /**
     *
     * @param code
     */
    private void getWx_access_token(String code) {
        RetrofitUtil.getInstance().apiService()
                .getWx_access_token("authorization_code",ConstValues.WX_APP_ID,ConstValues.WX_APP_SECRET,code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WxAccessTokenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WxAccessTokenBean result) {
                        if(StringUtil.isNotBlank(result.getErrcode())) {
                            dismiss();
                            return;
                        }
                        if(StringUtil.isNotBlank(result.getAccess_token())){
                            postVerifyAppOauthWx(result.getOpenid(),result.getAccess_token());
                        }else{
                            dismiss();
                            ToastUtils.showShort("授权失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    private void postVerifyAppOauthWx(String openId,String accessToken) {
        RetrofitUtil.getInstance().apiService()
                .postVerifyAppOauthWx("5afit",openId,accessToken)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VerifyAppOauthQq>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VerifyAppOauthQq> result) {
                        dismiss();
                        if(isDataInfoSucceed(result)){
                            thirdPartyLogin(result.getData());
                            WXEntryActivity.resp = null;
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

    IWXAPI api;

    private void createWXAPI() {
        if (api ==null) {
            api = WXAPIFactory.createWXAPI(LoginActivity.this,null);
            api.registerApp(ConstValues.WX_APP_ID);
        }

        if (!api.isWXAppInstalled()) {
            ToastUtils.showShort("您手机尚未安装微信，请安装后再登录");
            return;
        }
        SendAuth.Req req =new SendAuth.Req();
        req.scope ="snsapi_userinfo";
        req.state ="wechat_sdk_jj_login_state";
        //官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），
        // 建议第三方带上该参数，可设置为简单的随机数加session进行校验
        api.sendReq(req);
    }

    private void startIconsoleApp() {
        Uri uri = Uri.parse("iconsoleplus://3ptoken?rights=read_profile,read_workouts&linkback=FiveAFitness://3ptoken.result&appname=5AFit");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify if app XYZ has this screen path
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            startActivity(mapIntent);
        }else{
            ToastUtils.showShort("请先安装iConsole");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("123", "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,ThirdLoginUtils.loginListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void userVerifyLogin() {
        String sjh = mEtInputSjh.getText().toString();
        String yzm = mEtInputYzm.getText().toString();
        String mm = mEtInputMm.getText().toString();

        if(StringUtil.isBlank(sjh)){
            ToastUtils.showShort("填写不完整");
            return;
        }
        if(loginType == 1) {
            yzm = null;
            if(StringUtil.isBlank(mm)){
                ToastUtils.showShort("填写不完整");
                return;
            }
        }
        if(loginType == 2){
            mm = null;
            if(StringUtil.isBlank(yzm)){
                ToastUtils.showShort("填写不完整");
                return;
            }
        }
        if(!iv_select.isSelected()){
            ToastUtils.showShort("请先阅读并同意《用户协议》和《隐私政策》");
            return;
        }
        show();
        String finalMm = mm;
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<LoginInfo>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.userVerifyLogin_al(3, sjh, mm, yzm);
        }else {
            mObservable = mApiService.userVerifyLogin(3, sjh, mm, yzm);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginInfo> result) {
                        if(isDataInfoSucceed(result)){
                            if(finalMm!=null){
                                SharedUtils.singleton().put(ConstValues.USER_PASSWORD, finalMm);
                            }
                            saveUserInfo(result.getData());
                            if(result.getData()==null || result.getData().getUserPermissionBaseDTO()==null
                                    || Integer.parseInt(result.getData().getUserPermissionBaseDTO().getGender())==0){
                                IntentUtils.getInstence().intent(LoginActivity.this, SetUserXbActivity.class,"type",0);
                            }else{
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
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


    public static void saveUserInfo(LoginInfo data) {
        SharedUtils.singleton().put(ConstValues.ISLOGIN,true);
        SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+data.getTokenId());
        SharedUtils.singleton().put(ConstValues.USER_PHONE,data.getUserPermissionBaseDTO().getUserNo());
        SharedUtils.singleton().put(ConstValues.USERID,data.getUserPermissionBaseDTO().getId());
        SharedUtils.singleton().put(ConstValues.USER_NAME,data.getUserPermissionBaseDTO().getNickName());
        SharedUtils.singleton().put(ConstValues.USER_IMG,data.getUserPermissionBaseDTO().getAvatar());
        SharedUtils.singleton().put(ConstValues.USER_AGE,data.getUserPermissionBaseDTO().getAge());
        SharedUtils.singleton().put(ConstValues.USER_GENDER,data.getUserPermissionBaseDTO().getGender());
        if(data.getUserPermissionBaseDTO().getUserType()==2){
            SharedUtils.singleton().put(ConstValues.LOGIN_USER_TYPE,"1");
        } else {
            SharedUtils.singleton().put(ConstValues.LOGIN_USER_TYPE,"0");
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

    private void postVerifyAppOauth(String token) {
        RetrofitUtil.getInstance().apiService()
                .postVerifyAppOauth("qqweb",token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VerifyAppOauthQq>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VerifyAppOauthQq> result) {
                        dismiss();
                        if(isDataInfoSucceed(result)){
                            thirdPartyLogin(result.getData());
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

    private void thirdPartyLogin(VerifyAppOauthQq data) {
        if(data.isBindFlag()){
            SharedUtils.singleton().put(ConstValues.ISLOGIN,true);
            SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+data.getTokenId());
            SharedUtils.singleton().put(ConstValues.USER_PHONE,data.getUserBase().getUserNo());
            SharedUtils.singleton().put(ConstValues.USERID,data.getUserBase().getId());
            SharedUtils.singleton().put(ConstValues.USER_NAME,data.getUserBase().getNickName());
            SharedUtils.singleton().put(ConstValues.USER_IMG,data.getUserBase().getAvatar());
            SharedUtils.singleton().put(ConstValues.USER_AGE,data.getUserBase().getAge());
            SharedUtils.singleton().put(ConstValues.USER_GENDER,data.getUserBase().getGender());
            if(Double.parseDouble(data.getUserBase().getWeight()) ==0
                    || Double.parseDouble(data.getUserBase().getHeight()) ==0){
                IntentUtils.getInstence().intent(LoginActivity.this, SetUserXbActivity.class,"type",0);
            }else{
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }else{
            SharedUtils.singleton().put(ConstValues.THIRD_LOGIN_BIND_INFO,data.getThirdLoginBindInfo());
            IntentUtils.getInstence().intent(LoginActivity.this,LoginBindPhoneActivity.class);
        }
    }
}
