package com.neurix.simrs.transaksi.hargaobat.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HargaObatPerKonsumen {
    private String id;
    private String idHargaObat;
    private String namaKonsumen;
    private String jenisKonsumen;
    private String idRekanan;
    private BigDecimal hargaBruto;
    private BigDecimal margin;
    private BigDecimal hargaJual;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String tipeRekanan;
    private String flagIsBpjs;
    private BigDecimal hargaTerakhir;
    private String idObat;
    private String branchId;

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getHargaTerakhir() {
        return hargaTerakhir;
    }

    public void setHargaTerakhir(BigDecimal hargaTerakhir) {
        this.hargaTerakhir = hargaTerakhir;
    }

    public String getTipeRekanan() {
        return tipeRekanan;
    }

    public void setTipeRekanan(String tipeRekanan) {
        this.tipeRekanan = tipeRekanan;
    }

    public String getFlagIsBpjs() {
        return flagIsBpjs;
    }

    public void setFlagIsBpjs(String flagIsBpjs) {
        this.flagIsBpjs = flagIsBpjs;
    }

    public String getNamaKonsumen() {
        return namaKonsumen;
    }

    public void setNamaKonsumen(String namaKonsumen) {
        this.namaKonsumen = namaKonsumen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHargaObat() {
        return idHargaObat;
    }

    public void setIdHargaObat(String idHargaObat) {
        this.idHargaObat = idHargaObat;
    }

    public String getJenisKonsumen() {
        return jenisKonsumen;
    }

    public void setJenisKonsumen(String jenisKonsumen) {
        this.jenisKonsumen = jenisKonsumen;
    }

    public String getIdRekanan() {
        return idRekanan;
    }

    public void setIdRekanan(String idRekanan) {
        this.idRekanan = idRekanan;
    }

    public BigDecimal getHargaBruto() {
        return hargaBruto;
    }

    public void setHargaBruto(BigDecimal hargaBruto) {
        this.hargaBruto = hargaBruto;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public BigDecimal getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(BigDecimal hargaJual) {
        this.hargaJual = hargaJual;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HargaObatPerKonsumen that = (HargaObatPerKonsumen) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idHargaObat != null ? !idHargaObat.equals(that.idHargaObat) : that.idHargaObat != null) return false;
        if (jenisKonsumen != null ? !jenisKonsumen.equals(that.jenisKonsumen) : that.jenisKonsumen != null)
            return false;
        if (idRekanan != null ? !idRekanan.equals(that.idRekanan) : that.idRekanan != null) return false;
        if (hargaBruto != null ? !hargaBruto.equals(that.hargaBruto) : that.hargaBruto != null) return false;
        if (margin != null ? !margin.equals(that.margin) : that.margin != null) return false;
        if (hargaJual != null ? !hargaJual.equals(that.hargaJual) : that.hargaJual != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idHargaObat != null ? idHargaObat.hashCode() : 0);
        result = 31 * result + (jenisKonsumen != null ? jenisKonsumen.hashCode() : 0);
        result = 31 * result + (idRekanan != null ? idRekanan.hashCode() : 0);
        result = 31 * result + (hargaBruto != null ? hargaBruto.hashCode() : 0);
        result = 31 * result + (margin != null ? margin.hashCode() : 0);
        result = 31 * result + (hargaJual != null ? hargaJual.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
