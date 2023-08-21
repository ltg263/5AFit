package com.jxkj.fit_5a.precache;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: u8-app-android
 * @Package: com.danikula.videocache
 * @ClassName: VideoPreLoader
 * @Description: AndroidVideoCache+VideoPreLoader实现短视频预加载，这里为了满足目前的紧急续期，先做一下简单处理。ps：缓存本地+预加载
 * 参考链接：https://www.jianshu.com/p/590c3f67d395、https://www.jianshu.com/p/4745de02dcdc
 * @Author: wei.yang
 * @CreateDate: 2021/3/24 10:45
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/3/24 10:45
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class VideoPreLoader {
    private VideoPreLoader() {
        handlerThread = new HandlerThread("VideoPreLoader_HandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

    }

    private static VideoPreLoader instance = null;

    public static synchronized VideoPreLoader getInstance() {
        if (instance == null) {
            instance = new VideoPreLoader();
        }
        return instance;
    }

    private Handler handler;
    private HandlerThread handlerThread;
    private List<String> cancelList = new ArrayList<>();
    /**
     * 当前正在播放的url
     */
    private String currentUrl = null;

    /**
     * 添加预加载路径
     *
     * @param data 预加载数据
     */
    public void addPreLoadUrl(final PreLoaderBean data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                realPreload(data);
            }
        });
    }

    /**
     * 取消置顶的预加载url
     *
     * @param url
     */
    public void cancelPreLoadURLIfNeeded(String url) {
        cancelList.add(url);
    }

    /**
     * 设置当前正在播放的url
     *
     * @param url 当前正在播放的url
     */
    public void setCurrentUrl(String url) {
        this.currentUrl = url;
    }

    /**
     * 取消所有的预加载url
     */
    public void cancelAnyPreLoads() {
        handler.removeCallbacksAndMessages(null);
        cancelList.clear();
    }

    /**
     * 开始预加载
     *
     * @param data
     */
    private void realPreload(PreLoaderBean data) {
        //如果原始url为空则停止向下执行
        if (data == null || isCancel(data.getOriginalUrl())) {
            return;
        }
        HttpURLConnection conn = null;
        try {
            //此处为连接本地代理服务，不会执行真正的预加载动作。真正的预加载动作在AndroidVideoCache中执行
            URL mUrl = new URL(data.getProxyUrl());
            conn = (HttpURLConnection) mUrl.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int downloadSize = 0;
            //此处有可能会出现两个下载路径同时下载数据到缓存文件中的情况，视频会播放异常，此时必须停止一个
            do {
//                String endWidth = "http://127.0.0.1:36403/";
//                if (currentUrl != null && data.getProxyUrl().endsWith(currentUrl.substring(endWidth.length()))) {//如果视频正在播放就break
//                    Log.e("VideoPreLoader", "当前正在播放此url,为防止冲突，请立马取消此链接的缓存");
//                    break;
//                }
                int numRead = is.read(buffer);
                downloadSize += numRead;
                if (/*downloadSize >= data.getPreLoadBytes() || */numRead == -1) {
                    break;
                }
            } while (true);
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
//                    conn.disconnect();
                }
            } catch (Exception e) {

            }


        }
    }

    /**
     * @description url是否已经被取消了。
     * @date: 2021/3/24 10:57
     * @author: wei.yang
     */
    private boolean isCancel(String url) {
        if (TextUtils.isEmpty(url)) {
            return true;
        }
        for (String cancelUrl : cancelList) {
            if (cancelUrl.equals(url)) {
                return true;
            }
        }
        return false;
    }

}
