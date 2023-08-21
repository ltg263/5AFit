package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.CommunityHomeInfoBean;
import com.jxkj.fit_5a.entity.HotTopicBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeTop_HtAdapter extends BaseQuickAdapter<HotTopicBean, BaseViewHolder> {
    public HomeThreeTop_HtAdapter(@Nullable List<HotTopicBean> data) {
        super(R.layout.item_home_three_top_ht, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotTopicBean item) {
        if(item==null){
            helper.setGone(R.id.rv,false).setVisible(R.id.all,true);
        }else{
            helper.setGone(R.id.rv,true).setVisible(R.id.all,false);
            helper.setText(R.id.tv_topic_name,item.getName()).setText(R.id.tv_topic_num,item.getArticlesCount()+"人围观");
            GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_topic_img));
        }
    }

}
