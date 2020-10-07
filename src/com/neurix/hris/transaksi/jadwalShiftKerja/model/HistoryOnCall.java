package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class HistoryOnCall extends BaseModel {
    private String historyOnCallId;
    private String jadwalShiftKerjaDetailId;
    private Timestamp panggilDate;
    private String stPanggilDate;
    private String nip;
    private String keteranganPanggil;

    public String getKeteranganPanggil() {
        return keteranganPanggil;
    }

    public void setKeteranganPanggil(String keteranganPanggil) {
        this.keteranganPanggil = keteranganPanggil;
    }

    public String getStPanggilDate() {
        return stPanggilDate;
    }

    public void setStPanggilDate(String stPanggilDate) {
        this.stPanggilDate = stPanggilDate;
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
}
