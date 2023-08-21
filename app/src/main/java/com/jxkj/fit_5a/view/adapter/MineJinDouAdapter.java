package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.WalletListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineJinDouAdapter extends BaseQuickAdapter<WalletListBean.ListBean, BaseViewHolder> {
    public MineJinDouAdapter(@Nullable List<WalletListBean.ListBean> data) {
        super(R.layout.item_mine_jindou, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, WalletListBean.ListBean item) {
        helper.setText(R.id.tv_user,item.getRemark()).setText(R.id.tv_paymentTime,item.getPaymentTime())
                .setText(R.id.tv_amount,item.getAmount().replace(".00","")).setText(R.id.tv_amount_dou,item.getAmount().replace(".00",""))
                .setVisible(R.id.tv_amount_dou,false).setVisible(R.id.tv_amount,false);
        if(item.getType().equals("1")){
            helper.setVisible(R.id.tv_amount_dou,true);
        }else{
            helper.setVisible(R.id.tv_amount,true);
        }

    }

}
