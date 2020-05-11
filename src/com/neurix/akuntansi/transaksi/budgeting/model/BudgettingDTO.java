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

    private BigDecimal qtySem2;
    private BigDecimal nilaiSem2;
    private BigDecimal subTotalSem2;

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
