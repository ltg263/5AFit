package com.jxkj.fit_5a.entity;

import java.util.List;

public class TeachingMomentBean {
    /**
     * publisherId : -1
     * momentId : 1678872605957000
     * isLike : false
     * isFavorite : false
     * shareCount : 0
     * likeCount : 0
     * commentCount : 0
     * favoriteCount : 0
     * pageviews : 0
     * title : 测试动态视频
     * tags : ["地方","风格化阿萨德"]
     * introduction : 我们的目标并不仅仅是做一个纯粹的软件公司，而更注重于为客户挖掘信息化的价值，希望通过信息化帮助客户宣传品牌理念、提高服务质量、塑造企业形象。我们有热衷技术探索的程序员，资深的美工，更有专致电子商务实战研究方面的人才，我们希望用我们独特的信息技术加优质的服务，帮助客户企业快速成长，让科技服务于社会。我们的目标并不仅仅是做一个纯粹的软件公司，而更注重于为客户挖掘信息化的价值，希望通过信息化帮助客户宣传品牌理念、提高服务质量、塑造企业形象。我们有热衷技术探索的程序员，资深的美工，更有专致电子商务实战研究方面的人才，我们希望用我们独特的信息技术加优质的服务，帮助客户企业快速成长，让科技服务于社会。
     * coverImageUrl : https://oss.5afit.com/fLZDp6kaTf2Qd31dRJZTFg.jpeg
     * videoUrl : f20a2720c31371eda9a10764a0ec0102
     * classificationIds : [1,3]
     * classificationInfos : [{"classificationId":1,"classificationName":"明星教练","classificationImgUrl":""},{"classificationId":3,"classificationName":"徒手课程","classificationImgUrl":""}]
     * deviceTypeId : 3
     * deviceTypeName : 椭圆机
     * productId : 0
     * productTitle :
     * hot : 99
     * createTimestamp : 1678872605919
     */

    private String publisherId;
    private String momentId;
    private boolean isLike;
    private boolean isFavorite;
    private String shareCount;
    private String likeCount;
    private String commentCount;
    private String favoriteCount;
    private String pageviews;
    private String title;
    private String tags;
    private String introduction;
    private String coverImageUrl;
    private String videoUrl;
    private String classificationIds;
    private String deviceTypeId;
    private String deviceTypeName;
    private String productId;
    private String productTitle;
    private String hot;
    private String createTimestamp;
    private List<ClassificationInfosBean> classificationInfos;

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

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getClassificationIds() {
        return classificationIds;
    }

    public void setClassificationIds(String classificationIds) {
        this.classificationIds = classificationIds;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public List<ClassificationInfosBean> getClassificationInfos() {
        return classificationInfos;
    }

    public void setClassificationInfos(List<ClassificationInfosBean> classificationInfos) {
        this.classificationInfos = classificationInfos;
    }

    public static class ClassificationInfosBean {
        /**
         * classificationId : 1
         * classificationName : 明星教练
         * classificationImgUrl :
         */

        private String classificationId;
        private String classificationName;
        private String classificationImgUrl;

        public String getClassificationId() {
            return classificationId;
        }

        public void setClassificationId(String classificationId) {
            this.classificationId = classificationId;
        }

        public String getClassificationName() {
            return classificationName;
        }

        public void setClassificationName(String classificationName) {
            this.classificationName = classificationName;
        }

        public String getClassificationImgUrl() {
            return classificationImgUrl;
        }

        public void setClassificationImgUrl(String classificationImgUrl) {
            this.classificationImgUrl = classificationImgUrl;
        }
    }
}
