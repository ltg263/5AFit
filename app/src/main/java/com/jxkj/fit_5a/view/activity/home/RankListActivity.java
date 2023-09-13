package com.jxkj.fit_5a.view.activity.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.view.adapter.HomeTwoBelowAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RankListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_two_ri)
    TextView mTvTwoRi;
    @BindView(R.id.tv_two_zhou)
    TextView mTvTwoZhou;
    @BindView(R.id.tv_two_yue)
    TextView mTvTwoYue;
    @BindView(R.id.tv_phb_2)
    TextView mTvPhb2;
    @BindView(R.id.iv_phb_2)
    ImageView mIvPhb2;
    @BindView(R.id.tv_phb_22)
    TextView mTvPhb22;
    @BindView(R.id.tv_phb_1)
    TextView mTvPhb1;
    @BindView(R.id.iv_phb_1)
    ImageView mIvPhb1;
    @BindView(R.id.tv_phb_11)
    TextView mTvPhb11;
    @BindView(R.id.tv_phb_3)
    TextView mTvPhb3;
    @BindView(R.id.iv_phb_3)
    ImageView mIvPhb3;
    @BindView(R.id.tv_phb_33)
    TextView mTvPhb33;
    @BindView(R.id.tv_zs)
    TextView mTvZs;
    @BindView(R.id.rv_two_list)
    RecyclerView mRvTwoList;
    @BindView(R.id.tv_all_guo)
    TextView tv_all_guo;
    @BindView(R.id.tv_tong_cheng)
    TextView tv_tong_cheng;
    @BindView(R.id.view_all_guo)
    View view_all_guo;
    @BindView(R.id.view_tong_cheng)
    View view_tong_cheng;

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_mingc)
    TextView mTvMingc;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_dll)
    TextView mTvDll;
    @BindView(R.id.tv_zan)
    TextView mTvZan;
    int typeD = 0;
    boolean isQuanGuo = true;
    String cityAdCode = "";
    private HomeTwoBelowAdapter mHomeTwoBelowAdapter;
    @Override
    protected int getContentView() {
        return R.layout.activity_rank_list;
    }

    @Override
    protected void initViews() {
        cityAdCode = SharedUtils.singleton().get(ConstValues.CITY_AD_CODE,"");
        mTvTitle.setText("卡路里排名");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mHomeTwoBelowAdapter = new HomeTwoBelowAdapter(null);
        mRvTwoList.setLayoutManager(new LinearLayoutManager(this));
        mRvTwoList.setHasFixedSize(true);
        mRvTwoList.setAdapter(mHomeTwoBelowAdapter);

        mHomeTwoBelowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                getStatsZan(mHomeTwoBelowAdapter.getData().get(position).getId()
                        , typeD, mHomeTwoBelowAdapter.getData().get(position).isLike());
            }
        });
        getRankList(3);
    }


    @OnClick({R.id.tv_two_ri,R.id.tv_all_guo,R.id.tv_tong_cheng, R.id.tv_two_zhou, R.id.tv_two_yue,R.id.tv_zan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all_guo:
                isQuanGuo = true;
                tv_all_guo.setTextColor(getColor(R.color.color_4555a3));
                tv_tong_cheng.setTextColor(getColor(R.color.color_333333));
                view_all_guo.setVisibility(View.VISIBLE);
                view_tong_cheng.setVisibility(View.INVISIBLE);
                getRankStatsList(typeD);
                break;
            case R.id.tv_tong_cheng:
                isQuanGuo = false;
                tv_all_guo.setTextColor(getColor(R.color.color_333333));
                tv_tong_cheng.setTextColor(getColor(R.color.color_4555a3));
                view_all_guo.setVisibility(View.INVISIBLE);
                view_tong_cheng.setVisibility(View.VISIBLE);
                getRankStatsList(typeD);
                break;
            case R.id.tv_two_ri:
                getRankList(1);
                break;
            case R.id.tv_two_zhou:
                getRankList(2);
                break;
            case R.id.tv_two_yue:
                getRankList(3);
                break;
//            case R.id.tv_zan:
//                getStatsZan(userData.getUserId()
//                        , typeD, mHomeTwoBelowAdapter.getData().get(position).isLike());
//                break;
        }
    }


    private void getRankList(int type) {
        RetrofitUtil.getInstance().apiService()
                .getRankList(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankListData> result) {
                        if(isDataInfoSucceed(result)){
                            getRankStatsList(type);
                            List<RankListData.ListBean> list = result.getData().getList();
                            if (list != null && list.size() > 0) {
                                getRankDetails(list.get(0).getId());
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
    RankStatsData.UserBean userData;
    private void getRankStatsList(int type) {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<RankStatsData>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            if(!isQuanGuo){
                mObservable = mApiService.getRankStatsList_city_al(type, cityAdCode)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }else{
                mObservable = mApiService.getRankStatsList_al(type)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        }else {
            if(!isQuanGuo){
                mObservable = mApiService.getRankStatsList_city(type, cityAdCode)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }else{
                mObservable = mApiService.getRankStatsList(type)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        }
        show();
        mObservable.subscribe(new Observer<Result<RankStatsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankStatsData> result) {
                        if (isDataInfoSucceed(result)) {
                            typeD = type;
                            userData = result.getData().getUser();
                            mTvName.setText(userData.getNickName());
                            mTvZan.setText(result.getData().getLikeCount());
                            mTvDll.setText(result.getData().getCalories()+"kcal");
                            mTvMingc.setText("未上榜");
                            if (result.getData().getRanking()!=0) {
                                mTvMingc.setText("No."+result.getData().getRanking());
                            }
//                            Glide.with(getActivity()).load(R.drawable.icon_zan_no).into((ImageView) helper.getView(R.id.iv_3));
//                            if(result.getData().isLike()){
//                                Glide.with(mContext).load(R.drawable.icon_zan_yes).into((ImageView) helper.getView(R.id.iv_3));
//                            }
                            GlideImageUtils.setGlideImage(RankListActivity.this,userData.getAvatar(),iv_head);
                            mTvTwoYue.setBackgroundColor(getResources().getColor(R.color.transparent));
                            mTvTwoZhou.setBackgroundColor(getResources().getColor(R.color.transparent));
                            mTvTwoRi.setBackgroundColor(getResources().getColor(R.color.transparent));
                            mTvTwoYue.setTextColor(getColor(R.color.color_000000));
                            mTvTwoZhou.setTextColor(getColor(R.color.color_000000));
                            mTvTwoRi.setTextColor(getColor(R.color.color_000000));
                            if (typeD == 3) {
                                mTvTwoYue.setTextColor(getColor(R.color.white));
                                mTvTwoYue.setBackground(getDrawable(R.drawable.bj_circle_theme_10));
                            }
                            if (typeD == 2) {
                                mTvTwoZhou.setTextColor(getColor(R.color.white));
                                mTvTwoZhou.setBackground(getDrawable(R.drawable.bj_circle_theme_10));
                            }
                            if (typeD == 1) {
                                mTvTwoRi.setTextColor(getColor(R.color.white));
                                mTvTwoRi.setBackground(getDrawable(R.drawable.bj_circle_theme_10));
                            }
                            mHomeTwoBelowAdapter.setNewData(result.getData().getCaloriesRankingList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }


    private void getRankDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getRankDetails(Integer.valueOf(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankDetailsData> result) {
                        if (isDataInfoSucceed(result)) {
                            List<RankDetailsData.RankRewardsBean> rankRewards = result.getData().getRankRewards();
                            if (rankRewards != null && rankRewards.size() > 0) {
                                for (int i = 0; i < rankRewards.size(); i++) {
                                    RankDetailsData.RankRewardsBean rankReward = rankRewards.get(i);
                                    switch (i) {
                                        case 0:
                                            mTvPhb1.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb1.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(RankListActivity.this, rankReward.getImgUrl(), mIvPhb1);
                                            mTvPhb11.setText(rankReward.getName());
                                            break;
                                        case 1:
                                            mTvPhb2.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb2.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(RankListActivity.this, rankReward.getImgUrl(), mIvPhb2);
                                            mTvPhb22.setText(rankReward.getName());
                                            break;
                                        case 2:
                                            mTvPhb3.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb3.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(RankListActivity.this, rankReward.getImgUrl(), mIvPhb3);
                                            mTvPhb33.setText(rankReward.getName());
                                            break;

                                    }
                                }
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
    private void getStatsZan(String calRankId, int dimension, boolean hasZan) {
        if (!hasZan) {
            ApiService mApiService = RetrofitUtil.getInstance().apiService();
            Observable<Result> mObservable;
            if(StringUtil.getLoginUserType().equals("1")){
                mObservable = mApiService.getStatsZan_al(calRankId, dimension);
            }else {
                mObservable = mApiService.getStatsZan(calRankId, dimension);
            }
            mObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (isDataInfoSucceed(result)) {
                                getRankStatsList(typeD);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            ApiService mApiService = RetrofitUtil.getInstance().apiService();
            Observable<Result> mObservable;
            if(StringUtil.getLoginUserType().equals("1")){
                mObservable = mApiService.getCancelStatsZan_al(calRankId, dimension);
            }else {
                mObservable = mApiService.getCancelStatsZan(calRankId, dimension);
            }
            mObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (isDataInfoSucceed(result)) {
                                getRankStatsList(typeD);
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
    }

}
