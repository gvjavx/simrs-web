package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollThr extends BaseModel {
    private String thrId;
    private String payrollId;

    private String gajiGolongan;
    private String tunjanganUmk; //sankhus
    private String tunjanganStruktural;
    private String tunjanganJabatanStruktural; //tunjangan jabatan
    private String tunjanganStrategis; //tunjangan fungsional
    private String tunjanganPeralihan;
    private String tunjanganPph;
    private String thp;

    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai; //sankhus
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai; //tunjangan jabatan
    private BigDecimal tunjanganStrategisNilai; //tunjangan fungsional nilai
    private BigDecimal tunjanganPeralihanNilai;
    private BigDecimal tunjanganPphNilai;
    private BigDecimal thpNilai;

    //tambahan irfan
    private String tunjanganLain;
    private BigDecimal tunjanganLainNilai;
    private String tunjanganTambahan;
    private BigDecimal tunjanganTambahanNilai;
    private String pemondokan;
    private BigDecimal pemondokanNilai;
    private String komunikasi;
    private BigDecimal komunikasiNilai;
    private String totalRlab;
    private BigDecimal totalRlabNilai;

    private int bulanAktif;

    private String flagThr;
    private String nip;
    private String totalThr;
    private String totalThrBersih;
    private String pphThr;

    private BigDecimal totalThrNilaiBersih;
    private BigDecimal totalThrNilai;
    private BigDecimal pphThrNilai;

    public String getThp() {
        return thp;
    }

    public void setThp(String thp) {
        this.thp = thp;
    }

    public BigDecimal getThpNilai() {
        return thpNilai;
    }

    public void setThpNilai(BigDecimal thpNilai) {
        this.thpNilai = thpNilai;
    }

    public String getPphThr() {
        return pphThr;
    }

    public void setPphThr(String pphThr) {
        this.pphThr = pphThr;
    }

    public BigDecimal getPphThrNilai() {
        return pphThrNilai;
    }

    public void setPphThrNilai(BigDecimal pphThrNilai) {
        this.pphThrNilai = pphThrNilai;
    }

    public String getTotalRlab() {
        return totalRlab;
    }

    public void setTotalRlab(String totalRlab) {
        this.totalRlab = totalRlab;
    }

    public BigDecimal getTotalRlabNilai() {
        return totalRlabNilai;
    }

    public void setTotalRlabNilai(BigDecimal totalRlabNilai) {
        this.totalRlabNilai = totalRlabNilai;
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

    public String getTunjanganLain() {
        return tunjanganLain;
    }

    public void setTunjanganLain(String tunjanganLain) {
        this.tunjanganLain = tunjanganLain;
    }

    public BigDecimal getTunjanganLainNilai() {
        return tunjanganLainNilai;
    }

    public void setTunjanganLainNilai(BigDecimal tunjanganLainNilai) {
        this.tunjanganLainNilai = tunjanganLainNilai;
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

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public String getTotalThrBersih() {
        return totalThrBersih;
    }

    public void setTotalThrBersih(String totalThrBersih) {
        this.totalThrBersih = totalThrBersih;
    }

    public BigDecimal getTotalThrNilaiBersih() {
        return totalThrNilaiBersih;
    }

    public void setTotalThrNilaiBersih(BigDecimal totalThrNilaiBersih) {
        this.totalThrNilaiBersih = totalThrNilaiBersih;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
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

    public String getFlagThr() {
        return flagThr;
    }

    public void setFlagThr(String flagThr) {
        this.flagThr = flagThr;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTotalThr() {
        return totalThr;
    }

    public void setTotalThr(String totalThr) {
        this.totalThr = totalThr;
    }

    public BigDecimal getTotalThrNilai() {
        return totalThrNilai;
    }

    public void setTotalThrNilai(BigDecimal totalThrNilai) {
        this.totalThrNilai = totalThrNilai;
    }

    public BigDecimal getTunjanganJabatanStrukturalNilai() {
        return tunjanganJabatanStrukturalNilai;
    }

    public void setTunjanganJabatanStrukturalNilai(BigDecimal tunjanganJabatanStrukturalNilai) {
        this.tunjanganJabatanStrukturalNilai = tunjanganJabatanStrukturalNilai;
    }

    public BigDecimal getTunjanganPphNilai() {
        return tunjanganPphNilai;
    }

    public void setTunjanganPphNilai(BigDecimal tunjanganPphNilai) {
        this.tunjanganPphNilai = tunjanganPphNilai;
    }

    public BigDecimal getTunjanganStrategisNilai() {
        return tunjanganStrategisNilai;
    }

    public void setTunjanganStrategisNilai(BigDecimal tunjanganStrategisNilai) {
        this.tunjanganStrategisNilai = tunjanganStrategisNilai;
    }

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getThrId() {
        return thrId;
    }

    public void setThrId(String thrId) {
        this.thrId = thrId;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public String getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(String tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public String getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(String tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }
}