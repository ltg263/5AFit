package com.jxkj.fit_5a.entity;

import java.util.List;

public class TopicAllBean {

    /**
     * name : 热门
     * articlesCount : 28
     * pageviews : 927
     * introduction :
     * hot : false
     * sortWeights : 0
     * imgUrl :
     * children : [{"name":"如何减肥","articlesCount":17,"pageviews":641,"introduction":"简单介绍","hot":true,"sortWeights":811,"imgUrl":"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201604%2F02%2F20160402173031_JYyrn.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611215862&t=17cb6319866f942f465a4eaab2995496","children":[]},{"name":"每日健身计划","articlesCount":11,"pageviews":286,"introduction":"简介","hot":true,"sortWeights":396,"imgUrl":"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201604%2F02%2F20160402173031_JYyrn.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611215862&t=17cb6319866f942f465a4eaab2995496","children":[]}]
     */

    private String name;
    private String articlesCount;
    private String pageviews;
    private String introduction;
    private boolean hot;
    private boolean isSele;

    private String sortWeights;
    private String imgUrl;
    private List<HotTopicBean> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(String articlesCount) {
        this.articlesCount = articlesCount;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isSele() {
        return isSele;
    }

    public void setSele(boolean sele) {
        isSele = sele;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public String getSortWeights() {
        return sortWeights;
    }

    public void setSortWeights(String sortWeights) {
        this.sortWeights = sortWeights;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<HotTopicBean> getChildren() {
        return children;
    }

    public void setChildren(List<HotTopicBean> children) {
        this.children = children;
    }

}
