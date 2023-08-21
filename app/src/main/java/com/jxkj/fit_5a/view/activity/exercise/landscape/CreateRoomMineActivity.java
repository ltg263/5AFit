package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StatusBarUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.SelectableRoundedImageView;
import com.jxkj.fit_5a.entity.GameRoomDetailsBean;
import com.jxkj.fit_5a.view.activity.association.VideoActivity;
import com.jxkj.fit_5a.view.adapter.LandscapeCreateRoomMineAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateRoomMineActivity extends Activity{
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    String roomId;
    @BindView(R.id.tv_name_n)
    TextView mTvNameN;
    @BindView(R.id.iv_imgUrl)
    SelectableRoundedImageView mIvImgUrl;
    @BindView(R.id.tv_verification)
    TextView mTvVerification;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_map_name)
    TextView mTvMapName;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_map_box)
    TextView mTvMapBox;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.tv_js)
    TextView tv_js;
    @BindView(R.id.tv_limitNumber)
    TextView mTvLimitNumber;
    @BindView(R.id.tv_nickName)
    TextView mTvNickName;
    String ownerUserId = "";
    String mapId = "";
    private LandscapeCreateRoomMineAdapter mLandscapeCreateRoomMineAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去掉信息栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒
        setContentView(R.layout.activity_landscape_create_room_mine);
        ButterKnife.bind(this);
        initViews();
    }

    MsgReceiver msgReceiver;
    private void initViews() {
        roomId = getIntent().getStringExtra("roomId");
        Log.w("-->>>>","roomId"+roomId);
        mTvNameN.setText("房间号："+0);
        mLandscapeCreateRoomMineAdapter = new LandscapeCreateRoomMineAdapter(null);
        mRvList.setLayoutManager(new GridLayoutManager(this, 2));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mLandscapeCreateRoomMineAdapter);

        mLandscapeCreateRoomMineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(getActivity(), TaskSelectionActivity.class));
            }
        });
        get_gameRoomDetails();
        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jxkj.fit_5a.view.activity.exercise.landscape.CreateRoomMineActivity");//这里面的Action可以根据你的包来,这里的包是com.example.xx
        registerReceiver(msgReceiver, intentFilter);//如果是在fragment，那么getActivity().registerReceiver(msgReceiver, intentFilter);
    }
    /**
     * 广播接收器
     * @author len
     * @modify jiduoduo
     */
    public class MsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String notif_type = intent.getStringExtra("notif_type");
            Log.w("----->>","notif_type:"+notif_type);
            switch (notif_type) {
                case "room_join_the_room_notif"://玩家进入房间通知
                case "room_quit_the_room_notif"://玩家退出房间通知
                    get_gameRoomDetails();
                    break;
                case "room_dismiss_the_room_notif"://房主解散房间
                    DialogUtils.showUnificationDialog(CreateRoomMineActivity.this, "提示","房主已解散房间", "确定",false,
                            new DialogUtils.UnificationDialogInterface() {
                                @Override
                                public void bntClickListener(String pos) {
                                    CreateRoomMineActivity.this.finish();
                                }
                            });
                    break;
                case "room_start_game_notif"://房主开始游戏通知
                    Log.w("--->>>","roomId:"+roomId);
                    MotorPatternActivity.roomMemberId = intent.getStringExtra("roomMemberId");
                    MotorPatternActivity.roomId = roomId;
//                    MotorPatternActivity.intentStartActivityMapExercise(
//                            CreateRoomMineActivity.this,roomId, intent.getStringExtra("roomMemberId"));
                    break;
            }
        }
    }

    GameRoomDetailsBean mData;
    private void get_gameRoomDetails() {
        RetrofitUtil.getInstance().apiService()
                .get_gameRoomDetails(roomId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GameRoomDetailsBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GameRoomDetailsBean> result) {
                        if (result.getCode() == 0) {
                            mData = result.getData();
                            mapId = mData.getMapId();
                            mTvVerification.setText(0+"");
                            mTvVerification.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_suo_2),null,null,null);
                            if(mData.isVerification()){
                                mTvVerification.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_suo_1),null,null,null);
                            }
                            GlideImageUtils.setGlideImage(CreateRoomMineActivity.this,mData.getSportMapBase().getImgUrl(),mIvImgUrl);
                            mTvName.setText(mData.getName());
                            mTvDistance.setText(mData.getSportMapBase().getDistance()+"km");
                            mTvMapName.setText(mData.getSportMapBase().getName());
                            mTvLimitNumber.setText("人数:"+mData.getCurrentNumber()+"/"+mData.getLimitNumber()+"人");
                            if(mData.getUser()!=null){
                                mTvNickName.setText("房主:"+mData.getUser().getNickName());
                            }
                            if(mData.getSportMapBase().getBoxs()!=null){
                                mTvMapBox.setText("宝箱:"+mData.getSportMapBase().getBoxs().size()+"个");
                            }
                            ownerUserId = mData.getOwnerUserId();
                            tv_ok.setVisibility(View.GONE);
                            if(mData.getOwnerUserId().equals(SharedUtils.getUserId()+"")
                                    &&( mData.getMembers().size()>1 || mData.getType().equals(MotorPatternActivity.ROOM_TYPE[0]))){
                                tv_ok.setVisibility(View.VISIBLE);
                            }
                            if(!mData.getOwnerUserId().equals(SharedUtils.getUserId()+"")){
                                tv_js.setText("退出");
                            }
                            mLandscapeCreateRoomMineAdapter.setMapUserId(mData.getOwnerUserId());
                            mLandscapeCreateRoomMineAdapter.setNewData(mData.getMembers());
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

    @OnClick({R.id.iv_1, R.id.tv_ok,R.id.tv_js})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_js:
                if(mData.getOwnerUserId().equals(SharedUtils.getUserId()+"")){
//                    DialogUtils.showDialogOutRoom_zx(this, "解散房间","确定解散您创建的房间嘛？",new DialogUtils.EditTextDialogInterface() {
//                        @Override
//                        public void btnConfirm(String string) {
//                            gameRoomDismiss(roomId);
//                        }
//                    });

                    DialogUtils.showUnificationDialog(this, "解散房间","确定解散您创建的房间吗？", "解散",true,
                             new DialogUtils.UnificationDialogInterface() {
                                @Override
                                public void bntClickListener(String pos) {
                                    gameRoomDismiss(roomId);
                                }
                            });
                    return;
                }
