package com.jxkj.fit_5a.precache;

import android.util.Log;


import com.yw.library.videocache.HttpProxyCacheServer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;

public class PreloadTask implements Runnable {
    private static final String TAG = "PreloadTask";
    /**
     * 原始地址
     */
    public String mRawUrl;

    /**
     * 列表中的位置
     */
    public int mPosition;

    /**
     * VideoCache服务器
     */
    public HttpProxyCacheServer mCacheServer;

    /**
     * 是否被取消
     */
    private boolean mIsCanceled;

    /**
     * 是否正在预加载
     */
    private boolean mIsExecuted;

    @Override
    public void run() {
        Log.i(TAG,"mIsCanceled：" + mIsCanceled);
        if (!mIsCanceled) {
            start();
        }
        mIsExecuted = false;
        mIsCanceled = false;
    }

    /**
     * 开始预加载
     */
    private void start() {
        Log.i(TAG,"开始预加载：" + mPosition);
        HttpURLConnection connection = null;
        try {
            //获取HttpProxyCacheServer的代理地址
            String proxyUrl = mCacheServer.getProxyUrl(mRawUrl);
            Log.e("预加载的路径：",proxyUrl);
            URL url = new URL(proxyUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            InputStream in = new BufferedInputStream(connection.getInputStream());
            int length;
            int read = -1;
            byte[] bytes = new byte[8 * 1024];
            while ((length = in.read(bytes)) != -1) {
                read += length;
                //预加载完成或者取消预加载
                if (mIsCanceled || read >= PreloadManager.PRELOAD_LENGTH) {
                    Log.i(TAG,"结束预加载：" + mPosition);
                    break;
                }
            }
            if (read == -1) { //这种情况一般是预加载出错了，删掉缓存
                Log.i(TAG,"预加载失败：" +  mPosition);
                File cacheFile = mCacheServer.getCacheFile(mRawUrl);
                if (cacheFile.exists()) {
                    cacheFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG,"异常结束预加载：" + mPosition+"|"+e.getMessage()+"\n"+mCacheServer.getProxyUrl(mRawUrl));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 将预加载任务提交到线程池，准备执行
     */
    public void executeOn(ExecutorService executorService) {
        if (mIsExecuted) return;
        mIsExecuted = true;
        executorService.submit(this);
    }

    /**
     * 取消预加载任务
     */
    public void cancel() {
        if (mIsExecuted) {
            mIsCanceled = true;
        }
    }
}
