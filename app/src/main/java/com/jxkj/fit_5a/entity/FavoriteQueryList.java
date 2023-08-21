package com.jxkj.fit_5a.entity;

public class FavoriteQueryList {

    /**
     * userId : 80
     * circleId : 0
     * momentId : 1608694802238000
     * momentPublisherId : 95
     * moment : {"circleId":0,"publisherId":95,"momentId":1608694802238000,"user":{"userId":95,"relation":1,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0},"isLike":false,"likeCount":0,"commentCount":0,"topicArr":"[\"划船机\",\"如何减肥\"]","pageviews":4,"contentType":1,"simpleContent":"测试沈彪，你好啊","shareType":1,"timestamp":1608694803187,"position":"","location":"","media":"","delete":false,"violation":false}
     */

    private String userId;
    private String circleId;
    private String momentId;
    private String incrementId;
    private String momentPublisherId;
    private QueryPopularBean moment;

    public void setIncrementId(String incrementId) {
        this.incrementId = incrementId;
    }

    public String getIncrementId() {
        return incrementId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getMomentPublisherId() {
        return momentPublisherId;
    }

    public void setMomentPublisherId(String momentPublisherId) {
        this.momentPublisherId = momentPublisherId;
    }

    public QueryPopularBean getMoment() {
        return moment;
    }

    public void setMoment(QueryPopularBean moment) {
        this.moment = moment;
    }

}
