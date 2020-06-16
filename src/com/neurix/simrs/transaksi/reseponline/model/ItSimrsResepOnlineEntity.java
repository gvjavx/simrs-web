package com.neurix.simrs.transaksi.reseponline.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 16/06/20.
 */
public class ItSimrsResepOnlineEntity {
    private String id;
    private String idTransaksiOnline;
    private String idObat;
    private String idPelayanan;
    private BigInteger qty;
    private BigDecimal harga;
    private BigDecimal subTotal;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDokter;
    private String ttdDokter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTransaksiOnline() {
        return idTransaksiOnline;
    }

    public void setIdTransaksiOnline(String idTransaksiOnline) {
        this.idTransaksiOnline = idTransaksiOnline;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
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

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getTtdDokter() {
        return ttdDokter;
    }

    public void setTtdDokter(String ttdDokter) {
        this.ttdDokter = ttdDokter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItSimrsResepOnlineEntity that = (ItSimrsResepOnlineEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idTransaksiOnline != null ? !idTransaksiOnline.equals(that.idTransaksiOnline) : that.idTransaksiOnline != null)
            return false;
        if (idObat != null ? !idObat.equals(that.idObat) : that.idObat != null) return false;
        if (idPelayanan != null ? !idPelayanan.equals(that.idPelayanan) : that.idPelayanan != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;
        if (harga != null ? !harga.equals(that.harga) : that.harga != null) return false;
        if (subTotal != null ? !subTotal.equals(that.subTotal) : that.subTotal != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (idDokter != null ? !idDokter.equals(that.idDokter) : that.idDokter != null) return false;
        if (ttdDokter != null ? !ttdDokter.equals(that.ttdDokter) : that.ttdDokter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idTransaksiOnline != null ? idTransaksiOnline.hashCode() : 0);
        result = 31 * result + (idObat != null ? idObat.hashCode() : 0);
        result = 31 * result + (idPelayanan != null ? idPelayanan.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (harga != null ? harga.hashCode() : 0);
        result = 31 * result + (subTotal != null ? subTotal.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (idDokter != null ? idDokter.hashCode() : 0);
        result = 31 * result + (ttdDokter != null ? ttdDokter.hashCode() : 0);
        return result;
    }
}
