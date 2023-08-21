package com.jxkj.fit_5a.view.activity.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binioter.guideview.GuideBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GuideViewComponent;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.MatisseUtils;
import com.jxkj.fit_5a.conpoment.utils.PictureUtil;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ThirdLoginUtils;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.AddressPickTask;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.entity.LoginUserThirdInfo;
import com.jxkj.fit_5a.entity.VerifyAppOauthQq;
import com.jxkj.fit_5a.view.activity.exercise.landscape.MotorPatternActivity;
import com.jxkj.fit_5a.view.activity.login_other.SetUserXbActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_backImg)
    ImageView mIvBackImg;
    @BindView(R.id.tv_info_1)
    TextView mTvInfo1;
    @BindView(R.id.ll_info_1)
    LinearLayout mLlInfo1;
    @BindView(R.id.tv_info_2)
    TextView mTvInfo2;
    @BindView(R.id.ll_info_2)
    LinearLayout mLlInfo2;
    @BindView(R.id.tv_info_3)
    TextView mTvInfo3;
    @BindView(R.id.ll_info_3)
    LinearLayout mLlInfo3;
    @BindView(R.id.tv_info_4)
    TextView mTvInfo4;
    @BindView(R.id.ll_info_4)
    LinearLayout mLlInfo4;
    @BindView(R.id.iv_img)
    RoundImageView mIvImg;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv_info_5)
    TextView mTvInfo5;
    @BindView(R.id.ll_info_5)
    LinearLayout mLlInfo5;
    @BindView(R.id.tv_info_6)
    TextView mTvInfo6;
    @BindView(R.id.ll_info_6)
    LinearLayout mLlInfo6;
    @BindView(R.id.tv_info_7)
    TextView mTvInfo7;
    @BindView(R.id.ll_info_7)
    LinearLayout mLlInfo7;
    @BindView(R.id.tv_info_8)
    TextView mTvInfo8;
    @BindView(R.id.ll_info_8)
    LinearLayout mLlInfo8;
    @BindView(R.id.tv_info_9)
    TextView mTvInfo9;
    @BindView(R.id.ll_info_9)
    LinearLayout mLlInfo9;
    @BindView(R.id.tv_go_find)
    TextView mTvGoFind;
    @BindView(R.id.tv_jbzl)
    TextView mTvJbzl;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
        getUserBind();
    }

    private void getUserDetail() {
        RetrofitUtil.getInstance().apiService()
                .getUserDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserDetailData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserDetailData> result) {
                        if (isDataInfoSucceed(result)) {
                            initUI(result.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initUI(UserDetailData data) {
        avatar = data.getAvatar();
        backImg = data.getBackImg();
        SharedUtils.singleton().put(ConstValues.USER_BIRTHDAY,data.getBirthDay());
        SharedUtils.singleton().put(ConstValues.USER_GENDER,data.getGender());
        SharedUtils.singleton().put(ConstValues.USER_HEIGHT,data.getHeight());
        SharedUtils.singleton().put(ConstValues.USER_WEIGHT,data.getWeight());
        SharedUtils.singleton().put(ConstValues.USER_INTEREST,data.getInterest());
        SharedUtils.singleton().put(ConstValues.USER_EXPLAIN,data.getExplain());
        SharedUtils.singleton().put(ConstValues.USER_LEVELNAME,data.getLevelName());

        GlideImageUtils.setGlideImage(MineInfoActivity.this,backImg,mIvBackImg);
        GlideImageUtils.setGlideImage(MineInfoActivity.this,avatar,mIvImg);
        Glide.with(this)
                .asBitmap()
                .load(data.getBackImg())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
                        mRlActionbar.setBackground(drawable);
                    }

                });
        mTvInfo1.setText(data.getNickName());
        if(StringUtil.isNotBlank(data.getExplain())){
            mTvInfo2.setText(data.getExplain());
        }
        mTvInfo3.setText(data.getRegion());
        String gender = "未知";
        String height = "未知";
        String weight = "未知";
        if(StringUtil.isNotBlank(data.getGender())){
            gender = data.getGender().equals("1")?"男":"女";
        }
        if(StringUtil.isNotBlank(data.getHeight()) && Double.parseDouble(data.getHeight())>0){
            height = data.getHeight().replace(".00","")+"cm";
        }
        if(StringUtil.isNotBlank(data.getWeight())&& Double.parseDouble(data.getWeight())>0){
            weight = data.getWeight().replace(".00","")+"kg";
        }
        mTvJbzl.setText(gender+"/"+height+"/"+weight);
    }

    @OnClick({R.id.iv_back,R.id.ll_info_jbxx, R.id.ll_info_1, R.id.ll_info_2, R.id.ll_info_3, R.id.ll_info_4, R.id.rl, R.id.ll_info_5, R.id.ll_info_6, R.id.ll_info_7, R.id.ll_info_8, R.id.ll_info_9, R.id.tv_go_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
//                new GuideViewComponent(this, mTvInfo2, 1,new GuideViewComponent.GuideViewComponentListener() {
//                    @Override
//                    public void onClickView() {
//                        new GuideViewComponent(MineInfoActivity.this,mLlInfo4,3,null);
//                    }
//                });
                finish();
                break;
            case R.id.ll_info_jbxx:
                IntentUtils.getInstence().intent(this, SetUserXbActivity.class,"type",1);
                break;
            case R.id.rl:
                MatisseUtils.gotoSelectPhoto(this, 1, true);
                break;
            case R.id.ll_info_1:
//                DialogUtils.showEditTextDialog(this, 0,"修改昵称","输入昵称", season -> {
//                    mTvInfo1.setText(season);
//                    postUserUpdate();
//                });
                String inputText1 = mTvInfo1.getText().toString();
                DialogUtils.showUnificationDialogEditText(this, "修改昵称",
                        StringUtil.isNotBlank(inputText1)?inputText1:"请输入昵称",new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String str) {
                        mTvInfo1.setText(str);
                        postUserUpdate();
                    }
                });
                break;
            case R.id.ll_info_2:
