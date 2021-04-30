package com.neurix.simrs.transaksi.transaksiobat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

public class RiwayatTransaksiObat extends BaseModel {

    private String idRiwayatTransaksiObat;
    private String idApprovalObat;
    private String idObat;
    private String tipePermintaan;
    private BigInteger qty;
    private BigInteger totalHarga;

    public String getIdRiwayatTransaksiObat() {
        return idRiwayatTransaksiObat;
    }

    public void setIdRiwayatTransaksiObat(String idRiwayatTransaksiObat) {
        this.idRiwayatTransaksiObat = idRiwayatTransaksiObat;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getTipePermintaan() {
        return tipePermintaan;
    }

    public void setTipePermintaan(String tipePermintaan) {
        this.tipePermintaan = tipePermintaan;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigInteger getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(BigInteger totalHarga) {
        this.totalHarga = totalHarga;
    }
}