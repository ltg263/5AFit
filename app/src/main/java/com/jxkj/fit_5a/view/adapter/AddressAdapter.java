package com.jxkj.fit_5a.view.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.entity.AddressData;
import com.jxkj.fit_5a.view.activity.mine.order.AddressEditActivity;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class AddressAdapter extends BaseQuickAdapter<AddressData, BaseViewHolder> {
    public AddressAdapter(@Nullable List<AddressData> data) {
        super(R.layout.item_address, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AddressData item) {
        ImageView defaultIv = helper.getView(R.id.iv_default);
        LinearLayout defaultLl = helper.getView(R.id.ll_default);
        TextView nameTv = helper.getView(R.id.tv_name);
        TextView phoneTv = helper.getView(R.id.tv_phone);
        TextView addressTv = helper.getView(R.id.tv_address);
        TextView editTv = helper.getView(R.id.tv_edit);
        TextView delTv = helper.getView(R.id.tv_del);
        nameTv.setText(item.getAcceptName());
        phoneTv.setText(item.getMobile());
        addressTv.setText(item.getRegions()+item.getLocation());
        defaultIv.setImageResource(R.drawable.wxz);
        if (item.getIsDefault().equals("1")) {
            defaultIv.setImageResource(R.drawable.wxz_);
        }
        editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("address", item);
                AddressEditActivity.startActivity(view.getContext(), bundle);
            }
        });
        delTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDeleteClickListener != null) {
                    mDeleteClickListener.onDeleteClick(helper.getLayoutPosition());
                }

            }
        });
        defaultLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDeleteClickListener != null) {
                    mDeleteClickListener.onDefaultClick(helper.getLayoutPosition());
                }
            }
        });
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    private OnDeleteClickLister mDeleteClickListener;

    public interface OnDeleteClickLister {
        void onDeleteClick(int position);

        void onDefaultClick(int position);
    }
}
