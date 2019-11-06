package com.neurix.hris.master.sertifikat.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;


public class Sertifikat extends BaseModel {
    private String sertifikatId;
    private String nip;
    private Date tanggalPengesahan;
    private String stTanggalPengesahan;
    private String jenis;
    private Date masaBerlaku;
    private String stMasaBerlaku;
    private Date masaBerakhir;
    private String stMasaBerakhir;
    private String nama;
    private String lembaga;
    private String tempatPelaksana;
    private int jumlahHari;
    private double nilai;
    private String lulus;
    private String prestasiGrade;

    public int getJumlahHari() {
        return jumlahHari;
    }

    public void setJumlahHari(int jumlahHari) {
        this.jumlahHari = jumlahHari;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getLembaga() {
        return lembaga;
    }

    public void setLembaga(String lembaga) {
        this.lembaga = lembaga;
    }

    public String getLulus() {
        return lulus;
    }

    public void setLulus(String lulus) {
        this.lulus = lulus;
    }

    public Date getMasaBerakhir() {
        return masaBerakhir;
    }

    public void setMasaBerakhir(Date masaBerakhir) {
        this.masaBerakhir = masaBerakhir;
    }

    public Date getMasaBerlaku() {
        return masaBerlaku;
    }

    public void setMasaBerlaku(Date masaBerlaku) {
        this.masaBerlaku = masaBerlaku;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPrestasiGrade() {
        return prestasiGrade;
    }

    public void setPrestasiGrade(String prestasiGrade) {
        this.prestasiGrade = prestasiGrade;
    }

    public String getSertifikatId() {
        return sertifikatId;
    }

    public void setSertifikatId(String sertifikatId) {
        this.sertifikatId = sertifikatId;
    }

    public String getStMasaBerakhir() {
        return stMasaBerakhir;
    }

    public void setStMasaBerakhir(String stMasaBerakhir) {
        this.stMasaBerakhir = stMasaBerakhir;
    }

    public String getStMasaBerlaku() {
        return stMasaBerlaku;
    }

    public void setStMasaBerlaku(String stMasaBerlaku) {
        this.stMasaBerlaku = stMasaBerlaku;
    }

    public String getStTanggalPengesahan() {
        return stTanggalPengesahan;
    }

    public void setStTanggalPengesahan(String stTanggalPengesahan) {
        this.stTanggalPengesahan = stTanggalPengesahan;
    }

    public Date getTanggalPengesahan() {
        return tanggalPengesahan;
    }

    public void setTanggalPengesahan(Date tanggalPengesahan) {
        this.tanggalPengesahan = tanggalPengesahan;
    }

    public String getTempatPelaksana() {
        return tempatPelaksana;
    }

    public void setTempatPelaksana(String tempatPelaksana) {
        this.tempatPelaksana = tempatPelaksana;
    }
}