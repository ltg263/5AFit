package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.PrizeListData;
import com.jxkj.fit_5a.entity.NotObtainedBean;
import com.jxkj.fit_5a.view.adapter.MineYhq_ScAdapter;

import java.util.List;


public class PopupWindowYhq extends PopupWindow {

    private final MineYhq_ScAdapter mMineYhq_ScAdapter;
    Context mContext;

    @SuppressLint("ClickableViewAccessibility")
    public PopupWindowYhq(Activity context, List<PrizeListData.ListBean> dataSbType, GiveDialogInterface dialogInterface) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.popup_window_yhq, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(false);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.transparent));
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        RecyclerView mRvList = view.findViewById(R.id.rv_list);
        mMineYhq_ScAdapter = new MineYhq_ScAdapter(dataSbType);
        mRvList.setLayoutManager(new LinearLayoutManager(context));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMineYhq_ScAdapter);

        mMineYhq_ScAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                dialogInterface.btnConfirm(dataSbType.get(position).getId());
            }
        });
        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.btnConfirm(-1);
            }
        });

        view.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setNotifyDataSetChanged(List<PrizeListData.ListBean> dataSbType){
        mMineYhq_ScAdapter.setNewData(dataSbType);
    }



    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(int topicId);
    }


}
