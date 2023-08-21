package com.jxkj.fit_5a.entity;

public class WxAccessTokenBean {

    /**
     * access_token : 53_6bBStwMfHFV3nAnSOT_eg724iJjcaRprTf-88AQIjmmimWEFvBmrzDXWLiEKEIOIWP3BoV766DvuSsrNHOxrC80Ddu8qWxlZuDX1oH_7UUE
     * expires_in : 7200
     * refresh_token : 53_GimWi4XDXdC6AV-ETVu2RmHUe4IGviI-OEXOIHqWarZHJVmKDwmeN_2KDgFUpl2TnaqkSPbGW08LbyrYXN3dkijwrWI9WopHrFVg4bNq1Qg
     * openid : o9rOO6nEfd_oZ65caHCOdWyCqTgA
     * scope : snsapi_userinfo
     * unionid : odLNf6lhCZQ6xuuaei0_FIrGtX2I
     */

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
    /**
     * errcode : 40125
     * errmsg : invalid appsecret, rid: 61e26b3c-3b5efc57-1eae8061
     */

    private String errcode;
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
