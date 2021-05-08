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

public class ItPayrollRapelInsentifEntity implements Serializable {
    private String rapelInsentifId;
    private String payrollInsentifId;
    private String payrollRapelId;
    private String nip;
    private int masaKerja;
    private BigDecimal potonganInsentif;
    private BigDecimal potonganInsentifIndividuBaruNilai;
    private BigDecimal potonganInsentifIndividuLamaNilai;

    private BigDecimal insentifGajiGolonganNilaiBaru;
    private BigDecimal insentifUmkNilaiBaru;
    private BigDecimal insentifStrukturalNilaiBaru;
    private BigDecimal insentifJabStrukturalNilaiBaru;
    private BigDecimal insentifStrategisNilaiBaru;
    private BigDecimal insentifPeralihanNilaiBaru;

    private String insentifGajiGolonganBaru;
    private String insentifUmkBaru;
    private String insentifStrukturalBaru;
    private String insentifJabStrukturalBaru;
    private String insentifStrategisBaru;
    private String insentifPeraihanBaru;

    private BigDecimal insentifJumlahLamaNilai;
    private BigDecimal insentifJumlahBaruNilai;
    private BigDecimal insentifBrutoBaruNilai;
    private BigDecimal insentifBrutoLamaNIlai;
    private BigDecimal totalRapelInsentifNilai;
    private BigDecimal smkStandart;
    private BigDecimal smkAngka;

    private String insentifJumlahLama;
    private String insentifJumlahBaru;
    private String smkHuruf;
    private String insentifBrutoLama;
    private String insentifBrutoBaru;
    private String totalRapelInsentif;

    private int bulanMulai;
    private int bulanSampai;
    private int tahunInsentif;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public int getBulanMulai() {
        return bulanMulai;
    }

    public void setBulanMulai(int bulanMulai) {
        this.bulanMulai = bulanMulai;
    }

    public int getBulanSampai() {
        return bulanSampai;
    }

    public void setBulanSampai(int bulanSampai) {
        this.bulanSampai = bulanSampai;
    }

    public int getTahunInsentif() {
        return tahunInsentif;
    }

    public void setTahunInsentif(int tahunInsentif) {
        this.tahunInsentif = tahunInsentif;
    }

    public String getRapelInsentifId() {
        return rapelInsentifId;
    }

    public void setRapelInsentifId(String rapelInsentifId) {
        this.rapelInsentifId = rapelInsentifId;
    }

    public String getPayrollInsentifId() {
        return payrollInsentifId;
    }

