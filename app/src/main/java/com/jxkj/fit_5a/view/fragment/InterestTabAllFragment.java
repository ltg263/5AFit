package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InterestTabAllFragment extends BaseFragment {
    @BindView(R.id.viewpager)
    AutoHeightViewPager mViewpager;
    @BindView(R.id.tabs)
    TabLayout mTabs;

    @Override
    protected int getContentView() {
        return R.layout.fragment_interest_tab_all;
    }

    @Override
    protected void initViews() {
        queryDeviceTypeLists();
    }

    private void queryDeviceTypeLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceTypeLists(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceTypeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceTypeData> result) {
                        if(isDataInfoSucceed(result)){
                            initTabs(result.getData().getList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> getFragments(List<DeviceTypeData.ListBean> lists) {
        for (int i = 0; i < lists.size(); i++) {
            InterestAllFragment fragment = new InterestAllFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",lists.get(i).getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
//        InterestAllFragment fragment0 = new InterestAllFragment();
//        Bundle bundle0 = new Bundle();
//        bundle0.putInt("id",0);
//        fragment0.setArguments(bundle0);
//        fragments.add(fragment0);
        return fragments;
    }
    private void initTabs(List<DeviceTypeData.ListBean> lists) {
        List<String> titles = new ArrayList<>();
        for(int i=0;i<lists.size();i++){
            titles.add(lists.get(i).getName());
        }
//        titles.add("无设备");
        final List<Fragment> mFragments = getFragments(lists);
        mViewpager.setOffscreenPageLimit(titles.size());

        mViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
        mTabs.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }

    @Override
    public void initImmersionBar() {

    }
}
