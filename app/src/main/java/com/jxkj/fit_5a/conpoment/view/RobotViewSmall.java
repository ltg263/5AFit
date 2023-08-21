package com.jxkj.fit_5a.conpoment.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotViewSmall extends ImageView {

    private Canvas mCanvas;
    Bitmap mBitmap;
    Double distance;
    List<Float[]> info;
    MapDetailsBean.ParamBean param;

    public RobotViewSmall(Context context) {
        super(context);
        init(null, 0);
    }

    public RobotViewSmall(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RobotViewSmall(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RobotView, defStyle, 0);
        a.recycle();
    }

    public void setData(Bitmap mBitmap,Double distance, List<Float[]> info,MapDetailsBean.ParamBean param) {
        this.param = param;
        this.mBitmap = mBitmap;
        this.info = info;
        this.distance = distance;
        invalidate();
    }
    boolean isMaxMap = false;

    public void setType(boolean isMaxMap) {
        this.isMaxMap = isMaxMap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        if(getWidth()<=10 || getHeight()<=10){
            Log.w("--->>>>>", "为获取到屏幕的宽过高" );//1920 1852*700 900*600
            return;
        }
        if (info != null && mBitmap!=null) {
            draw_1100900Canvas(mCanvas, info, param, mBitmap);
        }
    }


    private static class CacheFor_1100900Canvas {
        private static Paint paint = new Paint();
        private static Path _2Path = new Path();
        private static Path _3Path = new Path();
    }
    public static float[] mCurrentPosition;
    public static PathMeasure mPathMeasure;
    public static void draw_1100900Canvas(Canvas canvas, List<Float[]> info, MapDetailsBean.ParamBean param,Bitmap bitmap) {
        float bl =  param.getReferenceWidth()/param.getReferenceHeight();//1.5
        float yBl = canvas.getHeight()/param.getHeight();
        float xBl = (canvas.getHeight() * bl)/(param.getHeight()*bl);
        //矩形范围宽:1620   150
        float rectLeft = (canvas.getWidth() - canvas.getHeight() * bl) / 2;
        float rectRight = rectLeft + canvas.getHeight() * bl;
        RobotViewSmall.CacheFor_1100900Canvas._2Path.reset();
        RobotViewSmall.CacheFor_1100900Canvas._2Path.moveTo(info.get(0)[0]*xBl+rectLeft, info.get(0)[1]*yBl);
        for (int i = 0; i < info.size(); i++) {
            RobotViewSmall.CacheFor_1100900Canvas._2Path.lineTo(info.get(i)[0]*xBl+rectLeft, info.get(i)[1]*yBl);
        }
        RobotViewSmall.CacheFor_1100900Canvas._2Path.lineTo(info.get(0)[0]*xBl+rectLeft , info.get(0)[1]*yBl);
        RobotViewSmall.CacheFor_1100900Canvas.paint.reset();
        RobotViewSmall.CacheFor_1100900Canvas.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        RobotViewSmall.CacheFor_1100900Canvas.paint.setStrokeWidth(2f);
        canvas.save();
        RobotViewSmall.CacheFor_1100900Canvas.paint.setStyle(Paint.Style.STROKE);
        RobotViewSmall.CacheFor_1100900Canvas.paint.setColor(MainApplication.getContext().getResources().getColor(R.color.color_333333));
        canvas.drawPath(RobotViewSmall.CacheFor_1100900Canvas._2Path, RobotViewSmall.CacheFor_1100900Canvas.paint);


        if (mCurrentPosition == null) {
            mCurrentPosition = new float[2];
            mCurrentPosition[0] = info.get(0)[0] * xBl + rectLeft;
            mCurrentPosition[1] = info.get(0)[1] * yBl;
        }
        bitmap = StringUtil.zoomImage(bitmap, 16, 16);
        canvas.drawBitmap(bitmap, mCurrentPosition[0]-8, mCurrentPosition[1]-8, null);

        if (mPathMeasure == null) {
            mPathMeasure = new PathMeasure(RobotViewSmall.CacheFor_1100900Canvas._2Path, true);
        }

        canvas.restore();
    }

    ValueAnimator valueAnimator;
    private float lastAnimtionValue = 0;
//
    long resetDuration = 0;
    int quanNum = 1;
    public void setRed(long resetDuration,int quanNum) {
        Log.w("--->>>","时间准备改变:"+resetDuration);//
        if(resetDuration<4000 || this.resetDuration == resetDuration || RobotViewSmall.mPathMeasure==null){
            return;
        }

        if(this.quanNum == quanNum && this.resetDuration==1999){
            return;
        }

        Log.w("--->>>","时间准备改变:"+resetDuration);

        if(valueAnimator!=null){
            valueAnimator.cancel();
            valueAnimator = null;
        }
        this.resetDuration = resetDuration;
        if(this.quanNum < quanNum){
            this.resetDuration = 1999;
        }
        this.quanNum = quanNum;

        valueAnimator = ValueAnimator.ofFloat(lastAnimtionValue, RobotViewSmall.mPathMeasure.getLength());
        valueAnimator.setDuration(this.resetDuration < 1000 ? 1000 : this.resetDuration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lastAnimtionValue = value;
                if(value == RobotViewSmall.mPathMeasure.getLength()){
                    lastAnimtionValue = 0;
                    RobotViewSmall.this.resetDuration = 0;
                }
                invalidateByValue(value);
            }
        });
        valueAnimator.start();
    }
    int state = 1;
    public void setState(int state){
        this.state = state;
        if(valueAnimator==null){
            return;
        }
        if(state==1){
            if( valueAnimator.isRunning()){
                valueAnimator.pause();
            }
        }else{
            if(!valueAnimator.isRunning()){
                valueAnimator.resume();
            }
        }
    }

    public void setCancel(){
        if(valueAnimator!=null) {
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }

    private void invalidateByValue(float value) {
        RobotViewSmall.mPathMeasure.getPosTan(value, RobotViewSmall.mCurrentPosition, null);
        RobotViewSmall.mCurrentPosition[0] = RobotViewSmall.mCurrentPosition[0];
        RobotViewSmall.mCurrentPosition[1] = RobotViewSmall.mCurrentPosition[1];
        postInvalidate();
    }


    /**
     * 划船机
     */
    public void setRedHcj(float endValue) {
        if(valueAnimator!=null){
            if(valueAnimator.isRunning()){
                valueAnimator.pause();
            }
            valueAnimator.cancel();
            valueAnimator = null;
        }

        valueAnimator = ValueAnimator.ofFloat(lastAnimtionValue, endValue);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lastAnimtionValue = value;
                if(value == RobotViewSmall.mPathMeasure.getLength()){
                    lastAnimtionValue = 0;
                }
                invalidateByValue(value);
            }
        });
        valueAnimator.start();
    }

}
