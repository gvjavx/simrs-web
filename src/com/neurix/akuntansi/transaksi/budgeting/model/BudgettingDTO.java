package com.neurix.akuntansi.transaksi.budgeting.model;

import java.math.BigDecimal;

public class BudgettingDTO {
    private String grup;
    private String kodeRekening;
    private String kodeRekeningName;
    private BigDecimal qty;
    private BigDecimal nilai;
    private BigDecimal subTotal;
    private BigDecimal nilaiTotal;
    private String noBudgetting;
    private String tipe;
    private String divisi;
    private String divisiId;
    private String tahun;

    private BigDecimal qtySem2;
    private BigDecimal nilaiSem2;
    private BigDecimal subTotalSem2;

    private BigDecimal subTotalQ1;
    private BigDecimal subTotalQ2;
    private BigDecimal subTotalQ3;
    private BigDecimal subTotalQ4;

    private BigDecimal subTotalJanuari;
    private BigDecimal subTotalFebruari;
    private BigDecimal subTotalMaret;
    private BigDecimal subTotalApril;
    private BigDecimal subTotalMei;
    private BigDecimal subTotalJuni;
    private BigDecimal subTotalJuli;
    private BigDecimal subTotalAgustus;
    private BigDecimal subTotalSeptember;
    private BigDecimal subTotalOktober;
    private BigDecimal subTotalNovember;
    private BigDecimal subTotalDesember;

    private BigDecimal nilaiTotalTahunLalu;
    private BigDecimal nilaiTotal2TahunLalu;
    private BigDecimal nilaiTotalRealisasi;
    private BigDecimal nilaiTotalSisaBayar;

    private BigDecimal nilaiAdendum1;
    private BigDecimal nilaiAdendum2;
    private BigDecimal nilaiAdendum3;

    private boolean cetak=false;

    public boolean isCetak() {
        return cetak;
    }

    public void setCetak(boolean cetak) {
        this.cetak = cetak;
    }

    public BigDecimal getNilaiAdendum1() {
        return nilaiAdendum1;
    }

    public void setNilaiAdendum1(BigDecimal nilaiAdendum1) {
        this.nilaiAdendum1 = nilaiAdendum1;
    }

    public BigDecimal getNilaiAdendum2() {
        return nilaiAdendum2;
    }

    public void setNilaiAdendum2(BigDecimal nilaiAdendum2) {
        this.nilaiAdendum2 = nilaiAdendum2;
    }

    public BigDecimal getNilaiAdendum3() {
        return nilaiAdendum3;
    }

