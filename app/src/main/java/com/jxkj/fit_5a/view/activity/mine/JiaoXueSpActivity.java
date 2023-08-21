package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.ClassificationAllData;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean;
import com.jxkj.fit_5a.entity.TeachingMomentBean;
import com.jxkj.fit_5a.view.adapter.FacilityAddSbAdapter;
import com.jxkj.fit_5a.view.adapter.HomeJiaoXueSpAdapter;
import com.jxkj.fit_5a.view.adapter.JiaoXueTitleAdapter;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JiaoXueSpActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.rl_jiaoxue)
    RelativeLayout rlJiaoXue;
    @BindView(R.id.iv_title_f)
    ImageView mIvTitleF;
    @BindView(R.id.tv_title_f)
    TextView mTvTitleF;
    @BindView(R.id.ll_zuo_f)
    LinearLayout mLlZuoF;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.tv_yuyinshezhi)
    TextView mTvYuyinshezhi;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rv_sb_list)
    RecyclerView mRvSbList;
    @BindView(R.id.rv_title)
    RecyclerView mRvTitle;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private FacilityAddSbAdapter mFacilityAddSbAdapter;
    private JiaoXueTitleAdapter mJiaoXueTitleAdapter;
    private HomeJiaoXueSpAdapter mHomeJiaoXueSpAdapter;
    int page = 1;
    String keyword = null;
    @Override
    protected int getContentView() {
        return R.layout.activity_jiao_xue_sp;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("教学视频");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        mIvBack.setOnClickListener(v -> finish());
        rlJiaoXue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstence().intent(JiaoXueSpActivity.this,
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[4]);
            }
        });
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getTeachingMomentQuery();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getTeachingMomentQuery();
            }
        });

        mFacilityAddSbAdapter = new FacilityAddSbAdapter(null);
        mFacilityAddSbAdapter.setFang(true);
        mRvSbList.setAdapter(mFacilityAddSbAdapter);
        mFacilityAddSbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                deviceTypeId = mFacilityAddSbAdapter.getData().get(position).getId();
                getTeachingMomentQuery();
            }
        });

        mJiaoXueTitleAdapter = new JiaoXueTitleAdapter(null);
        mJiaoXueTitleAdapter.setPos(0);
        mRvTitle.setAdapter(mJiaoXueTitleAdapter);
        mJiaoXueTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mJiaoXueTitleAdapter.setPos(position);
                mJiaoXueTitleAdapter.notifyDataSetChanged();
                classificationId = mJiaoXueTitleAdapter.getData().get(position).getId();
                getTeachingMomentQuery();
            }
        });

        mHomeJiaoXueSpAdapter = new HomeJiaoXueSpAdapter(null);
        mRvContent.setAdapter(mHomeJiaoXueSpAdapter);
        mHomeJiaoXueSpAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(JiaoXueSpActivity.this,JiaoXueSpXpActivity.class,
                        "momentId",mHomeJiaoXueSpAdapter.getData().get(position).getMomentId()+"",
                        "publisherId",mHomeJiaoXueSpAdapter.getData().get(position).getPublisherId()+"");
            }
        });
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
                            mFacilityAddSbAdapter.setNewData(result.getData().getList());
                            deviceTypeId = mFacilityAddSbAdapter.getData().get(0).getId();
                            getClassificationAll();
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
    private void getClassificationAll() {
        RetrofitUtil.getInstance().apiService()
                .getClassificationQuery()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<ClassificationAllData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<ClassificationAllData>> result) {
                        if (isDataInfoSucceed(result)) {
                            mJiaoXueTitleAdapter.setNewData(result.getData());
                            classificationId = mJiaoXueTitleAdapter.getData().get(0).getId();
                            getTeachingMomentQuery();
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
    int classificationId,deviceTypeId;
    private void getTeachingMomentQuery() {
        RetrofitUtil.getInstance().apiService()
                .getTeachingMomentQuery(keyword,classificationId,deviceTypeId,page,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<TeachingMomentBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<TeachingMomentBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            List<TeachingMomentBean> data = result.getData();
                            if(page==1){
                                mHomeJiaoXueSpAdapter.setNewData(data);
                            }else{
                                mHomeJiaoXueSpAdapter.addData(data);
                            }
                            mRvContent.setVisibility(View.GONE);
                            tv_not.setVisibility(View.VISIBLE);
                            if(mHomeJiaoXueSpAdapter.getData().size()>0){
                                mRvContent.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                            }
                            if(data.size() < ConstValues.PAGE_SIZE){
                                mRefreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                });
    }
}
