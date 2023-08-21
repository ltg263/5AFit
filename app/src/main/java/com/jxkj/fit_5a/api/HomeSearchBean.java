package com.jxkj.fit_5a.api;

import com.jxkj.fit_5a.entity.CircleQueryJoinedBean;
import com.jxkj.fit_5a.entity.FollowFansList;
import com.jxkj.fit_5a.entity.HotTopicBean;
import com.jxkj.fit_5a.entity.QueryPopularBean;

import java.util.List;

public class HomeSearchBean {

    /**
     * circles : [{"completedCount":0,"createTimestamp":0,"deviceType":0,"explain":"","hot":true,"id":0,"imgUrl":"","join":true,"lastTimestamp":0,"memberCount":0,"momentCount":0,"name":""}]
     * moments : [{"circleId":0,"circleInfo":{"circleId":0,"imgUrl":"","name":""},"commentCount":0,"content":"","contentType":0,"favoriteCount":0,"isFavorite":true,"isLike":true,"isSyncPersonalMoment":true,"likeCount":0,"location":"","media":"","momentId":0,"pageviews":0,"position":"","publisherId":0,"shareCount":0,"shareType":0,"simpleContent":"","timestamp":0,"topicArr":"","user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}]
     * searchType :
     * topics : [{"articlesCount":0,"checked":true,"fansCount":0,"hot":true,"imgUrl":"","introduction":"","name":"","pageviews":0,"typeName":""}]
     * users : [{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}]
     */

    private String searchType;
    private List<CircleQueryJoinedBean.ListBean> circles;
    private List<QueryPopularBean> moments;
    private List<HotTopicBean> topics;
    private List<FollowFansList.DataBean.UserBean> users;

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public List<CircleQueryJoinedBean.ListBean> getCircles() {
        return circles;
    }

    public void setCircles(List<CircleQueryJoinedBean.ListBean> circles) {
        this.circles = circles;
    }

    public List<QueryPopularBean> getMoments() {
        return moments;
    }

    public void setMoments(List<QueryPopularBean> moments) {
        this.moments = moments;
    }

    public List<HotTopicBean> getTopics() {
        return topics;
    }

    public void setTopics(List<HotTopicBean> topics) {
        this.topics = topics;
    }

    public List<FollowFansList.DataBean.UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<FollowFansList.DataBean.UserBean> users) {
        this.users = users;
    }

}
