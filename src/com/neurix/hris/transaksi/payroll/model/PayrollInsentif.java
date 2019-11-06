package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollInsentif extends BaseModel {
    private String insentifId;
    private String nip;
    private String tahun;
    private String nama;
    private String branchId;
    private String branchName;
    private BigDecimal jumlahInsentif ;
    private BigDecimal jumlahPph ;

    private String strJumlahInsentif ;
    private String strJumlahPph ;

    private String flagInsentif;

    private String gajiGolongan;
    private String tunjanganUmk;
    private String tunjanganStruktural;
    private String tunjanganPeralihan;
    private String tunjanganJabatanStruktural;
    private String tunjanganStrategis;
    private String jumlahBruto;

    private BigDecimal gajiGolonganNilai;
    private BigDecimal tunjanganUmkNilai;
    private BigDecimal tunjanganStrukturalNilai;
    private BigDecimal tunjanganPeralihanNilai;
    private BigDecimal tunjanganJabatanStrukturalNilai;
    private BigDecimal tunjanganStrategisNilai;
    private BigDecimal jumlahBrutoNilai;

    private Integer masaKerja;
    private double smkStandart;
    private String smkHuruf;
    private double smkAngka;
    private BigDecimal potonganinsentifNilai;
    private BigDecimal potonganinsentifIndividuNilai;
    private BigDecimal insentifyangDiterimaNilai;

    private String potonganinsentif;
    private String potonganinsentifIndividu;
    private String insentifyangDiterima;

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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getGajiGolongan() {
        return gajiGolongan;
    }

    public void setGajiGolongan(String gajiGolongan) {
        this.gajiGolongan = gajiGolongan;
    }

    public String getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(String tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }

    public String getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(String tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public String getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(String tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public String getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(String tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public String getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(String tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public String getJumlahBruto() {
        return jumlahBruto;
    }

    public void setJumlahBruto(String jumlahBruto) {
        this.jumlahBruto = jumlahBruto;
    }

    public BigDecimal getGajiGolonganNilai() {
        return gajiGolonganNilai;
    }

    public void setGajiGolonganNilai(BigDecimal gajiGolonganNilai) {
        this.gajiGolonganNilai = gajiGolonganNilai;
    }

    public BigDecimal getTunjanganUmkNilai() {
        return tunjanganUmkNilai;
    }

    public void setTunjanganUmkNilai(BigDecimal tunjanganUmkNilai) {
        this.tunjanganUmkNilai = tunjanganUmkNilai;
    }

    public BigDecimal getTunjanganStrukturalNilai() {
        return tunjanganStrukturalNilai;
    }

    public void setTunjanganStrukturalNilai(BigDecimal tunjanganStrukturalNilai) {
        this.tunjanganStrukturalNilai = tunjanganStrukturalNilai;
    }

    public BigDecimal getTunjanganPeralihanNilai() {
        return tunjanganPeralihanNilai;
    }

    public void setTunjanganPeralihanNilai(BigDecimal tunjanganPeralihanNilai) {
        this.tunjanganPeralihanNilai = tunjanganPeralihanNilai;
    }

    public BigDecimal getTunjanganJabatanStrukturalNilai() {
        return tunjanganJabatanStrukturalNilai;
    }

    public void setTunjanganJabatanStrukturalNilai(BigDecimal tunjanganJabatanStrukturalNilai) {
        this.tunjanganJabatanStrukturalNilai = tunjanganJabatanStrukturalNilai;
    }

    public BigDecimal getTunjanganStrategisNilai() {
        return tunjanganStrategisNilai;
    }

    public void setTunjanganStrategisNilai(BigDecimal tunjanganStrategisNilai) {
        this.tunjanganStrategisNilai = tunjanganStrategisNilai;
    }

    public BigDecimal getJumlahBrutoNilai() {
        return jumlahBrutoNilai;
    }

    public void setJumlahBrutoNilai(BigDecimal jumlahBrutoNilai) {
        this.jumlahBrutoNilai = jumlahBrutoNilai;
    }

    public Integer getMasaKerja() {
        return masaKerja;
    }

    public void setMasaKerja(Integer masaKerja) {
        this.masaKerja = masaKerja;
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

    public BigDecimal getPotonganinsentifNilai() {
        return potonganinsentifNilai;
    }

    public void setPotonganinsentifNilai(BigDecimal potonganinsentifNilai) {
        this.potonganinsentifNilai = potonganinsentifNilai;
    }

    public BigDecimal getPotonganinsentifIndividuNilai() {
        return potonganinsentifIndividuNilai;
    }

    public void setPotonganinsentifIndividuNilai(BigDecimal potonganinsentifIndividuNilai) {
        this.potonganinsentifIndividuNilai = potonganinsentifIndividuNilai;
    }

    public BigDecimal getInsentifyangDiterimaNilai() {
        return insentifyangDiterimaNilai;
    }

    public void setInsentifyangDiterimaNilai(BigDecimal insentifyangDiterimaNilai) {
        this.insentifyangDiterimaNilai = insentifyangDiterimaNilai;
    }

    public String getPotonganinsentif() {
        return potonganinsentif;
    }

    public void setPotonganinsentif(String potonganinsentif) {
        this.potonganinsentif = potonganinsentif;
    }

    public String getPotonganinsentifIndividu() {
        return potonganinsentifIndividu;
    }

    public void setPotonganinsentifIndividu(String potonganinsentifIndividu) {
        this.potonganinsentifIndividu = potonganinsentifIndividu;
    }

    public String getInsentifyangDiterima() {
        return insentifyangDiterima;
    }

    public void setInsentifyangDiterima(String insentifyangDiterima) {
        this.insentifyangDiterima = insentifyangDiterima;
    }

    public String getFlagInsentif() {
        return flagInsentif;
    }

    public void setFlagInsentif(String flagInsentif) {
        this.flagInsentif = flagInsentif;
    }


    public String getStrJumlahInsentif() {
        return strJumlahInsentif;
    }

    public void setStrJumlahInsentif(String strJumlahInsentif) {
        this.strJumlahInsentif = strJumlahInsentif;
    }

    public String getStrJumlahPph() {
        return strJumlahPph;
    }

    public void setStrJumlahPph(String strJumlahPph) {
        this.strJumlahPph = strJumlahPph;
    }

    public String getInsentifId() {
        return insentifId;
    }

    public void setInsentifId(String insentifId) {
        this.insentifId = insentifId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}