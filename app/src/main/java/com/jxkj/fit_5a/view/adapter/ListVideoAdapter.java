package com.jxkj.fit_5a.view.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ListVideoAdapter extends BaseQuickAdapter<VideoPlayInfoBean.PlayInfoListBean, BaseViewHolder> {
    VideoInterface videoInterface;
    public ListVideoAdapter(@Nullable List<VideoPlayInfoBean.PlayInfoListBean> data, VideoInterface videoInterface) {
        super(R.layout.item_page2, data);
        this.videoInterface = videoInterface;
    }

    public interface VideoInterface {
        /**
         * 留言
         */
        public void btnLiuYan(MomentDetailsBean data,int type);

        public void position(int position);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, VideoPlayInfoBean.PlayInfoListBean bean) {

        MomentDetailsBean data = bean.getData();
        JzvdStdTikTok mp_video = helper.getView(R.id.mp_video);
        TextView tv_gz = helper.getView(R.id.tv_gz);
        TextView tv_topic = helper.getView(R.id.tv_topic);
        TextView tv_shoucang = helper.getView(R.id.tv_shoucang);
        TextView tv_xihuan = helper.getView(R.id.tv_xihuan);
        TextView tv_liuyan = helper.getView(R.id.tv_liuyan);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_fenxiang = helper.getView(R.id.tv_fenxiang);
        TextView tv_content = helper.getView(R.id.tv_content);
        LinearLayout ll_topic = helper.getView(R.id.ll_topic);
        LinearLayout ll_shoucang = helper.getView(R.id.ll_shoucang);
        LinearLayout ll_liuyan = helper.getView(R.id.ll_liuyan);
        ImageView iv_shoucang = helper.getView(R.id.iv_shoucang);
        ImageView iv_xihuan = helper.getView(R.id.iv_xihuan);
        tv_name.setText(data.getUser().getNickName());
        tv_content.setText(data.getContent());
        JZDataSource jzDataSource = new JZDataSource(bean.getPlayURL(),data.getContent());
        jzDataSource.looping = true;
        mp_video.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
        Glide.with(mp_video.getContext()).load(bean.getImageUrl()).into(mp_video.posterImageView);
        if(data.getUser() == null){
            helper.setText(R.id.tv_name,"游客").setVisible(R.id.tv_gz,false);
            GlideImageUtils.setGlideImage(mContext,R.mipmap.icon_app_logo,helper.getView(R.id.iv_head));
        }else{
            helper.setText(R.id.tv_name,data.getUser().getNickName());
            tv_gz.setVisibility(View.INVISIBLE);
            GlideImageUtils.setGlideImage(mContext,data.getUser().getAvatar(),helper.getView(R.id.iv_head));
            //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
            if (data.getUser().getRelation() == 0 ) {
                tv_gz.setText("+关注");
                tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
            } else if (data.getUser().getRelation() == 1) {
                tv_gz.setText("已关注");
                tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
            } else if (data.getUser().getRelation() == 2) {
                tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
                tv_gz.setText("+关注");
            } else if (data.getUser().getRelation() == 3) {
                tv_gz.setText("相互关注");
                tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
            } else if (data.getUser().getRelation() == 4) {
                tv_gz.setVisibility(View.INVISIBLE);
            }
        }

        ll_topic.setVisibility(View.GONE);
        if(StringUtil.isNotBlank(data.getTopicArr())){
            try {
                JSONArray array = new JSONArray(data.getTopicArr());
                ll_topic.setVisibility(View.VISIBLE);
                tv_topic.setText(array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        iv_shoucang.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_share_sc_d));
        tv_shoucang.setText(data.getFavoriteCount()+"");
        if(data.isIsFavorite()){
            iv_shoucang.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_share_sc_dx));
        }

        iv_xihuan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
        tv_xihuan.setText(data.getLikeCount()+"");
        if(data.isIsLike()){
            iv_xihuan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }

        tv_liuyan.setText(data.getCommentCount()+"");

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHomeActivity.startActivity(mContext,bean.getData().getUser().getUserId()+"");
            }
        });
        helper.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHomeActivity.startActivity(mContext,bean.getData().getUser().getUserId()+"");
            }
        });
        videoInterface.position(helper.getLayoutPosition());
        ll_liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("data","data:"+data.getContent());
                videoInterface.btnLiuYan(data,1);
            }
        });
        tv_fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoInterface.btnLiuYan(data,2);
            }
        });
        iv_xihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xihuan(data,iv_xihuan,tv_xihuan);
            }
        });
        ll_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoucang(data,iv_shoucang,tv_shoucang);
            }
        });
        tv_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getzhu(data,tv_gz);
            }
        });
    }


    private void xihuan(MomentDetailsBean data, ImageView iv_xihuan,TextView tv_xihuan){
        if(data.isIsLike()){
            HttpRequestUtils.postLikeCancel(data.getCircleId(),data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        data.setIsLike(false);
                        data.setLikeCount(data.getLikeCount()-1);
                        iv_xihuan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
                        tv_xihuan.setText(data.getLikeCount()+"");
                    }
                }
            });
        }else{
            HttpRequestUtils.postLike(data.getCircleId(),data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")) {
                        data.setIsLike(true);
                        data.setLikeCount(data.getLikeCount()+1);
                        tv_xihuan.setText(data.getLikeCount()+"");
                        iv_xihuan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));

                    }
                }
            });
        }
    }

    private void shoucang(MomentDetailsBean data,ImageView iv_shoucang,TextView tv_shoucang){
        if(data.isIsFavorite()){
            HttpRequestUtils.postFavoritCancel(data.getCircleId(), data.getMomentId(), new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        data.setFavoriteCount(data.getFavoriteCount()-1);
                        data.setIsFavorite(false);
                        iv_shoucang.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_share_sc_d));
                        tv_shoucang.setText(data.getFavoriteCount()+"");

                    }
                }
            });
        }else {
            HttpRequestUtils.postFavorit(data.getCircleId(), data.getMomentId(),
                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            if(path.equals("0")) {
                                data.setFavoriteCount(data.getFavoriteCount()+1);
                                data.setIsFavorite(true);
                                tv_shoucang.setText(data.getFavoriteCount()+"");
                                iv_shoucang.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_share_sc_dx));
                            }
                        }
                    });
        }
    }


    private void getzhu(MomentDetailsBean data, TextView tv_gz){
        if(data.getUser().getRelation() == 1 || data.getUser().getRelation() == 3){
            HttpRequestUtils.postfollowCancel(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("1")){
                        if(data.getUser().getRelation()==3){
                            data.getUser().setRelation(2);
                        }else{
                            data.getUser().setRelation(0);
                        }
                        tv_gz.setText("+关注");
                        tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
                    }
                }
            });
        }else {
            HttpRequestUtils.postfollow(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        if(data.getUser().getRelation()==2){
                            data.getUser().setRelation(3);
                            tv_gz.setText("相互关注");
                            tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
                        }else{
                            data.getUser().setRelation(1);
                            tv_gz.setText("已关注");
                            tv_gz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
                        }
                    }
                }
            });
        }
    }

}
