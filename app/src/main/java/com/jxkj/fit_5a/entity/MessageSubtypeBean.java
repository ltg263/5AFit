package com.jxkj.fit_5a.entity;

public class MessageSubtypeBean {

    /**
     * lastUnreadMessage : {"contentCircleDismiss":{"circleDismissParam":{"circleId":0,"circleName":"","messageId":""},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"contentComment":{"replyCommentParam":{"circleId":0,"circleName":"","commentContent":"","commentId":0,"messageId":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0},"replyCommentType":0,"replyMomentParam":{"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"contentLike":{"commentLikeParam":{"circleId":0,"circleName":"","commentContent":"","commentId":0,"commentPublisherId":0,"messageId":"","momentId":0,"momentPublisherId":0,"publisherId":0},"likeType":0,"momentLikeParam":{"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"publisherId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"contentNewFans":{"newFansParam":{"messageId":"","userId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"createTime":0,"id":"","readStatus":0,"readTime":0,"receivingMessageUserId":0,"subType":0,"title":"","userId":0}
     * logoUrl :
     * subType : 0
     * subTypeText :
     * unReadCount : 0
     */

    private LastUnreadMessageBean lastUnreadMessage;
    private String logoUrl;
    private String  subType;
    private String subTypeText;
    private String  unReadCount;

    public LastUnreadMessageBean getLastUnreadMessage() {
        return lastUnreadMessage;
    }

