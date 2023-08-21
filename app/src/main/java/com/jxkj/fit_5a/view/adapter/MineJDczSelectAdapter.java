package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineJDczSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    int currPos = 0;
    public MineJDczSelectAdapter(@Nullable List<String> data) {
        super(R.layout.item_view_jdcs_select, data);
    }

    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv1,item).setText(R.id.tv2,item).setText(R.id.tv12,item).setText(R.id.tv22,item);
        if(currPos==helper.getLayoutPosition()){
            helper.setVisible(R.id.rv_select_yes,true);
            helper.setVisible(R.id.rv_select_no,false);
        }else{
            helper.setVisible(R.id.rv_select_yes,false);
            helper.setVisible(R.id.rv_select_no,true);
        }
    }

}
