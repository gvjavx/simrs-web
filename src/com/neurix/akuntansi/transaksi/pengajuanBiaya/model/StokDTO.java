package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

public class StokDTO {
    private String idBarang;
    private String namaBarang;
    private String qty;
    private String totalSaldo;
    private String subTotalSaldo;

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(String totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public String getSubTotalSaldo() {
        return subTotalSaldo;
    }

    public void setSubTotalSaldo(String subTotalSaldo) {
        this.subTotalSaldo = subTotalSaldo;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
}

