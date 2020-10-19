package com.neurix.hris.transaksi.absensi.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ItHrisAbsensiOnCallEntity {
    private String absensiOnCallId;
    private String nip;
    private Date tanggal;
    private String jamMasuk;
    private String jamPulang;
    private BigDecimal lamaLembur;

    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public BigDecimal getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(BigDecimal lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public String getAbsensiOnCallId() {
        return absensiOnCallId;
    }

    public void setAbsensiOnCallId(String absensiOnCallId) {
        this.absensiOnCallId = absensiOnCallId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItHrisAbsensiOnCallEntity that = (ItHrisAbsensiOnCallEntity) o;
        return Objects.equals(absensiOnCallId, that.absensiOnCallId) &&
                Objects.equals(nip, that.nip) &&
                Objects.equals(tanggal, that.tanggal) &&
                Objects.equals(jamMasuk, that.jamMasuk) &&
                Objects.equals(jamPulang, that.jamPulang) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(absensiOnCallId, nip, tanggal, jamMasuk, jamPulang, flag, action, createdWho, lastUpdateWho, createdDate, lastUpdate);
    }
}
