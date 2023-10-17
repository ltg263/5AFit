package com.jxkj.fit_5a.view.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.conpoment.utils.SharedAssociationUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeCgxAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserCgxActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    String localMinId = "0";
    String userId = "";
    private HomeThreeCgxAdapter mHomeThreeCgxAdapter;
    List<QueryPopularBean> data;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("草稿箱");
        userId = getIntent().getStringExtra("userId");
        mIvBack.setImageDrawable(getDrawable(R.drawable.icon_back_h));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
//        mTvRighttext.setText("收藏文章");
//        mTvRighttext.setTextColor(getResources().getColor(R.color.color_333333));
//        mTvRighttext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UserScActivity_gf.startActivity(UserCgxActivity.this,userId);
//            }
//        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvList.setLayoutManager(manager);
        mHomeThreeCgxAdapter = new HomeThreeCgxAdapter(null);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mHomeThreeCgxAdapter);

        mHomeThreeCgxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QueryPopularBean mData = mHomeThreeCgxAdapter.getData().get(position);
                AssociationAddActivity.startActivityAddAssociation(UserCgxActivity.this,Integer.parseInt(mData.getContentType()),mData.getCircleId()
                        ,mData.getCircleName(),mData.getCircleIcon(),mData.getTopicArr(),mData,position);
            }
        });
        query_related();
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        query_related();
    }

    private void query_related() {
        List<QueryPopularBean>  mSharedHistoryEquipment = SharedAssociationUtils.singleton().getSharedHistoryEquipment();
        if(mSharedHistoryEquipment!=null){
            mLvNot.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            mHomeThreeCgxAdapter.setNewData(mSharedHistoryEquipment);
        }
    }



    public static void startActivity(Context mContext, String userId) {
        Intent intent = new Intent(mContext, UserCgxActivity.class);
        if(StringUtil.isNotBlank(userId)){
            intent.putExtra("userId", userId);
        }
        mContext.startActivity(intent);
    }
}
