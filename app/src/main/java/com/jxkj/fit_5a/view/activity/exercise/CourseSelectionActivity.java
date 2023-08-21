package com.jxkj.fit_5a.view.activity.exercise;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceCourseTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.fragment.CourseSelectionFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CourseSelectionActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    AutoHeightViewPager mViewpager;

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    @Override
    protected int getContentView() {
        return R.layout.activity_course_selection;
    }

    @Override
    protected void initViews() {
        queryDeviceCourseTypeList();
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mViewpager.setScrollable(false);
    }

    private void queryDeviceCourseTypeList() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceCourseTypeList(ConstValues_Ly.DEVICE_TYPE_ID_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceCourseTypeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceCourseTypeData> result) {
                        if (isDataInfoSucceed(result)) {
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

    private List<Fragment> getFragments(List<DeviceCourseTypeData.ListBean> lists) {
        for (int i = 0; i < lists.size(); i++) {
            CourseSelectionFragment fragment = new CourseSelectionFragment();
            Bundle mBundle = new Bundle();
            mBundle.putString("type", lists.get(i).getId());
            mBundle.putString("introduct", lists.get(i).getIntroduct());
            fragment.setArguments(mBundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    private void initTabs(List<DeviceCourseTypeData.ListBean> lists) {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            titles.add(lists.get(i).getName());
        }
        final List<Fragment> mFragments = getFragments(lists);
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
        MagicIndicatorUtils.initMagicIndicator_1(this, true,titles, mMagicIndicator, mViewpager);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(StringUtil.isBlank(PopupWindowLanYan.BleName)){
            finish();
        }
    }
}
