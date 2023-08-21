package com.jxkj.fit_5a.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionFragment;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.LoadDialog;

import butterknife.ButterKnife;

/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */

public abstract class BaseFragment extends SimpleImmersionFragment {

    protected View mRootView;
    protected Gson mGson;
    private LoadDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        mRootView = inflater.inflate(getContentView(), null);
        ButterKnife.bind(this, mRootView);
        //解决fragment点击事件穿透问题
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        mGson = new Gson();
        initViews();
        return mRootView;
    }

    protected abstract int getContentView();

    protected abstract void initViews();

    public void ifNotLoginTurnToLogin(int status) {
        if (status == 101) {
            StringUtil.loginNo(getActivity());
            SharedUtils.singleton().clear();
            return;
        }

    }


    /**
     * 显示网络加载弹窗
     */
    public void show(Context context, String tips) {
        dialog = new LoadDialog(getActivity(), tips);
        if (!((Activity) context).isFinishing()) {
//            dialog.show();
        }
    }
    public void show(Context context) {
        if(dialog==null){
            dialog = new LoadDialog(context, "");
        }

        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }


    public boolean isDataInfoSucceed(Result result){
        if(result.getCode()==0){
            return true;
        }
        ToastUtils.showShortToast(getActivity(),result.getMesg());
        return false;
    }


    public boolean isDataInfoSucceed(ResultList result){
        if(result.getCode()==0){
            return true;
        }
        ToastUtils.showShortToast(getActivity(),result.getMesg());
        return false;
    }

}
