package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollRapelJubileumEntity implements Serializable {
    private String rapelJubileumId;
    private String payrollJubileumId;
    private String payrollRapelId;
    private String nip;

    private BigDecimal jubileumGajiGolonganNilaiBaru;
    private BigDecimal jubileumUmkNilaiBaru;
    private BigDecimal jubileumStrukturalNilaiBaru;
    private BigDecimal jubileumJabStrukturalNilaiBaru;
    private BigDecimal jubileumPeralihanNilaiBaru;
    private BigDecimal totalRapelNilaiBaru;

    private String jubileumGajiGolonganBaru;
    private String jubileumUmkBaru;
    private String jubileumStrukturalBaru;
    private String jubileumJabStrukturalBaru;
    private String jubileumPeralihanBaru;
    private String totalRapelBaru;

    private String jubileumGajiGolonganLama;
    private String jubileumUmkLama;
    private String jubileumStrukturalLama;
    private String jubileumJabStrukturalLama;
    private String jubileumPeralihanLama;
    private String totalRapelLama;

    private BigDecimal jubileumGajiGolonganNilaiSelisihBaru;
    private BigDecimal jubileumUmkNilaiSelisihBaru;
    private BigDecimal jubileumStrukturalNilaiSelisihBaru;
    private BigDecimal jubileumJabStrukturalNilaiSelisihBaru;
    private BigDecimal jubileumPeralihanNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisih;
    private BigDecimal totalRapelJubileumNilai;

    private String jubileumGajiGolonganSelisihBaru;
    private String jubileumUmkSelisihBaru;
    private String jubileumStrukturalSelisihBaru;
    private String jubileumJabStrukturalSelisihBaru;
    private String jubileumPeralihanSelisihBaru;
    private String totalRapelSelisih;
    private String totalRapelJubileum;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ItPayrollJubileumEntity payrollJubileumEntity;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getJubileumGajiGolonganBaru() {
        return jubileumGajiGolonganBaru;
    }

    public void setJubileumGajiGolonganBaru(String jubileumGajiGolonganBaru) {
        this.jubileumGajiGolonganBaru = jubileumGajiGolonganBaru;
    }

    public String getJubileumGajiGolonganLama() {
        return jubileumGajiGolonganLama;
    }

    public void setJubileumGajiGolonganLama(String jubileumGajiGolonganLama) {
        this.jubileumGajiGolonganLama = jubileumGajiGolonganLama;
    }

    public BigDecimal getJubileumGajiGolonganNilaiBaru() {
        return jubileumGajiGolonganNilaiBaru;
    }

    public void setJubileumGajiGolonganNilaiBaru(BigDecimal jubileumGajiGolonganNilaiBaru) {
        this.jubileumGajiGolonganNilaiBaru = jubileumGajiGolonganNilaiBaru;
    }

    public BigDecimal getJubileumGajiGolonganNilaiSelisihBaru() {
        return jubileumGajiGolonganNilaiSelisihBaru;
    }

    public void setJubileumGajiGolonganNilaiSelisihBaru(BigDecimal jubileumGajiGolonganNilaiSelisihBaru) {
        this.jubileumGajiGolonganNilaiSelisihBaru = jubileumGajiGolonganNilaiSelisihBaru;
    }

    public String getJubileumGajiGolonganSelisihBaru() {
        return jubileumGajiGolonganSelisihBaru;
    }

    public void setJubileumGajiGolonganSelisihBaru(String jubileumGajiGolonganSelisihBaru) {
        this.jubileumGajiGolonganSelisihBaru = jubileumGajiGolonganSelisihBaru;
    }

    public String getJubileumJabStrukturalBaru() {
        return jubileumJabStrukturalBaru;
    }

    public void setJubileumJabStrukturalBaru(String jubileumJabStrukturalBaru) {
        this.jubileumJabStrukturalBaru = jubileumJabStrukturalBaru;
    }

    public String getJubileumJabStrukturalLama() {
        return jubileumJabStrukturalLama;
    }

    public void setJubileumJabStrukturalLama(String jubileumJabStrukturalLama) {
        this.jubileumJabStrukturalLama = jubileumJabStrukturalLama;
    }

    public BigDecimal getJubileumJabStrukturalNilaiBaru() {
        return jubileumJabStrukturalNilaiBaru;
    }

    public void setJubileumJabStrukturalNilaiBaru(BigDecimal jubileumJabStrukturalNilaiBaru) {
        this.jubileumJabStrukturalNilaiBaru = jubileumJabStrukturalNilaiBaru;
    }

    public BigDecimal getJubileumJabStrukturalNilaiSelisihBaru() {
        return jubileumJabStrukturalNilaiSelisihBaru;
    }

    public void setJubileumJabStrukturalNilaiSelisihBaru(BigDecimal jubileumJabStrukturalNilaiSelisihBaru) {
        this.jubileumJabStrukturalNilaiSelisihBaru = jubileumJabStrukturalNilaiSelisihBaru;
    }

    public String getJubileumJabStrukturalSelisihBaru() {
        return jubileumJabStrukturalSelisihBaru;
    }

    public void setJubileumJabStrukturalSelisihBaru(String jubileumJabStrukturalSelisihBaru) {
        this.jubileumJabStrukturalSelisihBaru = jubileumJabStrukturalSelisihBaru;
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

    public String getJubileumStrukturalBaru() {
        return jubileumStrukturalBaru;
    }

    public void setJubileumStrukturalBaru(String jubileumStrukturalBaru) {
        this.jubileumStrukturalBaru = jubileumStrukturalBaru;
    }

    public String getJubileumStrukturalLama() {
        return jubileumStrukturalLama;
    }

    public void setJubileumStrukturalLama(String jubileumStrukturalLama) {
        this.jubileumStrukturalLama = jubileumStrukturalLama;
    }

    public BigDecimal getJubileumStrukturalNilaiBaru() {
        return jubileumStrukturalNilaiBaru;
    }

    public void setJubileumStrukturalNilaiBaru(BigDecimal jubileumStrukturalNilaiBaru) {
        this.jubileumStrukturalNilaiBaru = jubileumStrukturalNilaiBaru;
    }

    public BigDecimal getJubileumStrukturalNilaiSelisihBaru() {
        return jubileumStrukturalNilaiSelisihBaru;
    }

    public void setJubileumStrukturalNilaiSelisihBaru(BigDecimal jubileumStrukturalNilaiSelisihBaru) {
        this.jubileumStrukturalNilaiSelisihBaru = jubileumStrukturalNilaiSelisihBaru;
    }

    public String getJubileumStrukturalSelisihBaru() {
        return jubileumStrukturalSelisihBaru;
    }

    public void setJubileumStrukturalSelisihBaru(String jubileumStrukturalSelisihBaru) {
        this.jubileumStrukturalSelisihBaru = jubileumStrukturalSelisihBaru;
    }

    public String getJubileumUmkBaru() {
        return jubileumUmkBaru;
    }

    public void setJubileumUmkBaru(String jubileumUmkBaru) {
        this.jubileumUmkBaru = jubileumUmkBaru;
    }

    public String getJubileumUmkLama() {
        return jubileumUmkLama;
    }

    public void setJubileumUmkLama(String jubileumUmkLama) {
        this.jubileumUmkLama = jubileumUmkLama;
    }

    public BigDecimal getJubileumUmkNilaiBaru() {
        return jubileumUmkNilaiBaru;
    }

    public void setJubileumUmkNilaiBaru(BigDecimal jubileumUmkNilaiBaru) {
        this.jubileumUmkNilaiBaru = jubileumUmkNilaiBaru;
    }

    public BigDecimal getJubileumUmkNilaiSelisihBaru() {
        return jubileumUmkNilaiSelisihBaru;
    }

    public void setJubileumUmkNilaiSelisihBaru(BigDecimal jubileumUmkNilaiSelisihBaru) {
        this.jubileumUmkNilaiSelisihBaru = jubileumUmkNilaiSelisihBaru;
    }

    public String getJubileumUmkSelisihBaru() {
        return jubileumUmkSelisihBaru;
    }

    public void setJubileumUmkSelisihBaru(String jubileumUmkSelisihBaru) {
        this.jubileumUmkSelisihBaru = jubileumUmkSelisihBaru;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public ItPayrollJubileumEntity getPayrollJubileumEntity() {
        return payrollJubileumEntity;
    }

    public void setPayrollJubileumEntity(ItPayrollJubileumEntity payrollJubileumEntity) {
        this.payrollJubileumEntity = payrollJubileumEntity;
    }

    public String getPayrollJubileumId() {
        return payrollJubileumId;
    }

    public void setPayrollJubileumId(String payrollJubileumId) {
        this.payrollJubileumId = payrollJubileumId;
    }

    public String getPayrollRapelId() {
        return payrollRapelId;
    }

    public void setPayrollRapelId(String payrollRapelId) {
        this.payrollRapelId = payrollRapelId;
    }

    public String getRapelJubileumId() {
        return rapelJubileumId;
    }

    public void setRapelJubileumId(String rapelJubileumId) {
        this.rapelJubileumId = rapelJubileumId;
    }

    public String getTotalRapelBaru() {
        return totalRapelBaru;
    }

    public void setTotalRapelBaru(String totalRapelBaru) {
        this.totalRapelBaru = totalRapelBaru;
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

    public String getTotalRapelLama() {
        return totalRapelLama;
    }

    public void setTotalRapelLama(String totalRapelLama) {
        this.totalRapelLama = totalRapelLama;
    }

    public BigDecimal getTotalRapelNilaiBaru() {
        return totalRapelNilaiBaru;
    }

    public void setTotalRapelNilaiBaru(BigDecimal totalRapelNilaiBaru) {
        this.totalRapelNilaiBaru = totalRapelNilaiBaru;
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
}