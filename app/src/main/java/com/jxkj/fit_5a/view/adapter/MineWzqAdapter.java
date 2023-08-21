package com.jxkj.fit_5a.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.entity.MedalListData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineWzqAdapter extends BaseQuickAdapter<MedalListData, BaseViewHolder> {

    public MineWzqAdapter(@Nullable List<MedalListData> data) {
        super(R.layout.item_mine_wzq, data);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void convert(@NonNull BaseViewHolder helper, MedalListData item) {
        helper.setText(R.id.tv_title, item.getName())
                .setGone(R.id.ll_b, false)
                .setVisible(R.id.ll_1, false)
                .setVisible(R.id.ll_11, false)
                .setVisible(R.id.ll_2, false)
                .setVisible(R.id.ll_22, false)
                .setVisible(R.id.ll_3, false)
                .setVisible(R.id.ll_4, false)
                .setVisible(R.id.ll_44, false)
                .setVisible(R.id.ll_5, false)
                .setVisible(R.id.ll_55, false)
                .setVisible(R.id.ll_6, false);
        List<MedalListData.MedalsBean> medals = item.getMedals();
        for (int i = 0; i < medals.size(); i++) {
            MedalListData.MedalsBean bean = medals.get(i);
            switch (i) {
                case 0:
                    helper.setVisible(R.id.ll_1, true).setText(R.id.tv_1, bean.getName());
                    setOnClickListener(helper.getView(R.id.ll_1), bean);
                    if (bean.isHasHave()) {
                        Glide.with(mContext).load(bean.getImgUrl()).into((ImageView) helper.getView(R.id.iv_1));
                    } else {
                        Glide.with(mContext).load(bean.getImgUrlNo()).into((ImageView) helper.getView(R.id.iv_1));
                    }
                    break;
                case 1:
                    helper.setVisible(R.id.ll_2, true).setVisible(R.id.ll_11, true).setText(R.id.tv_2, bean.getName());
                    setOnClickListener(helper.getView(R.id.ll_2), bean);
                    if (bean.isHasHave()) {
                        Glide.with(mContext).load(bean.getImgUrl()).into((ImageView) helper.getView(R.id.iv_2));
                    } else {
                        Glide.with(mContext).load(bean.getImgUrlNo()).into((ImageView) helper.getView(R.id.iv_2));
                    }
                    break;
                case 2:
                    helper.setVisible(R.id.ll_3, true).setVisible(R.id.ll_22, true).setText(R.id.tv_3, bean.getName());
                    setOnClickListener(helper.getView(R.id.ll_3), bean);
                    if (bean.isHasHave()) {
                        Glide.with(mContext).load(bean.getImgUrl()).into((ImageView) helper.getView(R.id.iv_3));
                    } else {
                        Glide.with(mContext).load(bean.getImgUrlNo()).into((ImageView) helper.getView(R.id.iv_3));
                    }
                    break;
                case 3:
                    helper.setVisible(R.id.ll_4, true).setVisible(R.id.ll_b, true).setText(R.id.tv_4, bean.getName());
                    setOnClickListener(helper.getView(R.id.ll_4), bean);
                    if (bean.isHasHave()) {
                        Glide.with(mContext).load(bean.getImgUrl()).into((ImageView) helper.getView(R.id.iv_4));
                    } else {
                        Glide.with(mContext).load(bean.getImgUrlNo()).into((ImageView) helper.getView(R.id.iv_4));
                    }
                    break;
                case 4:
                    helper.setVisible(R.id.ll_5, true).setVisible(R.id.ll_44, true).setText(R.id.tv_5, bean.getName());
                    setOnClickListener(helper.getView(R.id.ll_5), bean);
                    if (bean.isHasHave()) {
                        Glide.with(mContext).load(bean.getImgUrl()).into((ImageView) helper.getView(R.id.iv_5));
                    } else {
                        Glide.with(mContext).load(bean.getImgUrlNo()).into((ImageView) helper.getView(R.id.iv_5));
                    }
                    break;
                case 5:
                    helper.setVisible(R.id.ll_6, true).setVisible(R.id.ll_55, true).setText(R.id.tv_6, bean.getName());
                    setOnClickListener(helper.getView(R.id.ll_6), bean);
                    if (bean.isHasHave()) {
                        Glide.with(mContext).load(bean.getImgUrl()).into((ImageView) helper.getView(R.id.iv_6));
                    } else {
                        Glide.with(mContext).load(bean.getImgUrlNo()).into((ImageView) helper.getView(R.id.iv_6));
                    }
                    break;
            }
        }
    }

    private void setOnClickListener(View view, MedalListData.MedalsBean bean) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bean.isHasHave()){
                    return;
                }
                DialogUtils.showDialogWzq((Activity) mContext, bean, new DialogUtils.DialogLyInterface() {
                    @Override
                    public void btnConfirm() {

                    }
                });
            }
        });
    }
}
