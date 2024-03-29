package com.jxkj.fit_5a.conpoment.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;

/**
 * Created on 2016/6/17.
 * whc
 */
public class LoadDialog extends Dialog {
    private TextView loadingText;
    private ImageView iv_close;
    private ImageView ivLoading;

    public LoadDialog(Context context) {
        super(context, R.style.simpleDialog);
        init(context, null);
    }

    public ImageView getIv_close() {
        return iv_close;
    }

    public LoadDialog(Context context, String text) {
        super(context, R.style.simpleDialog);
        init(context, text);
    }

    private void init(Context context, String text) {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        View v = LayoutInflater.from(context).inflate(R.layout.progress_loading, null);

        loadingText = v.findViewById(R.id.loading_text);
        ivLoading = v.findViewById(R.id.iv_loading);
        iv_close = v.findViewById(R.id.iv_close);

        if(text.equals("蓝牙连接中...")){
            iv_close.setVisibility(View.VISIBLE);
        }

        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getBackground();
        animationDrawable.start();
        if (text != null)
            setLoadingText(text);

        setContentView(v);
    }

    public void setLoadingText(String text) {
        if (!TextUtils.isEmpty(text)) {
            loadingText.setText(text);
        }
    }
}
