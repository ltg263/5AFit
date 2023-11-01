package com.jxkj.fit_5a.conpoment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.security.biometrics.build.V;
import com.jxkj.fit_5a.R;

import cn.jzvd.JzvdStd;

public class JzvdStdTikTok extends JzvdStd {
    public JzvdStdTikTok(Context context) {
        super(context);
    }

    public JzvdStdTikTok(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
        bottomProgressBar.setVisibility(GONE);
        posterImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
    //changeUiTo 真能能修改ui的方法
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
    }

    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro,
                                        int posterImg, int bottomPro, int retryLayout) {
        topContainer.setVisibility(INVISIBLE);
        bottomContainer.setVisibility(INVISIBLE);
        startButton.setVisibility(startBtn);
        loadingProgressBar.setVisibility(loadingPro);
        posterImageView.setVisibility(posterImg);
        bottomProgressBar.setVisibility(GONE);
        mRetryLayout.setVisibility(retryLayout);
    }

    @Override
    public void dissmissControlView() {
        if (state != STATE_NORMAL
                && state != STATE_ERROR
                && state != STATE_AUTO_COMPLETE) {
            post(() -> {
                bottomContainer.setVisibility(View.INVISIBLE);
                topContainer.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                if (clarityPopWindow != null) {
                    clarityPopWindow.dismiss();
                }
                if (screen != SCREEN_TINY) {
                    bottomProgressBar.setVisibility(View.GONE);
                }
            });
        }
    }


    @Override
    public void onClickUiToggle() {
        super.onClickUiToggle();
        Log.i(TAG, "click blank");
        startButton.performClick();
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
    }

    boolean isMap = false;

    public void setMap(boolean map) {
        isMap = map;
    }

    public void updateStartImage() {
        if(isMap){
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(GONE);
            return;
        }
        if (state == STATE_PLAYING) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.icon_shipin_bofang);
            replayTextView.setVisibility(GONE);
        } else if (state == STATE_ERROR) {
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(GONE);
        } else if (state == STATE_AUTO_COMPLETE) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.icon_shipin_bofang);
            replayTextView.setVisibility(VISIBLE);
        } else {
            startButton.setImageResource(R.drawable.icon_shipin_bofang);
            replayTextView.setVisibility(GONE);
        }
    }

    public void setPlayState(){
        if (isPlay()) {
            goOnPlayOnPause();
        } else {
            //暂停
            if (state == STATE_PAUSE) {
                goOnPlayOnResume();
            } else  if (state == SCREEN_NORMAL) {
                startVideo();
            }
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
    boolean isSilence = false;

    public void setSilence(boolean isSilence) {
        Log.w("setSilence","setSilence:"+isSilence);
        this.isSilence = isSilence;
    }

    @Override
    public void onPrepared() {
        super.onPrepared();
        if(mediaInterface!=null){
            if (isSilence) {
                mediaInterface.setVolume(0f, 0f);
            } else {
                mediaInterface.setVolume(1f, 1f);
            }
        }
    }
}
