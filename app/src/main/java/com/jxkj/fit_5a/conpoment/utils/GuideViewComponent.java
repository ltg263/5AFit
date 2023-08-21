package com.jxkj.fit_5a.conpoment.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.binioter.guideview.Component;
import com.binioter.guideview.Guide;
import com.binioter.guideview.GuideBuilder;
import com.jxkj.fit_5a.R;

import net.lucode.hackware.magicindicator.buildins.UIUtil;


/**
 * Description:
 * author:jiawei.hao
 * Date:5/9/22
 */
public class GuideViewComponent implements Component {
    public static String isFirstExercise = "isFirstExercise";
    GuideViewComponentListener mGuideViewComponentListener;
    Guide mGuide;
    /**
     * 上1:点击此处，可切换运动模式\n选择适合的运动模式
     * 上2:点击此处，可查看地图排名\n与大家共同运动
     * 下3:点击此处，创建或加入房间\n快来与大家一同运动吧
     * 下4:点击此处，可切换地图模式\n观看教学视频以及退出运动
     */
    int pos;
    /**
     * Gif播放完毕回调
     */
    public interface GuideViewComponentListener {
        void onClickView();
    }

    public GuideViewComponent(Activity mActivity, View mView,int pos,GuideViewComponentListener guideViewComponentListener) {
        mGuideViewComponentListener = guideViewComponentListener;
        GuideBuilder mGuideBuilder = showGuideBuilder(mActivity,mView);
        this.pos = pos;
        mGuideBuilder.addComponent(this);
        mGuide = mGuideBuilder.createGuide();
        mGuide.show(mActivity);
    }

    @Override
    public View getView(LayoutInflater inflater) {
        //写你的布局，然后渲染过来
        View mView = inflater.inflate(R.layout.guide_view_layout,null);
            TextView mTvStr1 = mView.findViewById(R.id.tv_str_1);
        TextView mTvStr2 = mView.findViewById(R.id.tv_str_2);
        TextView mTvStr3 = mView.findViewById(R.id.tv_str_3);
        TextView btn_1 = mView.findViewById(R.id.btn_1);
        TextView btn_2 = mView.findViewById(R.id.btn_2);
        TextView btn_3 = mView.findViewById(R.id.btn_3);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuide.dismiss();
                if(mGuideViewComponentListener!=null){
                    mGuideViewComponentListener.onClickView();
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuide.dismiss();
                if(mGuideViewComponentListener!=null){
                    mGuideViewComponentListener.onClickView();
                }
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuide.dismiss();
                if(mGuideViewComponentListener!=null){
                    mGuideViewComponentListener.onClickView();
                }
            }
        });
        if(pos==1){
            mView.findViewById(R.id.rl_top).setVisibility(View.VISIBLE);
            mTvStr1.setText("点击此处，可切换运动模式\n选择适合的运动模式");
            btn_1.setText("下一步");
        }
        if(pos==2){
            mView.findViewById(R.id.rl_top).setVisibility(View.VISIBLE);
            mTvStr1.setText("点击此处，可查看地图排名\n与大家共同运动");
            btn_1.setText("下一步");
        }
        if(pos==3){
            mView.findViewById(R.id.rl_bottom).setVisibility(View.VISIBLE);
            mTvStr2.setText("点击此处，创建或加入房间\n快来与大家一同运动吧");
            btn_2.setText("知道了");
        }
        if(pos==4){
            mView.findViewById(R.id.rl_3).setVisibility(View.VISIBLE);
            mTvStr3.setText("点击此处，可切换地图模式\n观看教学视频以及退出运动");
            btn_3.setText("知道了");
        }

        return mView;
    }

    //引导内容在高亮View的上方
    @Override
    public int getAnchor() {
        if(pos==1 || pos==2|| pos==4){
            return ANCHOR_BOTTOM;
        }
        return ANCHOR_TOP;
    }

    //引导内容与高亮View左对齐
    @Override
    public int getFitPosition() {
        if(pos==4){
            return FIT_END;
        }
        return FIT_START;
    }

    //向左偏移158dp，注意单位是dp
    @Override
    public int getXOffset() {
        return 0;
    }

    //向上偏移10dp，注意单位是dp
    @Override
    public int getYOffset() {
        return -10;
    }

    public GuideBuilder showGuideBuilder(Activity mActivity, View mView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(mView)
                .setHighTargetCorner(UIUtil.dip2px(mActivity,8)) //设置高亮部分的圆角，单位是像素
                .setHighTargetPadding(UIUtil.dip2px(mActivity,10)) //设置高亮部分的padding，相当于扩大高亮部分，单位是像素
                .setOutsideTouchable(true) //true：遮盖部分点击事件无效(component中的点击事件不受影响，有效)，false：点击事件有效，点击的时候会关闭引导
                .setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() { //引导消失显示回调
                    @Override
                    public void onShown() {

                    }

                    @Override
                    public void onDismiss() {

                    }
                })
                .setAlpha(128); //设置覆盖的maskview透明度，取值0-255；
        return builder;
//        builder.addComponent();
//        Guide mGuide = builder.createGuide();
//        mGuide.show(mActivity);
    }


}
