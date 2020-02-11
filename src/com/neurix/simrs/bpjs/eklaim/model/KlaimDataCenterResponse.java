package com.neurix.simrs.bpjs.eklaim.model;

public class KlaimDataCenterResponse {
    private String SEP;
    private String tglPulang;
    private String kemkesDcStatus;
    private String bpjsDcStatus;
    private String cobDcStatus;

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCobDcStatus() {
        return cobDcStatus;
    }

    public void setCobDcStatus(String cobDcStatus) {
        this.cobDcStatus = cobDcStatus;
    }

    public String getSEP() {
        return SEP;
    }

    public void setSEP(String SEP) {
        this.SEP = SEP;
    }

    public String getTglPulang() {
        return tglPulang;
    }

    public void setTglPulang(String tglPulang) {
        this.tglPulang = tglPulang;
    }

    public String getKemkesDcStatus() {
        return kemkesDcStatus;
    }

    public void setKemkesDcStatus(String kemkesDcStatus) {
        this.kemkesDcStatus = kemkesDcStatus;
    }

    public String getBpjsDcStatus() {
        return bpjsDcStatus;
    }

    public void setBpjsDcStatus(String bpjsDcStatus) {
        this.bpjsDcStatus = bpjsDcStatus;
    }
}
