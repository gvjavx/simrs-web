package com.neurix.hris.master.rsKerjasama.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class RsKerjasama extends BaseModel {
    private String rsId;
    private String kodeRs;
    private String rsName;
    private String alamatRs;
    private String tipeRs;


    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getKodeRs() {
        return kodeRs;
    }

    public void setKodeRs(String kodeRs) {
        this.kodeRs = kodeRs;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getAlamatRs() {
        return alamatRs;
    }

    public void setAlamatRs(String alamatRs) {
        this.alamatRs = alamatRs;
    }

    public String getTipeRs() {
        return tipeRs;
    }

    public void setTipeRs(String tipeRs) {
        this.tipeRs = tipeRs;
    }
}