package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

import static com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils.initMagicIndicator_1;

public class MagicIndicatorUtils {


    public static void initMagicIndicator_1(Context mContext, List<String> mDataList,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        initMagicIndicator_1(mContext,false,mDataList,mMagicIndicator, mViewPager);
    }
    /**
     * 基础的下划线
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_1(Context mContext,boolean mAdjustMode, List<String> mDataList,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        mMagicIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdjustMode(mAdjustMode);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.color_666666));
                simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.color_000000));
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 1));
                indicator.setYOffset(20);
                indicator.setColors(mContext.getResources().getColor(R.color.color_text_theme));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    /**
     * 基础的下划线+动作
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     */
    public static void initMagicIndicator_2(Context mContext, List<String> mDataList,MagicIndicator mMagicIndicator,ViewPager mViewPager,
                                            onClickInterface mOnClickInterface) {
        mMagicIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mViewPager.getCurrentItem() == index && isDoubleClicked()){
                            mOnClickInterface.bntClickListener();
                        }
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#333333"));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    private static final long CLICK_INTERVAL_TIME = 300;
    private static long lastClickTime = 0;

    /**
     * 双击事件
     * @return
     */
    private static boolean isDoubleClicked() {
        //获取系统当前毫秒数，从开机到现在的毫秒数(手机睡眠时间不包括在内)
        long currentTimeMillis = SystemClock.uptimeMillis();
        //两次点击间隔时间小于300ms代表双击
        if (currentTimeMillis - lastClickTime < CLICK_INTERVAL_TIME) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }

    public interface onClickInterface {
        /**
         * 确定
         */
        void bntClickListener();
    }

    /**
     * 指标为圆点
     * @param mContext
     * @param circleCount
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_3(Context mContext,int circleCount,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        MagicIndicatorScaleCircleNavigator scaleCircleNavigator = new MagicIndicatorScaleCircleNavigator(mContext);
        scaleCircleNavigator.setCircleCount(circleCount);
        scaleCircleNavigator.setNormalCircleColor(Color.parseColor("#CCCCCC"));
        scaleCircleNavigator.setSelectedCircleColor(Color.parseColor("#000000"));
        scaleCircleNavigator.setCircleClickListener(new MagicIndicatorScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        mMagicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }
    /**
     * 基础的下划线+动作
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_4(Context mContext, List<String> mDataList,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        mMagicIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#333333"));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }
}
