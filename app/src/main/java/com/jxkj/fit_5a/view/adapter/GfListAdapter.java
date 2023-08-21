package com.jxkj.fit_5a.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.QueryPopularMomentBean;
import com.jxkj.fit_5a.entity.UserReportType;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity_Gf;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class GfListAdapter extends BaseQuickAdapter<QueryPopularMomentBean, BaseViewHolder> {
    public GfListAdapter(@Nullable List<QueryPopularMomentBean> data) {
        super(R.layout.item_home_gf, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularMomentBean item) {
        helper.setText(R.id.tv_tw_contact, item.getTitle()).setVisible(R.id.ll_topic,false)
                .setText(R.id.tv_sp_contact,item.getTitle())
                .setText(R.id.tv_tw_contact_f,item.getIntroduction())
                .setText(R.id.tv_shoucang,item.getFavoriteCount()+"")
                .setText(R.id.tv_liuyan,item.getCommentCount()+"")
                .setText(R.id.tv_xihuan,item.getLikeCount()+"")
                .setGone(R.id.rv_img_tw,false)
                .setGone(R.id.rv_img_sp,false);
        if(StringUtil.isNotBlank(item.getTopicArr())){
            try {
                JSONArray array = new JSONArray(item.getTopicArr());
                helper.setVisible(R.id.ll_topic,true).setText(R.id.tv_topic,array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (item.isIsLike()) {
            helper.setImageResource(R.id.iv_xihuan, R.drawable.ic_celect_xh_yes);
        }

        if (item.isIsFavorite()) {
            helper.setImageResource(R.id.iv_shoucang, R.drawable.icon_share_sc_dx);
        }
        if(item.isVideo()){
            helper.setVisible(R.id.rv_img_sp,true);
        }else{
            helper.setVisible(R.id.rv_img_tw,true);
        }
        setOnClickListener(helper,helper.getView(R.id.ll_topic),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_liuyan),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_xihuan),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_shoucang),item,this);

        GlideImgLoader.loadImageViewRadius(mContext,item.getCoverImageUrl(),10,helper.getView(R.id.iv_tupian));
        GlideImgLoader.loadImageViewRadius(mContext,item.getCoverImageUrl(),10,helper.getView(R.id.iv_shipin));

    }


    private void setOnClickListener(BaseViewHolder helper,View view,QueryPopularMomentBean data,BaseQuickAdapter adapter) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (view.getId()){
                    case R.id.ll_topic:
//                        MineTopicActivity.startActivity(mContext,((TextView)helper.getView(R.id.tv_topic)).getText().toString());
//                        break;
                    case R.id.ll_liuyan:
                        WebViewActivity_Gf.startActivityIntent(mContext,data.getMomentId(),data.getPublisherId(),"详情");
                        break;
                    case R.id.ll_xihuan:
                        xihuan(data,helper);
                        break;
                    case R.id.ll_shoucang:
                        shoucang(data,helper);
                        break;
                }
            }
        });
    }



    private void xihuan(QueryPopularMomentBean data, BaseViewHolder helper){
        if(data.isIsLike()){
            HttpRequestUtils.postLikeCancel_gf(data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        data.setIsLike(false);
                        data.setLikeCount((Integer.parseInt(data.getLikeCount())-1)+"");
                        helper.setImageResource(R.id.iv_xihuan,R.drawable.icon_xin_99_d);
                        helper.setText(R.id.tv_xihuan,data.getLikeCount());
                    }
                }
            });
        }else{
            HttpRequestUtils.postLike_gf(data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")) {
                        data.setIsLike(true);
                        data.setLikeCount((Integer.parseInt(data.getLikeCount())+1)+"");
                        helper.setImageResource(R.id.iv_xihuan,R.drawable.ic_celect_xh_yes);
                        helper.setText(R.id.tv_xihuan,data.getLikeCount());
                    }
                }
            });
        }
    }

    private void shoucang(QueryPopularMomentBean data, BaseViewHolder helper){
        if(data.isIsFavorite()){
            HttpRequestUtils.postFavoritCancel_gf(data.getMomentId(), new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        data.setFavoriteCount(Integer.parseInt(data.getFavoriteCount())-1+"");
                        data.setIsFavorite(false);
                        helper.setImageResource(R.id.iv_shoucang,R.drawable.icon_share_sc_d);
                        helper.setText(R.id.tv_shoucang,data.getFavoriteCount());
                    }
                }
            });
        }else {
            HttpRequestUtils.postFavorit_gf(data.getMomentId(),
                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            if(path.equals("0")) {
                                data.setFavoriteCount(Integer.parseInt(data.getFavoriteCount())+1+"");
                                data.setIsFavorite(true);
                                helper.setImageResource(R.id.iv_shoucang,R.drawable.icon_share_sc_dx);
                                helper.setText(R.id.tv_shoucang,data.getFavoriteCount());
                            }
                        }
                    });
        }
    }

}
