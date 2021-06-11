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

public class PayrollRapelInsentif extends BaseModel {
    private String rapelInsentifId;
    private String payrollInsentifId;
    private String nip;

    private BigDecimal insentifGajiGolonganNilaiBaru;
    private BigDecimal insentifUmkNilaiBaru;
    private BigDecimal insentifStrukturalNilaiBaru;
    private BigDecimal insentifJabStrukturalNilaiBaru;
    private BigDecimal insentifStrategisNilaiBaru;
    private BigDecimal insentifPeralihanNilaiBaru;
    private BigDecimal totalRapelNilaiBaru;

    private BigDecimal insentifGajiGolonganNilaiLama;
    private BigDecimal insentifUmkNilaiLama;
    private BigDecimal insentifStrukturalNilaiLama;
    private BigDecimal insentifJabStrukturalNilaiLama;
    private BigDecimal insentifStrategisNilaiLama;
    private BigDecimal insentifPeralihanNilaiLama;
    private BigDecimal totalRapelNilaiLama;

    private String insentifGajiGolonganBaru;
    private String insentifUmkBaru;
    private String insentifStrukturalBaru;
    private String insentifJabStrukturalBaru;
    private String insentifStrategisBaru;
    private String insentifPeralihanBaru;
    private String totalRapelBaru;

    private String insentifGajiGolonganLama;
    private String insentifUmkLama;
    private String insentifStrukturalLama;
    private String insentifJabStrukturalLama;
    private String insentifStrategisLama;
    private String insentifPeralihanLama;


    private String brutoInsentif;
    private String brutoInsentifLama;
    private BigDecimal brutoInsentifNilai;
    private BigDecimal brutoInsentifLamaNilai;
    private int masaKerja;
    private BigDecimal potonganInsentifNilai;
    private String potonganInsentif;
    private BigDecimal potonganInsentifIndividuNilai;
    private String potonganInsentifIndividu;
    private BigDecimal potonganInsentifIndividuLamaNilai;
    private String potonganInsentifIndividuLama;
    private BigDecimal insentifYangDiterimaLamaNilai;
    private String insentifYangDiterimaLama;
    private BigDecimal insentifYangDiterimaBaruNilai;
    private String insentifYangDiterimaBaru;
    private BigDecimal insentifYangDiterimaSelisihNilai;
    private String insentifYangDiterimaSelisih;
    private String smkStandart;
    private BigDecimal smkStandartNilai;
    private String smkHuruf;
    private String smkAngka;
    private BigDecimal smkAngkaNilai;
    private String tahun;
    private String payrollId;

    private String nama;
    private String golonganNameLama;
    private String golonganName;
    private String statusKeluarga;
    private String rapelId;
    private int jumlahAnak;
    private int point;
    private int pointLama;

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

    public BigDecimal getSmkStandartNilai() {
        return smkStandartNilai;
    }

    public void setSmkStandartNilai(BigDecimal smkStandartNilai) {
        this.smkStandartNilai = smkStandartNilai;
    }

    public String getPotonganInsentifIndividuLama() {
        return potonganInsentifIndividuLama;
    }

    public void setPotonganInsentifIndividuLama(String potonganInsentifIndividuLama) {
        this.potonganInsentifIndividuLama = potonganInsentifIndividuLama;
    }

    public BigDecimal getPotonganInsentifIndividuLamaNilai() {
        return potonganInsentifIndividuLamaNilai;
    }

    public void setPotonganInsentifIndividuLamaNilai(BigDecimal potonganInsentifIndividuLamaNilai) {
        this.potonganInsentifIndividuLamaNilai = potonganInsentifIndividuLamaNilai;
    }

    public String getBrutoInsentifLama() {
        return brutoInsentifLama;
    }

    public void setBrutoInsentifLama(String brutoInsentifLama) {
        this.brutoInsentifLama = brutoInsentifLama;
    }

    public BigDecimal getBrutoInsentifLamaNilai() {
        return brutoInsentifLamaNilai;
    }

