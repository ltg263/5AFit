package com.jxkj.fit_5a.view.activity.mine;

import android.os.Bundle;
import android.util.Log;
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
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.view.CustomPopWindow_spsx;
import com.jxkj.fit_5a.entity.ClassificationAllData;
import com.jxkj.fit_5a.entity.DeviceTypeCoachData;
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
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.rl_xuanze_1)
    RelativeLayout rlXuanze1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.rl_xuanze_2)
    RelativeLayout rlXuanze2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.rl_xuanze_3)
    RelativeLayout rlXuanze3;
    @BindView(R.id.ll_shaixuan)
    LinearLayout ll_shaixuan;
    @BindView(R.id.iv_shaixuan)
    ImageView ivShaixuan;
    private FacilityAddSbAdapter mFacilityAddSbAdapter;
    private JiaoXueTitleAdapter mJiaoXueTitleAdapter;
    private HomeJiaoXueSpAdapter mHomeJiaoXueSpAdapter;
    int page = 1;

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
                        SearchGoodsActivity.class, "searchType", SearchResultGoodsActivity.tabListBlq[4]);
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
                IntentUtils.getInstence().intent(JiaoXueSpActivity.this, JiaoXueSpXpActivity.class,
                        "momentId", mHomeJiaoXueSpAdapter.getData().get(position).getMomentId() + "",
                        "publisherId", mHomeJiaoXueSpAdapter.getData().get(position).getPublisherId() + "");
            }
        });
        queryDeviceTypeLists();
        queryDeviceCoachLists();
    }

    private void queryDeviceCoachLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceCoachLists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<DeviceTypeCoachData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<DeviceTypeCoachData>> result) {
                        if (isDataInfoSucceed(result)) {
                            list_shaixuan_jl.clear();
                            list_shaixuan_jl.addAll(result.getData());
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
                        if (isDataInfoSucceed(result)) {
                            list_shaixuan_sb.clear();
                            list_shaixuan_sb.addAll(result.getData().getList());
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

    Integer classificationId, deviceTypeId,difficulty,sortType,videoMaxDuration,videoMinDuration;
    Long starCoachId;

    private void getTeachingMomentQuery() {
        RetrofitUtil.getInstance().apiService()
                .getTeachingMomentQuery(classificationId, difficulty,sortType,deviceTypeId,starCoachId,videoMaxDuration,videoMinDuration,
                        page, ConstValues.PAGE_SIZE)
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
                            if (page == 1) {
                                mHomeJiaoXueSpAdapter.setNewData(data);
                            } else {
                                mHomeJiaoXueSpAdapter.addData(data);
                            }
                            mRvContent.setVisibility(View.GONE);
                            tv_not.setVisibility(View.VISIBLE);
                            if (mHomeJiaoXueSpAdapter.getData().size() > 0) {
                                mRvContent.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                            }
                            if (data.size() < ConstValues.PAGE_SIZE) {
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
    List<String> listsSelect = new ArrayList<>();
    List<DeviceTypeCoachData> list_shaixuan_jl = new ArrayList<>();
    List<DeviceTypeData.ListBean> list_shaixuan_sb = new ArrayList<>();
    String[] list_shaixuan_nd ={"全部","入门","初级","中级","中高级","进阶","高级"};
    String[] list_shaixuan_sj ={"全部","≤5min","5-10min","10-15min","15-20min","20-25min","25-30min","≥30min"};
    String[] list_shaixuan_zx ={"默认","最新","最热"};
    @OnClick({R.id.rl_xuanze_1, R.id.rl_xuanze_2, R.id.rl_xuanze_3, R.id.iv_shaixuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_xuanze_1:
                iv1.setImageResource(R.drawable.icon_jiantou_shangla_yes);
                CustomPopWindow_spsx.initPopupWindow(this, rlXuanze1, Arrays.asList(list_shaixuan_nd),
                        new CustomPopWindow_spsx.PopWindowInterface() {
                            @Override
                            public void getPosition(int position) {
                                iv1.setImageResource(R.drawable.icon_jiantou_xiala_no);
                                if(position!=-1){
                                    tv1.setTextColor(getColor(R.color.color_4555a3));
                                    tv1.setText(list_shaixuan_nd[position]);
                                    if(position == 0){
                                        difficulty = null;
                                    }else{
                                        difficulty = position;
                                    }
                                    getTeachingMomentQuery();
                                }
                            }
                        });
                break;
            case R.id.rl_xuanze_2:
                iv2.setImageResource(R.drawable.icon_jiantou_shangla_yes);
                CustomPopWindow_spsx.initPopupWindow(this, rlXuanze2, Arrays.asList(list_shaixuan_sj),
                        new CustomPopWindow_spsx.PopWindowInterface() {
                            @Override
                            public void getPosition(int position) {
                                iv2.setImageResource(R.drawable.icon_jiantou_xiala_no);
                                if(position!=-1) {
                                    tv2.setTextColor(getColor(R.color.color_4555a3));
                                    tv2.setText(list_shaixuan_sj[position]);
                                    setTimeUi(position);

                                    getTeachingMomentQuery();
                                }
                            }
                        });
                break;
            case R.id.rl_xuanze_3:
                iv3.setImageResource(R.drawable.icon_jiantou_shangla_yes);
                CustomPopWindow_spsx.initPopupWindow(this, rlXuanze3, Arrays.asList(list_shaixuan_zx),
                        new CustomPopWindow_spsx.PopWindowInterface() {
                            @Override
                            public void getPosition(int position) {
                                iv3.setImageResource(R.drawable.icon_jiantou_xiala_no);
                                if(position!=-1) {
                                    tv3.setTextColor(getColor(R.color.color_4555a3));
                                    tv3.setText(list_shaixuan_zx[position]);
                                    sortType = position+1;
                                    getTeachingMomentQuery();
                                }
                            }
                        });
                break;
            case R.id.iv_shaixuan:
                CustomPopWindow_spsx.initPopupWindowAll(this, ll_shaixuan,listsSelect,
                        list_shaixuan_sb,
                        Arrays.asList(list_shaixuan_nd),Arrays.asList(list_shaixuan_sj),
                        list_shaixuan_jl,
                        new CustomPopWindow_spsx.PopWindowInterfaceAll() {
                            @Override
                            public void getPosition(List<String> lists) {
                                Log.w("--","lists"+lists);
                                listsSelect = new ArrayList<>(lists);
                                Log.w("--","listsSelect"+listsSelect);
                                if(listsSelect.get(0)==null || listsSelect.get(0).equals("null")){
                                    deviceTypeId = null;
                                }else{
                                    deviceTypeId = Integer.parseInt(listsSelect.get(0));
                                }

                                tv1.setTextColor(getColor(R.color.color_4555a3));
                                tv1.setText(list_shaixuan_nd[Integer.parseInt(listsSelect.get(1))]);

                                if(Integer.parseInt(listsSelect.get(1))==0){
                                    difficulty = null;
                                }else{
                                    difficulty = Integer.parseInt(listsSelect.get(1));
                                }

                                tv2.setTextColor(getColor(R.color.color_4555a3));
                                tv2.setText(list_shaixuan_sj[Integer.parseInt(listsSelect.get(2))]);
                                setTimeUi(Integer.parseInt(listsSelect.get(2)));

                                if(listsSelect.get(3)==null || listsSelect.get(3).equals("null")){
                                    starCoachId = null;
                                }else{
                                    starCoachId = Long.parseLong(listsSelect.get(3));
                                }
                                getTeachingMomentQuery();
                            }
                        });
                break;
        }
    }

    private void setTimeUi(int position) {
        switch (position){
            case 0:
                videoMaxDuration = null;
                videoMinDuration = null;
                break;
            case 1:
                videoMinDuration = null;
                videoMaxDuration = 300;
                break;
            case 2:
                videoMinDuration = 300;
                videoMaxDuration = 600;
                break;
            case 3:
                videoMinDuration = 600;
                videoMaxDuration = 900;
                break;
            case 4:
                videoMinDuration = 900;
                videoMaxDuration = 1200;
                break;
            case 5:
                videoMinDuration = 1200;
                videoMaxDuration = 1500;
                break;
            case 6:
                videoMinDuration = 1500;
                videoMaxDuration = 1800;
                break;
            case 7:
                videoMinDuration = 1800;
                videoMaxDuration = null;
                break;
        }
    }
}
