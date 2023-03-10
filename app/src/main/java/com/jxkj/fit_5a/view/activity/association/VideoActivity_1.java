package com.jxkj.fit_5a.view.activity.association;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.source.VidSts;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.view.MyVideoPlayer;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.adapter.ListVideoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoActivity_1 extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvPage2;

    private PagerSnapHelper snapHelper;
    private ListVideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;
    private int currentPosition;
        String videoId;
    ArrayList<String> urlList = new ArrayList<>();

    AliPlayer aliyunVodPlayer;
    private SurfaceView surfaceView;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_1;
    }

    @Override
    protected void initViews() {
        videoId = getIntent().getStringExtra("videoId");
//        getQueryByPublisher();
        getPlay_info(videoId);

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);


//        videoAdapter = new ListVideoAdapter(null, new ListVideoAdapter.VideoInterface() {
//            @Override
//            public void btnLiuYan(MomentDetailsBean data) {
//
//            }
//        });
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);
        addListener();
        aliyunVodPlayer = AliPlayerFactory.createAliPlayer(getApplicationContext());
        surfaceView = (SurfaceView) findViewById(R.id.playview);
        postOSSFile();
    }
    public void postOSSFile(){
        RetrofitUtil.getInstance().apiService()
                .getStsToken()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<StsTokenBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<StsTokenBean> result) {
                        if(result.getCode()==0) {
                            StsTokenBean data = result.getData();
                            SharedUtils.singleton().put(ConstValues.accessKeyId,data.getAccessKeyId());
                            SharedUtils.singleton().put(ConstValues.accessKeySecret,data.getAccessKeySecret());
                            SharedUtils.singleton().put(ConstValues.SecurityToken,data.getSecurityToken());
                            initVodPlayer();
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
    private void initVodPlayer() {
        //??????VidSts
        VidSts aliyunVidSts = new VidSts();
        aliyunVidSts.setVid(videoId);
        aliyunVidSts.setAccessKeyId(SharedUtils.singleton().get(ConstValues.accessKeyId,""));
        aliyunVidSts.setAccessKeySecret(SharedUtils.singleton().get(ConstValues.accessKeySecret,""));
        aliyunVidSts.setSecurityToken(SharedUtils.singleton().get(ConstValues.SecurityToken,""));
        aliyunVidSts.setRegion("cn-shanghai");
        aliyunVodPlayer.setAutoPlay(true);
        //???????????????
        aliyunVodPlayer.setDataSource(aliyunVidSts);

        //????????????
        aliyunVodPlayer.prepare();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                aliyunVodPlayer.setDisplay(holder);
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                aliyunVodPlayer.redraw();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                aliyunVodPlayer.setDisplay(null);
            }
        });
        // ???????????????
//        aliyunVodPlayer.start();
    }

    private void getPlay_info(String videoId){
        RetrofitUtil.getInstance().apiService()
                .getPlay_info(null,videoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VideoPlayInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VideoPlayInfoBean> result) {
                        if(isDataInfoSucceed(result)) {
                            for(int i =0;i<5;i++){
                                urlList.add(result.getData().getPlayInfoList().get(0).getPlayURL());
//                                videoAdapter.setNewData(urlList);
                                videoAdapter.notifyDataSetChanged();
                            }
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


    private void addListener() {

        rvPage2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://????????????
                        View view = snapHelper.findSnapView(layoutManager);

                        //??????????????????item position
                        int position = recyclerView.getChildAdapterPosition(view);
                        if (currentPosition != position) {
                            //????????????position ??? ?????????????????????position ??????, ??????????????????, ???????????????????????????, ??????????????????
                            MyVideoPlayer.releaseAllVideos();
                            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                            if (viewHolder != null && viewHolder instanceof ListVideoAdapter.VideoViewHolder) {
                                ((ListVideoAdapter.VideoViewHolder) viewHolder).mp_video.startVideo();
                            }
                        }
                        currentPosition = position;
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://??????
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://????????????
                        break;
                }

            }
        });
    }

    private void getQueryByPublisher() {
        RetrofitUtil.getInstance().apiService()
                .getQueryByPublisherOwn(0, 3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0) {
//                            for(int i=0;i<result.getData().size();i++){
                            String[] strArr = result.getData().get(0).getMedia().split(",");
                            getPlay_info(strArr[1]);
//                            }
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

    @Override
    public void onPause() {
        super.onPause();
        MyVideoPlayer.releaseAllVideos();
    }

    public static void startActivity(Context mContext, String videoId) {
        Intent intent = new Intent(mContext, VideoActivity_1.class);
        intent.putExtra("videoId", videoId);
        mContext.startActivity(intent);
    }
}
