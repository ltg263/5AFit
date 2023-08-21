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
public class TaskFinishPjAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    int pos = 0;

    public TaskFinishPjAdapter(@Nullable List<String> data) {
        super(R.layout.item_task_finish_pj, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setImageResource(R.id.iv_select,R.drawable.wxz_1);
        helper.setText(R.id.tv,item);
        if(pos == helper.getLayoutPosition()){
            helper.setImageResource(R.id.iv_select,R.drawable.wxz_);
        }
        switch (helper.getLayoutPosition()){
            case 0:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj1);
                break;
            case 1:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj2);
                break;
            case 2:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj3);
                break;
            case 3:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj4);
                break;
            case 4:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj5);
                break;
            case 5:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj6);
                break;
            case 6:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj7);
                break;
            case 7:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj8);
                break;
            case 8:
                helper.setImageResource(R.id.iv,R.mipmap.ic_fin_pj9);
                break;
        }
    }

    public void setSelectPos(int pos){
        this.pos = pos;
    }
}
