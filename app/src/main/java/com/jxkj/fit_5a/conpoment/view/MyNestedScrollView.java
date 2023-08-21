package com.jxkj.fit_5a.conpoment.view;
//MyNestedScrollView

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.widget.NestedScrollView;

/**
 * Created by cc on 17-7-27.
 */
public class MyNestedScrollView extends NestedScrollView {
    int lastX = 0;
    int lastY = 0;
    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(getChildAt(0).getHeight()<getHeight()){
//            getParent().requestDisallowInterceptTouchEvent(false);
        }else{
//            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return super.dispatchTrackballEvent(event);
    }
}
