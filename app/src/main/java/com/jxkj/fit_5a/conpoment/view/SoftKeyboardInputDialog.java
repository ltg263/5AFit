package com.jxkj.fit_5a.conpoment.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

public class SoftKeyboardInputDialog extends Dialog {
    private Activity activity;
    DialogInterfaceKey dialogInterface;
    String context2;
    public SoftKeyboardInputDialog(@NonNull Context context, String context2,int themeResId,DialogInterfaceKey dialogInterface) {
        super(context, themeResId);
        this.activity=(Activity) context;
        this.dialogInterface = dialogInterface;
        this.context2 = context2;
    }

    public interface DialogInterfaceKey{
        /**
         * 确定
         */
        public void strContext(String context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //==dialog设置布局，也可以用xml文件的布局
        setContentView(R.layout.activity_association_dialog);
        EditText tv_content = findViewById(R.id.tv_content);
        TextView tv_fabu = findViewById(R.id.tv_fabu);
        if(StringUtil.isNotBlank(context2)){
            tv_content.setHint(context2);
        }
        tv_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.strContext(tv_content.getText().toString().trim());
            }
        });
        //==监听dialog弹出后立即弹出软键盘并设置焦点
        setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(300);//在setOnShowListener方法里弹出暂为发现冲突，所以不休眠等dialog弹出后再弹出键盘
                        //editText.requestFocus();必须在该线程执行；弹出键盘的代码也是，否则有概率不弹出键盘
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //设置控件焦点
                                tv_content.setFocusable(true);
                                tv_content.setFocusableInTouchMode(true);
                                tv_content.requestFocus();

                                //弹出键盘
                                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                            }
                        });


                    }
                }).start();
            }
        });

        //==设置dialog参数
        Window window=getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams windowParams=window.getAttributes();
        windowParams.width=WindowManager.LayoutParams.MATCH_PARENT;

    }

}
