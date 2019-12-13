package com.neurix.simrs.transaksi.transaksiobat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

public class TransaksiObatDetail extends BaseModel {

    private String idTransaksiObatDetail;
    private String idApprovalObat;
    private String idObat;
    private BigInteger qty;
    private String keterangan;
    private String namaObat;

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdTransaksiObatDetail() {
        return idTransaksiObatDetail;
    }

    public void setIdTransaksiObatDetail(String idTransaksiObatDetail) {
        this.idTransaksiObatDetail = idTransaksiObatDetail;
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

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }
}