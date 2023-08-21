package com.jxkj.fit_5a.entity;

import java.io.Serializable;
import java.util.List;

public class HotTopicBean{

    /**
     * name : 如何减肥
     * typeName : 热门
     * articlesCount : 13
     * pageviews : 152
     * introduction : 简单介绍
     * imgUrl : https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201604%2F02%2F20160402173031_JYyrn.thumb.700_0.jpeg&amp;refer=http%3A%2F%2Fb-ssl.duitang.com&amp;app=2002&amp;size=f9999,10000&amp;q=a80&amp;n=0&amp;g=0n&amp;fmt=jpeg?sec=1611215862&amp;t=17cb6319866f942f465a4eaab2995496
     * user : [{"userId":95,"relation":0,"nickName":"请修改昵称_17212312311","avatar":"13123","gender":0}]
     */

    private int id;
    private String name;
    private String typeName;
    private int articlesCount;
    private int pageviews;
    private String introduction;
    private String imgUrl;
    private boolean isSelect;
    private List<UserBean> user;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public int getPageviews() {
        return pageviews;
    }

    public void setPageviews(int pageviews) {
        this.pageviews = pageviews;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public static class UserBean implements Serializable{
        /**
         * userId : 95
         * relation : 0
         * nickName : 请修改昵称_17212312311
         * avatar : 13123
         * gender : 0
         */

        private int userId;
        private int relation;
        private String nickName;
        private String avatar;
        private int gender;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}
