package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Date;

public class JadwalPelayananDTO extends BaseModel {
    private String idDokter;
    private String namaDokter;
    private String idSpesialis;
    private String namaSpesialis;
    private String idPelayanan;
    private String namaPelayanan;
    private Date tanggal;
    private String jamAwal;
    private String jamAkhir;
    private String stTanggal;
    private String branchId;
    private String branchName;
    private String kuota;
    private String flagLibur;

    //sodiq, 29-09-2020
    private String sip;
    private String kodeDpjp;
    private BigInteger kuotaOnSite;
    private BigInteger kuotaTerpakai;

    public BigInteger getKuotaTerpakai() {
        return kuotaTerpakai;
    }

    public void setKuotaTerpakai(BigInteger kuotaTerpakai) {
        this.kuotaTerpakai = kuotaTerpakai;
    }

    public BigInteger getKuotaOnSite() {
        return kuotaOnSite;
    }

    public void setKuotaOnSite(BigInteger kuotaOnSite) {
        this.kuotaOnSite = kuotaOnSite;
    }

    public String getFlagLibur() {
        return flagLibur;
    }

    public void setFlagLibur(String flagLibur) {
        this.flagLibur = flagLibur;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getKodeDpjp() {
        return kodeDpjp;
    }

    public void setKodeDpjp(String kodeDpjp) {
        this.kodeDpjp = kodeDpjp;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
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

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }

    public String getNamaSpesialis() {
        return namaSpesialis;
    }

    public void setNamaSpesialis(String namaSpesialis) {
        this.namaSpesialis = namaSpesialis;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }
}
