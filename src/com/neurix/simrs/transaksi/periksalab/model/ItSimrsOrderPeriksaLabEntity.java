package com.neurix.simrs.transaksi.periksalab.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsOrderPeriksaLabEntity {
    private String idOrderPeriksa;
    private String idDetailCheckup;
    private String idLab;
    private String idLabDetail;
    private String isPemeriksaan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String keterangan;

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

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getIdLabDetail() {
        return idLabDetail;
    }

    public void setIdLabDetail(String idLabDetail) {
        this.idLabDetail = idLabDetail;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItSimrsOrderPeriksaLabEntity that = (ItSimrsOrderPeriksaLabEntity) o;
        return Objects.equals(idOrderPeriksa, that.idOrderPeriksa) &&
                Objects.equals(idDetailCheckup, that.idDetailCheckup) &&
                Objects.equals(idLab, that.idLab) &&
                Objects.equals(idLabDetail, that.idLabDetail) &&
                Objects.equals(isPemeriksaan, that.isPemeriksaan) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(keterangan, that.keterangan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderPeriksa, idDetailCheckup, idLab, idLabDetail, isPemeriksaan, flag, action, createdDate, createdWho, lastUpdate, lastUpdateWho, keterangan);
    }
}
