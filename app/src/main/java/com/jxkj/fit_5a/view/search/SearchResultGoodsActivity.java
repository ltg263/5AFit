package com.jxkj.fit_5a.view.search;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchResultGoodsActivity extends BaseActivity {


    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rl_actionbar)
    RelativeLayout llActionbar;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.tabs_tribe)
    TabLayout mTabTribe;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    public static String[] tabListBlq = {"动态", "用户", "话题", "圈子","教学","商品","商品_收藏"};

    private String[] tabListBlq_x = {"动态", "用户", "话题", "圈子"};
    String searchType = tabListBlq_x[0];
    private String search;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_goods_result;
    }

    @Override
    protected void initViews() {
        mTabTribe.setTabMode(TabLayout.MODE_FIXED);
        mTabTribe.setTabGravity(TabLayout.GRAVITY_FILL);
        search = getIntent().getStringExtra("search");
        searchType = getIntent().getStringExtra("searchType");
        tvTopTitle.setText(search);
        initVP();
    }

    @OnClick({R.id.ll_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initVP() {
        getFragments();
        mViewpager.setOffscreenPageLimit(tabListBlq_x.length);
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tabListBlq_x.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabListBlq_x[position];
            }
        });

        mTabTribe.setupWithViewPager(mViewpager);
        if(searchType.equals(tabListBlq_x[0])){
            mViewpager.setCurrentItem(0);
        }else if(searchType.equals(tabListBlq_x[1])){
            mViewpager.setCurrentItem(1);
        }else if(searchType.equals(tabListBlq_x[2])){
            mViewpager.setCurrentItem(2);
        }else if(searchType.equals(tabListBlq_x[3])){
            mViewpager.setCurrentItem(3);
        }
    }
    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments() {
        Bundle bundle = new Bundle();
        bundle.putString("search",search);

        HomeSearchDpListFragment fragment = new HomeSearchDpListFragment();
        fragment.setArguments(bundle);
        fragments.add(fragment);

        HomeSearchYhListFragment fragment1 = new HomeSearchYhListFragment();
        fragment1.setArguments(bundle);
        fragments.add(fragment1);

        HomeSearchHtListFragment fragment2 = new HomeSearchHtListFragment();
        fragment2.setArguments(bundle);
        fragments.add(fragment2);

        HomeSearchQzListFragment fragment3 = new HomeSearchQzListFragment();
        fragment3.setArguments(bundle);
        fragments.add(fragment3);
        return fragments;
    }

}
