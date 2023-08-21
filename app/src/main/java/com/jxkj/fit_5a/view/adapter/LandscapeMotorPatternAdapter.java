package com.jxkj.fit_5a.view.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.GameRoomListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class LandscapeMotorPatternAdapter extends BaseQuickAdapter<GameRoomListBean.ListBean, BaseViewHolder> {
    public LandscapeMotorPatternAdapter(@Nullable List<GameRoomListBean.ListBean> data) {
        super(R.layout.item_landscape_motor_pattern, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GameRoomListBean.ListBean item) {
        TextView tv_verification = helper.getView(R.id.tv_verification);
        tv_verification.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.ic_suo_2),null,null,null);
        GlideImageUtils.setGlideImage(mContext,item.getSportMapBase().getImgUrl(),helper.getView(R.id.iv_imgUrl));
        helper.setText(R.id.tv_name,item.getName()).setText(R.id.tv_verification,(helper.getLayoutPosition()+1)+"")
                .setText(R.id.tv_distance,item.getSportMapBase().getDistance()+"km")
                .setText(R.id.tv_map_name,item.getSportMapBase().getName())
                .setText(R.id.tv_,"宝箱：0个")
                .setText(R.id.tv_limitNumber,"人数:"+item.getCurrentNumber()+"/"+item.getLimitNumber()+"人");
        if(item.getSportMapBase().getBoxs()!=null){
            helper.setText(R.id.tv_,"宝箱："+item.getSportMapBase().getBoxs().size()+"个");
        }
        if(item.getUser()!=null){
            helper.setText(R.id.tv_nickName,"房主:"+item.getUser().getNickName());
        }else{
            helper.setText(R.id.tv_nickName,"房主:"+"");
        }
        if(item.isVerification()){
            tv_verification.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.ic_suo_1),null,null,null);
        }
    }

}
