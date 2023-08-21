package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceCourseData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class YaLingB_1Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
    int pos = 0;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public YaLingB_1Adapter(@Nullable List<String> data) {
        super(R.layout.item_yaling_b_1, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setGone(R.id.rl1,false).setGone(R.id.rl,false)
                .setText(R.id.tv_title,item).setText(R.id.tv_title_1,item);
        if(helper.getLayoutPosition()==pos){
            helper.setGone(R.id.rl,true);
        }else{
            helper.setGone(R.id.rl1,true);
        }
    }

}
