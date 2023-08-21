package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.view.adapter.UserLwAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserLwActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected int getContentView() {
        return R.layout.activity_user_lw;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我收到的礼物");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        List<String> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
//            list.add("");
        }
        UserLwAdapter mUserLwAdapter = new UserLwAdapter(list);
        mRvList.setLayoutManager(new GridLayoutManager(this, 4));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mUserLwAdapter);

        mUserLwAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserHomeActivity.startActivity(UserLwActivity.this,"80");
            }
        });
    }


    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }
}
