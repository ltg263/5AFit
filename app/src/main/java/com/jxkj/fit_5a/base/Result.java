package com.jxkj.fit_5a.base;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;

public class Result<T> {
    private String sub_mesg;
    private int code;
    private T data;

    public String getMesg() {
        return sub_mesg;
    }

    public void setMesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    public int getCode() {
        if(code!=0){
            Log.w("getCode","getCode:"+sub_mesg);
            Toast mToast = Toast.makeText(MainApplication.getContext(), sub_mesg, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
//            ToastUtils.showShort(sub_mesg);
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
