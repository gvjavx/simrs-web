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

public class PayrollRapelLembur extends BaseModel {
    private String absensiPegawaiId;
    private String rapelLemburId;

    private String payrollId;
    private String nama;
    private String golonganName;
    private String statusKeluarga;
    private int jumlahAnak;
    private int point;
    private String nip;
    private String rapelId;
    private Date Tanggal;
    private String strTanggal;

    private BigDecimal gajiGolonganLama;
    private BigDecimal tunjanganUmkLama;
    private BigDecimal tunjanganPeralihanLama;
    private String strGajiGolonganLama;
    private String strTunjanganUmkLama;
    private String strTunjanganPeralihanLama;

    private String jenisLembur;
    private double lamaLembur;
    private double jamLembur;
    private BigDecimal biayaLemburLama;
    private String strBiayaLemburLama;
    private String tipePegawai;
    private double faktorPengali;

    // baru
    private BigDecimal gajiGolonganBaru;
    private BigDecimal tunjanganUmkBaru;
    private BigDecimal tunjanganPeralihanBaru;
    private BigDecimal biayaLemburBaru;
    private BigDecimal selisihBiayaLemburBaru;

    private String strGajiGolonganBaru;
    private String strTunjanganUmkBaru;
    private String strTunjanganPeralihanBaru;
    private String strBiayaLemburBaru;
    private String strSelisihBiayaLemburBaru;

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
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

    public String getStrBiayaLemburLama() {
        return strBiayaLemburLama;
    }

    public void setStrBiayaLemburLama(String strBiayaLemburLama) {
        this.strBiayaLemburLama = strBiayaLemburLama;
    }

    public String getStrGajiGolonganLama() {
        return strGajiGolonganLama;
    }

    public void setStrGajiGolonganLama(String strGajiGolonganLama) {
        this.strGajiGolonganLama = strGajiGolonganLama;
    }

    public String getStrTunjanganUmkLama() {
        return strTunjanganUmkLama;
    }

    public void setStrTunjanganUmkLama(String strTunjanganUmkLama) {
        this.strTunjanganUmkLama = strTunjanganUmkLama;
    }

    public String getStrTunjanganPeralihanLama() {
        return strTunjanganPeralihanLama;
    }

    public void setStrTunjanganPeralihanLama(String strTunjanganPeralihanLama) {
        this.strTunjanganPeralihanLama = strTunjanganPeralihanLama;
    }

    public String getStrGajiGolonganBaru() {
        return strGajiGolonganBaru;
    }

    public void setStrGajiGolonganBaru(String strGajiGolonganBaru) {
        this.strGajiGolonganBaru = strGajiGolonganBaru;
    }

    public String getStrTunjanganUmkBaru() {
        return strTunjanganUmkBaru;
    }

    public void setStrTunjanganUmkBaru(String strTunjanganUmkBaru) {
        this.strTunjanganUmkBaru = strTunjanganUmkBaru;
    }

    public String getStrTunjanganPeralihanBaru() {
        return strTunjanganPeralihanBaru;
    }

    public void setStrTunjanganPeralihanBaru(String strTunjanganPeralihanBaru) {
        this.strTunjanganPeralihanBaru = strTunjanganPeralihanBaru;
    }

    public String getStrBiayaLemburBaru() {
        return strBiayaLemburBaru;
    }

    public void setStrBiayaLemburBaru(String strBiayaLemburBaru) {
        this.strBiayaLemburBaru = strBiayaLemburBaru;
    }

    public String getStrSelisihBiayaLemburBaru() {
        return strSelisihBiayaLemburBaru;
    }

    public void setStrSelisihBiayaLemburBaru(String strSelisihBiayaLemburBaru) {
        this.strSelisihBiayaLemburBaru = strSelisihBiayaLemburBaru;
    }

    public BigDecimal getSelisihBiayaLemburBaru() {
        return selisihBiayaLemburBaru;
    }

    public void setSelisihBiayaLemburBaru(BigDecimal selisihBiayaLemburBaru) {
        this.selisihBiayaLemburBaru = selisihBiayaLemburBaru;
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
}