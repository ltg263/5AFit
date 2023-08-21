package com.jxkj.fit_5a.entity;


import java.io.Serializable;

public class QueryPopularBean implements Serializable {

    /**
     * circleId : 1631335318909000
     * publisherId : 145
     * momentId : 1631349396521000
     * user : {"userId":145,"relation":0,"nickName":"阿呆","avatar":"https://5a-fit-oss.nbqichen.com/user/BPNZCWXK9mvsa6LZD0KPg.jpg","gender":1}
     * circleInfo : {"circleId":1631335318909000,"imgUrl":"https://5a-fit-oss.nbqichen.com/interest/U7btlL9IqzkFEGgB6aTCVA.jpg","name":"一日三餐打卡"}
     * isLike : false
     * isFavorite : false
     * isSyncPersonalMoment : true
     * shareCount : 0
     * likeCount : 1
     * commentCount : 17
     * favoriteCount : 1
     * topicArr : ["晒出你的营养早餐"]
     * pageviews : 247
     * contentType : 2
     * simpleContent : 晚上吃啥啊
     * content : 晚上吃啥啊
     * shareType : 1
     * timestamp : 1631349396385
     * position : 宁波市·县前街
     * location : [121.55027,29.87386]
     * media : [{"imageUrl":"https://5a-fit-oss.nbqichen.com/circle/uOEe5FxngLOoqAwOpNpXw.jpg","type":2},{"imageUrl":"https://5a-fit-oss.nbqichen.com/circle/XrwtFZypqTUzF6wmKNkQ.jpg","type":2}]
     */

    private String circleId;
    private String circleName;
    private String circleIcon;
    private String publisherId;
    private String momentId;
    private UserBean user;
    private CircleInfoBean circleInfo;
    private boolean isLike;
    private boolean isFavorite;
    private boolean isSyncPersonalMoment;
    private String shareCount;
    private String likeCount;
    private String commentCount;
    private String favoriteCount;
    private String topicArr;
    private String pageviews;
    private String contentType;
    private String simpleContent;
    private String content;
    private String shareType;
    private long timestamp;
    private String position;
    private String location;
    private String media;

    public void setCircleIcon(String circleIcon) {
        this.circleIcon = circleIcon;
    }

    public String getCircleIcon() {
        return circleIcon;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleName() {
        return circleName;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public CircleInfoBean getCircleInfo() {
        return circleInfo;
    }

    public void setCircleInfo(CircleInfoBean circleInfo) {
        this.circleInfo = circleInfo;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isIsSyncPersonalMoment() {
        return isSyncPersonalMoment;
    }

    public void setIsSyncPersonalMoment(boolean isSyncPersonalMoment) {
        this.isSyncPersonalMoment = isSyncPersonalMoment;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getTopicArr() {
        return topicArr;
    }

    public void setTopicArr(String topicArr) {
        this.topicArr = topicArr;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSimpleContent() {
        return simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public static class UserBean {
        /**
         * userId : 145
         * relation : 0
         * nickName : 阿呆
         * avatar : https://5a-fit-oss.nbqichen.com/user/BPNZCWXK9mvsa6LZD0KPg.jpg
         * gender : 1
         */

        private String userId;
        private int relation;
        private String nickName;
        private String avatar;
        private String gender;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getNickName() {
            return nickName;
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
    }

    public static class CircleInfoBean {
        /**
         * circleId : 1631335318909000
         * imgUrl : https://5a-fit-oss.nbqichen.com/interest/U7btlL9IqzkFEGgB6aTCVA.jpg
         * name : 一日三餐打卡
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
}
