package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.SportLogBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class TwoJlxqAdapter extends BaseQuickAdapter<SportLogBean.ListBean, BaseViewHolder> {
    public TwoJlxqAdapter(@Nullable List<SportLogBean.ListBean> data) {
        super(R.layout.item_two_jlxq, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SportLogBean.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getDeviceImg(),helper.getView(R.id.iv_imgUrl));
        helper.setText(R.id.tv_name, item.getDeviceName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getStartTimestamp(), "yyyy-MM-dd HH:mm"))
                .setText(R.id.tv_distance, StringUtil.getValue(item.getDistance()))
                .setText(R.id.tv_calories, item.getCalories()).setText(R.id.tv_dw,"里程/km")
                .setText(R.id.tv_duration, StringUtil.getTimeGeShi(Long.parseLong(item.getDuration())));
        if(item.getDeviceName().equals("哑铃")){
            helper.setText(R.id.tv_dw,"个");
        }
    }

}
