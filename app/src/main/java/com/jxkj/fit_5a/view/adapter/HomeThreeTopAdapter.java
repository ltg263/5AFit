package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CircleDetailsBean;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeTopAdapter extends BaseQuickAdapter<CircleDetailsBean, BaseViewHolder> {
    public HomeThreeTopAdapter(@Nullable List<CircleDetailsBean> data) {
        super(R.layout.item_home_three_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleDetailsBean item) {
        if(item==null){
            helper.setVisible(R.id.tv,false);
            helper.setImageResource(R.id.iv_img,R.drawable.ic_home_top_all);
        }else{
            helper.setText(R.id.tv,item.getName());
            GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
            helper.setVisible(R.id.tv,true);
        }
        switch (helper.getLayoutPosition()){
            case 0:
            case 3:
                helper.setBackgroundRes(R.id.rl_gj,R.drawable.bj_circle_line_edeef0);
                break;
            case 1:
                helper.setBackgroundRes(R.id.rl_gj,R.drawable.bj_circle_line_ffd2d2);
                break;
            case 2:
                helper.setBackgroundRes(R.id.rl_gj,R.drawable.bj_circle_line_cfe8f8);
                break;
        }
    }

}
