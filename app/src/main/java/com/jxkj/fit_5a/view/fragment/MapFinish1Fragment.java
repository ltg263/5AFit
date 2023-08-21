package com.jxkj.fit_5a.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.entity.BpmDataBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.MapFinishDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MapFinish1Fragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_xb)
    ImageView iv_xb;

    private MapFinishDataAdapter mMapFinishDataAdapter;

    private ArrayList<BpmDataBean> mBpmDataBeans;
    BpmDataBean.BpmTopData mBpmTopData;
    @Override
    protected int getContentView() {
        return R.layout.fragment_map_finish_1;
    }

    @Override
    protected void initViews() {
        Integer gender = Integer.valueOf(SharedUtils.singleton().get(ConstValues.USER_GENDER, "0"));
        if(gender==1){
            iv_xb.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ren_nan));
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            mBpmDataBeans = bundle.getParcelableArrayList("mBpmDataBeans");
            if(mBpmDataBeans!=null){
                mBpmTopData = mBpmDataBeans.get(0).getBpmTopData();
            }
        }
        initRv();

    }

    private void initRv() {

        mMapFinishDataAdapter = new MapFinishDataAdapter(null);
        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mMapFinishDataAdapter);

        mMapFinishDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        List<String> list = new ArrayList<>();
        list.add("卡路里");
        list.add("运动里程");
        list.add("运动时间");
//        list.add("功率");
//        list.add("段位");
        if(ConstValues_Ly.METER_ID == ConstValues_Ly.METER_ID_S[3]){
            list.add("平均浆频");
            list.add("最快浆频");
        }else {
            list.add("平均速度");
            list.add("最快速度");
        }
//        list.add("平均心跳");
//        list.add("心率区间");
        list.add("BAI");

        mMapFinishDataAdapter.setNewData(list, mBpmTopData);
    }

    @Override
    public void initImmersionBar() {

    }
}
