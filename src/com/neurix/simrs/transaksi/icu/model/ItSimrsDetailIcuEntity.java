package com.neurix.simrs.transaksi.icu.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsDetailIcuEntity {
    private String idDetailIcu;
    private String idHeaderIcu;
    private String nilai;
    private String waktu;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDetailChekcup;

    public String getIdDetailChekcup() {
        return idDetailChekcup;
    }

    public void setIdDetailChekcup(String idDetailChekcup) {
        this.idDetailChekcup = idDetailChekcup;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIdDetailIcu() {
        return idDetailIcu;
    }

    public void setIdDetailIcu(String idDetailIcu) {
        this.idDetailIcu = idDetailIcu;
    }

    public String getIdHeaderIcu() {
        return idHeaderIcu;
    }

    public void setIdHeaderIcu(String idHeaderIcu) {
        this.idHeaderIcu = idHeaderIcu;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
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
        ItSimrsDetailIcuEntity that = (ItSimrsDetailIcuEntity) o;
        return Objects.equals(idDetailIcu, that.idDetailIcu) &&
                Objects.equals(idHeaderIcu, that.idHeaderIcu) &&
                Objects.equals(nilai, that.nilai) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetailIcu, idHeaderIcu, nilai, flag, action, createdWho, createdDate, lastUpdate, lastUpdateWho);
    }
}
