package com.jxkj.fit_5a.view.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.MagicIndicatorUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.view.AutoHeightViewPager;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeThreeNewFragment extends BaseFragment {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    AutoHeightViewPager mViewPager;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.tv_add_dt)
    ImageView tv_add_dt;
//    private CustomPopWindow distancePopWindow;
    //
    private static final String[] CHANNELS = new String[]{"关注", "精选", "圈子","栏目"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_three_new;
    }

    @Override
    protected void initViews() {
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstence().intent(getActivity(), SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[0]);
            }
        });
        tv_add_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(window!=null){
                    window.showAtLocation(tv_add_dt, Gravity.BOTTOM, 0, 0);
                    return;
                }
                initPopupWindow();
            }
        });
        initVP();
    }
    PopupWindowTy window;

    private void initPopupWindow() {
        List<String> list = new ArrayList<>();
        list.add("发布图文");
        list.add("发布视频");
        if (window == null) {
            window = new PopupWindowTy(getActivity(), list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    if (position == 0) {
                        AssociationAddActivity.startActivityAddAssociation(getActivity(),2,"","","","");
                    }
                    if (position == 1) {
                        AssociationAddActivity.startActivityAddAssociation(getActivity(),3,"","","","");
                    }
                    window.dismiss();
                }
            });

            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        window.showAtLocation(tv_add_dt, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }

    public void setDoubleClicked(){
//        mNestedScrollView.smoothScrollTo(0,refreshLayout.getTop());
        if(mViewPager!=null){
            Log.w("mViewPager","mViewPager:"+mViewPager.getCurrentItem());
            switch (mViewPager.getCurrentItem()){
                case 0:
                    ((HomeThreeFragment_gz)fragments.get(mViewPager.getCurrentItem())).setDoubleClicked();
                    break;
                case 1:
                    ((HomeThreeFragment_ht)fragments.get(mViewPager.getCurrentItem())).setDoubleClicked();
                    break;
                case 2:
                    ((HomeThreeFragment_qz)fragments.get(mViewPager.getCurrentItem())).setDoubleClicked();
                    break;
                case 3:
                    ((HomeThreeFragment_lm)fragments.get(mViewPager.getCurrentItem())).setDoubleClicked();
                    break;
            }
        }
    }
    private void getFollowFollowers(){
        RetrofitUtil.getInstance().apiService()
                .getFollowFollowers(SharedUtils.getUserId()+"",1,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FollowFansList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowFansList result) {
                        if (result.getCode()==0) {
                            if(result.getData().size()>0){
//                                tv_add_dt.setVisibility(View.VISIBLE);
                            }else{
                                tv_add_dt.setVisibility(View.GONE);
                            }
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

    public static HomeThreeNewFragment newInstance() {
        HomeThreeNewFragment homeThreeNewFragment = new HomeThreeNewFragment();
        return homeThreeNewFragment;
    }

    List<Fragment> fragments = new ArrayList<>();

    private List<Fragment> getFragments() {

        HomeThreeFragment_gz fragment_gz = new HomeThreeFragment_gz();
//            Bundle bundle = new Bundle();
//            fragment.setArguments(bundle);
        fragments.add(fragment_gz);

        HomeThreeFragment_ht fragment_ht = new HomeThreeFragment_ht();
//            Bundle bundle = new Bundle();
//            fragment.setArguments(bundle);
        fragments.add(fragment_ht);

        HomeThreeFragment_qz fragment_qz = new HomeThreeFragment_qz();
//            Bundle bundle = new Bundle();
//            fragment.setArguments(bundle);
        fragments.add(fragment_qz);

        HomeThreeFragment_lm fragment_tg = new HomeThreeFragment_lm();
//            Bundle bundle = new Bundle();
//            fragment.setArguments(bundle);
        fragments.add(fragment_tg);
        return fragments;
    }

    private void initVP() {
        getFragments();
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
                if (position == 1) {
//                    tv_add_dt.setVisibility(View.VISIBLE);
                }
                if (position == 2) {
                    tv_add_dt.setVisibility(View.GONE);
                }
                if(position == 0){
                    getFollowFollowers();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        MagicIndicatorUtils.initMagicIndicator_2(getActivity(), mDataList, mMagicIndicator,mViewPager,
                new MagicIndicatorUtils.onClickInterface() {
            @Override
            public void bntClickListener() {
                setDoubleClicked();
            }
        });
        mViewPager.setCurrentItem(1);
    }


    @Override
    public void initImmersionBar() {

    }


    public static void xihuan(QueryPopularBean data, BaseQuickAdapter mAdapter){
        if(data.isIsLike()){
            HttpRequestUtils.postLikeCancel(data.getCircleId(),data.getMomentId(), data.getPublisherId(), new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        data.setIsLike(false);
                        data.setLikeCount((Integer.parseInt(data.getLikeCount())-1)+"");
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else{
            HttpRequestUtils.postLike(data.getCircleId(),data.getMomentId(), data.getPublisherId(), new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")) {
                        data.setIsLike(true);
                        data.setLikeCount((Integer.parseInt(data.getLikeCount())+1)+"");
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
