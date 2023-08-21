package com.jxkj.fit_5a.base;

import java.util.List;

public class ResultList<T> {
    private String sub_mesg;
    private int code;
    private List<T> data;

    public String getMesg() {
        return sub_mesg;
    }

    public void setMesg(String sub_mesg) {
        this.sub_mesg = sub_mesg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
