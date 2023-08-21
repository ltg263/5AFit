package com.jxkj.fit_5a.conpoment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class RelativeLayoutSv extends RelativeLayout {
    public RelativeLayoutSv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RelativeLayoutSv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutSv(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
        return super.dispatchTouchEvent(ev);
    }

}
