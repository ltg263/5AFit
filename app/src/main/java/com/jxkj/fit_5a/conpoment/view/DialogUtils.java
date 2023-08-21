package com.jxkj.fit_5a.conpoment.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.DiscountUsableNotBean;
import com.jxkj.fit_5a.entity.GameCompleteBean;
import com.jxkj.fit_5a.entity.MedalListData;
import com.jxkj.fit_5a.entity.RewardLogBean;
import com.jxkj.fit_5a.view.adapter.MineGameRankingAdapter;
import com.jxkj.fit_5a.view.adapter.MineRwzxAdapter;
import com.jxkj.fit_5a.view.adapter.MineYhq_DlqAdapter;
import com.jxkj.fit_5a.view.adapter.ReportContactListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DialogUtils {

    public static AlertDialog cancelDialog(Context context, String title, String content, DialogInterface.OnClickListener listener) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定",listener)
                .setNegativeButton("取消",null)
                .create();

    }
    public static AlertDialog donelDialog(Context context, String title, String content) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定",null)
                .create();

    }
    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
    public static void showEditTextKefuKuang(final Context context,String del, String img,final EditTextDialogInterface editTextDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_kefukuang, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tv_log = view.findViewById(R.id.tv_log);
        tv_log.setText(del);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        Glide.with(context).load(img).into(iv_icon);
        iv_icon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                editTextDialogInterface.btnConfirm("2");
                dialog.dismiss();
                return false;
            }
        });
        // 确定
        tv_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                editTextDialogInterface.btnConfirm("1");
                dialog.dismiss();
            }

        });
    }
    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
    public static void showEditTextDialog(final Context context, int type,String title, String content, final EditTextDialogInterface editTextDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_text, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tvLogPrompt = view.findViewById(R.id.tv_log_prompt);
        final EditText etLogContent = view.findViewById(R.id.et_log_content);
        etLogContent.setMaxLines(10);
        if(StringUtil.isNotBlank(content)){
            etLogContent.setHint(content);
        }
        if(type==1){
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if(type==2){
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        TextView tvLogConfirm = view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = view.findViewById(R.id.tv_log_cancel);

        tvLogPrompt.setText(title);
        // 确定
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String season = etLogContent.getText().toString().trim();
                editTextDialogInterface.btnConfirm(season);
                dialog.dismiss();
            }

        });
        // 取消
        tvLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    public interface EditTextDialogInterface{
        void btnConfirm(String string);
    }
    @SuppressLint("ClickableViewAccessibility")
    public static void showDialogStartYd(Activity context, final DialogInterfaceS dialogInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_start_sop, null);

        LinearLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,5)));
        ImageView tv = view.findViewById(R.id.iv_1);
        ImageView tv1 = view.findViewById(R.id.iv_2);
        StepArcView_Btn mSv = view.findViewById(R.id.sv);

        tv.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                dialogInterface.btnType(1);
                dialog.dismiss();
            }
        });
        tv1.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP){
                mSv.setStopAnimator();
            }
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                mSv.setCurrentCount(100, 100, new StepArcView_Btn.CurrentAngleInterface() {
                    @Override
                    public void complete() {
                        dialog.dismiss();
                        dialogInterface.btnType(2);
                    }
                });
            }
            return true;
        });

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 链接蓝牙
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogLyState(Activity context,String title, String state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_lanyan_state, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        tv_name.setText(title);
//        Glide.with(context).load(imgUrl).into(iv_icon);
        if(state.equals("连接成功")){
            tv_state.setText(state);
            tv_ok.setText("确定");
        }else{
            tv_state.setText(state);
            tv_ok.setText("确定");
        }
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    /**
     * 违章墙
     */
    public static void showDialogWzq(Activity context, MedalListData.MedalsBean bean, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.selectorDialog);
//        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_wzq, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
//        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        ImageView iv = view.findViewById(R.id.iv);
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 =  view.findViewById(R.id.tv2);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        Glide.with(context).load(bean.getImgUrl()).into( iv);
        tv1.setText(bean.getExplain());
        tv2.setText(bean.getCreateTime()+"获得");
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        if(!dialog.isShowing()){
            dialog.show();
        }
    }
    /**
     * 违章墙
     */
    public static void showDialogWzq_get(Activity context, RewardLogBean.ListBean bean, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.selectorDialog);
