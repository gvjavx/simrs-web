package com.neurix.simrs.transaksi.rekammedik.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsStatusPengisianRekamMedisEntity {

    private String idStatusPengisianRekamMedis;
    private String noCheckup;
    private String idDetailCheckup;
    private String idPasien;
    private String idRekamMedisPasien;
    private String isPengisian;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String jumlahKategori;

    public String getJumlahKategori() {
        return jumlahKategori;
    }

    public void setJumlahKategori(String jumlahKategori) {
        this.jumlahKategori = jumlahKategori;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdStatusPengisianRekamMedis() {
        return idStatusPengisianRekamMedis;
    }

    public void setIdStatusPengisianRekamMedis(String idStatusPengisianRekamMedis) {
        this.idStatusPengisianRekamMedis = idStatusPengisianRekamMedis;
    }

    public String getIdRekamMedisPasien() {
        return idRekamMedisPasien;
    }

    public void setIdRekamMedisPasien(String idRekamMedisPasien) {
        this.idRekamMedisPasien = idRekamMedisPasien;
    }

    public String getIsPengisian() {
        return isPengisian;
    }

    public void setIsPengisian(String isPengisian) {
        this.isPengisian = isPengisian;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItSimrsStatusPengisianRekamMedisEntity that = (ItSimrsStatusPengisianRekamMedisEntity) o;
        return Objects.equals(idStatusPengisianRekamMedis, that.idStatusPengisianRekamMedis) &&
                Objects.equals(idRekamMedisPasien, that.idRekamMedisPasien) &&
                Objects.equals(isPengisian, that.isPengisian) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStatusPengisianRekamMedis, idRekamMedisPasien, isPengisian, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
