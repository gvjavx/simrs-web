package com.neurix.hris.master.dashboardAbsensi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class DashboardAbsensi extends BaseModel {
    String nip;
    String nama;
    String branchId;
    String branchName;
    String divisiId;
    String divisiName;
    String bagianId;
    String bagianName;
    String positionId;
    String positionName;
    String jumlahTelat;
    Date tanggalMasuk;
    String jamMasuk;
    Date dateJamMasuk;
    String strTanggalMasuk;

    // medical record
    String medicalId;
    BigDecimal jumlahPengobatan ;
    String strJumlahPengobatan ;
    String strTanggalBerobat;
    Date tanggalBerobat;
    String tipeOrangBerobat;
    String tipePengobatan;

    // Lembur
    String absensiId;
    BigDecimal jumlahLembur ;
    String strJumlahLembur ;
    String strTanggalLembur;
    Date tanggalLembur;
    BigDecimal lamaLembur;
    BigDecimal jamLembur;

    public String getAbsensiId() {
        return absensiId;
    }

    public void setAbsensiId(String absensiId) {
        this.absensiId = absensiId;
    }

    public BigDecimal getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(BigDecimal jamLembur) {
        this.jamLembur = jamLembur;
    }

    public BigDecimal getJumlahLembur() {
        return jumlahLembur;
    }

    public void setJumlahLembur(BigDecimal jumlahLembur) {
        this.jumlahLembur = jumlahLembur;
    }

    public BigDecimal getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(BigDecimal lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public String getStrJumlahLembur() {
        return strJumlahLembur;
    }

    public void setStrJumlahLembur(String strJumlahLembur) {
        this.strJumlahLembur = strJumlahLembur;
    }

    public String getStrTanggalLembur() {
        return strTanggalLembur;
    }

    public void setStrTanggalLembur(String strTanggalLembur) {
        this.strTanggalLembur = strTanggalLembur;
    }

    public Date getTanggalLembur() {
        return tanggalLembur;
    }

    public void setTanggalLembur(Date tanggalLembur) {
        this.tanggalLembur = tanggalLembur;
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

    public String getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(String medicalId) {
        this.medicalId = medicalId;
    }

    public BigDecimal getJumlahPengobatan() {
        return jumlahPengobatan;
    }

    public void setJumlahPengobatan(BigDecimal jumlahPengobatan) {
        this.jumlahPengobatan = jumlahPengobatan;
    }

    public String getStrJumlahPengobatan() {
        return strJumlahPengobatan;
    }

    public void setStrJumlahPengobatan(String strJumlahPengobatan) {
        this.strJumlahPengobatan = strJumlahPengobatan;
    }

    public String getStrTanggalBerobat() {
        return strTanggalBerobat;
    }

    public void setStrTanggalBerobat(String strTanggalBerobat) {
        this.strTanggalBerobat = strTanggalBerobat;
    }

    public Date getTanggalBerobat() {
        return tanggalBerobat;
    }

    public void setTanggalBerobat(Date tanggalBerobat) {
        this.tanggalBerobat = tanggalBerobat;
    }

    public String getTipeOrangBerobat() {
        return tipeOrangBerobat;
    }

    public void setTipeOrangBerobat(String tipeOrangBerobat) {
        this.tipeOrangBerobat = tipeOrangBerobat;
    }

    public String getTipePengobatan() {
        return tipePengobatan;
    }

    public void setTipePengobatan(String tipePengobatan) {
        this.tipePengobatan = tipePengobatan;
    }

    public Date getDateJamMasuk() {
        return dateJamMasuk;
    }

    public void setDateJamMasuk(Date dateJamMasuk) {
        this.dateJamMasuk = dateJamMasuk;
    }

    public String getStrTanggalMasuk() {
        return strTanggalMasuk;
    }

    public void setStrTanggalMasuk(String strTanggalMasuk) {
        this.strTanggalMasuk = strTanggalMasuk;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
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

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getJumlahTelat() {
        return jumlahTelat;
    }

    public void setJumlahTelat(String jumlahTelat) {
        this.jumlahTelat = jumlahTelat;
    }

    public java.sql.Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(java.sql.Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
}