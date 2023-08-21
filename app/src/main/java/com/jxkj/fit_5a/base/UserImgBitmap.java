package com.jxkj.fit_5a.base;

import android.graphics.Bitmap;

public class UserImgBitmap {
    String userId;
    Bitmap mBitmap;

    public UserImgBitmap(String userId, Bitmap bitmap) {
        this.userId = userId;
        mBitmap = bitmap;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getUserId() {
        return userId;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }
}
