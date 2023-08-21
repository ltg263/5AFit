package com.jxkj.fit_5a.entity;

import java.util.List;

public class TeachingMomentBeanWc {

    /**
     * incrementId : 0
     * moment : {"classificationIds":"","classificationInfos":[{"classificationId":0,"classificationImgUrl":"","classificationName":""}],"commentCount":0,"coverImageUrl":"","createTimestamp":0,"deviceTypeId":0,"deviceTypeName":"","favoriteCount":0,"hot":0,"introduction":"","isFavorite":true,"isLike":true,"likeCount":0,"momentId":0,"pageviews":0,"productId":0,"productTitle":"","publisherId":0,"shareCount":0,"tags":"","title":"","videoUrl":""}
     * momentId : 0
     * momentPublisherId : 0
     * userId : 0
     */

    private String incrementId;
    private TeachingMomentBean moment;
    private String momentId;
    private String momentPublisherId;
    private String userId;

    public String getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(String incrementId) {
        this.incrementId = incrementId;
    }

    public TeachingMomentBean getMoment() {
        return moment;
    }

    public void setMoment(TeachingMomentBean moment) {
        this.moment = moment;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

