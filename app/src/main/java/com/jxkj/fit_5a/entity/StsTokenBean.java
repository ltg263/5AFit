package com.jxkj.fit_5a.entity;

public class StsTokenBean {

    /**
     * SecurityToken : CAIShAJ1q6Ft5B2yfSjIr5fWeovEo61R+oGaShXTpUQtY7h+gfHDoDz2IHtIfnRpAOEcsfk1m29W6vcblqN+W4NIX0rNaY4otinaSOAnJdivgde8yJBZor/HcDHhJnyW9cvWZPqDP7G5U/yxalfCuzZuyL/hD1uLVECkNpv74vwOLK5gPG+CYCFBGc1dKyZ7tcYeLgGxD/u2NQPwiWeiZygB+CgE0DkhsPrnkpLDukCB3A2hk9V4/dqhfsKWCOB3J4p6XtuP2+h7S7HMyiY46WIRr/ss0fUdqG2Y44vCXAcNskycQOPQ88xyKghifbQ9GKNCo/X6mOdxpuvJjYPzxgaKmkXF6aEpLhqAAX4oVYvho6ymhY+p2o7kk0WUXdgQ/oIF/lR0vMeKptE8G6WkD4UgkIHcGBrwdPnd6cU0slCR5Tnvr0YSKsTe8KZ+AsiKIZMATx3tJJ3Eos3oyg7wPf/6L5YTjF+SUogZZ5plXwDf0uk6K2l9b0j57R04exsuCaCkeklAcxeE+2gN
     * AccessKeyId : STS.NTc11pNrpMCqH3bADxo7Rn4hB
     * AccessKeySecret : DjSYLGXvZ4mxMZg8WnjRZXZa4gy3maFQoVVsqbu9dU7P
     * Expiration : 2021-01-08T10:04:33Z
     * StatusCode : 200
     */

    private String SecurityToken;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String Expiration;
    private String StatusCode;

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String SecurityToken) {
        this.SecurityToken = SecurityToken;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String AccessKeyId) {
        this.AccessKeyId = AccessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String AccessKeySecret) {
        this.AccessKeySecret = AccessKeySecret;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String Expiration) {
        this.Expiration = Expiration;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }
}
