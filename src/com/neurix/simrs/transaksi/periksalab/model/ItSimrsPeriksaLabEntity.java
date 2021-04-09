package com.neurix.simrs.transaksi.periksalab.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsPeriksaLabEntity {
    private String idPeriksaLab;
    private String idHeaderPemeriksaan;
    private String idPemeriksaan;
    private String namaPemeriksaan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private String lastUpdateWho;
    private Timestamp lastUpdate;

    public String getIdPeriksaLab() {
        return idPeriksaLab;
    }

    public void setIdPeriksaLab(String idPeriksaLab) {
        this.idPeriksaLab = idPeriksaLab;
    }

    public String getIdHeaderPemeriksaan() {
        return idHeaderPemeriksaan;
    }

    public void setIdHeaderPemeriksaan(String idHeaderPemeriksaan) {
        this.idHeaderPemeriksaan = idHeaderPemeriksaan;
    }

    public String getIdPemeriksaan() {
        return idPemeriksaan;
    }

    public void setIdPemeriksaan(String idPemeriksaan) {
        this.idPemeriksaan = idPemeriksaan;
    }

    public String getNamaPemeriksaan() {
        return namaPemeriksaan;
    }

    public void setNamaPemeriksaan(String namaPemeriksaan) {
        this.namaPemeriksaan = namaPemeriksaan;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
