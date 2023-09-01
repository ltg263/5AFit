package com.jxkj.fit_5a.view.adapter;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;

import java.util.List;

public class PopupWindowAdapter_sx extends BaseQuickAdapter<String, BaseViewHolder> {

    int selePos = 0;

    @SuppressLint("NotifyDataSetChanged")
    public void setSelePos(int selePos) {
        this.selePos = selePos;
        notifyDataSetChanged();
    }

    public PopupWindowAdapter_sx(List<String> data) {
        super(R.layout.item_spsx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,String item) {
        helper.setText(R.id.text,item).setTextColor(R.id.text,mContext.getColor(R.color.color_333333))
                .setBackgroundRes(R.id.text,R.drawable.bj_circle_f2_5);

        if(helper.getLayoutPosition()==selePos){
            helper.setTextColor(R.id.text,mContext.getColor(R.color.color_4555a3))
                    .setBackgroundRes(R.id.text,R.drawable.bj_circle_line_f2_5);
        }
    }

}
