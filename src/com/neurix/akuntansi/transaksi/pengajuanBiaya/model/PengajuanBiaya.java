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
    private String tahun;
    private String aprovalId;
    private String aprovalName;
    private String aprovalDate;
    private String aprovalFlag;
    private String branchId;
    private BigDecimal budgetSaatIni;

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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    public String getAprovalDate() {
        return aprovalDate;
    }

    public void setAprovalDate(String aprovalDate) {
        this.aprovalDate = aprovalDate;
    }

    public String getAprovalFlag() {
        return aprovalFlag;
    }

    public void setAprovalFlag(String aprovalFlag) {
        this.aprovalFlag = aprovalFlag;
    }
}