package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.DeviceCourseTypeData;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.TrainingCourseData;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class YaLingTop_1Adapter extends BaseQuickAdapter<TrainingCourseData, BaseViewHolder> {

    int pos = 0;

    public void setPos(int pos) {
        this.pos = pos;
    }
    public YaLingTop_1Adapter(@Nullable List<TrainingCourseData> data) {
        super(R.layout.item_yaling_top_1, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TrainingCourseData item) {
        if(StringUtil.isNotBlank(item.getBackgroundUrl())){
            GlideImgLoader.loadImageViewRadius(mContext,item.getBackgroundUrl(),10,helper.getView(R.id.iv_icon_1));
            GlideImgLoader.loadImageViewRadius(mContext,item.getBackgroundUrl(),10,helper.getView(R.id.iv_icon));
        }
        GlideImgLoader.loadImageViewRadius(mContext,item.getGifCoverUrl(),10,helper.getView(R.id.iv_icon_gif));
        GlideImgLoader.loadImageViewRadius(mContext,item.getGifCoverUrl(),10,helper.getView(R.id.iv_icon_gif_1));
        helper.setGone(R.id.rl1,false).setGone(R.id.rl,false)
                .setText(R.id.tv_title,item.getName()).setText(R.id.tv_title_1,item.getName())
                .setText(R.id.tv_introduct,item.getName()).setText(R.id.tv_introduct_1,item.getName());
        helper.setBackgroundColor(R.id.rl,mContext.getResources().getColor(R.color.color_ffffff));
        helper.setBackgroundColor(R.id.rl1,mContext.getResources().getColor(R.color.color_ffffff));
        if(helper.getLayoutPosition()==pos){
            helper.setBackgroundRes(R.id.rl,R.drawable.bj_shape_line_theme_2);
            helper.setBackgroundRes(R.id.rl1,R.drawable.bj_shape_line_theme_2);
        }
        if(helper.getLayoutPosition()%2!=0){
            helper.setGone(R.id.rl,true);
        }else{
            helper.setGone(R.id.rl1,true);
        }
    }

}
