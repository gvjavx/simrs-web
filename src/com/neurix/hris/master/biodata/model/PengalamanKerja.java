package com.neurix.hris.master.biodata.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PengalamanKerja extends BaseModel {
    private String pengalamanId;
    private String nip;
    private String namaPerusahaan;
    private String branchId;
    private String posisiId;
    private String profesiId;
    private String divisiId;
    private String tipePegawaiId;
    private String golonganId;
    private String point;
    private String pointLebih;
    private String nilaiSmk;
    private String gradeSmk;
    private Date tahunMasuk;

    private String jabatan;
    private Date tahunKeluar;
    private String stTtahunMasuk;
    private String stTahunKeluar;
    private String tipePegawai;
    private String GolonganName;
    private String tanggalMasuk;
    private String tanggalKeluar;
    private String pjsFlag;
    private String divisiName;
    private String positionName;
    private String profesiName;
    private String flagJabatanAktif;

    private String branchName;
    private String tahun;
    private String jabatanName;
    private String jenisPegawaiId;
    private String flagDigaji;

    public String getFlagDigaji() {
        return flagDigaji;
    }

    public void setFlagDigaji(String flagDigaji) {
        this.flagDigaji = flagDigaji;
    }

    public String getJenisPegawaiId() {
        return jenisPegawaiId;
    }

    public void setJenisPegawaiId(String jenisPegawaiId) {
        this.jenisPegawaiId = jenisPegawaiId;
    }

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
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

    public String getFlagJabatanAktif() {
        return flagJabatanAktif;
    }

    public void setFlagJabatanAktif(String flagJabatanAktif) {
        this.flagJabatanAktif = flagJabatanAktif;
    }

    public String getPjsFlag() {
        return pjsFlag;
    }

    public void setPjsFlag(String pjsFlag) {
        this.pjsFlag = pjsFlag;
    }

    public String getPosisiId() {
        return posisiId;
    }

    public void setPosisiId(String posisiId) {
        this.posisiId = posisiId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return GolonganName;
    }

    public void setGolonganName(String golonganName) {
        GolonganName = golonganName;
    }

    public String getGradeSmk() {
        return gradeSmk;
    }

    public void setGradeSmk(String gradeSmk) {
        this.gradeSmk = gradeSmk;
    }

    public String getNilaiSmk() {
        return nilaiSmk;
    }

    public void setNilaiSmk(String nilaiSmk) {
        this.nilaiSmk = nilaiSmk;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPointLebih() {
        return pointLebih;
    }

    public void setPointLebih(String pointLebih) {
        this.pointLebih = pointLebih;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getPengalamanId() {
        return pengalamanId;
    }

    public void setPengalamanId(String pengalamanId) {
        this.pengalamanId = pengalamanId;
    }

    public String getStTahunKeluar() {
        return stTahunKeluar;
    }

    public void setStTahunKeluar(String stTahunKeluar) {
        this.stTahunKeluar = stTahunKeluar;
    }

    public String getStTtahunMasuk() {
        return stTtahunMasuk;
    }

    public void setStTtahunMasuk(String stTtahunMasuk) {
        this.stTtahunMasuk = stTtahunMasuk;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTahunKeluar() {
        return tahunKeluar;
    }

    public void setTahunKeluar(Date tahunKeluar) {
        this.tahunKeluar = tahunKeluar;
    }

    public Date getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(Date tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }
}