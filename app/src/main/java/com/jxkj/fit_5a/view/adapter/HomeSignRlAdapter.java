package com.jxkj.fit_5a.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.SignLogData;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeSignRlAdapter extends BaseQuickAdapter<SignLogData.ListBean, BaseViewHolder> {
    public HomeSignRlAdapter(@Nullable List<SignLogData.ListBean> data) {
        super(R.layout.item_home_sign_rl, data);
    }
    boolean setCurrTime = true;

    public void setSetCurrTime(boolean setCurrTime) {
        this.setCurrTime = setCurrTime;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SignLogData.ListBean item) {

        if(item==null){
            helper.setGone(R.id.ll,false);
            return;
        }
        helper.setGone(R.id.ll,true).setText(R.id.tv_rq,item.getSj());

        helper.setVisible(R.id.view,false).setVisible(R.id.img,false)
                .setVisible(R.id.tv_rq,true).setVisible(R.id.view_dot,false)
                .setTextColor(R.id.tv_rq,mContext.getResources().getColor(R.color.color_333333));

        if(StringUtil.isNotBlank(item.getSj()) && StringUtil.getDay(new Date())==Integer.valueOf(item.getSj()) && setCurrTime){//当前日期选中
            helper.setVisible(R.id.view,true).setTextColor(R.id.tv_rq,mContext.getResources().getColor(R.color.color_ffffff));
        }

        if(item.isSig()){//已签到日期先点
            helper.setVisible(R.id.view_dot,true);
        }
        if(false){//有礼物的日期
            helper.setVisible(R.id.img,true).setVisible(R.id.tv_rq,false);
        }

    }

}
