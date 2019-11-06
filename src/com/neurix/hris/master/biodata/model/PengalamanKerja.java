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
    private String jabatan;
    private Date tahunMasuk;
    private Date tahunKeluar;
    private String stTtahunMasuk;
    private String branchId;
    private String stTahunKeluar;
    private String tipePegawai;
    private String tipePegawaiId;
    private String GolonganId;
    private String GolonganName;
    private String point;
    private String pointLebih;
    private String nilaiSmk;
    private String gradeSmk;

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
        return GolonganId;
    }

    public void setGolonganId(String golonganId) {
        GolonganId = golonganId;
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
}