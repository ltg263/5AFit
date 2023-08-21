package com.jxkj.fit_5a.conpoment.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.UserImgBitmap;
import com.jxkj.fit_5a.conpoment.utils.AnimationUtils_5AFit;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.StyleKitName_Zx;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
@SuppressLint("AppCompatCustomView")
public class RobotView_Zx extends ImageView {

    private Canvas mCanvas;
    List<UserImgBitmap> mImgBitmap;
    Bitmap mBoxBit;
    Bitmap mBoxBit_G;
    List<MapDetailsBean.BoxsBean> box;
    List<Float[]> info;
    MapDetailsBean.ParamBean param;
    Double distance;

    public RobotView_Zx(Context context) {
        super(context);
        init(null, 0);
    }

    public RobotView_Zx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RobotView_Zx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.RobotView, defStyle, 0);
        a.recycle();
    }

    boolean isMaxMap = false;
    public void setData(List<MapDetailsBean.BoxsBean> box, Bitmap boxBit, List<Float[]> info,
                        List<UserImgBitmap> mImgBitmap, Bitmap mBoxBit_G, Double distance, MapDetailsBean.ParamBean param) {
        this.box = box;
        this.info = info;
        this.mBoxBit = boxBit;
        this.mBoxBit_G = mBoxBit_G;
        this.param = param;
        this.mImgBitmap = mImgBitmap;
        this.distance = distance;
        invalidate();
    }
    public void addData(UserImgBitmap mUserImgBitmap){
        mImgBitmap.add(mUserImgBitmap);
        invalidate();
    }

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
            return;
        }
        if (info != null) {
            mBoxBit = StringUtil.zoomImage(mBoxBit, 50, 50);
            mBoxBit_G = StringUtil.zoomImage(mBoxBit_G, 50, 50);

            StyleKitName_Zx.draw_1100900Canvas(mCanvas, info, param,  mImgBitmap, box,mBoxBit,mBoxBit_G,
                    new StyleKitName_Zx.StyleKitInterface() {
                        @Override
                        public void getLeftTop(float x, float y) {
                            MapX=x;
                            MapY=y;
                            setAnimationMap();
                        }
                    });

            if (isBox){
                for(int i =0;i< box.size();i++){
                    StyleKitName_Zx.mPathMeasure.getPosTan((float) (StyleKitName_Zx.mPathMeasure.getLength()/(distance/box.get(i).getDistance())),
                            StyleKitName_Zx.mCurrentPosition_box.get(i), null);
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

    public static List<ValueUserAnimator> listUserAnimator = new ArrayList<>();
    public static List<String> userIds_ts = new ArrayList<>();

    public static class ValueUserAnimator{
        RobotView_Zx iv_img;
        String userId;
        public ValueUserAnimator(RobotView_Zx iv_img,String userId){
            this.iv_img = iv_img;
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        ValueAnimator valueAnimator;
        private float lastAnimtionValue = 0;

        public float getLastAnimtionValue() {
            return lastAnimtionValue;
        }

        private long resetDuration = 0;
        int quanNum = 1;
        public void setLastAnimtionValue(float lastAnimtionValue) {
            this.lastAnimtionValue = lastAnimtionValue;
        }

        public void setRed(long resetDuration, int pos,int quanNum) {
            if(resetDuration<1000 || this.resetDuration == resetDuration || StyleKitName_Zx.mPathMeasure==null){
                return;
            }
            if(this.quanNum == quanNum && this.resetDuration==1999){
                return;
            }

            if(valueAnimator!=null){
                valueAnimator.cancel();
                valueAnimator = null;
            }
            this.resetDuration = resetDuration;
            if(this.quanNum < quanNum && lastAnimtionValue!=0){
                this.resetDuration = 1999;
            }
            this.quanNum = quanNum;

            valueAnimator = ValueAnimator.ofFloat(lastAnimtionValue, StyleKitName_Zx.mPathMeasure.getLength());
            valueAnimator.setDuration(this.resetDuration < 1000 ? 1000 : this.resetDuration);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.removeAllUpdateListeners();
//          valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    lastAnimtionValue = value;
                    if(value == StyleKitName_Zx.mPathMeasure.getLength()){
                        lastAnimtionValue = 0;
                        ValueUserAnimator.this.resetDuration = 0;
                    }
                    if(pos<StyleKitName_Zx.mCurrentPosition.size()){
                        StyleKitName_Zx.mPathMeasure.getPosTan(value, StyleKitName_Zx.mCurrentPosition.get(pos), null);
                        StyleKitName_Zx.mCurrentPosition.get(pos)[0] = StyleKitName_Zx.mCurrentPosition.get(pos)[0];
                        StyleKitName_Zx.mCurrentPosition.get(pos)[1] = StyleKitName_Zx.mCurrentPosition.get(pos)[1];
                        List<Float> floats = new ArrayList<>();
                        floats.add(StyleKitName_Zx.mCurrentPosition.get(pos)[0]);
                        floats.add(StyleKitName_Zx.mCurrentPosition.get(pos)[1]);
                    }
                    iv_img.postInvalidate();
                }
            });
            valueAnimator.start();
        }

        public void setState(int state){
            if(valueAnimator==null){
                return;
            }
            if(state==1){
                if(valueAnimator.isRunning()){
                    this.resetDuration = 0;
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

        /**
         * 划船机
         */
        public void setRedHcj(float endValue,int pos,int quanNum) {
            if(this.quanNum ==-2){
                this.quanNum = quanNum;
                return;
            }
            if(valueAnimator!=null){
                if(valueAnimator.isRunning()){
                    valueAnimator.pause();
                }
                valueAnimator.cancel();
                valueAnimator = null;
            }
            if(this.quanNum < quanNum || lastAnimtionValue > endValue){
                endValue = StyleKitName_Zx.mPathMeasure.getLength();
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
                    if(value == StyleKitName_Zx.mPathMeasure.getLength()){
                        lastAnimtionValue = 0;
                    }
                    if(pos<StyleKitName_Zx.mCurrentPosition.size()){
                        StyleKitName_Zx.mPathMeasure.getPosTan(value, StyleKitName_Zx.mCurrentPosition.get(pos), null);
                        StyleKitName_Zx.mCurrentPosition.get(pos)[0] = StyleKitName_Zx.mCurrentPosition.get(pos)[0];
                        StyleKitName_Zx.mCurrentPosition.get(pos)[1] = StyleKitName_Zx.mCurrentPosition.get(pos)[1];
                        List<Float> floats = new ArrayList<>();
                        floats.add(StyleKitName_Zx.mCurrentPosition.get(pos)[0]);
                        floats.add(StyleKitName_Zx.mCurrentPosition.get(pos)[1]);
                    }
                    iv_img.postInvalidate();
                }
            });
            valueAnimator.start();
        }
    }
}
