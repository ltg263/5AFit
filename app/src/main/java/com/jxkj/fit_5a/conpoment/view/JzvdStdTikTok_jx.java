package com.jxkj.fit_5a.conpoment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jxkj.fit_5a.R;

import cn.jzvd.JzvdStd;

public class JzvdStdTikTok_jx extends JzvdStd {
    View iv_back;
    public JzvdStdTikTok_jx(Context context) {
        super(context);
    }

    public JzvdStdTikTok_jx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_std_jx;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        iv_back = findViewById(R.id.iv_back);
    }

    public void updateStartImage() {
        Log.w("state:","state:"+state);
        if(state==1){
            iv_back.setVisibility(GONE);
        }
//        if (state == STATE_PLAYING) {
//            startButton.setImageResource(R.drawable.icon_shipin_bofang);
//        } else if (state == STATE_ERROR) {
////            startButton.setVisibility(INVISIBLE);
////            replayTextView.setVisibility(GONE);
//        } else if (state == STATE_AUTO_COMPLETE) {
//            startButton.setImageResource(R.drawable.icon_shipin_bofang);
//        } else {
//            startButton.setImageResource(R.drawable.icon_shipin_bofang);
//        }
    }
}
