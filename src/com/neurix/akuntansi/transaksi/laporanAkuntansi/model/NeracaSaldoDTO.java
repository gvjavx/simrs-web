package com.neurix.akuntansi.transaksi.laporanAkuntansi.model;

import java.math.BigDecimal;

public class NeracaSaldoDTO {
    private String grup;
    private String namarekening;
    private String koderekening;
    private BigDecimal lastdebit;
    private BigDecimal lastkredit;
    private BigDecimal lastsaldo;
    private String lastposisi;
    private BigDecimal jumlahdebit;
    private BigDecimal jumlahkredit;
    private BigDecimal currdebit;
    private BigDecimal currkredit;
    private BigDecimal currsaldo;
    private String currposisi;

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getNamarekening() {
        return namarekening;
    }

    public void setNamarekening(String namarekening) {
        this.namarekening = namarekening;
    }

    public String getKoderekening() {
        return koderekening;
    }

    public void setKoderekening(String koderekening) {
        this.koderekening = koderekening;
    }

    public BigDecimal getLastdebit() {
        return lastdebit;
    }

    public void setLastdebit(BigDecimal lastdebit) {
        this.lastdebit = lastdebit;
    }

    public BigDecimal getLastkredit() {
        return lastkredit;
    }

    public void setLastkredit(BigDecimal lastkredit) {
        this.lastkredit = lastkredit;
    }

    public BigDecimal getLastsaldo() {
        return lastsaldo;
    }

    public void setLastsaldo(BigDecimal lastsaldo) {
        this.lastsaldo = lastsaldo;
    }

    public String getLastposisi() {
        return lastposisi;
    }

    public void setLastposisi(String lastposisi) {
        this.lastposisi = lastposisi;
    }

    public BigDecimal getJumlahdebit() {
        return jumlahdebit;
    }

    public void setJumlahdebit(BigDecimal jumlahdebit) {
        this.jumlahdebit = jumlahdebit;
    }

    public BigDecimal getJumlahkredit() {
        return jumlahkredit;
    }

    public void setJumlahkredit(BigDecimal jumlahkredit) {
        this.jumlahkredit = jumlahkredit;
    }

    public BigDecimal getCurrdebit() {
        return currdebit;
    }

    public void setCurrdebit(BigDecimal currdebit) {
        this.currdebit = currdebit;
    }

    public BigDecimal getCurrkredit() {
        return currkredit;
    }

    public void setCurrkredit(BigDecimal currkredit) {
        this.currkredit = currkredit;
    }

    public BigDecimal getCurrsaldo() {
        return currsaldo;
    }

    public void setCurrsaldo(BigDecimal currsaldo) {
        this.currsaldo = currsaldo;
    }

    public String getCurrposisi() {
        return currposisi;
    }

    public void setCurrposisi(String currposisi) {
        this.currposisi = currposisi;
    }
}
