package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPengajuanBiayaEntity implements Serializable {

    private String pengajuanBiayaId;
    private String divisiId;
    private String coaAjuan;
    private BigDecimal totalBiaya;
    private Date tanggal;
    private String aprovalId;
    private String aprovalName;
    private Date aprovalDate;
    private String aprovalFlag;
    private String branchId;
    private BigDecimal budgetSaatIni;
    private BigDecimal budgetTerpakai;
    private String transaksi;
    private String coaTarget;
    private String keterangan;
    private String noJurnal;
    private String rkDikirim;

    private BigDecimal totalPengajuan;
    private String stTotalPengajuan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getRkDikirim() {
        return rkDikirim;
    }

    public void setRkDikirim(String rkDikirim) {
        this.rkDikirim = rkDikirim;
    }

    public BigDecimal getTotalPengajuan() {
        return totalPengajuan;
    }

    public void setTotalPengajuan(BigDecimal totalPengajuan) {
        this.totalPengajuan = totalPengajuan;
    }

    public String getStTotalPengajuan() {
        return stTotalPengajuan;
    }

    public void setStTotalPengajuan(String stTotalPengajuan) {
        this.stTotalPengajuan = stTotalPengajuan;
    }

    public BigDecimal getBudgetTerpakai() {
        return budgetTerpakai;
    }

    public void setBudgetTerpakai(BigDecimal budgetTerpakai) {
        this.budgetTerpakai = budgetTerpakai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigDecimal getBudgetSaatIni() {
        return budgetSaatIni;
    }

    public void setBudgetSaatIni(BigDecimal budgetSaatIni) {
        this.budgetSaatIni = budgetSaatIni;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPengajuanBiayaId() {
        return pengajuanBiayaId;
    }

    public void setPengajuanBiayaId(String pengajuanBiayaId) {
        this.pengajuanBiayaId = pengajuanBiayaId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getCoaAjuan() {
        return coaAjuan;
    }

    public void setCoaAjuan(String coaAjuan) {
        this.coaAjuan = coaAjuan;
    }

    public BigDecimal getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigDecimal totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
    }

    public String getCoaTarget() {
        return coaTarget;
    }

    public void setCoaTarget(String coaTarget) {
        this.coaTarget = coaTarget;
    }

    public String getAprovalId() {
        return aprovalId;
    }

    public void setAprovalId(String aprovalId) {
        this.aprovalId = aprovalId;
    }

    public String getAprovalName() {
        return aprovalName;
    }

    public void setAprovalName(String aprovalName) {
        this.aprovalName = aprovalName;
    }

    public Date getAprovalDate() {
        return aprovalDate;
    }

    public void setAprovalDate(Date aprovalDate) {
        this.aprovalDate = aprovalDate;
    }

    public String getAprovalFlag() {
        return aprovalFlag;
    }

    public void setAprovalFlag(String aprovalFlag) {
        this.aprovalFlag = aprovalFlag;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
