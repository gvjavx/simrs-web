package com.neurix.simrs.transaksi.hargaobat.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 23/01/20.
 */
public class HargaObat {

    private String idHargaObat;
    private String idObat;
    private String namaObat;
    private BigDecimal hargaBeli;
    private BigDecimal hargaJual;
    private BigDecimal hargaRata;
    private BigDecimal diskon;
    private BigDecimal hargaNet;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String satuan;
    private String idBarang;
    private Integer margin;
    private String branchId;

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdHargaObat() {
        return idHargaObat;
    }

    public void setIdHargaObat(String idHargaObat) {
        this.idHargaObat = idHargaObat;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public BigDecimal getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(BigDecimal hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public BigDecimal getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(BigDecimal hargaJual) {
        this.hargaJual = hargaJual;
    }

    public BigDecimal getHargaRata() {
        return hargaRata;
    }

    public void setHargaRata(BigDecimal hargaRata) {
        this.hargaRata = hargaRata;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public BigDecimal getHargaNet() {
        return hargaNet;
    }

    public void setHargaNet(BigDecimal hargaNet) {
        this.hargaNet = hargaNet;
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

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
