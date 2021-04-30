package com.neurix.simrs.master.pasien.model;

import java.sql.Timestamp;

public class FingerData {
    private String idFingerData;
    private String idPasien;
    private String fingerData;
    private String Sn;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getSn() {
        return Sn;
    }

    public void setSn(String sn) {
        Sn = sn;
    }

    public String getIdFingerData() {
        return idFingerData;
    }

    public void setIdFingerData(String idFingerData) {
        this.idFingerData = idFingerData;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getFingerData() {
        return fingerData;
    }

    public void setFingerData(String fingerData) {
        this.fingerData = fingerData;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
