package com.neurix.simrs.transaksi.pemberianobat.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

public class CatatanPemberianObat extends BaseModel {

    private String idCatatanPemberianObat;
    private String idDetailCheckup;
    private String waktu;
    private String namaDokter;
    private String namaObat;
    private String aturanPakai;
    private Date tanggalMulai;
    private Date tanggalStop;
    private String ttdDokter;
    private String ttdApoteker;
    private String keterangan;
    private String status;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String namaTerangDokter;
    private String namaTerangPerawat;
    private String sipDokter;
    private String jenis;
    private String sipPerawat;

    public String getSipPerawat() {
        return sipPerawat;
    }

    public void setSipPerawat(String sipPerawat) {
        this.sipPerawat = sipPerawat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamaTerangDokter() {
        return namaTerangDokter;
    }

    public void setNamaTerangDokter(String namaTerangDokter) {
        this.namaTerangDokter = namaTerangDokter;
    }

    public String getNamaTerangPerawat() {
        return namaTerangPerawat;
    }

    public void setNamaTerangPerawat(String namaTerangPerawat) {
        this.namaTerangPerawat = namaTerangPerawat;
    }

    public String getSipDokter() {
        return sipDokter;
    }

    public void setSipDokter(String sipDokter) {
        this.sipDokter = sipDokter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdCatatanPemberianObat() {
        return idCatatanPemberianObat;
    }

    public void setIdCatatanPemberianObat(String idCatatanPemberianObat) {
        this.idCatatanPemberianObat = idCatatanPemberianObat;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getAturanPakai() {
        return aturanPakai;
    }

    public void setAturanPakai(String aturanPakai) {
        this.aturanPakai = aturanPakai;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalStop() {
        return tanggalStop;
    }

    public void setTanggalStop(Date tanggalStop) {
        this.tanggalStop = tanggalStop;
    }

    public String getTtdDokter() {
        return ttdDokter;
    }

    public void setTtdDokter(String ttdDokter) {
        this.ttdDokter = ttdDokter;
    }

    public String getTtdApoteker() {
        return ttdApoteker;
    }

    public void setTtdApoteker(String ttdApoteker) {
        this.ttdApoteker = ttdApoteker;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
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
