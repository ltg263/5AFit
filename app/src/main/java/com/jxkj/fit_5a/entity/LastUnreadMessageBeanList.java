package com.jxkj.fit_5a.entity;

import java.util.List;

public class LastUnreadMessageBeanList {

    /**
     * list : [{"contentCircleDismiss":{"circleDismissParam":{"circleId":0,"circleName":"","messageId":""},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"contentComment":{"replyCommentParam":{"circleId":0,"circleName":"","commentContent":"","commentId":0,"messageId":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0},"replyCommentType":0,"replyMomentParam":{"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"replyCommentContent":"","replyCommentId":0,"replyPublisherId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"contentLike":{"commentLikeParam":{"circleId":0,"circleName":"","commentContent":"","commentId":0,"commentPublisherId":0,"messageId":"","momentId":0,"momentPublisherId":0,"publisherId":0},"likeType":0,"momentLikeParam":{"circleId":0,"circleName":"","messageId":"","momentContent":"","momentId":0,"momentPublisherId":0,"publisherId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"contentNewFans":{"newFansParam":{"messageId":"","userId":0},"user":{"avatar":"","gender":0,"nickName":"","relation":0,"userId":0}},"createTime":0,"id":"","readStatus":0,"readTime":0,"receivingMessageUserId":0,"subType":0,"title":"","userId":0}]
     * totalCount : 0
     * unReadCount : 0
     */

    private int totalCount;
    private int unReadCount;
    private List<MessageSubtypeBean.LastUnreadMessageBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public List<MessageSubtypeBean.LastUnreadMessageBean> getList() {
        return list;
    }

    public void setList(List<MessageSubtypeBean.LastUnreadMessageBean> list) {
        this.list = list;
    }
}
