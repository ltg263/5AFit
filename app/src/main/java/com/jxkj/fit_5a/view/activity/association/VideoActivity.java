package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogCommentPackage;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.JzvdStdTikTok;
import com.jxkj.fit_5a.conpoment.view.OnViewPagerListener;
import com.jxkj.fit_5a.conpoment.view.SoftKeyboardInputDialog;
import com.jxkj.fit_5a.conpoment.view.ViewPagerLayoutManager;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.MomentDetailsBean_X;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvTiktok;
    @BindView(R.id.ll_top)
    RelativeLayout ll_top;

    private ListVideoAdapter videoAdapter;
    private ViewPagerLayoutManager mViewPagerLayoutManager;
    String circleId, publisherId, momentId;
    String nextParam = null;
    private int mCurrentPosition = -1;
    @Override
    protected int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews() {
        circleId = getIntent().getStringExtra("circleId");
        publisherId = getIntent().getStringExtra("publisherId");
        momentId = getIntent().getStringExtra("momentId");
        videoAdapter = new ListVideoAdapter(null, new ListVideoAdapter.VideoInterface() {
            @Override
            public void btnLiuYan(MomentDetailsBean data,int type) {
                if(type==1){
                    ShowCommentPackageDialog(data);
                }else if(type== 2){
                    showSoftInputFromWindow(data);
                }
            }

            @Override
            public void position(int position) {
                Log.w("ListVideoAdapter","ListVideoAdapter"+position);
                if(position==infoList.size()-1 && jobId.equals(infoList.get(infoList.size()-1).getJobId())){
                    jobId = "";
                    getQuery_next_graphic(getIntent().getStringExtra("momentId"));
                }

            }
        });
        mViewPagerLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        rvTiktok.setLayoutManager(mViewPagerLayoutManager);
        rvTiktok.setAdapter(videoAdapter);
        addListener();
        getMomentDetails();
        postBrows();
    }

    SoftKeyboardInputDialog dialog_input;
    /**
     * EditText获取焦点并显示软键盘
     * @param data
     */
    public void showSoftInputFromWindow(MomentDetailsBean data) {
        //使用自定义的dialog
        dialog_input = new SoftKeyboardInputDialog(this, "", R.style.DialogTheme,
                new SoftKeyboardInputDialog.DialogInterfaceKey() {
                    @Override
                    public void strContext(String context) {
                        liuyan(context,data);
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
    private void liuyan(String context, MomentDetailsBean data) {
        show();
        HttpRequestUtils.postCommentMoment(data.getCircleId(), context, data.getMomentId(), data.getPublisherId() + "",
                null, 2,new HttpRequestUtils.LoginInterface() {
                    @Override
                    public void succeed(String path) {
                        dismiss();
                        if (!path.equals("0")) {
                            ToastUtils.showShort("发表失败");
                            return;
                        }
                        dialog_input.dismiss();
                        ToastUtils.showShort("发表成功");
                    }
                });
    }


    private void postBrows(){
        RetrofitUtil.getInstance().apiService()
                .postBrows(circleId,getIntent().getStringExtra("momentId")
                        ,getIntent().getStringExtra("publisherId"))
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
                            String media = result.getData().getMedia();
                            if(StringUtil.isNotBlank(media)
                                    && media.contains("vedioId") &&  media.contains("imageUrl")){
                                try {
                                    nextParam = null;
                                    JSONArray jsonArray = new JSONArray(media);
                                    getPlay_infos(result.getData(),jsonArray.getJSONObject(0).getString("vedioId")
                                            , jsonArray.getJSONObject(0).getString("imageUrl"),null);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else{
//                                DialogUtils.showDialogHint(VideoActivity.this, "视频丢失", true, new DialogUtils.ErrorDialogInterface() {
//                                    @Override
//                                    public void btnConfirm() {
//                                        finish();
//                                    }
//                                });

                                DialogUtils.showUnificationDialog(VideoActivity.this, "提示","视频丢失无法播放", "确定",false,
                                        new DialogUtils.UnificationDialogInterface() {
                                            @Override
                                            public void bntClickListener(String pos) {
                                                finish();
                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void getQuery_next_graphic(String momentId){
        RetrofitUtil.getInstance().apiService()
                .query_next_simple_video(momentId+"",nextParam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MomentDetailsBean_X>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Result<MomentDetailsBean_X> result) {
                        if(isDataInfoSucceed(result)){
                            nextParam = result.getData().getNextParam();
                            lists = result.getData().getList();
                            getPlay_infoLists(0);
//                            if(lists.size()==0){
//                                nextParam = null;
//                                getQuery_next_graphic(getIntent().getStringExtra("momentId"));
//                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
    List<MomentDetailsBean> lists;
    private void getPlay_infoLists(int pos) {
        if(lists!= null && lists.size()>pos){
//        for(int i=0;i<lists.size();i++){
            String media = lists.get(pos).getMedia();
            try {
                JSONArray jsonArray = new JSONArray(media);
                getPlay_infos(lists.get(pos),jsonArray.getJSONObject(0).getString("vedioId")
                        , jsonArray.getJSONObject(0).getString("imageUrl"),pos);
            } catch (JSONException e) {
                pos++;
                getPlay_infoLists(pos);
                e.printStackTrace();
            }
//        }
        }else{
            Log.w("--->>>>>>>>>>>>>","没有了");

        }
    }

    String jobId = "";
    List<VideoPlayInfoBean.PlayInfoListBean> infoList = new ArrayList<>();
    private void getPlay_infos(MomentDetailsBean data,String videoId, String imageUrl,Integer pos) {
        HttpRequestUtils.getPlayInfo(this,videoId, new HttpRequestUtils.PlayInfoInterface() {
            @Override
            public void succeed(Result<VideoPlayInfoBean> result) {
                if (result.getCode()==0) {
                    if(StringUtil.isBlank(nextParam)){
//                                getQuery_next_graphic(data.getMomentId());
                    }
                    List<VideoPlayInfoBean.PlayInfoListBean> info = result.getData().getPlayInfoList();
                    info.get(0).setImageUrl(imageUrl);
                    info.get(0).setData(data);
                    jobId = info.get(0).getJobId();
                    String playUrl = HttpRequestUtils
                            .initVideo(VideoActivity.this,info.get(0).getPlayURL(),videoId);
                    info.get(0).setPlayURL(playUrl);
                    infoList.addAll(info);
                    videoAdapter.addData(info);
                }else{
                    if(infoList.size()==0){
                        finish();
                    }
                }
                Integer posV = pos;
                if(posV != null){
                    posV++;
                    getPlay_infoLists(posV);
                }
            }
        });
    }

    private void addListener() {
        mViewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                autoPlayVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurrentPosition == position) {
                    Jzvd.releaseAllVideos();
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) {
                    return;
                }
                autoPlayVideo(position);
                mCurrentPosition = position;
            }
        });
        rvTiktok.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.mp_video);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos();
                    }
                }
            }
        });
    }

    private void autoPlayVideo(int postion) {
        if (rvTiktok == null || rvTiktok.getChildAt(0) == null) {
            return;
        }
        JzvdStdTikTok player = rvTiktok.getChildAt(0).findViewById(R.id.mp_video);
        if (player != null) {
            player.startVideoAfterPreloading();
        }
    }
    DialogCommentPackage choicePackageDialog;
    private void ShowCommentPackageDialog(MomentDetailsBean data) {
        choicePackageDialog = new DialogCommentPackage(this,data.getCircleId());
        HttpRequestUtils.getCommentMoment("lately",data.getCircleId(),data.getMomentId() , data.getPublisherId(),1,100,
                new HttpRequestUtils.ResultInterface() {
                    @Override
                    public void succeed(ResultList<CommentMomentBean> result) {
                        isDataInfoSucceed(result);
                        choicePackageDialog.setNewData(result.getData(),data.getCommentCount()+"");
                    }
                });
        choicePackageDialog.setOnCommentPackageDialogListener(new DialogCommentPackage.OnCommentPackageDialogListener() {
            @Override
            public void addListener(String commentOrderType,String context, String commentId) {
                show();
                HttpRequestUtils.postCommentMoment(data.getCircleId(),context, data.getMomentId(), data.getPublisherId(),
                        commentId, 2,new HttpRequestUtils.LoginInterface() {
                            @Override
                            public void succeed(String path) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                dismiss();
                                                HttpRequestUtils.getCommentMoment("lately",data.getCircleId(),data.getMomentId() , data.getPublisherId(),1,100,
                                                        new HttpRequestUtils.ResultInterface() {
                                                            @Override
                                                            public void succeed(ResultList<CommentMomentBean> result) {
                                                                if(isDataInfoSucceed(result)){
                                                                    data.setCommentCount(data.getCommentCount()+1);
                                                                    choicePackageDialog.setNewData(result.getData(),data.getCommentCount()+"");
                                                                }
                                                            }
                                                        });
                                            }
                                        });
                                    }
                                }).start();
                            }
                        });
            }

            @Override
            public void sortListener(String commentOrderType, int num) {
                HttpRequestUtils.getCommentMoment(commentOrderType,data.getCircleId(),data.getMomentId() , data.getPublisherId(),1,100,
                        new HttpRequestUtils.ResultInterface() {
                            @Override
                            public void succeed(ResultList<CommentMomentBean> result) {
                                if(isDataInfoSucceed(result)){
                                    choicePackageDialog.setNewData(result.getData(),data.getCommentCount()+"");
                                }
                            }
                        });
            }

        });
        choicePackageDialog.showDialog();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 获取动态信息 圈子
     */
    public static void startActivity(Context mContext, String circleId,String publisherId, String momentId) {
        Intent intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra("circleId", circleId);
        intent.putExtra("publisherId", publisherId);
        intent.putExtra("momentId", momentId);
        mContext.startActivity(intent);
    }


    @OnClick({R.id.iv_back, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
        }
    }
}
