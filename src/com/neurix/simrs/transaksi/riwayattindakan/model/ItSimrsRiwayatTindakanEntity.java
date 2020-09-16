package com.neurix.simrs.transaksi.riwayattindakan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ItSimrsRiwayatTindakanEntity implements Serializable {

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

    private String flagUpdateKlaim;
    private Timestamp tanggalTindakan;
    private String rekanan;
    private String isKamar;
    private String idRuangan;

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getIsKamar() {
        return isKamar;
    }

    public void setIsKamar(String isKamar) {
        this.isKamar = isKamar;
    }

    public String getRekanan() {
        return rekanan;
    }

    public void setRekanan(String rekanan) {
        this.rekanan = rekanan;
    }

    public Timestamp getTanggalTindakan() {
        return tanggalTindakan;
    }

    public void setTanggalTindakan(Timestamp tanggalTindakan) {
        this.tanggalTindakan = tanggalTindakan;
    }

    public String getFlagUpdateKlaim() {
        return flagUpdateKlaim;
    }

    public void setFlagUpdateKlaim(String flagUpdateKlaim) {
        this.flagUpdateKlaim = flagUpdateKlaim;
    }

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