package com.jxkj.fit_5a.entity;

public class UserOwnInfo {

    /**
     * userId : 95
     * relation : 0
     * nickName : 请修改昵称_17212312311
     * avatar : 13123
     * gender : 0
     * fansCount : 1
     * followCount : 2
     * likeCount : 1
     * momentLikeCount : 1
     * commentLikeCount : 0
     * momentCount : 12
     * favoriteCount : 2
     * createCricleCount : 6
     * joinCricleCount : 0
     * lastUpdateMomentTimestamp : 1608695907027
     */

    private String userId;
    private String relation;
    private String nickName;
    private String explain;
    private String avatar;
    private String backImg;
    private String gender;
    private String fansCount;
    private String followCount;
    private String likeCount;
    private String medalCount;
    private String momentLikeCount;
    private String commentLikeCount;
    private String momentCount;
    private String favoriteCount;
    private String giftCount;
    private String createCricleCount;
    private String joinCricleCount;
    private long lastUpdateMomentTimestamp;

    public void setMedalCount(String medalCount) {
        this.medalCount = medalCount;
    }

    public String getMedalCount() {
        return medalCount;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getBackImg() {
        return backImg;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(String giftCount) {
        this.giftCount = giftCount;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getFollowCount() {
        return followCount;
    }

    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getMomentLikeCount() {
        return momentLikeCount;
    }

    public void setMomentLikeCount(String momentLikeCount) {
        this.momentLikeCount = momentLikeCount;
    }

    public String getCommentLikeCount() {
        return commentLikeCount;
    }

    public void setCommentLikeCount(String commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public String getMomentCount() {
        return momentCount;
    }

    public void setMomentCount(String momentCount) {
        this.momentCount = momentCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getCreateCricleCount() {
        return createCricleCount;
    }

    public void setCreateCricleCount(String createCricleCount) {
        this.createCricleCount = createCricleCount;
    }

    public String getJoinCricleCount() {
        return joinCricleCount;
    }

    public void setJoinCricleCount(String joinCricleCount) {
        this.joinCricleCount = joinCricleCount;
    }

    public long getLastUpdateMomentTimestamp() {
        return lastUpdateMomentTimestamp;
    }

    public void setLastUpdateMomentTimestamp(long lastUpdateMomentTimestamp) {
        this.lastUpdateMomentTimestamp = lastUpdateMomentTimestamp;
    }
}
