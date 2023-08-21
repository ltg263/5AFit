package com.jxkj.fit_5a.view.activity.mine;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.view.fragment.MineGiftFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineGiftActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    AutoHeightViewPager mViewPager;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.view3)
    View mView3;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private static final String[] CHANNELS = new String[]{"收到礼物", "送出礼物", "背包礼物"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_gift;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("礼 物");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initVP();

    }

    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments() {
        for (int i = 0; i < mDataList.size(); i++) {
            MineGiftFragment fragment = new MineGiftFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", i );
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    initView(mTv1, mView1);
                } else if (position == 1) {
                    initView(mTv2, mView2);
                } else {
                    initView(mTv3, mView3);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
        mViewPager.setScrollable(false);
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
    }


    @OnClick({R.id.ll_back,  R.id.rl1, R.id.rl2, R.id.rl3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rl3:
                initView(mTv3, mView3);
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv3.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_999999));
        mTv2.setTextColor(getResources().getColor(R.color.color_999999));
        mTv3.setTextColor(getResources().getColor(R.color.color_999999));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);
        mView3.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }
}