    public void setNilaiAdendum3(BigDecimal nilaiAdendum3) {
        this.nilaiAdendum3 = nilaiAdendum3;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public BigDecimal getNilaiTotalSisaBayar() {
        return nilaiTotalSisaBayar;
    }

    public void setNilaiTotalSisaBayar(BigDecimal nilaiTotalSisaBayar) {
        this.nilaiTotalSisaBayar = nilaiTotalSisaBayar;
    }

    public BigDecimal getNilaiTotalRealisasi() {
        return nilaiTotalRealisasi;
    }

    public void setNilaiTotalRealisasi(BigDecimal nilaiTotalRealisasi) {
        this.nilaiTotalRealisasi = nilaiTotalRealisasi;
    }

    public BigDecimal getNilaiTotalTahunLalu() {
        return nilaiTotalTahunLalu;
    }

    public void setNilaiTotalTahunLalu(BigDecimal nilaiTotalTahunLalu) {
        this.nilaiTotalTahunLalu = nilaiTotalTahunLalu;
    }

    public BigDecimal getNilaiTotal2TahunLalu() {
        return nilaiTotal2TahunLalu;
    }

    public void setNilaiTotal2TahunLalu(BigDecimal nilaiTotal2TahunLalu) {
        this.nilaiTotal2TahunLalu = nilaiTotal2TahunLalu;
    }

    public BigDecimal getSubTotalJanuari() {
        return subTotalJanuari;
    }

    public void setSubTotalJanuari(BigDecimal subTotalJanuari) {
        this.subTotalJanuari = subTotalJanuari;
    }

    public BigDecimal getSubTotalFebruari() {
        return subTotalFebruari;
    }

    public void setSubTotalFebruari(BigDecimal subTotalFebruari) {
        this.subTotalFebruari = subTotalFebruari;
    }

    public BigDecimal getSubTotalMaret() {
        return subTotalMaret;
    }

    public void setSubTotalMaret(BigDecimal subTotalMaret) {
        this.subTotalMaret = subTotalMaret;
    }

    public BigDecimal getSubTotalApril() {
        return subTotalApril;
    }

    public void setSubTotalApril(BigDecimal subTotalApril) {
        this.subTotalApril = subTotalApril;
    }

    public BigDecimal getSubTotalMei() {
        return subTotalMei;
    }

    public void setSubTotalMei(BigDecimal subTotalMei) {
        this.subTotalMei = subTotalMei;
    }

    public BigDecimal getSubTotalJuni() {
        return subTotalJuni;
    }

    public void setSubTotalJuni(BigDecimal subTotalJuni) {
        this.subTotalJuni = subTotalJuni;
    }

    public BigDecimal getSubTotalJuli() {
        return subTotalJuli;
    }

    public void setSubTotalJuli(BigDecimal subTotalJuli) {
        this.subTotalJuli = subTotalJuli;
    }

    public BigDecimal getSubTotalAgustus() {
        return subTotalAgustus;
    }

    public void setSubTotalAgustus(BigDecimal subTotalAgustus) {
        this.subTotalAgustus = subTotalAgustus;
    }

    public BigDecimal getSubTotalSeptember() {
        return subTotalSeptember;
    }

    public void setSubTotalSeptember(BigDecimal subTotalSeptember) {
        this.subTotalSeptember = subTotalSeptember;
    }

    public BigDecimal getSubTotalOktober() {
        return subTotalOktober;
    }

    public void setSubTotalOktober(BigDecimal subTotalOktober) {
        this.subTotalOktober = subTotalOktober;
    }

    public BigDecimal getSubTotalNovember() {
        return subTotalNovember;
    }

    public void setSubTotalNovember(BigDecimal subTotalNovember) {
        this.subTotalNovember = subTotalNovember;
    }

    public BigDecimal getSubTotalDesember() {
        return subTotalDesember;
    }

    public void setSubTotalDesember(BigDecimal subTotalDesember) {
        this.subTotalDesember = subTotalDesember;
    }

    public BigDecimal getSubTotalQ1() {
        return subTotalQ1;
    }

    public void setSubTotalQ1(BigDecimal subTotalQ1) {
        this.subTotalQ1 = subTotalQ1;
    }

    public BigDecimal getSubTotalQ2() {
        return subTotalQ2;
    }

    public void setSubTotalQ2(BigDecimal subTotalQ2) {
        this.subTotalQ2 = subTotalQ2;
    }

    public BigDecimal getSubTotalQ3() {
        return subTotalQ3;
    }

    public void setSubTotalQ3(BigDecimal subTotalQ3) {
        this.subTotalQ3 = subTotalQ3;
    }

    public BigDecimal getSubTotalQ4() {
        return subTotalQ4;
    }

    public void setSubTotalQ4(BigDecimal subTotalQ4) {
        this.subTotalQ4 = subTotalQ4;
    }

    public BigDecimal getQtySem2() {
        return qtySem2;
    }

    public void setQtySem2(BigDecimal qtySem2) {
        this.qtySem2 = qtySem2;
    }

    public BigDecimal getNilaiSem2() {
        return nilaiSem2;
    }

    public void setNilaiSem2(BigDecimal nilaiSem2) {
        this.nilaiSem2 = nilaiSem2;
    }

    public BigDecimal getSubTotalSem2() {
        return subTotalSem2;
    }

    public void setSubTotalSem2(BigDecimal subTotalSem2) {
        this.subTotalSem2 = subTotalSem2;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public BigDecimal getNilaiTotal() {
        return nilaiTotal;
    }

    public void setNilaiTotal(BigDecimal nilaiTotal) {
        this.nilaiTotal = nilaiTotal;
    }

    public String getNoBudgetting() {
        return noBudgetting;
    }

    public void setNoBudgetting(String noBudgetting) {
        this.noBudgetting = noBudgetting;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getKodeRekeningName() {
        return kodeRekeningName;
    }

    public void setKodeRekeningName(String kodeRekeningName) {
        this.kodeRekeningName = kodeRekeningName;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
