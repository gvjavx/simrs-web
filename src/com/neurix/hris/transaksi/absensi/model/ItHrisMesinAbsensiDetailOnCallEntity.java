package com.neurix.hris.transaksi.absensi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItHrisMesinAbsensiDetailOnCallEntity {
    private String mesinAbsensiDetailOnCallId;
    private String pin;
    private String status;
    private Timestamp scanDate;
    private String action;
    private String flag;
    private Timestamp lastUpdate;
    private Timestamp createdDate;
    private String createdWho;
    private String lastUpdateWho;

    public String getMesinAbsensiDetailOnCallId() {
        return mesinAbsensiDetailOnCallId;
    }

    public void setMesinAbsensiDetailOnCallId(String mesinAbsensiDetailOnCallId) {
        this.mesinAbsensiDetailOnCallId = mesinAbsensiDetailOnCallId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getScanDate() {
        return scanDate;
    }

    public void setScanDate(Timestamp scanDate) {
        this.scanDate = scanDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        ItHrisMesinAbsensiDetailOnCallEntity that = (ItHrisMesinAbsensiDetailOnCallEntity) o;
        return Objects.equals(mesinAbsensiDetailOnCallId, that.mesinAbsensiDetailOnCallId) &&
                Objects.equals(pin, that.pin) &&
                Objects.equals(status, that.status) &&
                Objects.equals(scanDate, that.scanDate) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mesinAbsensiDetailOnCallId, pin, status, scanDate, action, flag, lastUpdate, createdDate, createdWho, lastUpdateWho);
    }
}
