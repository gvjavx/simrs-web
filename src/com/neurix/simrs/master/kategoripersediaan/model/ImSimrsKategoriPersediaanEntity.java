package com.neurix.simrs.master.kategoripersediaan.model;

import java.sql.Timestamp;

/**
 * Created by reza on 30/09/20.
 */
public class ImSimrsKategoriPersediaanEntity {
    private String idKategoriPersediaan;
    private String nama;
    private String rekeningId;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdKategoriPersediaan() {
        return idKategoriPersediaan;
    }

    public void setIdKategoriPersediaan(String idKategoriPersediaan) {
        this.idKategoriPersediaan = idKategoriPersediaan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
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

        ImSimrsKategoriPersediaanEntity that = (ImSimrsKategoriPersediaanEntity) o;

        if (idKategoriPersediaan != null ? !idKategoriPersediaan.equals(that.idKategoriPersediaan) : that.idKategoriPersediaan != null)
            return false;
        if (nama != null ? !nama.equals(that.nama) : that.nama != null) return false;
        if (rekeningId != null ? !rekeningId.equals(that.rekeningId) : that.rekeningId != null) return false;
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
        int result = idKategoriPersediaan != null ? idKategoriPersediaan.hashCode() : 0;
        result = 31 * result + (nama != null ? nama.hashCode() : 0);
        result = 31 * result + (rekeningId != null ? rekeningId.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}