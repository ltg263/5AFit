package com.jxkj.fit_5a.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.CommentMomentBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineComment2Adapter_gf extends BaseQuickAdapter<CommentMomentBean, BaseViewHolder> {
    public MineComment2Adapter_gf(@Nullable List<CommentMomentBean> data) {
        super(R.layout.item_mine_comment2, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentMomentBean item) {
        String str =item.getUser().getNickName();
        if(item.getReplyComment()!=null && item.getReplyComment().getUser()!=null && StringUtil.isNotBlank(item.getReplyComment().getUser().getNickName())){
            str = item.getUser().getNickName()+" 回复："+item.getReplyComment().getUser().getNickName();
        }
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_11));
        helper.setText(R.id.tv_name_11,str).setText(R.id.tv_pl_content_11,item.getContent())
                .setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d))
                .setText(R.id.tv_xh_s,item.getLikeCount()+"").addOnClickListener(R.id.iv_liuyan2);

        if(item.isIsLike()){
            helper.setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }

        helper.getView(R.id.ll_xh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xihuanliuyan(helper,item);
            }
        });
    }
    private void xihuanliuyan(BaseViewHolder helper, CommentMomentBean commentMomentBean) {
        if(commentMomentBean.isIsLike()){
            HttpRequestUtils.postCommentLikeCancel_gf(commentMomentBean.getCommentId()+"", commentMomentBean.getMomentId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        commentMomentBean.setIsLike(false);
                        commentMomentBean.setLikeCount(commentMomentBean.getLikeCount()-1);
                        helper.setText(R.id.tv_xh_s,commentMomentBean.getLikeCount()+"")
                                .setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
                    }
                }
            });
        }else{
            HttpRequestUtils.postCommentLike_gf(commentMomentBean.getCommentId()+"", commentMomentBean.getMomentId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")) {
                        commentMomentBean.setIsLike(true);
                        commentMomentBean.setLikeCount(commentMomentBean.getLikeCount() + 1);
                        helper.setText(R.id.tv_xh_s,commentMomentBean.getLikeCount()+"")
                                .setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
                    }
                }
            });
        }
    }
}
