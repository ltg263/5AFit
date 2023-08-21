package com.jxkj.fit_5a.base;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.LoadDialog;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;

import butterknife.ButterKnife;


/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Bundle savedInstanceState;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(getContentView());
        Log.w("当前Activity","Activity:"+this.getClass().toString());
        HttpRequestUtils.okGo(this);
        if(this instanceof VideoActivity){
            ImmersionBar.with(this).statusBarDarkFont(false).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        }else {
            ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        }
        ButterKnife.bind(this);
        initViews();
    }

    protected abstract int getContentView();
    protected abstract void initViews();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void isShowTitle(){
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }


    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private LoadDialog dialog;
    public void show() {
        if(dialog==null){
            dialog = new LoadDialog(this, "");
        }

        if (!isFinishing()) {
            dialog.show();
        }
    }
    public void show(String show) {
        if(dialog==null){
            dialog = new LoadDialog(this, show);
        }

        if (!isFinishing()) {
            dialog.show();
        }
        dialog.getIv_close().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog_dismiss();
            }
        });
    }
    protected void dialog_dismiss(){
        if(PopupWindowLanYan.ble4Util!=null){
            PopupWindowLanYan.ble4Util.setLianJieZhong(false);
            PopupWindowLanYan.ble4Util.stopScan();
        }
    }
    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public boolean isDataInfoSucceed(Result result){
        if(result.getCode()==0){
            return true;
        }
        ToastUtils.showShortToast(this,result.getMesg());
        return false;
    }
    public boolean isDataInfoSucceed(ResultList result){
        if(result.getCode()==0){
            return true;
        }
        ToastUtils.showShortToast(this,result.getMesg());
        return false;
    }

    public boolean isLogin(){
        if (!SharedUtils.singleton().get(ConstValues.ISLOGIN,true)){
            return false;
        }
        return true;
    }
}
