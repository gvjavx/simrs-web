package com.neurix.simrs.master.parampemeriksaan.model;

import java.sql.Timestamp;

public class ParameterPemeriksaan {
    private String idParameterPemeriksaan;
    private String idPelayanan;
    private String namaPemeriksaan;
    private String keteranganAcuanL;
    private String keteranganAcuanP;
    private String satuan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdParameterPemeriksaan() {
        return idParameterPemeriksaan;
    }

    public void setIdParameterPemeriksaan(String idParameterPemeriksaan) {
        this.idParameterPemeriksaan = idParameterPemeriksaan;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaPemeriksaan() {
        return namaPemeriksaan;
    }

    public void setNamaPemeriksaan(String namaPemeriksaan) {
        this.namaPemeriksaan = namaPemeriksaan;
    }

    public String getKeteranganAcuanL() {
        return keteranganAcuanL;
    }

    public void setKeteranganAcuanL(String keteranganAcuanL) {
        this.keteranganAcuanL = keteranganAcuanL;
    }

    public String getKeteranganAcuanP() {
        return keteranganAcuanP;
    }

    public void setKeteranganAcuanP(String keteranganAcuanP) {
        this.keteranganAcuanP = keteranganAcuanP;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
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