//                DialogUtils.showEditTextDialog(this, 0,"设置签名","输入你的签名", season -> {
//                    mTvInfo2.setText(season);
//                    postUserUpdate();
//                });
                String inputText2 = mTvInfo2.getText().toString();
                DialogUtils.showUnificationDialogEditText(this, "设置签名", StringUtil.isNotBlank(inputText2)?inputText2:"输入你的签名",new DialogUtils.UnificationDialogInterface() {
                    @Override
                    public void bntClickListener(String str) {
                        mTvInfo2.setText(str);
                        postUserUpdate();
                    }
                });
                break;
            case R.id.ll_info_3:
                onAddressPicker();
                break;
            case R.id.ll_info_4:
                MatisseUtils.gotoSelectPhotoBl(this, 1, true);
                break;
            case R.id.ll_info_5:
//                DialogUtils.showEditTextDialog(this, 1,"绑定手机号","输入您的手机号", season -> {
//                    mTvInfo5.setText(season);
//                    userThirdBind(season);
//
//                });
                break;
            case R.id.ll_info_6:
//                DialogUtils.showEditTextDialog(this, 0,"绑定微信","输入您的微信", season -> {
//                    mTvInfo6.setText(season);
//                });
                break;
            case R.id.ll_info_7:

                if(!ThirdLoginUtils.mTencent.isQQInstalled(this)){
                    ToastUtils.showShort("未安装QQ");
                    return;
                }
                ThirdLoginUtils.onClickLoginQQweb(this, new ThirdLoginUtils.ThirdLoginInterface() {
                    @Override
                    public void loginInterface(String token) {
                        postVerifyAppOauth(token);
                    }

                    @Override
                    public void onCancel() {
                        dismiss();
                    }
                });
                break;
            case R.id.ll_info_8:
//                DialogUtils.showEditTextDialog(this, 0,"绑定微博","输入您的微博", season -> {
//                    mTvInfo8.setText(season);
//                });
                break;
            case R.id.ll_info_9:
