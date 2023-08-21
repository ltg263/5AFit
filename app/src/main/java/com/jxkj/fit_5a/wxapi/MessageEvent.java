package com.jxkj.fit_5a.wxapi;

import com.jxkj.fit_5a.base.PatientListData;
import com.jxkj.fit_5a.base.ProductData;

import java.io.Serializable;
import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/6/112:54
 */
public class MessageEvent implements Serializable {
    private boolean booleanFlag;
    private String originClass;
    private int code;
    private PatientListData mPatientListData;
    private List<ProductData> productData;

    public PatientListData getmPatientListData() {
        return mPatientListData;
    }

    public void setmPatientListData(PatientListData mPatientListData) {
        this.mPatientListData = mPatientListData;
    }

    public List<ProductData> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isBooleanFlag() {
        return booleanFlag;
    }

    public void setBooleanFlag(boolean booleanFlag) {
        this.booleanFlag = booleanFlag;
    }

    public String getOriginClass() {
        return originClass;
    }

    public void setOriginClass(String originClass) {
        this.originClass = originClass;
    }
}
