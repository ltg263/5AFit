package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.GuideViewComponent;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtilsNot;
import com.jxkj.fit_5a.conpoment.utils.StatusBarUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.LoadDialog;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.entity.ConnectRoomInfoBean;
import com.jxkj.fit_5a.entity.ConnectRoomUnfinishedBean;
import com.jxkj.fit_5a.entity.GageRoomCreateBean;
import com.jxkj.fit_5a.entity.GameRoomDetailsBean;
import com.jxkj.fit_5a.entity.GameRoomListBean;
import com.jxkj.fit_5a.entity.JoinQuickAndStartBean;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.SportLogDetailBean;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.netty_client.game.service.GameConnectService;
import com.jxkj.fit_5a.netty_client.game.service.impl.GameConnectServiceImpl;
import com.jxkj.fit_5a.netty_client.server.service.ConnectService;
import com.jxkj.fit_5a.netty_client.server.service.impl.ConnectServiceImpl;
import com.jxkj.fit_5a.view.adapter.LandscapeMotorPatternAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.netty.channel.ChannelFuture;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MotorPatternActivity extends Activity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_select_z)
    ImageView mIvSelectZ;
    @BindView(R.id.iv_select_y)
    ImageView mIvSelectY;
    @BindView(R.id.ll_btn)
    LinearLayout ll_btn;
    /**
     * quicks即时模式、
     * athletics竞赛模式
     */
    public static String[] ROOM_TYPE = new String[]{"quicks","athletics"};
    private String roomType = "";
    boolean isF = true;//首次进入
    private LandscapeMotorPatternAdapter mLandscapeMotorPatternAdapter;
    private MsgReceiver msgReceiver;
    boolean isFirstShow = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去掉信息栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒
        setContentView(R.layout.activity_landscape_motor_pattern);
        ButterKnife.bind(this);
        roomType = getIntent().getStringExtra("ROOM_TYPE");

        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jxkj.fit_5a.view.activity.exercise.landscape.CreateRoomMineActivity");//这里面的Action可以根据你的包来,这里的包是com.example.xx
        registerReceiver(msgReceiver, intentFilter);//如果是在fragment，那么getActivity().registerReceiver(msgReceiver, intentFilter);

        mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z1));
        mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y2));

        if(roomType.equals(ROOM_TYPE[1])){
            mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z2));
            mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y1));
        }
        get_connect_info();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            boolean isFirstExercise = SharedUtilsNot.singletonNotClear()
                                    .get(GuideViewComponent.isFirstExercise,false);
                            Log.w("isFirstExercise","isFirstExercise"+isFirstExercise);
                            if(!isFirstExercise){
                                isFirstShow = true;
                                new GuideViewComponent(MotorPatternActivity.this, mIvSelectY, 1,new GuideViewComponent.GuideViewComponentListener() {
                                    @Override
                                    public void onClickView() {
                                        new GuideViewComponent(MotorPatternActivity.this, ll_btn, 3, new GuideViewComponent.GuideViewComponentListener() {
                                            @Override
                                            public void onClickView() {
                                                isFirstShow = false;
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public static String roomMemberId = null;
    public static String roomId = null;
    /**
     * 广播接收器
     * @author len
     * @modify jiduoduo
     */
    public class MsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String notif_type = intent.getStringExtra("notif_type");
            Log.w("--->>","notif_type:"+notif_type);
            switch (notif_type) {
                case "room_join_the_room_notif"://玩家进入房间通知
                case "room_quit_the_room_notif"://玩家退出房间通知
                    break;
                case "room_start_game_notif"://房主开始游戏通知
                    break;
                case "game_authenticate_nack"://玩家拒绝认证
                    ToastUtils.showShort("玩家拒绝认证");
                    if(MotorPatternActivity.gameConnectService!=null){
                        MotorPatternActivity.gameConnectService.removeConnect();
                        MotorPatternActivity.gameConnectService = null;
                    }
                    if(StringUtil.isNotBlank(MotorPatternActivity.roomMemberId)){
                        gameGivenUp();
                    }
                    break;
                case "game_authenticate_ack"://玩家认证通过
                    intentStartActivityMapExercise(MotorPatternActivity.this,roomId,roomMemberId);
                    break;
            }
        }
    }

    private void get_unfinished() {
        show("连接中...");
        /**
         * 是否有未完成的任务
         */
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<ConnectRoomUnfinishedBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.get_unfinished_al();
        }else {
            mObservable = mApiService.get_unfinished();
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ConnectRoomUnfinishedBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ConnectRoomUnfinishedBean> result) {
                        dismiss();
                        if(result.getCode()==0 && result.getData()!=null){
                            ConnectRoomUnfinishedBean mConn = result.getData();
                            startGameConnectService(mConn.getGameServerHost(),mConn.getGameServerPort(),SharedUtils.getUserId()+"",mConn.getId(),mConn.getRoomMemberId());
                        }
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
    /**
     * 获取可用的房间列表
     */
    private void get_gameRoomList() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<GameRoomListBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.get_gameRoomList_al(1,100,ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType);//ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType
        }else {
            mObservable = mApiService.get_gameRoomList(1,100,ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType);//ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType;
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GameRoomListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GameRoomListBean> result) {
                        dismiss();
                        if(result.getCode()==0){
                            initViews(result.getData().getList());
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
    /**
     * 获取tcp连接信息
     */
    private void get_connect_info() {
        show("链接中...");
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<ConnectRoomInfoBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.get_connect_info_al();
        }else {
            mObservable = mApiService.get_connect_info();
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ConnectRoomInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ConnectRoomInfoBean> result) {
                        if(result.getCode()==0){
                            goConnectServiceInfo(result.getData());
                        }else{
                            dismiss();
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

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mConnectServiceImpl!=null && mConnectServiceImpl.getClientInfo().getCtx().isOpen()){
            return;
        }
        get_connect_info();
    }

    public static GameConnectServiceImpl gameConnectService;
    ConnectServiceImpl mConnectServiceImpl;
    private void goConnectServiceInfo(ConnectRoomInfoBean data) {
        mConnectServiceImpl = new ConnectServiceImpl();
        mConnectServiceImpl.addConnect(data.getRoomServerHost(), data.getRoomServerPort(),
                (long) SharedUtils.getUserId(), data.getConnectionPassword(), new ConnectService.ConnectServiceInterface() {
                    @Override
                    public void ConnectServiceInfo(ChannelFuture future) {
                        if(future.isSuccess()){
                            get_gameRoomList();
                        }else{
                            dismiss();
//                            DialogUtils.showDialogServerShiBai(MotorPatternActivity.this,
//                                    new DialogUtils.DialogInterfaceYhq() {
//                                        @Override
//                                        public void btnConfirm(int type) {
//                                            if(type==1){
//                                                if(PopupWindowLanYan.ble4Util!=null){
//                                                    PopupWindowLanYan.ble4Util.disconnect();
//                                                }
//                                                MotorPatternActivity.this.finish();
//                                                return;
//                                            }
//                                            get_connect_info();
//                                        }
//                                    });


                            DialogUtils.showUnificationDialog(MotorPatternActivity.this, "连接超时","连接运动服务超时", "放弃连接","重新连接",
                                     new DialogUtils.UnificationDialogInterface() {
                                        @Override
                                        public void bntClickListener(String pos) {
                                            if (pos.equals("1")) {
                                                if (PopupWindowLanYan.ble4Util != null) {
                                                    PopupWindowLanYan.ble4Util.disconnect();
                                                }
                                                MotorPatternActivity.this.finish();
                                                return;
                                            }
                                            get_connect_info();
                                        }
                                    });
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if(PopupWindowLanYan.ble4Util!=null){
            PopupWindowLanYan.ble4Util.disconnect();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mConnectServiceImpl!=null){
            mConnectServiceImpl.removeConnect();
            mConnectServiceImpl = null;
        }
        if(msgReceiver!=null){
            unregisterReceiver(msgReceiver);
        }

    }

    private void initViews(List<GameRoomListBean.ListBean> list) {
        mLandscapeMotorPatternAdapter = new LandscapeMotorPatternAdapter(list);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mLandscapeMotorPatternAdapter);

        mLandscapeMotorPatternAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(isFirstShow){
                    return;
                }
                GameRoomListBean.ListBean item = mLandscapeMotorPatternAdapter.getData().get(position);
                if(item.getType().equals(MotorPatternActivity.ROOM_TYPE[0])){
                    if(!item.isVerification()){
                        joinQuickAndStart(item.getId(),null);
                        return;
                    }
//                    DialogUtils.showDialogInputPass(MotorPatternActivity.this, new DialogUtils.EditTextDialogInterface() {
//                        @Override
//                        public void btnConfirm(String str) {
//                            joinQuickAndStart(item.getId(),str);
//                        }
//                    });
                    DialogUtils.showUnificationDialogEditText(MotorPatternActivity.this, "输入密码","",new DialogUtils.UnificationDialogInterface() {
                        @Override
                        public void bntClickListener(String str) {
                            joinQuickAndStart(item.getId(),str);
                        }
                    });
                    return;
                }
//                if(mLandscapeMotorPatternAdapter.getData().get(position).getStatus().equals("waitting")){
                    if(item.getUser().getUserId().equals(SharedUtils.getUserId()+"")){
                        CreateRoomMineActivity.intentStartActivity(MotorPatternActivity.this,
                                item.getId());
                        return;
                    }
                    if(!item.isVerification()){
                        gameRoomJoin(item.getId(),null,null);
                        return;
                    }
//                    DialogUtils.showDialogInputPass(MotorPatternActivity.this, new DialogUtils.EditTextDialogInterface() {
//                        @Override
//                        public void btnConfirm(String str) {
//                            gameRoomJoin(item.getId(),str,null);
//                        }
//                    });
                    DialogUtils.showUnificationDialogEditText(MotorPatternActivity.this, "输入密码","",new DialogUtils.UnificationDialogInterface() {
                        @Override
                        public void bntClickListener(String str) {
                            gameRoomJoin(item.getId(),str,null);
                        }
                    });
//                }else{
//                    ToastUtil.showLongStrToast(MotorPatternActivity.this,"当前房间不能进入");
//                }
            }
        });
        if(isF){
            isF = false;
            get_unfinished();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        joinQuickAndStart = false;
        get_gameRoomList();
        if(StringUtil.isBlank(PopupWindowLanYan.BleName)){
//            DialogUtils.showDialogHint(this, "您的设备已断开连接\n请重新连接", true, new DialogUtils.ErrorDialogInterface() {
//                @Override
//                public void btnConfirm() {
//                    startActivity(new Intent(MotorPatternActivity.this, MainActivity.class));
//                    finish();
//                }
//            });

            DialogUtils.showUnificationDialog(this, "提示", "您的设备已断开连接\n请重新连接", "确定", false, new DialogUtils.UnificationDialogInterface() {
                @Override
                public void bntClickListener(String pos) {
                    startActivity(new Intent(MotorPatternActivity.this, MainActivity.class));
                    finish();
                }
            });
            return;
        }
    }

    boolean joinQuickAndStart = false;
    /**
     * 进入即时房间并开始游戏
     */
    private void joinQuickAndStart(String roomId, String password) {
        show("连接中...");
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<JoinQuickAndStartBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.joinQuickAndStart_al(roomId,password);
        }else {
            mObservable = mApiService.joinQuickAndStart(roomId,password);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<JoinQuickAndStartBean>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onNext(Result<JoinQuickAndStartBean> result) {
                        dismiss();
                        if(result.getCode()==0){
                            JoinQuickAndStartBean.StartGameInfoBean mStartGameInfo = result.getData().getStartGameInfo();
                            if(!joinQuickAndStart && mStartGameInfo!=null){
                                joinQuickAndStart = true;
                                startGameConnectService(mStartGameInfo.getHost(),mStartGameInfo.getPort(),
                                        mStartGameInfo.getUserId(),mStartGameInfo.getRoomId(),mStartGameInfo.getRoomMemberId());
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

    /**
     * 连接游戏服务
     */
    private void startGameConnectService(String host, String port, String userId, String roomId,String roomMemberId) {
        show("连接中...");

        MotorPatternActivity.this.roomMemberId =roomMemberId ;
        MotorPatternActivity.this.roomId = roomId;
        gameConnectService = new GameConnectServiceImpl();
        gameConnectService.addConnect(host, Integer.parseInt(port), Long.parseLong(userId), Long.parseLong(roomId),
                new GameConnectService.ConnectServiceInterface() {
                    @Override
                    public void ConnectServiceInfo(ChannelFuture future) {
                        dismiss();
                        if(future.isSuccess()){
                            MotorPatternActivity.this.roomMemberId =roomMemberId ;
                            MotorPatternActivity.this.roomId = roomId;
                        }else{
//                            DialogUtils.showDialogServerShiBai(MotorPatternActivity.this,
//                                    new DialogUtils.DialogInterfaceYhq() {
//                                        @Override
//                                        public void btnConfirm(int type) {
//                                            if(type==1){
//                                                if(PopupWindowLanYan.ble4Util!=null){
//                                                    PopupWindowLanYan.ble4Util.disconnect();
//                                                }
//                                                MotorPatternActivity.this.finish();
//                                                return;
//                                            }
//                                            get_connect_info();
//                                        }
//                                    });

                            DialogUtils.showUnificationDialog(MotorPatternActivity.this, "连接超时","连接运动服务超时", "放弃连接","重新连接",
                                     new DialogUtils.UnificationDialogInterface() {
                                        @Override
                                        public void bntClickListener(String pos) {
                                            if (pos.equals("1")) {
                                                if (PopupWindowLanYan.ble4Util != null) {
                                                    PopupWindowLanYan.ble4Util.disconnect();
                                                }
                                                MotorPatternActivity.this.finish();
                                                return;
                                            }
                                            get_connect_info();
                                        }
                                    });
                        }
                    }
                });
    }

    /**
     * 快速开始游戏
     */
    private void get_gameRoomQuickStart(String deviceTypeId) {
        show("连接中...");
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<JoinQuickAndStartBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.get_gameRoomQuickStart_al(deviceTypeId);
        }else {
            mObservable = mApiService.get_gameRoomQuickStart(deviceTypeId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<JoinQuickAndStartBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<JoinQuickAndStartBean> result) {
                        dismiss();
                        if(result.getCode()==0){
                            JoinQuickAndStartBean.StartGameInfoBean mStartGameInfo = result.getData().getStartGameInfo();
                            if(!joinQuickAndStart && mStartGameInfo!=null){
                                joinQuickAndStart = true;
                                startGameConnectService(mStartGameInfo.getHost(),mStartGameInfo.getPort(),
                                        mStartGameInfo.getUserId(),mStartGameInfo.getRoomId(),mStartGameInfo.getRoomMemberId());
                            }
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
    /**
     * 进入房间
     */
    private void gameRoomJoin(String roomId, String password,ConnectRoomUnfinishedBean mConn) {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.gameRoomJoin_al(roomId,password);
        }else {
            mObservable = mApiService.gameRoomJoin(roomId,password);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            if(mConn!=null){
                                roomMemberId = mConn.getRoomMemberId();
                                MotorPatternActivity.this.roomId = mConn.getId();
                                intentStartActivityMapExercise(MotorPatternActivity.this,mConn.getId(),roomMemberId);
                                return;
                            }
                            CreateRoomMineActivity.intentStartActivity(MotorPatternActivity.this,
                                    roomId);
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
    @OnClick({R.id.iv_1,R.id.bnt1,R.id.iv_shaxin,R.id.bnt2,R.id.iv_select_z,R.id.iv_select_y})
    public void onViewClicked(View view) {
        if(isFirstShow){
            return;
        }
        if(view.getId()!=R.id.iv_1 && StringUtil.isBlank(PopupWindowLanYan.BleName)) {
//            DialogUtils.showDialogHint(this, "您的设备已断开连接\n请重新连接", true, new DialogUtils.ErrorDialogInterface() {
//                @Override
//                public void btnConfirm() {
//                    startActivity(new Intent(MotorPatternActivity.this, MainActivity.class));
//                    if(PopupWindowLanYan.ble4Util!=null){
//                        PopupWindowLanYan.ble4Util.disconnect();
//                    }
//                    finish();
//                }
//            });
            DialogUtils.showUnificationDialog(this, "提示", "您的设备已断开连接\n请重新连接", "确定", false, new DialogUtils.UnificationDialogInterface() {
                @Override
                public void bntClickListener(String pos) {
                    startActivity(new Intent(MotorPatternActivity.this, MainActivity.class));
                    if(PopupWindowLanYan.ble4Util!=null){
                        PopupWindowLanYan.ble4Util.disconnect();
                    }
                    finish();
                }
            });
            return;
        }
        switch (view.getId()) {
            case R.id.iv_1:
                if(PopupWindowLanYan.ble4Util!=null){
                    PopupWindowLanYan.ble4Util.disconnect();
                }
                finish();
                break;
            case R.id.iv_select_z:
                mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z1));
                mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y2));
                roomType = ROOM_TYPE[0];
                get_gameRoomList();
                break;
            case R.id.iv_select_y:
                mIvSelectZ.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_z2));
                mIvSelectY.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hp_select_y1));
                roomType = ROOM_TYPE[1];
                get_gameRoomList();
                break;
            case R.id.iv_shaxin:
                get_gameRoomList();
                break;
            case R.id.bnt1:
                get_gameRoomQuickStart(ConstValues_Ly.DEVICE_TYPE_ID_URL);
                break;
            case R.id.bnt2:
                CreateRoomActivity.intentStartActivity(this,roomType);
                break;
        }
    }

    public static void startIntentActivity(Context mContext,String roomType){
        Intent mIntent = new Intent(mContext,MotorPatternActivity.class);
        mIntent.putExtra("ROOM_TYPE",roomType);
        mContext.startActivity(mIntent);
    }

    /**
     * 准备跳转直运动页面
     * @param roomId
     */
    public static void intentStartActivityMapExercise(Context mContext ,String roomId, String roomMemberId) {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<GameRoomDetailsBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.get_gameRoomDetails_al(roomId);//ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType
        }else {
            mObservable = mApiService.get_gameRoomDetails(roomId);//ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType;
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GameRoomDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GameRoomDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null){
                            getMapDetails(mContext,result.getData(),roomId,roomMemberId);
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
    public static void getMapDetails(Context mContext ,GameRoomDetailsBean mGameRoomDetailsBean, String roomId, String roomMemberId) {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapDetailsBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getMapDetails_al(mGameRoomDetailsBean.getMapId());
        }else {
            mObservable = mApiService.getMapDetails(mGameRoomDetailsBean.getMapId());
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null){
                            MapDetailsBean mMapDetailsBean_zx = result.getData();
                            List<GameRoomDetailsBean.UserBean> members = mGameRoomDetailsBean.getMembers();
                            List<MapDetailsBean.UserBean> userMembers = new ArrayList<>();
                            List<String> userIds = new ArrayList<>();
                            for(int i=0;i<members.size();i++){
                                MapDetailsBean.UserBean mUserBean = new MapDetailsBean.UserBean();
                                mUserBean.setAvatar(members.get(i).getAvatar());
                                mUserBean.setGender(members.get(i).getGender());
                                mUserBean.setNickName(members.get(i).getNickName());
                                mUserBean.setUserId(members.get(i).getUserId());
                                if(!userIds.contains(mUserBean.getUserId())){
                                    if(members.get(i).getUserId().equals(SharedUtils.getUserId()+"")){
                                        userIds.add(mUserBean.getUserId());
                                        userMembers.add(mUserBean);
                                    }else{
//                                        userIds.add(0,mUserBean.getUserId());
//                                        userMembers.add(0,mUserBean);
                                    }
                                }
                            }
                            mMapDetailsBean_zx.setType(mGameRoomDetailsBean.getType());
                            mMapDetailsBean_zx.setUserMembers(userMembers);
//                            Log.w("--->>>","头像个数"+userMembers.toString());
                            Log.w("--->>>","roomId:"+roomId);
                            Log.w("--->>>","roomMemberId:"+roomMemberId);
                            MapExerciseActivity_Zx.intentStartActivity(mContext,mMapDetailsBean_zx,roomId,roomMemberId);
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
    private LoadDialog dialog;
    private void show(String str) {
        if(dialog==null){
            dialog = new LoadDialog(this, str);
        }

        if (!isFinishing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }

    private void gameGivenUp() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.gameGivenUp_al(roomMemberId);
        }else {
            mObservable = mApiService.gameGivenUp(roomMemberId);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0) {
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
