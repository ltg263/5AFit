package com.jxkj.fit_5a.conpoment.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.tencent.connect.auth.AuthAgent;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.HashMap;

import static com.tencent.connect.common.Constants.KEY_ENABLE_SHOW_DOWNLOAD_URL;
import static com.tencent.connect.common.Constants.KEY_QRCODE;
import static com.tencent.connect.common.Constants.KEY_RESTORE_LANDSCAPE;
import static com.tencent.connect.common.Constants.KEY_SCOPE;

public class ThirdLoginUtils {

    public static Tencent mTencent = Tencent.createInstance(ConstValues.APP_ID_TENCENT, MainApplication.getContext(), ConstValues.APP_AUTHORITIES);
    private static ThirdLoginInterface loginInterface;
    public static void onClickLoginQQweb(Activity mActivity,ThirdLoginInterface loginInterface) {
        ThirdLoginUtils.loginInterface = loginInterface;
        if(mTencent.isSessionValid()){
            mTencent.logout(mActivity);
        }
        if (!mTencent.isSessionValid()) {
            // 强制扫码登录
            mActivity.getIntent().putExtra(AuthAgent.KEY_FORCE_QR_LOGIN, false);
            HashMap<String, Object> params = new HashMap<String, Object>();
            if (mActivity.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
                params.put(KEY_RESTORE_LANDSCAPE, true);
            }
            params.put(KEY_SCOPE, "all");
            params.put(KEY_QRCODE, false);
            params.put(KEY_ENABLE_SHOW_DOWNLOAD_URL, false);
            mTencent.login(mActivity,  loginListener, params);
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            ToastUtils.showShortToast(mActivity,"QQ登录");
//            if (isServerSideLogin) { // Server-Side 模式的登录, 先退出，再进行SSO登录
//
//                mTencent.login(this, "all", loginListener);
//                isServerSideLogin = false;
//                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
//                return;
//            }
//            mTencent.logout(this);
//            // 第三方也可以选择注销的时候不去清除第三方的targetUin/targetMiniAppId
//            saveTargetUin("");
//            saveTargetMiniAppId("");
//            updateUserInfo();
//            updateLoginButton();
        }
    }
    private static void initOpenidAndToken(JSONObject jsonObject,ThirdLoginInterface loginInterface) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            Log.w("-->>>","token:"+token);
            Log.w("-->>>","expires:"+expires);
            Log.w("-->>>","openId:"+openId);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
                loginInterface.loginInterface(token.trim());
            }
        } catch(Exception e) {
            loginInterface.loginInterface(null);
            ToastUtils.showShortToast(MainApplication.getContext(),"授权失败："+e.toString());
        }
    }


    public interface ThirdLoginInterface {
        /**
         * 确定
         */
        public void loginInterface(String token);
        public void onCancel();
    }
    public static IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:++++++" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values,loginInterface);
        }

        @Override
        public void onCancel() {
            loginInterface.onCancel();
        }

        @Override
        public void onError(UiError e) {
            super.onError(e);
            loginInterface.onCancel();
        }
    };
    private static class BaseUiListener extends DefaultUiListener {

        @Override
        public void onComplete(Object response) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:response"+response);
            if (null == response) {
                ToastUtils.showShortToast(MainApplication.getContext(),"授权失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (jsonResponse.length() == 0) {
                ToastUtils.showShortToast(MainApplication.getContext(),"授权失败");
                return;
            }
            ToastUtils.showShortToast(MainApplication.getContext(),"授权成功");
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:errorDetail"+e.errorDetail);
            ToastUtils.showShortToast(MainApplication.getContext(),"onError: " + e.errorDetail);
        }

        @Override
        public void onCancel() {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:onCancel");
        }
    }
}
