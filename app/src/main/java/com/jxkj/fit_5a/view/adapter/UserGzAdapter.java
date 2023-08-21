package com.jxkj.fit_5a.view.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.QueryPopularBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserGzAdapter extends BaseQuickAdapter<FollowFansList.DataBean, BaseViewHolder> {
    public UserGzAdapter(@Nullable List<FollowFansList.DataBean> data) {
        super(R.layout.item_user_gz, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FollowFansList.DataBean item) {
        FollowFansList.DataBean.UserBean user = item.getUser();
        Log.w("--->>>>>>>>>>>>>","getContent:"+user.getNickName());
        if(user==null){
            return;
        }
        //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
        if (user.getRelation() == 0 ) {
            helper.setText(R.id.tv_gz,"+关注");
            helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_theme_25);
        } else if (user.getRelation() == 1) {
            helper.setText(R.id.tv_gz,"已关注");
            helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);
        } else if (user.getRelation() == 2) {
            helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_theme_25);
            helper.setText(R.id.tv_gz,"+关注");
        } else if (user.getRelation() == 3) {
            helper.setText(R.id.tv_gz,"相互关注");
            helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);
        } else if (user.getRelation() == 4) {
            helper.setVisible(R.id.tv_gz,false);
        }
        GlideImageUtils.setGlideImage(mContext,user.getAvatar(),helper.getView(R.id.iv_head_img));
        helper.setText(R.id.tv_user_name,user.getNickName()).setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd"));
        if(user.getGender()==1){
            Glide.with(mContext).load(R.drawable.icon_xb_nan).into((ImageView) helper.getView(R.id.iv_xb));
        }else{
            Glide.with(mContext).load(R.drawable.icon_xb_nv).into((ImageView) helper.getView(R.id.iv_xb));
        }
        helper.getView(R.id.tv_gz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getzhu(user);
            }
        });
    }

    private void getzhu(FollowFansList.DataBean.UserBean user){
        if(user.getRelation() == 1 || user.getRelation() == 3){
            HttpRequestUtils.postfollowCancel(user.getUserId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("1")){
                        if(user.getRelation()==3){
                            user.setRelation(2);
                        }else{
                            user.setRelation(0);
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }else {
            HttpRequestUtils.postfollow(user.getUserId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        if(user.getRelation()==2){
                            user.setRelation(3);
                        }else{
                            user.setRelation(1);
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }

}
