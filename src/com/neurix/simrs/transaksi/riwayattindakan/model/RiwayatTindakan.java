package com.neurix.simrs.transaksi.riwayattindakan.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RiwayatTindakan extends BaseModel {

    private String idRiwayatTindakan;
    private String idTindakan;
    private String namaTindakan;
    private String keterangan;
    private String jenisPasien;
    private BigDecimal totalTarif;
    private String kategoriTindakanBpjs;
    private String approveBpjsFlag;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDetailCheckup;

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdRiwayatTindakan() {
        return idRiwayatTindakan;
    }

    public void setIdRiwayatTindakan(String idRiwayatTindakan) {
        this.idRiwayatTindakan = idRiwayatTindakan;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJenisPasien() {
        return jenisPasien;
    }

    public void setJenisPasien(String jenisPasien) {
        this.jenisPasien = jenisPasien;
    }

    public BigDecimal getTotalTarif() {
        return totalTarif;
    }

    public void setTotalTarif(BigDecimal totalTarif) {
        this.totalTarif = totalTarif;
    }

    public String getKategoriTindakanBpjs() {
        return kategoriTindakanBpjs;
    }

    public void setKategoriTindakanBpjs(String kategoriTindakanBpjs) {
        this.kategoriTindakanBpjs = kategoriTindakanBpjs;
    }

    public String getApproveBpjsFlag() {
        return approveBpjsFlag;
    }

    public void setApproveBpjsFlag(String approveBpjsFlag) {
        this.approveBpjsFlag = approveBpjsFlag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}