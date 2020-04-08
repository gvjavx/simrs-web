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

public class ItPayrollRapelPendidikanEntity implements Serializable {
    private String rapelPendidikanId;
    private String payrollPendidikanId;
    private String payrollRapelId;
    private String nip;

    private BigDecimal pendidikanGajiGolonganNilaiBaru;
    private BigDecimal pendidikanUmkNilaiBaru;
    private BigDecimal pendidikanPeralihanNilaiBaru;
    private BigDecimal pendidikanStrukturalNilaiBaru;
    private BigDecimal pendidikanJabStrukturalNilaiBaru;
    private BigDecimal pendidikanStrategisNilaiBaru;
    private BigDecimal pendidikanAirListrikNilaiBaru;
    private BigDecimal pendidikanKompensasiNilaiBaru;
    private BigDecimal totalRapelNilaiBaru;

    private String pendidikanGajiGolonganBaru;
    private String pendidikanUmkBaru;
    private String pendidikanPeralihanBaru;
    private String pendidikanStrukturalBaru;
    private String pendidikanJabStrukturalBaru;
    private String pendidikanStrategisBaru;
    private String pendidikanAirListrikBaru;
    private String totalRapelBaru;

    private String pendidikanGajiGolonganLama;
    private String pendidikanUmkLama;
    private String pendidikanPeralihanLama;
    private String pendidikanStrukturalLama;
    private String pendidikanJabStrukturalLama;
    private String pendidikanStrategisLama;
    private String pendidikanAirListrikLama;

    private BigDecimal pendidikanGajiGolonganNilaiSelisihBaru;
    private BigDecimal pendidikanUmkNilaiSelisihBaru;
    private BigDecimal pendidikanPeralihanNilaiSelisihBaru;
    private BigDecimal pendidikanStrukturalNilaiSelisihBaru;
    private BigDecimal pendidikanJabStrukturalNilaiSelisihBaru;
    private BigDecimal pendidikanStrategisNilaiSelisihBaru;
    private BigDecimal pendidikanAirListrikNilaiSelisihBaru;
    private BigDecimal pendidikanKompensasiNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisihBaru;
    private BigDecimal totalRapelNilaiSelisih;

    private int bulanAktif;
    private String pendidikanGajiGolonganSelisihBaru;
    private String pendidikanUmkSelisihBaru;
    private String pendidikanPeralihanSelisihBaru;
    private String pendidikanStrukturalSelisihBaru;
    private String pendidikanJabStrukturalSelisihBaru;
    private String pendidikanStrategisSelisihBaru;
    private String pendidikanAirListrikSelisihBaru;
    private String totalRapelSelisihBaru;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public BigDecimal getPendidikanKompensasiNilaiBaru() {
        return pendidikanKompensasiNilaiBaru;
    }

    public void setPendidikanKompensasiNilaiBaru(BigDecimal pendidikanKompensasiNilaiBaru) {
        this.pendidikanKompensasiNilaiBaru = pendidikanKompensasiNilaiBaru;
    }

    public BigDecimal getPendidikanKompensasiNilaiSelisihBaru() {
        return pendidikanKompensasiNilaiSelisihBaru;
    }

    public void setPendidikanKompensasiNilaiSelisihBaru(BigDecimal pendidikanKompensasiNilaiSelisihBaru) {
        this.pendidikanKompensasiNilaiSelisihBaru = pendidikanKompensasiNilaiSelisihBaru;
    }

    public BigDecimal getTotalRapelNilaiSelisih() {
        return totalRapelNilaiSelisih;
    }

    public void setTotalRapelNilaiSelisih(BigDecimal totalRapelNilaiSelisih) {
        this.totalRapelNilaiSelisih = totalRapelNilaiSelisih;
    }

    public int getBulanAktif() {
        return bulanAktif;
    }

    public void setBulanAktif(int bulanAktif) {
        this.bulanAktif = bulanAktif;
    }

    public BigDecimal getPendidikanPeralihanNilaiBaru() {
        return pendidikanPeralihanNilaiBaru;
    }

    public void setPendidikanPeralihanNilaiBaru(BigDecimal pendidikanPeralihanNilaiBaru) {
        this.pendidikanPeralihanNilaiBaru = pendidikanPeralihanNilaiBaru;
    }