    public void setLastUnreadMessage(LastUnreadMessageBean lastUnreadMessage) {
        this.lastUnreadMessage = lastUnreadMessage;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String  getSubType() {
        return subType;
    }

    public void setSubType(String  subType) {
        this.subType = subType;
    }

    public String getSubTypeText() {
        return subTypeText;
    }

    public void setSubTypeText(String subTypeText) {
        this.subTypeText = subTypeText;
    }

    public String  getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(String  unReadCount) {
        this.unReadCount = unReadCount;
    }

    public static class LastUnreadMessageBean {
        /**
         * contentCircleDismiss : {"circleDismissParam":{"circleId":0,"circleName":"","messageId":""},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}
         * contentComment : {"replyCommentParam":{"circleId":0,"circleName":"","commentContent":"","commentId":0,"messageId":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0},"replyCommentType":0,"replyMomentParam":{"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}
         * contentLike : {"commentLikeParam":{"circleId":0,"circleName":"","commentContent":"","commentId":0,"commentPublisherId":0,"messageId":"","momentId":0,"momentPublisherId":0,"publisherId":0},"likeType":0,"momentLikeParam":{"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"publisherId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}
         * contentNewFans : {"newFansParam":{"messageId":"","userId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}}
         * createTime : 0
         * id :
         * readStatus : 0
         * readTime : 0
         * receivingMessageUserId : 0
         * subType : 0
         * title :
         * userId : 0
         */

        private ContentCircleDismissBean contentCircleDismiss;
        private ContentCommentBean contentComment;
        private ContentLikeBean contentLike;
        private ContentNewFansBean contentNewFans;
        private String  createTime;
        private String id;
        private String  readStatus;
        private String  readTime;
        private String  receivingMessageUserId;
        private String  subType;
        private String title;
        private String  userId;

        public ContentCircleDismissBean getContentCircleDismiss() {
            return contentCircleDismiss;
        }

        public void setContentCircleDismiss(ContentCircleDismissBean contentCircleDismiss) {
            this.contentCircleDismiss = contentCircleDismiss;
        }

        public ContentCommentBean getContentComment() {
            return contentComment;
        }

        public void setContentComment(ContentCommentBean contentComment) {
            this.contentComment = contentComment;
        }

        public ContentLikeBean getContentLike() {
            return contentLike;
        }

        public void setContentLike(ContentLikeBean contentLike) {
            this.contentLike = contentLike;
        }

        public ContentNewFansBean getContentNewFans() {
            return contentNewFans;
        }

        public void setContentNewFans(ContentNewFansBean contentNewFans) {
            this.contentNewFans = contentNewFans;
        }

        public String  getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String  createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String  getReadStatus() {
            return readStatus;
        }

        public void setReadStatus(String  readStatus) {
            this.readStatus = readStatus;
        }

        public String  getReadTime() {
            return readTime;
        }

        public void setReadTime(String  readTime) {
            this.readTime = readTime;
        }

        public String  getReceivingMessageUserId() {
            return receivingMessageUserId;
        }

        public void setReceivingMessageUserId(String  receivingMessageUserId) {
            this.receivingMessageUserId = receivingMessageUserId;
        }

        public String  getSubType() {
            return subType;
        }

        public void setSubType(String  subType) {
            this.subType = subType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String  getUserId() {
            return userId;
        }

        public void setUserId(String  userId) {
            this.userId = userId;
        }

        public static class ContentCircleDismissBean {
            /**
             * circleDismissParam : {"circleId":0,"circleName":"","messageId":""}
             * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
             */

            private CircleDismissParamBean circleDismissParam;
            private MessageSubtypeUserBeanBean user;

            public CircleDismissParamBean getCircleDismissParam() {
                return circleDismissParam;
            }

            public void setCircleDismissParam(CircleDismissParamBean circleDismissParam) {
                this.circleDismissParam = circleDismissParam;
            }

            public MessageSubtypeUserBeanBean getUser() {
                return user;
            }

            public void setUser(MessageSubtypeUserBeanBean user) {
                this.user = user;
            }

            public static class CircleDismissParamBean {
                /**
                 * circleId : 0
                 * circleName :
                 * messageId :
                 */

                private String  circleId;
                private String circleName;
                private String messageId;

                public String  getCircleId() {
                    return circleId;
                }

                public void setCircleId(String  circleId) {
                    this.circleId = circleId;
                }

                public String getCircleName() {
                    return circleName;
                }

                public void setCircleName(String circleName) {
                    this.circleName = circleName;
                }

                public String getMessageId() {
                    return messageId;
                }

                public void setMessageId(String messageId) {
                    this.messageId = messageId;
                }
            }

        }

        public static class ContentCommentBean {
            /**
             * replyCommentParam : {"circleId":0,"circleName":"","commentContent":"","commentId":0,"messageId":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0}
             * replyCommentType : 0
             * replyMomentParam : {"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0}
             * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
             */

            private ReplyCommentParamBean replyCommentParam;
            private String  replyCommentType;
            private ReplyMomentParamBean replyMomentParam;
            private MessageSubtypeUserBeanBean user;

            public ReplyCommentParamBean getReplyCommentParam() {
                return replyCommentParam;
            }

            public void setReplyCommentParam(ReplyCommentParamBean replyCommentParam) {
                this.replyCommentParam = replyCommentParam;
            }

            public String  getReplyCommentType() {
                return replyCommentType;
            }

            public void setReplyCommentType(String  replyCommentType) {
                this.replyCommentType = replyCommentType;
            }

            public ReplyMomentParamBean getReplyMomentParam() {
                return replyMomentParam;
            }

            public void setReplyMomentParam(ReplyMomentParamBean replyMomentParam) {
                this.replyMomentParam = replyMomentParam;
            }

            public MessageSubtypeUserBeanBean getUser() {
                return user;
            }

            public void setUser(MessageSubtypeUserBeanBean user) {
                this.user = user;
            }

            public static class ReplyCommentParamBean {
                /**
                 * circleId : 0
                 * circleName :
                 * commentContent :
                 * commentId : 0
                 * messageId :
                 * momentId : 0
                 * momentPublisherId : 0
                 * replyCommentContent :
                 * replyCommentId : 0
                 * replyPublisherId : 0
                 */

                private String  circleId;
                private String circleName;
                private String commentContent;
                private String  commentId;
                private String messageId;
                private String  momentId;
                private String  momentPublisherId;
                private String replyCommentContent;
                private String  replyCommentId;
                private String  replyPublisherId;

                public String  getCircleId() {
                    return circleId;
                }

                public void setCircleId(String  circleId) {
                    this.circleId = circleId;
                }

                public String getCircleName() {
                    return circleName;
                }

                public void setCircleName(String circleName) {
                    this.circleName = circleName;
                }

                public String getCommentContent() {
                    return commentContent;
                }

                public void setCommentContent(String commentContent) {
                    this.commentContent = commentContent;
                }

                public String  getCommentId() {
                    return commentId;
                }

                public void setCommentId(String  commentId) {
                    this.commentId = commentId;
                }

                public String getMessageId() {
                    return messageId;
                }

                public void setMessageId(String messageId) {
                    this.messageId = messageId;
                }

                public String  getMomentId() {
                    return momentId;
                }

                public void setMomentId(String  momentId) {
                    this.momentId = momentId;
                }

                public String  getMomentPublisherId() {
                    return momentPublisherId;
                }

                public void setMomentPublisherId(String  momentPublisherId) {
                    this.momentPublisherId = momentPublisherId;
                }

                public String getReplyCommentContent() {
                    return replyCommentContent;
                }

                public void setReplyCommentContent(String replyCommentContent) {
                    this.replyCommentContent = replyCommentContent;
                }

                public String  getReplyCommentId() {
                    return replyCommentId;
                }

                public void setReplyCommentId(String  replyCommentId) {
                    this.replyCommentId = replyCommentId;
                }

                public String  getReplyPublisherId() {
                    return replyPublisherId;
                }

                public void setReplyPublisherId(String  replyPublisherId) {
                    this.replyPublisherId = replyPublisherId;
                }
            }

            public static class ReplyMomentParamBean {
                /**
                 * circleId : 0
                 * circleName :
                 * messageId :
                 * momentContent :
                 * momentId : 0
                 * momentPublisherId : 0
                 * replyCommentContent :
                 * replyCommentId : 0
                 * replyPublisherId : 0
                 */

                private String  circleId;
                private String circleName;
                private String messageId;
                private String momentContent;
                private String  momentId;
                private String  momentPublisherId;
                private String replyCommentContent;
                private String  replyCommentId;
                private String  replyPublisherId;

                public String  getCircleId() {
                    return circleId;
                }

                public void setCircleId(String  circleId) {
                    this.circleId = circleId;
                }

                public String getCircleName() {
                    return circleName;
                }

                public void setCircleName(String circleName) {
                    this.circleName = circleName;
                }

                public String getMessageId() {
                    return messageId;
                }

                public void setMessageId(String messageId) {
                    this.messageId = messageId;
                }

                public String getMomentContent() {
                    return momentContent;
                }

                public void setMomentContent(String momentContent) {
                    this.momentContent = momentContent;
                }

                public String  getMomentId() {
                    return momentId;
                }

                public void setMomentId(String  momentId) {
                    this.momentId = momentId;
                }

                public String  getMomentPublisherId() {
                    return momentPublisherId;
                }

                public void setMomentPublisherId(String  momentPublisherId) {
                    this.momentPublisherId = momentPublisherId;
                }

                public String getReplyCommentContent() {
                    return replyCommentContent;
                }

                public void setReplyCommentContent(String replyCommentContent) {
                    this.replyCommentContent = replyCommentContent;
                }

                public String  getReplyCommentId() {
                    return replyCommentId;
                }

                public void setReplyCommentId(String  replyCommentId) {
                    this.replyCommentId = replyCommentId;
                }

                public String  getReplyPublisherId() {
                    return replyPublisherId;
                }

                public void setReplyPublisherId(String  replyPublisherId) {
                    this.replyPublisherId = replyPublisherId;
                }
            }
        }

        public static class ContentLikeBean {
            /**
             * commentLikeParam : {"circleId":0,"circleName":"","commentContent":"","commentId":0,"commentPublisherId":0,"messageId":"","momentId":0,"momentPublisherId":0,"publisherId":0}
             * likeType : 0
             * momentLikeParam : {"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"publisherId":0}
             * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
             */

            private CommentLikeParamBean commentLikeParam;
            private String  likeType;
            private MomentLikeParamBean momentLikeParam;
            private MessageSubtypeUserBeanBean user;

            public CommentLikeParamBean getCommentLikeParam() {
                return commentLikeParam;
            }

            public void setCommentLikeParam(CommentLikeParamBean commentLikeParam) {
                this.commentLikeParam = commentLikeParam;
            }

            public String  getLikeType() {
                return likeType;
            }

            public void setLikeType(String  likeType) {
                this.likeType = likeType;
            }

            public MomentLikeParamBean getMomentLikeParam() {
                return momentLikeParam;
            }

            public void setMomentLikeParam(MomentLikeParamBean momentLikeParam) {
                this.momentLikeParam = momentLikeParam;
            }

            public MessageSubtypeUserBeanBean getUser() {
                return user;
            }

            public void setUser(MessageSubtypeUserBeanBean user) {
                this.user = user;
            }

            public static class CommentLikeParamBean {
                /**
                 * circleId : 0
                 * circleName :
                 * commentContent :
                 * commentId : 0
                 * commentPublisherId : 0
                 * messageId :
                 * momentId : 0
                 * momentPublisherId : 0
                 * publisherId : 0
                 */

                private String  circleId;
                private String circleName;
                private String commentContent;
                private String  commentId;
                private String  commentPublisherId;
                private String messageId;
                private String  momentId;
                private String  momentPublisherId;
                private String  publisherId;

                public String  getCircleId() {
                    return circleId;
                }

                public void setCircleId(String  circleId) {
                    this.circleId = circleId;
                }

                public String getCircleName() {
                    return circleName;
                }

                public void setCircleName(String circleName) {
                    this.circleName = circleName;
                }

                public String getCommentContent() {
                    return commentContent;
                }

                public void setCommentContent(String commentContent) {
                    this.commentContent = commentContent;
                }

                public String  getCommentId() {
                    return commentId;
                }

                public void setCommentId(String  commentId) {
                    this.commentId = commentId;
                }

                public String  getCommentPublisherId() {
                    return commentPublisherId;
                }

                public void setCommentPublisherId(String  commentPublisherId) {
                    this.commentPublisherId = commentPublisherId;
                }

                public String getMessageId() {
                    return messageId;
                }

                public void setMessageId(String messageId) {
                    this.messageId = messageId;
                }

                public String  getMomentId() {
                    return momentId;
                }

                public void setMomentId(String  momentId) {
                    this.momentId = momentId;
                }

                public String  getMomentPublisherId() {
                    return momentPublisherId;
                }

                public void setMomentPublisherId(String  momentPublisherId) {
                    this.momentPublisherId = momentPublisherId;
                }

                public String  getPublisherId() {
                    return publisherId;
                }

                public void setPublisherId(String  publisherId) {
                    this.publisherId = publisherId;
                }
            }

            public static class MomentLikeParamBean {
                /**
                 * circleId : 0
                 * circleName :
                 * messageId :
                 * momentContent :
                 * momentId : 0
                 * momentPublisherId : 0
                 * publisherId : 0
                 */

                private String  circleId;
                private String circleName;
                private String messageId;
                private String momentContent;
                private String  momentId;
                private String  momentPublisherId;
                private String  publisherId;

                public String  getCircleId() {
                    return circleId;
                }

                public void setCircleId(String  circleId) {
                    this.circleId = circleId;
                }

                public String getCircleName() {
                    return circleName;
                }

                public void setCircleName(String circleName) {
                    this.circleName = circleName;
                }

                public String getMessageId() {
                    return messageId;
                }

                public void setMessageId(String messageId) {
                    this.messageId = messageId;
                }

                public String getMomentContent() {
                    return momentContent;
                }

                public void setMomentContent(String momentContent) {
                    this.momentContent = momentContent;
                }

                public String  getMomentId() {
                    return momentId;
                }

                public void setMomentId(String  momentId) {
                    this.momentId = momentId;
                }

                public String  getMomentPublisherId() {
                    return momentPublisherId;
                }

                public void setMomentPublisherId(String  momentPublisherId) {
                    this.momentPublisherId = momentPublisherId;
                }

                public String  getPublisherId() {
                    return publisherId;
                }

                public void setPublisherId(String  publisherId) {
                    this.publisherId = publisherId;
                }
            }
        }

        public static class ContentNewFansBean {
            /**
             * newFansParam : {"messageId":"","userId":0}
             * user : {"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}
             */

            private NewFansParamBean newFansParam;
            private MessageSubtypeUserBeanBean user;

            public NewFansParamBean getNewFansParam() {
                return newFansParam;
            }

            public void setNewFansParam(NewFansParamBean newFansParam) {
                this.newFansParam = newFansParam;
            }

            public MessageSubtypeUserBeanBean getUser() {
                return user;
            }

            public void setUser(MessageSubtypeUserBeanBean user) {
                this.user = user;
            }

            public static class NewFansParamBean {
                /**
                 * messageId :
                 * userId : 0
                 */

                private String messageId;
                private String  userId;

                public String getMessageId() {
                    return messageId;
                }

                public void setMessageId(String messageId) {
                    this.messageId = messageId;
                }

                public String  getUserId() {
                    return userId;
                }

                public void setUserId(String  userId) {
                    this.userId = userId;
                }
            }
        }
    }
}
