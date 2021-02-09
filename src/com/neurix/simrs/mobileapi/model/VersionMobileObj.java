package com.neurix.simrs.mobileapi.model;

public class VersionMobileObj {
    private String idVersionMobile;
    private String namaVersion;
    private String os;
    private String flag;
    private String action;
    private String createdDate;
    private String lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdVersionMobile() {
        return idVersionMobile;
    }

    public void setIdVersionMobile(String idVersionMobile) {
        this.idVersionMobile = idVersionMobile;
    }

    public String getNamaVersion() {
        return namaVersion;
    }

    public void setNamaVersion(String namaVersion) {
        this.namaVersion = namaVersion;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