//                DialogUtils.showEditTextDialog(this, 0,"绑定iconsole","输入您的iconsole", season -> {
//                    mTvInfo9.setText(season);
//                });
                break;
            case R.id.tv_go_find:
                postUserUpdate();
                break;
        }
    }


    String avatar = null;
    String backImg = null;
    private void postUserUpdate() {
        PostUser.UserInfoUpdate userInfoUpdate = new PostUser.UserInfoUpdate();
        userInfoUpdate.setAvatar(avatar);
        userInfoUpdate.setBackImg(backImg);
        userInfoUpdate.setNickName(mTvInfo1.getText().toString());
        userInfoUpdate.setExplain(mTvInfo2.getText().toString());
        userInfoUpdate.setRegion(mTvInfo3.getText().toString());
        RetrofitUtil.getInstance().apiService()
                .postUserUpdate(userInfoUpdate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
//                            MineInfoActivity.this.finish();
                            ToastUtils.showShort("保存成功");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void postVerifyAppOauth(String token) {
        RetrofitUtil.getInstance().apiService()
                .postVerifyAppOauth("qqweb",token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VerifyAppOauthQq>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VerifyAppOauthQq> result) {
                        if(isDataInfoSucceed(result)) {
                            ToastUtils.showShort("绑定成功");
                            mTvInfo7.setText("已绑定");
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

    private void getUserBind() {
        RetrofitUtil.getInstance().apiService()
                .getUserBind()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<LoginUserThirdInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<LoginUserThirdInfo>> result) {
                        if (isDataInfoSucceed(result)) {
                            for(int i= 0;i<result.getData().size();i++){
                                switch (result.getData().get(i).getName()){
                                    case "qqweb":
                                        mTvInfo7.setText("已绑定");
                                        break;
                                    case "iconsole":
                                        mTvInfo9.setText("已绑定");
                                        break;
                                    case "weixin":
                                        mTvInfo6.setText("已绑定");
                                        break;
                                    case "sina":
                                        mTvInfo8.setText("已绑定");
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {

            @Override
            public void onAddressInitFailed() {
                ToastUtils.showShort("数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    mTvInfo3.setText(province.getAreaName() + "," + city.getAreaName());
                } else {
                    mTvInfo3.setText(province.getAreaName() + "," + city.getAreaName() + "," + county.getAreaName());
                }
                postUserUpdate();
            }
        });
        task.execute("北京", "北京", "北京");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,ThirdLoginUtils.loginListener);
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        HttpRequestUtils.postOSSFile(1, new HttpRequestUtils.OSSClientInterface() {
                            @Override
                            public void succeed(double pos) {
                                if (pos == 0) {
                                    ToastUtils.showShort("获取oss信息错误");
                                    return;
                                }
                                postImg(selectList.get(0).getCompressPath(),0);
                            }
                        });
                    }
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> selectListbj = PictureSelector.obtainMultipleResult(data);
                    if (selectListbj.size() > 0) {
                        HttpRequestUtils.postOSSFile(1, new HttpRequestUtils.OSSClientInterface() {
                            @Override
                            public void succeed(double pos) {
                                if (pos == 0) {
                                    ToastUtils.showShort("获取oss信息错误");
                                    return;
                                }
                                postImg(selectListbj.get(0).getCompressPath(),1);
                            }
                        });
                    }
                    break;
            }
        }
    }


    private void postImg(final String listPath,int type) {
        String path = PictureUtil.compressBmpFileToTargetSize( new File(listPath), 1024 * 1024).getPath();
//        String path = selectList.get(i).getPath();
        String fileName = StringUtil.stringToMD5(path) + ".jpg";
        HttpRequestUtils.initOSSClient(this, fileName, path, new HttpRequestUtils.OSSClientInterface() {
            @Override
            public void succeed(double pos) {
                if (pos == 101) {
                    String urlpath = SharedUtils.singleton().get(ConstValues.host, "")
                            + "/" + SharedUtils.singleton().get(ConstValues.dir, "") + "/" + fileName;
                    Log.w("UploadSuccessnull","UploadSuccessnull:"+urlpath+"type:"+type);
                    if(type ==0){
                        avatar = urlpath;
                        Glide.with(MineInfoActivity.this).asBitmap().load(avatar)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                        Drawable drawable = new BitmapDrawable(resource);
                                        mIvImg.setImageDrawable(drawable);
                                    }
                                });
                        postUserUpdate();
                    }
                    if(type ==1){
                        backImg = urlpath;
                        Glide.with(MineInfoActivity.this).asBitmap().load(backImg)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                        Drawable drawable = new BitmapDrawable(resource);
                                        mRlActionbar.setBackground(drawable);
                                    }
                                });
                        postUserUpdate();
                    }
                }
            }
        });
    }
}
