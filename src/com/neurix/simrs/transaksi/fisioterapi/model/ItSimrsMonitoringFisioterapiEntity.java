package com.neurix.simrs.transaksi.fisioterapi.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ItSimrsMonitoringFisioterapiEntity implements Serializable {

    private String idMonitoringFisioterapi;
    private String idDetailCheckup;
    private Date tanggal;
    private String tindakan;
    private String keterangan;
    private String fisioterapi;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

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

    public String getIdMonitoringFisioterapi() {
        return idMonitoringFisioterapi;
    }

    public void setIdMonitoringFisioterapi(String idMonitoringFisioterapi) {
        this.idMonitoringFisioterapi = idMonitoringFisioterapi;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFisioterapi() {
        return fisioterapi;
    }

    public void setFisioterapi(String fisioterapi) {
        this.fisioterapi = fisioterapi;
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
}
