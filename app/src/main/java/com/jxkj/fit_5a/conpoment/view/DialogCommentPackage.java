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
    private TextView mTvFs,mTvZs;
    private Activity mContext;
    private LayoutInflater mInflater;
    private RecyclerView mRvList;
    private MineCommentAdapter mMineCommentAdapter;
    private String commentId = null;
    private String circleId;


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
        mTvFs = contentView.findViewById(R.id.tv_fs);
        mTvZs = contentView.findViewById(R.id.tv_zs);
        mTvFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommentPackageDialogListener.addListener(mEtContext.getText().toString(),commentId);
            }
        });
        mRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mMineCommentAdapter = new MineCommentAdapter(null);
        mMineCommentAdapter.setCircleId(circleId);
        mRvList.setAdapter(mMineCommentAdapter);
        mMineCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommentMomentBean data = mMineCommentAdapter.getData().get(position);
                commentId = data.getCommentId()+"";
                mEtContext.setHint("??????@"+data.getUser().getNickName()+":");
            }
        });
        mMineCommentAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            CommentMomentBean data = mMineCommentAdapter.getData().get(position);
            switch (view.getId()){
                case R.id.ll_xh:
                    if(data.isIsLike()){
                        HttpRequestUtils.postCommentLikeCancel(data.getCommentId()+"", data.getMomentId() + "", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                if(path.equals("0")){
                                    ((TextView)view.findViewById(R.id.tv_xh_s)).setText((data.getLikeCount() - 1)+"");
                                    ((ImageView)view.findViewById(R.id.iv_xh)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_xin_99_d));
                                    data.setIsLike(false);
                                    data.setLikeCount(data.getLikeCount()-1);
                                }
                            }
                        });
                    }else{
                        HttpRequestUtils.postCommentLike(data.getCommentId()+"", data.getMomentId() + "", new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                if(path.equals("0")) {
                                    ((TextView)view.findViewById(R.id.tv_xh_s)).setText((data.getLikeCount() + 1)+"");
                                    ((ImageView)view.findViewById(R.id.iv_xh)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_celect_xh_yes));
                                    data.setIsLike(true);
                                    data.setLikeCount(data.getLikeCount() + 1);
                                }
                            }
                        });
                    }
                    break;
                case R.id.tv_pl_content_1:

                    break;
            }
        });
    }
    public void setNewData(List<CommentMomentBean> data,String num){
        mMineCommentAdapter.setNewData(data);
        mTvZs.setText("???"+num+"?????????");
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
        lp.width = SDUIUtil.getScreenWidth(mContext); // ????????????????????????
//      lp.height = SDUIUtil.getScreenHeight(mContext);
        dialogWindow.setAttributes(lp);
    }

    public void showDialog() {
        if (dialog != null)
            dialog.show();
    }


    private OnCommentPackageDialogListener onCommentPackageDialogListener;

    public OnCommentPackageDialogListener getOnCommentPackageDialogListener() {
        return onCommentPackageDialogListener;
    }

    public void setOnCommentPackageDialogListener(OnCommentPackageDialogListener onCommentPackageDialogListener) {
        this.onCommentPackageDialogListener = onCommentPackageDialogListener;
    }

    public interface OnCommentPackageDialogListener {
        void addListener(String context, String commentId);

        void buyListener(String skuId, int num);
    }
}

