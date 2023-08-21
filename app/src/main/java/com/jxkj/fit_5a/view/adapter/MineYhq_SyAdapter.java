package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.ShowOrderInfo;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineYhq_SyAdapter extends BaseQuickAdapter<ShowOrderInfo.RedListBean, BaseViewHolder> {
    public MineYhq_SyAdapter(@Nullable List<ShowOrderInfo.RedListBean> data) {
        super(R.layout.item_mine_yhq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShowOrderInfo.RedListBean item) {
//        GlideImgLoader.loadImageViewRadius(mContext,item.getImgUrl(),10,helper.getView(R.id.tv_icon));
//        helper.setText(R.id.tv_1,item.getCouponName()).addOnClickListener(R.id.btn)
//                .setText(R.id.tv_2,item.getCouponName())
//                .setText(R.id.tv_4,"有效期至："+item.getExpireDate());

        helper.setText(R.id.tv_yhq_je,StringUtil.getValue(item.getReliefAmount()))
                .setText(R.id.tv_yhq_mj,"满"+StringUtil.getValue(item.getLimitAmount())+"元可用")
                .setText(R.id.tv_yhq_go,"选择").addOnClickListener(R.id.tv_yhq_go)
                .setText(R.id.tv_yhq_time,"有效期至："
                        + StringUtil.getTimeToYMD(StringUtil.getMsToTime(item.getExpireDate(),"yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd"));
    }

}
