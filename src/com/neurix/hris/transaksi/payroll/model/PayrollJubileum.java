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
    private String payrollId;
    private String nip;


    private BigDecimal totalJubileumNilai;
    private BigDecimal pphJubileumNilai;
    private BigDecimal nettoJubileumNilai;

    private BigDecimal gajiNilai;
    private BigDecimal sankhusNilai;
    private BigDecimal tunjanganjabatanNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganFungsionalNilai;
    private BigDecimal tunjanganPeralihanNilai;
    private BigDecimal tunjanganTambahanNilai;
    private BigDecimal pemondokanNilai;
    private BigDecimal komunikasiNilai;

    private Date tanggalJubileumDate;
    private String stTanggalJubileum;


    private String totalJubileum;
    private String pphJubileum;
    private String nettoJubileum;

    private String gaji;
    private String sankhus;
    private String tunjanganJabatan;
    private String tunjanganStruktural;
    private String tunjanganFungsional;
    private String tunjanganPeralihan;
    private String tunjanganTambahan;
    private String pemondokan;
    private String komunikasi;

    private String keteranganEmas;
    private String keteranganjubileum;

    public String getKeteranganjubileum() {
        return keteranganjubileum;
    }

    public void setKeteranganjubileum(String keteranganjubileum) {
        this.keteranganjubileum = keteranganjubileum;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getGajiNilai() {
        return gajiNilai;
    }

    public void setGajiNilai(BigDecimal gajiNilai) {
        this.gajiNilai = gajiNilai;
    }

    public String getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(String komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getKomunikasiNilai() {
        return komunikasiNilai;
    }

    public void setKomunikasiNilai(BigDecimal komunikasiNilai) {
        this.komunikasiNilai = komunikasiNilai;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(String pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getPemondokanNilai() {
        return pemondokanNilai;
    }

    public void setPemondokanNilai(BigDecimal pemondokanNilai) {
        this.pemondokanNilai = pemondokanNilai;
    }

    public String getSankhus() {
        return sankhus;
    }

    public void setSankhus(String sankhus) {
        this.sankhus = sankhus;
    }

    public BigDecimal getSankhusNilai() {
        return sankhusNilai;
    }

    public void setSankhusNilai(BigDecimal sankhusNilai) {
        this.sankhusNilai = sankhusNilai;
    }

    public String getStTanggalJubileum() {
        return stTanggalJubileum;
    }

    public void setStTanggalJubileum(String stTanggalJubileum) {
        this.stTanggalJubileum = stTanggalJubileum;
    }

    public String getTunjanganFungsional() {
        return tunjanganFungsional;
    }

    public void setTunjanganFungsional(String tunjanganFungsional) {
        this.tunjanganFungsional = tunjanganFungsional;
    }

    public BigDecimal getTunjanganFungsionalNilai() {
        return tunjanganFungsionalNilai;
    }

    public void setTunjanganFungsionalNilai(BigDecimal tunjanganFungsionalNilai) {
        this.tunjanganFungsionalNilai = tunjanganFungsionalNilai;
    }

    public String getTunjanganJabatan() {
        return tunjanganJabatan;
    }

    public void setTunjanganJabatan(String tunjanganJabatan) {
        this.tunjanganJabatan = tunjanganJabatan;
    }

    public BigDecimal getTunjanganjabatanNilai() {
        return tunjanganjabatanNilai;
    }

    public void setTunjanganjabatanNilai(BigDecimal tunjanganjabatanNilai) {
        this.tunjanganjabatanNilai = tunjanganjabatanNilai;
    }

    public String getTunjanganTambahan() {
        return tunjanganTambahan;
    }

    public void setTunjanganTambahan(String tunjanganTambahan) {
        this.tunjanganTambahan = tunjanganTambahan;
    }

    public BigDecimal getTunjanganTambahanNilai() {
        return tunjanganTambahanNilai;
    }

    public void setTunjanganTambahanNilai(BigDecimal tunjanganTambahanNilai) {
        this.tunjanganTambahanNilai = tunjanganTambahanNilai;
    }

    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganJabStrukturalNilai;
    private BigDecimal besarJubileumNilai;

    private BigDecimal peralihanNilai;

    private String gajiGolongan;
    private String tunjanganUmk;
    private String tunjanganJabStruktural;
    private String besarJubileum;

    private String peralihan;

    //Tambahan
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
    public String getPeralihan() {
        return peralihan;
    }

    public void setPeralihan(String peralihan) {
        this.peralihan = peralihan;
    }

    public BigDecimal getPeralihanNilai() {
        return peralihanNilai;
    }

    public void setPeralihanNilai(BigDecimal peralihanNilai) {
        this.peralihanNilai = peralihanNilai;
    }

    public String getKeteranganEmas() {
        return keteranganEmas;
    }

    public void setKeteranganEmas(String keteranganEmas) {
        this.keteranganEmas = keteranganEmas;
    }

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