package com.jxkj.fit_5a.entity;

import java.util.List;

public class CommentMomentBean {

    /**
     * momentId : 1608101070473000
     * commentId : 1609132916162000
     * publisherId : 88
     * circleId : 0
     * momentPublisherId : 95
     * user : {"userId":88,"relation":0,"nickName":"YW-2188","avatar":"13123","gender":2}
     * isLike : false
     * replyType : 1
     * replyCommentId : 0
     * replyCommentPublisherId : 0
     * authorPublish : false
     * authorReplyCount : 0
     * likeCount : 0
     * commentCount : 1
     * contentType : 1
     * content : Daddy’s Cheshire 测试测试 速度 速度是的 是东方风格受到非法给我发新增长点的大额风水大师的恶得瑟发生的时代风格哥哥哥哥
     * timestamp : 1609132916215
     * replyComment : null
     * authorReplyComments : []
     *commentOrderType=lately&circleId=1631335318909000&momentId=1631349396521000&momentPublisherId=145&page=1&pageSize=10
     */

    private String momentId;
    private String commentId;
    private String publisherId;
    private String circleId;
    private String momentPublisherId;
    private UserBean user;
    private boolean isLike;
    private String replyType;
    private String replyCommentId;
    private String replyCommentPublisherId;
    private boolean authorPublish;
    private String authorReplyCount;
    private int likeCount;
    private int commentCount;
    private String contentType;
    private String content;
    private long timestamp;
    private CommentMomentBean replyComment;
    private List<CommentMomentBean> authorReplyComments;



    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getMomentPublisherId() {
        return momentPublisherId;
    }

    public void setMomentPublisherId(String momentPublisherId) {
        this.momentPublisherId = momentPublisherId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public String getReplyCommentPublisherId() {
        return replyCommentPublisherId;
    }

    public void setReplyCommentPublisherId(String replyCommentPublisherId) {
        this.replyCommentPublisherId = replyCommentPublisherId;
    }

    public boolean isAuthorPublish() {
        return authorPublish;
    }

    public void setAuthorPublish(boolean authorPublish) {
        this.authorPublish = authorPublish;
    }

    public String getAuthorReplyCount() {
        return authorReplyCount;
    }

    public void setAuthorReplyCount(String authorReplyCount) {
        this.authorReplyCount = authorReplyCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setReplyComment(CommentMomentBean replyComment) {
        this.replyComment = replyComment;
    }

    public CommentMomentBean getReplyComment() {
        return replyComment;
    }

    public List<CommentMomentBean> getAuthorReplyComments() {
        return authorReplyComments;
    }

    public void setAuthorReplyComments(List<CommentMomentBean> authorReplyComments) {
        this.authorReplyComments = authorReplyComments;
    }

    public static class UserBean {
        /**
         * userId : 88
         * relation : 0
         * nickName : YW-2188
         * avatar : 13123
         * gender : 2
         */

        private String userId;
        private String relation;
        private String nickName;
        private String avatar;
        private String gender;

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
}
