package com.jxkj.fit_5a.entity;

public class ClassificationAllData {

    /**
     * classificationId : 20
     * name : 资讯音频
     * imgUrl :
     */

    private int classificationId;
    private int id;
    private String name;
    private String imgUrl;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
