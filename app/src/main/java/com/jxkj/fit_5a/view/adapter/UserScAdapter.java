package com.jxkj.fit_5a.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.FavoriteQueryList;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserScAdapter extends BaseQuickAdapter<FavoriteQueryList, BaseViewHolder> {
    public UserScAdapter(@Nullable List<FavoriteQueryList> data) {
        super(R.layout.item_user_sc, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FavoriteQueryList item) {

        helper.setText(R.id.tv_name,item.getMoment().getUser().getNickName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getMoment().getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.tv_content,item.getMoment().getSimpleContent())
                .setGone(R.id.rv_img_list,false)
                .setText(R.id.tv_xihuan,item.getMoment().getLikeCount()+"")
                .setText(R.id.tv_liuyan,item.getMoment().getCommentCount()+"")
                .setText(R.id.tv_browse_num,item.getMoment().getPageviews()+"")
                .addOnClickListener(R.id.ll_xihuan)
                .addOnClickListener(R.id.iv_head_img).addOnClickListener(R.id.tv_name).addOnClickListener(R.id.tv_time);

//        if(item.getMoment().getUser().getRelation()==4){
//            helper.setVisible(R.id.tv_wgz,false).setVisible(R.id.tv_ygz,false);
//        }else if(item.getMoment().getUser().getRelation()==1||item.getMoment().getUser().getRelation()==3){
//            helper.setVisible(R.id.tv_wgz,false).setVisible(R.id.tv_ygz,true);
//        }else{
//            helper.setVisible(R.id.tv_wgz,true).setVisible(R.id.tv_ygz,false);
//        }


        if(StringUtil.isNotBlank(item.getMoment().getTopicArr())){
            try {
                JSONArray array = new JSONArray(item.getMoment().getTopicArr());
                helper.setGone(R.id.tv_topic,true).setText(R.id.tv_topic,"#"+array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if(item.getMoment().isIsLike()){
            helper.setImageDrawable(R.id.iv_xihuan,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }else{
            helper.setImageDrawable(R.id.iv_xihuan,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
        }
        GlideImageUtils.setGlideImage(mContext,item.getMoment().getUser().getAvatar(),helper.getView(R.id.iv_head_img));

        if(StringUtil.isNotBlank(item.getMoment().getMedia())){
            helper.setGone(R.id.rv_img_list,true);

            try {
                JSONArray jsonArray = new JSONArray(item.getMoment().getMedia());

                if(jsonArray.length()==1){
                    String imageUrl = jsonArray.getJSONObject(0).getString("imageUrl");
                    helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
                            .setGone(R.id.siv_3,false).setGone(R.id.siv_4,true).setGone(R.id.rv_img_1,true);
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl,10,helper.getView(R.id.siv_4));
//                    if(item.getMoment().getContentType()==3){
//                        helper.setGone(R.id.iv_baofang,true);
//                    }
                }else if(jsonArray.length()==2){
                    String imageUrl1 = jsonArray.getJSONObject(0).getString("imageUrl");
                    String imageUrl2 = jsonArray.getJSONObject(1).getString("imageUrl");
                    helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true)
                            .setVisible(R.id.siv_3,false).setGone(R.id.siv_4,false).setGone(R.id.rv_img_1,false);
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl1,10,helper.getView(R.id.siv_1));
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl2,10,helper.getView(R.id.siv_2));
                }else if(jsonArray.length()>2){
                    String imageUrl1 = jsonArray.getJSONObject(0).getString("imageUrl");
                    String imageUrl2 = jsonArray.getJSONObject(1).getString("imageUrl");
                    String imageUrl3 = jsonArray.getJSONObject(2).getString("imageUrl");
                    helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true)
                            .setVisible(R.id.siv_3,true).setGone(R.id.siv_4,false).setGone(R.id.rv_img_1,false);
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl1,10,helper.getView(R.id.siv_1));
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl2,10,helper.getView(R.id.siv_2));
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl3,10,helper.getView(R.id.siv_3));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}
