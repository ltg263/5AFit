package com.jxkj.fit_5a.view.fragment;

import android.Manifest;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedHistoryEquipment;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowLanYan;
import com.jxkj.fit_5a.entity.MapDetailsBean;
import com.jxkj.fit_5a.entity.MapListSposrt;
import com.jxkj.fit_5a.entity.RankDetailsData;
import com.jxkj.fit_5a.entity.RankListData;
import com.jxkj.fit_5a.entity.RankStatsData;
import com.jxkj.fit_5a.lanya.ConstValues_Ly;
import com.jxkj.fit_5a.view.activity.exercise.CourseStartActivity;
import com.jxkj.fit_5a.view.activity.exercise.ExerciseRecordActivity;
import com.jxkj.fit_5a.view.activity.exercise.HistoryEquipmentActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskSelectionActivity;
import com.jxkj.fit_5a.view.activity.exercise.TaskStartActivity;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MapExerciseActivity;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.activity.home.RankListActivity;
import com.jxkj.fit_5a.view.activity.login_other.FacilityAddSbActivity;
import com.jxkj.fit_5a.view.activity.yaling.YaLingActivity_1;
import com.jxkj.fit_5a.view.adapter.FacilityAddSbAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTwoBelowAdapter;
import com.jxkj.fit_5a.view.adapter.HomeTwoTopAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeTwoFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_top_list)
    RecyclerView mRvTopList;
    @BindView(R.id.rv_sb_list)
    RecyclerView rv_sb_list;
    @BindView(R.id.tv_two_ri)
    TextView mTvTwoRi;
    @BindView(R.id.tv_two_zhou)
    TextView mTvTwoZhou;
    @BindView(R.id.tv_two_yue)
    TextView mTvTwoYue;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_lianjie)
    TextView tv_lianjie;
    @BindView(R.id.iv_two_img)
    ImageView iv_two_img;
    @BindView(R.id.rv_two_list)
    RecyclerView mRvTwoList;
    @BindView(R.id.tv_phb_2)
    TextView mTvPhb2;
    @BindView(R.id.iv_phb_2)
    ImageView mIvPhb2;
    @BindView(R.id.tv_phb_22)
    TextView mTvPhb22;
    @BindView(R.id.tv_phb_1)
    TextView mTvPhb1;
    @BindView(R.id.iv_phb_1)
    ImageView mIvPhb1;
    @BindView(R.id.tv_phb_11)
    TextView mTvPhb11;
    @BindView(R.id.tv_phb_3)
    TextView mTvPhb3;
    @BindView(R.id.iv_phb_3)
    ImageView mIvPhb3;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_phb_33)
    TextView mTvPhb33;
    @BindView(R.id.tv_mingc)
    TextView mTvMingc;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_dll)
    TextView mTvDll;
    @BindView(R.id.tv_zan)
    TextView mTvZan;
    @BindView(R.id.tv_all_guo)
    TextView tv_all_guo;
    @BindView(R.id.tv_tong_cheng)
    TextView tv_tong_cheng;
    @BindView(R.id.view_all_guo)
    View view_all_guo;
    @BindView(R.id.view_tong_cheng)
    View view_tong_cheng;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.rl_lianya_no)
    RelativeLayout rl_lianya_no;
    @BindView(R.id.rl_lianya_yes)
    RelativeLayout rl_lianya_yes;

    private FacilityAddSbAdapter mFacilityAddSbAdapter;
    private HomeTwoTopAdapter mHomeTwoTopAdapter;
    private HomeTwoBelowAdapter mHomeTwoBelowAdapter;
    String cityAdCode = "";

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_two;
    }


    @Override
    protected void initViews() {
        cityAdCode = SharedUtils.singleton().get(ConstValues.CITY_AD_CODE, "");
        initRvUi();
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(false));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getRankStatsList(typeD);
            }
        });
    }

    private void initRvUi() {

        List<String> list = new ArrayList<>();
        list.add("在线运动");
        list.add("经典运动");
//        list.add("哑铃运动");


        mRlActionbar.setAlpha(1);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY <= 500) {
//                    mRlActionbar.setAlpha(1.0f - (float) scrollY / 500.0f);
//                } else {
//                    mRlActionbar.setAlpha(0);
//                }
            }
        });

        mFacilityAddSbAdapter = new FacilityAddSbAdapter(null);
        mFacilityAddSbAdapter.setFang(true);
        rv_sb_list.setHasFixedSize(true);
        rv_sb_list.setAdapter(mFacilityAddSbAdapter);

        mFacilityAddSbAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (isYdQuanXian(getActivity())) {
                    return;
                }

