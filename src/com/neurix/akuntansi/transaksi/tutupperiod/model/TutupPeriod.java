package com.neurix.akuntansi.transaksi.tutupperiod.model;

import java.sql.Timestamp;

/**
 * Created by reza on 18/03/20.
 */
public class TutupPeriod {
    private String id;
    private String tahun;
    private String bulan;
    private String stTglAkhirPeriod;
    private String stTglAwalPeriod;
    private String unit;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flagTutup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getStTglAkhirPeriod() {
        return stTglAkhirPeriod;
    }

    public void setStTglAkhirPeriod(String stTglAkhirPeriod) {
        this.stTglAkhirPeriod = stTglAkhirPeriod;
    }

    public String getStTglAwalPeriod() {
        return stTglAwalPeriod;
    }

    public void setStTglAwalPeriod(String stTglAwalPeriod) {
        this.stTglAwalPeriod = stTglAwalPeriod;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getFlagTutup() {
        return flagTutup;
    }

    public void setFlagTutup(String flagTutup) {
        this.flagTutup = flagTutup;
    }
}
