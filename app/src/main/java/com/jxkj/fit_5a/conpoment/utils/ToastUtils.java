package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.jxkj.fit_5a.app.MainApplication;

/**
 * author : LiuJie
 * date   : 2020/5/2915:01
 */
public class ToastUtils {
    private static ToastUtils instance;
    private static Toast mToast;

    public static ToastUtils getInstance(Context mContext) {
        if (null == instance) {
            synchronized (ToastUtils.class) {
                if (null == instance) {
                    instance = new ToastUtils(mContext);
                }
            }
        }
        return instance;
    }

    private ToastUtils(Context mContext) {
        if (null == mContext) {
            throw new NullPointerException("此类没有进行初始化");
        }
        if (null == mToast) {
            mToast = Toast.makeText(mContext, null, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    public static void showShort(final String message){
            mToast = Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
    }

    public static void showShortToast(final Context context, final String message){
        if (mToast == null){
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