//                PopupWindowLanYan.BleName = list.get(position).getName();
                ConstValues_Ly.DEVICE_IMG = mFacilityAddSbAdapter.getData().get(position).getImg();
//                IntentUtils.getInstence().intent(FacilityAddSbActivity.this, FacilityAddPpActivity.class,"bundle",mBundle);
                ConstValues_Ly.DEVICE_TYPE_ID_URL = mFacilityAddSbAdapter.getData().get(position).getId() + "";
                FacilityAddSbActivity.getBluetoothChannel(getActivity(),
                        mFacilityAddSbAdapter.getData().get(position).getName(), mTvZan);

//                Bundle mBundle = new Bundle();
//                mBundle.putString("name",mFacilityAddSbAdapter.getData().get(position).getName());
//                mBundle.putString("id",mFacilityAddSbAdapter.getData().get(position).getId()+"");
//
////              ConstValues_Ly.DEVICE_IMG = mFacilityAddSbAdapter.getData().get(position).getImg();
////              IntentUtils.getInstence().intent(getActivity(), FacilityAddPpActivity.class,"bundle",mBundle);
//                FacilityAddSbActivity.intentActivity(getContext());
            }
        });


        mHomeTwoTopAdapter = new HomeTwoTopAdapter(list);

        LinearLayoutManager mss = new LinearLayoutManager(getActivity());
        mss.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopList.setLayoutManager(mss);
        mRvTopList.setHasFixedSize(true);
        mRvTopList.setAdapter(mHomeTwoTopAdapter);

        mHomeTwoTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (isYdQuanXian(getActivity())) {
                    return;
                }
                if (tv_lianjie.getText().toString().equals("暂未连接设备")) {
//                    TaskStartActivity.goLianJie((BaseActivity) getActivity(), new TaskStartActivity.GoLianJieInterface() {
//                        @Override
//                        public void bntClickListener(String tvStr, String imgStr) {
//                            onResume();
//                        }
//                    });

                    List<HistoryEquipmentData> historyEquipment = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
                    if (historyEquipment == null || historyEquipment.size() == 0) {
                        ToastUtils.showShort("请先链接设备");
                        return;
                    }
                    HistoryEquipmentData historyEquipmentData = historyEquipment.get(0);
                    Log.w("historyEquipmentData", "historyEquipmentData:" + historyEquipmentData.toString());
                    HistoryEquipmentActivity.goLianJie((BaseActivity) getActivity(), historyEquipmentData, new HistoryEquipmentActivity.GoLianJieInterface() {
                        @Override
                        public void bntClickListener(String tvStr, String imgStr) {
                            onResume();
                            if (ConstValues_Ly.isYalingsheben(PopupWindowLanYan.BleName)) {
                                YaLingActivity_1.intentActivity(getActivity());
                            }
                        }
                    });
                    return;
                }
                if (!ConstValues_Ly.isA1) {
                    initLianZl(new InitLianZlInterface() {
                        @Override
                        public void bntClickListener() {
                            if (list.get(position).equals("在线运动")) {
                                MotorPatternActivity.startIntentActivity(getActivity(), MotorPatternActivity.ROOM_TYPE[0]);
                            } else if (list.get(position).equals("经典运动")) {
//                                IntentUtils.getInstence().
//                                        intent(getActivity(), TaskSelectionActivity.class, "exercise_type", list.get(position));
                                startActivity(new Intent(getActivity(), TaskStartActivity.class));
                            } else {
                                YaLingActivity_1.intentActivity(getActivity());
                            }
                        }
                    });
                    return;
                }

                if (list.get(position).equals("在线运动")) {
                    MotorPatternActivity.startIntentActivity(getActivity(), MotorPatternActivity.ROOM_TYPE[0]);
                } else if (list.get(position).equals("经典运动")) {
//                    IntentUtils.getInstence().
//                            intent(getActivity(), TaskSelectionActivity.class, "exercise_type", list.get(position));
                    startActivity(new Intent(getActivity(), TaskStartActivity.class));
                } else {
                    YaLingActivity_1.intentActivity(getActivity());
                }
            }
        });

        mHomeTwoTopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (isYdQuanXian(getActivity())) {
                    return;
                }

                if (tv_lianjie.getText().toString().equals("暂未连接设备")) {
//                    TaskStartActivity.goLianJie((BaseActivity) getActivity(), new TaskStartActivity.GoLianJieInterface() {
//                        @Override
//                        public void bntClickListener(String tvStr, String imgStr) {
//                            onResume();
//                        }
//                    });

                    List<HistoryEquipmentData> historyEquipment = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
                    if (historyEquipment == null || historyEquipment.size() == 0) {
                        ToastUtils.showShort("请先链接设备");
                        return;
                    }
                    HistoryEquipmentData historyEquipmentData = historyEquipment.get(0);
                    Log.w("historyEquipmentData", "historyEquipmentData:" + historyEquipmentData.toString());
                    HistoryEquipmentActivity.goLianJie((BaseActivity) getActivity(), historyEquipmentData, new HistoryEquipmentActivity.GoLianJieInterface() {
                        @Override
                        public void bntClickListener(String tvStr, String imgStr) {
                            onResume();
                            if (ConstValues_Ly.isYalingsheben(PopupWindowLanYan.BleName)) {
                                YaLingActivity_1.intentActivity(getActivity());
                            }
                        }
                    });
                    return;
                }

                if (!ConstValues_Ly.isA1) {
                    initLianZl(new InitLianZlInterface() {
                        @Override
                        public void bntClickListener() {
                            if (list.get(position).equals("在线运动")) {
                                if (view.getId() == R.id.tv_go_1) {
                                    MotorPatternActivity.startIntentActivity(getActivity(), MotorPatternActivity.ROOM_TYPE[0]);
                                } else if (view.getId() == R.id.tv_go_2) {
                                    MotorPatternActivity.startIntentActivity(getActivity(), MotorPatternActivity.ROOM_TYPE[1]);
                                }
                            } else {
                                if (view.getId() == R.id.tv_go_1) {
                                    goStartMap(getActivity());
                                } else if (view.getId() == R.id.tv_go_2) {
                                    startActivity(new Intent(getActivity(), TaskStartActivity.class));
                                }
                            }
                        }
                    });
                    return;
                }
                if (list.get(position).equals("在线运动")) {
                    if (view.getId() == R.id.tv_go_1) {
                        MotorPatternActivity.startIntentActivity(getActivity(), MotorPatternActivity.ROOM_TYPE[0]);
                    } else if (view.getId() == R.id.tv_go_2) {
                        MotorPatternActivity.startIntentActivity(getActivity(), MotorPatternActivity.ROOM_TYPE[1]);
                    }
                } else {
                    if (view.getId() == R.id.tv_go_1) {
                        goStartMap(getActivity());
                    } else if (view.getId() == R.id.tv_go_2) {
                        startActivity(new Intent(getActivity(), TaskStartActivity.class));
                    }
                }
            }
        });
        mHomeTwoBelowAdapter = new HomeTwoBelowAdapter(null);
        mRvTwoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvTwoList.setHasFixedSize(true);
        mRvTwoList.setAdapter(mHomeTwoBelowAdapter);

        mHomeTwoBelowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                getStatsZan(mHomeTwoBelowAdapter.getData().get(position).getId()
                        , typeD, mHomeTwoBelowAdapter.getData().get(position).isLike());
            }
        });
        getRankList(3);
        queryDeviceTypeLists();
        getSportMapList();


        /**
         * 广播动态注册
         */
        mMyReceiver = new MyReceiver();//集成广播的类
        IntentFilter filter = new IntentFilter("com.jxkj.fit_5a.view.fragment.HomeTwoFragment");// 创建IntentFilter对象
        getActivity().registerReceiver(mMyReceiver, filter);// 注册Broadcast Receive
    }

    MyReceiver mMyReceiver;

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra("type");
            if (type.equals("-2") && !tv_lianjie.getText().toString().equals("暂未连接设备")) {
                DialogUtils.showUnificationDialog(getContext(), "提示", "您的设备已断开连接\n请重新连接", "确定", false, new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String pos) {
                        onResume();
                    }
                });
            }

        }
    }

    private void initLianZl(InitLianZlInterface zlInterface) {
        show(getActivity());
        new Thread(new Runnable() {
            @Override
            public void run() {
                long s = System.currentTimeMillis();
                boolean isa1 = false;
                while (!isa1) {
                    if (ConstValues_Ly.isA1) {
                        isa1 = true;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                zlInterface.bntClickListener();
                                dismiss();
                            }
                        });
                    }
                    if ((s + 15000) < System.currentTimeMillis()) {
                        if (!ConstValues_Ly.isA1) {
                            isa1 = true;
                            dismiss();
                        }
                    }
                }
            }
        }).start();
    }

    private interface InitLianZlInterface {
        /**
         * 确定
         */
        void bntClickListener();
    }

    private void getSportMapList() {
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapListSposrt>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getSportMapList_al(1, 1, null);
        }else {
            mObservable = mApiService.getSportMapList(1, 1, null);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapListSposrt>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapListSposrt> result) {
                        if (isDataInfoSucceed(result)) {
                            mHomeTwoTopAdapter.setNum_map(result.getData().getTotalCount());
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

    private void queryDeviceTypeLists() {
        RetrofitUtil.getInstance().apiService()
                .queryDeviceTypeLists(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<DeviceTypeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DeviceTypeData> result) {
                        if (isDataInfoSucceed(result)) {
                            mFacilityAddSbAdapter.setNewData(result.getData().getList());
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

    public static void goStartMap(Context mContext) {
        if (true) {
            mContext.startActivity(new Intent(mContext, CourseStartActivity.class));
            return;
        }
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<MapDetailsBean>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.getMapRandomDetails_al(ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }else {
            mObservable = mApiService.getMapRandomDetails(ConstValues_Ly.DEVICE_TYPE_ID_URL);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null) {
                            MapExerciseActivity.intentStartActivity(mContext, null);
                        } else {
//                            DialogUtils.showDialogHint(mContext, result.getMesg(), true,null);
                            DialogUtils.showUnificationDialog(mContext, "提示", result.getMesg(), "确定", false, null);
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
    public void onResume() {
        super.onResume();
        GlideImageUtils.setGlideImage(getActivity(), R.drawable.ic_moren, iv_two_img);
        if (StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
            rl_lianya_yes.setVisibility(View.VISIBLE);
            rl_lianya_no.setVisibility(View.GONE);
            tv_lianjie.setText(PopupWindowLanYan.BleName);
            if (StringUtil.isNotBlank(ConstValues_Ly.DEVICE_IMG)) {
                GlideImageUtils.setGlideImage(getActivity(), ConstValues_Ly.DEVICE_IMG, iv_two_img);
            }
            List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
            for (int i = 0; i < lists.size(); i++) {
                if (PopupWindowLanYan.BleName.equals(lists.get(i).getName())) {
                    tv_time.setText(lists.get(i).getTime());
                }
            }
        } else {
            dismiss();
            ConstValues_Ly.isA1 = false;
            tv_lianjie.setText("暂未连接设备");
            rl_lianya_yes.setVisibility(View.GONE);
            rl_lianya_no.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeTwoFragment newInstance() {
        HomeTwoFragment homeFragment = new HomeTwoFragment();
        return homeFragment;
    }


    @OnClick({R.id.tv_left_text, R.id.tv_all_guo, R.id.tv_tong_cheng, R.id.tv_two_ri, R.id.tv_two_zhou, R.id.tv_two_yue, R.id.tv_go_find, R.id.tv_right_text})
    public void onViewClicked(View view) {
        Log.w("view","view:"+view.getId());
        switch (view.getId()) {
            case R.id.tv_left_text:
                if (isYdQuanXian(getActivity())) {
                    return;
                }
                List<HistoryEquipmentData> lists = SharedHistoryEquipment.singleton().getSharedHistoryEquipment();
                if (lists != null && lists.size() > 0) {
                    if (!ConstValues_Ly.isA1 && StringUtil.isNotBlank(PopupWindowLanYan.BleName)) {
                        initLianZl(new InitLianZlInterface() {
                            @Override
                            public void bntClickListener() {
                                IntentUtils.getInstence().intent(getActivity(), HistoryEquipmentActivity.class);
                            }
                        });
                        return;
                    }
                    IntentUtils.getInstence().intent(getActivity(), HistoryEquipmentActivity.class);
                    return;
                }
                FacilityAddSbActivity.intentActivity(getActivity());
                break;
            case R.id.tv_all_guo:
                isQuanGuo = true;
                tv_all_guo.setTextColor(getActivity().getColor(R.color.color_4555a3));
                tv_tong_cheng.setTextColor(getActivity().getColor(R.color.color_333333));
                view_all_guo.setVisibility(View.VISIBLE);
                view_tong_cheng.setVisibility(View.INVISIBLE);
                getRankList(typeD);
                break;
            case R.id.tv_tong_cheng:
                isQuanGuo = false;
                tv_all_guo.setTextColor(getActivity().getColor(R.color.color_333333));
                tv_tong_cheng.setTextColor(getActivity().getColor(R.color.color_4555a3));
                view_all_guo.setVisibility(View.INVISIBLE);
                view_tong_cheng.setVisibility(View.VISIBLE);
                getRankList(typeD);
                break;
            case R.id.tv_two_ri:
                getRankList(1);
                break;
            case R.id.tv_two_zhou:
                getRankList(2);
                break;
            case R.id.tv_two_yue:
                getRankList(3);
                break;
            case R.id.tv_go_find:
                startActivity(new Intent(getActivity(), RankListActivity.class));
                break;
            case R.id.tv_right_text:
                if (isYdQuanXian(getActivity())) {
                    return;
                }
                startActivity(new Intent(getActivity(), ExerciseRecordActivity.class));
                break;
        }
    }

    int typeD = 0;
    boolean isQuanGuo = true;

    private void getRankList(int type) {
        RetrofitUtil.getInstance().apiService()
                .getRankList(type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankListData> result) {
                        if (isDataInfoSucceed(result)) {
                            getRankStatsList(type);
                            List<RankListData.ListBean> list = result.getData().getList();
                            if (list != null && list.size() > 0) {
                                getRankDetails(list.get(0).getId());
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

    private void getRankDetails(String id) {
        RetrofitUtil.getInstance().apiService()
                .getRankDetails(Integer.valueOf(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<RankDetailsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RankDetailsData> result) {
                        if (isDataInfoSucceed(result)) {
                            List<RankDetailsData.RankRewardsBean> rankRewards = result.getData().getRankRewards();
                            if (rankRewards != null && rankRewards.size() > 0) {
                                for (int i = 0; i < rankRewards.size(); i++) {
                                    RankDetailsData.RankRewardsBean rankReward = rankRewards.get(i);
                                    switch (i) {
                                        case 0:
                                            mTvPhb1.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb1.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(), rankReward.getImgUrl(), mIvPhb1);
                                            mTvPhb11.setText(rankReward.getName());
                                            break;
                                        case 1:
                                            mTvPhb2.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb2.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(), rankReward.getImgUrl(), mIvPhb2);
                                            mTvPhb22.setText(rankReward.getName());
                                            break;
                                        case 2:
                                            mTvPhb3.setText("第" + rankReward.getStartRank() + "-" + rankReward.getEndRank() + "名");
                                            if (rankReward.getStartRank() == rankReward.getEndRank()) {
                                                mTvPhb3.setText("第" + rankReward.getStartRank() + "名");
                                            }
                                            GlideImageUtils.setGlideImage(getActivity(), rankReward.getImgUrl(), mIvPhb3);
                                            mTvPhb33.setText(rankReward.getName());
                                            break;

                                    }
                                }
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


    private void getRankStatsList(int type) {
        typeD = type;
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<RankStatsData>> mObservable;
        if (StringUtil.getLoginUserType().equals("1")) {
            if (!isQuanGuo) {
                mObservable = mApiService.getRankStatsList_city_al(type, cityAdCode)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            } else {
                mObservable = mApiService.getRankStatsList_al(type)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        } else {
            if (!isQuanGuo) {
                mObservable = mApiService.getRankStatsList_city(type, cityAdCode)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            } else {
                mObservable = mApiService.getRankStatsList(type)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        }
        show(getActivity());
        mObservable.subscribe(new Observer<Result<RankStatsData>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<RankStatsData> result) {
                if (isDataInfoSucceed(result)) {
                    RankStatsData.UserBean userData = result.getData().getUser();
                    if (userData != null) {
                    }
                    String nameNew = userData.getNickName();
                    String q = "";
                    String h = "";
                    if(!HomeTwoBelowAdapter.containsChineseCharacters(nameNew)){
                        if(nameNew.length()>10){
                            q = nameNew.substring(0,5);
                            h = nameNew.substring(nameNew.length()-3);
                            nameNew = q+"..."+h;
                        }
                    }else if(nameNew.length()>5){
                        q = nameNew.substring(0,2);
                        h = nameNew.substring(nameNew.length()-2);
                        nameNew = q+"..."+h;
                    }
                    mTvName.setText(nameNew);

                    GlideImageUtils.setGlideImage(getActivity(), userData.getAvatar(), iv_head);
                    mTvZan.setText(result.getData().getLikeCount());
                    mTvDll.setText(result.getData().getCalories() + "kcal");
                    mTvMingc.setText("未上榜");
                    if (result.getData().getRanking() != 0) {
                        mTvMingc.setText("No." + result.getData().getRanking());
                    }
//                            Glide.with(getActivity()).load(R.drawable.icon_zan_no).into((ImageView) helper.getView(R.id.iv_3));
//                            if(result.getData().isLike()){
//                                Glide.with(mContext).load(R.drawable.icon_zan_yes).into((ImageView) helper.getView(R.id.iv_3));
//                            }
                    mTvTwoYue.setBackgroundColor(0);
                    mTvTwoZhou.setBackgroundColor(0);
                    mTvTwoRi.setBackgroundColor(0);
                    mTvTwoYue.setTextColor(getActivity().getColor(R.color.color_000000));
                    mTvTwoZhou.setTextColor(getActivity().getColor(R.color.color_000000));
                    mTvTwoRi.setTextColor(getActivity().getColor(R.color.color_000000));
                    if (typeD == 3) {
                        mTvTwoYue.setTextColor(getActivity().getColor(R.color.white));
                        mTvTwoYue.setBackground(getActivity().getDrawable(R.drawable.bj_circle_theme_10));
                    }
                    if (typeD == 2) {
                        mTvTwoZhou.setTextColor(getActivity().getColor(R.color.white));
                        mTvTwoZhou.setBackground(getActivity().getDrawable(R.drawable.bj_circle_theme_10));
                    }
                    if (typeD == 1) {
                        mTvTwoRi.setTextColor(getActivity().getColor(R.color.white));
                        mTvTwoRi.setBackground(getActivity().getDrawable(R.drawable.bj_circle_theme_10));
                    }
                    List<RankStatsData.CaloriesRankingListBean> mCaloriesRankingList = result.getData().getCaloriesRankingList();
                    if (mCaloriesRankingList.size() > 10) {
                        mCaloriesRankingList = mCaloriesRankingList.subList(0, 10);
                    }
                    mHomeTwoBelowAdapter.setNewData(mCaloriesRankingList);
                }
            }

            @Override
            public void onError(Throwable e) {
                dismiss();
            }

            @Override
            public void onComplete() {
                dismiss();
                refreshLayout.finishRefresh();
            }
        });
    }


    private void getStatsZan(String calRankId, int dimension, boolean hasZan) {
        if (!hasZan) {
            ApiService mApiService = RetrofitUtil.getInstance().apiService();
            Observable<Result> mObservable;
            if(StringUtil.getLoginUserType().equals("1")){
                mObservable = mApiService.getStatsZan_al(calRankId, dimension);
            }else {
                mObservable = mApiService.getStatsZan(calRankId, dimension);
            }
            mObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (isDataInfoSucceed(result)) {
                                getRankStatsList(typeD);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            ApiService mApiService = RetrofitUtil.getInstance().apiService();
            Observable<Result> mObservable;
            if(StringUtil.getLoginUserType().equals("1")){
                mObservable = mApiService.getCancelStatsZan_al(calRankId, dimension);
            }else {
                mObservable = mApiService.getCancelStatsZan(calRankId, dimension);
            }
            mObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Result result) {
                            if (isDataInfoSucceed(result)) {
                                getRankStatsList(typeD);
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

    //先检查权限 anroid 6.0以上 需要动态获取 位置权限
    public static boolean isYdQuanXian(Context mContext) {
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(mContext, permissions)) {
            EasyPermissions.requestPermissions((BaseActivity) mContext, "为了您更好使用本应用，请允许应用获取以下权限", 1, permissions);
            return true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            String[] permissions_12 = new String[]{Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN};
            // 只包括蓝牙这部分的权限，其余的需要什么权限自己添加
            if (!EasyPermissions.hasPermissions(mContext, permissions_12)) {
                EasyPermissions.requestPermissions((BaseActivity) mContext, "为了您更好使用本应用，请允许应用获取以下权限", 1, permissions_12);
                return true;
            }
        }

        BluetoothManager bluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);

        //不支持 蓝牙
        if (bluetoothManager.getAdapter() == null) {
            ToastUtils.showShort("不支持蓝牙");
            return true;
        }

        //没有打开蓝牙
        if (!bluetoothManager.getAdapter().isEnabled()) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Log.w("bluetoothManager","bluetoothManager******************");
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                bluetoothManager.getAdapter().enable();
                return true;
            }
            bluetoothManager.getAdapter().enable();
            return true;
        }
        bluetoothManager=null;

        return false;
    }
}



