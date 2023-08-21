package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jxkj.fit_5a.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GlideImageUtils {

    public static void setGlideImage(Context mContext, Object imgUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.icon_app_logo).centerCrop();
        Glide.with(mContext).load(imgUrl).apply(requestOptions).into(imageView);
    }

    public static void loadGlideRequestListener(Context mContext, String imgUrl, ImageView imageView,GlideRequestListener mGlideRequestListener) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.icon_app_logo).centerCrop();
        Glide.with(mContext)
                .load(imgUrl).override(100,100).apply(requestOptions).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.w("------123456789","onLoadFailed");
                mGlideRequestListener.glideComplete();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.w("------123456789","onResourceReady");
                mGlideRequestListener.glideComplete();
                return false;
            }
        }) .into(imageView);
    }

    /**
     * Gif播放完毕回调
     */
    public interface GlideRequestListener {
        void glideComplete();
    }
}
