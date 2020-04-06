package com.neurix.hris.transaksi.payroll.model;

import java.math.BigDecimal;

public class Ptt {
    private String payrollPttId;
    private String tipePttId;
    private String tipePttName;
    private String nilai;
    private BigDecimal nilaiPtt;
    private String tahun;
    private String bulan;
    private String nip;
    private BigDecimal jumlahPtt;
    private String stJumlahPtt;

    public BigDecimal getJumlahPtt() {
        return jumlahPtt;
    }

    public void setJumlahPtt(BigDecimal jumlahPtt) {
        this.jumlahPtt = jumlahPtt;
    }

    public String getStJumlahPtt() {
        return stJumlahPtt;
    }

    public void setStJumlahPtt(String stJumlahPtt) {
        this.stJumlahPtt = stJumlahPtt;
    }

    public String getPayrollPttId() {
        return payrollPttId;
    }

    public void setPayrollPttId(String payrollPttId) {
        this.payrollPttId = payrollPttId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public String getTipePttId() {
        return tipePttId;
    }

    public void setTipePttId(String tipePttId) {
        this.tipePttId = tipePttId;
    }

    public String getTipePttName() {
        return tipePttName;
    }

    public void setTipePttName(String tipePttName) {
        this.tipePttName = tipePttName;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiPtt() {
        return nilaiPtt;
    }

    public void setNilaiPtt(BigDecimal nilaiPtt) {
        this.nilaiPtt = nilaiPtt;
    }
}
