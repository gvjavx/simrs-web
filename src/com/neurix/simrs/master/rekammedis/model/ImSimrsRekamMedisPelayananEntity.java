package com.neurix.simrs.master.rekammedis.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsRekamMedisPelayananEntity {
    private String idRekamMedisPelayanan;
    private String idRekamMedisPasien;
    private String tipePelayanan;
    private String urutan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String tipe;

    public String getIdRekamMedisPelayanan() {
        return idRekamMedisPelayanan;
    }

    public void setIdRekamMedisPelayanan(String idRekamMedisPelayanan) {
        this.idRekamMedisPelayanan = idRekamMedisPelayanan;
    }

    public String getIdRekamMedisPasien() {
        return idRekamMedisPasien;
    }

    public void setIdRekamMedisPasien(String idRekamMedisPasien) {
        this.idRekamMedisPasien = idRekamMedisPasien;
    }

    public String getTipePelayanan() {
        return tipePelayanan;
    }

    public void setTipePelayanan(String tipePelayanan) {
        this.tipePelayanan = tipePelayanan;
    }

    public String getUrutan() {
        return urutan;
    }

    public void setUrutan(String urutan) {
        this.urutan = urutan;
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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsRekamMedisPelayananEntity that = (ImSimrsRekamMedisPelayananEntity) o;
        return Objects.equals(idRekamMedisPelayanan, that.idRekamMedisPelayanan) &&
                Objects.equals(idRekamMedisPasien, that.idRekamMedisPasien) &&
                Objects.equals(tipePelayanan, that.tipePelayanan) &&
                Objects.equals(urutan, that.urutan) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(tipe, that.tipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRekamMedisPelayanan, idRekamMedisPasien, tipePelayanan, urutan, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho, tipe);
    }
}
