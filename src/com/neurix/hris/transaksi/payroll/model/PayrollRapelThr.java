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

public class PayrollRapelThr extends BaseModel {
    private String rapelThrId;
    private String payrollRapelId;
    private String nip;

    private String payrollId;
    private String nama;
    private String golonganName;
    private String golonganNameLama;
    private String statusKeluarga;
    private int jumlahAnak;
    private int point;
    private int pointLama;

    private BigDecimal thrGajiGolonganNilaiBaru;
    private BigDecimal thrUmkNilaiBaru;
    private BigDecimal thrPeralihanNilaiBaru;
    private BigDecimal thrStrukturalNilaiBaru;
    private BigDecimal thrJabStrukturalNilaiBaru;
    private BigDecimal thrStrategisNilaiBaru;
    private BigDecimal totalRapelNilaiBaru;
    private BigDecimal totalRapelBersihNilaiBaru;

    private BigDecimal thrGajiGolonganNilaiLama;
    private BigDecimal thrUmkNilaiLama;
    private BigDecimal thrPeralihanNilaiLama;
    private BigDecimal thrStrukturalNilaiLama;
    private BigDecimal thrJabStrukturalNilaiLama;
    private BigDecimal thrStrategisNilaiLama;
    private BigDecimal totalRapelNilaiLama;
    private BigDecimal totalRapelBersihNilaiLama;

    private String thrGajiGolonganBaru;
    private String thrUmkBaru;
    private String thrPeralihanBaru;
    private String thrStrukturalBaru;
    private String thrJabStrukturalBaru;
    private String thrStrategisBaru;
    private String totalRapelBaru;
    private String totalRapelBersihBaru;

    private String thrGajiGolonganLama;
    private String thrUmkLama;
    private String thrPeralihanLama;
    private String thrStrukturalLama;
    private String thrJabStrukturalLama;
    private String thrStrategisLama;
    private String totalRapelLama;
    private String totalRapelBersihLama;

    private BigDecimal thrGajiGolonganNilaiSelisihBaru;
    private BigDecimal thrPeralihanNilaiSelisihBaru;
    private BigDecimal thrUmkNilaiSelisihBaru;
    private BigDecimal thrStrukturalNilaiSelisihBaru;
    private BigDecimal thrJabStrukturalNilaiSelisihBaru;
    private BigDecimal thrStrategisNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisihBaru;
    private BigDecimal totalRapelFinalNilai;

    private String thrGajiGolonganSelisihBaru;
    private String thrUmkSelisihBaru;
    private String thrPeralihanSelisihBaru;
    private String thrStrukturalSelisihBaru;
    private String thrJabStrukturalSelisihBaru;
    private String thrStrategisSelisihBaru;
    private String totalRapelSelisihBaru;
    private String totalRapelFinal;
    private String bulanAktif;

    private String rapelId;
    private String payrollThrId;
    private String payrollPendidikanId;

    public BigDecimal getTotalRapelFinalNilai() {
        return totalRapelFinalNilai;
    }

    public void setTotalRapelFinalNilai(BigDecimal totalRapelFinalNilai) {
        this.totalRapelFinalNilai = totalRapelFinalNilai;
    }

    public String getTotalRapelFinal() {
        return totalRapelFinal;
    }

    public void setTotalRapelFinal(String totalRapelFinal) {
        this.totalRapelFinal = totalRapelFinal;
    }

    public BigDecimal getTotalRapelBersihNilaiBaru() {
        return totalRapelBersihNilaiBaru;
    }

    public void setTotalRapelBersihNilaiBaru(BigDecimal totalRapelBersihNilaiBaru) {
        this.totalRapelBersihNilaiBaru = totalRapelBersihNilaiBaru;
    }

    public String getTotalRapelBersihBaru() {
        return totalRapelBersihBaru;
    }

    public void setTotalRapelBersihBaru(String totalRapelBersihBaru) {
        this.totalRapelBersihBaru = totalRapelBersihBaru;
    }

    public BigDecimal getTotalRapelBersihNilaiLama() {
        return totalRapelBersihNilaiLama;
    }

    public void setTotalRapelBersihNilaiLama(BigDecimal totalRapelBersihNilaiLama) {
        this.totalRapelBersihNilaiLama = totalRapelBersihNilaiLama;
    }

    public String getTotalRapelBersihLama() {
        return totalRapelBersihLama;
    }

