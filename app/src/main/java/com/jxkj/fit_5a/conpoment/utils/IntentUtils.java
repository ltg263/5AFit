package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.Serializable;

/**
 * intent的跳转
 *
 * @author Administrator
 */
public class IntentUtils {
    public static IntentUtils instence;

    public static IntentUtils getInstence() {
        if (null == instence) {
            instence = new IntentUtils();
        }
        return instence;
    }

    private IntentUtils() {

    }

    /**
     * 不带参数的跳转
     *
     * @param fromContext
     * @param cls         泛型
     */
    public void intent(Context fromContext, Class<?> cls) {
        Intent intent = new Intent(fromContext, cls);
        fromContext.startActivity(intent);
    }
    /**
     * 带参数的跳转
     *
     * @param fromContext
     * @param cls         泛型
     */
    public void intent(Context fromContext, Class<?> cls, String name, Bundle con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, String con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, Serializable con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, int con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, boolean con) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, int con, String name1, int con1) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        intent.putExtra(name1,con1);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, int con, String name1, String con1) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        intent.putExtra(name1,con1);
        fromContext.startActivity(intent);
    }
    public void intent(Context fromContext, Class<?> cls, String name, String con, String name1, String con1) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtra(name,con);
        intent.putExtra(name1,con1);
        fromContext.startActivity(intent);
    }
    /**
     * 带参数的跳转
     *
     * @param fromContext
     * @param cls         泛型
     */
    public void intent(Context fromContext, Class<?> cls, Bundle bb) {
        Intent intent = new Intent(fromContext, cls);
        intent.putExtras(bb);
        fromContext.startActivity(intent);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(Context mContext ,String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
