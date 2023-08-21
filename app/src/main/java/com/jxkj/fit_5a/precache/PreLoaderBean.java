package com.jxkj.fit_5a.precache;

/**
 * @ProjectName: u8-app-android
 * @Package: com.danikula.videocache.preloader
 * @ClassName: PreLoaderBean
 * @Description: 预加载使用到的实体类
 * @Author: wei.yang
 * @CreateDate: 2021/3/24 11:11
 * @UpdateUser: 更新者：wei.yang
 * @UpdateDate: 2021/3/24 11:11
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class PreLoaderBean {
    /**
     * 视频原始路径
     */
    private String originalUrl;
    /**
     * 本地代理路径
     */
    private String proxyUrl;
    /**
     * 已经预加载的byte数
     */
    private int preLoadBytes = 1024*1024*2;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public int getPreLoadBytes() {
        return preLoadBytes;
    }

    public void setPreLoadBytes(int preLoadBytes) {
        this.preLoadBytes = preLoadBytes;
    }

    public PreLoaderBean() {
    }

    public PreLoaderBean(String originalUrl, String proxyUrl) {
        this.originalUrl = originalUrl;
        this.proxyUrl = proxyUrl;
    }
}
