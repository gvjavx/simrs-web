package com.neurix.simrs.mobileapi.model;

import java.sql.Timestamp;

/**
 * @author gondok
 * Tuesday, 11/02/20 13:42
 */
public class KategoriTindakanMobile {
    private String idKategoriTindakan;
    private String kategoriTindakan;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public String getKategoriTindakan() {
        return kategoriTindakan;
    }

    public void setKategoriTindakan(String kategoriTindakan) {
        this.kategoriTindakan = kategoriTindakan;
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
