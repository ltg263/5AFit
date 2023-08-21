package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.GiftListData;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.view.adapter.UserLwMineAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwShouAdapter;
import com.jxkj.fit_5a.view.adapter.UserLwSongAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.open.im.IM;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

public class MineGiftFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.rl_shoudao)
    LinearLayout rl_shoudao;
    @BindView(R.id.ll_select)
    LinearLayout ll_select;
    @BindView(R.id.tv_dhlwj)
    TextView tv_dhlwj;
    @BindView(R.id.iv_select)
    ImageView iv_select;
    @BindView(R.id.tv_qx)
    TextView tv_qx;
    List<String> ids = new ArrayList<>();
    int page = 1;
    @Override
    protected int getContentView() {
        return R.layout.fragment_mine_gift;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);

        Bundle bundle = getArguments();
        int type=0;
        if (bundle != null) {
            type = bundle.getInt("type");
        }
        rl_shoudao.setVisibility(View.GONE);
        initRv(type);
        tv_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserLwShouAdapter.setSelectAll(false);
                mUserLwShouAdapter.setSelect(false);
                mUserLwShouAdapter.notifyDataSetChanged();
                ids.clear();
                ll_select.setVisibility(View.GONE);
                tv_dhlwj.setText("兑换礼物金");
            }
        });
        iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iv_select.isSelected()){
                    ids.clear();
                    iv_select.setSelected(false);
                    mUserLwShouAdapter.setSelectAll(false);
                }else{
                    for(int i= 0;i<mUserLwShouAdapter.getData().size();i++){
                        ids.add(mUserLwShouAdapter.getData().get(i).getId());
                    }
                    iv_select.setSelected(true);
                    mUserLwShouAdapter.setSelectAll(true);
                    setTv_dhlwjStr();
                }
                mUserLwShouAdapter.notifyDataSetChanged();
            }
        });
        tv_dhlwj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ll_select.getVisibility()==View.GONE){
                    ll_select.setVisibility(View.VISIBLE);
                    mUserLwShouAdapter.setSelect(true);
                    mUserLwShouAdapter.notifyDataSetChanged();
                    return;
                }
                if(ids.size()>0){
                    postUserGiftTransform();
                }
            }
        });
    }
    private void setTv_dhlwjStr(){
        double realPrice = 0.0;
        for(int i= 0;i<mUserLwShouAdapter.getData().size();i++){
            if(ids.contains(mUserLwShouAdapter.getData().get(i).getId())){
                realPrice = realPrice+Double.parseDouble(mUserLwShouAdapter.getData().get(i).getRealPrice());
            }
        }
        tv_dhlwj.setText("兑换礼物金     "+realPrice);
    }

    UserLwShouAdapter mUserLwShouAdapter;
    UserLwSongAdapter mUserLwSongAdapter;
    UserLwMineAdapter mUserLwMineAdapter;
    private void initRv(int type) {
        if(type==0){
            mUserLwShouAdapter = new UserLwShouAdapter(null);
            mUserLwShouAdapter.setSelect(false);
            mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            mRvList.setHasFixedSize(true);
            mRvList.setAdapter(mUserLwShouAdapter);
            mUserLwShouAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    ImageView iv = ((ImageView)view);
                    if(iv.isSelected()){
                        iv.setSelected(false);
                        ids.remove(mUserLwShouAdapter.getData().get(position).getId());
                    }else{
                        ids.add(mUserLwShouAdapter.getData().get(position).getId());
                        iv.setSelected(true);
                    }
                    setTv_dhlwjStr();
                }
            });
            rl_shoudao.setVisibility(View.VISIBLE);
            getUserGiftLogList(false);
        }else if(type ==1){
            mUserLwSongAdapter = new UserLwSongAdapter(null);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRvList.setHasFixedSize(true);
            mRvList.setAdapter(mUserLwSongAdapter);
            getUserGiftLogList(true);
        }else{
            mUserLwMineAdapter = new UserLwMineAdapter(null);
            mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            mRvList.setHasFixedSize(true);
            mRvList.setAdapter(mUserLwMineAdapter);
            getUserGiftList();
        }
    }

    @Override
    public void initImmersionBar() {

    }

    private void postUserGiftTransform() {
        RetrofitUtil.getInstance().apiService()
                .postUserGiftTransform(ids)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("兑换成功");
                            getUserGiftLogList(false);
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

    private void getUserGiftList() {
        RetrofitUtil.getInstance().apiService()
                .getUserGiftList(true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GiftListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GiftListData> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData().getList().size()>0){
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                                mUserLwMineAdapter.setNewData(result.getData().getList());
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
    private void getUserGiftLogList(boolean flag) {
        RetrofitUtil.getInstance().apiService()
                .getUserGiftLogList(flag,1,page,100)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GiftLogListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GiftLogListData> result) {
                        if(isDataInfoSucceed(result)){
                            if(result.getData().getList().size()>0) {
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mLvNot.setVisibility(View.GONE);
                                if (flag) {
                                    mUserLwSongAdapter.setNewData(result.getData().getList());
                                } else {
                                    mUserLwShouAdapter.setNewData(result.getData().getList());
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

}
