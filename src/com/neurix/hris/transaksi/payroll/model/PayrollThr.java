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
    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganJabatanStruktural;
    private String tunjanganStrategis;
    private String tunjanganPeralihan;
    private String tunjanganPph;

    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal tunjanganPeralihanNilai;
    private BigDecimal tunjanganPphNilai;

    private int bulanAktif;

    private String flagThr;
    private String nip;
    private String totalThr;
    private String totalThrBersih;

    private BigDecimal totalThrNilaiBersih;
    private BigDecimal totalThrNilai;

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