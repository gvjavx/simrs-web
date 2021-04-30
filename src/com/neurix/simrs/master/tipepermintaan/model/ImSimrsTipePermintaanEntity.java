package com.neurix.simrs.master.tipepermintaan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class ImSimrsTipePermintaanEntity implements Serializable {

    private String idPermintaan;
    private String namaPermintaan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public String getNamaPermintaan() {
        return namaPermintaan;
    }

    public void setNamaPermintaan(String namaPermintaan) {
        this.namaPermintaan = namaPermintaan;
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