    public void setBrutoInsentifLamaNilai(BigDecimal brutoInsentifLamaNilai) {
        this.brutoInsentifLamaNilai = brutoInsentifLamaNilai;
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

    public BigDecimal getTotalRapelNilaiLama() {
        return totalRapelNilaiLama;
    }

    public void setTotalRapelNilaiLama(BigDecimal totalRapelNilaiLama) {
        this.totalRapelNilaiLama = totalRapelNilaiLama;
    }

    public String getInsentifYangDiterimaLama() {
        return insentifYangDiterimaLama;
    }

    public void setInsentifYangDiterimaLama(String insentifYangDiterimaLama) {
        this.insentifYangDiterimaLama = insentifYangDiterimaLama;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public BigDecimal getTotalRapelNilaiBaru() {
        return totalRapelNilaiBaru;
    }

    public void setTotalRapelNilaiBaru(BigDecimal totalRapelNilaiBaru) {
        this.totalRapelNilaiBaru = totalRapelNilaiBaru;
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

    public String getInsentifPeralihanBaru() {
        return insentifPeralihanBaru;
    }

    public void setInsentifPeralihanBaru(String insentifPeralihanBaru) {
        this.insentifPeralihanBaru = insentifPeralihanBaru;
    }

    public String getTotalRapelBaru() {
        return totalRapelBaru;
    }

    public void setTotalRapelBaru(String totalRapelBaru) {
        this.totalRapelBaru = totalRapelBaru;
    }

    public String getInsentifGajiGolonganLama() {
        return insentifGajiGolonganLama;
    }

    public void setInsentifGajiGolonganLama(String insentifGajiGolonganLama) {
        this.insentifGajiGolonganLama = insentifGajiGolonganLama;
    }

    public String getInsentifUmkLama() {
        return insentifUmkLama;
    }

    public void setInsentifUmkLama(String insentifUmkLama) {
        this.insentifUmkLama = insentifUmkLama;
    }

    public String getInsentifStrukturalLama() {
        return insentifStrukturalLama;
    }

    public void setInsentifStrukturalLama(String insentifStrukturalLama) {
        this.insentifStrukturalLama = insentifStrukturalLama;
    }

    public String getInsentifJabStrukturalLama() {
        return insentifJabStrukturalLama;
    }

    public void setInsentifJabStrukturalLama(String insentifJabStrukturalLama) {
        this.insentifJabStrukturalLama = insentifJabStrukturalLama;
    }

    public String getInsentifStrategisLama() {
        return insentifStrategisLama;
    }

    public void setInsentifStrategisLama(String insentifStrategisLama) {
        this.insentifStrategisLama = insentifStrategisLama;
    }

    public String getInsentifPeralihanLama() {
        return insentifPeralihanLama;
    }

    public void setInsentifPeralihanLama(String insentifPeralihanLama) {
        this.insentifPeralihanLama = insentifPeralihanLama;
    }

    public String getBrutoInsentif() {
        return brutoInsentif;
    }

    public void setBrutoInsentif(String brutoInsentif) {
        this.brutoInsentif = brutoInsentif;
    }

    public BigDecimal getBrutoInsentifNilai() {
        return brutoInsentifNilai;
    }

    public void setBrutoInsentifNilai(BigDecimal brutoInsentifNilai) {
        this.brutoInsentifNilai = brutoInsentifNilai;
    }

    public int getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(int masaKerja) {
        this.masaKerja = masaKerja;
    }

    public BigDecimal getPotonganInsentifNilai() {
        return potonganInsentifNilai;
    }

    public void setPotonganInsentifNilai(BigDecimal potonganInsentifNilai) {
        this.potonganInsentifNilai = potonganInsentifNilai;
    }

    public String getPotonganInsentif() {
        return potonganInsentif;
    }

    public void setPotonganInsentif(String potonganInsentif) {
        this.potonganInsentif = potonganInsentif;
    }

    public BigDecimal getPotonganInsentifIndividuNilai() {
        return potonganInsentifIndividuNilai;
    }

    public void setPotonganInsentifIndividuNilai(BigDecimal potonganInsentifIndividuNilai) {
        this.potonganInsentifIndividuNilai = potonganInsentifIndividuNilai;
    }

    public String getPotonganInsentifIndividu() {
        return potonganInsentifIndividu;
    }

    public void setPotonganInsentifIndividu(String potonganInsentifIndividu) {
        this.potonganInsentifIndividu = potonganInsentifIndividu;
    }

    public BigDecimal getInsentifYangDiterimaLamaNilai() {
        return insentifYangDiterimaLamaNilai;
    }

    public void setInsentifYangDiterimaLamaNilai(BigDecimal insentifYangDiterimaLamaNilai) {
        this.insentifYangDiterimaLamaNilai = insentifYangDiterimaLamaNilai;
    }

    public BigDecimal getInsentifYangDiterimaBaruNilai() {
        return insentifYangDiterimaBaruNilai;
    }

    public void setInsentifYangDiterimaBaruNilai(BigDecimal insentifYangDiterimaBaruNilai) {
        this.insentifYangDiterimaBaruNilai = insentifYangDiterimaBaruNilai;
    }

    public String getInsentifYangDiterimaBaru() {
        return insentifYangDiterimaBaru;
    }

    public void setInsentifYangDiterimaBaru(String insentifYangDiterimaBaru) {
        this.insentifYangDiterimaBaru = insentifYangDiterimaBaru;
    }

    public BigDecimal getInsentifYangDiterimaSelisihNilai() {
        return insentifYangDiterimaSelisihNilai;
    }

    public void setInsentifYangDiterimaSelisihNilai(BigDecimal insentifYangDiterimaSelisihNilai) {
        this.insentifYangDiterimaSelisihNilai = insentifYangDiterimaSelisihNilai;
    }

    public String getInsentifYangDiterimaSelisih() {
        return insentifYangDiterimaSelisih;
    }

    public void setInsentifYangDiterimaSelisih(String insentifYangDiterimaSelisih) {
        this.insentifYangDiterimaSelisih = insentifYangDiterimaSelisih;
    }

    public String getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(String smkStandart) {
        this.smkStandart = smkStandart;
    }

    public String getSmkHuruf() {
        return smkHuruf;
    }

    public void setSmkHuruf(String smkHuruf) {
        this.smkHuruf = smkHuruf;
    }

    public String getSmkAngka() {
        return smkAngka;
    }

    public void setSmkAngka(String smkAngka) {
        this.smkAngka = smkAngka;
    }

    public BigDecimal getSmkAngkaNilai() {
        return smkAngkaNilai;
    }

    public void setSmkAngkaNilai(BigDecimal smkAngkaNilai) {
        this.smkAngkaNilai = smkAngkaNilai;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public BigDecimal getInsentifGajiGolonganNilaiLama() {
        return insentifGajiGolonganNilaiLama;
    }

    public void setInsentifGajiGolonganNilaiLama(BigDecimal insentifGajiGolonganNilaiLama) {
        this.insentifGajiGolonganNilaiLama = insentifGajiGolonganNilaiLama;
    }

    public BigDecimal getInsentifUmkNilaiLama() {
        return insentifUmkNilaiLama;
    }

    public void setInsentifUmkNilaiLama(BigDecimal insentifUmkNilaiLama) {
        this.insentifUmkNilaiLama = insentifUmkNilaiLama;
    }

    public BigDecimal getInsentifStrukturalNilaiLama() {
        return insentifStrukturalNilaiLama;
    }

    public void setInsentifStrukturalNilaiLama(BigDecimal insentifStrukturalNilaiLama) {
        this.insentifStrukturalNilaiLama = insentifStrukturalNilaiLama;
    }

    public BigDecimal getInsentifJabStrukturalNilaiLama() {
        return insentifJabStrukturalNilaiLama;
    }

    public void setInsentifJabStrukturalNilaiLama(BigDecimal insentifJabStrukturalNilaiLama) {
        this.insentifJabStrukturalNilaiLama = insentifJabStrukturalNilaiLama;
    }

    public BigDecimal getInsentifStrategisNilaiLama() {
        return insentifStrategisNilaiLama;
    }

    public void setInsentifStrategisNilaiLama(BigDecimal insentifStrategisNilaiLama) {
        this.insentifStrategisNilaiLama = insentifStrategisNilaiLama;
    }

    public BigDecimal getInsentifPeralihanNilaiLama() {
        return insentifPeralihanNilaiLama;
    }

    public void setInsentifPeralihanNilaiLama(BigDecimal insentifPeralihanNilaiLama) {
        this.insentifPeralihanNilaiLama = insentifPeralihanNilaiLama;
    }
}