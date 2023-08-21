package com.jxkj.fit_5a.view.activity.mine;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.view.fragment.ShoppingFragment;
import com.jxkj.fit_5a.view.fragment.ShoppingFragment_Dhq;
import com.jxkj.fit_5a.view.fragment.ShoppingLhqFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    AutoHeightViewPager mViewPager;
    private static final String[] CHANNELS = new String[]{"兑好物", "兑好券"};
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.view2)
    View mView2;
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping;
    }

    @Override
    protected void initViews() {
        getusable_not_obtained();
        initVP();


    }
    Dialog dialog;

    private void getusable_not_obtained() {
        RetrofitUtil.getInstance().apiService()
                .getusable_not_obtained()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DiscountUsableNotBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DiscountUsableNotBean> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData()==null || result.getData().getList()==null || result.getData().getList().size()==0){
                                return;
                            }
                            if(dialog!=null && dialog.isShowing()){
                                dialog.dismiss();
                                dialog = null;
                            }
                            dialog = DialogUtils.showDiaYouHuiQuan(ShoppingActivity.this, result.getData().getList(),
                                    new DialogUtils.DialogInterfaceYhq() {
                                        @Override
                                        public void btnConfirm(int id) {
                                            if (id == -1) {
                                                List<Integer> lists = new ArrayList<>();
                                                for (int i = 0; i < result.getData().getList().size(); i++) {
                                                    lists.add(result.getData().getList().get(i).getId());
                                                }
                                                getPrizeReceives(lists);
                                            } else {
                                                getPrizeReceive(id);
                                            }
                                        }
                                    });
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

    private void getPrizeReceive(int id) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceive(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            getusable_not_obtained();
                            ToastUtils.showShort("领取成功");
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

    private void getPrizeReceives(List<Integer> lists) {
        RetrofitUtil.getInstance().apiService()
                .getPrizeReceives(lists)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
//                            getusable_not_obtained();
                            dialog.dismiss();
                            ToastUtils.showShort("领取成功");
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

    private List<Fragment> getFragments() {
        ShoppingFragment fragment = new ShoppingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", "1");
        fragment.setArguments(bundle);
        fragments.add(fragment);

//        ShoppingLhqFragment fragmentQ = new ShoppingLhqFragment();
//        Bundle bundleQ = new Bundle();
//        bundleQ.putString("type", "1");
//        fragment.setArguments(bundleQ);
//        fragments.add(fragmentQ);
        ShoppingFragment_Dhq fragmentQ = new ShoppingFragment_Dhq();
        Bundle bundleQ = new Bundle();
        bundleQ.putString("type", "1");
        fragment.setArguments(bundleQ);
        fragments.add(fragmentQ);
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
                if(position==0){
                    initView(mTv1, mView1);
                }else{
                    initView(mTv2, mView2);
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


    @OnClick({R.id.iv_back, R.id.tv_tiao_guo, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_tiao_guo:
                break;
            case R.id.rl1:
                initView(mTv1, mView1);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rl2:
                initView(mTv2, mView2);
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    private void initView(TextView tv, View v) {
        mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        mTv1.setTextColor(getResources().getColor(R.color.color_999999));
        mTv2.setTextColor(getResources().getColor(R.color.color_999999));
        mView1.setVisibility(View.INVISIBLE);
        mView2.setVisibility(View.INVISIBLE);

        tv.setTextColor(getResources().getColor(R.color.color_000000));
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        v.setVisibility(View.VISIBLE);
    }
    public static void intentStartActivity(Context mContext){
        mContext.startActivity(new Intent(mContext, ShoppingJfActivity.class));
    }

}
