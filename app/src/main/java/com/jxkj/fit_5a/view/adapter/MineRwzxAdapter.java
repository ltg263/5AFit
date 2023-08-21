package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.TaskListBase;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineRwzxAdapter extends BaseQuickAdapter<TaskListBase.ListBean, BaseViewHolder> {
    boolean isfinishTask = false;
    public MineRwzxAdapter(@Nullable List<TaskListBase.ListBean> data) {
        super(R.layout.item_mine_rwzx, data);
    }

    public void setIsfinishTask(boolean isfinishTask) {
        this.isfinishTask = isfinishTask;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TaskListBase.ListBean item) {
        GlideImageUtils.setGlideImage(mContext,item.getImg(),helper.getView(R.id.iv));
        helper.setText(R.id.tv1,item.getName());

        if(item.getRewards()!=null && item.getRewards().size()>0){
            String detail = item.getRewards().get(item.getRewards().size()-1).getDetail();
            String object="";
            try {
                object = new JSONObject(detail).getString("incrementValue");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            helper.setText(R.id.tv3,"+"+object);
        }

        if(item.getSpeeds()!=null && item.getSpeeds().size()>0){
            helper.setText(R.id.tv2,"完成"+item.getSpeeds().get(item.getSpeeds().size()-1).getSpeed()+"/"+item.getSpeeds().get(item.getSpeeds().size()-1).getTarget()+item.getSpeeds().get(item.getSpeeds().size()-1).getUnit());
        }
        if(isfinishTask){
            if(StringUtil.isNotBlank(item.getReward())){
                try {
                    JSONArray mJSONArray = new JSONArray(item.getReward());
                    if(mJSONArray.length()>0){
                        String detail = mJSONArray.getJSONObject(0).getString("detail");
                        JSONObject mJSONObject = new JSONObject(detail);
                        helper.setText(R.id.tv3,"+"+mJSONObject.getString("incrementValue"));
                        helper.setText(R.id.tv2,"已完成");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
