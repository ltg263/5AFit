package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotView11_zx extends View {
    List<Float[]> info;
    MapDetailsBean.ParamBean param;

    private static class CacheFor_1100900Canvas {
        private static Paint paint = new Paint();
        private static Path _2Path = new Path();
        private static Path _3Path = new Path();
    }
    public RobotView11_zx(Context context) {
        super(context);
        init(null, 0);
    }

    public RobotView11_zx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RobotView11_zx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RobotView, defStyle, 0);
        a.recycle();
    }

    public void setData(List<Float[]> info, MapDetailsBean.ParamBean param) {
        this.info = info;
        this.param = param;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(info==null){
            return;
        }
        Log.w("--->>>>>", "屏幕宽:" + getWidth());//1920 1852*700 900*600
        float yBl = getHeight()/param.getHeight();
        float xBl = getHeight() /param.getHeight();
        //矩形范围宽:1620
        RectF resizedFrame = new RectF(0,0,getWidth(),getHeight());
        canvas.translate(resizedFrame.left, resizedFrame.top);
        CacheFor_1100900Canvas._2Path.reset();
        CacheFor_1100900Canvas._2Path.moveTo(info.get(0)[0]*xBl, info.get(0)[1]*yBl);
        for (int i = 0; i < info.size(); i++) {
            CacheFor_1100900Canvas._2Path.lineTo(info.get(i)[0]*xBl, info.get(i)[1]*yBl);
        }
        CacheFor_1100900Canvas._2Path.lineTo(info.get(0)[0]*xBl , info.get(0)[1]*yBl);
        CacheFor_1100900Canvas.paint.reset();
        CacheFor_1100900Canvas.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        CacheFor_1100900Canvas.paint.setStrokeWidth(5f);
        canvas.save();
        CacheFor_1100900Canvas.paint.setStyle(Paint.Style.STROKE);
        CacheFor_1100900Canvas.paint.setColor(MainApplication.getContext().getResources().getColor(R.color.color_text_theme));
        canvas.drawPath(CacheFor_1100900Canvas._2Path, CacheFor_1100900Canvas.paint);
    }

}
