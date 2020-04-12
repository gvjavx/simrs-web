package com.neurix.simrs.transaksi.checkupdetail.model;

import java.math.BigInteger;
import java.sql.Date;

public class KlaimFpkDTO {
    private String idDetailCheckup;
    private String noSep;
    private String idPasien;
    private String namaPasien;
    private BigInteger totalBiaya;
    private BigInteger totalBiayaBpjs;
    private String stTotalBiaya;
    private String stTotalBiayaBpjs;
    private String statusBayar;
    private String status;
    private String koderingPoli;
    private String poliName;

    private Integer jumlahSudahDiKlaim;
    private Integer jumlahSepTidakAda;
    private Integer jumlahBiayaBpjsKurangDariRs;
    private Integer jumlahBiayaBpjsLebihDariRs;
    private Integer jumlahBiayaBpjsSamaDenganRs;
    private Integer jumlahSalah;

    //for save
    private String noFpk;
    private String stTanggalFpk;
    private Date tanggalFpk;
    private Integer jumlahSeluruhnya;
    private Integer jumlahSelisih;
    private String stJumlahSelisih;
    private String stJumlahSeluruhnya;

    public Integer getJumlahSelisih() {
        return jumlahSelisih;
    }

    public void setJumlahSelisih(Integer jumlahSelisih) {
        this.jumlahSelisih = jumlahSelisih;
    }

    public String getStJumlahSelisih() {
        return stJumlahSelisih;
    }

    public void setStJumlahSelisih(String stJumlahSelisih) {
        this.stJumlahSelisih = stJumlahSelisih;
    }

    public Integer getJumlahSeluruhnya() {
        return jumlahSeluruhnya;
    }

    public void setJumlahSeluruhnya(Integer jumlahSeluruhnya) {
        this.jumlahSeluruhnya = jumlahSeluruhnya;
    }

    public String getStJumlahSeluruhnya() {
        return stJumlahSeluruhnya;
    }

    public void setStJumlahSeluruhnya(String stJumlahSeluruhnya) {
        this.stJumlahSeluruhnya = stJumlahSeluruhnya;
    }

    public String getKoderingPoli() {
        return koderingPoli;
    }

    public void setKoderingPoli(String koderingPoli) {
        this.koderingPoli = koderingPoli;
    }

    public String getPoliName() {
        return poliName;
    }

    public void setPoliName(String poliName) {
        this.poliName = poliName;
    }

    public String getNoFpk() {
        return noFpk;
    }

    public void setNoFpk(String noFpk) {
        this.noFpk = noFpk;
    }

    public String getStTanggalFpk() {
        return stTanggalFpk;
    }

    public void setStTanggalFpk(String stTanggalFpk) {
        this.stTanggalFpk = stTanggalFpk;
    }

    public Date getTanggalFpk() {
        return tanggalFpk;
    }

    public void setTanggalFpk(Date tanggalFpk) {
        this.tanggalFpk = tanggalFpk;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public BigInteger getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigInteger totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public BigInteger getTotalBiayaBpjs() {
        return totalBiayaBpjs;
    }

    public void setTotalBiayaBpjs(BigInteger totalBiayaBpjs) {
        this.totalBiayaBpjs = totalBiayaBpjs;
    }

    public String getStTotalBiaya() {
        return stTotalBiaya;
    }

    public void setStTotalBiaya(String stTotalBiaya) {
        this.stTotalBiaya = stTotalBiaya;
    }

    public String getStTotalBiayaBpjs() {
        return stTotalBiayaBpjs;
    }

    public void setStTotalBiayaBpjs(String stTotalBiayaBpjs) {
        this.stTotalBiayaBpjs = stTotalBiayaBpjs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJumlahSudahDiKlaim() {
        return jumlahSudahDiKlaim;
    }

    public void setJumlahSudahDiKlaim(Integer jumlahSudahDiKlaim) {
        this.jumlahSudahDiKlaim = jumlahSudahDiKlaim;
    }

    public Integer getJumlahSepTidakAda() {
        return jumlahSepTidakAda;
    }

    public void setJumlahSepTidakAda(Integer jumlahSepTidakAda) {
        this.jumlahSepTidakAda = jumlahSepTidakAda;
    }

    public Integer getJumlahBiayaBpjsKurangDariRs() {
        return jumlahBiayaBpjsKurangDariRs;
    }

    public void setJumlahBiayaBpjsKurangDariRs(Integer jumlahBiayaBpjsKurangDariRs) {
        this.jumlahBiayaBpjsKurangDariRs = jumlahBiayaBpjsKurangDariRs;
    }

    public Integer getJumlahBiayaBpjsLebihDariRs() {
        return jumlahBiayaBpjsLebihDariRs;
    }

    public void setJumlahBiayaBpjsLebihDariRs(Integer jumlahBiayaBpjsLebihDariRs) {
        this.jumlahBiayaBpjsLebihDariRs = jumlahBiayaBpjsLebihDariRs;
    }

    public Integer getJumlahBiayaBpjsSamaDenganRs() {
        return jumlahBiayaBpjsSamaDenganRs;
    }

    public void setJumlahBiayaBpjsSamaDenganRs(Integer jumlahBiayaBpjsSamaDenganRs) {
        this.jumlahBiayaBpjsSamaDenganRs = jumlahBiayaBpjsSamaDenganRs;
    }

    public Integer getJumlahSalah() {
        return jumlahSalah;
    }

    public void setJumlahSalah(Integer jumlahSalah) {
        this.jumlahSalah = jumlahSalah;
    }
}
