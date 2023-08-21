package com.jxkj.fit_5a.view.activity.login_other;

import android.content.Intent;
import android.view.View;

import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.view.activity.exercise.HistoryEquipmentActivity;

import butterknife.OnClick;


public class WelcomeLoginActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        isShowTitle();
        return R.layout.activity_welcome_login;
    }

    @Override
    protected void initViews() {

    }



    @OnClick({R.id.tv_go, R.id.tv_go_x})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_go_x:
                IntentUtils.getInstence().intent(this,HistoryEquipmentActivity.class);
                break;
        }
    }
}
