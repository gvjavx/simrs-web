package com.neurix.simrs.transaksi.periksalab.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsUploadHasilPemeriksaanEntity {
    private String idUploadHasilPemeriksaan;
    private String idHeaderPemeriksaan;
    private String urlImg;
    private String tipe;
    private String action;
    private String flag;
    private Timestamp lastUpdate;
    private Timestamp createdDate;
    private String createdWho;
    private String lastUpdateWho;
    private String idPeriksaLabDetail;
    private String namaDetailPeriksa;

    public String getIdPeriksaLabDetail() {
        return idPeriksaLabDetail;
    }

    public void setIdPeriksaLabDetail(String idPeriksaLabDetail) {
        this.idPeriksaLabDetail = idPeriksaLabDetail;
    }

    public String getNamaDetailPeriksa() {
        return namaDetailPeriksa;
    }

    public void setNamaDetailPeriksa(String namaDetailPeriksa) {
        this.namaDetailPeriksa = namaDetailPeriksa;
    }

    public String getIdUploadHasilPemeriksaan() {
        return idUploadHasilPemeriksaan;
    }

    public void setIdUploadHasilPemeriksaan(String idUploadHasilPemeriksaan) {
        this.idUploadHasilPemeriksaan = idUploadHasilPemeriksaan;
    }

    public String getIdHeaderPemeriksaan() {
        return idHeaderPemeriksaan;
    }

    public void setIdHeaderPemeriksaan(String idHeaderPemeriksaan) {
        this.idHeaderPemeriksaan = idHeaderPemeriksaan;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

}
