package com.neurix.hris.transaksi.notifikasi.model;

import com.neurix.common.model.BaseModel;

public class NotifikasiFcm extends BaseModel{
    private String userId;
    private String userName;
    private String tokenFcm;
    private String tokenExpo;
    private String os;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTokenFcm() {
        return tokenFcm;
    }

    public void setTokenFcm(String tokenFcm) {
        this.tokenFcm = tokenFcm;
    }

    public String getTokenExpo() {
        return tokenExpo;
    }

    public void setTokenExpo(String tokenExpo) {
        this.tokenExpo = tokenExpo;
    }
}
