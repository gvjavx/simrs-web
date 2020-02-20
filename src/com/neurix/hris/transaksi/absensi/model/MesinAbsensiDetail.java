package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

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
public class MesinAbsensiDetail extends BaseModel {
    private String mesinAbsensiDetailId;
    private String pin;
    private String status;
    private Timestamp scanDate;
    private Date dateScanDate;
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
    private Date tanggalDari;
    private Date tanggalSelesai;
    private String jam;

    public Date getDateScanDate() {
        return dateScanDate;
    }

    public void setDateScanDate(Date dateScanDate) {
        this.dateScanDate = dateScanDate;
    }

    public String getStScanDate() {
        return stScanDate;
    }

    public void setStScanDate(String stScanDate) {
        this.stScanDate = stScanDate;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public Date getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(Date tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStTanggalDari() {return stTanggalDari;}
    public void setStTanggalDari(String stTanggalDari) {this.stTanggalDari = stTanggalDari;}
    public String getStTanggalSelesai() {return stTanggalSelesai;}

    public void setStTanggalSelesai(String stTanggalSelesai) {this.stTanggalSelesai = stTanggalSelesai;}

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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

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

    public Timestamp getScanDate() {
        return scanDate;
    }

    public void setScanDate(Timestamp scanDate) {
        this.scanDate = scanDate;
    }

    public String getVerifyMode() {
        return verifyMode;
    }

    public void setVerifyMode(String verifyMode) {
        this.verifyMode = verifyMode;
    }
}
