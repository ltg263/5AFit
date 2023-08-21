package com.jxkj.fit_5a.conpoment.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SDUIUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.view.adapter.MineCommentAdapter;

import java.util.List;


/**
 * author : LiuJie
 * date   : 2020/6/514:57
 */
public class DialogCommentPackage {
    private View contentView;
    private Dialog dialog;
    private EditText mEtContext;
    private TextView mTvFs,mTvZs,mTvRd,mTvSj;
    private Activity mContext;
    private LayoutInflater mInflater;
    private RecyclerView mRvList;
    private MineCommentAdapter mMineCommentAdapter;
    private String commentId = null;
    private String circleId;
    private String commentOrderType = "lately";

    public DialogCommentPackage(Activity mContext,String circleId) {
        this.circleId = circleId;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.contentView = this.mInflater.inflate(R.layout.dialog_comment_package_layout, null);
        initDialog();
        findViewById();
    }

    private void findViewById(){
        mRvList = contentView.findViewById(R.id.rv_list);
        mEtContext = contentView.findViewById(R.id.et_content);
        mTvFs = contentView.findViewById(R.id.tv_fabu);
        mTvZs = contentView.findViewById(R.id.tv_zs);
        mTvRd = contentView.findViewById(R.id.tv_rd);
        mTvSj = contentView.findViewById(R.id.tv_sj);
        mTvFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvSj.setTextColor(mContext.getResources().getColor(R.color.color_333333));
                mTvRd.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                String con = mEtContext.getText().toString();
                if(StringUtil.isBlank(con)){
                    ToastUtils.showShortToast(mContext,"留言内容不能为空");
                    return;
                }
                onCommentPackageDialogListener.addListener("lately",con,commentId);
            }
        });
        mTvRd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvRd.setTextColor(mContext.getResources().getColor(R.color.color_333333));
                mTvSj.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                commentId = null;
                commentOrderType = "hot";
                onCommentPackageDialogListener.sortListener(commentOrderType,0);
            }
        });
        mTvSj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentId = null;
                commentOrderType = "lately";
                mTvSj.setTextColor(mContext.getResources().getColor(R.color.color_333333));
                mTvRd.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                onCommentPackageDialogListener.sortListener(commentOrderType,0);
            }
        });
        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mMineCommentAdapter = new MineCommentAdapter(null);
        mMineCommentAdapter.setContentType(3);
        mRvList.setAdapter(mMineCommentAdapter);
        mMineCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mMineCommentAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            CommentMomentBean data = mMineCommentAdapter.getData().get(position);
            switch (view.getId()){
                case R.id.ll_xh:
                    if(data.isIsLike()){
                        HttpRequestUtils.postCommentLikeCancel(data.getCommentId()+"", data.getMomentId()+"", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                if(path.equals("0")){
                                    data.setIsLike(false);
                                    data.setLikeCount(data.getLikeCount()-1);
                                    ((TextView)view.findViewById(R.id.tv_xh_s)).setText((data.getLikeCount() - 1)+"");
                                    ((ImageView)view.findViewById(R.id.iv_xh)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
                                }
                            }
                        });
                    }else{
                        HttpRequestUtils.postCommentLike(data.getCommentId()+"", data.getMomentId()+"", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                if(path.equals("0")) {
                                    data.setIsLike(true);
                                    data.setLikeCount(data.getLikeCount() + 1);
                                    ((TextView)view.findViewById(R.id.tv_xh_s)).setText((data.getLikeCount() + 1)+"");
                                    ((ImageView)view.findViewById(R.id.iv_xh)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
                                }
                            }
                        });
                    }
                    break;
                case R.id.iv_liuyan2:
                    commentId = data.getCommentId()+"";
                    mEtContext.setHint("回复@"+data.getUser().getNickName()+":");
                    break;
            }
        });
    }
    public void setNewData(List<CommentMomentBean> data,String num){
        mMineCommentAdapter.setNewData(data);
        mTvZs.setText("评论("+num+")");
        mEtContext.setText("");
    }



    private void initDialog() {
        dialog = new Dialog(mContext, R.style.MyDialog);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.menushow);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lp.width = SDUIUtil.getScreenWidth(mContext); // 宽度设置为屏幕的
//      lp.height = SDUIUtil.getScreenHeight(mContext);
        dialogWindow.setAttributes(lp);
    }

    public void showDialog() {
        if (dialog != null)
            dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }


    private OnCommentPackageDialogListener onCommentPackageDialogListener;

    public OnCommentPackageDialogListener getOnCommentPackageDialogListener() {
        return onCommentPackageDialogListener;
    }

    public void setOnCommentPackageDialogListener(OnCommentPackageDialogListener onCommentPackageDialogListener) {
        this.onCommentPackageDialogListener = onCommentPackageDialogListener;
    }

    public interface OnCommentPackageDialogListener {
        void addListener(String commentOrderType,String context, String commentId);

        void sortListener(String commentOrderType, int num);
    }
}

