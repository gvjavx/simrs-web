package com.neurix.akuntansi.transaksi.budgeting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 29/04/20.
 */
public class ItAkunBudgetingPengadaanEntity implements Serializable {

    private String idPengadaan;
    private String noBudgetingDetail;
    private String namPengadaan;
    private BigDecimal nilai;
    private BigInteger qty;
    private BigDecimal subTotal;
    private String tipe;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idBudgetingDetail;
    private String noKontrak;
    private BigDecimal nilaiKontrak;

    public String getIdBudgetingDetail() {
        return idBudgetingDetail;
    }

    public void setIdBudgetingDetail(String idBudgetingDetail) {
        this.idBudgetingDetail = idBudgetingDetail;
    }

    public String getIdPengadaan() {
        return idPengadaan;
    }

    public void setIdPengadaan(String idPengadaan) {
        this.idPengadaan = idPengadaan;
    }

    public String getNamPengadaan() {
        return namPengadaan;
    }

    public void setNamPengadaan(String namPengadaan) {
        this.namPengadaan = namPengadaan;
    }

    public String getNoBudgetingDetail() {
        return noBudgetingDetail;
    }

    public void setNoBudgetingDetail(String noBudgetingDetail) {
        this.noBudgetingDetail = noBudgetingDetail;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public BigDecimal getNilaiKontrak() {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(BigDecimal nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }
}
