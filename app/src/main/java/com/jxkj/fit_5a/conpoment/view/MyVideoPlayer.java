package com.jxkj.fit_5a.conpoment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jxkj.fit_5a.R;


/**
 * 作者： ch
 * 时间： 2018/8/17 0017-下午 5:14
 * 描述：
 * 来源：
 */


public class MyVideoPlayer extends JzvdStd_View {
    public static String PLAY_STATE_EXERCISE = "play_state_exercise";
    public RelativeLayout rl_touch_help;
    private ImageView iv_start;
    private LinearLayout ll_start;

    private Context context;


    public MyVideoPlayer(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
    }
        @Override
    public void onCompletion() {
        thumbImageView.setVisibility(View.GONE);
        //循环播放
        startVideo();
    }


    @Override
    public void setUp(String url, String title, int screen) {
        Log.w("--->>>>>>>>>>>>>:","title:"+title+"url:"+url);
        if(title.equals(PLAY_STATE_EXERCISE)){
            isOnClick = false;
        }
        super.setUp(url, title, screen);
    }

    @Override
    public void init(final Context context) {
        super.init(context);

        rl_touch_help = findViewById(R.id.rl_touch_help);
        ll_start = findViewById(R.id.ll_start);
        iv_start = findViewById(R.id.iv_start);
        resetPlayView();

        rl_touch_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isPlay()) { 28.1  12
////                    fullscreenButton.performClick();
//                }
                if(isOnClick){
                    setPlayState();
                    resetPlayView();
                }

            }
        });

        ll_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnClick){
                    setPlayState();
                    resetPlayView();
                }
            }
        });
    }
    public void setPlayState(){
        if (isPlay()) {
            goOnPlayOnPause();
        } else {
            //暂停
            if (state == STATE_PAUSE) {
                goOnPlayOnResume();
            } else {
                startVideo();
            }
        }
    }

    @Override
    public void startVideo() {
        if (screen == SCREEN_FULLSCREEN) {
            startFullscreenDirectly(context, MyVideoPlayer.class, jzDataSource);
            onStatePreparing();
            ll_start.setVisibility(VISIBLE);
        } else {
            super.startVideo();
            ll_start.setVisibility(GONE);
        }
        resetPlayView();
    }


    private void resetPlayView() {

        if (isPlay()) {
            ll_start.setVisibility(GONE);
            iv_start.setBackgroundResource(R.mipmap.video_play_parse);
        } else {
            ll_start.setVisibility(VISIBLE);
            iv_start.setBackgroundResource(R.drawable.ic_baofang);
        }
    }

    /**
     * 是否播放
     *
     * @return
     */
    public boolean isPlay() {
        if (state == STATE_PREPARING || state == STATE_PLAYING || state == -1) {
            return true;
        }

        return false;
    }


}
