package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.GameRoomDetailsBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class LandscapeCreateRoomMineAdapter extends BaseQuickAdapter<GameRoomDetailsBean.UserBean, BaseViewHolder> {
    String mapUserId = "";
    public LandscapeCreateRoomMineAdapter(@Nullable List<GameRoomDetailsBean.UserBean> data) {
        super(R.layout.item_landscape_create_room_mine, data);
    }

    public void setMapUserId(String mapUserId) {
        this.mapUserId = mapUserId;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GameRoomDetailsBean.UserBean item) {
        GlideImgLoader.loadImageViewRadius(mContext,item.getAvatar(),helper.getView(R.id.iv_avatar));
        helper.setText(R.id.tv_nickName,item.getNickName())
        .setText(R.id.tv_calories,"0")
        .setText(R.id.tv_distance,"0")
        .setText(R.id.tv_duration, "0");

        if(item.getSportStats()!=null){
            helper.setText(R.id.tv_calories,item.getSportStats().getCalories()).setText(R.id.tv_distance,item.getSportStats().getDistance())
                .setText(R.id.tv_duration, StringUtil.getTimeGeShiYw(item.getSportStats().getDuration()));
        }
        if(mapUserId.equals(item.getUserId())){
            helper.setText(R.id.tv_nickName,item.getNickName()+"(房主)");
        }
    }

}
//0000fff0-0000-1000-8000-00805f9b34fb
//2023-10-17 12:22:40.194 26584-26908 ---》》》                  com.jxkj.fit_5a                      W  mDevReadCharacteristic:0000fff1-0000-1000-8000-00805f9b34fb