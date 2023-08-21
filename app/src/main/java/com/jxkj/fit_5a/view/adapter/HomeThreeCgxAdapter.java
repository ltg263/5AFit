package com.jxkj.fit_5a.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedAssociationUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class HomeThreeCgxAdapter extends BaseQuickAdapter<QueryPopularBean, BaseViewHolder> {
    public HomeThreeCgxAdapter(@Nullable List<QueryPopularBean> data) {
        super(R.layout.item_home_three_cgx, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QueryPopularBean item) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        if(StringUtil.isNotBlank(item.getMedia()) && item.getMedia().contains("imageUrl")){
            try {
                JSONArray array = new JSONArray(item.getMedia());
                GlideImageUtils.setGlideImage(mContext,array.getJSONObject(0).getString("imageUrl"),helper.getView(R.id.iv_icon));
                GlideImageUtils.setGlideImage(mContext,array.getJSONObject(0).getString("imageUrl"),helper.getView(R.id.iv_icon_sp));
                String vedioId = array.getJSONObject(0).getString("vedioId");
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        helper.setText(R.id.tv_title,item.getContent())
                .setText(R.id.tv_time,StringUtil.getTimeToYMD(item.getTimestamp(), "yyyy-MM-dd"))
                .setGone(R.id.iv_icon_sp,false).setGone(R.id.iv_icon,true)
                .setImageResource(R.id.iv_select_xh,R.drawable.icon_xin_99_d)
                .setGone(R.id.iv_baofang,false).setText(R.id.tv_num,item.getLikeCount()+"");
        if(item.getUser()!=null){
            helper.setText(R.id.tv_name,item.getUser().getNickName())
                    .addOnClickListener(R.id.iv_head_img).addOnClickListener(R.id.tv_name).addOnClickListener(R.id.ll_xh);
            GlideImageUtils.setGlideImage(mContext,item.getUser().getAvatar(),helper.getView(R.id.iv_head_img));
        }
        if(item.isIsLike()){
            helper.setImageResource(R.id.iv_select_xh,R.drawable.ic_celect_xh_yes);
        }
        if(item.getContentType().equals("3")){
            helper.setGone(R.id.iv_baofang,true)
                    .setGone(R.id.iv_icon_sp,true).setGone(R.id.iv_icon,false);
        }
        helper.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedAssociationUtils.singleton().updateSharedHistoryEquipmentUpdate(helper.getLayoutPosition());
                remove(helper.getLayoutPosition());
                notifyDataSetChanged();
            }
        });
    }

}
