package com.neurix.simrs.transaksi.transaksiobat.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
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

    private BigInteger qtyApprove;

    private BigInteger qtyBox;
    private BigInteger qtyLembar;
    private BigInteger qtyBiji;
    private BigInteger lembarPerBox;
    private BigInteger bijiPerLembar;
    private BigDecimal averageHargaBox;
    private BigDecimal averageHargaLembar;
    private BigDecimal averageHargaBiji;
    private String flagDiterima;
    private String jenisSatuan;

    private BigDecimal hargaTerakhir;

    private BigDecimal hargaPo;

    private String merek;
    private String idPabrik;

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public BigDecimal getHargaPo() {
        return hargaPo;
    }

    public void setHargaPo(BigDecimal hargaPo) {
        this.hargaPo = hargaPo;
    }

    public BigDecimal getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(BigDecimal hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public String getFlagDiterima() {
        return flagDiterima;
    }

    public void setFlagDiterima(String flagDiterima) {
        this.flagDiterima = flagDiterima;
    }

    public BigInteger getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(BigInteger qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

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

    public BigInteger getQtyBox() {
        return qtyBox;
    }

    public void setQtyBox(BigInteger qtyBox) {
        this.qtyBox = qtyBox;
    }

    public BigInteger getQtyLembar() {
        return qtyLembar;
    }

    public void setQtyLembar(BigInteger qtyLembar) {
        this.qtyLembar = qtyLembar;
    }

    public BigInteger getQtyBiji() {
        return qtyBiji;
    }

    public void setQtyBiji(BigInteger qtyBiji) {
        this.qtyBiji = qtyBiji;
    }

    public BigInteger getLembarPerBox() {
        return lembarPerBox;
    }

    public void setLembarPerBox(BigInteger lembarPerBox) {
        this.lembarPerBox = lembarPerBox;
    }

    public BigInteger getBijiPerLembar() {
        return bijiPerLembar;
    }

    public void setBijiPerLembar(BigInteger bijiPerLembar) {
        this.bijiPerLembar = bijiPerLembar;
    }

    public BigDecimal getAverageHargaBox() {
        return averageHargaBox;
    }

    public void setAverageHargaBox(BigDecimal averageHargaBox) {
        this.averageHargaBox = averageHargaBox;
    }

    public BigDecimal getAverageHargaLembar() {
        return averageHargaLembar;
    }

    public void setAverageHargaLembar(BigDecimal averageHargaLembar) {
        this.averageHargaLembar = averageHargaLembar;
    }

    public BigDecimal getAverageHargaBiji() {
        return averageHargaBiji;
    }

    public void setAverageHargaBiji(BigDecimal averageHargaBiji) {
        this.averageHargaBiji = averageHargaBiji;
    }
}