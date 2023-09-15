package com.jxkj.fit_5a.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceTypeData;
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
public class MineRwzxDzAdapter extends BaseQuickAdapter<TaskListBase.ListBean, BaseViewHolder> {

    List<DeviceTypeData.ListBean> mList;
    public MineRwzxDzAdapter(@Nullable List<TaskListBase.ListBean> data) {
        super(R.layout.item_mine_rwzx_dz, data);
    }

    public void setList(List<DeviceTypeData.ListBean> mList) {
        this.mList = mList;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TaskListBase.ListBean item) {
        helper.setText(R.id.tv1,item.getName()).setText(R.id.tv2,item.getExplain())
                .addOnClickListener(R.id.bnt);

        if(item.getSpeeds()!=null && item.getSpeeds().size()>0 && mList != null){
            String mParamId = item.getSpeeds().get(item.getSpeeds().size()-1).getParamId();
            String deviceName = "";
            if(mParamId.length()>4){
                int deviceId = Integer.parseInt(mParamId.substring(mParamId.length()-3,mParamId.length()-1));
                Log.w("deviceId","deviceId:"+deviceId);
                for(int i =0;i<mList.size();i++){
                    if(deviceId == mList.get(i).getId()){
                        deviceName = mList.get(i).getName();
                        item.setName_rw(mList.get(i).getName());
                        item.setImg_rs(mList.get(i).getImg());
                        item.setId_rw(mList.get(i).getId());
                        break;
                    }
                }
            }
            helper.setText(R.id.tv3,deviceName);
        }
        if(item.getRewards()!=null && item.getRewards().size()>0){
//            String detail = item.getRewards().get(item.getRewards().size()-1).getDetail();
//            String object="";
//            try {
//                object = new JSONObject(detail).getString("incrementValue");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            helper.setText(R.id.tv_4,item.getRewards().get(item.getRewards().size()-1).getName());
        }

    }

}
