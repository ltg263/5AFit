package com.jxkj.fit_5a.view.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.view.fragment.QcIssueFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineIssueQcActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private static final String[] CHANNELS = new String[]{"椭圆机", "跑步机", "划水机", "跑步机"};
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_issue_qc;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("器材问题");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initVP();
        MagicIndicatorUtils.initMagicIndicator_1(this, mDataList, mMagicIndicator, mViewPager);
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        for(int i=0;i<mDataList.size();i++){
            QcIssueFragment fragment = new QcIssueFragment();
            Bundle bundle = new Bundle();
            bundle.putString("search","");
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }
    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(2);
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

        mViewPager.setCurrentItem(0);
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }
}
