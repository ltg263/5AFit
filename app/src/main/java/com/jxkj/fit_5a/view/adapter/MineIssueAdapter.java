package com.jxkj.fit_5a.view.adapter;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.HelpListData;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineIssueAdapter extends BaseQuickAdapter<HelpListData.ListBean, BaseViewHolder> {
    public MineIssueAdapter(@Nullable List<HelpListData.ListBean> data) {
        super(R.layout.item_mine_issue, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HelpListData.ListBean item) {

        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_num,(getLoadMoreViewPosition()+1)+"");
        WebView mVebView = helper.getView(R.id.tv_content);
        initWebView(mVebView,item.getDetail());
        helper.getView(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mVebView.getVisibility()==View.VISIBLE){
                    mVebView.setVisibility(View.GONE);
                }else{
                    mVebView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initWebView(WebView mVebView,String detail) {
        WebSettings webSettings = mVebView.getSettings();//获取webview设置属性
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        //支持javascript
//        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mVebView.loadData(
                StringUtil.getNewContent1(detail), "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }

}
