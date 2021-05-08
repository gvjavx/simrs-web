package com.neurix.simrs.transaksi.permintaanresep.model;

import java.math.BigInteger;

public class ResepObat {

    private String Jenis;
    private String Obat;
    private BigInteger qty;
    private String Keterangan;

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public String getObat() {
        return Obat;
    }

    public void setObat(String obat) {
        Obat = obat;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }
}