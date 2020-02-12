package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Aging implements Serializable {
    private String kodeRekening;
    private String namaRekening;
    private String noNota;
    private Date tglJurnal;
    private String mataUang;
    private BigDecimal total;
    private BigDecimal jumlah1;
    private BigDecimal jumlah2;
    private BigDecimal jumlah3;
    private BigDecimal jumlah4;
    private BigDecimal jumlah5;
    private BigDecimal jumlah6;
    private BigDecimal jumlah7;
    private BigDecimal kurs;
    private String masterGrp;
    private String masterId;
    private String namaMaster;
    private String reportName;
    private String periode;

    private String strKurs;

    private String namaGeneralManager;
    private String nipGeneralManager;
    private String namaManagerKeuangan;
    private String nipManagerKeuangan;
    private String periodeTitle;
    private String kota;
    private String tanggal;

    private String grup;

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public Date getTglJurnal() {
        return tglJurnal;
    }

    public void setTglJurnal(Date tglJurnal) {
        this.tglJurnal = tglJurnal;
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getJumlah1() {
        return jumlah1;
    }

    public void setJumlah1(BigDecimal jumlah1) {
        this.jumlah1 = jumlah1;
    }

    public BigDecimal getJumlah2() {
        return jumlah2;
    }

    public void setJumlah2(BigDecimal jumlah2) {
        this.jumlah2 = jumlah2;
    }

    public BigDecimal getJumlah3() {
        return jumlah3;
    }

    public void setJumlah3(BigDecimal jumlah3) {
        this.jumlah3 = jumlah3;
    }

    public BigDecimal getJumlah4() {
        return jumlah4;
    }

    public void setJumlah4(BigDecimal jumlah4) {
        this.jumlah4 = jumlah4;
    }

    public BigDecimal getJumlah5() {
        return jumlah5;
    }

    public void setJumlah5(BigDecimal jumlah5) {
        this.jumlah5 = jumlah5;
    }

    public BigDecimal getJumlah6() {
        return jumlah6;
    }

    public void setJumlah6(BigDecimal jumlah6) {
        this.jumlah6 = jumlah6;
    }

    public BigDecimal getJumlah7() {
        return jumlah7;
    }

    public void setJumlah7(BigDecimal jumlah7) {
        this.jumlah7 = jumlah7;
    }

    public BigDecimal getKurs() {
        return kurs;
    }

    public void setKurs(BigDecimal kurs) {
        this.kurs = kurs;
    }

    public String getMasterGrp() {
        return masterGrp;
    }

    public void setMasterGrp(String masterGrp) {
        this.masterGrp = masterGrp;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNamaMaster() {
        return namaMaster;
    }

    public void setNamaMaster(String namaMaster) {
        this.namaMaster = namaMaster;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getStrKurs() {
        return strKurs;
    }

    public void setStrKurs(String strKurs) {
        this.strKurs = strKurs;
    }

    public String getNamaGeneralManager() {
        return namaGeneralManager;
    }

    public void setNamaGeneralManager(String namaGeneralManager) {
        this.namaGeneralManager = namaGeneralManager;
    }

    public String getNipGeneralManager() {
        return nipGeneralManager;
    }

    public void setNipGeneralManager(String nipGeneralManager) {
        this.nipGeneralManager = nipGeneralManager;
    }

    public String getNamaManagerKeuangan() {
        return namaManagerKeuangan;
    }

    public void setNamaManagerKeuangan(String namaManagerKeuangan) {
        this.namaManagerKeuangan = namaManagerKeuangan;
    }

    public String getNipManagerKeuangan() {
        return nipManagerKeuangan;
    }

    public void setNipManagerKeuangan(String nipManagerKeuangan) {
        this.nipManagerKeuangan = nipManagerKeuangan;
    }

    public String getPeriodeTitle() {
        return periodeTitle;
    }

    public void setPeriodeTitle(String periodeTitle) {
        this.periodeTitle = periodeTitle;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }
}
