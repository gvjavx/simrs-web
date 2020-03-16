package com.neurix.hris.master.sertifikat.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSertifikatEntity implements Serializable {
    private String sertifikatId;
    private String nip;
    private Date tanggalPengesahan;
    private String stTanggalPengesahan;
    private String jenis;
    private Date masaBerlaku;
    private String stMasaBerlaku;
    private int jumlahHari;
    private Date masaBerakhir;
    private String stMasaBerakhir;
    private String nama;
    private String lembaga;
    private String tempatPelaksana;
    private double nilai;
    private String lulus;
    private String prestasiGrade;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public int getJumlahHari() {
        return jumlahHari;
    }

    public void setJumlahHari(int jumlahHari) {
        this.jumlahHari = jumlahHari;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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