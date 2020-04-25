package com.neurix.hris.transaksi.pendapatanDokter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class ItHrisPendapatanDokterEntity implements Serializable {

    String pendapatanDokterId;
    String dokterId;
    String dokterName;
    String branchId;
    String bulan;
    String tahun;
    BigDecimal bruto;
    BigDecimal dppPph50;
    BigDecimal dppPph21Komulatif;
    BigDecimal dppPph21;
    BigDecimal tarif;
    BigDecimal pphDipungut;
    BigDecimal gajiBersih;
    String approvalFlag;
    String approvalWho;
    Timestamp approvalDate;
    String flagDiterima;
    Timestamp dateDiterima;
    String pemberiWho;

    Date tanggalJurnal;
    String jurnalDetailId;
    String divisiId;
    String activityId;
    String personId;
    Double jumlahBulanIni;
    Double jumlahBulanLalu;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    BigDecimal pendapatanRs;
    BigDecimal hrBruto;
    BigDecimal hrAktifitasNetto;
    BigDecimal potKs;

    private String noNota;

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalWho() {
        return approvalWho;
    }

    public void setApprovalWho(String approvalWho) {
        this.approvalWho = approvalWho;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Date getDateDiterima() {
        return dateDiterima;
    }

    public String getDokterId() {
        return dokterId;
    }

    public void setDokterId(String dokterId) {
        this.dokterId = dokterId;
    }

    public String getDokterName() {
        return dokterName;
    }

    public void setDokterName(String dokterName) {
        this.dokterName = dokterName;
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

    public BigDecimal getDppPph50() {
        return dppPph50;
    }

    public void setDppPph50(BigDecimal dppPph50) {
        this.dppPph50 = dppPph50;
    }

    public String getFlagDiterima() {
        return flagDiterima;
    }

    public void setFlagDiterima(String flagDiterima) {
        this.flagDiterima = flagDiterima;
    }

    public BigDecimal getGajiBersih() {
        return gajiBersih;
    }

    public void setGajiBersih(BigDecimal gajiBersih) {
        this.gajiBersih = gajiBersih;
    }

    public String getPemberiWho() {
        return pemberiWho;
    }

    public void setPemberiWho(String pemberiWho) {
        this.pemberiWho = pemberiWho;
    }

    public String getPendapatanDokterId() {
        return pendapatanDokterId;
    }

    public void setPendapatanDokterId(String pendapatanDokterId) {
        this.pendapatanDokterId = pendapatanDokterId;
    }

    public BigDecimal getPphDipungut() {
        return pphDipungut;
    }

    public void setPphDipungut(BigDecimal pphDipungut) {
        this.pphDipungut = pphDipungut;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setDateDiterima(Timestamp dateDiterima) {
        this.dateDiterima = dateDiterima;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public Double getJumlahBulanIni() {
        return jumlahBulanIni;
    }

    public void setJumlahBulanIni(Double jumlahBulanIni) {
        this.jumlahBulanIni = jumlahBulanIni;
    }

    public Double getJumlahBulanLalu() {
        return jumlahBulanLalu;
    }

    public void setJumlahBulanLalu(Double jumlahBulanLalu) {
        this.jumlahBulanLalu = jumlahBulanLalu;
    }

    public String getJurnalDetailId() {
        return jurnalDetailId;
    }

    public void setJurnalDetailId(String jurnalDetailId) {
        this.jurnalDetailId = jurnalDetailId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Date getTanggalJurnal() {
        return tanggalJurnal;
    }

    public void setTanggalJurnal(Date tanggalJurnal) {
        this.tanggalJurnal = tanggalJurnal;
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

    public BigDecimal getPendapatanRs() {
        return pendapatanRs;
    }

    public void setPendapatanRs(BigDecimal pendapatanRs) {
        this.pendapatanRs = pendapatanRs;
    }

    public BigDecimal getPotKs() {
        return potKs;
    }

    public void setPotKs(BigDecimal potKs) {
        this.potKs = potKs;
    }
}