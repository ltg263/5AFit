package com.jxkj.fit_5a.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.view.PileAvertView;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class InterestListAdapter extends BaseQuickAdapter<CircleQueryJoinedBean.ListBean, BaseViewHolder> {
    public InterestListAdapter(@Nullable List<CircleQueryJoinedBean.ListBean> data) {
        super(R.layout.item_interest_all, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleQueryJoinedBean.ListBean item) {
        helper.setText(R.id.tv1, item.getName())
                .setText(R.id.tv2, item.getExplain())
                .setText(R.id.tv3, "共"+item.getMemberCount() + "人")
                .setText(R.id.tv4, item.getMomentCount() + "条");
        GlideImgLoader.loadImageViewRadius(mContext, item.getImgUrl(), 10, helper.getView(R.id.iv));
        if (item.isJoin()) {
            helper.setBackgroundRes(R.id.tv5, R.drawable.btn_shape_bj_fd1de_25);
            helper.setText(R.id.tv5, "已加入");
        } else {
            helper.setBackgroundRes(R.id.tv5, R.drawable.btn_shape_bj_theme_25);
            helper.setText(R.id.tv5, "+ 加入");
        }

        helper.getView(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isJoin()){
                    getCircleQuit(item);
                }else{
                    getCircleJoin(item);
                }
            }
        });

        PileAvertView pile_view_1 = helper.getView(R.id.pile_view_1);
        List<String> urls=new ArrayList<>();
        urls.clear();
//        if(item.getMembers()!=null){
//            for(int i=0;i<data.getMembers().size();i++){
//                if(data.getMembers().get(i).getUser()!=null){
//                    urls.add(data.getMembers().get(i).getUser().getAvatar());
//                }
//            }
//        }
        pile_view_1.setAvertImages(urls);
    }

    private void getCircleQuit(CircleQueryJoinedBean.ListBean item) {
        RetrofitUtil.getInstance().apiService()
                .getCircleQuit(item.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.getCode()==0) {
                            item.setJoin(false);
                            item.setMemberCount(item.getMomentCount()-1);
                            notifyDataSetChanged();
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
    private void getCircleJoin(CircleQueryJoinedBean.ListBean item) {
        RetrofitUtil.getInstance().apiService()
                .getCircleJoin(item.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.getCode()==0) {
                            item.setJoin(true);
                            item.setMemberCount(item.getMomentCount()+1);
                            notifyDataSetChanged();
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
