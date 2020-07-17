package com.neurix.simrs.master.rekammedis.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsRekamMedisPasienEntity {
    private String idRekamMedisPasien;
    private String kodeRm;
    private String namaRm;
    private String keterangan;
    private String jenis;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String function;

    public String getIdRekamMedisPasien() {
        return idRekamMedisPasien;
    }

    public void setIdRekamMedisPasien(String idRekamMedisPasien) {
        this.idRekamMedisPasien = idRekamMedisPasien;
    }

    public String getKodeRm() {
        return kodeRm;
    }

    public void setKodeRm(String kodeRm) {
        this.kodeRm = kodeRm;
    }

    public String getNamaRm() {
        return namaRm;
    }

    public void setNamaRm(String namaRm) {
        this.namaRm = namaRm;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsRekamMedisPasienEntity that = (ImSimrsRekamMedisPasienEntity) o;
        return Objects.equals(idRekamMedisPasien, that.idRekamMedisPasien) &&
                Objects.equals(kodeRm, that.kodeRm) &&
                Objects.equals(namaRm, that.namaRm) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(jenis, that.jenis) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRekamMedisPasien, kodeRm, namaRm, keterangan, jenis, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho, function);
    }
}
