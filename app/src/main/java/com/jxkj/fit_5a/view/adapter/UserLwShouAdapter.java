package com.jxkj.fit_5a.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.GiftLogListData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserLwShouAdapter extends BaseQuickAdapter<GiftLogListData.ListBean, BaseViewHolder> {
    boolean isSelect = false;
    boolean isSelectAll = false;

    public void setSelectAll(boolean selectAll) {
        isSelectAll = selectAll;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public UserLwShouAdapter(@Nullable List<GiftLogListData.ListBean> data) {
        super(R.layout.item_user_lw_shou, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GiftLogListData.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getImgUrl(),helper.getView(R.id.iv_img));
        GlideImageUtils.setGlideImage(mContext,item.getAvatar(),helper.getView(R.id.icon_head));
        helper.setVisible(R.id.rl_select_yes,false).addOnClickListener(R.id.iv_select)
                .setVisible(R.id.rl_select_no,true)
                .setVisible(R.id.iv_select,false);
        helper.setText(R.id.tv_user,item.getNickName())
                .setText(R.id.tv_time,item.getCreateTime())
                .setText(R.id.tv_user_lwj,item.getWorth());
        ImageView iv_select = helper.getView(R.id.iv_select);
        iv_select.setSelected(false);
        if(isSelect){
            helper.setVisible(R.id.rl_select_yes,true)
                    .setVisible(R.id.rl_select_no,false)
                    .setVisible(R.id.iv_select,true);
        }
        if(isSelectAll){
            iv_select.setSelected(true);
        }


    }

}
