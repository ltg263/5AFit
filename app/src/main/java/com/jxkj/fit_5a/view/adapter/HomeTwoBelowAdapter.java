package com.jxkj.fit_5a.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;

import java.util.List;
import java.util.regex.Pattern;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeTwoBelowAdapter extends BaseQuickAdapter<RankStatsData.CaloriesRankingListBean, BaseViewHolder> {
    public HomeTwoBelowAdapter(@Nullable List<RankStatsData.CaloriesRankingListBean> data) {
        super(R.layout.item_home_two_below, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RankStatsData.CaloriesRankingListBean item) {
        if(helper.getLayoutPosition()==0){
            Glide.with(mContext).load(R.drawable.icon_home_two_1).into((ImageView) helper.getView(R.id.iv_1));
            helper.setGone(R.id.tv,false).setGone(R.id.iv_1,true);
        }else if(helper.getLayoutPosition()==1){
            Glide.with(mContext).load(R.drawable.icon_home_two_2).into((ImageView) helper.getView(R.id.iv_1));
            helper.setGone(R.id.tv,false).setGone(R.id.iv_1,true);
        }else if(helper.getLayoutPosition()==2){
            Glide.with(mContext).load(R.drawable.icon_home_two_3).into((ImageView) helper.getView(R.id.iv_1));
            helper.setGone(R.id.tv,false).setGone(R.id.iv_1,true);
        }else{
            helper.setGone(R.id.tv,true).setGone(R.id.iv_1,false);
            helper.setText(R.id.tv,"No."+item.getRanking());
        }
        if(item.getUser()!=null){
           String nameNew = item.getUser().getNickName();
            String q = "";
            String h = "";
           if(!containsChineseCharacters(nameNew)){
               if(nameNew.length()>10){
                   q = nameNew.substring(0,5);
                   h = nameNew.substring(nameNew.length()-3);
                   nameNew = q+"..."+h;
               }
           }else if(nameNew.length()>6){
               q = nameNew.substring(0,3);
               h = nameNew.substring(nameNew.length()-2);
               nameNew = q+"..."+h;
           }
            helper.setText(R.id.tv_1,nameNew).setText(R.id.tv_2,item.getCalories()+"kcal").setText(R.id.tv_3,item.getLikeCount()+"");
            GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_2));
        }
        Glide.with(mContext).load(R.drawable.icon_zan_no).into((ImageView) helper.getView(R.id.iv_3));
        if(item.isLike()){
            Glide.with(mContext).load(R.drawable.icon_zan_yes).into((ImageView) helper.getView(R.id.iv_3));
        }
        helper.addOnClickListener(R.id.ll_dianzan);
        helper.getView(R.id.ll_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHomeActivity.startActivity(mContext,item.getUserId()+"");
            }
        });
    }
    /**
     * 判断字符串是否仅含有数字和字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        return str.matches("^[a-z0-9A-Z]+$");
    }
    /**
     * 是否为汉字，不包括标点符号
     *
     * @param con
     * @return true 是汉字
     */
    public static boolean isChinese(String con) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        for (int i = 0; i < con.length(); i = i + 1) {
            if (!pattern.matcher(
                    String.valueOf(con.charAt(i))).find()) {
                return false;
            }
        }
        return true;
    }
    public static boolean containsChineseCharacters(String str) {
        if (str == null) {
            return false;
        }
        String regex = "[\u4e00-\u9fa5]+";
        return str.matches(".*" + regex + ".*");
    }

}
