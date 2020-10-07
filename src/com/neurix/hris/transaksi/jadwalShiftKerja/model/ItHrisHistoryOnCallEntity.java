package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ItHrisHistoryOnCallEntity implements Serializable {
    private String historyOnCallId;
    private String jadwalShiftKerjaDetailId;
    private Timestamp panggilDate;
    private String nip;
    private String keteranganPanggil;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getKeteranganPanggil() {
        return keteranganPanggil;
    }

    public void setKeteranganPanggil(String keteranganPanggil) {
        this.keteranganPanggil = keteranganPanggil;
    }

    public String getHistoryOnCallId() {
        return historyOnCallId;
    }

    public void setHistoryOnCallId(String historyOnCallId) {
        this.historyOnCallId = historyOnCallId;
    }

    public String getJadwalShiftKerjaDetailId() {
        return jadwalShiftKerjaDetailId;
    }

    public void setJadwalShiftKerjaDetailId(String jadwalShiftKerjaDetailId) {
        this.jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailId;
    }

    public Timestamp getPanggilDate() {
        return panggilDate;
    }

    public void setPanggilDate(Timestamp panggilDate) {
        this.panggilDate = panggilDate;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        ItHrisHistoryOnCallEntity that = (ItHrisHistoryOnCallEntity) o;
        return Objects.equals(historyOnCallId, that.historyOnCallId) &&
                Objects.equals(jadwalShiftKerjaDetailId, that.jadwalShiftKerjaDetailId) &&
                Objects.equals(panggilDate, that.panggilDate) &&
                Objects.equals(nip, that.nip) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyOnCallId, jadwalShiftKerjaDetailId, panggilDate, nip, flag, action, createdDate, lastUpdate, createdWho, lastUpdateWho);
    }
}
