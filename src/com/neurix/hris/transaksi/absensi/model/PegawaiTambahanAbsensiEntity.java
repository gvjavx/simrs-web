package com.neurix.hris.transaksi.absensi.model;

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
public class PegawaiTambahanAbsensiEntity implements Serializable {
    private String pegawaiTambahanAbsensiId;
    private String pin;
    private String nama;
    private String statusAbsensi;
    private String jamPulang;
    private String jamMasuk;
    private Date tanggal;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
