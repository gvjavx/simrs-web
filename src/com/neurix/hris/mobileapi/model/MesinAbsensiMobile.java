package com.neurix.hris.mobileapi.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author gondok
 * Tuesday, 24/03/20 10:28
 */
public class MesinAbsensiMobile {
    private String mesinAbsensiDetailId;
    private String pin;
    private String status;
    private String scanDate;
    private String dateScanDate;
    private String stScanDate;
    private String verifyMode;
    private String workCode;

    private String statusName;
    private String tanggal;
    private String jenisLembur;
    private String jamAwalLembur;
    private String jamSelesaiLembur;
    private String ijinName;
    private String jamKeluarIjin;
    private String jamKembaliIjin;

    private String stTanggalDari;
    private String stTanggalSelesai;
    private String tanggalDari;
    private String tanggalSelesai;
    private String jam;

    private String message;

    public String getMesinAbsensiDetailId() {
        return mesinAbsensiDetailId;
    }

    public void setMesinAbsensiDetailId(String mesinAbsensiDetailId) {
        this.mesinAbsensiDetailId = mesinAbsensiDetailId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScanDate() {
        return scanDate;
    }

    public void setScanDate(String scanDate) {
        this.scanDate = scanDate;
    }

    public String getDateScanDate() {
        return dateScanDate;
    }

    public void setDateScanDate(String dateScanDate) {
        this.dateScanDate = dateScanDate;
    }

    public String getStScanDate() {
        return stScanDate;
    }

    public void setStScanDate(String stScanDate) {
        this.stScanDate = stScanDate;
    }

    public String getVerifyMode() {
        return verifyMode;
    }

    public void setVerifyMode(String verifyMode) {
        this.verifyMode = verifyMode;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenisLembur() {
        return jenisLembur;
    }

    public void setJenisLembur(String jenisLembur) {
        this.jenisLembur = jenisLembur;
    }

    public String getJamAwalLembur() {
        return jamAwalLembur;
    }

    public void setJamAwalLembur(String jamAwalLembur) {
        this.jamAwalLembur = jamAwalLembur;
    }

    public String getJamSelesaiLembur() {
        return jamSelesaiLembur;
    }

    public void setJamSelesaiLembur(String jamSelesaiLembur) {
        this.jamSelesaiLembur = jamSelesaiLembur;
    }

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public String getJamKeluarIjin() {
        return jamKeluarIjin;
    }

    public void setJamKeluarIjin(String jamKeluarIjin) {
        this.jamKeluarIjin = jamKeluarIjin;
    }

    public String getJamKembaliIjin() {
        return jamKembaliIjin;
    }

    public void setJamKembaliIjin(String jamKembaliIjin) {
        this.jamKembaliIjin = jamKembaliIjin;
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

    public String getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(String tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
