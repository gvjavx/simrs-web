package com.neurix.simrs.transaksi.periksalab.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderPeriksaLab {
    private String idOrderPeriksa;
    private String idDetailCheckup;
    private String idPemeriksaan;
    private String namaPemeriksaan;
    private String idLabDetail;
    private String namaDetailPemeriksaan;
    private String idKategoriLab;
    private String keterangan;
    private String isPemeriksaan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private List<OrderPeriksaLab> detaiLab = new ArrayList<>();

    public List<OrderPeriksaLab> getDetaiLab() {
        return detaiLab;
    }

    public void setDetaiLab(List<OrderPeriksaLab> detaiLab) {
        this.detaiLab = detaiLab;
    }

    public String getIdOrderPeriksa() {
        return idOrderPeriksa;
    }

    public void setIdOrderPeriksa(String idOrderPeriksa) {
        this.idOrderPeriksa = idOrderPeriksa;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
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

    public String getIdLabDetail() {
        return idLabDetail;
    }

    public void setIdLabDetail(String idLabDetail) {
        this.idLabDetail = idLabDetail;
    }

    public String getNamaDetailPemeriksaan() {
        return namaDetailPemeriksaan;
    }

    public void setNamaDetailPemeriksaan(String namaDetailPemeriksaan) {
        this.namaDetailPemeriksaan = namaDetailPemeriksaan;
    }

    public String getIdKategoriLab() {
        return idKategoriLab;
    }

    public void setIdKategoriLab(String idKategoriLab) {
        this.idKategoriLab = idKategoriLab;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIsPemeriksaan() {
        return isPemeriksaan;
    }

    public void setIsPemeriksaan(String isPemeriksaan) {
        this.isPemeriksaan = isPemeriksaan;
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
