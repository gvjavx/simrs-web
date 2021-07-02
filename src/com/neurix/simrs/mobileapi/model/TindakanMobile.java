package com.neurix.simrs.mobileapi.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author gondok
 * Tuesday, 11/02/20 13:49
 */
public class TindakanMobile {
    private String idTindakan;
    private String tindakan;
    private String idKategoriTindakan;
    private String tarif;
    private String diskon;
    private String tarifBpjs;
    private String flag;
    private String action;
    private String createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(String tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
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

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }
}
