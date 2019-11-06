package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollRapelLemburEntity implements Serializable {
    private String absensiPegawaiId;
    private String rapelId;
    private String rapelLemburId;

    private String nip;
    private Date Tanggal;
    private String strTanggal;

    private BigDecimal gajiGolonganLama;
    private BigDecimal tunjanganUmkLama;
    private BigDecimal tunjanganPeralihanLama;

    private String jenisLembur;
    private double lamaLembur;
    private double jamLembur;
    private BigDecimal biayaLemburLama;
    private String tipePegawai;
    private double faktorPengali;

    // baru
    private BigDecimal gajiGolonganBaru;
    private BigDecimal tunjanganUmkBaru;
    private BigDecimal tunjanganPeralihanBaru;
    private BigDecimal biayaLemburBaru;
    private BigDecimal selisihBiayaLemburBaru;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getRapelId() {
        return rapelId;
    }

    public void setRapelId(String rapelId) {
        this.rapelId = rapelId;
    }

    public String getAbsensiPegawaiId() {
        return absensiPegawaiId;
    }

    public void setAbsensiPegawaiId(String absensiPegawaiId) {
        this.absensiPegawaiId = absensiPegawaiId;
    }

    public String getRapelLemburId() {
        return rapelLemburId;
    }

    public void setRapelLemburId(String rapelLemburId) {
        this.rapelLemburId = rapelLemburId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date tanggal) {
        Tanggal = tanggal;
    }

    public String getStrTanggal() {
        return strTanggal;
    }

    public void setStrTanggal(String strTanggal) {
        this.strTanggal = strTanggal;
    }

    public BigDecimal getGajiGolonganLama() {
        return gajiGolonganLama;
    }

    public void setGajiGolonganLama(BigDecimal gajiGolonganLama) {
        this.gajiGolonganLama = gajiGolonganLama;
    }

    public BigDecimal getTunjanganUmkLama() {
        return tunjanganUmkLama;
    }

    public void setTunjanganUmkLama(BigDecimal tunjanganUmkLama) {
        this.tunjanganUmkLama = tunjanganUmkLama;
    }

    public BigDecimal getTunjanganPeralihanLama() {
        return tunjanganPeralihanLama;
    }

    public void setTunjanganPeralihanLama(BigDecimal tunjanganPeralihanLama) {
        this.tunjanganPeralihanLama = tunjanganPeralihanLama;
    }

    public String getJenisLembur() {
        return jenisLembur;
    }

    public void setJenisLembur(String jenisLembur) {
        this.jenisLembur = jenisLembur;
    }

    public double getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(double lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public double getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(double jamLembur) {
        this.jamLembur = jamLembur;
    }

    public BigDecimal getBiayaLemburLama() {
        return biayaLemburLama;
    }

    public void setBiayaLemburLama(BigDecimal biayaLemburLama) {
        this.biayaLemburLama = biayaLemburLama;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public double getFaktorPengali() {
        return faktorPengali;
    }

    public void setFaktorPengali(double faktorPengali) {
        this.faktorPengali = faktorPengali;
    }

    public BigDecimal getGajiGolonganBaru() {
        return gajiGolonganBaru;
    }

    public void setGajiGolonganBaru(BigDecimal gajiGolonganBaru) {
        this.gajiGolonganBaru = gajiGolonganBaru;
    }

    public BigDecimal getTunjanganUmkBaru() {
        return tunjanganUmkBaru;
    }

    public void setTunjanganUmkBaru(BigDecimal tunjanganUmkBaru) {
        this.tunjanganUmkBaru = tunjanganUmkBaru;
    }

    public BigDecimal getTunjanganPeralihanBaru() {
        return tunjanganPeralihanBaru;
    }

    public void setTunjanganPeralihanBaru(BigDecimal tunjanganPeralihanBaru) {
        this.tunjanganPeralihanBaru = tunjanganPeralihanBaru;
    }

    public BigDecimal getBiayaLemburBaru() {
        return biayaLemburBaru;
    }

    public void setBiayaLemburBaru(BigDecimal biayaLemburBaru) {
        this.biayaLemburBaru = biayaLemburBaru;
    }

    public BigDecimal getSelisihBiayaLemburBaru() {
        return selisihBiayaLemburBaru;
    }

    public void setSelisihBiayaLemburBaru(BigDecimal selisihBiayaLemburBaru) {
        this.selisihBiayaLemburBaru = selisihBiayaLemburBaru;
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