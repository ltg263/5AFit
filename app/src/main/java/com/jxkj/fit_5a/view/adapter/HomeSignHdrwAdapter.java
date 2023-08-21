package com.jxkj.fit_5a.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeSignHdrwAdapter extends BaseQuickAdapter<TaskListBase.ListBean, BaseViewHolder> {
    public HomeSignHdrwAdapter(@Nullable List<TaskListBase.ListBean> data) {
        super(R.layout.item_home_sign_hdrw, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TaskListBase.ListBean item) {
        helper.setText(R.id.tv_num,item.getExplain())
                .setText(R.id.tv_title,item.getName());
        if(item.getStatus()==1){
            helper.setText(R.id.tv_go_sign,"未完成");
            helper.addOnClickListener(R.id.tv_go_sign);
        }else if(item.getStatus()==2){
            helper.setText(R.id.tv_go_sign,"已完成");
        }else if(item.getStatus()==3){
            helper.setText(R.id.tv_go_sign,"已过期");
        }

        if(item.getSpeeds()!=null && item.getSpeeds().size()>0){
            TaskListBase.ListBean.SpeedsBean speeds = item.getSpeeds().get(item.getSpeeds().size() - 1);
            if(StringUtil.isNotBlank(speeds.getTarget()) && StringUtil.isNotBlank(speeds.getSpeed())){
                Log.w("getTarget","getTarget:"+speeds.getTarget());
                Log.w("getTarget","getSpeed:"+speeds.getSpeed());
//                helper.setProgress(R.id.pb_progressbar,(int)Double.parseDouble(speeds.getSpeed()),(int)Double.parseDouble(speeds.getTarget()));
            }
        }
    }

}
