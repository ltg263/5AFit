package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.entity.SpecListBaen;

import butterknife.BindView;

public class VipItemFragment extends BaseFragment {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.iv_vip)
    ImageView mIvVip;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_sign)
    TextView mTvSign;
    @BindView(R.id.tv_no)
    TextView mTvNo;
    @BindView(R.id.tv_xf)
    TextView mTvXf;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    private Bundle bundle;
    SpecListBaen.ListBean mListBean;

    @Override
    protected int getContentView() {
        return R.layout.fragment_vip_item;
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        if (bundle != null) {
            mListBean = bundle.getParcelable("ListBean");
        }
        getSpecList();
    }//

    private void getSpecList() {
        if (mListBean.getStatus()==1) {
            mRl.setBackground(getActivity().getResources().getDrawable(R.drawable.vip_shape_bj_15_1));
            mIvVip.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_vip_1));
            mTvXf.setText("立即续费");
            mTvSign.setVisibility(View.VISIBLE);
        } else {
            mRl.setBackground(getActivity().getResources().getDrawable(R.drawable.vip_shape_bj_15_2));
            mIvVip.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.icon_vip_2));
            mTvXf.setText("开通会员");
            mTvSign.setVisibility(View.INVISIBLE);
        }
        mTvName.setText(mListBean.getName());
        mTvNo.setText(mListBean.getLevelNo());
        mTvTime.setText(mListBean.getDuration());
        GlideImageUtils.setGlideImage(getActivity(),mListBean.getImgUrl(),mIvIcon);
    }
    @Override
    public void initImmersionBar() {
        getSpecList();
    }
}
