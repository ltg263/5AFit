package com.jxkj.fit_5a.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    public static BaseResp resp =null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.api = WXAPIFactory.createWXAPI(this, ConstValues.WX_APP_ID, true);

        boolean handleIntent =this.api.handleIntent(this.getIntent(), this);

        if(!handleIntent){
            finish();

        }
    }


    protected void onNewIntent(Intent var1) {
        super.onNewIntent(var1);

        this.setIntent(var1);

        this.api.handleIntent(var1, this);

    }

    public void onReq(BaseReq var1) {
    }

    public void onResp(BaseResp var1) {
        if (var1 !=null) {
            resp = var1;

        }

        switch(var1.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:

                Log.v("WXEntryActivity", "发送被拒绝");

                break;

            case BaseResp.ErrCode.ERR_SENT_FAILED:

                Log.v("WXEntryActivity", "发送-3");

                break;

            case BaseResp.ErrCode.ERR_COMM:

                Log.v("WXEntryActivity", "发送-1");

                break;

            case BaseResp.ErrCode.ERR_USER_CANCEL:

                Log.v("WXEntryActivity", "发送取消");

                break;

            case BaseResp.ErrCode.ERR_OK:
//                Intent intent =new Intent(Constants.wxshare_action);
//                sendBroadcast(intent);
                break;
        }
        this.finish();
    }

}