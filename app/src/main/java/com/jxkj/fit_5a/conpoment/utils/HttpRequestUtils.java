package com.jxkj.fit_5a.conpoment.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.vod.upload.VODUploadCallback;
import com.alibaba.sdk.android.vod.upload.VODUploadClientImpl;
import com.alibaba.sdk.android.vod.upload.model.UploadFileInfo;
import com.alibaba.sdk.android.vod.upload.model.VodInfo;
import com.jxkj.fit_5a.MainActivity;
import com.jxkj.fit_5a.R;
import com.jxkj.fit_5a.api.ApiService;
import com.jxkj.fit_5a.api.RetrofitUtil;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.BaseActivity;
import com.jxkj.fit_5a.base.BlackListBean;
import com.jxkj.fit_5a.base.PostUser;
import com.jxkj.fit_5a.base.Result;
import com.jxkj.fit_5a.base.ResultList;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.entity.CommentMomentBean;
import com.jxkj.fit_5a.entity.LoginInfo;
import com.jxkj.fit_5a.entity.MomentDetailsBean;
import com.jxkj.fit_5a.entity.OssInfoBean;
import com.jxkj.fit_5a.entity.ParameterBean;
import com.jxkj.fit_5a.entity.StsTokenBean;
import com.jxkj.fit_5a.entity.SubmitFilesBean;
import com.jxkj.fit_5a.entity.TemplateBean;
import com.jxkj.fit_5a.entity.VideoInfoBean;
import com.jxkj.fit_5a.entity.VideoPlayInfoBean;
import com.jxkj.fit_5a.precache.PreloadManager;
import com.jxkj.fit_5a.view.activity.login_other.LoginActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yw.library.videocache.CacheListener;
import com.yw.library.videocache.HttpProxyCacheServer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constant.UiType;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import listener.Md5CheckResultListener;
import listener.UpdateDownloadListener;
import model.UiConfig;
import model.UpdateConfig;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import update.UpdateAppUtils;

public class HttpRequestUtils {

