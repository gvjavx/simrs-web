package com.neurix.hris.transaksi.refreshLembur.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Aji Noor on 21/07/2021
 */
public class RefreshLembur {
    private String refreshLemburId;
    private String groupRefreshId;

    private String absensiPegawaiId;
    private Date tanggal;
    private String stTanggal;
    private String nama;
    private String jamMasuk;
    private String jamKeluar;
    private String jenisLembur;
    private Double lamaLembur;
    private String stLamaLembur;
    private Double jamLembur;
    private String stJamLembur;
    private Double biayaLembur;
    private String stBiayaLembur;
    private String tipeHari;
    private Double realisasiLembur;
    private String stRealisasiLembur;
    private String branchId;

    private String lemburId;
    private Date tglAwalLembur;
    private String stTglAwalLembur;
    private Date tglAkhirLembur;
    private String stTglAkhirLembur;
    private String jamAwalLembur;
    private String jamAkhirLembur;
    private String flagApprove;
    private String approvalwho;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;


    public String getRefreshLemburId() {
        return refreshLemburId;
    }

    public void setRefreshLemburId(String refreshLemburId) {
        this.refreshLemburId = refreshLemburId;
    }

    public String getGroupRefreshId() {
        return groupRefreshId;
    }

    public void setGroupRefreshId(String groupRefreshId) {
        this.groupRefreshId = groupRefreshId;
    }

    public String getAbsensiPegawaiId() {
        return absensiPegawaiId;
    }

    public void setAbsensiPegawaiId(String absensiPegawaiId) {
        this.absensiPegawaiId = absensiPegawaiId;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getJenisLembur() {
        return jenisLembur;
    }

    public void setJenisLembur(String jenisLembur) {
        this.jenisLembur = jenisLembur;
    }

    public Double getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(Double lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public Double getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(Double jamLembur) {
        this.jamLembur = jamLembur;
    }

    public Double getBiayaLembur() {
        return biayaLembur;
    }

    public void setBiayaLembur(Double biayaLembur) {
        this.biayaLembur = biayaLembur;
    }

    public String getStBiayaLembur() {
        return stBiayaLembur;
    }

    public void setStBiayaLembur(String stBiayaLembur) {
        this.stBiayaLembur = stBiayaLembur;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }

    public Double getRealisasiLembur() {
        return realisasiLembur;
    }

    public void setRealisasiLembur(Double realisasiLembur) {
        this.realisasiLembur = realisasiLembur;
    }

    public String getStRealisasiLembur() {
        return stRealisasiLembur;
    }

    public void setStRealisasiLembur(String stRealisasiLembur) {
        this.stRealisasiLembur = stRealisasiLembur;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getLemburId() {
        return lemburId;
    }

    public void setLemburId(String lemburId) {
        this.lemburId = lemburId;
    }

    public Date getTglAwalLembur() {
        return tglAwalLembur;
    }

    public void setTglAwalLembur(Date tglAwalLembur) {
        this.tglAwalLembur = tglAwalLembur;
    }

    public Date getTglAkhirLembur() {
        return tglAkhirLembur;
    }

    public void setTglAkhirLembur(Date tglAkhirLembur) {
        this.tglAkhirLembur = tglAkhirLembur;
    }

    public String getJamAwalLembur() {
        return jamAwalLembur;
    }

    public void setJamAwalLembur(String jamAwalLembur) {
        this.jamAwalLembur = jamAwalLembur;
    }

    public String getJamAkhirLembur() {
        return jamAkhirLembur;
    }

    public void setJamAkhirLembur(String jamAkhirLembur) {
        this.jamAkhirLembur = jamAkhirLembur;
    }

    public String getFlagApprove() {
        return flagApprove;
    }

    public void setFlagApprove(String flagApprove) {
        this.flagApprove = flagApprove;
    }

    public String getApprovalwho() {
        return approvalwho;
    }

    public void setApprovalwho(String approvalwho) {
        this.approvalwho = approvalwho;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getStLamaLembur() {
        return stLamaLembur;
    }

    public void setStLamaLembur(String stLamaLembur) {
        this.stLamaLembur = stLamaLembur;
    }

    public String getStJamLembur() {
        return stJamLembur;
    }

    public void setStJamLembur(String stJamLembur) {
        this.stJamLembur = stJamLembur;
    }

    public String getStTglAwalLembur() {
        return stTglAwalLembur;
    }

    public void setStTglAwalLembur(String stTglAwalLembur) {
        this.stTglAwalLembur = stTglAwalLembur;
    }

    public String getStTglAkhirLembur() {
        return stTglAkhirLembur;
    }

    public void setStTglAkhirLembur(String stTglAkhirLembur) {
        this.stTglAkhirLembur = stTglAkhirLembur;
    }
}
