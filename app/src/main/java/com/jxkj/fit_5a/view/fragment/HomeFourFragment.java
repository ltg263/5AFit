package com.jxkj.fit_5a.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.base.BaseFragment;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.base.UserDetailData;
import com.jxkj.fit_5a.base.UserInfoData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.GlideImageUtils;
import com.jxkj.fit_5a.conpoment.utils.GlideImgLoader;
import com.jxkj.fit_5a.conpoment.utils.HttpRequestUtils;
import com.jxkj.fit_5a.conpoment.utils.IntentUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedAssociationUtils;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;
import com.jxkj.fit_5a.conpoment.utils.ToastUtils;
import com.jxkj.fit_5a.conpoment.view.DialogUtils;
import com.jxkj.fit_5a.conpoment.view.PopupWindowTy;
import com.jxkj.fit_5a.conpoment.view.RoundImageView;
import com.jxkj.fit_5a.entity.MessageSubtypeBean;
import com.jxkj.fit_5a.entity.ParameterBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;
import com.jxkj.fit_5a.entity.UserOwnInfo;
import com.jxkj.fit_5a.entity.UserReportBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.view.activity.association.AssociationActivity;
import com.jxkj.fit_5a.view.activity.association.AssociationAddActivity;
import com.jxkj.fit_5a.view.activity.association.MineCircleActivity;
import com.jxkj.fit_5a.view.activity.home.WebViewActivity;
import com.jxkj.fit_5a.view.activity.mine.MineGiftActivity;
import com.jxkj.fit_5a.view.activity.mine.MineHomeActivity;
import com.jxkj.fit_5a.view.activity.mine.MineInfoActivity;
import com.jxkj.fit_5a.view.activity.mine.MineIntegralActivity;
import com.jxkj.fit_5a.view.activity.mine.MineJinDouActivity;
import com.jxkj.fit_5a.view.activity.mine.MineLwjActivity;
import com.jxkj.fit_5a.view.activity.mine.MineMessageActivity;
import com.jxkj.fit_5a.view.activity.mine.MineRegardsActivity;
import com.jxkj.fit_5a.view.activity.mine.MineRwzxActivity;
import com.jxkj.fit_5a.view.activity.mine.MineSetActivity;
import com.jxkj.fit_5a.view.activity.mine.MineVipActivity;
import com.jxkj.fit_5a.view.activity.mine.MineWzqActivity;
import com.jxkj.fit_5a.view.activity.mine.MineYhqActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingActivity;
import com.jxkj.fit_5a.view.activity.mine.ShoppingGwcActivity;
import com.jxkj.fit_5a.view.activity.mine.UserCgxActivity;
import com.jxkj.fit_5a.view.activity.mine.UserScActivity;
import com.jxkj.fit_5a.view.activity.mine.order.AddressActivity;
import com.jxkj.fit_5a.view.activity.mine.order.OrderActivity;
import com.jxkj.fit_5a.view.adapter.HomeDynamicAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFourFragment extends BaseFragment {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.iv_caogao)
    ImageView iv_caogao;
    @BindView(R.id.iv_backImg)
    ImageView mIvBackImg;
    @BindView(R.id.on_iv_set)
    ImageView mOnIvSet;
    @BindView(R.id.on_iv_msg)
    ImageView mOnIvMsg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.rl_actionbar1)
    RelativeLayout mRlActionbar1;
    @BindView(R.id.tv_level)
    TextView mTvLevel;
    @BindView(R.id.tv_level_sy)
    TextView mTvLevelSy;
    @BindView(R.id.on_bnt_zjxf)
    TextView mOnBntZjxf;
    @BindView(R.id.tv_nickName)
    TextView mTvNickName;
    @BindView(R.id.tv_explain)
    TextView mTvExplain;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.on_ll_mine_jd)
    LinearLayout mOnLlMineJd;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.on_ll_mine_jf)
    LinearLayout mOnLlMineJf;
    @BindView(R.id.tv_couponCount)
    TextView mTvCouponCount;
    @BindView(R.id.on_ll_mine_fpq)
    LinearLayout mOnLlMineFpq;
    @BindView(R.id.tv_giftCount)
    TextView mTvGiftCount;
    @BindView(R.id.on_ll_mine_lw)
    LinearLayout mOnLlMineLw;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.on_tv_mine_grzy)
    TextView mOnTvMineGrzy;
    @BindView(R.id.iv_avatar)
    RoundImageView mIvAvatar;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.on_ll_mine_qb)
    LinearLayout mOnLlMineQb;
    @BindView(R.id.on_ll_mine_dfk)
    LinearLayout mOnLlMineDfk;
    @BindView(R.id.on_ll_mine_dfh)
    LinearLayout mOnLlMineDfh;
    @BindView(R.id.on_ll_mine_dsh)
    LinearLayout mOnLlMineDsh;
    @BindView(R.id.on_ll_mine_dpj)
    LinearLayout mOnLlMineDpj;
    @BindView(R.id.on_ll_mine_rwzx)
    LinearLayout mOnLlMineRwzx;
    @BindView(R.id.on_ll_mine_wzq)
    LinearLayout mOnLlMineWzq;
    @BindView(R.id.iv_4)
    ImageView mIv4;
    @BindView(R.id.rl_jfsc)
    RelativeLayout mRlJfsc;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.on_ll_mine_zxkh)
    LinearLayout mOnLlMineZxkh;
    @BindView(R.id.on_ll_mine_shdz)
    LinearLayout mOnLlMineShdz;
    @BindView(R.id.on_ll_mine_qcbz)
    LinearLayout mOnLlMineQcbz;
    @BindView(R.id.on_ll_mine_cjwt)
    LinearLayout mOnLlMineCjwt;
    @BindView(R.id.on_ll_mine_gywm)
    LinearLayout mOnLlMineGywm;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.tv_gz)
    TextView mTvGz;
    @BindView(R.id.tv_fs)
    TextView mTvFs;
    @BindView(R.id.tv_doc_1)
    TextView tv_doc_1;
    @BindView(R.id.tv_doc)
    TextView tv_doc;
    @BindView(R.id.tv_title_qz)
    TextView tv_title_qz;
    @BindView(R.id.rv_dt_list)
    RecyclerView mRvDtList;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_four;
    }

    @Override
    protected void initViews() {

        mRlActionbar1.setAlpha(0);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY<=500){
                    mRlActionbar1.setAlpha((float) scrollY / 500.0f);
                }else{
                    mRlActionbar1.setAlpha(1);
                }
            }
        });
        getQueryByPublisher();
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserDetail();
        getUserStatistic();
        getParameter();
        getMessageSubtypeList();
    }
    private void getMessageSubtypeList() {
        RetrofitUtil.getInstance().apiService()
                .getMessageSubtypeList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<MessageSubtypeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<MessageSubtypeBean>> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData().size() > 0 ) {
                                int unReadCount = 0;
                                for(int i=0;i<result.getData().size();i++){
                                    unReadCount += Integer.parseInt(result.getData().get(i).getUnReadCount());
                                }
                                tv_doc.setVisibility(View.GONE);
                                tv_doc_1.setVisibility(View.GONE);
                                Log.w("unReadCount:","unReadCount:"+unReadCount);
                                if(unReadCount>0){
                                    tv_doc.setVisibility(View.VISIBLE);
                                    tv_doc_1.setVisibility(View.VISIBLE);
                                    tv_doc.setText(unReadCount+"");
                                    tv_doc_1.setText(unReadCount+"");
                                    if(unReadCount>99){
                                        tv_doc.setText("99+");
                                        tv_doc_1.setText("99+");
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
    @Override
    public void initImmersionBar() {
        getUserDetail();
        getUserStatistic();
    }

    public static HomeFourFragment newInstance() {
        HomeFourFragment homeFragment = new HomeFourFragment();
        return homeFragment;
    }

    @OnClick({R.id.iv_avatar, R.id.on_iv_set,R.id.on_iv_set_1, R.id.rl_jfsc, R.id.on_iv_msg, R.id.on_iv_msg_1,
            R.id.on_bnt_zjxf,R.id.on_ll_mine_lwj, R.id.on_ll_mine_jd, R.id.on_ll_mine_jf, R.id.on_ll_mine_fpq,
            R.id.on_ll_mine_lw, R.id.on_tv_mine_grzy, R.id.on_ll_mine_qb, R.id.on_ll_mine_dfk, R.id.on_ll_mine_dfh,
            R.id.on_ll_mine_dsh, R.id.on_ll_mine_dpj, R.id.on_ll_mine_rwzx, R.id.on_ll_mine_wzq, R.id.on_ll_mine_zxkh,
            R.id.on_ll_mine_gwc,
            R.id.on_ll_mine_shdz, R.id.on_ll_mine_qcbz, R.id.on_ll_mine_cjwt,R.id.tv_gerenzhuye, R.id.on_ll_mine_gywm,
            R.id.tv_mine_wdsc,R.id.tv_mine_cgx,R.id.iv_caogao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
            case R.id.tv_nickName:
            case R.id.tv_explain:
                startActivity(new Intent(getActivity(), MineInfoActivity.class));
                break;
            case R.id.on_iv_set:
            case R.id.on_iv_set_1:
                startActivity(new Intent(getActivity(), MineSetActivity.class));
                break;
            case R.id.on_iv_msg:
            case R.id.on_iv_msg_1:
                startActivity(new Intent(getActivity(), MineMessageActivity.class));
//                shareData();
                break;
            case R.id.on_ll_mine_gwc:
                IntentUtils.getInstence().intent(getActivity(), ShoppingGwcActivity.class);
                break;
            case R.id.on_bnt_zjxf:
                startActivity(new Intent(getActivity(), MineVipActivity.class));
                break;
            case R.id.on_ll_mine_jd:
                if(true){
                    ToastUtils.showShort("功能开发中敬请期待");
                    return;
                }
                startActivity(new Intent(getActivity(), MineJinDouActivity.class));
                break;
            case R.id.on_ll_mine_jf:
                startActivity(new Intent(getActivity(), MineIntegralActivity.class));
                break;
            case R.id.on_ll_mine_fpq:
                startActivity(new Intent(getActivity(), MineYhqActivity.class));
                break;
            case R.id.on_ll_mine_lw:
                if(true){//这周不能解决 我要去
                    ToastUtils.showShort("功能开发中敬请期待");
                    return;
                }
                startActivity(new Intent(getActivity(), MineGiftActivity.class));
                break;
            case R.id.on_tv_mine_grzy:
            case R.id.tv_gerenzhuye:
                startActivity(new Intent(getActivity(), MineHomeActivity.class));
                break;
            case R.id.rl_jfsc:
                ShoppingActivity.intentStartActivity(getActivity());
                break;
            case R.id.on_ll_mine_qb:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 0);
                break;
            case R.id.on_ll_mine_dfk:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 1);
                break;
            case R.id.on_ll_mine_dfh:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 2);
                break;
            case R.id.on_ll_mine_dsh:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 3);
                break;
            case R.id.on_ll_mine_dpj:
                IntentUtils.getInstence().intent(getActivity(), OrderActivity.class, "type", 4);
                break;
            case R.id.on_ll_mine_lwj:
                if(true){
                    ToastUtils.showShort("功能开发中敬请期待");
                    return;
                }
                startActivity(new Intent(getActivity(), MineLwjActivity.class));
                break;
            case R.id.on_ll_mine_rwzx:
                startActivity(new Intent(getActivity(), MineRwzxActivity.class));
                break;
            case R.id.on_ll_mine_wzq:
                startActivity(new Intent(getActivity(), MineWzqActivity.class));
                break;
            case R.id.on_ll_mine_zxkh:
                if(StringUtil.isBlank(ext1) || StringUtil.isBlank(paramValue)){
                    ToastUtils.showShort("客服信息获取失败");
                    return;
                }
                showEditTextKefuKuang(getActivity(),ext1,paramValue);
                break;
            case R.id.on_ll_mine_shdz:
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            case R.id.on_ll_mine_qcbz:
//                startActivity(new Intent(getActivity(), MineIssueQcActivity.class));

                WebViewActivity.startActivityIntent(getContext(), ConstValues.USER_QCBZ_URL,"器材帮助");
                break;
            case R.id.on_ll_mine_cjwt:
//                startActivity(new Intent(getActivity(), MineIssueActivity.class));
                WebViewActivity.startActivityIntent(getContext(), ConstValues.USER_CJWT_URL,"常见问题");
                break;
            case R.id.on_ll_mine_gywm:
                startActivity(new Intent(getActivity(), MineRegardsActivity.class));
                break;
            case R.id.tv_mine_wdsc:
                UserScActivity.startActivity(getActivity(), SharedUtils.getUserId() + "");
                break;
            case R.id.tv_mine_cgx:
//                UserCgxActivity.startActivity(getActivity(), SharedUtils.getUserId() + "");
//                List<QueryPopularBean> mData = SharedAssociationUtils.singleton().getSharedHistoryEquipment();
//                if(mData!=null && mData.size()>0){
//                    AssociationAddActivity.startActivityAddAssociation(
//                            getActivity(),Integer.parseInt(mData.get(0).getContentType()),mData.get(0).getCircleId()
//                            ,mData.get(0).getCircleName(),mData.get(0).getCircleIcon(),mData.get(0).getTopicArr());
//                }else{
//                    AssociationAddActivity.startActivityAddAssociation(getActivity(),3,"","","","");
//                }
//                break;
            case R.id.iv_caogao:
//                if(mQueryPopularBean!=null){
//                    AssociationActivity.startActivity(getActivity(),
//                            mQueryPopularBean.getCircleId(),mQueryPopularBean.getPublisherId(),mQueryPopularBean.getMomentId());
//                    return;
//                }
                if(window!=null){
                    window.showAtLocation(iv_caogao, Gravity.BOTTOM, 0, 0);
                    return;
                }
                initPopupWindow();
                break;
        }
    }

    public static void showEditTextKefuKuang(Activity mContext,String ext1, String paramValue) {
        DialogUtils.showEditTextKefuKuang(mContext,ext1,paramValue, new DialogUtils.EditTextDialogInterface() {
            @Override
            public void btnConfirm(String string) {
                if(string.equals("1")){
                    IntentUtils.getInstence().callPhone(mContext,ext1);
                }else{
                    shareDataText(mContext,paramValue);
                }
            }
        });
    }

    private static void shareDataText(Activity mContext, String paramValue) {
//        Bitmap bitmap= BitmapFactory.decodeFile(paramValue);
//        if(bitmap==null){
//            return;
//        }
        new ShareAction(mContext)
                .withMedia(new UMImage(mContext, paramValue))
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        ToastUtils.showShort("分享成功");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        ToastUtils.showShort("分享失败");
                    }
                }).open();
    }

    PopupWindowTy window;

    private void initPopupWindow() {
        List<String> list = new ArrayList<>();
        list.add("发布图文");
        list.add("发布视频");
        if (window == null) {
            window = new PopupWindowTy(getActivity(), list, new PopupWindowTy.GiveDialogInterface() {
                @Override
                public void btnConfirm(int position) {
                    if (position == 0) {
                        AssociationAddActivity.startActivityAddAssociation(getActivity(),2,"","","","");
                    }
                    if (position == 1) {
                        AssociationAddActivity.startActivityAddAssociation(getActivity(),3,"","","","");
                    }
                    window.dismiss();
                }
            });
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        window.showAtLocation(iv_caogao, Gravity.BOTTOM, 0, 0); // 设置layout在PopupWindow中显示的位置10464.66
    }
    private void getUserStatistic() {
        RetrofitUtil.getInstance().apiService()
                .getUserStatistic()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserInfoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserInfoData> result) {
                        if (isDataInfoSucceed(result)) {
                            UserInfoData data = result.getData();
                            if(data!=null){
                                mTvBalance.setText(data.getBalance());
                                mTvCouponCount.setText(data.getCouponCount());
                                mTvGiftCount.setText(data.getGiftCount());
                                mTvIntegral.setText(data.getIntegral());
                                SharedUtils.singleton().put(ConstValues.MY_BALANCE,data.getBalance()+"");
                                SharedUtils.singleton().put(ConstValues.MY_COUPON_COUNT,data.getCouponCount()+"");
                                SharedUtils.singleton().put(ConstValues.MY_GIFTCOUNT,data.getGiftCount()+"");
                                SharedUtils.singleton().put(ConstValues.MY_INTEGRAL,data.getIntegral()+"");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShortToast(getActivity(),"系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    String paramValue="";
    String ext1="";
    private void getParameter() {
        RetrofitUtil.getInstance().apiService()
                .getParameter("customer.service")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ParameterBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ParameterBean> result) {
                        if (result.getCode()==0) {
                            try {
                                JSONObject mObj = new JSONObject(result.getData().getParamValue());
                                paramValue = mObj.getString("paramValue");
                                ext1 = mObj.getString("ext1");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return;
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
                        if (isDataInfoSucceed(result) && result.getData()!=null) {
                            initUI(result.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShortToast(getActivity(),"系统异常" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        getUserProfileOwn();
    }

    private void getUserProfileOwn() {
        RetrofitUtil.getInstance().apiService()
                .getUserProfileOwn()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserOwnInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserOwnInfo> userOwnInfoResult) {
                        if (isDataInfoSucceed(userOwnInfoResult) && userOwnInfoResult.getData()!=null) {
                            mTvGz.setText(userOwnInfoResult.getData().getFollowCount());
                            mTvFs.setText(userOwnInfoResult.getData().getFansCount());
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
    private void initUI(UserDetailData data) {
        if(StringUtil.isNotBlank(data.getBackImg())){
            GlideImageUtils.setGlideImage(getActivity(),data.getBackImg(),mIvBackImg);
        }
        GlideImageUtils.setGlideImage(getActivity(),data.getAvatar(),mIvAvatar);
        mTvNickName.setText(data.getNickName());
        if(StringUtil.isNotBlank(data.getExplain())){
            mTvExplain.setText(data.getExplain());
            SharedUtils.singleton().put(ConstValues.USER_EXPLAIN,data.getExplain());
        }
        SharedUtils.singleton().put(ConstValues.USER_AGE,data.getAge());
        SharedUtils.singleton().put(ConstValues.USER_GENDER,data.getGender());
        SharedUtils.singleton().put(ConstValues.USER_IMG,data.getAvatar());
        SharedUtils.singleton().put(ConstValues.USER_LEVELNAME,data.getLevelName());


        if(StringUtil.isBlank(data.getLevelExpireTime())){
            return;
        }
        long endTime = StringUtil.getMsToTime(data.getLevelExpireTime(),"yyyy-MM-dd HH:mm:ss");
        long surrentMillis = System.currentTimeMillis();
        if(endTime > surrentMillis){
            double aa =  (endTime - surrentMillis)/ (1000*60*60*24);
            mTvLevel.setText("已开通会员");
            mTvLevelSy.setText("还有"+aa+"天到期");
            mOnBntZjxf.setText("立即续费");
        }
    }
    private void shareData(){
//        var web= UMWeb(HttpUtil.baseUrl+"html/h5page/?refererId=$id&mobile=$phone#/registered");
//        web.title="五黑鸡农场";
//        web.description="养赋生态,种植绿色,田赋浓情,食安天下"
//        web.setThumb(UMImage(this, R.mipmap.icon_all))

//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/test.jpg";
//        Log.w("path","path"+path);
//
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Bitmap bitmap = BitmapFactory.decodeStream(fis);
        //Log.e("TAG","--->"+"http://yqb.nbyjdz.com/shenlonggu/html/h5page/?refererId=$id&mobile=$phone#/registered")
        new ShareAction(getActivity())
                .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
//                .withMedia(new UMImage(getActivity(), bitmap))
                .withText("123456789")
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        ToastUtils.showShortToast(getActivity(),"分享成功");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        ToastUtils.showShortToast(getActivity(),"分享失败");
                    }
                }).open();
    }
    QueryPopularBean mQueryPopularBean;
    private HomeDynamicAdapter mHomeDynamicAdapter;
    private void getQueryByPublisher() {
        mHomeDynamicAdapter = new HomeDynamicAdapter(null);
        mRvDtList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDtList.setHasFixedSize(true);
        mRvDtList.setAdapter(mHomeDynamicAdapter);
        RetrofitUtil.getInstance().apiService()
                .getQueryByPublisherOwn("0", 2,ConstValues.PAGE_SIZE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<QueryPopularBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<QueryPopularBean> result) {
                        if (result.getCode() == 0 && result.getData()!=null && result.getData().size()>0) {
//                            iv_caogao.setVisibility(View.GONE);
                            tv_title_qz.setText("发布新鲜动态，让更多人认识您！");
//                            mRvDtList.setVisibility(View.VISIBLE);
//                            mHomeDynamicAdapter.setNewData(result.getData());
//                            mQueryPopularBean = result.getData().get(0);

//                            tv_title_qz.setText(mQueryPopularBean.getContent());
//                            if(StringUtil.isNotBlank(mQueryPopularBean.getMedia())){
//                                try {
//                                    JSONArray jsonArray = new JSONArray(mQueryPopularBean.getMedia());
//                                    if(jsonArray.length()>0){
//                                        String imageUrl = jsonArray.getJSONObject(0).getString("imageUrl");
//                                        GlideImgLoader.loadImageViewRadius(getContext(),imageUrl,10,iv_caogao);
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
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
}



