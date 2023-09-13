package com.jxkj.fit_5a.view.activity.exercise;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseActivity;
import com.jxkj.fit_5a.view.fragment.SelectMapFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CourseStartActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_intension)
    TextView mTvIntension;

    String mapId = "";

    List<String> listTime = new ArrayList<>();
    List<String> listLoad = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_course_start;
    }

    @Override
    protected void initViews() {
        listTime.add("不限制");
        listTime.add("10min");
        listTime.add("20min");
        listTime.add("30min");
        listTime.add("40min");
        listTime.add("50min");
        listTime.add("60min");
        mTvTime.setText(listTime.get(0));
        mTvIntension.setText(listTime.get(0));
        listLoad.add(listTime.get(0));
        for(int i =0;i<ConstValues_Ly.maxLoad;i++){
            listLoad.add(String.valueOf(i+1));
        }
        getSportMapList();
    }

    private void getSportMapList() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapListSposrt>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getSportMapList_al(1, 20, ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }else {
            mObservable = mApiService.getSportMapList(1, 20, ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapListSposrt>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapListSposrt> result) {
                        if (isDataInfoSucceed(result)) {
                            initVP(result.getData().getList());
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

    private void initVP(List<MapListSposrt.ListBean> list) {
        getFragments(list);
        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public float getPageWidth(int position) {
                return (float) 0.8;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });
        if(list.size()>0){
            mapId = list.get(0).getId();
        }
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mapId = list.get(position).getId();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments(List<MapListSposrt.ListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            SelectMapFragment fragment = new SelectMapFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", list.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }
    int currentTime = 0;
    @OnClick({R.id.tv_time, R.id.tv_intension, R.id.iv_out, R.id.iv_jr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                PickerViewUtils.selectorCustom(this, listTime, "运动时间", mTvTime);
                break;
            case R.id.tv_intension:
                if(listLoad.size()<=2){
                    ToastUtils.showShort("该设备不支持设置强度");
                    return;
                }
                PickerViewUtils.selectorCustom(this, listLoad, "运动强度", mTvIntension);
                break;
            case R.id.iv_out:
                finish();
                break;
            case R.id.iv_jr:
                Log.w("--->>",""+mapId);
                String tvTimeS = mTvTime.getText().toString();
                byte[] loadLista =null;
                for(int i=0;i<listTime.size();i++){
                    if(tvTimeS.equals(listTime.get(i))){
                        currentTime = i*10;
                    }
                }
                String tvIntension = mTvIntension.getText().toString();
                if(!tvIntension.equals(listTime.get(0))){
                    loadLista = new byte[1];
                    loadLista[0] = Byte.parseByte(tvIntension);
                }
                MapExerciseActivity.intentStartActivityKc(this, currentTime,loadLista,mapId);
                finish();
                break;
        }
    }
}
