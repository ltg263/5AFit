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
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BlackListBean;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.FollowFansList;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class UserHmdAdapter extends BaseQuickAdapter<BlackListBean, BaseViewHolder> {
    public UserHmdAdapter(@Nullable List<BlackListBean> data) {
        super(R.layout.item_user_gz, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BlackListBean item) {
        BlackListBean.UserBean user = item.getUser();
        Log.w("--->>>>>>>>>>>>>","getContent:"+user.getNickName());
        if(user==null){
            return;
        }

        helper.setText(R.id.tv_gz,"移除");
        helper.setBackgroundRes(R.id.tv_gz,R.drawable.btn_shape_bj_fd1de_25);
        //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
        GlideImageUtils.setGlideImage(mContext,user.getAvatar(),helper.getView(R.id.iv_head_img));
        helper.setText(R.id.tv_user_name,user.getNickName()).setText(R.id.tv_time, StringUtil.getTimeToYMD(item.getTimestamp(),"yyyy-MM-dd"));
        if(user.getGender()==1){
            Glide.with(mContext).load(R.drawable.icon_xb_nan).into((ImageView) helper.getView(R.id.iv_xb));
        }else{
            Glide.with(mContext).load(R.drawable.icon_xb_nv).into((ImageView) helper.getView(R.id.iv_xb));
        }
        helper.addOnClickListener(R.id.tv_gz);
    }


}
