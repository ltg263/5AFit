package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.FavoriteQueryList;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.jxkj.fit_5a.view.adapter.PopupWindwSbAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserScActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    String localMinId = "0";
    String userId = "";
    private HomeDynamicAdapter mHomeDynamicAdapter;
    List<QueryPopularBean> data;
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("Ta的收藏");
        mTvRighttext.setVisibility(View.INVISIBLE);
        userId = getIntent().getStringExtra("userId");
        if(userId.equals(SharedUtils.getUserId()+"")){
            mTvTitle.setText("我的动态收藏");
            mTvRighttext.setVisibility(View.VISIBLE);
        }
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mTvRighttext.setText("更多收藏");
        mTvRighttext.setTextColor(getResources().getColor(R.color.color_333333));
        mTvRighttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UserScActivity_gf.startActivity(UserScActivity.this,userId);
                if (distancePopWindow == null)
                    showDistancePopup();
                else {
                    distancePopWindow.showAsDropDown(mTvRighttext);
                }
            }
        });
        mHomeDynamicAdapter = new HomeDynamicAdapter(data);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeDynamicAdapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(userId.equals(SharedUtils.getUserId()+"")){
                    getFavoriteQueryOwnA();
                }else{
                    getFavoriteQueryOwn();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                localMinId = "0";
                if(userId.equals(SharedUtils.getUserId()+"")){
                    getFavoriteQueryOwnA();
                }else{
                    getFavoriteQueryOwn();
                }
            }
        });
        if(userId.equals(SharedUtils.getUserId()+"")){
            getFavoriteQueryOwnA();
        }else{
            getFavoriteQueryOwn();
        }
    }
    private CustomPopWindow distancePopWindow;
    private void showDistancePopup() {
        List<DeviceTypeData.ListBean> dataSbType = new ArrayList<>();
        dataSbType.add(new DeviceTypeData.ListBean("商品"));
        dataSbType.add(new DeviceTypeData.ListBean("教学视频"));
        View view = getLayoutInflater().inflate(R.layout.popup_window_sb, null, false);
        RecyclerView mRvList = view.findViewById(R.id.rv_list);
        PopupWindwSbAdapter mPopupWindwSbAdapter = new PopupWindwSbAdapter(dataSbType);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mPopupWindwSbAdapter);

        mPopupWindwSbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                distancePopWindow.dissmiss();
                if(position == 0){
                    ShoppingScActivity.intentStartActivity(UserScActivity.this,"我的收藏");
                }
                if(position == 1){
                    UserShiPinScActivity.startActivity(UserScActivity.this,userId);
                }
            }
        });

        //创建并显示popWindow
        distancePopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        rbDistance.setChecked(false);
                    }
                })
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(mTvRighttext, 0, 0);//显示PopupWindow
    }

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }


    private void getFavoriteQueryOwnA() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQueryOwn(localMinId,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<FavoriteQueryList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<FavoriteQueryList> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            data = new ArrayList<>();
                            for(int i= 0;i<result.getData().size();i++){
                                data.add(result.getData().get(i).getMoment());
                            }
                            if(localMinId.equals("0")){
                                mHomeDynamicAdapter.setNewData(data);
                            }else{
                                if(data.size()>0){
                                    mHomeDynamicAdapter.addData(data);
                                }
                            }

                            if(result.getData().size()==0 || result.getData().size()<10){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                localMinId = result.getData().get(result.getData().size()-1).getIncrementId();
                            }

                            if(mHomeDynamicAdapter.getData().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                            }else{
                                mLvNot.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }
    private void getFavoriteQueryOwn() {
        RetrofitUtil.getInstance().apiService()
                .getFavoriteQueryOwn(userId, localMinId,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<FavoriteQueryList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<FavoriteQueryList> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData()==null){
                                return;
                            }
                            data = new ArrayList<>();
                            for(int i= 0;i<result.getData().size();i++){
                                data.add(result.getData().get(i).getMoment());
                            }
                            if(localMinId.equals("0")){
                                mHomeDynamicAdapter.setNewData(data);
                            }else{
                                if(data.size()>0){
                                    mHomeDynamicAdapter.addData(data);
                                }
                            }

                            if(result.getData().size()==0 || result.getData().size()<10){
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }else{
                                localMinId = result.getData().get(result.getData().size()-1).getIncrementId();
                            }

                            if(mHomeDynamicAdapter.getData().size()>0){
                                mLvNot.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                            }else{
                                mLvNot.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }



    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, UserScActivity.class);
        if(StringUtil.isNotBlank(userId)){
            intent.putExtra("userId", userId);
        }
        mContext.startActivity(intent);
    }
}
