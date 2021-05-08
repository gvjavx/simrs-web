package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PegawaiTambahanAbsensi extends BaseModel {
    private String pegawaiTambahanAbsensiId;
    private String pin;
    private String nama;
    private String statusAbsensi;
    private String jamPulang;
    private String jamMasuk;
    private Date tanggal;

    private String stTanggal;
    private String jamMasukDb;
    private String jamPulangDb;
    private String statusAbsensiName;
    private String positionName;
    private String divisi;
    private String unit;
    private String bagian;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String absensi;
    private String stUangmakan;


    public String getAbsensi() {
        return absensi;
    }

    public void setAbsensi(String absensi) {
        this.absensi = absensi;
    }

    public String getStUangmakan() {
        return stUangmakan;
    }

    public void setStUangmakan(String stUangmakan) {
        this.stUangmakan = stUangmakan;
    }

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTanggalSelesai() {
        return stTanggalSelesai;
    }

    public void setStTanggalSelesai(String stTanggalSelesai) {
        this.stTanggalSelesai = stTanggalSelesai;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getStatusAbsensiName() {
        return statusAbsensiName;
    }

    public void setStatusAbsensiName(String statusAbsensiName) {
        this.statusAbsensiName = statusAbsensiName;
    }

    public String getJamMasukDb() {
        return jamMasukDb;
    }

    public void setJamMasukDb(String jamMasukDb) {
        this.jamMasukDb = jamMasukDb;
    }

    public String getJamPulangDb() {
        return jamPulangDb;
    }

    public void setJamPulangDb(String jamPulangDb) {
        this.jamPulangDb = jamPulangDb;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getPegawaiTambahanAbsensiId() {
        return pegawaiTambahanAbsensiId;
    }

    public void setPegawaiTambahanAbsensiId(String pegawaiTambahanAbsensiId) {
        this.pegawaiTambahanAbsensiId = pegawaiTambahanAbsensiId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatusAbsensi() {
        return statusAbsensi;
    }

    public void setStatusAbsensi(String statusAbsensi) {
        this.statusAbsensi = statusAbsensi;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}