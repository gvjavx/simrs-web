package com.neurix.simrs.transaksi.periksalab.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ItSimrsPeriksaLabEntity implements Serializable {

    private String idPeriksaLab;
    private String idDetailCheckup;
    private String idLab;
    private Date tanggalMasukLab;
    private Timestamp tanggalSelesaiPeriksa;
    private String idDokterPengirim;
    private String idDokter;
    private String idPemeriksa;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String statusPeriksa;
    private String approveFlag;
    private String keterangan;
    private String urlImg;
    private String ttdPengirim;

    public String getTtdPengirim() {
        return ttdPengirim;
    }

    public void setTtdPengirim(String ttdPengirim) {
        this.ttdPengirim = ttdPengirim;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getStatusPeriksa() {
        return statusPeriksa;
    }

    public void setStatusPeriksa(String statusPeriksa) {
        this.statusPeriksa = statusPeriksa;
    }

    public String getIdPeriksaLab() {
        return idPeriksaLab;
    }

    public void setIdPeriksaLab(String idPeriksaLab) {
        this.idPeriksaLab = idPeriksaLab;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public Date getTanggalMasukLab() {
        return tanggalMasukLab;
    }

    public void setTanggalMasukLab(Date tanggalMasukLab) {
        this.tanggalMasukLab = tanggalMasukLab;
    }

    public Timestamp getTanggalSelesaiPeriksa() {
        return tanggalSelesaiPeriksa;
    }

    public void setTanggalSelesaiPeriksa(Timestamp tanggalSelesaiPeriksa) {
        this.tanggalSelesaiPeriksa = tanggalSelesaiPeriksa;
    }

    public String getIdDokterPengirim() {
        return idDokterPengirim;
    }

    public void setIdDokterPengirim(String idDokterPengirim) {
        this.idDokterPengirim = idDokterPengirim;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPemeriksa() {
        return idPemeriksa;
    }

    public void setIdPemeriksa(String idPemeriksa) {
        this.idPemeriksa = idPemeriksa;
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