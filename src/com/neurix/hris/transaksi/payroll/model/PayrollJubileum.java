package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollJubileum extends BaseModel {
    private String jubileumId;
    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganPeralihanNilai;
    private BigDecimal tunjanganJabStrukturalNilai;
    private BigDecimal besarJubileumNilai;
    private BigDecimal totalJubileumNilai;
    private BigDecimal pphJubileumNilai;
    private BigDecimal nettoJubileumNilai;

    private String gajiGolongan;
    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganPeralihan;
    private String tunjanganJabStruktural;
    private String besarJubileum;
    private String totalJubileum;
    private String pphJubileum;
    private String nettoJubileum;
    private Date tanggalJubileumDate;

    //Tambahan
    private String nip;
    private String tipePegawai;
    private String stTanggalPkwtAwal;
    private String stTanggalPkwtAkhir;
    private String lamaPkwt;
    private String stTanggalPegawaiTetapAwal;
    private String stTanggalPegawaiTetapAkhir;
    private String lamaPegawaiTetap;
    private String masaKerja;
    private String centangJubileum ;

    private String nama;

    public Date getTanggalJubileumDate() {
        return tanggalJubileumDate;
    }

    public void setTanggalJubileumDate(Date tanggalJubileumDate) {
        this.tanggalJubileumDate = tanggalJubileumDate;
    }

    public BigDecimal getNettoJubileumNilai() {
        return nettoJubileumNilai;
    }

    public void setNettoJubileumNilai(BigDecimal nettoJubileumNilai) {
        this.nettoJubileumNilai = nettoJubileumNilai;
    }

    public String getNettoJubileum() {
        return nettoJubileum;
    }

    public void setNettoJubileum(String nettoJubileum) {
        this.nettoJubileum = nettoJubileum;
    }

    public BigDecimal getPphJubileumNilai() {
        return pphJubileumNilai;
    }

    public void setPphJubileumNilai(BigDecimal pphJubileumNilai) {
        this.pphJubileumNilai = pphJubileumNilai;
    }

    public String getPphJubileum() {
        return pphJubileum;
    }

    public void setPphJubileum(String pphJubileum) {
        this.pphJubileum = pphJubileum;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCentangJubileum() {
        return centangJubileum;
    }

    public void setCentangJubileum(String centangJubileum) {
        this.centangJubileum = centangJubileum;
    }

    public String getBesarJubileum() {
        return besarJubileum;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public String getTotalJubileum() {
        return totalJubileum;
    }

    public String getTunjanganJabStruktural() {
        return tunjanganJabStruktural;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setBesarJubileum(String besarJubileum) {
        this.besarJubileum = besarJubileum;
    }

    public BigDecimal getBesarJubileumNilai() {
        return besarJubileumNilai;
    }

    public void setBesarJubileumNilai(BigDecimal besarJubileumNilai) {
        this.besarJubileumNilai = besarJubileumNilai;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public void setTotalJubileum(String totalJubileum) {
        this.totalJubileum = totalJubileum;
    }

    public BigDecimal getTotalJubileumNilai() {
        return totalJubileumNilai;
    }

    public void setTotalJubileumNilai(BigDecimal totalJubileumNilai) {
        this.totalJubileumNilai = totalJubileumNilai;
    }

    public void setTunjanganJabStruktural(String tunjanganJabStruktural) {
        this.tunjanganJabStruktural = tunjanganJabStruktural;
    }

    public BigDecimal getTunjanganJabStrukturalNilai() {
        return tunjanganJabStrukturalNilai;
    }

    public void setTunjanganJabStrukturalNilai(BigDecimal tunjanganJabStrukturalNilai) {
        this.tunjanganJabStrukturalNilai = tunjanganJabStrukturalNilai;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public String getStTanggalPkwtAwal() {
        return stTanggalPkwtAwal;
    }

    public void setStTanggalPkwtAwal(String stTanggalPkwtAwal) {
        this.stTanggalPkwtAwal = stTanggalPkwtAwal;
    }

    public String getStTanggalPkwtAkhir() {
        return stTanggalPkwtAkhir;
    }

    public void setStTanggalPkwtAkhir(String stTanggalPkwtAkhir) {
        this.stTanggalPkwtAkhir = stTanggalPkwtAkhir;
    }

    public String getLamaPkwt() {
        return lamaPkwt;
    }

    public void setLamaPkwt(String lamaPkwt) {
        this.lamaPkwt = lamaPkwt;
    }

    public String getStTanggalPegawaiTetapAwal() {
        return stTanggalPegawaiTetapAwal;
    }

    public void setStTanggalPegawaiTetapAwal(String stTanggalPegawaiTetapAwal) {
        this.stTanggalPegawaiTetapAwal = stTanggalPegawaiTetapAwal;
    }

    public String getStTanggalPegawaiTetapAkhir() {
        return stTanggalPegawaiTetapAkhir;
    }

    public void setStTanggalPegawaiTetapAkhir(String stTanggalPegawaiTetapAkhir) {
        this.stTanggalPegawaiTetapAkhir = stTanggalPegawaiTetapAkhir;
    }

    public String getLamaPegawaiTetap() {
        return lamaPegawaiTetap;
    }

    public void setLamaPegawaiTetap(String lamaPegawaiTetap) {
        this.lamaPegawaiTetap = lamaPegawaiTetap;
    }

    public String getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(String masaKerja) {
        this.masaKerja = masaKerja;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    private String tanggalJubileum;

    private boolean flagJubileum = false;

    public String getTanggalJubileum() {
        return tanggalJubileum;
    }

    public void setTanggalJubileum(String tanggalJubileum) {
        this.tanggalJubileum = tanggalJubileum;
    }

    public String getJubileumId() {
        return jubileumId;
    }

    public void setJubileumId(String jubileumId) {
        this.jubileumId = jubileumId;
    }



    public boolean isFlagJubileum() {
        return flagJubileum;
    }

    public void setFlagJubileum(boolean flagJubileum) {
        this.flagJubileum = flagJubileum;
    }
}