package com.jxkj.fit_5a.view.adapter;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.SoftKeyboardInputDialog;
import com.jxkj.fit_5a.entity.CommentMomentBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineCommentAdapter extends BaseQuickAdapter<CommentMomentBean, BaseViewHolder> {
    int contentType = 2;

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public MineCommentAdapter(@Nullable List<CommentMomentBean> data) {
        super(R.layout.item_mine_comment, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommentMomentBean item) {
        helper.addOnClickListener(R.id.iv_liuyan2);
        GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_1));
        helper.setText(R.id.tv_name_1,item.getUser().getNickName())
                .setText(R.id.tv_pl_content_1,item.getContent())
                .setGone(R.id.tv_num,false)
                .setText(R.id.tv_time_1, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd HH:mm:ss"))
                .setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.icon_xin_99_d))
                .setText(R.id.tv_xh_s,item.getLikeCount()+"");
        if(item.isIsLike()){
            helper.setImageDrawable(R.id.iv_xh,mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
        }

        RecyclerView RvList = helper.getView(R.id.rv_list);
        MineComment2Adapter mMineComment2Adapter = new MineComment2Adapter(null);
        RvList.setLayoutManager(new LinearLayoutManager(mContext));
        RvList.setHasFixedSize(true);
        RvList.setAdapter(mMineComment2Adapter);

        mMineComment2Adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_liuyan2:
                        CommentMomentBean data = mMineComment2Adapter.getData().get(position);
                        showSoftInputFromWindow(mMineComment2Adapter,view,data.getCommentId(),item, "回复@" + data.getUser().getNickName() + ":");
                        break;
                }
            }
        });

        helper.getView(R.id.ll_xh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xihuanliuyan(helper,item);
            }
        });

        if(item.getCommentCount()>0){
            if(item.getCommentCount()>1){
                helper.setGone(R.id.tv_num,true).setText(R.id.tv_num,"展开 "+item.getCommentCount()+" 条回复");
            }
            HttpRequestUtils.getCommentQueryReply(item.getCircleId(),item.getCommentId()+"", item.getMomentId()+"", item.getMomentPublisherId()+"", 1, 1, new HttpRequestUtils.ResultInterface() {
                @Override
                public void succeed(ResultList<CommentMomentBean> result) {
                    if(result!=null && result.getCode()==0 && result.getData().size()>0){
                        mMineComment2Adapter.setNewData(result.getData());
                    }
                }
            });
        }

        helper.getView(R.id.tv_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequestUtils.getCommentQueryReply(item.getCircleId(),item.getCommentId(), item.getMomentId(), item.getMomentPublisherId(), 1, 100, new HttpRequestUtils.ResultInterface() {
                    @Override
                    public void succeed(ResultList<CommentMomentBean> result) {
                        if(result!=null && result.getCode()==0){
                            mMineComment2Adapter.setNewData(result.getData());
                            helper.setGone(R.id.tv_num,false);
                        }
                    }
                });
            }
        });

    }

    private void xihuanliuyan(BaseViewHolder helper, CommentMomentBean commentMomentBean) {
        if(commentMomentBean.isIsLike()){
            HttpRequestUtils.postCommentLikeCancel(commentMomentBean.getCommentId(), commentMomentBean.getMomentId(), new HttpRequestUtils.LoginInterface() {
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
            HttpRequestUtils.postCommentLike(commentMomentBean.getCommentId(), commentMomentBean.getMomentId(), new HttpRequestUtils.LoginInterface() {
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
    SoftKeyboardInputDialog dialog_input;
    /**
     * EditText获取焦点并显示软键盘
     */
    public void showSoftInputFromWindow(MineComment2Adapter mMineComment2Adapter, View view, String commentId, CommentMomentBean item, String context2) {
        //使用自定义的dialog
        dialog_input = new SoftKeyboardInputDialog(mContext, context2, R.style.DialogTheme,
                new SoftKeyboardInputDialog.DialogInterfaceKey() {
                    @Override
                    public void strContext(String context) {
                        liuyan(mMineComment2Adapter,context,commentId,item);
                    }
                });
        dialog_input.show();

        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                if (bottom > oldBottom) {
                    dialog_input.dismiss();
                    view.removeOnLayoutChangeListener(this);//移除监听，避免不需要时还在监听
                }
            }
        });
    }
    private void liuyan(MineComment2Adapter mMineComment2Adapter, String context, String commentId, CommentMomentBean item) {
        HttpRequestUtils.postCommentMoment(item.getCircleId(), context, item.getMomentId(), item.getMomentPublisherId(),
                commentId,contentType, new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        if (!path.equals("0")) {
                            ToastUtils.showShortToast(mContext,"留言失败");
                            return;
                        }
                        ToastUtils.showShortToast(mContext,"留言成功");
                        dialog_input.dismiss();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                ((Activity)mContext).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        HttpRequestUtils.getCommentQueryReply(item.getCircleId(),item.getCommentId(), item.getMomentId(),
                                                item.getMomentPublisherId(), 1, 100, new HttpRequestUtils.ResultInterface() {
                                                    @Override
                                                    public void succeed(ResultList<CommentMomentBean> result) {
                                                        if(result!=null && result.getCode()==0){
                                                            mMineComment2Adapter.setNewData(result.getData());
//                                                               helper.setGone(R.id.tv_num,false);
                                                        }
                                                    }
                                                });
                                    }
                                });
                            }
                        }).start();

                    }
                });
    }
}