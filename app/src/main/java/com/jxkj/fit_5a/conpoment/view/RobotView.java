package com.jxkj.fit_5a.conpoment.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.AnimationUtils_5AFit;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotView extends ImageView {

    private Canvas mCanvas;
    Bitmap mBitmap;
    Bitmap mBoxBit;
    Bitmap mBoxBit_G;
    Double distance;
    List<MapDetailsBean.BoxsBean> box;
    List<Float[]> info;
    MapDetailsBean.ParamBean param;

    public RobotView(Context context) {
        super(context);
        init(null, 0);
    }

    public RobotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RobotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RobotView, defStyle, 0);
        a.recycle();
    }

    public void setData(List<MapDetailsBean.BoxsBean> box, Bitmap boxBit,
                        List<Float[]> info, Bitmap mBitmap, Bitmap mBoxBit_G, Double distance, MapDetailsBean.ParamBean param) {
        this.box = box;
        this.info = info;
        this.mBoxBit = boxBit;
        this.mBoxBit_G = mBoxBit_G;
        this.param = param;
        this.mBitmap = mBitmap;
        this.distance = distance;
        invalidate();
    }
    boolean isMaxMap = false;

    public void setType(boolean isMaxMap) {
        this.isMaxMap = isMaxMap;
    }
    float MapX, MapY;

    public float getMapX() {
        return MapX;
    }

    public float getMapY() {
        return MapY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        if(getWidth()<=10 || getHeight()<=10){
            Log.w("--->>>>>", "为获取到屏幕的宽过高" );//1920 1852*700 900*600
            return;
        }
        if (info != null) {
            mBoxBit = StringUtil.zoomImage(mBoxBit, 50, 50);
            mBoxBit_G = StringUtil.zoomImage(mBoxBit_G, 50, 50);
            StyleKitName.draw_1100900Canvas(mCanvas, info, param, mBitmap, box,mBoxBit,mBoxBit_G,
                    new StyleKitName.StyleKitInterface() {
                        @Override
                        public void getLeftTop(float x, float y) {
                            MapX=x;
                            MapY=y;
                            if(state==0){//运动中
                                setAnimationMap();
                            }else{
                                if(isMaxMap){
                                    setAnimation(AnimationUtils_5AFit.get_Scale_Animation(2,2,2,2,MapX,MapY,0));
                                }else{
                                    setAnimation(AnimationUtils_5AFit.get_Scale_Animation(1,1,1,1,MapX,MapY,0));
                                }
                            }
                        }
                    });

            if (isBox){
                for(int i =0;i< box.size();i++){
                    StyleKitName.mPathMeasure.getPosTan((float) (StyleKitName.mPathMeasure.getLength()/(distance/box.get(i).getDistance())),
                            StyleKitName.mCurrentPosition_box.get(i), null);
                    postInvalidate();
                }
                isBox = false;
            }
        }
    }
    boolean isBox = true;

    public void setAnimationMap() {
        if(isMaxMap){
            setAnimation(AnimationUtils_5AFit.get_Scale_Animation(2,2,2,2,MapX,MapY,0));
        }else{
            setAnimation(AnimationUtils_5AFit.get_Scale_Animation(1,1,1,1,MapX,MapY,0));
        }
    }

    public void setOnClickAnimationMap() {
        if(isMaxMap){
            setAnimation(AnimationUtils_5AFit.get_Scale_Animation(2,2,2,2,MapX,MapY,0));
        }else{
            setAnimation(AnimationUtils_5AFit.get_Scale_Animation(1,1,1,1,MapX,MapY,0));
        }
    }


    ValueAnimator valueAnimator;
    private float lastAnimtionValue = 0;
//
    long resetDuration = 0;
    int quanNum = 1;
    public void setRed(long resetDuration,int quanNum) {
        Log.w("--->>>","时间准备改变:"+resetDuration);//
        if(resetDuration<4000 || this.resetDuration == resetDuration || StyleKitName.mPathMeasure==null){
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

        valueAnimator = ValueAnimator.ofFloat(lastAnimtionValue, StyleKitName.mPathMeasure.getLength());
        valueAnimator.setDuration(this.resetDuration < 1000 ? 1000 : this.resetDuration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lastAnimtionValue = value;
                if(value == StyleKitName.mPathMeasure.getLength()){
                    lastAnimtionValue = 0;
                    RobotView.this.resetDuration = 0;
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
        StyleKitName.mPathMeasure.getPosTan(value, StyleKitName.mCurrentPosition, null);
        StyleKitName.mCurrentPosition[0] = StyleKitName.mCurrentPosition[0];
        StyleKitName.mCurrentPosition[1] = StyleKitName.mCurrentPosition[1];
        postInvalidate();
    }


    /**
     * 划船机
     */
    public void setRedHcj(float endValue,int quanNum) {
        if(valueAnimator!=null){
            if(valueAnimator.isRunning()){
                valueAnimator.pause();
            }
            valueAnimator.cancel();
            valueAnimator = null;
        }
        if(this.quanNum < quanNum || lastAnimtionValue > endValue){
            endValue = StyleKitName.mPathMeasure.getLength();
        }
        this.quanNum = quanNum;

        valueAnimator = ValueAnimator.ofFloat(lastAnimtionValue, endValue);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.removeAllUpdateListeners();
//          valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lastAnimtionValue = value;
                if(value == StyleKitName.mPathMeasure.getLength()){
                    lastAnimtionValue = 0;
                }
                invalidateByValue(value);
            }
        });
        valueAnimator.start();
    }

}
