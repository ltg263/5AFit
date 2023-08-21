package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.UserReportBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ReportContactListAdapter extends BaseQuickAdapter<UserReportBean, BaseViewHolder> {
    public ReportContactListAdapter(@Nullable List<UserReportBean> data) {
        super(R.layout.item_report_manage, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserReportBean item) {
        helper.setText(R.id.tv_name,item.getName());
    }

}
