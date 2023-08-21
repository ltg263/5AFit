package com.jxkj.fit_5a.view.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImageLoader;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.MyNestedScrollView;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class AssociationListAdapter extends BaseQuickAdapter<MomentDetailsBean, BaseViewHolder> {
    String circleId = "";

    public AssociationListAdapter(@Nullable List<MomentDetailsBean> data) {
        super(R.layout.item_association_list, data);
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MomentDetailsBean item) {
        MyNestedScrollView mMyNestedScrollView = helper.getView(R.id.mMyNestedScrollView);
        mMyNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mMyNestedScrollView.getParent().requestDisallowInterceptTouchEvent(true);
                boolean isTop = false;
                if (scrollY > oldScrollY) {
                    isTop = false;
                    Log.w("=====", "下滑");
                }

                if (scrollY < oldScrollY) {
                    isTop = true;
                    Log.w("=====", "上滑");
                }

                if (scrollY == 0 && isTop) {
                    mMyNestedScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                    Log.w("=====", "滑倒顶部");
                }

                if(mMyNestedScrollView.getChildAt(0).getHeight() <= mMyNestedScrollView.getScrollY() + mMyNestedScrollView.getHeight() && !isTop){
                    mMyNestedScrollView.getParent().requestDisallowInterceptTouchEvent(false);
                    Log.w("=====", "滑倒底部");
                }

            }
        });
        helper.addOnClickListener(R.id.iv_head_img).addOnClickListener(R.id.rl_all_comment)
                .addOnClickListener(R.id.ll_xihuan).addOnClickListener(R.id.ll_shoucang)
                .addOnClickListener(R.id.tv_fenxiang).addOnClickListener(R.id.tv_ygz)
                .addOnClickListener(R.id.tv_wgz).addOnClickListener(R.id.ll_liuyan)
                .addOnClickListener(R.id.tv_topic);
        helper.setText(R.id.tv_name, item.getUser().getNickName())
                .setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(), "yyyy-MM-dd HH:mm:ss"))
                .setGone(R.id.tv_topic, false)
                .setGone(R.id.ll_pl, false)
                .setText(R.id.banner_home_one, item.getContent() + "")
                .setText(R.id.tv_shoucang, item.getFavoriteCount() + "")
                .setText(R.id.tv_xihuan, item.getLikeCount() + "")
                .setText(R.id.tv_liuyan, item.getCommentCount() + "")
                .setText(R.id.tv_all, "全部评论(" + item.getCommentCount() + ")")
                .setText(R.id.tv_browse_num,"浏览 "+item.getPageviews()+" 次")
                .setText(R.id.tv_fenxiang, "0")
                .setGone(R.id.tv_address, false)
                .setImageDrawable(R.id.iv_xihuan, mContext.getResources().getDrawable(R.drawable.icon_xin_99_d))
                .setImageDrawable(R.id.iv_shoucang, mContext.getResources().getDrawable(R.drawable.icon_share_sc_d));
        GlideImageUtils.setGlideImage(mContext, item.getUser().getAvatar(), helper.getView(R.id.iv_head_img));
        if (StringUtil.isNotBlank(item.getPosition())) {
            helper.setText(R.id.tv_address, item.getPosition()).setGone(R.id.tv_address, true);
        }
        if (item.isIsLike()) {
            helper.setImageDrawable(R.id.iv_xihuan, mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }

        if (item.isIsFavorite()) {
            helper.setImageDrawable(R.id.iv_shoucang, mContext.getResources().getDrawable(R.drawable.icon_share_sc_dx));
        }
        //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
        if (item.getUser().getRelation() == 1 || item.getUser().getRelation() == 3) {
            helper.setVisible(R.id.tv_ygz, true).setVisible(R.id.tv_wgz, false);
        } else if (item.getUser().getRelation() == 4) {
            helper.setVisible(R.id.tv_ygz, false).setVisible(R.id.tv_wgz, false);
        } else {
            helper.setVisible(R.id.tv_ygz, false).setVisible(R.id.tv_wgz, true);
        }
        if (StringUtil.isNotBlank(item.getTopicArr())) {
            try {
                JSONArray array = new JSONArray(item.getTopicArr());
                helper.setGone(R.id.tv_topic, true).setText(R.id.tv_topic, "来自话题：" + array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Banner mBanner = helper.getView(R.id.banner);
        mBanner.setVisibility(View.GONE);
        if (StringUtil.isNotBlank(item.getMedia()) && !item.getMedia().equals("[]")) {
            mBanner.setVisibility(View.VISIBLE);
            try {
                initBannerOne(mBanner, new JSONArray(item.getMedia()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (item.getCommentCount() > 0 && false) {
//            HttpRequestUtils.getCommentMoment1(circleId, item.getMomentId(), item.getPublisherId() + "", 1, 2,
//                    new HttpRequestUtils.ResultInterface() {
//                        @Override
//                        public void succeed(ResultList<CommentMomentBean> result) {
//                            if (result != null && result.getCode() == 0 && result.getData().size() > 0) {
//                                helper.setGone(R.id.ll_pl, true).setGone(R.id.rl_pl_2, false)
//                                        .setImageDrawable(R.id.iv_xh, mContext.getResources().getDrawable(R.drawable.icon_xin_99_d))
//                                        .setImageDrawable(R.id.iv_xh2, mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
//                                List<CommentMomentBean> data = result.getData();
//                                for (int i = 0; i < data.size(); i++) {
//                                    if (i == 0) {
//                                        GlideImageUtils.setGlideImage(mContext, data.get(0).getUser().getAvatar(), helper.getView(R.id.iv_head_1));
//                                        helper.setText(R.id.tv_name_1, data.get(0).getUser().getNickName()).setText(R.id.tv_pl_content_1, data.get(0).getContent())
//                                                .setText(R.id.tv_xh_s, data.get(0).getLikeCount() + "").setText(R.id.tv_pl_num_1, "评论(" + data.get(0).getCommentCount() + ")");
//
//                                        helper.setVisible(R.id.tv_pl_num_1, true).setVisible(R.id.view1, true);
//                                        if (data.get(0).getCommentCount() == 0) {
//                                            helper.setVisible(R.id.tv_pl_num_1, false).setVisible(R.id.view1, false);
//                                        }
//                                        helper.setGone(R.id.rl_pl_11, false);
//                                        if (data.get(0).getCommentCount() > 0) {
//                                            HttpRequestUtils.getCommentQueryReply1(circleId, data.get(0).getCommentId() + "", data.get(0).getMomentId() + "",
//                                                    data.get(0).getMomentPublisherId() + "", 1, 1, new HttpRequestUtils.ResultInterface() {
//                                                        @Override
//                                                        public void succeed(ResultList<CommentMomentBean> result) {
//                                                            if (result != null && result.getCode() == 0 && result.getData().size() > 0) {
//                                                                helper.setGone(R.id.rl_pl_11, true);
//                                                                CommentMomentBean dataX = result.getData().get(0);
//                                                                GlideImageUtils.setGlideImage(mContext, dataX.getUser().getAvatar(), helper.getView(R.id.iv_head_11));
//                                                                helper.setText(R.id.tv_name_11, dataX.getUser().getNickName()).setText(R.id.tv_pl_content_11, dataX.getContent());
//                                                            }
//                                                        }
//                                                    });
//                                        }
//                                        if (item.isIsLike()) {
//                                            helper.setImageDrawable(R.id.iv_xh, mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
//                                        }
//                                    }
//                                    if (i == 1) {
//                                        GlideImageUtils.setGlideImage(mContext, data.get(1).getUser().getAvatar(), helper.getView(R.id.iv_head_2));
//                                        helper.setText(R.id.tv_name_2, data.get(1).getUser().getNickName()).setText(R.id.tv_pl_content_2, data.get(1).getContent())
//                                                .setText(R.id.tv_xh_s2, data.get(1).getLikeCount() + "")
//                                                .setText(R.id.tv_pl_num_2, "评论(" + data.get(1).getCommentCount() + ")").setGone(R.id.rl_pl_2, true);
//
//                                        helper.setVisible(R.id.tv_pl_num_2, true).setVisible(R.id.view2, true);
//                                        if (data.get(1).getCommentCount() == 0) {
//                                            helper.setVisible(R.id.tv_pl_num_2, false).setVisible(R.id.view2, false);
//                                        }
//                                        if (item.isIsLike()) {
//                                            helper.setImageDrawable(R.id.iv_xh2, mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
//                                        }
//
//                                        helper.setGone(R.id.rl_pl_21, false);
//                                        if (data.get(1).getCommentCount() > 0) {
//                                            HttpRequestUtils.getCommentQueryReply1(circleId, data.get(1).getCommentId() + "", data.get(1).getMomentId() + "",
//                                                    data.get(1).getMomentPublisherId() + "", 1, 1, new HttpRequestUtils.ResultInterface() {
//                                                        @Override
//                                                        public void succeed(ResultList<CommentMomentBean> result) {
//                                                            if (result != null && result.getCode() == 0 && result.getData().size() > 0) {
//                                                                helper.setGone(R.id.rl_pl_21, true);
//                                                                CommentMomentBean dataX = result.getData().get(0);
//                                                                GlideImageUtils.setGlideImage(mContext, dataX.getUser().getAvatar(), helper.getView(R.id.iv_head_11));
//                                                                helper.setText(R.id.tv_name_11, dataX.getUser().getNickName()).setText(R.id.tv_pl_content_11, dataX.getContent());
//                                                            }
//                                                        }
//                                                    });
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    });
        }
    }

    private void initBannerOne(Banner mBanner, JSONArray split) throws JSONException {
        ArrayList<String> list_path = new ArrayList<>();
        for (int i = 0; i < split.length(); i++) {
            list_path.add(split.getJSONObject(i).getString("imageUrl"));
        }
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.i("tag", "你点了第" + position + "张轮播图:" + list_path.get(position));
                MatisseUtils.oPenUrlImg((Activity) mContext, list_path, position);
            }
        });

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(list_path);
        //设置banner动画效果
//        mTopBanner.setBannerAnimation(Transformer.Stack);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

}
