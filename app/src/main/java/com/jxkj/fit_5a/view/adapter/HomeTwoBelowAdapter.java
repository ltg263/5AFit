package com.jxkj.fit_5a.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeTwoBelowAdapter extends BaseQuickAdapter<RankStatsData.CaloriesRankingListBean, BaseViewHolder> {
    public HomeTwoBelowAdapter(@Nullable List<RankStatsData.CaloriesRankingListBean> data) {
        super(R.layout.item_home_two_below, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RankStatsData.CaloriesRankingListBean item) {
        if(helper.getLayoutPosition()==0){
            Glide.with(mContext).load(R.drawable.icon_home_two_1).into((ImageView) helper.getView(R.id.iv_1));
            helper.setGone(R.id.tv,false).setGone(R.id.iv_1,true);
        }else if(helper.getLayoutPosition()==1){
            Glide.with(mContext).load(R.drawable.icon_home_two_2).into((ImageView) helper.getView(R.id.iv_1));
            helper.setGone(R.id.tv,false).setGone(R.id.iv_1,true);
        }else if(helper.getLayoutPosition()==2){
            Glide.with(mContext).load(R.drawable.icon_home_two_3).into((ImageView) helper.getView(R.id.iv_1));
            helper.setGone(R.id.tv,false).setGone(R.id.iv_1,true);
        }else{
            helper.setGone(R.id.tv,true).setGone(R.id.iv_1,false);
            helper.setText(R.id.tv,"No."+item.getRanking());
        }
        if(item.getUser()!=null){
            helper.setText(R.id.tv_1,item.getUser().getNickName()).setText(R.id.tv_2,item.getCalories()+"kcal").setText(R.id.tv_3,item.getLikeCount()+"");
            GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_2));
        }
        Glide.with(mContext).load(R.drawable.icon_zan_no).into((ImageView) helper.getView(R.id.iv_3));
        if(item.isLike()){
            Glide.with(mContext).load(R.drawable.icon_zan_yes).into((ImageView) helper.getView(R.id.iv_3));
        }
        helper.addOnClickListener(R.id.ll_dianzan);
        helper.getView(R.id.ll_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHomeActivity.startActivity(mContext,item.getUserId()+"");
            }
        });
    }

}