    public String getPendidikanPeralihanBaru() {
        return pendidikanPeralihanBaru;
    }

    public void setPendidikanPeralihanBaru(String pendidikanPeralihanBaru) {
        this.pendidikanPeralihanBaru = pendidikanPeralihanBaru;
    }

    public String getPendidikanPeralihanLama() {
        return pendidikanPeralihanLama;
    }

    public void setPendidikanPeralihanLama(String pendidikanPeralihanLama) {
        this.pendidikanPeralihanLama = pendidikanPeralihanLama;
    }

    public BigDecimal getPendidikanPeralihanNilaiSelisihBaru() {
        return pendidikanPeralihanNilaiSelisihBaru;
    }

    public void setPendidikanPeralihanNilaiSelisihBaru(BigDecimal pendidikanPeralihanNilaiSelisihBaru) {
        this.pendidikanPeralihanNilaiSelisihBaru = pendidikanPeralihanNilaiSelisihBaru;
    }

    public String getPendidikanPeralihanSelisihBaru() {
        return pendidikanPeralihanSelisihBaru;
    }

    public void setPendidikanPeralihanSelisihBaru(String pendidikanPeralihanSelisihBaru) {
        this.pendidikanPeralihanSelisihBaru = pendidikanPeralihanSelisihBaru;
    }

    private ItPayrollPendidikanEntity payrollPendidikanEntity;

    public ItPayrollPendidikanEntity getPayrollPendidikanEntity() {
        return payrollPendidikanEntity;
    }

    public void setPayrollPendidikanEntity(ItPayrollPendidikanEntity payrollPendidikanEntity) {
        this.payrollPendidikanEntity = payrollPendidikanEntity;
    }

    public String getRapelPendidikanId() {
        return rapelPendidikanId;
    }

    public void setRapelPendidikanId(String rapelPendidikanId) {
        this.rapelPendidikanId = rapelPendidikanId;
    }

    public String getPayrollPendidikanId() {
        return payrollPendidikanId;
    }

    public void setPayrollPendidikanId(String payrollPendidikanId) {
        this.payrollPendidikanId = payrollPendidikanId;
    }

    public String getPayrollRapelId() {
        return payrollRapelId;
    }

