package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.DataCleanManager;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineSetActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_banben)
    TextView tv_banben;
    @BindView(R.id.tv_con)
    TextView tv_con;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("设 置");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        tv_banben.setText("5AFit-V" + StringUtil.getVersionName(this));
        try {
            tv_con.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getParameterUp() {

        HttpRequestUtils.getVersionUpdating(this, new HttpRequestUtils.UploadFileInterface() {
            @Override
            public void succeed(String path) {

            }

            @Override
            public void failure() {
                ToastUtils.showShort("已经是最新版本");
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.ll1, R.id.ll2,R.id.ll3,R.id.ll4,R.id.tv_tui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll3:
                getParameterUp();
                break;
            case R.id.ll1:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                break;
            case R.id.ll4:
                startActivity(new Intent(this,UserHmdActivity.class));

                break;
            case R.id.ll2:
                DataCleanManager.clearAllCache(this);
                try {
                    tv_con.setText(DataCleanManager.getTotalCacheSize(this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_tui:
                DialogUtils.showUnificationDialog(this, "退出登录","您确定要退出登录吗？", "退出",true,
                        new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        if(ThirdLoginUtils.mTencent!=null){
                            ThirdLoginUtils.mTencent.logout(MineSetActivity.this);
                        }
                        startActivity(new Intent(MineSetActivity.this, LoginActivity.class));
                        MainApplication.getContext().AppExit();
                        SharedUtils.singleton().clear();
                        finish();
                    }
                });
                break;
        }
    }
}

