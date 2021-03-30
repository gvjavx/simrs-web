package com.neurix.simrs.transaksi.makananpendamping.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HeaderPendampingMakanan {
    private String idHeaderPendampingMakanan;
    private String idDetailCheckup;
    private String idRuangan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String status;

    private String idPasien;
    private String nama;
    private String noRuangan;
    private String namaRuangan;
    private String idKelas;

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoRuangan() {
        return noRuangan;
    }

    public void setNoRuangan(String noRuangan) {
        this.noRuangan = noRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private List<ItSimrsDetailPendampingMakananEntity> detailPendampingMakananList = new ArrayList<>();

    public List<ItSimrsDetailPendampingMakananEntity> getDetailPendampingMakananList() {
        return detailPendampingMakananList;
    }

    public void setDetailPendampingMakananList(List<ItSimrsDetailPendampingMakananEntity> detailPendampingMakananList) {
        this.detailPendampingMakananList = detailPendampingMakananList;
    }

    public String getIdHeaderPendampingMakanan() {
        return idHeaderPendampingMakanan;
    }

    public void setIdHeaderPendampingMakanan(String idHeaderPendampingMakanan) {
        this.idHeaderPendampingMakanan = idHeaderPendampingMakanan;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
