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

public class ItPayrollInsentifEntity implements Serializable {
    private String insentifId;
    private String nip;
    private String payrollId;
    private String nama;
    private String branchId;
    private String tahun;
    private String branchName;
    private BigDecimal jumlahInsentif ;
    private BigDecimal jumlahPph ;

    private BigDecimal gajiGolongan;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganPeralihan;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganStrategis;
    private BigDecimal jumlahBruto;
    private BigDecimal potPph;
    private Integer masaKerja;
    private BigDecimal potonganinsentif;
    private BigDecimal potonganinsentifIndividu;
    private double smkStandart;
    private String smkHuruf;
    private double smkAngka;
    private BigDecimal insentifyangDiterima;
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

    public BigDecimal getPotPph() {
        return potPph;
    }

    public void setPotPph(BigDecimal potPph) {
        this.potPph = potPph;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(BigDecimal gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(BigDecimal tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getJumlahBruto() {
        return jumlahBruto;
    }

    public void setJumlahBruto(BigDecimal jumlahBruto) {
        this.jumlahBruto = jumlahBruto;
    }

    public Integer getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(Integer masaKerja) {
        this.masaKerja = masaKerja;
    }

    public BigDecimal getPotonganinsentif() {
        return potonganinsentif;
    }

    public void setPotonganinsentif(BigDecimal potonganinsentif) {
        this.potonganinsentif = potonganinsentif;
    }

    public BigDecimal getPotonganinsentifIndividu() {
        return potonganinsentifIndividu;
    }

    public void setPotonganinsentifIndividu(BigDecimal potonganinsentifIndividu) {
        this.potonganinsentifIndividu = potonganinsentifIndividu;
    }

    public double getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(double smkStandart) {
        this.smkStandart = smkStandart;
    }

    public String getSmkHuruf() {
        return smkHuruf;
    }

    public void setSmkHuruf(String smkHuruf) {
        this.smkHuruf = smkHuruf;
    }

    public double getSmkAngka() {
        return smkAngka;
    }

    public void setSmkAngka(double smkAngka) {
        this.smkAngka = smkAngka;
    }

    public BigDecimal getInsentifyangDiterima() {
        return insentifyangDiterima;
    }

    public void setInsentifyangDiterima(BigDecimal insentifyangDiterima) {
        this.insentifyangDiterima = insentifyangDiterima;
    }

    public String getNip() {
        return nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getInsentifId() {
        return insentifId;
    }

    public void setInsentifId(String insentifId) {
        this.insentifId = insentifId;
    }

    public BigDecimal getJumlahInsentif() {
        return jumlahInsentif;
    }

    public void setJumlahInsentif(BigDecimal jumlahInsentif) {
        this.jumlahInsentif = jumlahInsentif;
    }

    public BigDecimal getJumlahPph() {
        return jumlahPph;
    }

    public void setJumlahPph(BigDecimal jumlahPph) {
        this.jumlahPph = jumlahPph;
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