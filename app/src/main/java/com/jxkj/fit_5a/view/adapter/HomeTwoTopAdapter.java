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
public class HomeTwoTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeTwoTopAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_two_top, data);
    }
    String num_map = "0";

    public void setNum_map(String num_map) {
        this.num_map = num_map;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.tv_go_1).addOnClickListener(R.id.tv_go_2)
                .setText(R.id.tv_num,num_map+" 款运动地图");

        if(item.equals("在线运动")){
            helper.setText(R.id.tv_title,item).setText(R.id.tv_title_f,"Online \nSports")
                .setText(R.id.tv_go_1,"即时模式").setText(R.id.tv_go_2,"竞赛模式");
            helper.setImageResource(R.id.iv_icon,R.mipmap.ic_two_1);
        }else{
            helper.setText(R.id.tv_title,item).setText(R.id.tv_title_f,"Classic \nSports")
                .setText(R.id.tv_go_1,"快速开始").setText(R.id.tv_go_2,"跳过任务");
            helper.setImageResource(R.id.iv_icon,R.mipmap.ic_two_2);
        }

    }

}
