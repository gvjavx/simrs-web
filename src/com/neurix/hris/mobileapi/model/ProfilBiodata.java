package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.util.Date;

public class ProfilBiodata implements Serializable {

    private String payrollId;
    private String nip;
    private String namaPegawai;
    private String gelarDepan;
    private String gelarBelakang;
    private String noKtp;
    private String alamat;
    private String noTelp;
    private String tempatLahir;
    private Date tanggalLahir;
    private String tanggalLahirSt;
    private Date tanggalAktif;
    private String tanggalAktifSt;
    private String tanggalPensiun;
    private String masakerja;
    private String branchName;
    private String divisiName;
    private String positionName;
    private String npwp;
    private String tipePegawai;
    private String statusPegawai;
    private String statusGiling;
    private String zakatProfesi;
    private String flag;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getGelarDepan() {
        return gelarDepan;
    }

    public void setGelarDepan(String gelarDepan) {
        this.gelarDepan = gelarDepan;
    }

    public String getGelarBelakang() {
        return gelarBelakang;
    }

    public void setGelarBelakang(String gelarBelakang) {
        this.gelarBelakang = gelarBelakang;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalLahirSt() {
        return tanggalLahirSt;
    }

    public void setTanggalLahirSt(String tanggalLahirst) {
        this.tanggalLahirSt = tanggalLahirst;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getTanggalAktifSt() {
        return tanggalAktifSt;
    }

    public void setTanggalAktifSt(String tanggalAktifSt) {
        this.tanggalAktifSt = tanggalAktifSt;
    }

    public String getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(String tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getMasakerja() {
        return masakerja;
    }

    public void setMasakerja(String masakerja) {
        this.masakerja = masakerja;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getZakatProfesi() {
        return zakatProfesi;
    }

    public void setZakatProfesi(String zakatProfesi) {
        this.zakatProfesi = zakatProfesi;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
