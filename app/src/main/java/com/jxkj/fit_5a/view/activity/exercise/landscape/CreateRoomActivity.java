package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StatusBarUtil;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.entity.GageRoomCreateBean;
import com.jxkj.fit_5a.entity.GameRoomDetailsBean;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.adapter.LandscapeCreateRoomAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateRoomActivity extends Activity {
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_7)
    ImageView iv_7;
    @BindView(R.id.iv_8)
    ImageView iv_8;
    @BindView(R.id.et_1)
    EditText et_1;
    @BindView(R.id.et_2)
    EditText et_2;
    String mapId = "";
    String roomType = MotorPatternActivity.ROOM_TYPE[0];
    private LandscapeCreateRoomAdapter mLandscapeCreateRoomAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去掉信息栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕唤醒
        setContentView(R.layout.activity_landscape_create_room);
        ButterKnife.bind(this);
        getSportMapList();
        roomType = getIntent().getStringExtra("roomType");
        if(roomType.equals(MotorPatternActivity.ROOM_TYPE[1])){
            iv_7.setVisibility(View.INVISIBLE);
            iv_8.setVisibility(View.VISIBLE);
        }
        initViews();
    }

    private void initViews() {
        mLandscapeCreateRoomAdapter = new LandscapeCreateRoomAdapter(null);
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvList.setLayoutManager(ms);
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mLandscapeCreateRoomAdapter);

        mLandscapeCreateRoomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mapId = mLandscapeCreateRoomAdapter.getData().get(position).getId();
                mLandscapeCreateRoomAdapter.setPos(position);
                mLandscapeCreateRoomAdapter.notifyDataSetChanged();
            }
        });

    }
    private void getSportMapList() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapListSposrt>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getSportMapList_al(1, 20, ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }else {
            mObservable = mApiService.getSportMapList(1, 20, ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapListSposrt>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapListSposrt> result) {
                        if (result.getCode()==0) {
                            mLandscapeCreateRoomAdapter.setNewData(result.getData().getList());
                            if(mLandscapeCreateRoomAdapter.getData().size()>0){
                                mapId = mLandscapeCreateRoomAdapter.getData().get(0).getId();
                                mLandscapeCreateRoomAdapter.setPos(0);
                                mLandscapeCreateRoomAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.iv_1, R.id.iv_8, R.id.iv_3, R.id.iv_2, R.id.iv_7,R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                finish();
                break;
            case R.id.iv_2:
            case R.id.iv_8:
                roomType = MotorPatternActivity.ROOM_TYPE[0];
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_7:
            case R.id.iv_3:
                roomType = MotorPatternActivity.ROOM_TYPE[1];
                iv_7.setVisibility(View.INVISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                break;
            case R.id.btn:
                if(et_1.getText().toString().equals("")){
                    ToastUtils.showShort("房间名不能为空");
                    return;
                }
                gameCreateRoom();

                break;
        }
    }

    private void gameCreateRoom() {
        String password = null;
        if(StringUtil.isNotBlank(et_2.getText().toString())){
            password = et_2.getText().toString();
        }
        if(StringUtil.isNotBlank(password) && password.length()!=6){
            ToastUtils.showShort("密码长度设置6位数字");
            return;
        }
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<GageRoomCreateBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.gameCreateRoom_al(10, mapId,et_1.getText().toString(),password, password != null,roomType);//ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType
        }else {
            mObservable = mApiService.gameCreateRoom(10, mapId,et_1.getText().toString(),password, password != null,roomType);//ConstValues_Ly.DEVICE_TYPE_ID_URL,roomType;
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<GageRoomCreateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GageRoomCreateBean> result) {
                        if (result.getCode()==0) {
                            CreateRoomMineActivity.intentStartActivity(CreateRoomActivity.this,
                                    result.getData().getId());
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

    public static void intentStartActivity(Context mContext,String roomType){
        Intent mIntent = new Intent(mContext,CreateRoomActivity.class);
        mIntent.putExtra("roomType",roomType);
        mContext.startActivity(mIntent);
    }
}
