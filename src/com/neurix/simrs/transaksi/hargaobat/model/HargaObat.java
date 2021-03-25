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

    private BigDecimal hargaTerakhir;
    private BigDecimal diskonUmum;
    private BigDecimal hargaJualUmum;
    private BigDecimal hargaNetUmum;
    private Integer marginUmum;
    private String flagIsBpjs;

    private BigDecimal diskonKhususBpjs;
    private BigDecimal hargaJualKhususBpjs;
    private BigDecimal hargaNetKhususBpjs;
    private Integer marginKhususBpjs;

    private BigDecimal diskonUmumBpjs;
    private BigDecimal hargaJualUmumBpjs;
    private BigDecimal hargaNetUmumBpjs;
    private Integer marginNetUmumBpjs;

    public BigDecimal getDiskonKhususBpjs() {
        return diskonKhususBpjs;
    }

    public void setDiskonKhususBpjs(BigDecimal diskonKhususBpjs) {
        this.diskonKhususBpjs = diskonKhususBpjs;
    }

    public BigDecimal getHargaJualKhususBpjs() {
        return hargaJualKhususBpjs;
    }

    public void setHargaJualKhususBpjs(BigDecimal hargaJualKhususBpjs) {
        this.hargaJualKhususBpjs = hargaJualKhususBpjs;
    }

    public BigDecimal getHargaNetKhususBpjs() {
        return hargaNetKhususBpjs;
    }

    public void setHargaNetKhususBpjs(BigDecimal hargaNetKhususBpjs) {
        this.hargaNetKhususBpjs = hargaNetKhususBpjs;
    }

    public BigDecimal getDiskonUmumBpjs() {
        return diskonUmumBpjs;
    }

    public void setDiskonUmumBpjs(BigDecimal diskonUmumBpjs) {
        this.diskonUmumBpjs = diskonUmumBpjs;
    }

    public BigDecimal getHargaJualUmumBpjs() {
        return hargaJualUmumBpjs;
    }

    public void setHargaJualUmumBpjs(BigDecimal hargaJualUmumBpjs) {
        this.hargaJualUmumBpjs = hargaJualUmumBpjs;
    }

    public BigDecimal getHargaNetUmumBpjs() {
        return hargaNetUmumBpjs;
    }

    public void setHargaNetUmumBpjs(BigDecimal hargaNetUmumBpjs) {
        this.hargaNetUmumBpjs = hargaNetUmumBpjs;
    }

    public Integer getMarginKhususBpjs() {
        return marginKhususBpjs;
    }

    public void setMarginKhususBpjs(Integer marginKhususBpjs) {
        this.marginKhususBpjs = marginKhususBpjs;
    }

    public Integer getMarginNetUmumBpjs() {
        return marginNetUmumBpjs;
    }

    public void setMarginNetUmumBpjs(Integer marginNetUmumBpjs) {
        this.marginNetUmumBpjs = marginNetUmumBpjs;
    }

    public String getFlagIsBpjs() {
        return flagIsBpjs;
    }

    public void setFlagIsBpjs(String flagIsBpjs) {
        this.flagIsBpjs = flagIsBpjs;
    }

    public Integer getMarginUmum() {
        return marginUmum;
    }

    public void setMarginUmum(Integer marginUmum) {
        this.marginUmum = marginUmum;
    }

    public BigDecimal getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(BigDecimal hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public BigDecimal getDiskonUmum() {
        return diskonUmum;
    }

    public void setDiskonUmum(BigDecimal diskonUmum) {
        this.diskonUmum = diskonUmum;
    }

    public BigDecimal getHargaJualUmum() {
        return hargaJualUmum;
    }

    public void setHargaJualUmum(BigDecimal hargaJualUmum) {
        this.hargaJualUmum = hargaJualUmum;
    }

    public BigDecimal getHargaNetUmum() {
        return hargaNetUmum;
    }

    public void setHargaNetUmum(BigDecimal hargaNetUmum) {
        this.hargaNetUmum = hargaNetUmum;
    }

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
