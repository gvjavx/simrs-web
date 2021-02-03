package com.neurix.simrs.master.pelayanan.model;

import java.sql.Timestamp;

public class ImSimrsHeaderPelayananEntity {
    private String idHeaderPelayanan;
    private String namaPelayanan;
    private String tipePelayanan;
    private String kategoriPelayanan;
    private String kodeVclaim;
    private String divisiId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String isVaksin;

    public String getIsVaksin() {
        return isVaksin;
    }

    public void setIsVaksin(String isVaksin) {
        this.isVaksin = isVaksin;
    }

    public String getIdHeaderPelayanan() {
        return idHeaderPelayanan;
    }

    public void setIdHeaderPelayanan(String idHeaderPelayanan) {
        this.idHeaderPelayanan = idHeaderPelayanan;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    public String getKategoriPelayanan() {
        return kategoriPelayanan;
    }

    public void setKategoriPelayanan(String kategoriPelayanan) {
        this.kategoriPelayanan = kategoriPelayanan;
    }

    public String getKodeVclaim() {
        return kodeVclaim;
    }

    public void setKodeVclaim(String kodeVclaim) {
        this.kodeVclaim = kodeVclaim;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
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