    public void setPayrollInsentifId(String payrollInsentifId) {
        this.payrollInsentifId = payrollInsentifId;
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

    public int getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(int masaKerja) {
        this.masaKerja = masaKerja;
    }

    public BigDecimal getPotonganInsentif() {
        return potonganInsentif;
    }

    public void setPotonganInsentif(BigDecimal potonganInsentif) {
        this.potonganInsentif = potonganInsentif;
    }

    public BigDecimal getPotonganInsentifIndividuBaruNilai() {
        return potonganInsentifIndividuBaruNilai;
    }

    public void setPotonganInsentifIndividuBaruNilai(BigDecimal potonganInsentifIndividuBaruNilai) {
        this.potonganInsentifIndividuBaruNilai = potonganInsentifIndividuBaruNilai;
    }

    public BigDecimal getPotonganInsentifIndividuLamaNilai() {
        return potonganInsentifIndividuLamaNilai;
    }

    public void setPotonganInsentifIndividuLamaNilai(BigDecimal potonganInsentifIndividuLamaNilai) {
        this.potonganInsentifIndividuLamaNilai = potonganInsentifIndividuLamaNilai;
    }

    public BigDecimal getInsentifGajiGolonganNilaiBaru() {
        return insentifGajiGolonganNilaiBaru;
    }

    public void setInsentifGajiGolonganNilaiBaru(BigDecimal insentifGajiGolonganNilaiBaru) {
        this.insentifGajiGolonganNilaiBaru = insentifGajiGolonganNilaiBaru;
    }

    public BigDecimal getInsentifUmkNilaiBaru() {
        return insentifUmkNilaiBaru;
    }

    public void setInsentifUmkNilaiBaru(BigDecimal insentifUmkNilaiBaru) {
        this.insentifUmkNilaiBaru = insentifUmkNilaiBaru;
    }

    public BigDecimal getInsentifStrukturalNilaiBaru() {
        return insentifStrukturalNilaiBaru;
    }

    public void setInsentifStrukturalNilaiBaru(BigDecimal insentifStrukturalNilaiBaru) {
        this.insentifStrukturalNilaiBaru = insentifStrukturalNilaiBaru;
    }

    public BigDecimal getInsentifJabStrukturalNilaiBaru() {
        return insentifJabStrukturalNilaiBaru;
    }

    public void setInsentifJabStrukturalNilaiBaru(BigDecimal insentifJabStrukturalNilaiBaru) {
        this.insentifJabStrukturalNilaiBaru = insentifJabStrukturalNilaiBaru;
    }

    public BigDecimal getInsentifStrategisNilaiBaru() {
        return insentifStrategisNilaiBaru;
    }

    public void setInsentifStrategisNilaiBaru(BigDecimal insentifStrategisNilaiBaru) {
        this.insentifStrategisNilaiBaru = insentifStrategisNilaiBaru;
    }

    public BigDecimal getInsentifPeralihanNilaiBaru() {
        return insentifPeralihanNilaiBaru;
    }

    public void setInsentifPeralihanNilaiBaru(BigDecimal insentifPeralihanNilaiBaru) {
        this.insentifPeralihanNilaiBaru = insentifPeralihanNilaiBaru;
    }

    public String getInsentifGajiGolonganBaru() {
        return insentifGajiGolonganBaru;
    }

    public void setInsentifGajiGolonganBaru(String insentifGajiGolonganBaru) {
        this.insentifGajiGolonganBaru = insentifGajiGolonganBaru;
    }

    public String getInsentifUmkBaru() {
        return insentifUmkBaru;
    }

    public void setInsentifUmkBaru(String insentifUmkBaru) {
        this.insentifUmkBaru = insentifUmkBaru;
    }

    public String getInsentifStrukturalBaru() {
        return insentifStrukturalBaru;
    }

    public void setInsentifStrukturalBaru(String insentifStrukturalBaru) {
        this.insentifStrukturalBaru = insentifStrukturalBaru;
    }

    public String getInsentifJabStrukturalBaru() {
        return insentifJabStrukturalBaru;
    }

    public void setInsentifJabStrukturalBaru(String insentifJabStrukturalBaru) {
        this.insentifJabStrukturalBaru = insentifJabStrukturalBaru;
    }

    public String getInsentifStrategisBaru() {
        return insentifStrategisBaru;
    }

    public void setInsentifStrategisBaru(String insentifStrategisBaru) {
        this.insentifStrategisBaru = insentifStrategisBaru;
    }

    public String getInsentifPeraihanBaru() {
        return insentifPeraihanBaru;
    }

    public void setInsentifPeraihanBaru(String insentifPeraihanBaru) {
        this.insentifPeraihanBaru = insentifPeraihanBaru;
    }

    public BigDecimal getInsentifJumlahLamaNilai() {
        return insentifJumlahLamaNilai;
    }

    public void setInsentifJumlahLamaNilai(BigDecimal insentifJumlahLamaNilai) {
        this.insentifJumlahLamaNilai = insentifJumlahLamaNilai;
    }

    public BigDecimal getInsentifJumlahBaruNilai() {
        return insentifJumlahBaruNilai;
    }

    public void setInsentifJumlahBaruNilai(BigDecimal insentifJumlahBaruNilai) {
        this.insentifJumlahBaruNilai = insentifJumlahBaruNilai;
    }

    public BigDecimal getInsentifBrutoBaruNilai() {
        return insentifBrutoBaruNilai;
    }

    public void setInsentifBrutoBaruNilai(BigDecimal insentifBrutoBaruNilai) {
        this.insentifBrutoBaruNilai = insentifBrutoBaruNilai;
    }

    public BigDecimal getInsentifBrutoLamaNIlai() {
        return insentifBrutoLamaNIlai;
    }

    public void setInsentifBrutoLamaNIlai(BigDecimal insentifBrutoLamaNIlai) {
        this.insentifBrutoLamaNIlai = insentifBrutoLamaNIlai;
    }

    public BigDecimal getTotalRapelInsentifNilai() {
        return totalRapelInsentifNilai;
    }

    public void setTotalRapelInsentifNilai(BigDecimal totalRapelInsentifNilai) {
        this.totalRapelInsentifNilai = totalRapelInsentifNilai;
    }

    public BigDecimal getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(BigDecimal smkStandart) {
        this.smkStandart = smkStandart;
    }

    public BigDecimal getSmkAngka() {
        return smkAngka;
    }

    public void setSmkAngka(BigDecimal smkAngka) {
        this.smkAngka = smkAngka;
    }

    public String getInsentifJumlahLama() {
        return insentifJumlahLama;
    }

    public void setInsentifJumlahLama(String insentifJumlahLama) {
        this.insentifJumlahLama = insentifJumlahLama;
    }

    public String getInsentifJumlahBaru() {
        return insentifJumlahBaru;
    }

    public void setInsentifJumlahBaru(String insentifJumlahBaru) {
        this.insentifJumlahBaru = insentifJumlahBaru;
    }

    public String getSmkHuruf() {
        return smkHuruf;
    }

    public void setSmkHuruf(String smkHuruf) {
        this.smkHuruf = smkHuruf;
    }

    public String getInsentifBrutoLama() {
        return insentifBrutoLama;
    }

    public void setInsentifBrutoLama(String insentifBrutoLama) {
        this.insentifBrutoLama = insentifBrutoLama;
    }

    public String getInsentifBrutoBaru() {
        return insentifBrutoBaru;
    }

    public void setInsentifBrutoBaru(String insentifBrutoBaru) {
        this.insentifBrutoBaru = insentifBrutoBaru;
    }

    public String getTotalRapelInsentif() {
        return totalRapelInsentif;
    }

    public void setTotalRapelInsentif(String totalRapelInsentif) {
        this.totalRapelInsentif = totalRapelInsentif;
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