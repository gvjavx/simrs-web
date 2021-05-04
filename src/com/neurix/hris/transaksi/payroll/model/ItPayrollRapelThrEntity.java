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

public class ItPayrollRapelThrEntity implements Serializable {
    private String rapelThrId;
    private String payrollThrId;
    private String payrollRapelId;
    private String nip;

    private BigDecimal thrGajiGolonganNilaiBaru;
    private BigDecimal thrPeralihanNilaiBaru;
    private BigDecimal thrUmkNilaiBaru;
    private BigDecimal thrStrukturalNilaiBaru;
    private BigDecimal thrJabStrukturalNilaiBaru;
    private BigDecimal thrStrategisNilaiBaru;
    private BigDecimal totalRapelNilaiBaru;

    private String thrGajiGolonganBaru;
    private String thrPeralihanBaru;
    private String thrUmkBaru;
    private String thrStrukturalBaru;
    private String thrJabStrukturalBaru;
    private String thrStrategisBaru;
    private String totalRapelBaru;

    private String thrGajiGolonganLama;
    private String thrUmkLama;
    private String thrStrukturalLama;
    private String thrJabStrukturalLama;
    private String thrStrategisLama;
    private String totalRapelLama;

    private BigDecimal thrGajiGolonganNilaiSelisihBaru;
    private BigDecimal thrUmkNilaiSelisihBaru;
    private BigDecimal thrPeralihanNilaiSelisihBaru;
    private BigDecimal thrStrukturalNilaiSelisihBaru;
    private BigDecimal thrJabStrukturalNilaiSelisihBaru;
    private BigDecimal thrStrategisNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisihBaru;
    private BigDecimal totalRapelBersihNilaiSelisihBaru;

    private String thrGajiGolonganSelisihBaru;
    private String thrUmkSelisihBaru;
    private String thrStrukturalSelisihBaru;
    private String thrJabStrukturalSelisihBaru;
    private String thrStrategisSelisihBaru;
    private String totalRapelSelisihBaru;
    private String bulanAktif;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public BigDecimal getTotalRapelBersihNilaiSelisihBaru() {
        return totalRapelBersihNilaiSelisihBaru;
    }

    public void setTotalRapelBersihNilaiSelisihBaru(BigDecimal totalRapelBersihNilaiSelisihBaru) {
        this.totalRapelBersihNilaiSelisihBaru = totalRapelBersihNilaiSelisihBaru;
    }

    public String getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(String bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public BigDecimal getThrPeralihanNilaiSelisihBaru() {
        return thrPeralihanNilaiSelisihBaru;
    }

    public void setThrPeralihanNilaiSelisihBaru(BigDecimal thrPeralihanNilaiSelisihBaru) {
        this.thrPeralihanNilaiSelisihBaru = thrPeralihanNilaiSelisihBaru;
    }

    public BigDecimal getThrPeralihanNilaiBaru() {
        return thrPeralihanNilaiBaru;
    }

    public void setThrPeralihanNilaiBaru(BigDecimal thrPeralihanNilaiBaru) {
        this.thrPeralihanNilaiBaru = thrPeralihanNilaiBaru;
    }

    public String getThrPeralihanBaru() {
        return thrPeralihanBaru;
    }

    public void setThrPeralihanBaru(String thrPeralihanBaru) {
        this.thrPeralihanBaru = thrPeralihanBaru;
    }

    private ItPayrollThrEntity payrollThrEntity;

    public ItPayrollThrEntity getPayrollThrEntity() {
        return payrollThrEntity;
    }

    public void setPayrollThrEntity(ItPayrollThrEntity payrollThrEntity) {
        this.payrollThrEntity = payrollThrEntity;
    }

    public String getPayrollRapelId() {
        return payrollRapelId;
    }

    public void setPayrollRapelId(String payrollRapelId) {
        this.payrollRapelId = payrollRapelId;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}