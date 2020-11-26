package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MesinAbsensi extends BaseModel {
    private String absensiId;
    private String pin;
    private Date tanggal;
    private String jamMasuk;
    private String jamKeluar;
    private String status;
    private String flagAbsensi;
    private Double realisasiJamLembur;

    private String stTanggal;
    private String nip;
    private String nama;
    private String statusName;
    private String tipePegawai;
    private String statusGiling;
    private String branchId;
    private String jamMasukDb;
    private String jamPulangDb;
    private String saved;
    private String shift;
    private String lembur;

    private String mesinId;
    private String mesinAddress;
    private String mesinSn;
    private String stCreatedDate;
    private String stLastUpdate;

    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLembur() {
        return lembur;
    }

    public void setLembur(String lembur) {
        this.lembur = lembur;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
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
    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Double getRealisasiJamLembur() {
        return realisasiJamLembur;
    }

    public void setRealisasiJamLembur(Double realisasiJamLembur) {
        this.realisasiJamLembur = realisasiJamLembur;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAbsensiId() {
        return absensiId;
    }

    public void setAbsensiId(String absensiId) {
        this.absensiId = absensiId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlagAbsensi() {
        return flagAbsensi;
    }

    public void setFlagAbsensi(String flagAbsensi) {
        this.flagAbsensi = flagAbsensi;
    }

    public String getMesinAddress() {
        return mesinAddress;
    }

    public void setMesinAddress(String mesinAddress) {
        this.mesinAddress = mesinAddress;
    }

    public String getMesinId() {
        return mesinId;
    }

    public void setMesinId(String mesinId) {
        this.mesinId = mesinId;
    }

    public String getMesinSn() {
        return mesinSn;
    }

    public void setMesinSn(String mesinSn) {
        this.mesinSn = mesinSn;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }
}
