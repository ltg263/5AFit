package com.jxkj.fit_5a.view.activity.exercise.landscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTopicUtils_Map;
import com.jxkj.fit_5a.entity.MapDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapExerciseActivity_1 extends Activity {
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_3)
    ImageView mIv3;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    boolean isSuo = false;
    String mapId;
    String boxId;
    private MapView mMapView;

    AMap aMap;
    private SmoothMoveMarker smoothMarker;

    public static void intentStartActivity(Context mContext, String mapId) {
        Intent intent = new Intent(mContext, MapExerciseActivity_1.class);
        intent.putExtra("mapId", mapId);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_map_exercise);
        ButterKnife.bind(this);
        initViews();

        //????????????????????????
        mMapView = (MapView) findViewById(R.id.map);
        //??????????????????????????????logo?????????????????????
        //???activity??????onCreate?????????mMapView.onCreate(savedInstanceState)???????????????
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);
        mUiSettings.setAllGesturesEnabled(false);

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("lgq","dianjiddd===="+marker.getPeriod());//??????markerID

                getBoxReceive(marker.getPeriod()+"");
                return true;
            }
        });
    }


    PopupWindowTopicUtils_Map window;

    private void initViews() {
        mapId = getIntent().getStringExtra("mapId");
        getMapDetails();

        if(window==null){
            window = new PopupWindowTopicUtils_Map(MapExerciseActivity_1.this, type -> {
                if(smoothMarker==null){
                    return;
                }
                if(type ==0){
                    smoothMarker.startSmoothMove();
                }else if(type==1){
                    smoothMarker.stopMove();
                }else if(type==2){
                    mTvTime.setVisibility(View.VISIBLE);
                }

            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
//        DialogUtils.showDialogClass(ClassicExerciseActivity.this, 1, new DialogUtils.DialogLyInterface() {
//            @Override
//            public void btnConfirm() {
//
//            }
//        });
    }


    @OnClick({R.id.iv_1, R.id.iv_2, R.id.iv_3, R.id.iv_4, R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                if (mIv3.getVisibility() == View.VISIBLE) {
                    mIv3.setVisibility(View.GONE);
                    mIv4.setVisibility(View.GONE);
                } else {
                    mIv3.setVisibility(View.VISIBLE);
                    mIv4.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_2:
                if (isSuo) {
                    isSuo = false;
                    mIv2.setImageDrawable(MapExerciseActivity_1.this.getResources().getDrawable(R.mipmap.ic_hp_yd_9));
                    window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // ??????layout???PopupWindow??????????????????
                    mTvTime.setVisibility(View.GONE);
                } else {
                    isSuo = true;
                    mTvTime.setVisibility(View.VISIBLE);
                    mIv2.setImageDrawable(MapExerciseActivity_1.this.getResources().getDrawable(R.mipmap.ic_hp_yd_99));
                    if (window != null && window.isShowing()) {
                        window.dismiss();
                    }
                }
                break;
            case R.id.iv_3:

                break;
            case R.id.iv_4:
                outRoom();
                break;
            case R.id.iv:
                if (StringUtil.isNotBlank(boxId)) {
                }
                break;
        }
    }

    private void getBoxReceive(String boxId) {
        RetrofitUtil.getInstance().apiService()
                .getBoxReceive(boxId, mapId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.getCode() == 0) {

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


    private void getMapDetails() {
        RetrofitUtil.getInstance().apiService()
                .getMapDetails(mapId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MapDetailsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MapDetailsBean> result) {
                        if (result.getCode() == 0 && result.getData() != null) {
//                            double distance = result.getData().getDistance();
//                            mTvDistance.setText(distance + "m");
//                            if (result.getData().getDistance() > 1000) {
//                                mTvDistance.setText(distance / 1000d + "km");
//                            }
                            if (result.getData().getInfo() != null && result.getData().getInfo().size() > 0) {
//                                initUi(result.getData().getInfo());
                            }
                            if (result.getData().getBoxs() != null && result.getData().getBoxs().size() > 0) {
//                                setMapBoxs(result.getData().getBoxs());
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

    private void initUi(List<List<Double>> info) {
        List<LatLng> latLngs = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            latLngs.add(new LatLng(info.get(i).get(1), info.get(i).get(0)));
        }
        latLngs.add(new LatLng(info.get(0).get(1), info.get(0).get(0)));
//      ????????????
        MarkerOptions mMarkerOptions = new MarkerOptions().position(latLngs.get(0));
        mMarkerOptions.icon(BitmapDescriptorFactory.
                fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_d_red)));
        aMap.addMarker(mMarkerOptions);

        //????????????
        aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(getResources().getColor(R.color.color_text_theme)));

        //?????? ???????????????
        setHD(latLngs);

        //??????????????????
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//???????????????????????????
        for (int i = 0; i < latLngs.size(); i++) {
            boundsBuilder.include(latLngs.get(i));//???????????????include?????????LatLng?????????
        }
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 100));//????????????????????????????????????

        window.showAtLocation(mLl, Gravity.BOTTOM, 0, 0); // ??????layout???PopupWindow??????????????????
    }

//    private void setMapBoxs(List<MapDetailsBean.BoxsBean> boxs) {
//        for (int i = 0; i < boxs.size(); i++) {
//            MapDetailsBean.BoxsBean box = boxs.get(i);
//            LatLng latLng = new LatLng(box.getLatlng().get(1), box.getLatlng().get(0));
//            MarkerOptions mMarkerOptions = new MarkerOptions().position(latLng);
//            mMarkerOptions.icon(BitmapDescriptorFactory.
//                    fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_boxs)));
//            mMarkerOptions.period(boxs.get(i).getId());
//            aMap.addMarker(mMarkerOptions);
//        }
//    }


    private void setHD(List<LatLng> latLngs) {
        // ?????????????????????
        LatLngBounds bounds = new LatLngBounds(latLngs.get(0), latLngs.get(latLngs.size() - 2));
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

        smoothMarker = new SmoothMoveMarker(aMap);
        // ?????????????????????
        smoothMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.mipmap.ic_my_dw));

        Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(latLngs, latLngs.get(0));
        latLngs.set(pair.first, latLngs.get(0));
        List<LatLng> subList = latLngs.subList(pair.first, latLngs.size());

        // ??????????????????????????????
        smoothMarker.setPoints(subList);
        // ????????????????????????
        smoothMarker.setTotalDuration(400);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //???activity??????onDestroy?????????mMapView.onDestroy()???????????????
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //???activity??????onResume?????????mMapView.onResume ()???????????????????????????
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //???activity??????onPause?????????mMapView.onPause ()????????????????????????
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //???activity??????onSaveInstanceState?????????mMapView.onSaveInstanceState (outState)??????????????????????????????
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        outRoom();
        return super.onKeyDown(keyCode, event);
    }

    private void outRoom() {
        DialogUtils.showDialogOutRoom(MapExerciseActivity_1.this, new DialogUtils.DialogLyInterface() {
            @Override
            public void btnConfirm() {
                finish();
            }
        });
    }

}
