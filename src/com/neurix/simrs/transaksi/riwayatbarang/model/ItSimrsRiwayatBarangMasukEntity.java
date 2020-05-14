package com.neurix.simrs.transaksi.riwayatbarang.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 14/05/20.
 */
public class ItSimrsRiwayatBarangMasukEntity implements Serializable {

    private String idBarangMasuk;
    private String idBarang;
    private BigInteger qty;
    private BigDecimal hargaTerakhir;
    private BigDecimal averageHarga;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String branchId;
    private String idVendor;
    private BigInteger stock;

    public String getIdBarangMasuk() {
        return idBarangMasuk;
    }

    public void setIdBarangMasuk(String idBarangMasuk) {
        this.idBarangMasuk = idBarangMasuk;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigDecimal getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(BigDecimal hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public BigDecimal getAverageHarga() {
        return averageHarga;
    }

    public void setAverageHarga(BigDecimal averageHarga) {
        this.averageHarga = averageHarga;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        this.stock = stock;
    }
}
