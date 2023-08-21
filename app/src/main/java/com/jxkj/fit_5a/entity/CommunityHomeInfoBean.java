package com.jxkj.fit_5a.entity;

import java.util.List;

public class CommunityHomeInfoBean {

    private List<CircleQueryJoinedBean.ListBean> hotCircles;
    private List<HotTopicBean> hotTopics;
    private List<QueryPopularBean> hotMoments;

    public List<CircleQueryJoinedBean.ListBean> getHotCircles() {
        return hotCircles;
    }

    public void setHotCircles(List<CircleQueryJoinedBean.ListBean> hotCircles) {
        this.hotCircles = hotCircles;
    }

    public List<HotTopicBean> getHotTopics() {
        return hotTopics;
    }

    public void setHotTopics(List<HotTopicBean> hotTopics) {
        this.hotTopics = hotTopics;
    }

    public List<QueryPopularBean> getHotMoments() {
        return hotMoments;
    }

    public void setHotMoments(List<QueryPopularBean> hotMoments) {
        this.hotMoments = hotMoments;
    }
}
