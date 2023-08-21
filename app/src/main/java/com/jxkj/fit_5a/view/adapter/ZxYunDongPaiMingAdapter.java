
package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ZxYunDongPaiMingAdapter extends BaseQuickAdapter<MapDetailsBean.UserBean, BaseViewHolder> {
    public ZxYunDongPaiMingAdapter(@Nullable List<MapDetailsBean.UserBean> data) {
        super(R.layout.item_user_paim, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MapDetailsBean.UserBean item) {
        GlideImageUtils.setGlideImage(mContext, item.getAvatar(), helper.getView(R.id.iv_head_img));
        helper.setText(R.id.tv_user_name,item.getNickName())
                .setText(R.id.tv_time, item.getDistance()+"km");
//                .setText(R.id.tv_time, item.getDistance()+"km · "+StringUtil.getTimeToYMD(item.getTimestamp()*1000,"mm:ss"));
    }

}
