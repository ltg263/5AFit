package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.MyNestedScrollView;
import com.jxkj.fit_5a.conpoment.view.PileAvertView;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.conpoment.view.SoftKeyboardInputDialog;
import com.jxkj.fit_5a.conpoment.view.SquareImageView;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.view.activity.mine.UserHomeActivity;
import com.jxkj.fit_5a.view.adapter.HomeThreeSqAdapter;
import com.jxkj.fit_5a.view.adapter.MineCommentAdapter;
import com.jxkj.fit_5a.view.adapter.SpPhotoAdapter;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AssociationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_list_xg)
    RecyclerView mRvListXg;
    @BindView(R.id.iv_head_img_top)
    RoundImageView mIvHeadImgTop;
    @BindView(R.id.tv_name_top)
    TextView mTvNameTop;
    @BindView(R.id.tv_time_top)
    TextView mTvTimeTop;
    @BindView(R.id.tv_gz_top)
    TextView mTvGzTop;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.tv_share)
    ImageView mTvShare;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_gz)
    TextView mTvGz;
    @BindView(R.id.iv_head_img)
    RoundImageView mIvHeadImg;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.banner_home_one)
    TextView mBannerHomeOne;
    @BindView(R.id.tv_topic)
    TextView mTvTopic;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.pile_view_1)
    PileAvertView mPileView1;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.ll_r)
    LinearLayout mLlR;
    @BindView(R.id.tv_browse_num)
    TextView mTvBrowseNum;
    @BindView(R.id.tv_rd)
    TextView mTvRd;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    @BindView(R.id.ali_pl)
    TextView mAliPl;
    @BindView(R.id.mMyNestedScrollView)
    MyNestedScrollView mMMyNestedScrollView;
    @BindView(R.id.ss)
    TextView mSs;
    @BindView(R.id.tv_fenxiang)
    TextView mTvFenxiang;
    @BindView(R.id.iv_shoucang)
    ImageView mIvShoucang;
    @BindView(R.id.tv_shoucang)
    TextView mTvShoucang;
    @BindView(R.id.ll_shoucang)
    LinearLayout mLlShoucang;
    @BindView(R.id.iv_xihuan)
    ImageView mIvXihuan;
    @BindView(R.id.tv_xihuan)
    TextView mTvXihuan;
    @BindView(R.id.ll_xihuan)
    LinearLayout mLlXihuan;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.iv_liuyan)
    ImageView mIvLiuyan;
    @BindView(R.id.tv_liuyan)
    TextView mTvLiuyan;
    @BindView(R.id.tv_not)
    TextView tv_not;
    @BindView(R.id.ll_liuyan)
    LinearLayout mLlLiuyan;
    @BindView(R.id.siv_1)
    ImageView mSiv1;
    @BindView(R.id.siv_11)
    SquareImageView mSiv11;
    @BindView(R.id.siv_22)
    SquareImageView mSiv22;
    @BindView(R.id.rv_img_2)
    LinearLayout mRvImg2;
    @BindView(R.id.rv_list_img)
    RecyclerView mRvListImg;
    int page = 1;
    MineCommentAdapter mMineCommentAdapter;
    String circleId, publisherId, momentId,ly;
    String commentOrderType = "lately";
    private HomeThreeSqAdapter mHomeThreeSqAdapter;
    private SpPhotoAdapter mSpPhotoAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_association;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("社群动态");
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMomentDetails();
            }
        });
        mTvTitle.setAlpha(1);
        mRlTitle.setAlpha(0);
        mMMyNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 150) {
                    float z = scrollY / 150f;
                    mTvTitle.setAlpha(1 - z);
                    mRlTitle.setAlpha(z);
                } else {
                    mTvTitle.setAlpha(0);
                    mRlTitle.setAlpha(1);
                }
            }
        });

        //生命为瀑布流的布局方式，3列，布局方向为垂直
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRvListXg.setLayoutManager(manager);
        mHomeThreeSqAdapter = new HomeThreeSqAdapter(null);
        mRvListXg.setHasFixedSize(true);
        mRvListXg.setAdapter(mHomeThreeSqAdapter);

        mHomeThreeSqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QueryPopularBean mData = mHomeThreeSqAdapter.getData().get(position);
                if(mData.getContentType().equals("3")){
                    VideoActivity.startActivity(AssociationActivity.this,mData.getCircleId(),mData.getPublisherId(),mData.getMomentId());
                }else{
                    AssociationActivity.startActivity(AssociationActivity.this,mData.getCircleId(),mData.getPublisherId(),mData.getMomentId());
                }
            }
        });

        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvListImg.setLayoutManager(new GridLayoutManager(this, 3));
        mRvListImg.setHasFixedSize(true);
        mRvListImg.setAdapter(mSpPhotoAdapter);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("tag", "你点了第" + position + "张轮播图:" + list_path.get(position));
                if(list_path!=null && list_path.size()>0){
                    MatisseUtils.oPenUrlImgL(AssociationActivity.this, list_path, position);
                }
            }
        });

        circleId = getIntent().getStringExtra("circleId");
        publisherId = getIntent().getStringExtra("publisherId");
        momentId = getIntent().getStringExtra("momentId");
        ly = getIntent().getStringExtra("ly");
        mTv.setVisibility(View.GONE);
        initLy();
        query_related();
    }

    private void initLy() {

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mMineCommentAdapter = new MineCommentAdapter(null);
        mMineCommentAdapter.setContentType(2);
        mRvList.setAdapter(mMineCommentAdapter);
        mMineCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        mMineCommentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_liuyan2:
                        CommentMomentBean data = mMineCommentAdapter.getData().get(position);
                        showSoftInputFromWindow(data.getCommentId() + "", "回复@" + data.getUser().getNickName() + ":");
                        break;
                }
            }
        });
        getMomentDetails();
        postBrows();
    }

    private void getCommentMoment() {
        HttpRequestUtils.getCommentMoment(commentOrderType, circleId, momentId, publisherId, page, ConstValues.PAGE_SIZE,
                new HttpRequestUtils.ResultInterface() {
                    @Override
                    public void succeed(ResultList<CommentMomentBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (page == 1) {
                                mMineCommentAdapter.setNewData(result.getData());
                            } else {
                                mMineCommentAdapter.addData(result.getData());
                            }
                            if(mMineCommentAdapter.getData().size()>0){
                                mAliPl.setVisibility(View.VISIBLE);
                                mRvList.setVisibility(View.VISIBLE);
                                mLl.setVisibility(View.VISIBLE);
                                tv_not.setVisibility(View.GONE);
                                if (result.getData().size() < 10) {
                                    mAliPl.setVisibility(View.GONE);
                                }
                            }else{
                                mAliPl.setVisibility(View.GONE);
                                mRvList.setVisibility(View.GONE);
                                mLl.setVisibility(View.GONE);
                                tv_not.setVisibility(View.VISIBLE);
                            }
//                            if(StringUtil.isNotBlank(ly)){
//                                ly = "";
//                                mMMyNestedScrollView.smoothScrollTo(0,ll1.getTop());
//                            }
                        }
                    }
                });
    }

    private void postBrows() {
        RetrofitUtil.getInstance().apiService()
                .postBrows(circleId, momentId, publisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }

    private void getMomentDetails() {
        RetrofitUtil.getInstance().apiService()
                .getMomentDetails(circleId, momentId, publisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MomentDetailsBean> result) {
                        if (isDataInfoSucceed(result)) {
                            initView(result.getData());
                            getCommentMoment();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();

                    }
                });
    }


    MomentDetailsBean data;

    private void initView(MomentDetailsBean data) {
        if(data.getUser()==null){
            return;
        }
        this.data = data;
        GlideImageUtils.setGlideImage(this, data.getUser().getAvatar(), mIvHeadImg);
        GlideImageUtils.setGlideImage(this, data.getUser().getAvatar(), mIvHeadImgTop);
        mTvName.setText(data.getUser().getNickName());
        mTvNameTop.setText(data.getUser().getNickName());
        if(StringUtil.isBlank(data.getPosition())){
            mIv.setVisibility(View.GONE);
            mTvAddress.setVisibility(View.GONE);
        }else{
            mTvAddress.setText(data.getPosition());
        }

        mTvGz.setVisibility(View.VISIBLE);
        mTvGzTop.setVisibility(View.VISIBLE);
        //(0:没有关系;1:已关注;2:粉丝;3:互为粉丝;4,本人)
        if (data.getUser().getRelation() == 0) {
            mTvGz.setText("+关注");
            mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
            mTvGzTop.setText("+关注");
            mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
        } else if (data.getUser().getRelation() == 1) {
            mTvGz.setText("已关注");
            mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
            mTvGzTop.setText("已关注");
            mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
        } else if (data.getUser().getRelation() == 2) {
            mTvGz.setText("+关注");
            mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
            mTvGzTop.setText("+关注");
            mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
        } else if (data.getUser().getRelation() == 3) {
            mTvGz.setText("相互关注");
            mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
            mTvGzTop.setText("相互关注");
            mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
        } else if (data.getUser().getRelation() == 4) {
            mTvGz.setVisibility(View.INVISIBLE);
            mTvGzTop.setVisibility(View.INVISIBLE);
        }
        mBannerHomeOne.setText(data.getContent());

        mTvTopic.setVisibility(View.GONE);
        if (StringUtil.isNotBlank(data.getTopicArr())) {
            try {
                JSONArray array = new JSONArray(data.getTopicArr());
                mTvTopic.setVisibility(View.VISIBLE);
                mTvTopic.setText("来自话题：" + array.getString(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mTvTime.setText(StringUtil.getTimeToYMD(data.getTimestamp(), "yyyy-MM-dd HH:mm:ss"));
        mTvTimeTop.setText(StringUtil.getTimeToYMD(data.getTimestamp(), "yyyy-MM-dd HH:mm:ss"));

        if (StringUtil.isNotBlank(data.getMedia()) && !data.getMedia().equals("[]")) {
            try {
                initBannerOne(new JSONArray(data.getMedia()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mLlR.setVisibility(View.GONE);
        if (data.getLikeCount() != 0) {
            mLlR.setVisibility(View.VISIBLE);
            tv_price.setText(data.getLikeCount() + "人赞过");
            List<MomentDetailsBean.MomentLikesBean> mList = data.getMomentLikes();
            List<String> urls = new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
                urls.add(mList.get(i).getUser().getAvatar());
            }
            mPileView1.setAvertImages(urls);
        }
        mTvXihuan.setText(data.getLikeCount() + "");
        mTvShoucang.setText(data.getFavoriteCount() + "");
        mTvLiuyan.setText(data.getCommentCount() + "");
        mTvBrowseNum.setText("评论(" + data.getCommentCount() + ")");
        mAliPl.setVisibility(View.VISIBLE);
        mAliPl.setText("展开更多条评论");
//        mAliPl.setText("展开 " + data.getCommentCount() + " 条评论");

        if (mMineCommentAdapter.getData().size() < 10) {
            mAliPl.setVisibility(View.GONE);
        }

        mIvXihuan.setImageResource(R.drawable.icon_xin_99_d);
        if (data.isIsLike()) {
            mIvXihuan.setImageResource(R.drawable.ic_celect_xh_yes);
        }

        mIvShoucang.setImageResource(R.drawable.icon_share_sc_d);
        if (data.isIsFavorite()) {
            mIvShoucang.setImageResource(R.drawable.icon_share_sc_dx);
        }
    }

    private void query_related() {
        RetrofitUtil.getInstance().apiService()
                .query_related(circleId, momentId, publisherId, 1, ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<QueryPopularBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<QueryPopularBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData() == null) {
                                return;
                            }
//                            if(momentLocalMinId.equals("0")){
                            if(result.getData().size()>0){
                                mTv.setVisibility(View.VISIBLE);
                                mHomeThreeSqAdapter.setNewData(result.getData());
                            }
//                            }else{
//                                mHomeDynamicAdapter.addData(result.getData());
//                            }
//                            if(result.getData().size()==0){
//                                refreshLayout.finishLoadMoreWithNoMoreData();
//                            }else{
//                                momentLocalMinId = result.getData().get(result.getData().size()-1).getMomentId();
//                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }
    ArrayList<LocalMedia> list_path;
    private void initBannerOne(JSONArray split) throws JSONException {
        list_path = new ArrayList<>();
        for (int i = 0; i < split.length(); i++) {
            LocalMedia mLocalMedia = new LocalMedia();
            mLocalMedia.setCompressPath(split.getJSONObject(i).getString("imageUrl"));
            mLocalMedia.setPath(split.getJSONObject(i).getString("imageUrl"));
            list_path.add(mLocalMedia);
        }
        if(list_path.size()==1){
            mSiv1.setVisibility(View.VISIBLE);
            GlideImageUtils.setGlideImage(this, list_path.get(0).getCompressPath(), mSiv1);
        }
        if(list_path.size()==2){
            mRvImg2.setVisibility(View.VISIBLE);
            GlideImageUtils.setGlideImage(this, list_path.get(0).getCompressPath(), mSiv11);
            GlideImageUtils.setGlideImage(this, list_path.get(1).getCompressPath(), mSiv22);
        }
        if(list_path.size()>2){
            mRvListImg.setVisibility(View.VISIBLE);
            mSpPhotoAdapter.setNewData(list_path);
        }
    }

    /**
     * 获取动态信息 圈子
     */
    public static void startActivity(Context mContext, String circleId, String publisherId, String momentId) {
        Intent intent = new Intent(mContext, AssociationActivity.class);
        intent.putExtra("circleId", circleId);
        intent.putExtra("publisherId", publisherId);
        intent.putExtra("momentId", momentId);
        mContext.startActivity(intent);
    }

    /**
     * 获取动态信息 圈子
     */
    public static void startActivity(Context mContext, String circleId, String publisherId, String momentId,String ly) {
        Intent intent = new Intent(mContext, AssociationActivity.class);
        intent.putExtra("circleId", circleId);
        intent.putExtra("publisherId", publisherId);
        intent.putExtra("momentId", momentId);
        intent.putExtra("ly", ly);
        mContext.startActivity(intent);
    }

    @OnClick({R.id.siv_1,R.id.siv_11,R.id.siv_22,R.id.ll_back,R.id.tv_gz,R.id.iv_head_img,R.id.tv_name,R.id.tv_time, R.id.ss, R.id.tv_rd, R.id.tv_sj, R.id.ll_shoucang, R.id.ll_xihuan, R.id.ll_liuyan, R.id.ali_pl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.siv_1:
            case R.id.siv_11:
                if(list_path!=null && list_path.size()>0){
                    MatisseUtils.oPenUrlImgL(AssociationActivity.this, list_path, 0);
                }
                break;
            case R.id.siv_22:
                if(list_path!=null && list_path.size()>0){
                    MatisseUtils.oPenUrlImgL(AssociationActivity.this, list_path, 1);
                }
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_gz:
            case R.id.tv_gz_top:
                getzhu();
                break;
            case R.id.iv_head_img:
            case R.id.tv_name:
            case R.id.tv_time:
                UserHomeActivity.startActivity(AssociationActivity.this,data.getUser().getUserId());
                break;
            case R.id.ss:
                showSoftInputFromWindow(null, "");
                break;
            case R.id.tv_rd:
                mTvRd.setTextColor(getResources().getColor(R.color.color_333333));
                mTvSj.setTextColor(getResources().getColor(R.color.color_999999));
                commentOrderType = "hot";
                page = 1;
                getCommentMoment();
                break;
            case R.id.tv_sj:
                mTvRd.setTextColor(getResources().getColor(R.color.color_999999));
                mTvSj.setTextColor(getResources().getColor(R.color.color_333333));
                commentOrderType = "lately";
                page = 1;
                getCommentMoment();
                break;
            case R.id.ll_shoucang:
                shoucang();
                break;
            case R.id.ll_xihuan:
                xihuan();
                break;
            case R.id.ll_liuyan:
                mMMyNestedScrollView.smoothScrollTo(0,ll1.getTop());
                break;
            case R.id.ali_pl:
                page++;
                getCommentMoment();
                break;
        }
    }
    private void getzhu(){
        if(data.getUser().getRelation() == 1 || data.getUser().getRelation() == 3){
            HttpRequestUtils.postfollowCancel(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("1")){
                        if(data.getUser().getRelation()==3){
                            data.getUser().setRelation(2);
                        }else{
                            data.getUser().setRelation(0);
                        }
                        mTvGz.setText("+关注");
                        mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);

                        mTvGzTop.setText("+关注");
                        mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_theme_25);
                    }
                }
            });
        }else {
            HttpRequestUtils.postfollow(data.getUser().getUserId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if(path.equals("0")){
                        if(data.getUser().getRelation()==2){
                            data.getUser().setRelation(3);
                            mTvGz.setText("相互关注");
                            mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
                            mTvGzTop.setText("相互关注");
                            mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);

                        }else{
                            data.getUser().setRelation(1);
                            mTvGz.setText("已关注");
                            mTvGz.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);
                            mTvGzTop.setText("已关注");
                            mTvGzTop.setBackgroundResource(R.drawable.btn_shape_bj_fd1de_25);

                        }
                    }
                }
            });
        }
    }
    SoftKeyboardInputDialog dialog_input;
    /**
     * EditText获取焦点并显示软键盘
     */
    public void showSoftInputFromWindow(String commentId, String context2) {
        //使用自定义的dialog
        dialog_input = new SoftKeyboardInputDialog(this, context2, R.style.DialogTheme,
                new SoftKeyboardInputDialog.DialogInterfaceKey() {
                    @Override
                    public void strContext(String context) {
                        liuyan(context, commentId);
                    }
                });
        dialog_input.show();

        /** ====  ps:如果想按返回键关闭软键盘的同时也关闭dialog，那可以写如下代码：
         监听布局宽高变化，进而退出dialog（软键盘弹出布局变小，退出则恢复，以此来判断键盘弹出或退出）

         该activity的布局一定要有ScrollView控件 或 滚动的控件(ps：控件可无内容，只为实现布局因软键盘状态而大小变化)，
         或在AndroidManifest.xml对应<activity>里加入：android:windowSoftInputMode="adjustResize"，
         或有RecyclerView，否则软键盘弹出或隐藏布局大小没有变化！！
         */
        ll_top.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                if (bottom > oldBottom) {
                    dialog_input.dismiss();
                    ll_top.removeOnLayoutChangeListener(this);//移除监听，避免不需要时还在监听
                }
            }
        });
    }

    private void liuyan(String context, String commentId) {
        show();
        HttpRequestUtils.postCommentMoment(circleId, context, data.getMomentId(), data.getPublisherId() + "",
                commentId, 2,new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        if (!path.equals("0")) {
                            ToastUtils.showShortToast(AssociationActivity.this,"留言失败");
                            dismiss();
                            return;
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dismiss();
                                        dialog_input.dismiss();
                                        page = 1;
                                        getMomentDetails();
                                        getCommentMoment();
                                    }
                                });
                            }
                        }).start();
                    }
                });
    }

    private void xihuan() {
        if (data.isIsLike()) {
            HttpRequestUtils.postLikeCancel(data.getCircleId(), data.getMomentId() + "", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if (path.equals("0")) {
                        data.setIsLike(false);
                        data.setLikeCount(data.getLikeCount() - 1);
                        mTvXihuan.setText(String.valueOf(data.getLikeCount()));
                        mIvXihuan.setImageResource(R.drawable.icon_xin_99_d);
                    }
                }
            });
        } else {
            HttpRequestUtils.postLike(data.getCircleId(), data.getMomentId() + "", data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                public void succeed(String path) {
                    dismiss();
                    if (path.equals("0")) {
                        data.setIsLike(true);
                        data.setLikeCount(data.getLikeCount() + 1);
                        mTvXihuan.setText(String.valueOf(data.getLikeCount()));
                        mIvXihuan.setImageResource(R.drawable.ic_celect_xh_yes);
                    }
                }
            });
        }
    }

    private void shoucang() {
        if (data.isIsFavorite()) {
            HttpRequestUtils.postFavoritCancel(circleId, data.getMomentId(), new HttpRequestUtils.LoginInterface() {
                @Override
                public void succeed(String path) {
                    if (path.equals("0")) {
                        mTvShoucang.setText((data.getFavoriteCount() - 1) + "");
                        mIvShoucang.setImageDrawable(getResources().getDrawable(R.drawable.icon_share_sc_d));
                        data.setIsFavorite(false);
                        data.setFavoriteCount(data.getFavoriteCount() - 1);
                    }
                }
            });
        } else {
            HttpRequestUtils.postFavorit(circleId, data.getMomentId(),
                    data.getPublisherId() + "", new HttpRequestUtils.LoginInterface() {
                        @Override
                        public void succeed(String path) {
                            if (path.equals("0")) {
                                mTvShoucang.setText((data.getFavoriteCount() + 1) + "");
                                mIvShoucang.setImageDrawable(getResources().getDrawable(R.drawable.icon_share_sc_dx));
                                data.setIsFavorite(true);
                                data.setFavoriteCount(data.getFavoriteCount() + 1);
                            }
                        }
                    });
        }
    }
}
