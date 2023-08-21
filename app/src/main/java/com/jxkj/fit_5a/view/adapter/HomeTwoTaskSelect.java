package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.CircleTaskData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeTwoTaskSelect extends BaseQuickAdapter<CircleTaskData, BaseViewHolder> {
    public HomeTwoTaskSelect(@Nullable List<CircleTaskData> data) {
        super(R.layout.item_home_two_task_select, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CircleTaskData item) {
        if(item.isSelect()){
            helper.setGone(R.id.rl_select_no,false).setGone(R.id.rl_select_yes,true);
        }else{
            helper.setGone(R.id.rl_select_no,true).setGone(R.id.rl_select_yes,false);
        }
        GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv_head_img));
        GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv_head_img1));
        helper.setText(R.id.tv_title,item.getName()).setText(R.id.tv_title1,item.getName())
                .setText(R.id.tv_day,item.getRoundCount()+"天").setText(R.id.tv_day1,item.getRoundCount()+"天")
                .setText(R.id.tv_explain,item.getExplain()).setText(R.id.tv_explain1,item.getExplain());
        if(item.getSpeeds()!=null && item.getSpeeds().size()>0){
            helper.setText(R.id.tv_target,item.getSpeeds().get(0).getTarget()+item.getSpeeds().get(0).getUnit())
                    .setText(R.id.tv_paramName,item.getSpeeds().get(0).getParamName());

            helper.setText(R.id.tv_target1,item.getSpeeds().get(0).getTarget()+item.getSpeeds().get(0).getUnit())
                    .setText(R.id.tv_paramName1,item.getSpeeds().get(0).getParamName());
        }
        if(item.getRewards()!=null && item.getRewards().size()>0){
            String detail = item.getRewards().get(0).getDetail();
            String object="";
            try {
                object = new JSONObject(detail).getString("incrementValue");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            helper.setText(R.id.tv_incrementValue1,object)
                    .setText(R.id.tv_name1,item.getRewards().get(0).getName());
            helper.setText(R.id.tv_incrementValue,object)
                    .setText(R.id.tv_name,item.getRewards().get(0).getName());
        }

    }

}
