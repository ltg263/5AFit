package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.BpmDataBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class TaskFinishListHpAdapter extends BaseQuickAdapter<BpmDataBean, BaseViewHolder> {
    int Ztime;
    public TaskFinishListHpAdapter(@Nullable List<BpmDataBean> data, int Ztime) {
        super(R.layout.item_task_finish_list_hp, data);
        this.Ztime = Ztime;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BpmDataBean item) {
        double xlfx = item.getTime()/Ztime;
        DecimalFormat df = new DecimalFormat("######0.00");
        helper.setText(R.id.tv1,item.getName()).setText(R.id.tv4, StringUtil.getTimeGeShi(item.getTime()))
                .setText(R.id.tv3,((int)(Double.valueOf(df.format(xlfx))*100))+"%")
                .setText(R.id.tv2,item.getStartV()+"bpm~"+item.getEndV()+"bpm");
    }

}
