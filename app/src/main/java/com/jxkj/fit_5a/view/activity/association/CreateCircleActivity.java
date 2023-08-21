package com.jxkj.fit_5a.view.activity.association;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.DeviceTypeData;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.PictureUtil;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PickerViewUtils;
import com.jxkj.fit_5a.entity.TaskCircleQueryBean;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateCircleActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_lefttext)
    TextView mTvLefttext;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.iv_bgImg)
    ImageView mIvBgImg;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.et1)
    EditText mEt1;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.et2)
    EditText mEt2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv_sb_type)
    TextView mTvSbType;
    @BindView(R.id.tv_sb_name)
    TextView mTvSbName;
    @BindView(R.id.tv4)
    TextView mTv4;
    @BindView(R.id.tv_mblx)
    TextView mTvMblx;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.tv_zqsc)
    TextView mTvZqsc;
    @BindView(R.id.tv5)
    TextView mTv5;
    @BindView(R.id.tv_v)
    TextView mTvV;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.tv_go_find)
    TextView mTvGoFind;
    int deviceType = -1;
    int circleTargetId = 0;
    private List<DeviceTypeData.ListBean> dataSbType;

    private List<String> zqscLists = new ArrayList<>();
    private TaskCircleQueryBean.ListBean.RewardDTOBean rewardDto;

    @Override
    protected int getContentView() {
        return R.layout.activity_create_circle;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("创建圈子");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        queryDeviceTypeLists();
    }


    @OnClick({R.id.ll_back, R.id.tv_go_find, R.id.tv_sb_name, R.id.iv_bgImg, R.id.tv_mblx, R.id.tv_zqsc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_go_find:
                getCircleCreate();
                break;
            case R.id.tv_sb_name:
                if (dataSbType == null || dataSbType.size() == 0) {
                    return;
                }
                List<String> list = new ArrayList<>();
                for (int i = 0; i < dataSbType.size(); i++) {
                    list.add(dataSbType.get(i).getName());
                }
                PickerViewUtils.selectorCustom(this, list, "设备类型", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        mTvMblx.setText("");
                        mTvZqsc.setText("");
                        mTvSbName.setText(dataSbType.get(pos).getName());
                        deviceType = dataSbType.get(pos).getId();
                    }
                });
                break;
            case R.id.tv_zqsc:
                if (zqscLists == null || zqscLists.size() == 0) {
                    return;
                }
                PickerViewUtils.selectorCustom(this, zqscLists, "选择周期", mTvZqsc);
                break;
            case R.id.iv_bgImg:
                MatisseUtils.gotoSelectPhoto(this, 1, true);
                break;
            case R.id.tv_mblx:
                getTaskCircleQuery();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        postImg(selectList.get(0));
                    }
                    break;
            }
        }
    }

    String avatar = null;

    private void postImg(final LocalMedia listPath) {
        show();
        HttpRequestUtils.postOSSFile(2, new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if (pos == 0) {
                    ToastUtils.showShortToast(CreateCircleActivity.this,"获取oss信息错误");
                    return;
                }
                String path = PictureUtil.compressBmpFileToTargetSize(new File(listPath.getCompressPath()), 1024 * 1024).getPath();
                double w = listPath.getWidth();
                double h = listPath.getHeight();
                String fileImgName = StringUtil.stringToMD5(path) + ".jpg";
                HttpRequestUtils.initOSSClient(CreateCircleActivity.this, fileImgName, path, new HttpRequestUtils.OSSClientInterface() {
                    @Override
                    public void succeed(double pos) {
                        if (pos == 101) {
                            CreateCircleActivity.this.dismiss();
                            avatar = SharedUtils.singleton().get(ConstValues.host,"")
                                    +"/"+SharedUtils.singleton().get(ConstValues.dir,"")+"/"+fileImgName+"?imageScale="+StringUtil.getValue(w/h);
                            Log.w("--:","avfater:"+avatar);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                GlideImgLoader.loadImageViewRadius(CreateCircleActivity.this, avatar, 10, mIvBgImg);
                                            }
                                        });
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }).start();
                        }
                    }
                });
            }

        });
    }

    private void getTaskCircleQuery() {
        RetrofitUtil.getInstance().apiService()
                .getTaskCircleQuery(deviceType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<TaskCircleQueryBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TaskCircleQueryBean> result) {
                        if (isDataInfoSucceed(result)) {
                            List<TaskCircleQueryBean.ListBean> data = result.getData().getList();
                            if(data==null || data.size()==0){
                                ToastUtils.showShortToast(CreateCircleActivity.this,"暂无可选运动任务，请选择其他其他设备类型");
                                return;
                            }
                            List<String> listMblx = new ArrayList<>();
                            for (int i = 0; i < data.size(); i++) {
                                listMblx.add(data.get(i).getParamName());
                            }
                            PickerViewUtils.selectorCustom(CreateCircleActivity.this, listMblx, "目标类型",
                                    new PickerViewUtils.ConditionInterfacd() {
                                        @Override
                                        public void setIndex(int pos) {
                                            circleTargetId = data.get(pos).getId();
                                            mTvMblx.setText(data.get(pos).getParamName());
                                            String det = data.get(pos).getRewardDTO().getDetail();
                                            try {
                                                int object = new JSONObject(det).getInt("maxValue");
                                                mTvV.setText(object+"");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            zqscLists.clear();
                                            rewardDto = data.get(pos).getRewardDTO();
                                            for (int i = data.get(pos).getMinCycle(); i < data.get(pos).getMaxCycle() + 1; i++) {
                                                zqscLists.add(i + "");
                                            }
                                            Log.w("zqscLists:", "circleTargetId" + circleTargetId);
                                        }
                                    });
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
                            dataSbType = result.getData().getList();
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

    private void getCircleCreate() {
        if (StringUtil.isBlank(mEt1.getText().toString())
                || StringUtil.isBlank(mEt2.getText().toString())
                || StringUtil.isBlank(mTvZqsc.getText().toString())
                || StringUtil.isBlank(avatar)) {
            ToastUtils.showShortToast(CreateCircleActivity.this,"信息不完整");
            return;
        }
        show();
        PostUser.CreateCircle postUser = new PostUser.CreateCircle();
        postUser.setBgImg(avatar);
        postUser.setDeviceType(deviceType);
        postUser.setName(mEt1.getText().toString());
        postUser.setExplain(mEt2.getText().toString());
        postUser.setImgUrl(avatar);
        postUser.setInterestId(0);
        PostUser.CreateCircle.TaskCircleFormDTOBean dtoBean = new PostUser.CreateCircle.TaskCircleFormDTOBean();
        dtoBean.setCircleTargetId(circleTargetId);
        dtoBean.setCycle(Integer.valueOf(mTvZqsc.getText().toString()));//mTvZqsc.getText().toString()
        dtoBean.setImgUrl(rewardDto.getImgUrl());
        dtoBean.setExplain(rewardDto.getExplain());
        postUser.setTaskCircleFormDTO(dtoBean);
        Log.w("postUser","postUser:"+postUser.toString());
        RetrofitUtil.getInstance().apiService()
                .getCircleCreate(postUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        dismiss();
                        if (isDataInfoSucceed(result)) {
                            DialogUtils.showDialogCgCircle(CreateCircleActivity.this,
                                    "创建圈子权限", 1, new DialogUtils.DialogLyInterface() {
                                        @Override
                                        public void btnConfirm() {
                                            CreateCircleActivity.this.finish();
                                        }
                                    });
                        }else{
                            ToastUtils.showShortToast(CreateCircleActivity.this,result.getMesg());
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
}
