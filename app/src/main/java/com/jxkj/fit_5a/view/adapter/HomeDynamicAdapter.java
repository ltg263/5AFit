package com.jxkj.fit_5a.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.PostUser;
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
import com.jxkj.fit_5a.entity.UserReportType;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.association.MineTopicActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.fragment.HomeThreeNewFragment;
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
public class HomeDynamicAdapter extends BaseQuickAdapter<QueryPopularBean, BaseViewHolder> {
    public HomeDynamicAdapter(@Nullable List<QueryPopularBean> data) {
        super(R.layout.item_home_dynamic, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularBean item) {
        if(MainActivity.mBlackListId!=null && MainActivity.mBlackListId.contains(item.getUser().getUserId())){
            helper.setGone(R.id.rl_all_context,false).setVisible(R.id.view,true);
            return;
        }
        helper.setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.tv_content,item.getSimpleContent())
                .setVisible(R.id.rl_all_context,true).setGone(R.id.view,false)
                .setText(R.id.tv_xihuan,item.getLikeCount()+"")
                .setText(R.id.tv_liuyan,item.getCommentCount()+"")
                .setText(R.id.tv_shoucang,item.getFavoriteCount()+"")
                .setGone(R.id.ll_topic,false)
                .setGone(R.id.rv_img_list,false)
                .setGone(R.id.tv_quanzi,false)
                .setGone(R.id.rv_img_2,false)
                .setVisible(R.id.tv_gz,true)
                .setText(R.id.tv_browse_num,"浏览 "+item.getPageviews()+" 次")
                .setImageResource(R.id.iv_xihuan, R.drawable.icon_xin_99_d)
                .setImageResource(R.id.iv_shoucang, R.drawable.icon_share_sc_d);

        if (item.isIsLike()) {
            helper.setImageResource(R.id.iv_xihuan, R.drawable.ic_celect_xh_yes);
        }

        if (item.isIsFavorite()) {
            helper.setImageResource(R.id.iv_shoucang, R.drawable.icon_share_sc_dx);
        }

        setOnClickListener(helper,helper.getView(R.id.rl_all_context),item,this);
        setOnClickListener(helper,helper.getView(R.id.iv_head_img),item,this);
        setOnClickListener(helper,helper.getView(R.id.tv_name),item,this);
        setOnClickListener(helper,helper.getView(R.id.tv_time),item,this);
        setOnClickListener(helper,helper.getView(R.id.tv_gz),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_liuyan),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_xihuan),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_shoucang),item,this);
        setOnClickListener(helper,helper.getView(R.id.tv_fenxiang),item,this);
        setOnClickListener(helper,helper.getView(R.id.ll_topic),item,this);
        setOnClickListener(helper,helper.getView(R.id.iv_jubao),item,this);

        setOnClickListenerImgUrl(item,helper.getView(R.id.siv_1),0);
        setOnClickListenerImgUrl(item,helper.getView(R.id.siv_2),1);
        setOnClickListenerImgUrl(item,helper.getView(R.id.siv_3),2);
        setOnClickListenerImgUrl(item,helper.getView(R.id.siv_11),0);
        setOnClickListenerImgUrl(item,helper.getView(R.id.siv_22),1);
        setOnClickListenerImgUrl(item,helper.getView(R.id.siv_5),0);



        helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false)
                .setGone(R.id.siv_3,false).setGone(R.id.rv_img_1,false);

        if(item.getCircleInfo()!=null && !(mContext instanceof MineCircleActivity)){
            helper.setVisible(R.id.tv_quanzi,true).setText(R.id.tv_quanzi,item.getCircleInfo().getName());
            setOnClickListener(helper,helper.getView(R.id.tv_quanzi),item,this);
        }
        if(item.getUser() == null){
            helper.setText(R.id.tv_name,"游客").setVisible(R.id.tv_gz,false);
            GlideImageUtils.setGlideImage(mContext,"",helper.getView(R.id.iv_head_img));
        }else{
            helper.setText(R.id.tv_name,item.getUser().getNickName());
            GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_img));
            //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
            if (item.getUser().getRelation() == 0 ) {
                helper.setText(R.id.tv_gz,"+关注");
                helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_theme_25);
            } else if (item.getUser().getRelation() == 1) {
                helper.setText(R.id.tv_gz,"已关注");
                helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);
            } else if (item.getUser().getRelation() == 2) {
                helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_theme_25);
                helper.setText(R.id.tv_gz,"+关注");
            } else if (item.getUser().getRelation() == 3) {
                helper.setText(R.id.tv_gz,"相互关注");
                helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);
            } else if (item.getUser().getRelation() == 4) {
                helper.setVisible(R.id.tv_gz,false);
            }
        }

        if(StringUtil.isNotBlank(item.getTopicArr())){
            try {
                JSONArray array = new JSONArray(item.getTopicArr());
                helper.setGone(R.id.ll_topic,true).setText(R.id.tv_topic,array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(StringUtil.isNotBlank(item.getMedia())){
            helper.setGone(R.id.rv_img_list,true);

            try {
                JSONArray jsonArray = new JSONArray(item.getMedia());

                helper.setGone(R.id.siv_1,false).setGone(R.id.siv_2,false).setGone(R.id.rv_img_2,false)
                        .setGone(R.id.siv_3,false).setGone(R.id.siv_5,false).setGone(R.id.rv_img_1,false);
                if(jsonArray.length()==1){
                    String imageUrl = jsonArray.getJSONObject(0).getString("imageUrl");
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl,10,helper.getView(R.id.siv_4));
                    if(item.getContentType().equals("3")){
                        GlideImgLoader.loadImageViewRadius(mContext,imageUrl,10,helper.getView(R.id.siv_4));
                        helper.setGone(R.id.rv_img_1,true);
                        String vedioId = jsonArray.getJSONObject(0).getString("vedioId");
                        HttpRequestUtils.getPlayInfo(mContext,vedioId, new HttpRequestUtils.PlayInfoInterface() {

                            @Override
                            public void succeed(Result<VideoPlayInfoBean> result) {
                                if(result.getCode()==0 && result.getData().getPlayInfoList()!=null){
                                    List<VideoPlayInfoBean.PlayInfoListBean> mBeans = result.getData().getPlayInfoList();
                                    if(mBeans.size()>0){
                                        HttpRequestUtils.initVideo(mContext,mBeans.get(0).getPlayURL(),vedioId);
                                    }
                                }
                            }
                        });
                    }else{
                        GlideImgLoader.loadImageViewRadius(mContext,imageUrl,10,helper.getView(R.id.siv_5));
                        helper.setVisible(R.id.siv_5,true);
                    }
                }else if(jsonArray.length()==2){
                    String imageUrl1 = jsonArray.getJSONObject(0).getString("imageUrl");
                    String imageUrl2 = jsonArray.getJSONObject(1).getString("imageUrl");
                    helper.setVisible(R.id.rv_img_2,true);
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl1,10,helper.getView(R.id.siv_11));
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl2,10,helper.getView(R.id.siv_22));
                }else if(jsonArray.length()>2){
                    String imageUrl1 = jsonArray.getJSONObject(0).getString("imageUrl");
                    String imageUrl2 = jsonArray.getJSONObject(1).getString("imageUrl");
                    String imageUrl3 = jsonArray.getJSONObject(2).getString("imageUrl");
                    helper.setVisible(R.id.siv_1,true).setVisible(R.id.siv_2,true).setVisible(R.id.siv_3,true);
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl1,10,helper.getView(R.id.siv_1));
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl2,10,helper.getView(R.id.siv_2));
                    GlideImgLoader.loadImageViewRadius(mContext,imageUrl3,10,helper.getView(R.id.siv_3));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void setOnClickListenerImgUrl(QueryPopularBean item,View view,int pos) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<LocalMedia> list_path = new ArrayList<>();
                if (StringUtil.isNotBlank(item.getMedia()) && !item.getMedia().equals("[]")) {
                    try {
                        JSONArray split = new JSONArray(item.getMedia());
                        for (int i = 0; i < split.length(); i++) {
                            LocalMedia mLocalMedia = new LocalMedia();
                            mLocalMedia.setCompressPath(split.getJSONObject(i).getString("imageUrl"));
                            mLocalMedia.setPath(split.getJSONObject(i).getString("imageUrl"));
                            list_path.add(mLocalMedia);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                MatisseUtils.oPenUrlImgL((Activity) mContext, list_path, pos);
            }
        });
    }

    private void setOnClickListener(BaseViewHolder helper,View view,QueryPopularBean data,BaseQuickAdapter adapter) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (view.getId()){
                    case R.id.iv_head_img:
                    case R.id.tv_name:
                    case R.id.tv_time:
                        UserHomeActivity.startActivity(mContext,data.getUser().getUserId());
                        break;
                    case R.id.tv_gz:
                        getzhu(data,helper);
                        break;
                    case R.id.ll_xihuan:
                        xihuan(data,helper);
                        break;
                    case R.id.ll_shoucang:
                        shoucang(data,helper);
                        break;
                    case R.id.tv_fenxiang:

                        break;
                    case R.id.ll_topic:
                        MineTopicActivity.startActivity(mContext,((TextView)helper.getView(R.id.tv_topic)).getText().toString());
                        break;
                    case R.id.tv_quanzi:
                        Intent mIntent = new Intent(mContext, MineCircleActivity.class);
                        mIntent.putExtra("id",data.getCircleInfo().getCircleId());
                        mContext.startActivity(mIntent);
                        break;
                    case R.id.ll_liuyan:
                        if(data.getContentType().equals("3")){
                            VideoActivity.startActivity(mContext,data.getCircleId(),data.getPublisherId(),data.getMomentId());
                        }else{
                            AssociationActivity.startActivity(mContext,data.getCircleId(),data.getPublisherId(),data.getMomentId(),"000");
                        }
                        break;
                    case R.id.rl_all_context:
                        if(data.getContentType().equals("3")){
                            VideoActivity.startActivity(mContext,data.getCircleId(),data.getPublisherId(),data.getMomentId());
                        }else{
                            AssociationActivity.startActivity(mContext,data.getCircleId(),data.getPublisherId(),data.getMomentId());
                        }
                        break;
                    case R.id.iv_jubao:
                        DialogUtils.showDialogJiaRuHeiMingDan(mContext, new DialogUtils.DialogInterfaceYhq() {
                            @Override
                            public void btnConfirm(int type) {
                                //加入黑名单
                                if(type == 0){
                                    postBlackList(data.getUser().getUserId());
                                    return;
                                }
                                DialogUtils.showDialogJuBao(mContext, new DialogUtils.UnificationDialogInterface() {
                                    @Override
                                    public void bntClickListener(String id) {
                                        postUserReportType(data,id);
                                    }
                                });
                            }
                        });
                        break;
                }
            }
        });
    }

    private void postBlackList(String userId) {
        if(SharedUtils.getUserId() == Integer.parseInt(userId)){
//            ToastUtils.showShort("");
//            return;
        }
        RetrofitUtil.getInstance().apiService()
                .postBlackList(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            ToastUtils.showShort("加入成功");
                            HttpRequestUtils.postBlackList((Activity) mContext,new HttpRequestUtils.LoginInterface() {
                                @Override
                                public void succeed(String path) {
                                    if(path.equals("0")){
                                        notifyDataSetChanged();
                                    }
                                }
                            });
                        }else{
//                            ToastUtils.showShort("加入失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        ToastUtils.showShort("加入失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void postUserReportType(QueryPopularBean data, String id) {

        UserReportType mPostUser = new UserReportType();
        UserReportType.RelationParamBean mRelationParamBean = new UserReportType.RelationParamBean();
        UserReportType.RelationParamBean.MomentKeyPoBean mMomentKeyPoBean = new UserReportType.RelationParamBean.MomentKeyPoBean();
        mPostUser.setType(Integer.parseInt(id));
        mPostUser.setReportedUserId(Integer.parseInt(data.getUser().getUserId()));
        mRelationParamBean.setType(1);
        mMomentKeyPoBean.setCircleId(data.getCircleId());
        mMomentKeyPoBean.setMomentId(Long.parseLong(data.getMomentId()));
        mMomentKeyPoBean.setPublisherId(Integer.parseInt(data.getPublisherId()));
        mRelationParamBean.setMomentKeyPo(mMomentKeyPoBean);
        mPostUser.setRelationParam(mRelationParamBean);
        Log.w("mPostUser","mPostUser:"+mPostUser.toString());
        RetrofitUtil.getInstance().apiService()
                .postUserReportType(mPostUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            ToastUtils.showShort("举报成功");
                        }else{
                            ToastUtils.showShort("举报失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("举报失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void xihuan(QueryPopularBean data, BaseViewHolder helper){
        if(data.isIsLike()){
            HttpRequestUtils.postLikeCancel(data.getCircleId(),data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
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
            HttpRequestUtils.postLike(data.getCircleId(),data.getMomentId()+"", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
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

    private void getzhu(QueryPopularBean data, BaseViewHolder helper){
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
                        helper.setText(R.id.tv_gz,"+关注");
                        helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_theme_25);
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
                            helper.setText(R.id.tv_gz,"相互关注");
                            helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);;
                        }else{
                            data.getUser().setRelation(1);
                            helper.setText(R.id.tv_gz,"已关注");
                            helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);
                        }
                    }
                }
            });
        }
    }

    private void shoucang(QueryPopularBean data, BaseViewHolder helper){
        if(data.isIsFavorite()){
            HttpRequestUtils.postFavoritCancel(data.getCircleId(), data.getMomentId(), new HttpRequestUtils.LoginInterface() {
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
            HttpRequestUtils.postFavorit(data.getCircleId(), data.getMomentId(),
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
