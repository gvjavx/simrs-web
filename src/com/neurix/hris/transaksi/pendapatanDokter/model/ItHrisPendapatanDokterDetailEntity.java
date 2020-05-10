package com.neurix.hris.transaksi.pendapatanDokter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ItHrisPendapatanDokterDetailEntity implements Serializable {

    String pendapatanDokterDetailId;
    String pendapatanDokterId;
    String activityId;
    String activityName;
    String poliId;
    String poliName;
    BigDecimal biaya;
    String noTrans;
    String flag;
    String action;
    Timestamp createdDate;
    Timestamp lastUpdate;
    String createdWho;
    String lastUpdateWho;

    String masterId;
    String jenisRawat;
    String kdjnspas;
    String namaPasien;
    Timestamp tanggal;
    String keterangan;
    BigDecimal tarifInacbg;
    BigDecimal bruto;
    BigDecimal pendapatanRs;
    BigDecimal hrBruto;
    BigDecimal dppPph21;
    BigDecimal dppPph21Komulatif;
    BigDecimal pajak;
    BigDecimal potonganPajak;
    BigDecimal hrAktifitasNetto;
    BigDecimal potonganKs;
    BigDecimal hrNetto;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getBiaya() {
        return biaya;
    }

    public void setBiaya(BigDecimal biaya) {
        this.biaya = biaya;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getNoTrans() {
        return noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    public String getPendapatanDokterDetailId() {
        return pendapatanDokterDetailId;
    }

    public void setPendapatanDokterDetailId(String pendapatanDokterDetailId) {
        this.pendapatanDokterDetailId = pendapatanDokterDetailId;
    }

    public String getPendapatanDokterId() {
        return pendapatanDokterId;
    }

    public void setPendapatanDokterId(String pendapatanDokterId) {
        this.pendapatanDokterId = pendapatanDokterId;
    }

    public String getPoliId() {
        return poliId;
    }

    public void setPoliId(String poliId) {
        this.poliId = poliId;
    }

    public String getPoliName() {
        return poliName;
    }

    public void setPoliName(String poliName) {
        this.poliName = poliName;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getDppPph21() {
        return dppPph21;
    }

    public void setDppPph21(BigDecimal dppPph21) {
        this.dppPph21 = dppPph21;
    }

    public BigDecimal getDppPph21Komulatif() {
        return dppPph21Komulatif;
    }

    public void setDppPph21Komulatif(BigDecimal dppPph21Komulatif) {
        this.dppPph21Komulatif = dppPph21Komulatif;
    }

    public BigDecimal getHrAktifitasNetto() {
        return hrAktifitasNetto;
    }

    public void setHrAktifitasNetto(BigDecimal hrAktifitasNetto) {
        this.hrAktifitasNetto = hrAktifitasNetto;
    }

    public BigDecimal getHrBruto() {
        return hrBruto;
    }

    public void setHrBruto(BigDecimal hrBruto) {
        this.hrBruto = hrBruto;
    }

    public BigDecimal getHrNetto() {
        return hrNetto;
    }

    public void setHrNetto(BigDecimal hrNetto) {
        this.hrNetto = hrNetto;
    }

    public String getJenisRawat() {
        return jenisRawat;
    }

    public void setJenisRawat(String jenisRawat) {
        this.jenisRawat = jenisRawat;
    }

    public String getKdjnspas() {
        return kdjnspas;
    }

    public void setKdjnspas(String kdjnspas) {
        this.kdjnspas = kdjnspas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public BigDecimal getPajak() {
        return pajak;
    }

    public void setPajak(BigDecimal pajak) {
        this.pajak = pajak;
    }

    public BigDecimal getPendapatanRs() {
        return pendapatanRs;
    }

    public void setPendapatanRs(BigDecimal pendapatanRs) {
        this.pendapatanRs = pendapatanRs;
    }

    public BigDecimal getPotonganKs() {
        return potonganKs;
    }

    public void setPotonganKs(BigDecimal potonganKs) {
        this.potonganKs = potonganKs;
    }

    public BigDecimal getPotonganPajak() {
        return potonganPajak;
    }

    public void setPotonganPajak(BigDecimal potonganPajak) {
        this.potonganPajak = potonganPajak;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getTarifInacbg() {
        return tarifInacbg;
    }

    public void setTarifInacbg(BigDecimal tarifInacbg) {
        this.tarifInacbg = tarifInacbg;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
}