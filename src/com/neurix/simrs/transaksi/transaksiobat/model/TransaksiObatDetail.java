package com.neurix.simrs.transaksi.transaksiobat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;

public class TransaksiObatDetail extends BaseModel {

    private String idTransaksiObatDetail;
    private String idApprovalObat;
    private String idObat;
    private BigInteger qty;
    private String keterangan;

    private String idPermintaanResep;
    private String branchId;

    private String namaObat;
    private String namaPoli;
    private String namaPasien;

    private BigInteger harga;
    private BigInteger totalHarga;

    private BigInteger totalBayar;
    private BigInteger totalDibayar;
    private BigInteger nominal;
    private BigInteger kembalian;

    public BigInteger getNominal() {
        return nominal;
    }

    public void setNominal(BigInteger nominal) {
        this.nominal = nominal;
    }

    public BigInteger getKembalian() {
        return kembalian;
    }

    public void setKembalian(BigInteger kembalian) {
        this.kembalian = kembalian;
    }

    public BigInteger getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(BigInteger totalBayar) {
        this.totalBayar = totalBayar;
    }

    public BigInteger getTotalDibayar() {
        return totalDibayar;
    }

    public void setTotalDibayar(BigInteger totalDibayar) {
        this.totalDibayar = totalDibayar;
    }

    public String getIdPermintaanResep() {
        return idPermintaanResep;
    }

    public void setIdPermintaanResep(String idPermintaanResep) {
        this.idPermintaanResep = idPermintaanResep;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getNamaPoli() {
        return namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    public BigInteger getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(BigInteger totalHarga) {
        this.totalHarga = totalHarga;
    }
}