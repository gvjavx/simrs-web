package com.neurix.simrs.transaksi.periksalab.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsHeaderPemeriksaanEntity {
    private String idHeaderPemeriksaan;
    private String idDetailCheckup;
    private String idKategoriLab;
    private String idDokterPengirim;
    private String namaDokterPengirim;
    private String ttdDokterPengirim;
    private String idPetugas;
    private String namaPetugas;
    private String ttdPetugas;
    private String idValidator;
    private String namaValidator;
    private String ttdValidator;
    private String statusPeriksa;
    private String catatan;
    private BigDecimal tarifLabLuar;
    private String approveFlag;
    private String isJustLab;
    private String isPending;
    private String isRead;
    private String isPeriksaLuar;
    private Timestamp tanggalMasukPeriksa;
    private Timestamp tanggalSelesaiPeriksa;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private String lastUpdateWho;
    private Timestamp lastUpdate;

    public String getIsPeriksaLuar() {
        return isPeriksaLuar;
    }

    public void setIsPeriksaLuar(String isPeriksaLuar) {
        this.isPeriksaLuar = isPeriksaLuar;
    }

    public String getIdHeaderPemeriksaan() {
        return idHeaderPemeriksaan;
    }

    public void setIdHeaderPemeriksaan(String idHeaderPemeriksaan) {
        this.idHeaderPemeriksaan = idHeaderPemeriksaan;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getIdKategoriLab() {
        return idKategoriLab;
    }

    public void setIdKategoriLab(String idKategoriLab) {
        this.idKategoriLab = idKategoriLab;
    }

    public String getIdDokterPengirim() {
        return idDokterPengirim;
    }

    public void setIdDokterPengirim(String idDokterPengirim) {
        this.idDokterPengirim = idDokterPengirim;
    }

    public String getNamaDokterPengirim() {
        return namaDokterPengirim;
    }

    public void setNamaDokterPengirim(String namaDokterPengirim) {
        this.namaDokterPengirim = namaDokterPengirim;
    }

    public String getTtdDokterPengirim() {
        return ttdDokterPengirim;
    }

    public void setTtdDokterPengirim(String ttdDokterPengirim) {
        this.ttdDokterPengirim = ttdDokterPengirim;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getTtdPetugas() {
        return ttdPetugas;
    }

    public void setTtdPetugas(String ttdPetugas) {
        this.ttdPetugas = ttdPetugas;
    }

    public String getIdValidator() {
        return idValidator;
    }

    public void setIdValidator(String idValidator) {
        this.idValidator = idValidator;
    }

    public String getNamaValidator() {
        return namaValidator;
    }

    public void setNamaValidator(String namaValidator) {
        this.namaValidator = namaValidator;
    }

    public String getTtdValidator() {
        return ttdValidator;
    }

    public void setTtdValidator(String ttdValidator) {
        this.ttdValidator = ttdValidator;
    }

    public String getStatusPeriksa() {
        return statusPeriksa;
    }

    public void setStatusPeriksa(String statusPeriksa) {
        this.statusPeriksa = statusPeriksa;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public BigDecimal getTarifLabLuar() {
        return tarifLabLuar;
    }

    public void setTarifLabLuar(BigDecimal tarifLabLuar) {
        this.tarifLabLuar = tarifLabLuar;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getIsJustLab() {
        return isJustLab;
    }

    public void setIsJustLab(String isJustLab) {
        this.isJustLab = isJustLab;
    }

    public String getIsPending() {
        return isPending;
    }

    public void setIsPending(String isPending) {
        this.isPending = isPending;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Timestamp getTanggalMasukPeriksa() {
        return tanggalMasukPeriksa;
    }

    public void setTanggalMasukPeriksa(Timestamp tanggalMasukPeriksa) {
        this.tanggalMasukPeriksa = tanggalMasukPeriksa;
    }

    public Timestamp getTanggalSelesaiPeriksa() {
        return tanggalSelesaiPeriksa;
    }

    public void setTanggalSelesaiPeriksa(Timestamp tanggalSelesaiPeriksa) {
        this.tanggalSelesaiPeriksa = tanggalSelesaiPeriksa;
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
