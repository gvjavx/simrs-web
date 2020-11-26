package com.neurix.simrs.master.lab.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImSimrsLabEntity implements Serializable {

    private String idLab;
    private String namaLab;
    private String idOperatorLab;
    private String idDokter;
    private String idKategoriLab;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getIdOperatorLab() {
        return idOperatorLab;
    }

    public void setIdOperatorLab(String idOperatorLab) {
        this.idOperatorLab = idOperatorLab;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdKategoriLab() {
        return idKategoriLab;
    }

    public void setIdKategoriLab(String idKategoriLab) {
        this.idKategoriLab = idKategoriLab;
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