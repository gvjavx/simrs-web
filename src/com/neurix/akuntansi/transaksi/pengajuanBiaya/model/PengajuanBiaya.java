package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PengajuanBiaya extends BaseModel {
    private String pengajuanBiayaId;
    private String divisiId;
    private String divisiName;
    private String coaAjuan;
    private String namaCoa;
    private BigDecimal totalBiaya;
    private String stTotalBiaya;
    private Date tanggal;
    private String aprovalId;
    private String aprovalName;
    private Date aprovalDate;
    private String aprovalFlag;
    private String branchId;
    private BigDecimal budgetSaatIni;
    private String stBudgetSaatIni;
    private String transaksi;
    private String coaTarget;
    private String branchIdKanpus;
    private String tipeTransaksi;
    private String keterangan;
    private String noJurnal;
    private String stTanggal;
    private BigDecimal budgetTerpakai;
    private String stBudgetTerpakai;

    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public BigDecimal getBudgetTerpakai() {
        return budgetTerpakai;
    }

    public void setBudgetTerpakai(BigDecimal budgetTerpakai) {
        this.budgetTerpakai = budgetTerpakai;
    }

    public String getStBudgetTerpakai() {
        return stBudgetTerpakai;
    }

    public void setStBudgetTerpakai(String stBudgetTerpakai) {
        this.stBudgetTerpakai = stBudgetTerpakai;
    }

    public String getStBudgetSaatIni() {
        return stBudgetSaatIni;
    }

    public void setStBudgetSaatIni(String stBudgetSaatIni) {
        this.stBudgetSaatIni = stBudgetSaatIni;
    }

    private boolean approvePengajuanBiaya;

    public boolean isApprovePengajuanBiaya() {
        return approvePengajuanBiaya;
    }

    public void setApprovePengajuanBiaya(boolean approvePengajuanBiaya) {
        this.approvePengajuanBiaya = approvePengajuanBiaya;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public String getBranchIdKanpus() {
        return branchIdKanpus;
    }

    public void setBranchIdKanpus(String branchIdKanpus) {
        this.branchIdKanpus = branchIdKanpus;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getBudgetSaatIni() {
        return budgetSaatIni;
    }

    public void setBudgetSaatIni(BigDecimal budgetSaatIni) {
        this.budgetSaatIni = budgetSaatIni;
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

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getCoaAjuan() {
        return coaAjuan;
    }

    public void setCoaAjuan(String coaAjuan) {
        this.coaAjuan = coaAjuan;
    }

    public String getNamaCoa() {
        return namaCoa;
    }

    public void setNamaCoa(String namaCoa) {
        this.namaCoa = namaCoa;
    }

    public BigDecimal getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigDecimal totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public String getStTotalBiaya() {
        return stTotalBiaya;
    }

    public void setStTotalBiaya(String stTotalBiaya) {
        this.stTotalBiaya = stTotalBiaya;
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
}