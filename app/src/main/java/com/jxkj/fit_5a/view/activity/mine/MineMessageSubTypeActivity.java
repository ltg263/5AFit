package com.jxkj.fit_5a.view.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.entity.LastUnreadMessageBeanList;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.adapter.MineMessageSubTypeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineMessageSubTypeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String subType;
    private MineMessageSubTypeAdapter mMineMessageSubTypeAdapter;

    private int page = 1;

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRv();
    }

    @Override
    protected void initViews() {
        //子消息类型(2,动态评论;3,动态或评论点赞;4,新粉丝;6,圈子解散)
        subType = getIntent().getStringExtra("subType");
        mTvTitle.setText("消 息");
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initRv();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initRv();
            }
        });
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mMineMessageSubTypeAdapter = new MineMessageSubTypeAdapter(null);
        mRvList.setAdapter(mMineMessageSubTypeAdapter);
        mMineMessageSubTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MessageSubtypeBean.LastUnreadMessageBean data = mMineMessageSubTypeAdapter.getData().get(position);
                //子消息类型(2,动态评论;3,动态或评论点赞;4,新粉丝;6,圈子解散)
                getMessagSetRead(data.getId());
                switch (data.getSubType()){
                    case "2":
                        MessageSubtypeBean.LastUnreadMessageBean.ContentCommentBean mData_2 = data.getContentComment();
                        if(mData_2.getReplyCommentType().equals("1")){//(1,回复动态的评论;2,回复评论的评论)
                            AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                    mData_2.getReplyMomentParam().getCircleId(),
                                    mData_2.getReplyMomentParam().getMomentPublisherId(),
                                    mData_2.getReplyMomentParam().getMomentId());
                        }else if(mData_2.getReplyCommentType().equals("2")){
                            AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                    mData_2.getReplyCommentParam().getCircleId(),
                                    mData_2.getReplyCommentParam().getMomentPublisherId(),
                                    mData_2.getReplyCommentParam().getMomentId());
                        }
                        break;
                    case "3":
                        MessageSubtypeBean.LastUnreadMessageBean.ContentLikeBean  mData = data.getContentLike();
                        if(mData.getLikeType().equals("1")){//(1,点赞动态;2,点赞评论)
                            AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                    mData.getMomentLikeParam().getCircleId(),
                                    mData.getMomentLikeParam().getMomentPublisherId(),
                                    mData.getMomentLikeParam().getMomentId());
                        }else if(mData.getLikeType().equals("2")){
                            AssociationActivity.startActivity(MineMessageSubTypeActivity.this,
                                    mData.getCommentLikeParam().getCircleId(),
                                    mData.getCommentLikeParam().getMomentPublisherId(),
                                    mData.getCommentLikeParam().getMomentId());
                        }

                        break;
                    case "4":
                        UserHomeActivity.startActivity(MineMessageSubTypeActivity.this,
                                data.getContentNewFans().getNewFansParam().getUserId());
                        break;
                    case "6":
                        data.getContentCircleDismiss();
                        break;
                }
            }
        });
    }

    private int totalPage;
    private void initRv() {
        RetrofitUtil.getInstance().apiService()
                .getMessageList(subType,page, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LastUnreadMessageBeanList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LastUnreadMessageBeanList> result) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                        if (isDataInfoSucceed(result)) {
                            if(mMineMessageSubTypeAdapter!=null){
                                if(result.getData().getList().size()>0){
                                    mLvNot.setVisibility(View.GONE);
                                    refreshLayout.setVisibility(View.VISIBLE);
                                }
                                if(page==1){
                                    mMineMessageSubTypeAdapter.setNewData(result.getData().getList());
                                }else{
                                    mMineMessageSubTypeAdapter.addData(result.getData().getList());
                                }
                                totalPage = StringUtil.getTotalPage(result.getData().getTotalCount(), ConstValues.PAGE_SIZE);
                                if(totalPage <= page){
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }
    private void getMessagSetRead(String id) {
        RetrofitUtil.getInstance().apiService()
                .getMessagSetRead(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
