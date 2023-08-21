package com.jxkj.fit_5a.entity;

public class ParameterBean {

    /**
     * paramName : android.url
     * type : 1
     * paramValue : {"paramValue":"https://5a-fit-oss.nbqichen.com/5AFit.apk","ext1":"1.0.0"}
     * description : 安卓apk
     * ext1 : 
     * ext2 : 
     * ext3 : 
     */

    private String paramName;
    private int type;
    private String paramValue;
    private String description;
    private String ext1;
    private String ext2;
    private String ext3;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}
