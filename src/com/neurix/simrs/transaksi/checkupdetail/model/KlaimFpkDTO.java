package com.neurix.simrs.transaksi.checkupdetail.model;

import java.math.BigInteger;

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

    private Integer jumlahSudahDiKlaim;
    private Integer jumlahSepTidakAda;
    private Integer jumlahBiayaBpjsKurangDariRs;
    private Integer jumlahBiayaBpjsLebihDariRs;
    private Integer jumlahBiayaBpjsSamaDenganRs;
    private Integer jumlahSalah;

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
