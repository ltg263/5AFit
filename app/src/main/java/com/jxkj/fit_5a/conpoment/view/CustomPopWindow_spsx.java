package com.jxkj.fit_5a.conpoment.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.conpoment.utils.CustomPopWindow;
import com.jxkj.fit_5a.entity.DeviceTypeCoachData;
import com.jxkj.fit_5a.view.adapter.PopupWindowAdapter;
import com.jxkj.fit_5a.view.adapter.PopupWindowAdapter_jl;
import com.jxkj.fit_5a.view.adapter.PopupWindowAdapter_sx;

import java.util.ArrayList;
import java.util.List;

public class CustomPopWindow_spsx {

    public static CustomPopWindow initPopupWindow(Activity mActivity, View view1, List<String> lists, PopWindowInterface mPopWindowInterface) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.popup_window_ty, null, false);
        RecyclerView rv_popup_list = view.findViewById(R.id.rv_popup_list);
        PopupWindowAdapter mPopupWindowAdapter = new PopupWindowAdapter(lists);
        rv_popup_list.setAdapter(mPopupWindowAdapter);
        //创建PopupWindow
        CustomPopWindow distancePopWindow = new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(view)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mPopWindowInterface.getPosition(-1);
                    }
                })
                .setBgDarkAlpha(0.5f)
                .enableBackgroundDark(true)
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(view1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(view1);//显示PopupWindow
        mPopupWindowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPopWindowInterface.getPosition(position);
                distancePopWindow.dissmiss();
            }
        });
        return distancePopWindow;
    }

    public interface PopWindowInterface {
        public void getPosition( int position);
    }

    public static CustomPopWindow initPopupWindowAll(Activity mActivity, View view1,List<String> seleCon , List<DeviceTypeData.ListBean> sb, List<String> nd,
                                                     List<String> sj, List<DeviceTypeCoachData> jl, PopWindowInterfaceAll mPopWindowInterface) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.popup_window_spsx_all, null, false);
        RecyclerView rv_1 = view.findViewById(R.id.rv_1);
        RecyclerView rv_2 = view.findViewById(R.id.rv_2);
        RecyclerView rv_3 = view.findViewById(R.id.rv_3);
        RecyclerView rv_4 = view.findViewById(R.id.rv_4);
        TextView tv_jlall = view.findViewById(R.id.tv_jlall);
        tv_jlall.setTextColor(mActivity.getResources().getColor(R.color.color_4555a3));
        tv_jlall.setBackgroundResource(R.drawable.bj_circle_line_f2_5);
        List<String> sbStr = new ArrayList<>();
        PopupWindowAdapter_sx mPwA1 = new PopupWindowAdapter_sx(sbStr);
        sbStr.add("全部");
        for(int i = 0;i<sb.size();i++){
            sbStr.add(sb.get(i).getName());
            if(seleCon.size() == 4 && seleCon.get(0)!=null
                    && sb.get(i).getId() == Integer.parseInt(seleCon.get(0))){
                mPwA1.setSelePos(i+1);
            }
        }
        rv_1.setAdapter(mPwA1);
        PopupWindowAdapter_sx mPwA2 = new PopupWindowAdapter_sx(nd);
        if(seleCon.size() == 4) {
            mPwA2.setSelePos(Integer.parseInt(seleCon.get(1)));
        }
        rv_2.setAdapter(mPwA2);
        PopupWindowAdapter_sx mPwA3 = new PopupWindowAdapter_sx(sj);
        if(seleCon.size() == 4) {
            mPwA3.setSelePos(Integer.parseInt(seleCon.get(2)));
        }
        rv_3.setAdapter(mPwA3);
        PopupWindowAdapter_jl mPwA4 = new PopupWindowAdapter_jl(jl);
        mPwA4.setSelePos(-1);
        if(seleCon.size() == 4) {
            if(seleCon.get(3)!=null && !seleCon.get(3).equals("null")){
                for(int i = 0;i<jl.size();i++){
                    if(jl.get(i).getCoachId().equals(seleCon.get(3))){
                        mPwA4.setSelePos(i);
                        tv_jlall.setTextColor(mActivity.getResources().getColor(R.color.color_333333));
                        tv_jlall.setBackgroundResource(R.drawable.bj_circle_f2_5);
                    }
                }
            }else{
                mPwA4.setSelePos(-1);
            }
        }
        rv_4.setAdapter(mPwA4);
        if(seleCon.size() == 0){
            seleCon.add(null);
            seleCon.add("0");
            seleCon.add("0");
            seleCon.add(null);
        }
        //创建PopupWindow
        CustomPopWindow distancePopWindow = new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(view)
                .setFocusable(false)//是否获取焦点，默认为ture
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        mPopWindowInterface.getPosition(-1);
                    }
                })
                .setBgDarkAlpha(0.5f)
                .enableBackgroundDark(true)
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .size(view1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()//创建PopupWindow
                .showAsDropDown(view1);//显示PopupWindow
        mPwA1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPwA1.setSelePos(position);
                if(position == 0){
                    seleCon.set(0,null);
                }else{
                    seleCon.set(0,sb.get(position-1).getId()+"");
                }

            }
        });
        mPwA2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPwA2.setSelePos(position);
                seleCon.set(1,position+"");
            }
        });
        mPwA3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPwA3.setSelePos(position);
                seleCon.set(2,position+"");
            }
        });
        tv_jlall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPwA4.setSelePos(-1);
                seleCon.set(3,null);
                tv_jlall.setTextColor(mActivity.getResources().getColor(R.color.color_4555a3));
                tv_jlall.setBackgroundResource(R.drawable.bj_circle_line_f2_5);
            }
        });
        mPwA4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPwA4.setSelePos(position);
                seleCon.set(3,jl.get(position).getCoachId());
                tv_jlall.setTextColor(mActivity.getResources().getColor(R.color.color_333333));
                tv_jlall.setBackgroundResource(R.drawable.bj_circle_f2_5);
            }
        });

        view.findViewById(R.id.tv_re).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPwA1.setSelePos(0);
                mPwA2.setSelePos(0);
                mPwA3.setSelePos(0);
                tv_jlall.setTextColor(mActivity.getResources().getColor(R.color.color_4555a3));
                tv_jlall.setBackgroundResource(R.drawable.bj_circle_line_f2_5);
                mPwA4.setSelePos(-1);
                seleCon.set(0,null);
                seleCon.add(1,"0");
                seleCon.add(2,"0");
                seleCon.add(3,null);
            }
        });
        view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindowInterface.getPosition(seleCon);
                distancePopWindow.dissmiss();

            }
        });
        return distancePopWindow;
    }

    public interface PopWindowInterfaceAll {
        public void getPosition(List<String> lists);
    }
}
