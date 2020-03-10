package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class LaporanAkuntansi extends BaseModel {
    private String laporanAkuntansiId;
    private String url;
    private String laporanAkuntansiName;

    //UNTUK REPORT
    private String unit;
    private String bulan;
    private String tahun;
    private String tipeLaporan;
    private String nipGeneralManager;
    private String namaGeneralManager;
    private String nipManagerKeuangan;
    private String namaManagerKeuangan;
    private String masterId;
    private String kodeRekening;
    private String rekeningId;
    private String tipeJurnalId;
    private String tipeLaporanName;
    private String tipePerson;
    private String stTanggalAwal;
    private String stTanggalAkhir;
    private String tipeTanggal;

    private String levelKodeRekening;

    public String getStTanggalAwal() {
        return stTanggalAwal;
    }

    public void setStTanggalAwal(String stTanggalAwal) {
        this.stTanggalAwal = stTanggalAwal;
    }

    public String getStTanggalAkhir() {
        return stTanggalAkhir;
    }

    public void setStTanggalAkhir(String stTanggalAkhir) {
        this.stTanggalAkhir = stTanggalAkhir;
    }

    public String getTipeTanggal() {
        return tipeTanggal;
    }

    public void setTipeTanggal(String tipeTanggal) {
        this.tipeTanggal = tipeTanggal;
    }

    public String getLevelKodeRekening() {
        return levelKodeRekening;
    }

    public void setLevelKodeRekening(String levelKodeRekening) {
        this.levelKodeRekening = levelKodeRekening;
    }

    public String getTipePerson() {
        return tipePerson;
    }

    public void setTipePerson(String tipePerson) {
        this.tipePerson = tipePerson;
    }

    public String getTipeLaporanName() {
        return tipeLaporanName;
    }

    public void setTipeLaporanName(String tipeLaporanName) {
        this.tipeLaporanName = tipeLaporanName;
    }

    public String getTipeJurnalId() {
        return tipeJurnalId;
    }

    public void setTipeJurnalId(String tipeJurnalId) {
        this.tipeJurnalId = tipeJurnalId;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getTipeLaporan() {
        return tipeLaporan;
    }

    public void setTipeLaporan(String tipeLaporan) {
        this.tipeLaporan = tipeLaporan;
    }

    public String getNipGeneralManager() {
        return nipGeneralManager;
    }

    public void setNipGeneralManager(String nipGeneralManager) {
        this.nipGeneralManager = nipGeneralManager;
    }

    public String getNamaGeneralManager() {
        return namaGeneralManager;
    }

    public void setNamaGeneralManager(String namaGeneralManager) {
        this.namaGeneralManager = namaGeneralManager;
    }

    public String getNipManagerKeuangan() {
        return nipManagerKeuangan;
    }

    public void setNipManagerKeuangan(String nipManagerKeuangan) {
        this.nipManagerKeuangan = nipManagerKeuangan;
    }

    public String getNamaManagerKeuangan() {
        return namaManagerKeuangan;
    }

    public void setNamaManagerKeuangan(String namaManagerKeuangan) {
        this.namaManagerKeuangan = namaManagerKeuangan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getLaporanAkuntansiId() {
        return laporanAkuntansiId;
    }

    public void setLaporanAkuntansiId(String laporanAkuntansiId) {
        this.laporanAkuntansiId = laporanAkuntansiId;
    }

    public String getLaporanAkuntansiName() {
        return laporanAkuntansiName;
    }

    public void setLaporanAkuntansiName(String laporanAkuntansiName) {
        this.laporanAkuntansiName = laporanAkuntansiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}