package com.neurix.hris.mobileapi.model;

import java.io.Serializable;

public class ProfilSisaCuti implements Serializable {

    private String nip;
    private String namaCuti;
    private String sisaCuti;
    private String cutiId;
    private String flag;

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaCuti() {
        return namaCuti;
    }

    public void setNamaCuti(String namaCuti) {
        this.namaCuti = namaCuti;
    }

    public String getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(String sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