    public void setTotalRapelBersihLama(String totalRapelBersihLama) {
        this.totalRapelBersihLama = totalRapelBersihLama;
    }

    public String getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(String bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public BigDecimal getThrPeralihanNilaiBaru() {
        return thrPeralihanNilaiBaru;
    }

    public void setThrPeralihanNilaiBaru(BigDecimal thrPeralihanNilaiBaru) {
        this.thrPeralihanNilaiBaru = thrPeralihanNilaiBaru;
    }

    public BigDecimal getThrPeralihanNilaiLama() {
        return thrPeralihanNilaiLama;
    }

    public void setThrPeralihanNilaiLama(BigDecimal thrPeralihanNilaiLama) {
        this.thrPeralihanNilaiLama = thrPeralihanNilaiLama;
    }

    public String getThrPeralihanBaru() {
        return thrPeralihanBaru;
    }

    public void setThrPeralihanBaru(String thrPeralihanBaru) {
        this.thrPeralihanBaru = thrPeralihanBaru;
    }

    public String getThrPeralihanLama() {
        return thrPeralihanLama;
    }

    public void setThrPeralihanLama(String thrPeralihanLama) {
        this.thrPeralihanLama = thrPeralihanLama;
    }

    public BigDecimal getThrPeralihanNilaiSelisihBaru() {
        return thrPeralihanNilaiSelisihBaru;
    }

    public void setThrPeralihanNilaiSelisihBaru(BigDecimal thrPeralihanNilaiSelisihBaru) {
        this.thrPeralihanNilaiSelisihBaru = thrPeralihanNilaiSelisihBaru;
    }

    public String getThrPeralihanSelisihBaru() {
        return thrPeralihanSelisihBaru;
    }

    public void setThrPeralihanSelisihBaru(String thrPeralihanSelisihBaru) {
        this.thrPeralihanSelisihBaru = thrPeralihanSelisihBaru;
    }

    public String getGolonganNameLama() {
        return golonganNameLama;
    }

    public void setGolonganNameLama(String golonganNameLama) {
        this.golonganNameLama = golonganNameLama;
    }

    public int getPointLama() {
        return pointLama;
    }

    public void setPointLama(int pointLama) {
        this.pointLama = pointLama;
    }

    public String getPayrollPendidikanId() {
        return payrollPendidikanId;
    }

    public void setPayrollPendidikanId(String payrollPendidikanId) {
        this.payrollPendidikanId = payrollPendidikanId;
    }

    public BigDecimal getThrGajiGolonganNilaiLama() {
        return thrGajiGolonganNilaiLama;
    }

    public void setThrGajiGolonganNilaiLama(BigDecimal thrGajiGolonganNilaiLama) {
        this.thrGajiGolonganNilaiLama = thrGajiGolonganNilaiLama;
    }

    public BigDecimal getThrUmkNilaiLama() {
        return thrUmkNilaiLama;
    }

    public void setThrUmkNilaiLama(BigDecimal thrUmkNilaiLama) {
        this.thrUmkNilaiLama = thrUmkNilaiLama;
    }

    public BigDecimal getThrStrukturalNilaiLama() {
        return thrStrukturalNilaiLama;
    }

    public void setThrStrukturalNilaiLama(BigDecimal thrStrukturalNilaiLama) {
        this.thrStrukturalNilaiLama = thrStrukturalNilaiLama;
    }

    public BigDecimal getThrJabStrukturalNilaiLama() {
        return thrJabStrukturalNilaiLama;
    }

    public void setThrJabStrukturalNilaiLama(BigDecimal thrJabStrukturalNilaiLama) {
        this.thrJabStrukturalNilaiLama = thrJabStrukturalNilaiLama;
    }

    public BigDecimal getThrStrategisNilaiLama() {
        return thrStrategisNilaiLama;
    }

    public void setThrStrategisNilaiLama(BigDecimal thrStrategisNilaiLama) {
        this.thrStrategisNilaiLama = thrStrategisNilaiLama;
    }

    public BigDecimal getTotalRapelNilaiLama() {
        return totalRapelNilaiLama;
    }

    public void setTotalRapelNilaiLama(BigDecimal totalRapelNilaiLama) {
        this.totalRapelNilaiLama = totalRapelNilaiLama;
    }

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getPayrollRapelId() {
        return payrollRapelId;
    }

    public void setPayrollRapelId(String payrollRapelId) {
        this.payrollRapelId = payrollRapelId;
    }

    public String getThrGajiGolonganLama() {
        return thrGajiGolonganLama;
    }

    public void setThrGajiGolonganLama(String thrGajiGolonganLama) {
        this.thrGajiGolonganLama = thrGajiGolonganLama;
    }

    public String getThrUmkLama() {
        return thrUmkLama;
    }

    public void setThrUmkLama(String thrUmkLama) {
        this.thrUmkLama = thrUmkLama;
    }

    public String getThrStrukturalLama() {
        return thrStrukturalLama;
    }

    public void setThrStrukturalLama(String thrStrukturalLama) {
        this.thrStrukturalLama = thrStrukturalLama;
    }

    public String getThrJabStrukturalLama() {
        return thrJabStrukturalLama;
    }

    public void setThrJabStrukturalLama(String thrJabStrukturalLama) {
        this.thrJabStrukturalLama = thrJabStrukturalLama;
    }

    public String getThrStrategisLama() {
        return thrStrategisLama;
    }

    public void setThrStrategisLama(String thrStrategisLama) {
        this.thrStrategisLama = thrStrategisLama;
    }

    public String getTotalRapelLama() {
        return totalRapelLama;
    }

    public void setTotalRapelLama(String totalRapelLama) {
        this.totalRapelLama = totalRapelLama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRapelThrId() {
        return rapelThrId;
    }

    public void setRapelThrId(String rapelThrId) {
        this.rapelThrId = rapelThrId;
    }

    public String getPayrollThrId() {
        return payrollThrId;
    }

    public void setPayrollThrId(String payrollThrId) {
        this.payrollThrId = payrollThrId;
    }

    public BigDecimal getThrGajiGolonganNilaiBaru() {
        return thrGajiGolonganNilaiBaru;
    }

    public void setThrGajiGolonganNilaiBaru(BigDecimal thrGajiGolonganNilaiBaru) {
        this.thrGajiGolonganNilaiBaru = thrGajiGolonganNilaiBaru;
    }

    public BigDecimal getThrUmkNilaiBaru() {
        return thrUmkNilaiBaru;
    }

    public void setThrUmkNilaiBaru(BigDecimal thrUmkNilaiBaru) {
        this.thrUmkNilaiBaru = thrUmkNilaiBaru;
    }

    public BigDecimal getThrStrukturalNilaiBaru() {
        return thrStrukturalNilaiBaru;
    }

    public void setThrStrukturalNilaiBaru(BigDecimal thrStrukturalNilaiBaru) {
        this.thrStrukturalNilaiBaru = thrStrukturalNilaiBaru;
    }

    public BigDecimal getThrJabStrukturalNilaiBaru() {
        return thrJabStrukturalNilaiBaru;
    }

    public void setThrJabStrukturalNilaiBaru(BigDecimal thrJabStrukturalNilaiBaru) {
        this.thrJabStrukturalNilaiBaru = thrJabStrukturalNilaiBaru;
    }

    public BigDecimal getThrStrategisNilaiBaru() {
        return thrStrategisNilaiBaru;
    }

    public void setThrStrategisNilaiBaru(BigDecimal thrStrategisNilaiBaru) {
        this.thrStrategisNilaiBaru = thrStrategisNilaiBaru;
    }

    public BigDecimal getTotalRapelNilaiBaru() {
        return totalRapelNilaiBaru;
    }

    public void setTotalRapelNilaiBaru(BigDecimal totalRapelNilaiBaru) {
        this.totalRapelNilaiBaru = totalRapelNilaiBaru;
    }

    public String getThrGajiGolonganBaru() {
        return thrGajiGolonganBaru;
    }

    public void setThrGajiGolonganBaru(String thrGajiGolonganBaru) {
        this.thrGajiGolonganBaru = thrGajiGolonganBaru;
    }

    public String getThrUmkBaru() {
        return thrUmkBaru;
    }

    public void setThrUmkBaru(String thrUmkBaru) {
        this.thrUmkBaru = thrUmkBaru;
    }

    public String getThrStrukturalBaru() {
        return thrStrukturalBaru;
    }

    public void setThrStrukturalBaru(String thrStrukturalBaru) {
        this.thrStrukturalBaru = thrStrukturalBaru;
    }

    public String getThrJabStrukturalBaru() {
        return thrJabStrukturalBaru;
    }

    public void setThrJabStrukturalBaru(String thrJabStrukturalBaru) {
        this.thrJabStrukturalBaru = thrJabStrukturalBaru;
    }

    public String getThrStrategisBaru() {
        return thrStrategisBaru;
    }

    public void setThrStrategisBaru(String thrStrategisBaru) {
        this.thrStrategisBaru = thrStrategisBaru;
    }

    public String getTotalRapelBaru() {
        return totalRapelBaru;
    }

    public void setTotalRapelBaru(String totalRapelBaru) {
        this.totalRapelBaru = totalRapelBaru;
    }

    public BigDecimal getThrGajiGolonganNilaiSelisihBaru() {
        return thrGajiGolonganNilaiSelisihBaru;
    }

    public void setThrGajiGolonganNilaiSelisihBaru(BigDecimal thrGajiGolonganNilaiSelisihBaru) {
        this.thrGajiGolonganNilaiSelisihBaru = thrGajiGolonganNilaiSelisihBaru;
    }

    public BigDecimal getThrUmkNilaiSelisihBaru() {
        return thrUmkNilaiSelisihBaru;
    }

    public void setThrUmkNilaiSelisihBaru(BigDecimal thrUmkNilaiSelisihBaru) {
        this.thrUmkNilaiSelisihBaru = thrUmkNilaiSelisihBaru;
    }

    public BigDecimal getThrStrukturalNilaiSelisihBaru() {
        return thrStrukturalNilaiSelisihBaru;
    }

    public void setThrStrukturalNilaiSelisihBaru(BigDecimal thrStrukturalNilaiSelisihBaru) {
        this.thrStrukturalNilaiSelisihBaru = thrStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getThrJabStrukturalNilaiSelisihBaru() {
        return thrJabStrukturalNilaiSelisihBaru;
    }

    public void setThrJabStrukturalNilaiSelisihBaru(BigDecimal thrJabStrukturalNilaiSelisihBaru) {
        this.thrJabStrukturalNilaiSelisihBaru = thrJabStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getThrStrategisNilaiSelisihBaru() {
        return thrStrategisNilaiSelisihBaru;
    }

    public void setThrStrategisNilaiSelisihBaru(BigDecimal thrStrategisNilaiSelisihBaru) {
        this.thrStrategisNilaiSelisihBaru = thrStrategisNilaiSelisihBaru;
    }

    public BigDecimal getTotalRapelNilaiSelisihBaru() {
        return totalRapelNilaiSelisihBaru;
    }

    public void setTotalRapelNilaiSelisihBaru(BigDecimal totalRapelNilaiSelisihBaru) {
        this.totalRapelNilaiSelisihBaru = totalRapelNilaiSelisihBaru;
    }

    public String getThrGajiGolonganSelisihBaru() {
        return thrGajiGolonganSelisihBaru;
    }

    public void setThrGajiGolonganSelisihBaru(String thrGajiGolonganSelisihBaru) {
        this.thrGajiGolonganSelisihBaru = thrGajiGolonganSelisihBaru;
    }

    public String getThrUmkSelisihBaru() {
        return thrUmkSelisihBaru;
    }

    public void setThrUmkSelisihBaru(String thrUmkSelisihBaru) {
        this.thrUmkSelisihBaru = thrUmkSelisihBaru;
    }

    public String getThrStrukturalSelisihBaru() {
        return thrStrukturalSelisihBaru;
    }

    public void setThrStrukturalSelisihBaru(String thrStrukturalSelisihBaru) {
        this.thrStrukturalSelisihBaru = thrStrukturalSelisihBaru;
    }

    public String getThrJabStrukturalSelisihBaru() {
        return thrJabStrukturalSelisihBaru;
    }

    public void setThrJabStrukturalSelisihBaru(String thrJabStrukturalSelisihBaru) {
        this.thrJabStrukturalSelisihBaru = thrJabStrukturalSelisihBaru;
    }

    public String getThrStrategisSelisihBaru() {
        return thrStrategisSelisihBaru;
    }

    public void setThrStrategisSelisihBaru(String thrStrategisSelisihBaru) {
        this.thrStrategisSelisihBaru = thrStrategisSelisihBaru;
    }

    public String getTotalRapelSelisihBaru() {
        return totalRapelSelisihBaru;
    }

    public void setTotalRapelSelisihBaru(String totalRapelSelisihBaru) {
        this.totalRapelSelisihBaru = totalRapelSelisihBaru;
    }
}