    public void setPayrollRapelId(String payrollRapelId) {
        this.payrollRapelId = payrollRapelId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getPendidikanGajiGolonganNilaiBaru() {
        return pendidikanGajiGolonganNilaiBaru;
    }

    public void setPendidikanGajiGolonganNilaiBaru(BigDecimal pendidikanGajiGolonganNilaiBaru) {
        this.pendidikanGajiGolonganNilaiBaru = pendidikanGajiGolonganNilaiBaru;
    }

    public BigDecimal getPendidikanUmkNilaiBaru() {
        return pendidikanUmkNilaiBaru;
    }

    public void setPendidikanUmkNilaiBaru(BigDecimal pendidikanUmkNilaiBaru) {
        this.pendidikanUmkNilaiBaru = pendidikanUmkNilaiBaru;
    }

    public BigDecimal getPendidikanStrukturalNilaiBaru() {
        return pendidikanStrukturalNilaiBaru;
    }

    public void setPendidikanStrukturalNilaiBaru(BigDecimal pendidikanStrukturalNilaiBaru) {
        this.pendidikanStrukturalNilaiBaru = pendidikanStrukturalNilaiBaru;
    }

    public BigDecimal getPendidikanJabStrukturalNilaiBaru() {
        return pendidikanJabStrukturalNilaiBaru;
    }

    public void setPendidikanJabStrukturalNilaiBaru(BigDecimal pendidikanJabStrukturalNilaiBaru) {
        this.pendidikanJabStrukturalNilaiBaru = pendidikanJabStrukturalNilaiBaru;
    }

    public BigDecimal getPendidikanStrategisNilaiBaru() {
        return pendidikanStrategisNilaiBaru;
    }

    public void setPendidikanStrategisNilaiBaru(BigDecimal pendidikanStrategisNilaiBaru) {
        this.pendidikanStrategisNilaiBaru = pendidikanStrategisNilaiBaru;
    }

    public BigDecimal getPendidikanAirListrikNilaiBaru() {
        return pendidikanAirListrikNilaiBaru;
    }

    public void setPendidikanAirListrikNilaiBaru(BigDecimal pendidikanAirListrikNilaiBaru) {
        this.pendidikanAirListrikNilaiBaru = pendidikanAirListrikNilaiBaru;
    }

    public BigDecimal getTotalRapelNilaiBaru() {
        return totalRapelNilaiBaru;
    }

    public void setTotalRapelNilaiBaru(BigDecimal totalRapelNilaiBaru) {
        this.totalRapelNilaiBaru = totalRapelNilaiBaru;
    }

    public String getPendidikanGajiGolonganBaru() {
        return pendidikanGajiGolonganBaru;
    }

    public void setPendidikanGajiGolonganBaru(String pendidikanGajiGolonganBaru) {
        this.pendidikanGajiGolonganBaru = pendidikanGajiGolonganBaru;
    }

    public String getPendidikanUmkBaru() {
        return pendidikanUmkBaru;
    }

    public void setPendidikanUmkBaru(String pendidikanUmkBaru) {
        this.pendidikanUmkBaru = pendidikanUmkBaru;
    }

    public String getPendidikanStrukturalBaru() {
        return pendidikanStrukturalBaru;
    }

    public void setPendidikanStrukturalBaru(String pendidikanStrukturalBaru) {
        this.pendidikanStrukturalBaru = pendidikanStrukturalBaru;
    }

    public String getPendidikanJabStrukturalBaru() {
        return pendidikanJabStrukturalBaru;
    }

    public void setPendidikanJabStrukturalBaru(String pendidikanJabStrukturalBaru) {
        this.pendidikanJabStrukturalBaru = pendidikanJabStrukturalBaru;
    }

    public String getPendidikanStrategisBaru() {
        return pendidikanStrategisBaru;
    }

    public void setPendidikanStrategisBaru(String pendidikanStrategisBaru) {
        this.pendidikanStrategisBaru = pendidikanStrategisBaru;
    }

    public String getPendidikanAirListrikBaru() {
        return pendidikanAirListrikBaru;
    }

    public void setPendidikanAirListrikBaru(String pendidikanAirListrikBaru) {
        this.pendidikanAirListrikBaru = pendidikanAirListrikBaru;
    }

    public String getTotalRapelBaru() {
        return totalRapelBaru;
    }

    public void setTotalRapelBaru(String totalRapelBaru) {
        this.totalRapelBaru = totalRapelBaru;
    }

    public String getPendidikanGajiGolonganLama() {
        return pendidikanGajiGolonganLama;
    }

    public void setPendidikanGajiGolonganLama(String pendidikanGajiGolonganLama) {
        this.pendidikanGajiGolonganLama = pendidikanGajiGolonganLama;
    }

    public String getPendidikanUmkLama() {
        return pendidikanUmkLama;
    }

    public void setPendidikanUmkLama(String pendidikanUmkLama) {
        this.pendidikanUmkLama = pendidikanUmkLama;
    }

    public String getPendidikanStrukturalLama() {
        return pendidikanStrukturalLama;
    }

    public void setPendidikanStrukturalLama(String pendidikanStrukturalLama) {
        this.pendidikanStrukturalLama = pendidikanStrukturalLama;
    }

    public String getPendidikanJabStrukturalLama() {
        return pendidikanJabStrukturalLama;
    }

    public void setPendidikanJabStrukturalLama(String pendidikanJabStrukturalLama) {
        this.pendidikanJabStrukturalLama = pendidikanJabStrukturalLama;
    }

    public String getPendidikanStrategisLama() {
        return pendidikanStrategisLama;
    }

    public void setPendidikanStrategisLama(String pendidikanStrategisLama) {
        this.pendidikanStrategisLama = pendidikanStrategisLama;
    }

    public String getPendidikanAirListrikLama() {
        return pendidikanAirListrikLama;
    }

    public void setPendidikanAirListrikLama(String pendidikanAirListrikLama) {
        this.pendidikanAirListrikLama = pendidikanAirListrikLama;
    }

    public BigDecimal getPendidikanGajiGolonganNilaiSelisihBaru() {
        return pendidikanGajiGolonganNilaiSelisihBaru;
    }

    public void setPendidikanGajiGolonganNilaiSelisihBaru(BigDecimal pendidikanGajiGolonganNilaiSelisihBaru) {
        this.pendidikanGajiGolonganNilaiSelisihBaru = pendidikanGajiGolonganNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanUmkNilaiSelisihBaru() {
        return pendidikanUmkNilaiSelisihBaru;
    }

    public void setPendidikanUmkNilaiSelisihBaru(BigDecimal pendidikanUmkNilaiSelisihBaru) {
        this.pendidikanUmkNilaiSelisihBaru = pendidikanUmkNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanStrukturalNilaiSelisihBaru() {
        return pendidikanStrukturalNilaiSelisihBaru;
    }

    public void setPendidikanStrukturalNilaiSelisihBaru(BigDecimal pendidikanStrukturalNilaiSelisihBaru) {
        this.pendidikanStrukturalNilaiSelisihBaru = pendidikanStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanJabStrukturalNilaiSelisihBaru() {
        return pendidikanJabStrukturalNilaiSelisihBaru;
    }

    public void setPendidikanJabStrukturalNilaiSelisihBaru(BigDecimal pendidikanJabStrukturalNilaiSelisihBaru) {
        this.pendidikanJabStrukturalNilaiSelisihBaru = pendidikanJabStrukturalNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanStrategisNilaiSelisihBaru() {
        return pendidikanStrategisNilaiSelisihBaru;
    }

    public void setPendidikanStrategisNilaiSelisihBaru(BigDecimal pendidikanStrategisNilaiSelisihBaru) {
        this.pendidikanStrategisNilaiSelisihBaru = pendidikanStrategisNilaiSelisihBaru;
    }

    public BigDecimal getPendidikanAirListrikNilaiSelisihBaru() {
        return pendidikanAirListrikNilaiSelisihBaru;
    }

    public void setPendidikanAirListrikNilaiSelisihBaru(BigDecimal pendidikanAirListrikNilaiSelisihBaru) {
        this.pendidikanAirListrikNilaiSelisihBaru = pendidikanAirListrikNilaiSelisihBaru;
    }

    public BigDecimal getTotalRapelNilaiSelisihBaru() {
        return totalRapelNilaiSelisihBaru;
    }

    public void setTotalRapelNilaiSelisihBaru(BigDecimal totalRapelNilaiSelisihBaru) {
        this.totalRapelNilaiSelisihBaru = totalRapelNilaiSelisihBaru;
    }

    public String getPendidikanGajiGolonganSelisihBaru() {
        return pendidikanGajiGolonganSelisihBaru;
    }

    public void setPendidikanGajiGolonganSelisihBaru(String pendidikanGajiGolonganSelisihBaru) {
        this.pendidikanGajiGolonganSelisihBaru = pendidikanGajiGolonganSelisihBaru;
    }

    public String getPendidikanUmkSelisihBaru() {
        return pendidikanUmkSelisihBaru;
    }

    public void setPendidikanUmkSelisihBaru(String pendidikanUmkSelisihBaru) {
        this.pendidikanUmkSelisihBaru = pendidikanUmkSelisihBaru;
    }

    public String getPendidikanStrukturalSelisihBaru() {
        return pendidikanStrukturalSelisihBaru;
    }

    public void setPendidikanStrukturalSelisihBaru(String pendidikanStrukturalSelisihBaru) {
        this.pendidikanStrukturalSelisihBaru = pendidikanStrukturalSelisihBaru;
    }

    public String getPendidikanJabStrukturalSelisihBaru() {
        return pendidikanJabStrukturalSelisihBaru;
    }

    public void setPendidikanJabStrukturalSelisihBaru(String pendidikanJabStrukturalSelisihBaru) {
        this.pendidikanJabStrukturalSelisihBaru = pendidikanJabStrukturalSelisihBaru;
    }

    public String getPendidikanStrategisSelisihBaru() {
        return pendidikanStrategisSelisihBaru;
    }

    public void setPendidikanStrategisSelisihBaru(String pendidikanStrategisSelisihBaru) {
        this.pendidikanStrategisSelisihBaru = pendidikanStrategisSelisihBaru;
    }

    public String getPendidikanAirListrikSelisihBaru() {
        return pendidikanAirListrikSelisihBaru;
    }

    public void setPendidikanAirListrikSelisihBaru(String pendidikanAirListrikSelisihBaru) {
        this.pendidikanAirListrikSelisihBaru = pendidikanAirListrikSelisihBaru;
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