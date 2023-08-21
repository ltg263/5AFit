package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.GameCompleteBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineGameRankingAdapter extends BaseQuickAdapter<GameCompleteBean.GameRankingsBean, BaseViewHolder> {
    public MineGameRankingAdapter(@Nullable List<GameCompleteBean.GameRankingsBean> data) {
        super(R.layout.item_game_rankings, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GameCompleteBean.GameRankingsBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head));
        helper.setText(R.id.tv_ranking,item.getRanking()).setText(R.id.tv_name,item.getUser().getNickName())
            .setText(R.id.tv_xq,item.getDistance()+"km"+"   "+item.getCalories()+"Cal");
    }

}
