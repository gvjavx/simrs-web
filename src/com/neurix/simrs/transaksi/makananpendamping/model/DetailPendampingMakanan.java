package com.neurix.simrs.transaksi.makananpendamping.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class DetailPendampingMakanan {
    private String idDetailPendampingMakanan;
    private String idHeaderPendampingMakanan;
    private String nama;
    private Integer qty;
    private BigDecimal tarif;
    private BigDecimal totalTarif;
    private String keterangan;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDetailPendampingMakanan() {
        return idDetailPendampingMakanan;
    }

    public void setIdDetailPendampingMakanan(String idDetailPendampingMakanan) {
        this.idDetailPendampingMakanan = idDetailPendampingMakanan;
    }

    public String getIdHeaderPendampingMakanan() {
        return idHeaderPendampingMakanan;
    }

    public void setIdHeaderPendampingMakanan(String idHeaderPendampingMakanan) {
        this.idHeaderPendampingMakanan = idHeaderPendampingMakanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public BigDecimal getTotalTarif() {
        return totalTarif;
    }

    public void setTotalTarif(BigDecimal totalTarif) {
        this.totalTarif = totalTarif;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
}
