package com.neurix.hris.mobileapi.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Lembur {
    private String idLembur;
    private String nip;
    private String namaPegawai;
    private String divisi;
    private String posisi;
    private String tanggalAwal;
    private String tanggalAkhir;
    private String unit;
    private String jamLemburAwal;
    private String lamaJamLembur;
    private String keterangan;
    private String jamLemburAkhir;
    private String message;
    private String jumlah;

    public String getLamaJamLembur() {
        return lamaJamLembur;
    }

    public void setLamaJamLembur(String lamaJamLembur) {
        this.lamaJamLembur = lamaJamLembur;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdLembur() {
        return idLembur;
    }

    public void setIdLembur(String idLembur) {
        this.idLembur = idLembur;
    }

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

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(String tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJamLemburAwal() {
        return jamLemburAwal;
    }

    public void setJamLemburAwal(String jamLemburAwal) {
        this.jamLemburAwal = jamLemburAwal;
    }

    public String getJamLemburAkhir() {
        return jamLemburAkhir;
    }

    public void setJamLemburAkhir(String jamLemburAkhir) {
        this.jamLemburAkhir = jamLemburAkhir;
    }
}
