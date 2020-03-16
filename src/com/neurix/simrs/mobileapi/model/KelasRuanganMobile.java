package com.neurix.simrs.mobileapi.model;

import java.sql.Timestamp;

/**
 * @author gondok
 * Friday, 14/02/20 19:04
 */
public class KelasRuanganMobile {
    private String idKelasRuangan;
    private String namaKelasRuangan;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    public String getIdKelasRuangan() {
        return idKelasRuangan;
    }

    public void setIdKelasRuangan(String idKelasRuangan) {
        this.idKelasRuangan = idKelasRuangan;
    }

    public String getNamaKelasRuangan() {
        return namaKelasRuangan;
    }

    public void setNamaKelasRuangan(String namaKelasRuangan) {
        this.namaKelasRuangan = namaKelasRuangan;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
