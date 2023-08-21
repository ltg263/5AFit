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
        conf.setConnectionTimeout(30 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(30 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(8); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        // oss为全局变量，endpoint是一个OSS区域地址
        oss = new OSSClient(context, endpoint, credentialProvider, conf);
    }


    public void beginupload(Context context, String filename, String path) {

        if (path == null || path.equals("")) {
            LogUtil.d("请选择图片....");
            return;
        }
        Log.w("ppppppp","bucketName:"+bucketName);
        Log.w("ppppppp","dir+ File.separator+filename:"+(dir+ File.separator+filename));
        //下面3个参数依次为bucket名，Object名，上传文件路径
        PutObjectRequest put = new PutObjectRequest(bucketName, dir+ File.separator+filename, path);
        LogUtil.d("正在上传中....");

        // 异步上传，可以设置进度回调
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
                progressCallback.onProgressCallback(101);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                LogUtil.d("UploadFailure");
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    LogUtil.e("UploadFailure：表示向OSS发送请求或解析来自OSS的响应时发生错误。 例如，当网络不可用时，这个异常将被抛出");
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.e("UploadFailure：表示在OSS服务端发生错误");
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
//        task.cancel(); // 可以取消任务
//        task.waitUntilFinished(); // 可以等待直到任务完成
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
