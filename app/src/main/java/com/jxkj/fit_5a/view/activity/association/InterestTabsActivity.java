package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.view.fragment.InterestTabAllFragment;
import com.jxkj.fit_5a.view.fragment.InterestTabMyFragment;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InterestTabsActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.viewpager)
    AutoHeightViewPager mViewpager;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;

    @Override
    protected int getContentView() {
        return R.layout.activity_interest_all;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("圈子");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.wb_search_icon));
        // mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_add_right));
//        mTvRighttext.setText("创建圈子");
        initTabs();
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        InterestTabAllFragment fragmentAll = new InterestTabAllFragment();
        fragments.add(fragmentAll);
        InterestTabMyFragment fragmentMy = new InterestTabMyFragment();
        fragments.add(fragmentMy);
        return fragments;
    }
    private void initTabs() {
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("我加入的");
        final List<Fragment> mFragments = getFragments();
        mViewpager.setOffscreenPageLimit(titles.size());

        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                // 切换到当前页面，重置高度
                mViewpager.requestLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewpager.setCurrentItem(0);
        MagicIndicatorUtils.initMagicIndicator_4(this, titles, mMagicIndicator, mViewpager);
    }
    boolean isY = false;
    @OnClick({R.id.iv_back, R.id.tv_righttext,R.id.iv_rightimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_rightimg:
                IntentUtils.getInstence().intent(this, SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[3]);
                break;
            case R.id.tv_righttext:
                if(true){
                    isY = false;
                    startActivity(new Intent(this,CreateCircleActivity.class));
                    return;
                }
                DialogUtils.showDialogNoCircle(this,  "创建圈子权限", 1, new DialogUtils.DialogLyInterface() {
                    @Override
                    public void btnConfirm() {
                        isY = true;
                    }
                });
                break;
        }
    }



}