    public static void okGo(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
//            Toast.makeText(mContext, "当前网络可用", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "当前网络不可用", Toast.LENGTH_SHORT).show();
        }
    }
    public static void getVerifyAppEmpower(Context mContext, String accessToken, String openId, String registrationId, String inviteCode, LoginInterface loginInterface) {
        RetrofitUtil.getInstance().apiService()
                .verifyAppEmpower(accessToken, openId, 3, registrationId, inviteCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
//                        if (result.getStatus() == 0) {
//                            SharedUtils.singleton().put(ConstValues.TOKEN,result.getData().getToken());
//                            SharedUtils.singleton().put(ConstValues.USERID,result.getData().getId());
//                            loginInterface.succeed(result.getData());
//                        }else{
////                            Toast.makeText(mContext,result.get(),Toast.LENGTH_LONG).show();
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public interface LoginInterface {
        void succeed(String path);
//        void failure();
    }

    public static void uploadFiles(String filePath,UploadFileInterface fileInterface) {
        if(StringUtil.isBlank(filePath)){
            fileInterface.succeed("-1");
            return;
        }
        File file = new File(filePath);
        Map<String, RequestBody> map = new HashMap<>();
//        map.put("dirtype", toRequestBody("3"));//头像：3，申诉 ：2 ，收款码：1
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的name是用file
        MultipartBody.Part body = null;
        try {
            body = MultipartBody.Part.createFormData("file",  URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RetrofitUtil.getInstance().apiService()
                .submitFiles(body, map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SubmitFilesBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SubmitFilesBean> result) {
                        if (result.getCode()==0  && result.getData()!=null
                                && StringUtil.isNotBlank(result.getData().getUrl())) {
                            fileInterface.succeed(result.getData().getUrl());
                        }else{
                            fileInterface.failure();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        fileInterface.failure();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public interface UploadFileInterface{
        void succeed(String path);
        void failure();
    }

    public static void userVerifyLogin() {
        String registrationId = SharedUtils.singleton().get("registrationId","");
        Log.w("registrationId","registrationId:"+registrationId);
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<LoginInfo>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.userVerifyLogin_al(3,SharedUtils.singleton().get(ConstValues.USER_PHONE,""),
                    SharedUtils.singleton().get(ConstValues.USER_PASSWORD,""),registrationId,null);
        }else {
            mObservable = mApiService.userVerifyLogin(3,SharedUtils.singleton().get(ConstValues.USER_PHONE,""),
                    SharedUtils.singleton().get(ConstValues.USER_PASSWORD,""),registrationId,null);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginInfo> result) {
                        if(result.getCode()==0){
                            SharedUtils.singleton().put(ConstValues.TOKEN,"Bearer "+result.getData().getTokenId());
                            LoginActivity.saveUserInfo(result.getData());
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

    public static void psotUserSportLog(PostUser.SportLogInfo sportLogInfo,LoginInterface mLoginInterface) {
        if(StringUtil.isBlank(sportLogInfo.getCalories())|| Double.parseDouble(sportLogInfo.getCalories())==0){
            mLoginInterface.succeed("");
            return;
        }
        Log.w("-------------->>>>>","sportLogInfo:"+sportLogInfo.toString());
        ApiService mApiService = RetrofitUtil.getInstance().apiService();
        Observable<Result<String>> mObservable;
        if(StringUtil.getLoginUserType().equals("1")){
            mObservable = mApiService.psotUserSportLog_al(sportLogInfo);
        }else {
            mObservable = mApiService.psotUserSportLog(sportLogInfo);
        }
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed(result.getData());
                            return;
                        }
                        mLoginInterface.succeed("");

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("");
                    }

                    @Override
                    public void onComplete() {
                    }
                });


    }

    /**
     * 去关注
     * @param followerId
     * @param mLoginInterface
     */
    public static void postfollow(String followerId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postfollow(followerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 取消去关注
     * @param followerId
     * @param mLoginInterface
     */
    public static void postfollowCancel(String followerId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postfollowCancel(followerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("1");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 去收藏
     */
    public static void postFavorit(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postFavorit(circleId,momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消收藏
     */
    public static void postFavoritCancel(String circleId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postFavoritCancel(circleId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 去收藏
     */
    public static void postFavorit_gf(String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postFavorit_gf(momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消收藏
     */
    public static void postFavoritCancel_gf(String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postFavoritCancel_gw(momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 去点赞
     */
    public static void postLike(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLike(circleId,momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消点赞
     */
    public static void postLikeCancel(String circleId,String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLikeCancel(circleId,momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 去点赞
     */
    public static void postLike_gf(String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLike_gf(momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 取消点赞
     */
    public static void postLikeCancel_gf(String momentId,String momentPublisherId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postLikeCancel_gf(momentId,momentPublisherId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void postBlackList(Activity mActivity, LoginInterface mLoginInterface) {
        if (mActivity instanceof BaseActivity) {
            ((BaseActivity)mActivity).show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getBlackList(mActivity,mLoginInterface);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void getBlackList(Activity mActivity,LoginInterface mLoginInterface) {

        RetrofitUtil.getInstance().apiService()
                .getBlackList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<BlackListBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<BlackListBean>> result) {
                        if (mActivity instanceof BaseActivity) {
                            ((BaseActivity)mActivity).dismiss();
                        }
                        if(result.getCode()==0){
                            if(MainActivity.mBlackListBean!=null){
                                MainActivity.mBlackListBean.clear();
                            }
                            MainActivity.mBlackListBean = result.getData();
                            MainActivity.mBlackListId = new ArrayList<>();
                            for(int i=0;i<MainActivity.mBlackListBean.size();i++){
                                MainActivity.mBlackListId.add(MainActivity.mBlackListBean.get(i).getBlUserId());
                            }

                            if(mLoginInterface!=null){
                                mLoginInterface.succeed("0");
                            }
                        }else{
                            if(mLoginInterface!=null){
                                mLoginInterface.succeed("-1");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (mActivity instanceof BaseActivity) {
                            ((BaseActivity)mActivity).dismiss();
                        }
                        if(mLoginInterface!=null){
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mActivity instanceof BaseActivity) {
                            ((BaseActivity)mActivity).dismiss();
                        }
                    }
                });
    }


    /**
     * 用户点赞动态评论
     */
    public static void postCommentLike(String commentId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentLike(commentId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 用户点赞动态评论
     */
    public static void postCommentLike_gf(String commentId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentLike_gf(commentId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /**
     * 用户取消点赞动态评论
     */
    public static void postCommentLikeCancel(String commentId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentLikeCancel(commentId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 用户取消点赞动态评论
     */
    public static void postCommentLikeCancel_gf(String commentId,String momentId,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentLikeCancel_gf(commentId,momentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    /**
     * 用户发布动态评论
     */
    public static void postCommentMoment(String circleId,String content,String momentId,String momentPublisherId,
                                         String replyCommentId,int contentType,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentMoment(circleId,content,contentType,momentId,momentPublisherId,replyCommentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 用户发布动态评论
     */
    public static void postCommentMoment_gf(String content,String momentId,String momentPublisherId,
                                         String replyCommentId,int contentType,LoginInterface mLoginInterface) {
        RetrofitUtil.getInstance().apiService()
                .postCommentMoment_gf(content,contentType,momentId,momentPublisherId,replyCommentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getCode()==0){
                            mLoginInterface.succeed("0");
                        }else{
                            mLoginInterface.succeed("-1");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginInterface.succeed("-1");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取动态下评论信息
     */
    public static void getCommentMoment(String commentOrderType,String circleId,String momentId,String momentPublisherId,int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentMoment(commentOrderType,circleId,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取动态下评论信息
     */
    public static void getCommentMoment_gf(String commentOrderType,String momentId,String momentPublisherId,int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentMoment_gf(commentOrderType,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 获取评论下的评论信息(回复评论的评论)--圈子
     */
    public static void getCommentQueryReply(String circleId,String commentId,String momentId,String momentPublisherId,
                                            int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentQueryReply(circleId,commentId,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 获取评论下的评论信息(回复评论的评论)--圈子
     */
    public static void getCommentQueryReply_gf(String commentId,String momentId,String momentPublisherId,
                                            int page,int pageSize,ResultInterface mResultInterface) {
        RetrofitUtil.getInstance().apiService()
                .getCommentQueryReply_gf(commentId,momentId,momentPublisherId,page,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList<CommentMomentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultList<CommentMomentBean> result) {
                        mResultInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ResultInterface {
        void succeed(ResultList<CommentMomentBean> result);
//        void failure();
    }
    public interface OSSClientInterface {
        void succeed(double pos);
//        void failure();
    }

    /**
     *
     * @param mResultInterface
     * @param type :0 用户相关 1动态相关 2圈子相关 3商城商品相关 4商城商品评论相关
     *             5商品封面(商品列表) 6商品轮播图 7商城商品详情 8商城商品规格封面 9商城商品分类
     *             10礼物 11任务 12勋章 13兴趣 14帮助 15广告 16运动
     */
    public static void postOSSFile(int type,OSSClientInterface mResultInterface){
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
                            getOssInfo(mResultInterface,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static void getOssInfo(OSSClientInterface mResultInterface, int type){
        RetrofitUtil.getInstance().apiService()
                .getOssInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<OssInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<OssInfoBean> result) {
                        if(result.getCode()==0) {
                            OssInfoBean data = result.getData();
                            SharedUtils.singleton().put(ConstValues.endpoint,data.getEndpoint());
                            SharedUtils.singleton().put(ConstValues.host,data.getHost());
                            SharedUtils.singleton().put(ConstValues.bucketName,data.getBucket());
                            SharedUtils.singleton().put(ConstValues.dir,data.getDirUnits().get(type).getDir());
                            mResultInterface.succeed(1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mResultInterface.succeed(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void getUploadVideo(String fileName,String title,String coverUrl,VideoInterface videoInterface){
        RetrofitUtil.getInstance().apiService()
                .getUploadVideo(fileName,title,coverUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VideoInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VideoInfoBean> result) {
                        if(result.getCode()==0) {
                            videoInterface.succeed(result.getData());
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
    public interface VideoInterface {
        void succeed(VideoInfoBean result);
//        void failure();
    }
    /**
     * 创建请求体
     *
     * @param value
     * @return
     */
    public static  RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
    public static void getUpload_Video(String filePath,String fileName,String title,String coverUrl){

        File file = new File(filePath);
        //String fileName, @Query("title") String title, @Query("coverUrl") String coverUrl
        Map<String, RequestBody> map = new HashMap<>();
        map.put("fileName", toRequestBody(fileName));
        map.put("title", toRequestBody(title));
        map.put("coverUrl", toRequestBody(coverUrl));//头像：3，申诉 ：2 ，收款码：1
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的name是用file
        MultipartBody.Part body = null;
        try {
            body = MultipartBody.Part.createFormData("file",  URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RetrofitUtil.getInstance().apiService()
                .getUpload_Video(body,map)
                .observeOn(AndroidSchedulers.mainThread())
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


    public static void initOSSClient(Context mContext,  String fileName,String filePath, OSSClientInterface mResultInterface){
        //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
        // String accessKeyId, String accessKeySecret, String endpoint,String bucketName,String dir, String SecurityToken
        OssService ossService = new OssService(mContext);
        //初始化OSSClient
        ossService.initOSSClient();
        //开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath

        ossService.beginupload(mContext, fileName, filePath);
        //上传的进度回调
        ossService.setProgressCallback(new OssService.ProgressCallback() {
            @Override
            public void onProgressCallback(final double progress) {
                mResultInterface.succeed(progress);
            }
        });
    }

    public static void initAcc(Context mContext,String filePath,String uploadAuth, String uploadAddress,String coverUrl) {
        VODUploadClientImpl uploader = new VODUploadClientImpl(mContext);
        VODUploadCallback callback = new VODUploadCallback() {

            @Override
            public void onUploadSucceed(UploadFileInfo info) {
                super.onUploadSucceed(info);
                System.out.println("onsucceed ------------------上传完成回调" + info.getFilePath());
            }

            @Override
            public void onUploadFailed(UploadFileInfo info, String code, String message) {
                super.onUploadFailed(info, code, message);
                System.out.println("onfailed ------------------ 上传失败回调 " + info.getFilePath() + " " + code + " " + message);
            }

            @Override
            public void onUploadProgress(UploadFileInfo info, long uploadedSize, long totalSize) {
                super.onUploadProgress(info, uploadedSize, totalSize);
                System.out.println("onProgress ------------------上传进度回调 " + info.getFilePath() + " " + uploadedSize + " " + totalSize);
            }

            @Override
            public void onUploadTokenExpired() {
                super.onUploadTokenExpired();
                System.out.println("onExpired ------------- token过期回调");
                //重新刷新上传凭证：RefreshUploadVideo
//                uploadAuth = "此处需要设置重新刷新凭证之后的值";
//                uploader.resumeWithAuth(uploadAuth);
            }

            @Override
            public void onUploadRetry(String code, String message) {
                super.onUploadRetry(code, message);
                System.out.println("onUploadRetry ------------- 上传开始重试回调");
            }

            @Override
            public void onUploadRetryResume() {
                super.onUploadRetryResume();
                System.out.println("onUploadRetryResume ------------- 上传结束重试，继续上传回调");
            }

            /**
             *
             * @param uploadFileInfo
             */
            @Override
            public void onUploadStarted(UploadFileInfo uploadFileInfo) {
                super.onUploadStarted(uploadFileInfo);
                System.out.println("onUploadStarted ------------- 开始上传回调");
                uploader.setUploadAuthAndAddress(uploadFileInfo, uploadAuth, uploadAddress);

            }
        };
        //上传初始化
        uploader.init(callback);

        File file = new File(filePath);
        System.out.println(file+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        VodInfo vodInfo = new VodInfo();
        vodInfo.setTitle("动态视频_Android");
        vodInfo.setDesc("描述信息");
        vodInfo.setCateId (0);
        vodInfo.setCoverUrl(coverUrl);
        vodInfo.setIsProcess(true);
//        String endpoint = "http://"+SharedUtils.singleton().get(ConstValues.endpoint,"");
//        String bucket = SharedUtils.singleton().get(ConstValues.bucketName,"");
//        uploader.addFile(filePath,endpoint,bucket,"123",vodInfo);
        uploader.addFile(filePath,vodInfo);
        uploader.start();

    }

    public static void getVersionUpdating(Context mContext,UploadFileInterface fileInterface) {
        RetrofitUtil.getInstance().apiService()
                .getParameter("android.url")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<ParameterBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ParameterBean> result) {
                        if (result.getCode()==0) {
                            goUpdating(mContext,result.getData(),fileInterface);
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
    public static void goUpdating(Context mContext, ParameterBean data, UploadFileInterface fileInterface) {
        if(data==null || StringUtil.isBlank(data.getParamValue())){
            fileInterface.failure();
            return;
        }
        String paramValue="";
        String ext1="";
        String ext2="";
        try {
            JSONObject mObj = new JSONObject(data.getParamValue());
            paramValue = mObj.getString("paramValue");
            ext1 = mObj.getString("ext1");
            ext2 = mObj.getString("ext2");
        } catch (JSONException e) {
            fileInterface.failure();
            e.printStackTrace();
            return;
        }

        if(ext1.equals(StringUtil.getVersionName(mContext)) || StringUtil.isBlank(paramValue)){
            fileInterface.failure();
            return;
        }
        UpdateAppUtils.init(mContext);
        UpdateConfig updateConfig = new UpdateConfig();
        updateConfig.setCheckWifi(true);
        updateConfig.setNeedCheckMd5(false);
//        updateConfig.setAlwaysShowDownLoadDialog(true);
        updateConfig.setNotifyImgRes(R.mipmap.ic_launcher);
        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.PLENTIFUL);
        uiConfig.setUpdateLogoImgRes(R.mipmap.ic_launcher);
        uiConfig.setUpdateBtnBgRes(R.drawable.btn_shape_bj_theme_25);
        UpdateAppUtils
                .getInstance()
                .apkUrl(paramValue)
                .updateTitle("发现新版本:V"+ext1)
                .updateContent(ext2)
                .uiConfig(uiConfig)
                .updateConfig(updateConfig)
                .setMd5CheckResultListener(new Md5CheckResultListener() {
                    @Override
                    public void onResult(boolean result) {

                    }
                })
                .setUpdateDownloadListener(new UpdateDownloadListener() {
                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onDownload(int progress) {

                    }

                    @Override
                    public void onFinish() {

                    }

                })
                .update();
    }



    public interface PlayInfoInterface {
        void succeed(Result<VideoPlayInfoBean> result);
//        void failure();
    }

    public static void getPlayInfo(Context mContext,String videoId,PlayInfoInterface mPlayInfoInterface) {
        RetrofitUtil.getInstance().apiService()
                .getPlay_info(null, videoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<VideoPlayInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<VideoPlayInfoBean> result) {
//                        List<VideoPlayInfoBean.PlayInfoListBean> info = result.getData().getPlayInfoList();
//                        String playUrl = initVideo(mContext,info.get(0).getPlayURL(),videoId);
                        mPlayInfoInterface.succeed(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public static String initVideo(Context mContext,String playURL,String videoId) {
        HttpProxyCacheServer proxy = MainApplication.getProxy(mContext,videoId);
        //1.我们会将原始url注册进去
        proxy.registerCacheListener(new CacheListener() {
            @Override
            public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
            }
        }, playURL);
        //2.我们播放视频的时候会调用以下代码生成proxyUrl
        String proxyUrl = proxy.getProxyUrl(playURL);
        if (proxy.isCached(playURL)) {
            Log.i("--->>>>>>>>>>>>>:", "已缓存");
        } else {
            PreloadManager.getInstance(mContext).addPreloadTask(proxyUrl,0);
            Log.i("--->>>>>>>>>>>>>:", "未缓存");
        }
        return proxyUrl;
    }
    public static void shareDataText(Activity mContext,String umWeb,String imgUrl, String title,String description){
        UMWeb web = new UMWeb(umWeb);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(mContext,imgUrl));  //缩略图，可以本地图片，也可以网络图片
        web.setDescription(description);//描述
        new ShareAction(mContext)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
//                        ToastUtil.showToast("分享成功");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
//                        ToastUtil.showToast("分享失败");
                    }
                }).open();
    }
}
