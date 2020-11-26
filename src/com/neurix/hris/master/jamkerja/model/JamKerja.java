package com.neurix.hris.master.jamkerja.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class JamKerja extends BaseModel implements Serializable {
    private String jamKerjaId;
    private String statusGiling;
    private String tipePegawaiId;
    private Integer hariKerja;
    private String jamAwalKerja;
    private String jamAkhirKerja;
    private String branchId;
    private String branchName;
    private String istirahatAwal;
    private String istirahatAkhir;

    private String stCreatedDate;
    private String stLastUpdate;
    private String tipePegawaiName;

    private String hariName;

    public String getIstirahatAwal() {
        return istirahatAwal;
    }

    public void setIstirahatAwal(String istirahatAwal) {
        this.istirahatAwal = istirahatAwal;
    }

    public String getIstirahatAkhir() {
        return istirahatAkhir;
    }

    public void setIstirahatAkhir(String istirahatAkhir) {
        this.istirahatAkhir = istirahatAkhir;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getHariName() {
        return hariName;
    }

    public void setHariName(String hariName) {
        this.hariName = hariName;
    }

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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }
}
