package com.neurix.hris.transaksi.pendapatanDokter.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class PendapatanDokter extends BaseModel {

    String pendapatanDokterId;
    String dokterId;
    String dokterName;
    String branchId;
    String branchName;
    String bulan;
    String tahun;
    String bruto;
    BigDecimal bgBruto;
    String dppPph50;
    BigDecimal bgDppPph50;
    String dppPph21Komulatif;
    BigDecimal bgDppPph21Komulatif;
    String dppPph21;
    BigDecimal bgDppPph21;
    BigDecimal tarif;
    String stTarif;
    String pphDipungut;
    BigDecimal bgPphDipungut;
    String gajiBersih;
    BigDecimal bgGajiBersih;
    String approvalFlag;
    String approvalWho;
    Timestamp approvalDate;
    String flagDiterima;
    Date dateDiterima;
    String stDateDiterima;
    String pemberiWho;

    String noReg;
    String jenisRawat;
    String kdjnspas;
    String namaPasien;
    Timestamp tanggal;
    String keterangan;
    String tarifInacbg;
    BigDecimal bgTarifInacbg;
    String pendapatanRs;
    BigDecimal bgPendapatanRs;
    String hrBruto;
    BigDecimal bgHrBruto;
    String hrAktifitasNetto;
    BigDecimal bgHrAktifitasNetto;
    String potKs;
    BigDecimal bgPotKs;
    String activityId;
    String activityName;
    String poliId;
    String poliName;
    String kodeJabatan;

    String branchUser;
    BigDecimal totalBruto;
    String stTotalBruto;
    BigDecimal totalPendapatanRs;
    String stTotalPendapatanRs;
    BigDecimal totalHrBruto;
    String stTotalHrBruto;
    BigDecimal totalDppPph50;
    String stTotalDppPph50;
    BigDecimal totalDppPph21Komulatif;
    String stTotalDppPph21Komulatif;
    BigDecimal totalDppPph21;
    String stTotalDppPph21;
    BigDecimal totalPphDipungut;
    String stTotalPphDipungut;
    BigDecimal totalHrAktifitasNetto;
    String stTotalHrAktifitasNetto;
    BigDecimal totalPotKs;
    String stTotalPotKs;
    BigDecimal totalGajiBersih;
    String stTotalGajiBersih;
    String masterId;


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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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

    public String getDppPph21() {
        return dppPph21;
    }

    public void setDppPph21(String dppPph21) {
        this.dppPph21 = dppPph21;
    }

    public String getDppPph21Komulatif() {
        return dppPph21Komulatif;
    }

    public void setDppPph21Komulatif(String dppPph21Komulatif) {
        this.dppPph21Komulatif = dppPph21Komulatif;
    }

    public String getDppPph50() {
        return dppPph50;
    }

    public void setDppPph50(String dppPph50) {
        this.dppPph50 = dppPph50;
    }

    public String getGajiBersih() {
        return gajiBersih;
    }

    public void setGajiBersih(String gajiBersih) {
        this.gajiBersih = gajiBersih;
    }

    public String getPendapatanDokterId() {
        return pendapatanDokterId;
    }

    public void setPendapatanDokterId(String pendapatanDokterId) {
        this.pendapatanDokterId = pendapatanDokterId;
    }

    public String getPphDipungut() {
        return pphDipungut;
    }

    public void setPphDipungut(String pphDipungut) {
        this.pphDipungut = pphDipungut;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getStTarif() {
        return stTarif;
    }

    public void setStTarif(String stTarif) {
        this.stTarif = stTarif;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public Date getDateDiterima() {
        return dateDiterima;
    }

    public void setDateDiterima(Date dateDiterima) {
        this.dateDiterima = dateDiterima;
    }

    public String getFlagDiterima() {
        return flagDiterima;
    }

    public void setFlagDiterima(String flagDiterima) {
        this.flagDiterima = flagDiterima;
    }

    public String getPemberiWho() {
        return pemberiWho;
    }

    public void setPemberiWho(String pemberiWho) {
        this.pemberiWho = pemberiWho;
    }

    public String getBranchUser() {
        return branchUser;
    }

    public void setBranchUser(String branchUser) {
        this.branchUser = branchUser;
    }

    public String getHrAktifitasNetto() {
        return hrAktifitasNetto;
    }

    public void setHrAktifitasNetto(String hrAktifitasNetto) {
        this.hrAktifitasNetto = hrAktifitasNetto;
    }

    public String getHrBruto() {
        return hrBruto;
    }

    public void setHrBruto(String hrBruto) {
        this.hrBruto = hrBruto;
    }

    public String getPendapatanRs() {
        return pendapatanRs;
    }

    public void setPendapatanRs(String pendapatanRs) {
        this.pendapatanRs = pendapatanRs;
    }

    public String getPotKs() {
        return potKs;
    }

    public void setPotKs(String potKs) {
        this.potKs = potKs;
    }

    public String getStTotalBruto() {
        return stTotalBruto;
    }

    public void setStTotalBruto(String stTotalBruto) {
        this.stTotalBruto = stTotalBruto;
    }

    public String getStTotalDppPph21() {
        return stTotalDppPph21;
    }

    public void setStTotalDppPph21(String stTotalDppPph21) {
        this.stTotalDppPph21 = stTotalDppPph21;
    }

    public String getStTotalDppPph21Komulatif() {
        return stTotalDppPph21Komulatif;
    }

    public void setStTotalDppPph21Komulatif(String stTotalDppPph21Komulatif) {
        this.stTotalDppPph21Komulatif = stTotalDppPph21Komulatif;
    }

    public String getStTotalDppPph50() {
        return stTotalDppPph50;
    }

    public void setStTotalDppPph50(String stTotalDppPph50) {
        this.stTotalDppPph50 = stTotalDppPph50;
    }

    public String getStTotalGajiBersih() {
        return stTotalGajiBersih;
    }

    public void setStTotalGajiBersih(String stTotalGajiBersih) {
        this.stTotalGajiBersih = stTotalGajiBersih;
    }

    public String getStTotalHrAktifitasNetto() {
        return stTotalHrAktifitasNetto;
    }

    public void setStTotalHrAktifitasNetto(String stTotalHrAktifitasNetto) {
        this.stTotalHrAktifitasNetto = stTotalHrAktifitasNetto;
    }

    public String getStTotalHrBruto() {
        return stTotalHrBruto;
    }

    public void setStTotalHrBruto(String stTotalHrBruto) {
        this.stTotalHrBruto = stTotalHrBruto;
    }

    public String getStTotalPendapatanRs() {
        return stTotalPendapatanRs;
    }

    public void setStTotalPendapatanRs(String stTotalPendapatanRs) {
        this.stTotalPendapatanRs = stTotalPendapatanRs;
    }

    public String getStTotalPotKs() {
        return stTotalPotKs;
    }

    public void setStTotalPotKs(String stTotalPotKs) {
        this.stTotalPotKs = stTotalPotKs;
    }

    public String getStTotalPphDipungut() {
        return stTotalPphDipungut;
    }

    public void setStTotalPphDipungut(String stTotalPphDipungut) {
        this.stTotalPphDipungut = stTotalPphDipungut;
    }

    public BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(BigDecimal totalBruto) {
        this.totalBruto = totalBruto;
    }

    public BigDecimal getTotalDppPph21() {
        return totalDppPph21;
    }

    public void setTotalDppPph21(BigDecimal totalDppPph21) {
        this.totalDppPph21 = totalDppPph21;
    }

    public BigDecimal getTotalDppPph21Komulatif() {
        return totalDppPph21Komulatif;
    }

    public void setTotalDppPph21Komulatif(BigDecimal totalDppPph21Komulatif) {
        this.totalDppPph21Komulatif = totalDppPph21Komulatif;
    }

    public BigDecimal getTotalDppPph50() {
        return totalDppPph50;
    }

    public void setTotalDppPph50(BigDecimal totalDppPph50) {
        this.totalDppPph50 = totalDppPph50;
    }

    public BigDecimal getTotalGajiBersih() {
        return totalGajiBersih;
    }

    public void setTotalGajiBersih(BigDecimal totalGajiBersih) {
        this.totalGajiBersih = totalGajiBersih;
    }

    public BigDecimal getTotalHrAktifitasNetto() {
        return totalHrAktifitasNetto;
    }

    public void setTotalHrAktifitasNetto(BigDecimal totalHrAktifitasNetto) {
        this.totalHrAktifitasNetto = totalHrAktifitasNetto;
    }

    public BigDecimal getTotalHrBruto() {
        return totalHrBruto;
    }

    public void setTotalHrBruto(BigDecimal totalHrBruto) {
        this.totalHrBruto = totalHrBruto;
    }

    public BigDecimal getTotalPendapatanRs() {
        return totalPendapatanRs;
    }

    public void setTotalPendapatanRs(BigDecimal totalPendapatanRs) {
        this.totalPendapatanRs = totalPendapatanRs;
    }

    public BigDecimal getTotalPotKs() {
        return totalPotKs;
    }

    public void setTotalPotKs(BigDecimal totalPotKs) {
        this.totalPotKs = totalPotKs;
    }

    public BigDecimal getTotalPphDipungut() {
        return totalPphDipungut;
    }

    public void setTotalPphDipungut(BigDecimal totalPphDipungut) {
        this.totalPphDipungut = totalPphDipungut;
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

    public String getNoReg() {
        return noReg;
    }

    public void setNoReg(String noReg) {
        this.noReg = noReg;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public String getTarifInacbg() {
        return tarifInacbg;
    }

    public void setTarifInacbg(String tarifInacbg) {
        this.tarifInacbg = tarifInacbg;
    }

    public String getStDateDiterima() {
        return stDateDiterima;
    }

    public void setStDateDiterima(String stDateDiterima) {
        this.stDateDiterima = stDateDiterima;
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

    public BigDecimal getBgBruto() {
        return bgBruto;
    }

    public void setBgBruto(BigDecimal bgBruto) {
        this.bgBruto = bgBruto;
    }

    public BigDecimal getBgHrAktifitasNetto() {
        return bgHrAktifitasNetto;
    }

    public void setBgHrAktifitasNetto(BigDecimal bgHrAktifitasNetto) {
        this.bgHrAktifitasNetto = bgHrAktifitasNetto;
    }

    public BigDecimal getBgHrBruto() {
        return bgHrBruto;
    }

    public void setBgHrBruto(BigDecimal bgHrBruto) {
        this.bgHrBruto = bgHrBruto;
    }

    public BigDecimal getBgPendapatanRs() {
        return bgPendapatanRs;
    }

    public void setBgPendapatanRs(BigDecimal bgPendapatanRs) {
        this.bgPendapatanRs = bgPendapatanRs;
    }

    public BigDecimal getBgPotKs() {
        return bgPotKs;
    }

    public void setBgPotKs(BigDecimal bgPotKs) {
        this.bgPotKs = bgPotKs;
    }

    public BigDecimal getBgTarifInacbg() {
        return bgTarifInacbg;
    }

    public void setBgTarifInacbg(BigDecimal bgTarifInacbg) {
        this.bgTarifInacbg = bgTarifInacbg;
    }

    public BigDecimal getBgDppPph21() {
        return bgDppPph21;
    }

    public void setBgDppPph21(BigDecimal bgDppPph21) {
        this.bgDppPph21 = bgDppPph21;
    }

    public BigDecimal getBgDppPph21Komulatif() {
        return bgDppPph21Komulatif;
    }

    public void setBgDppPph21Komulatif(BigDecimal bgDppPph21Komulatif) {
        this.bgDppPph21Komulatif = bgDppPph21Komulatif;
    }

    public BigDecimal getBgDppPph50() {
        return bgDppPph50;
    }

    public void setBgDppPph50(BigDecimal bgDppPph50) {
        this.bgDppPph50 = bgDppPph50;
    }

    public BigDecimal getBgGajiBersih() {
        return bgGajiBersih;
    }

    public void setBgGajiBersih(BigDecimal bgGajiBersih) {
        this.bgGajiBersih = bgGajiBersih;
    }

    public BigDecimal getBgPphDipungut() {
        return bgPphDipungut;
    }

    public void setBgPphDipungut(BigDecimal bgPphDipungut) {
        this.bgPphDipungut = bgPphDipungut;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(String kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }
}