//                DialogUtils.showDialogOutRoom_zx(this, "退出房间","确定退出房间嘛？",new DialogUtils.EditTextDialogInterface() {
//                    @Override
//                    public void btnConfirm(String string) {
//                        gameRoomQuit(roomId);
//                    }
//                });


                DialogUtils.showUnificationDialog(this, "退出房间","确定要退出房间吗？", "退出",true,
                         new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                gameRoomQuit(roomId);
                            }
                        });
                break;
            case R.id.iv_1:
                if(mData.getOwnerUserId().equals(SharedUtils.getUserId()+"")){
                    finish();
                    return;
                }
//                DialogUtils.showDialogOutRoom_zx(this, "退出房间","确定退出房间嘛？",new DialogUtils.EditTextDialogInterface() {
//                    @Override
//                    public void btnConfirm(String string) {
//                        gameRoomQuit(roomId);
//                    }
//                });
                DialogUtils.showUnificationDialog(this, "退出房间","确定要退出房间吗？", "退出",true,
                         new DialogUtils.UnificationDialogInterface() {
                            @Override
                            public void bntClickListener(String pos) {
                                gameRoomQuit(roomId);
                            }
                        });
                break;
            case R.id.tv_ok:
//                MotorPatternActivity.intentStartActivityMapExercise(
//                            CreateRoomMineActivity.this,roomId, intent.getStringExtra("roomMemberId"));
                ;
                MotorPatternActivity.roomMemberId = roomId;
                gameRoomStart(roomId);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(msgReceiver!=null){
            unregisterReceiver(msgReceiver);
        }
    }

    /**
     * 开始游戏
     */
    private void gameRoomStart(String roomId) {
        RetrofitUtil.getInstance().apiService()
                .gameRoomStart(roomId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){

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
    protected void onPause() {
        super.onPause();
        finish();
    }

    /**
     * 解散房间
     */
    private void gameRoomDismiss(String roomId) {
        RetrofitUtil.getInstance().apiService()
                .gameRoomDismiss(roomId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            finish();
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

    /**
     * 退出房间
     */
    private void gameRoomQuit(String roomId) {
        RetrofitUtil.getInstance().apiService()
                .gameRoomQuit(roomId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            finish();
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

    public static void intentStartActivity(Context mContext, String roomId) {
        Intent mIntent = new Intent(mContext, CreateRoomMineActivity.class);
        mIntent.putExtra("roomId", roomId);
        mContext.startActivity(mIntent);
    }
}
