package com.jxkj.fit_5a.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;

import java.util.List;

public class PopupWindowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PopupWindowAdapter(List<String> data) {
        super(R.layout.item_spinner_product_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,String item) {
        helper.setText(R.id.text,item).setVisible(R.id.view,true);
        if(helper.getLayoutPosition()==mData.size()-1){
            helper.setVisible(R.id.view,false);
        }

    }

}
