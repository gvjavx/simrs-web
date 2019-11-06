package com.neurix.hris.transaksi.notifikasi.model;

import com.neurix.common.model.BaseModel;

public class NotifikasiFcm extends BaseModel{
    private String userId;
    private String userName;
    private String tokenFcm;

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
}