//        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_wzq_hp, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
//        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        ImageView iv = view.findViewById(R.id.iv);
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 =  view.findViewById(R.id.tv2);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        if(StringUtil.isNotBlank(bean.getReward())){
            try {
                JSONObject object = new JSONObject(bean.getReward());
                Glide.with(context).load(object.getString("imgUrl")).into( iv);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tv1.setText(bean.getExplain());
        tv2.setText(bean.getCreateTime()+"获得");
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        if(!dialog.isShowing()){
            dialog.show();
        }
    }
    /**
     * 任务列表
     */
    public static void showDialogRw_get(Activity context, List<TaskListBase.ListBean> listFinishTask, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_wzq_hp_rw, null);

        Display mDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(mDisplay.getWidth(), mDisplay.getHeight());

        RecyclerView rv_list = view.findViewById(R.id.rv_list);

        MineRwzxAdapter mMineRwzxAdapter = new MineRwzxAdapter(listFinishTask);
        mMineRwzxAdapter.setIsfinishTask(true);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        rv_list.setHasFixedSize(true);
        rv_list.setAdapter(mMineRwzxAdapter);

        View iv_close = view.findViewById(R.id.bnt_text);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.setContentView(view,layout);
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    /**
     * 创建圈子成功
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogCgCircle(Activity context,String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_not_circle_cg, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 退出房间
     * @param context
     */
    public static void showDialogOutRoom(Activity context,final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_out_room, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 退出房间
     * @param context
     */
    public static void showDialogOutRoom_zx(Activity context,String title,String f_title,final EditTextDialogInterface mEditTextDialogInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_out_room_zx, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(title);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        tv_state.setText(f_title);
        TextView tv_no =  view.findViewById(R.id.tv_no);
        if(title.equals("退出房间")){
            tv_no.setText("退 出");
        }
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditTextDialogInterface.btnConfirm("0");
                dialog.dismiss();
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditTextDialogInterface.btnConfirm("1");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 在线运动完成
     * @param context
     * @param gameRankings
     * @param ranking
     */
    public static void showDialogyundong_zx(Activity context,String roomType, List<GameCompleteBean.GameRankingsBean> gameRankings, String ranking, final EditTextDialogInterface mEditTextDialogInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_yundong_ok_zx, null);

//        LinearLayout rl_parent = view.findViewById(R.id.rl_parent);
//        Bitmap bitmap=screenShotWithoutStatusBar(context);
//        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        RecyclerView mRvList = view.findViewById(R.id.rv_list);
        TextView tv_ok = view.findViewById(R.id.tv_ok);
        LinearLayoutManager ms = new LinearLayoutManager(context);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        MineGameRankingAdapter mBaseQuickAdapter = new MineGameRankingAdapter(gameRankings);
        mRvList.setAdapter(mBaseQuickAdapter);
        tv_ok.setText("第"+ranking+"名 查看成绩");
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditTextDialogInterface.btnConfirm("0");
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditTextDialogInterface.btnConfirm("1");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 创建圈子权限
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogNoCircle(Activity context,String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_not_circle_qx, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state =  view.findViewById(R.id.tv_state);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    public static void showDialogInputPass(Activity context,final EditTextDialogInterface mEditTextDialogInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_not_input_pss, null);

        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));
        EditText et_pss =  view.findViewById(R.id.et_pss);
        TextView tv_ok =  view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = et_pss.getText().toString();
                mEditTextDialogInterface.btnConfirm(str);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }
    /**
     * 当前的奖杯
     * @param context
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogClass(Activity context,int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_class, null);
        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap=screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        ImageView iv = view.findViewById(R.id.iv_1);
        Button bntOk =  view.findViewById(R.id.tv_ok);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bntOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    public interface DialogInterfaceS {
        /**
         * 确定
         */
        public void btnType(int pos);
    }
    public interface DialogLyInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }
    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.1f;

    public static Bitmap blurBitmap(Activity activity,Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(activity);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    public static Bitmap screenShotWithoutStatusBar(Activity activity) {
        //通过window的源码可以看出：检索顶层窗口的装饰视图，可以作为一个窗口添加到窗口管理器
        View view = activity.getWindow().getDecorView();
        //启用或禁用绘图缓存
        view.setDrawingCacheEnabled(true);
        //创建绘图缓存
        view.buildDrawingCache();
        //拿到绘图缓存
        Bitmap bitmap = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        //状态栏高度
        int statusBarHeight = frame.top;
        int width = getWindowWidth(activity);
        int height = getWindowHeight(activity);

        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap, 0, 0, width, height );
        view.destroyDrawingCache();
        return bp;
    }
    /** 获取屏幕宽度
     * @return */

    public static int getWindowWidth(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
    /** 获取屏幕宽度
     * @return */

    public static int getWindowHeight(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
    public static void showDialogHint(Context context, String title, boolean isOne, final ErrorDialogInterface dialogConfirm) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_hine, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
        TextView bt_ok1 = (TextView) view.findViewById(R.id.bt_confirm_1);
        TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        if(isOne){
            suanle.setVisibility(View.GONE);
            bt_ok.setVisibility(View.GONE);
            bt_ok1.setVisibility(View.VISIBLE);
        }
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogConfirm!=null){
                    dialogConfirm.btnConfirm();
                }
                dialog5.dismiss();
            }
        });
        bt_ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogConfirm!=null){
                    dialogConfirm.btnConfirm();
                }
                dialog5.dismiss();
            }
        });
        dialog5.setCancelable(false);
        dialog5.setContentView(view);
        dialog5.show();
    }

    public static void showDialogJiaRuHeiMingDan(Context context, final DialogInterfaceYhq dialogConfirm) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_server_shib, null);
        TextView tv_jubao =  view.findViewById(R.id.tv_jubao);
        TextView tv_heimingdan =  view.findViewById(R.id.tv_heimingdan);
        TextView tv_quxiao =  view.findViewById(R.id.tv_quxiao);

        tv_jubao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogConfirm.btnConfirm(1);
                dialog5.dismiss();
            }
        });
        tv_heimingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogConfirm!=null){
                    dialogConfirm.btnConfirm(0);
                }
                dialog5.dismiss();
            }
        });
        tv_quxiao.setOnClickListener(v -> dialog5.dismiss());
        dialog5.setCancelable(false);
        dialog5.setContentView(view);
        dialog5.show();
    }

    public static void showDialogJuBao(Context context, final UnificationDialogInterface dialogConfirm) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_server_jubao, null);
        TextView tv_quxiao =  view.findViewById(R.id.tv_quxiao);
        RecyclerView mRvList =  view.findViewById(R.id.rv_list);
        ReportContactListAdapter mReportContactListAdapter;
        if(MainActivity.mUserReportList != null){
            mReportContactListAdapter = new ReportContactListAdapter(MainActivity.mUserReportList);
            mRvList.setAdapter(mReportContactListAdapter);
            mReportContactListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    dialogConfirm.bntClickListener(MainActivity.mUserReportList.get(position).getId()+"");
                    dialog5.dismiss();
                }
            });
        }

        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });
        dialog5.setCancelable(false);
        dialog5.setContentView(view);
        dialog5.show();
    }

    public static void showDialogHintYunDong(Context context, final DialogInterfaceYhq dialogConfirm) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_hine_yundong, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
        TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogConfirm.btnConfirm(1);
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogConfirm!=null){
                    dialogConfirm.btnConfirm(0);
                }
                dialog5.dismiss();
            }
        });
        dialog5.setCancelable(false);
        dialog5.setContentView(view);
        dialog5.show();
    }

    public interface ErrorDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }


    public static Dialog showDiaYouHuiQuan(Context context, List<DiscountUsableNotBean.ListBean> data, final DialogInterfaceYhq dialogInterface) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_you_hui_quan_new, null);
        RecyclerView mRvList = view.findViewById(R.id.rv_list);
        TextView tv_bnt = view.findViewById(R.id.tv_bnt);
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });
        mRvList.setLayoutManager(new LinearLayoutManager(context));
        MineYhq_DlqAdapter mBaseQuickAdapter = new MineYhq_DlqAdapter(data);
        mRvList.setAdapter(mBaseQuickAdapter);
        mBaseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dialogInterface.btnConfirm(data.get(position).getId());
            }
        });
        tv_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.btnConfirm(-1);
            }
        });
        dialog5.setCancelable(false);
        dialog5.setContentView(view);
        dialog5.show();
        return dialog5;
    }

    public interface DialogInterfaceYhq {
        /**
         * 确定
         */
        public void btnConfirm(int type);
    }

    /**
     * 只有一个大按钮 isClose关闭按钮的状态
     */
    public static void showUnificationDialog(Context context, String title, String content,String bntText_1,
                                             boolean isClose,final UnificationDialogInterface mUnificationDialogInterface) {
        showUnificationDialog(context,title,content,bntText_1,"",isClose,false,mUnificationDialogInterface);
    }

    /**
     * 有两个大按钮 isClose关闭按钮的状态 不显示
     */
    public static void showUnificationDialog(Context context, String title, String content,String bntText_1,
                                             String bntText_2, final UnificationDialogInterface mUnificationDialogInterface) {
        showUnificationDialog(context,title,content,bntText_1,bntText_2,false,false,mUnificationDialogInterface);
    }

    /**
     * 有一个大按钮 isClose关闭按钮的状态 显示
     * 并显示输入框//
     */
    public static void showUnificationDialogEditText(Context context, String title, String inputText,final UnificationDialogInterface mUnificationDialogInterface) {
        showUnificationDialog(context,title,inputText,"确定","",true,true,mUnificationDialogInterface);
    }

    /**
     * 统一的弹框
     */
    public static void showUnificationDialog(Context context, String title, String content,String bntText_1,
                                             String bntText_2, boolean isClose,boolean isPass,final UnificationDialogInterface mUnificationDialogInterface) {
        final Dialog unificationDialog = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_unification_style, null);
        TextView tv_title =  view.findViewById(R.id.tv_title);
        TextView tv_content =  view.findViewById(R.id.tv_content);
        TextView bnt_text_1 =  view.findViewById(R.id.bnt_text_1);
        TextView bnt_text_2 =  view.findViewById(R.id.bnt_text_2);
        ImageView iv_close =  view.findViewById(R.id.iv_close);
        //密码框
        LinearLayout ll_pas =  view.findViewById(R.id.ll_pas);
        EditText et_pss =  view.findViewById(R.id.et_pss);
        EditText et_input =  view.findViewById(R.id.et_input);

        if(StringUtil.isBlank(bntText_2)){
            bnt_text_2.setVisibility(View.GONE);
        }
        if(isClose){
            iv_close.setVisibility(View.VISIBLE);
        }
        if(isPass){
            tv_content.setVisibility(View.GONE);
            ll_pas.setVisibility(View.VISIBLE);
            if(StringUtil.isNotBlank(content)){
                et_pss.setVisibility(View.GONE);
                et_input.setVisibility(View.VISIBLE);
                et_input.setHint(content);
                if(title.contains("昵称")){
                    et_input.setMaxLines(6);
                    et_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                }
                if(title.contains("签名")){
                    et_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                }
                if(title.contains("次数")){
                    et_input.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            }
        }
        tv_title.setText(title);
        tv_content.setText(content);
        bnt_text_1.setText(bntText_1);
        bnt_text_2.setText(bntText_2);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unificationDialog.dismiss();
            }
        });

        bnt_text_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUnificationDialogInterface!=null){
                    if(isPass){
                        String pas = et_pss.getText().toString();
                        if(StringUtil.isNotBlank(pas) && pas.length()==6){
                            mUnificationDialogInterface.bntClickListener(et_pss.getText().toString());
                            unificationDialog.dismiss();
                        }
                        String input = et_input.getText().toString();
                        if(StringUtil.isNotBlank(content) && StringUtil.isNotBlank(input)){
                            mUnificationDialogInterface.bntClickListener(input);
                            unificationDialog.dismiss();
                        }
                    }else{
                        unificationDialog.dismiss();
                        mUnificationDialogInterface.bntClickListener("1");
                    }
                }else{
                    unificationDialog.dismiss();
                }
            }
        });

        bnt_text_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unificationDialog.dismiss();
                if(mUnificationDialogInterface!=null){
                    mUnificationDialogInterface.bntClickListener("2");
                }
            }
        });

        unificationDialog.setCancelable(false);
        unificationDialog.setContentView(view);
        unificationDialog.show();
    }
    public interface UnificationDialogInterface {
        /**
         * 确定
         */
        void bntClickListener(String pos);
    }
}
