package com.neurix.simrs.transaksi.riwayatbarang.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by reza on 17/05/20.
 */
public class TransaksiStok {

    private String idTransaksi;
    private String idObat;
    private String keterangan;
    private String tipe;
    private String branchId;
    private BigInteger qty;
    private BigDecimal total;
    private BigDecimal subTotal;
    private Date registeredDate;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idVendor;
    private String idBarang;
    private String idPelayanan;
    private BigInteger qtyLalu;
    private BigDecimal totalLalu;
    private BigDecimal subTotalLalu;
    private BigInteger qtySaldo;
    private BigDecimal totalSaldo;
    private BigDecimal subTotalSaldo;
    private BigInteger qtyKredit;
    private BigDecimal totalKredit;
    private BigDecimal subTotalKredit;

    public BigInteger getQtyKredit() {
        return qtyKredit;
    }

    public void setQtyKredit(BigInteger qtyKredit) {
        this.qtyKredit = qtyKredit;
    }

    public BigDecimal getTotalKredit() {
        return totalKredit;
    }

    public void setTotalKredit(BigDecimal totalKredit) {
        this.totalKredit = totalKredit;
    }

    public BigDecimal getSubTotalKredit() {
        return subTotalKredit;
    }

    public void setSubTotalKredit(BigDecimal subTotalKredit) {
        this.subTotalKredit = subTotalKredit;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public BigInteger getQtyLalu() {
        return qtyLalu;
    }

    public void setQtyLalu(BigInteger qtyLalu) {
        this.qtyLalu = qtyLalu;
    }

    public BigDecimal getTotalLalu() {
        return totalLalu;
    }

    public void setTotalLalu(BigDecimal totalLalu) {
        this.totalLalu = totalLalu;
    }

    public BigDecimal getSubTotalLalu() {
        return subTotalLalu;
    }

    public void setSubTotalLalu(BigDecimal subTotalLalu) {
        this.subTotalLalu = subTotalLalu;
    }

    public BigInteger getQtySaldo() {
        return qtySaldo;
    }

    public void setQtySaldo(BigInteger qtySaldo) {
        this.qtySaldo = qtySaldo;
    }

    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(BigDecimal totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public BigDecimal getSubTotalSaldo() {
        return subTotalSaldo;
    }

    public void setSubTotalSaldo(BigDecimal subTotalSaldo) {
        this.subTotalSaldo = subTotalSaldo;
    }
}
