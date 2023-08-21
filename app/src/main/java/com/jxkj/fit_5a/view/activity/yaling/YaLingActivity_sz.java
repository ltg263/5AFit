package com.jxkj.fit_5a.view.activity.yaling;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YaLingActivity_sz extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_shuliang)
    TextView tv_shuliang;
    @BindView(R.id.iv_txhtj)
    ImageView iv_txhtj;
    List<String> listTime = new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.activity_yaling_sz;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("模式选择");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        boolean isSele = SharedUtils.singleton().get("yuyinbobao", true);
        int numliang = SharedUtils.singleton().get("yuyinbobao_numliang", 0);
        if(numliang==0){
            numliang = 5;
            SharedUtils.singleton().put("yuyinbobao_numliang", numliang);
        }
        iv_txhtj.setSelected(isSele);
        tv_shuliang.setText(numliang+"次");
    }


    @OnClick({R.id.iv_back, R.id.ll_back, R.id.iv_txhtj,R.id.tv_shuliang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shuliang:
                listTime.clear();
                for(int i=1;i<11;i++){
                    listTime.add((i * 5) +" 次");
                }
                PickerViewUtils.selectorCustom(this, listTime, "个数频率",
                        new PickerViewUtils.ConditionInterfacd() {
                            @Override
                            public void setIndex(int pos) {
                                String num = listTime.get(pos).replace(" 次","");
                                SharedUtils.singleton().put("yuyinbobao_numliang", Integer.parseInt(num));
                                tv_shuliang.setText(num+"次");
                            }
                        });
                break;
            case R.id.iv_back:
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_txhtj:
                iv_txhtj.setSelected(!iv_txhtj.isSelected());
                SharedUtils.singleton().put("yuyinbobao",iv_txhtj.isSelected());
                break;
        }
    }
}
