package com.neurix.simrs.master.lab.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImSimrsLabEntity implements Serializable {

    private String idLab;
    private String namaLab;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String isCatatan;
    private String isPaket;
    private String isAsesmen;

    public String getIsAsesmen() {
        return isAsesmen;
    }

    public void setIsAsesmen(String isAsesmen) {
        this.isAsesmen = isAsesmen;
    }

    public String getIsPaket() {
        return isPaket;
    }

    public void setIsPaket(String isPaket) {
        this.isPaket = isPaket;
    }

    public String getIsCatatan() {
        return isCatatan;
    }

    public void setIsCatatan(String isCatatan) {
        this.isCatatan = isCatatan;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getNamaLab() {
        return namaLab;
    }

    public void setNamaLab(String namaLab) {
        this.namaLab = namaLab;
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