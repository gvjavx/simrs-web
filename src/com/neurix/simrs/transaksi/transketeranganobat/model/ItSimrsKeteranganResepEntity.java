package com.neurix.simrs.transaksi.transketeranganobat.model;

import java.sql.Timestamp;

public class ItSimrsKeteranganResepEntity {
    private String id;
    private String idJenisObat;
    private String idKeteranganObat;
    private String keteranganLain;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idPermintaanResep;
    private String idObat;

    public String getIdPermintaanResep() {
        return idPermintaanResep;
    }

    public void setIdPermintaanResep(String idPermintaanResep) {
        this.idPermintaanResep = idPermintaanResep;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJenisObat() {
        return idJenisObat;
    }

    public void setIdJenisObat(String idJenisObat) {
        this.idJenisObat = idJenisObat;
    }

    public String getIdKeteranganObat() {
        return idKeteranganObat;
    }

    public void setIdKeteranganObat(String idKeteranganObat) {
        this.idKeteranganObat = idKeteranganObat;
    }

    public String getKeteranganLain() {
        return keteranganLain;
    }

    public void setKeteranganLain(String keteranganLain) {
        this.keteranganLain = keteranganLain;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItSimrsKeteranganResepEntity that = (ItSimrsKeteranganResepEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idJenisObat != null ? !idJenisObat.equals(that.idJenisObat) : that.idJenisObat != null) return false;
        if (idKeteranganObat != null ? !idKeteranganObat.equals(that.idKeteranganObat) : that.idKeteranganObat != null)
            return false;
        if (keteranganLain != null ? !keteranganLain.equals(that.keteranganLain) : that.keteranganLain != null)
            return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idJenisObat != null ? idJenisObat.hashCode() : 0);
        result = 31 * result + (idKeteranganObat != null ? idKeteranganObat.hashCode() : 0);
        result = 31 * result + (keteranganLain != null ? keteranganLain.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
