package com.neurix.simrs.master.jenisperiksapasien.model;

import java.sql.Timestamp;

public class Asuransi {

    private String idAsuransi;
    private String namaAsuransi;
    private String flag;
    private String action;
    private String stCreatedDate;
    private Timestamp createdDate;
    private String stLastUpdate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String isLaka;
    private String noMaster;

    public String getIsLaka() {
        return isLaka;
    }

    public void setIsLaka(String isLaka) {
        this.isLaka = isLaka;
    }



    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
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

    public String getNoMaster() {
        return noMaster;
    }

    public void setNoMaster(String noMaster) {
        this.noMaster = noMaster;
    }

    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }
}
