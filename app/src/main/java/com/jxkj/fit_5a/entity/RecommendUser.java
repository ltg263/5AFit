package com.jxkj.fit_5a.entity;

public class RecommendUser {

    /**
     * userId : 146
     * relation : 0
     * nickName : 请修改昵称_131222222222
     * avatar : 13123
     * gender : 0
     */

    private int userId;
    private int relation;
    private String nickName;
    private String avatar;
    private int gender;
    private boolean isSelect=false;

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

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
