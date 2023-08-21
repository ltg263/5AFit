package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseActivity_Zx;

public class PopupWindowTopicUtils_Map extends PopupWindow {


    Context mContext;
    TextView tv_Distance, tv_speed,tv_speed_1, tv_time, tv_Calories, tv_Watt, tv_Watt_1,tv_Pulse,tv_load,tv_Incline;
    ImageView mIv,iv_jia,iv_jian;
    LinearLayout ll_load,ll;
    public static boolean isTop = true;
    @SuppressLint("ClickableViewAccessibility")
    public PopupWindowTopicUtils_Map(Activity context,boolean isQuicks, GiveDialogInterface dialogInterface) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_exercise_layout_map, null);
        mIv = view.findViewById(R.id.iv);
        ll = view.findViewById(R.id.ll);
        if(context instanceof MapExerciseActivity_Zx && !isQuicks){
            mIv.setVisibility(View.INVISIBLE);
            ll.setBackgroundResource(R.drawable.bj_circle_fff_20_ss_t);
        }
        iv_jian = view.findViewById(R.id.iv_jian);
        iv_jia = view.findViewById(R.id.iv_jia);
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
        RelativeLayout rl = view.findViewById(R.id.rl);
        LinearLayout mLlBottom = view.findViewById(R.id.ll_bottom);
        if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[1]){
            mLlBottom.setVisibility(View.GONE);
        }
        mLlBottom.setBackgroundColor(mContext.getResources().getColor(R.color.white_t));
        LinearLayout mll_hcj = view.findViewById(R.id.ll_hcj);
        ll_load = view.findViewById(R.id.ll_load);
        tv_Distance = view.findViewById(R.id.tv_Distance);
        tv_speed = view.findViewById(R.id.tv_speed);
        tv_speed_1 = view.findViewById(R.id.tv_speed_1);
        tv_time = view.findViewById(R.id.tv_time);
        tv_Calories = view.findViewById(R.id.tv_Calories);
        tv_load = view.findViewById(R.id.tv_load);
        tv_Watt = view.findViewById(R.id.tv_Watt);
        tv_Watt_1 = view.findViewById(R.id.tv_Watt_1);
        tv_Pulse = view.findViewById(R.id.tv_Pulse);
        tv_Incline = view.findViewById(R.id.tv_Incline);
        if(ConstValues_Ly.METER_ID==ConstValues_Ly.METER_ID_S[3]){
            mll_hcj.setVisibility(View.VISIBLE);
        }
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.findViewById(R.id.iv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl.setAnimation(moveToViewBottom());
            }
        });
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIv.isSelected()) {
                    dialogInterface.btnConfirm(1);
                } else {
                    dialogInterface.btnConfirm(0);
                }
            }
        });
        iv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.btnConfirm(3);

            }
        });
        iv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.btnConfirm(2);
            }
        });
        rl.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    // 按下
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    // 移动
                    case MotionEvent.ACTION_MOVE:
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();
                        if (mCurrentPosX - mPosX > 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向右");
                        else if (mCurrentPosX - mPosX < 0 && Math.abs(mCurrentPosY - mPosY) < 10)
                            Log.e("", "向左");
                        else if (mCurrentPosY - mPosY > 0 && Math.abs(mCurrentPosX - mPosX) < 10){
                            Log.e("", "向下");
                            if(isTop){
                                rl.setAnimation(moveToViewBottom());
                            }
                        }else if (mCurrentPosY - mPosY < 0 && Math.abs(mCurrentPosX - mPosX) < 10){
                            Log.e("", "向上");
                            if(!isTop){
                                rl.setAnimation(moveToViewBottomS());
                            }
                        }
                        break;
                    // 拿起
                    case MotionEvent.ACTION_UP:

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.3f);
        mHiddenAction.setDuration(500);
        mHiddenAction.setFillAfter(true);
        isTop = false;
        return mHiddenAction;
    }
    public static TranslateAnimation moveToViewBottomS() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.3f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        mHiddenAction.setFillAfter(true);
        isTop = true;
        return mHiddenAction;
    }

    public void setIvSelect(boolean isSelect){
        mIv.setSelected(isSelect);
    }

    public void setTextLoad(String strLoad){
        ll_load.setVisibility(View.VISIBLE);
        tv_load.setText(strLoad);
    }
    public void setTextSpeed(String speed_str){
        tv_speed_1.setText(speed_str);
    }
    public void setTextTvWatt(String Watt,String text){
//        tv_Watt.setText(Watt);
        tv_Watt_1.setText(text);
    }

    private float mPosX;
    private float mPosY;
    private float mCurrentPosX;
    private float mCurrentPosY;
    public void setTextViewStr(String tv_Distance, String tv_speed, String tv_time, String tv_Calories, String tv_Watt, String tv_Pulse,String tv_Incline) {
        this.tv_Distance.setText(tv_Distance+ "km");//km
        if(tv_speed_1!=null && tv_speed_1.getText().equals("浆频")){
            this.tv_speed.setText(tv_speed+ "次/分钟");//km/h
        }else{
            this.tv_speed.setText(tv_speed+ "km/h");//km/h
        }
        this.tv_Calories.setText(tv_Calories+ "kcal");//kcal
        this.tv_Watt.setText(tv_Watt+ "w");//w
        this.tv_Pulse.setText(tv_Pulse+ "bpm");//bpm
        this.tv_Incline.setText(tv_Incline);

        if(Double.parseDouble(tv_Distance)==0){
            this.tv_Distance.setText("N/A");
        }
        if(Double.parseDouble(tv_speed)==0){
            this.tv_speed.setText("N/A");
        }
        if(Double.parseDouble(tv_Calories)==0){
            this.tv_Calories.setText("N/A");
        }
        if(Double.parseDouble(tv_Watt)==0){
            this.tv_Watt.setText("N/A");
        }
        if(Double.parseDouble(tv_Pulse)==0){
            this.tv_Pulse.setText("N/A");
        }
        if(Double.parseDouble(tv_Incline)==0){
            this.tv_Incline.setText("N/A");
        }

        this.tv_time.setText(tv_time);
    }


    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(int type);//0:Start  1:Stop  2:加阻力  3：减阻力
    }


}
