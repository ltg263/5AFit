package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.view.activity.mine.MineHomeActivity;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserTopAdapter extends BaseQuickAdapter<CircleQueryJoinedBean.ListBean, BaseViewHolder> {
    public UserTopAdapter(@Nullable List<CircleQueryJoinedBean.ListBean> data) {
        super(R.layout.item_user_top, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleQueryJoinedBean.ListBean item) {
        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.iv_shop_pic));
        helper.setVisible(R.id.iv_jr1,false).setVisible(R.id.iv_jr,false);
        if(!(mContext instanceof MineHomeActivity)){
//            if(!item.isJoin()){
//                helper.setVisible(R.id.iv_jr1,true).setVisible(R.id.iv_jr,true);
//                helper.addOnClickListener(R.id.iv_jr1);
//            }
        }
        helper.setText(R.id.tv_title,item.getName()).setText(R.id.tv_num,item.getMemberCount()+"人");

    }

}
