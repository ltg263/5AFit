package com.jxkj.fit_5a.view.activity.exercise;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.entity.AdListData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.activity.mine.JiaoXueSpXpActivity;
import com.jxkj.fit_5a.view.adapter.MineRwzxDzAdapter;
import com.jxkj.fit_5a.view.fragment.HomeTwoFragment;
import com.jxkj.fit_5a.view.search.SearchGoodsActivity;
import com.jxkj.fit_5a.view.search.SearchResultGoodsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskSelectionOneActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private MineRwzxDzAdapter mMineRwzxDzAdapter;
    private String exercise_type;


    @Override
    protected int getContentView() {
        return R.layout.activity_task_selection_one;
    }

    @Override
    protected void initViews() {
        exercise_type = getIntent().getStringExtra("exercise_type");
        mMineRwzxDzAdapter = new MineRwzxDzAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineRwzxDzAdapter);
        mMineRwzxDzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mMineRwzxDzAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (HomeTwoFragment.isYdQuanXian(TaskSelectionOneActivity.this)) {
                    return;
                }
                ConstValues_Ly.DEVICE_IMG = mMineRwzxDzAdapter.getData().get(position).getImg_rs();
                ConstValues_Ly.DEVICE_TYPE_ID_URL = mMineRwzxDzAdapter.getData().get(position).getId_rw()+"";
                FacilityAddSbActivity.getBluetoothChannel(TaskSelectionOneActivity.this,mMineRwzxDzAdapter.getData().get(position).getName_rw(),tv_title);
            }
        });
        getAdList();
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
                        if (isDataInfoSucceed(result)) {
                            mMineRwzxDzAdapter.setList(result.getData().getList());
                            getCircleTaskList();
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
    @OnClick({R.id.iv_back,R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                IntentUtils.getInstence().intent(this,
                        SearchGoodsActivity.class,"searchType", SearchResultGoodsActivity.tabListBlq[4]);
                break;
        }
    }
    private void getAdList() {
        RetrofitUtil.getInstance().apiService()
                .getAdList("2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<AdListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AdListData> result) {
                        if (isDataInfoSucceed(result)) {
                            List<AdListData.ListBean> data = result.getData().getList();
                            initBannerOne(data);
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
    private void initBannerOne(List<AdListData.ListBean> data ) {
        ArrayList<String> list_path = new ArrayList<>();
        if(data!=null){
            for(int i= 0;i<data.size();i++){
                list_path.add(data.get(i).getImgUrl());
            }
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // "{\"title\":\"炫彩哑铃系列 全身训练\",\"mid\":1693983856127000,\"pid\":-1}",
                String mCon = data.get(position).getContent();
                try {
                    JSONObject object = new JSONObject(mCon);
                    IntentUtils.getInstence().intent(TaskSelectionOneActivity.this, JiaoXueSpXpActivity.class,
                            "momentId",object.getString("mid"),
                            "publisherId",object.getString("pid"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });


        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(list_path);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }
    private void getCircleTaskList() {
        RetrofitUtil.getInstance().apiService()
                .getUserTaskList(6,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TaskListBase>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TaskListBase> result) {
                        if(isDataInfoSucceed(result)){
                            mMineRwzxDzAdapter.setNewData(result.getData().getList());
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
