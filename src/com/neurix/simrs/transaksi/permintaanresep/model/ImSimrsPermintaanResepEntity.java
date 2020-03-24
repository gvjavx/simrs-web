package com.neurix.simrs.transaksi.permintaanresep.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class ImSimrsPermintaanResepEntity implements Serializable {

    private String idPermintaanResep;
    private String idApprovalObat;
    private String idPasien;
    private String idDetailCheckup;
    private String keterangan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDokter;
    private String branchId;
    private String isUmum;
    private String status;
    private Timestamp tglAntrian;
    private String tujuanPelayanan;

    private String ttdPasien;
    private String ttdDokter;

    public String getTtdDokter() {
        return ttdDokter;
    }

    public void setTtdDokter(String ttdDokter) {
        this.ttdDokter = ttdDokter;
    }

    public String getTtdPasien() {
        return ttdPasien;
    }

    public void setTtdPasien(String ttdPasien) {
        this.ttdPasien = ttdPasien;
    }

    public String getTujuanPelayanan() {
        return tujuanPelayanan;
    }

    public void setTujuanPelayanan(String tujuanPelayanan) {
        this.tujuanPelayanan = tujuanPelayanan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIsUmum() {
        return isUmum;
    }

    public void setIsUmum(String isUmum) {
        this.isUmum = isUmum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTglAntrian() {
        return tglAntrian;
    }

    public void setTglAntrian(Timestamp tglAntrian) {
        this.tglAntrian = tglAntrian;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPermintaanResep() {
        return idPermintaanResep;
    }

    public void setIdPermintaanResep(String idPermintaanResep) {
        this.idPermintaanResep = idPermintaanResep;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
