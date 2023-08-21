package com.jxkj.fit_5a.entity;

import java.util.List;

public class MomentDetailsBean {

    /**
     * circleId : 0
     * circleInfo : {"circleId":0,"imgUrl":"","name":""}
     * commentCount : 0
     * content : 
     * contentType : 0
     * delete : true
     * favoriteCount : 0
     * isFavorite : true
     * isLike : true
     * likeCount : 0
     * location : 
     * media : 
     * momentId : 0
     * momentLikes : [{"authorLike":true,"circleId":0,"momentId":0,"momentPublisherId":0,"publisherId":0,"timestamp":0,"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}]
     * pageviews : 0
     * position : 
     * publisherId : 0
     * shareType : 0
     * simpleContent : 
     * timestamp : 0
     * topicArr : 
     * user : {"avatar":"","commentLikeCount":0,"fansCount":0,"favoriteCount":0,"followCount":0,"followTopicCount":0,"gender":0,"lastUpdateMomentTimestamp":0,"likeCount":0,"momentCount":0,"momentLikeCount":0,"nickName":"","relation":0,"userId":0}
     * violation : true
     */

    private String circleId;
    private CircleInfoBean circleInfo;
    private int commentCount;
    private String content;
    private String contentType;
    private boolean delete;
    private int favoriteCount;
    private boolean isFavorite;
    private boolean isLike;
    private int likeCount;
    private String location;
    private String media;
    private String momentId;
    private String pageviews;
    private String position;
    private String publisherId;
    private String shareType;
    private String simpleContent;
    private long timestamp;
    private String topicArr;
    private UserBean user;
    private boolean violation;
    private List<MomentLikesBean> momentLikes;

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public CircleInfoBean getCircleInfo() {
        return circleInfo;
    }

    public void setCircleInfo(CircleInfoBean circleInfo) {
        this.circleInfo = circleInfo;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getSimpleContent() {
        return simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTopicArr() {
        return topicArr;
    }

    public void setTopicArr(String topicArr) {
        this.topicArr = topicArr;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isViolation() {
        return violation;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }

    public List<MomentLikesBean> getMomentLikes() {
        return momentLikes;
    }

    public void setMomentLikes(List<MomentLikesBean> momentLikes) {
        this.momentLikes = momentLikes;
    }

    public static class CircleInfoBean {
        /**
         * circleId : 0
         * imgUrl : 
         * name : 
         */

        private String circleId;
        private String imgUrl;
        private String name;

        public String getCircleId() {
            return circleId;
        }

        public void setCircleId(String circleId) {
            this.circleId = circleId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class UserBean {
        /**
         * avatar : 
         * commentLikeCount : 0
         * fansCount : 0
         * favoriteCount : 0
         * followCount : 0
         * followTopicCount : 0
         * gender : 0
         * lastUpdateMomentTimestamp : 0
         * likeCount : 0
         * momentCount : 0
         * momentLikeCount : 0
         * nickName : 
         * relation : 0
         * userId : 0
         */

        private String avatar;
        private String commentLikeCount;
        private String fansCount;
        private String favoriteCount;
        private String followCount;
        private String followTopicCount;
        private String gender;
        private String lastUpdateMomentTimestamp;
        private String likeCount;
        private String momentCount;
        private String momentLikeCount;
        private String nickName;
        private int relation;
        private String userId;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCommentLikeCount() {
            return commentLikeCount;
        }

        public void setCommentLikeCount(String commentLikeCount) {
            this.commentLikeCount = commentLikeCount;
        }

        public String getFansCount() {
            return fansCount;
        }

        public void setFansCount(String fansCount) {
            this.fansCount = fansCount;
        }

        public String getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(String favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public String getFollowCount() {
            return followCount;
        }

        public void setFollowCount(String followCount) {
            this.followCount = followCount;
        }

        public String getFollowTopicCount() {
            return followTopicCount;
        }

        public void setFollowTopicCount(String followTopicCount) {
            this.followTopicCount = followTopicCount;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLastUpdateMomentTimestamp() {
            return lastUpdateMomentTimestamp;
        }

        public void setLastUpdateMomentTimestamp(String lastUpdateMomentTimestamp) {
            this.lastUpdateMomentTimestamp = lastUpdateMomentTimestamp;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getMomentCount() {
            return momentCount;
        }

        public void setMomentCount(String momentCount) {
            this.momentCount = momentCount;
        }

        public String getMomentLikeCount() {
            return momentLikeCount;
        }

        public void setMomentLikeCount(String momentLikeCount) {
            this.momentLikeCount = momentLikeCount;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class MomentLikesBean {
        /**
         * authorLike : true
         * circleId : 0
         * momentId : 0
         * momentPublisherId : 0
         * publisherId : 0
         * timestamp : 0
         * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
         */

        private boolean authorLike;
        private String circleId;
        private String momentId;
        private String momentPublisherId;
        private String publisherId;
        private long timestamp;
        private UserBean user;

        public boolean isAuthorLike() {
            return authorLike;
        }

        public void setAuthorLike(boolean authorLike) {
            this.authorLike = authorLike;
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

        public String getPublisherId() {
            return publisherId;
        }

        public void setPublisherId(String publisherId) {
            this.publisherId = publisherId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }
    }
}
