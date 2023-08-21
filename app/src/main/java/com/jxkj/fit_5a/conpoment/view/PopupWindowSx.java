package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.view.adapter.PopupListAdapter;

import java.util.List;


public class PopupWindowSx extends PopupWindow {

    Context mcontext;

    @SuppressLint("ClickableViewAccessibility")
    public PopupWindowSx(Activity context, GiveDialogInterface dialogInterface) {
        super(context);
        this.mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_exercise_sx, null);
        EditText et_jf_d = view.findViewById(R.id.et_jf_d);
        EditText et_jf_g = view.findViewById(R.id.et_jf_g);
        EditText et_jg_d = view.findViewById(R.id.et_jg_d);
        EditText et_jg_g = view.findViewById(R.id.et_jg_g);
        view.findViewById(R.id.tv_chongzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_jf_d.setText("");
                et_jf_g.setText("");
                et_jg_d.setText("");
                et_jg_g.setText("");
            }
        });
        view.findViewById(R.id.tv_queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                String a = et_jf_d.getText().toString();
                String b = et_jf_g.getText().toString();
                String c = et_jg_d.getText().toString();
                String d = et_jg_g.getText().toString();
                dialogInterface.btnConfirm(a,b,c,d);
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x7f000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

//        mPopupListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                dialogInterface.btnConfirm(position);
//                dismiss();
//            }
//        });
    }



    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(String jfd,String jfg,String jgd,String jgg);
    }


}
