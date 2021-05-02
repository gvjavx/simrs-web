package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollRapelJubileum extends BaseModel {
    private String rapelJubileumId;
    private String nip;

    private String payrollId;
    private String positionName;
    private String nama;
    private String golonganName;
    private String golonganNameLama;
    private String statusKeluarga;
    private int jumlahAnak;
    private int point;
    private int pointLama;

    private String rapelId;
    private String payrollJubileumId;

    private BigDecimal jubileumGajiGolonganNilaiBaru;
    private BigDecimal jubileumUmkNilaiBaru;
    private BigDecimal jubileumStrukturalNilaiBaru;
    private BigDecimal jubileumJabStrukturalNilaiBaru;
    private BigDecimal jubileumPeralihanNilaiBaru;

    private BigDecimal totalRapelNilaiBaru;
    private BigDecimal totalRapelNilaiLama;
    private BigDecimal totalRapelNilaiSelisih;
    private BigDecimal totalRapelJubileumNilai;
    private BigDecimal totalRapelJubileumLamaNilai;

    private Date tanggalJubileum;

    private String jubileumGajiGolonganBaru;
    private String jubileumUmkBaru;
    private String jubileumStrukturalBaru;
    private String jubileumJabStrukturalBaru;
    private String jubileumPeralihanBaru;

    private String totalRapelBaru;
    private String totalRapelLama;
    private String totalRapelSelisih;
    private String totalRapelJubileum;
    private String totalRapelJubileumLama;

    private String jubileumGajiGolonganLama;
    private String jubileumUmkLama;
    private String jubileumStrukturalLama;
    private String jubileumJabStrukturalLama;
    private String jubileumPeralihanLama;

    private BigDecimal jubileumGajiGolonganNilaiLama;
    private BigDecimal jubileumUmkNilaiLama;
    private BigDecimal jubileumStrukturalNilaiLama;
    private BigDecimal jubileumJabStrukturalNilaiLama;
    private BigDecimal jubileumPeralihanNilaiLama;

    private BigDecimal jubileumGajiGolonganNilaiSelisihBaru;
    private BigDecimal jubileumUmkNilaiSelisihBaru;
    private BigDecimal jubileumStrukturalNilaiSelisihBaru;
    private BigDecimal jubileumJabStrukturalNilaiSelisihBaru;
    private BigDecimal jubileumPeralihanNilaiSelisihBaru;

    private BigDecimal subTotalRapelNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisihBaru;

    private BigDecimal subTotalRapelNilaiSelisihLama;
    private BigDecimal totalRapelNilaiSelisihLama;

    private String jubileumGajiGolonganSelisihBaru;
    private String jubileumUmkSelisihBaru;
    private String jubileumStrukturalSelisihBaru;
    private String jubileumJabStrukturalSelisihBaru;
    private String jubileumPeralihanSelisihBaru;

    private String subTotalRapelSelisihBaru;
    private String totalRapelSelisihBaru;
    private String subTotalRapelSelisihLama;
    private String totalRapelSelisihLama;

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

    public Date getTanggalJubileum() {
        return tanggalJubileum;
    }

    public void setTanggalJubileum(Date tanggalJubileum) {
        this.tanggalJubileum = tanggalJubileum;
    }

    public String getTotalRapelJubileumLama() {
        return totalRapelJubileumLama;
    }

    public void setTotalRapelJubileumLama(String totalRapelJubileumLama) {
        this.totalRapelJubileumLama = totalRapelJubileumLama;
    }

    public BigDecimal getTotalRapelJubileumLamaNilai() {
        return totalRapelJubileumLamaNilai;
    }

    public void setTotalRapelJubileumLamaNilai(BigDecimal totalRapelJubileumLamaNilai) {
        this.totalRapelJubileumLamaNilai = totalRapelJubileumLamaNilai;
    }

    public BigDecimal getSubTotalRapelNilaiSelisihLama() {
        return subTotalRapelNilaiSelisihLama;
    }

    public void setSubTotalRapelNilaiSelisihLama(BigDecimal subTotalRapelNilaiSelisihLama) {
        this.subTotalRapelNilaiSelisihLama = subTotalRapelNilaiSelisihLama;
    }

    public String getSubTotalRapelSelisihLama() {
        return subTotalRapelSelisihLama;
    }

    public void setSubTotalRapelSelisihLama(String subTotalRapelSelisihLama) {
        this.subTotalRapelSelisihLama = subTotalRapelSelisihLama;
    }

    public BigDecimal getTotalRapelNilaiSelisihLama() {
        return totalRapelNilaiSelisihLama;
    }

    public void setTotalRapelNilaiSelisihLama(BigDecimal totalRapelNilaiSelisihLama) {
        this.totalRapelNilaiSelisihLama = totalRapelNilaiSelisihLama;
    }

    public String getTotalRapelSelisihLama() {
        return totalRapelSelisihLama;
    }

    public void setTotalRapelSelisihLama(String totalRapelSelisihLama) {
        this.totalRapelSelisihLama = totalRapelSelisihLama;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public BigDecimal getJubileumGajiGolonganNilaiLama() {
        return jubileumGajiGolonganNilaiLama;
    }

    public void setJubileumGajiGolonganNilaiLama(BigDecimal jubileumGajiGolonganNilaiLama) {
        this.jubileumGajiGolonganNilaiLama = jubileumGajiGolonganNilaiLama;
    }

    public BigDecimal getJubileumJabStrukturalNilaiLama() {
        return jubileumJabStrukturalNilaiLama;
    }

    public void setJubileumJabStrukturalNilaiLama(BigDecimal jubileumJabStrukturalNilaiLama) {
        this.jubileumJabStrukturalNilaiLama = jubileumJabStrukturalNilaiLama;
    }

    public BigDecimal getJubileumPeralihanNilaiLama() {
        return jubileumPeralihanNilaiLama;
    }

    public void setJubileumPeralihanNilaiLama(BigDecimal jubileumPeralihanNilaiLama) {
        this.jubileumPeralihanNilaiLama = jubileumPeralihanNilaiLama;
    }

    public BigDecimal getJubileumStrukturalNilaiLama() {
        return jubileumStrukturalNilaiLama;
    }

    public void setJubileumStrukturalNilaiLama(BigDecimal jubileumStrukturalNilaiLama) {
        this.jubileumStrukturalNilaiLama = jubileumStrukturalNilaiLama;
    }

    public BigDecimal getJubileumUmkNilaiLama() {
        return jubileumUmkNilaiLama;
    }

    public void setJubileumUmkNilaiLama(BigDecimal jubileumUmkNilaiLama) {
        this.jubileumUmkNilaiLama = jubileumUmkNilaiLama;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public BigDecimal getTotalRapelNilaiSelisih() {
        return totalRapelNilaiSelisih;
    }

    public void setTotalRapelNilaiSelisih(BigDecimal totalRapelNilaiSelisih) {
        this.totalRapelNilaiSelisih = totalRapelNilaiSelisih;
    }

    public String getTotalRapelSelisih() {
        return totalRapelSelisih;
    }

    public void setTotalRapelSelisih(String totalRapelSelisih) {
        this.totalRapelSelisih = totalRapelSelisih;
    }

    public String getTotalRapelLama() {
        return totalRapelLama;
    }

    public void setTotalRapelLama(String totalRapelLama) {
        this.totalRapelLama = totalRapelLama;
    }

    public BigDecimal getTotalRapelNilaiLama() {
        return totalRapelNilaiLama;
    }

    public void setTotalRapelNilaiLama(BigDecimal totalRapelNilaiLama) {
        this.totalRapelNilaiLama = totalRapelNilaiLama;
    }

    public String getTotalRapelJubileum() {
        return totalRapelJubileum;
    }

    public void setTotalRapelJubileum(String totalRapelJubileum) {
        this.totalRapelJubileum = totalRapelJubileum;
    }

    public BigDecimal getTotalRapelJubileumNilai() {
        return totalRapelJubileumNilai;
    }

    public void setTotalRapelJubileumNilai(BigDecimal totalRapelJubileumNilai) {
        this.totalRapelJubileumNilai = totalRapelJubileumNilai;
    }

    public String getJubileumPeralihanBaru() {
        return jubileumPeralihanBaru;
    }

    public void setJubileumPeralihanBaru(String jubileumPeralihanBaru) {
        this.jubileumPeralihanBaru = jubileumPeralihanBaru;
    }

    public String getJubileumPeralihanLama() {
        return jubileumPeralihanLama;
    }

    public void setJubileumPeralihanLama(String jubileumPeralihanLama) {
        this.jubileumPeralihanLama = jubileumPeralihanLama;
    }

    public BigDecimal getJubileumPeralihanNilaiBaru() {
        return jubileumPeralihanNilaiBaru;
    }

    public void setJubileumPeralihanNilaiBaru(BigDecimal jubileumPeralihanNilaiBaru) {
        this.jubileumPeralihanNilaiBaru = jubileumPeralihanNilaiBaru;
    }

    public BigDecimal getJubileumPeralihanNilaiSelisihBaru() {
        return jubileumPeralihanNilaiSelisihBaru;
    }

    public void setJubileumPeralihanNilaiSelisihBaru(BigDecimal jubileumPeralihanNilaiSelisihBaru) {
        this.jubileumPeralihanNilaiSelisihBaru = jubileumPeralihanNilaiSelisihBaru;
    }

    public String getJubileumPeralihanSelisihBaru() {
        return jubileumPeralihanSelisihBaru;
    }

    public void setJubileumPeralihanSelisihBaru(String jubileumPeralihanSelisihBaru) {
        this.jubileumPeralihanSelisihBaru = jubileumPeralihanSelisihBaru;
    }

    public String getJubileumGajiGolonganLama() {
        return jubileumGajiGolonganLama;
    }

    public void setJubileumGajiGolonganLama(String jubileumGajiGolonganLama) {
        this.jubileumGajiGolonganLama = jubileumGajiGolonganLama;
    }

    public String getJubileumUmkLama() {
        return jubileumUmkLama;
    }

    public void setJubileumUmkLama(String jubileumUmkLama) {
        this.jubileumUmkLama = jubileumUmkLama;
    }

    public String getJubileumStrukturalLama() {
        return jubileumStrukturalLama;
    }

    public void setJubileumStrukturalLama(String jubileumStrukturalLama) {
        this.jubileumStrukturalLama = jubileumStrukturalLama;
    }

    public String getJubileumJabStrukturalLama() {
        return jubileumJabStrukturalLama;
    }

    public void setJubileumJabStrukturalLama(String jubileumJabStrukturalLama) {
        this.jubileumJabStrukturalLama = jubileumJabStrukturalLama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getSubTotalRapelNilaiSelisihBaru() {
        return subTotalRapelNilaiSelisihBaru;
    }

    public void setSubTotalRapelNilaiSelisihBaru(BigDecimal subTotalRapelNilaiSelisihBaru) {
        this.subTotalRapelNilaiSelisihBaru = subTotalRapelNilaiSelisihBaru;
    }

    public String getSubTotalRapelSelisihBaru() {
        return subTotalRapelSelisihBaru;
    }

    public void setSubTotalRapelSelisihBaru(String subTotalRapelSelisihBaru) {
        this.subTotalRapelSelisihBaru = subTotalRapelSelisihBaru;
    }

    public String getRapelJubileumId() {
        return rapelJubileumId;
    }

    public void setRapelJubileumId(String rapelJubileumId) {
        this.rapelJubileumId = rapelJubileumId;
    }

    public String getPayrollJubileumId() {
        return payrollJubileumId;
    }

    public void setPayrollJubileumId(String payrollJubileumId) {
        this.payrollJubileumId = payrollJubileumId;
    }

    public BigDecimal getJubileumGajiGolonganNilaiBaru() {
        return jubileumGajiGolonganNilaiBaru;
    }

    public void setJubileumGajiGolonganNilaiBaru(BigDecimal jubileumGajiGolonganNilaiBaru) {
        this.jubileumGajiGolonganNilaiBaru = jubileumGajiGolonganNilaiBaru;
    }

    public BigDecimal getJubileumUmkNilaiBaru() {
        return jubileumUmkNilaiBaru;
    }

    public void setJubileumUmkNilaiBaru(BigDecimal jubileumUmkNilaiBaru) {
        this.jubileumUmkNilaiBaru = jubileumUmkNilaiBaru;
    }

    public BigDecimal getJubileumStrukturalNilaiBaru() {
        return jubileumStrukturalNilaiBaru;
    }

    public void setJubileumStrukturalNilaiBaru(BigDecimal jubileumStrukturalNilaiBaru) {
        this.jubileumStrukturalNilaiBaru = jubileumStrukturalNilaiBaru;
    }

    public BigDecimal getJubileumJabStrukturalNilaiBaru() {
        return jubileumJabStrukturalNilaiBaru;
    }

    public void setJubileumJabStrukturalNilaiBaru(BigDecimal jubileumJabStrukturalNilaiBaru) {
        this.jubileumJabStrukturalNilaiBaru = jubileumJabStrukturalNilaiBaru;
    }

    public BigDecimal getTotalRapelNilaiBaru() {
        return totalRapelNilaiBaru;
    }

    public void setTotalRapelNilaiBaru(BigDecimal totalRapelNilaiBaru) {
        this.totalRapelNilaiBaru = totalRapelNilaiBaru;
    }

    public String getJubileumGajiGolonganBaru() {
        return jubileumGajiGolonganBaru;
    }

    public void setJubileumGajiGolonganBaru(String jubileumGajiGolonganBaru) {
        this.jubileumGajiGolonganBaru = jubileumGajiGolonganBaru;
    }

    public String getJubileumUmkBaru() {
        return jubileumUmkBaru;
    }

    public void setJubileumUmkBaru(String jubileumUmkBaru) {
        this.jubileumUmkBaru = jubileumUmkBaru;
    }

    public String getJubileumStrukturalBaru() {
        return jubileumStrukturalBaru;
    }

    public void setJubileumStrukturalBaru(String jubileumStrukturalBaru) {
        this.jubileumStrukturalBaru = jubileumStrukturalBaru;
    }

    public String getJubileumJabStrukturalBaru() {
        return jubileumJabStrukturalBaru;
    }

    public void setJubileumJabStrukturalBaru(String jubileumJabStrukturalBaru) {
        this.jubileumJabStrukturalBaru = jubileumJabStrukturalBaru;
    }

    public String getTotalRapelBaru() {
        return totalRapelBaru;
    }

    public void setTotalRapelBaru(String totalRapelBaru) {
        this.totalRapelBaru = totalRapelBaru;
    }

    public BigDecimal getJubileumGajiGolonganNilaiSelisihBaru() {
        return jubileumGajiGolonganNilaiSelisihBaru;
    }

    public void setJubileumGajiGolonganNilaiSelisihBaru(BigDecimal jubileumGajiGolonganNilaiSelisihBaru) {
        this.jubileumGajiGolonganNilaiSelisihBaru = jubileumGajiGolonganNilaiSelisihBaru;
    }

    public BigDecimal getJubileumUmkNilaiSelisihBaru() {
        return jubileumUmkNilaiSelisihBaru;
    }

    public void setJubileumUmkNilaiSelisihBaru(BigDecimal jubileumUmkNilaiSelisihBaru) {
        this.jubileumUmkNilaiSelisihBaru = jubileumUmkNilaiSelisihBaru;
    }

    public BigDecimal getJubileumStrukturalNilaiSelisihBaru() {
        return jubileumStrukturalNilaiSelisihBaru;
    }

    public void setJubileumStrukturalNilaiSelisihBaru(BigDecimal jubileumStrukturalNilaiSelisihBaru) {
        this.jubileumStrukturalNilaiSelisihBaru = jubileumStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getJubileumJabStrukturalNilaiSelisihBaru() {
        return jubileumJabStrukturalNilaiSelisihBaru;
    }

    public void setJubileumJabStrukturalNilaiSelisihBaru(BigDecimal jubileumJabStrukturalNilaiSelisihBaru) {
        this.jubileumJabStrukturalNilaiSelisihBaru = jubileumJabStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getTotalRapelNilaiSelisihBaru() {
        return totalRapelNilaiSelisihBaru;
    }

    public void setTotalRapelNilaiSelisihBaru(BigDecimal totalRapelNilaiSelisihBaru) {
        this.totalRapelNilaiSelisihBaru = totalRapelNilaiSelisihBaru;
    }

    public String getJubileumGajiGolonganSelisihBaru() {
        return jubileumGajiGolonganSelisihBaru;
    }

    public void setJubileumGajiGolonganSelisihBaru(String jubileumGajiGolonganSelisihBaru) {
        this.jubileumGajiGolonganSelisihBaru = jubileumGajiGolonganSelisihBaru;
    }

    public String getJubileumUmkSelisihBaru() {
        return jubileumUmkSelisihBaru;
    }

    public void setJubileumUmkSelisihBaru(String jubileumUmkSelisihBaru) {
        this.jubileumUmkSelisihBaru = jubileumUmkSelisihBaru;
    }

    public String getJubileumStrukturalSelisihBaru() {
        return jubileumStrukturalSelisihBaru;
    }

    public void setJubileumStrukturalSelisihBaru(String jubileumStrukturalSelisihBaru) {
        this.jubileumStrukturalSelisihBaru = jubileumStrukturalSelisihBaru;
    }

    public String getJubileumJabStrukturalSelisihBaru() {
        return jubileumJabStrukturalSelisihBaru;
    }

    public void setJubileumJabStrukturalSelisihBaru(String jubileumJabStrukturalSelisihBaru) {
        this.jubileumJabStrukturalSelisihBaru = jubileumJabStrukturalSelisihBaru;
    }

    public String getTotalRapelSelisihBaru() {
        return totalRapelSelisihBaru;
    }

    public void setTotalRapelSelisihBaru(String totalRapelSelisihBaru) {
        this.totalRapelSelisihBaru = totalRapelSelisihBaru;
    }
}