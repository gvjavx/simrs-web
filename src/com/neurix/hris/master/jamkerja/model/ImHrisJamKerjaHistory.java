package com.neurix.hris.master.jamkerja.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ImHrisJamKerjaHistory implements Serializable {
    private String jamKerjaId;
    private String statusGiling;
    private String tipePegawaiId;
    private String branchId;
    private Integer hariKerja;
    private String jamAwalKerja;
    private String jamAkhirKerja;
    private String istirahatAwal;
    private String istirahatAkhir;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String jamKerjaIdHistory;

    public String getJamKerjaId() {
        return jamKerjaId;
    }

    public void setJamKerjaId(String jamKerjaId) {
        this.jamKerjaId = jamKerjaId;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public Integer getHariKerja() {
        return hariKerja;
    }

    public void setHariKerja(Integer hariKerja) {
        this.hariKerja = hariKerja;
    }

    public String getJamAwalKerja() {
        return jamAwalKerja;
    }

    public void setJamAwalKerja(String jamAwalKerja) {
        this.jamAwalKerja = jamAwalKerja;
    }

    public String getJamAkhirKerja() {
        return jamAkhirKerja;
    }

    public void setJamAkhirKerja(String jamAkhirKerja) {
        this.jamAkhirKerja = jamAkhirKerja;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateDateWho() {
        return createDateWho;
    }

    public void setCreateDateWho(String createDateWho) {
        this.createDateWho = createDateWho;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIstirahatAkhir() {
        return istirahatAkhir;
    }

    public void setIstirahatAkhir(String istirahatAkhir) {
        this.istirahatAkhir = istirahatAkhir;
    }

    public String getIstirahatAwal() {
        return istirahatAwal;
    }

    public void setIstirahatAwal(String istirahatAwal) {
        this.istirahatAwal = istirahatAwal;
    }

    public String getJamKerjaIdHistory() {
        return jamKerjaIdHistory;
    }

    public void setJamKerjaIdHistory(String jamKerjaIdHistory) {
        this.jamKerjaIdHistory = jamKerjaIdHistory;
    }
}
