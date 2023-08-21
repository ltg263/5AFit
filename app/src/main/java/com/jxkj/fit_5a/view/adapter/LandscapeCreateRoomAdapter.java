package com.jxkj.fit_5a.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.shape.CornerTreatment;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.entity.MapListSposrt;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class LandscapeCreateRoomAdapter extends BaseQuickAdapter<MapListSposrt.ListBean, BaseViewHolder> {
    int pos = -1;
    public LandscapeCreateRoomAdapter(@Nullable List<MapListSposrt.ListBean> data) {
        super(R.layout.item_landscape_create_room, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MapListSposrt.ListBean item) {
        helper.setBackgroundRes(R.id.ll,0).setVisible(R.id.iv_imgUrl_yy,true);

        helper.setText(R.id.tv_difficultyLevel,"0")
                .setText(R.id.tv_distance,item.getDistance()+"km")
                .setText(R.id.tv_name,item.getName());

        if(item.getBoxs()!=null){
            helper.setText(R.id.tv_difficultyLevel,item.getBoxs().size()+"");
        }
        Glide.with(mContext).load(item.getImgUrl()).into((ImageView)helper.getView(R.id.iv_imgUrl));
        if(helper.getLayoutPosition()==pos){
            helper.setBackgroundRes(R.id.ll,R.drawable.shape_yy_ff_all_10).setVisible(R.id.iv_imgUrl_yy,false);
        }
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
