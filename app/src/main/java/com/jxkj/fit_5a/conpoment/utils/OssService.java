package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;

import java.io.File;

import cn.forward.androids.utils.LogUtil;

public class OssService {

    private OSS oss;
    private String accessKeyId;
    private String bucketName;
    private String dir;
    private String accessKeySecret;
    private String SecurityToken;
    private String endpoint;
    private Context context;

    private ProgressCallback progressCallback;

    public OssService(Context context) {
        this.context = context;
        this.endpoint = "http://"+SharedUtils.singleton().get(ConstValues.endpoint,"");
        this.bucketName = SharedUtils.singleton().get(ConstValues.bucketName,"");
        this.dir = SharedUtils.singleton().get(ConstValues.dir,"");
        this.accessKeyId = SharedUtils.singleton().get(ConstValues.accessKeyId,"");
        this.accessKeySecret = SharedUtils.singleton().get(ConstValues.accessKeySecret,"");
        this.SecurityToken = SharedUtils.singleton().get(ConstValues.SecurityToken,"");
    }


    public void initOSSClient() {

        OSSStsTokenCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, SecurityToken);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(30 * 1000); // ?????????????????????15???
        conf.setSocketTimeout(30 * 1000); // socket???????????????15???
        conf.setMaxConcurrentRequest(8); // ??????????????????????????????5???
        conf.setMaxErrorRetry(2); // ????????????????????????????????????2???
        // oss??????????????????endpoint?????????OSS????????????
        oss = new OSSClient(context, endpoint, credentialProvider, conf);
    }


    public void beginupload(Context context, String filename, String path) {

        if (path == null || path.equals("")) {
            LogUtil.d("???????????????....");
            return;
        }
        Log.w("ppppppp","bucketName:"+bucketName);
        Log.w("ppppppp","dir+ File.separator+filename:"+(dir+ File.separator+filename));
        //??????3??????????????????bucket??????Object????????????????????????
        PutObjectRequest put = new PutObjectRequest(bucketName, dir+ File.separator+filename, path);
        LogUtil.d("???????????????....");

        // ???????????????????????????????????????
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                double progress = currentSize * 1.0 / totalSize * 100.f;
                if (progressCallback != null) {
                    progressCallback.onProgressCallback(progress);
                }
            }
        });
        @SuppressWarnings("rawtypes")
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LogUtil.d("UploadSuccess"+request.getUploadFilePath());
                LogUtil.d("UploadSuccess"+request.getUploadUri());
                progressCallback.onProgressCallback(101);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // ????????????
                LogUtil.d("UploadFailure");
                ToastUtils.showShort("UploadFailure");
                if (clientExcepion != null) {
                    // ??????????????????????????????
                    LogUtil.e("UploadFailure????????????OSS???????????????????????????OSS??????????????????????????? ?????????????????????????????????????????????????????????");
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // ????????????
                    LogUtil.e("UploadFailure????????????OSS?????????????????????");
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
//        task.cancel(); // ??????????????????
//        task.waitUntilFinished(); // ??????????????????????????????
    }


    public ProgressCallback getProgressCallback() {
        return progressCallback;
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    public interface ProgressCallback {
        void onProgressCallback(double progress);
    }

}
