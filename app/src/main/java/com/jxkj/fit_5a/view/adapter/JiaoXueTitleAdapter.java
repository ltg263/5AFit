package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.ClassificationAllData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class JiaoXueTitleAdapter extends BaseQuickAdapter<ClassificationAllData, BaseViewHolder> {

    int pos = 0;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public JiaoXueTitleAdapter(@Nullable List<ClassificationAllData> data) {
        super(R.layout.item_jiao_xue_title, data);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClassificationAllData item) {
        helper.setVisible(R.id.view,false).setText(R.id.tv_title,item.getName())
                .setBackgroundRes(R.id.tv_title,R.color.transparent);
        if(pos == helper.getLayoutPosition()){
            helper.setVisible(R.id.view,true)
                    .setBackgroundRes(R.id.tv_title,R.color.white);
        }
    }

}
