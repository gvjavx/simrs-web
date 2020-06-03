package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PengajuanBiayaDetail extends BaseModel {
    private String pengajuanBiayaDetailId;
    private String pengajuanBiayaId;
    private String branchId;
    private String divisiId;
    private String stTanggal;
    private String transaksi;
    private String noBudgeting;
    private String stJumlah;
    private String stBudgetBiaya;
    private String stBudgetTerpakai;
    private String keperluan;
    private String keterangan;

    private BigDecimal jumlah;
    private BigDecimal budgetBiaya;
    private BigDecimal budgetTerpakai;

    private String approvalKasubdivFlag;
    private String approvalKasubdivId;
    private Timestamp approvalKasubdivDate;

    private String approvalKadivFlag;
    private String approvalKadivId;
    private Timestamp approvalKadivDate;

    private String approvalGmFlag;
    private String approvalGmId;
    private Timestamp approvalGmDate;

    private String approvalKeuanganFlag;
    private String approvalKeuanganId;
    private Timestamp approvalKeuanganDate;

    private String approvalKeuanganKpFlag;
    private String approvalKeuanganKpId;
    private Timestamp approvalKeuanganKpDate;

    private String statusApproval;
    private String statusUserApproval;
    private String statusKeuangan;
    private String noJurnal;

    public String getApprovalKeuanganKpFlag() {
        return approvalKeuanganKpFlag;
    }

    public void setApprovalKeuanganKpFlag(String approvalKeuanganKpFlag) {
        this.approvalKeuanganKpFlag = approvalKeuanganKpFlag;
    }

    public String getApprovalKeuanganKpId() {
        return approvalKeuanganKpId;
    }

    public void setApprovalKeuanganKpId(String approvalKeuanganKpId) {
        this.approvalKeuanganKpId = approvalKeuanganKpId;
    }

    public Timestamp getApprovalKeuanganKpDate() {
        return approvalKeuanganKpDate;
    }

    public void setApprovalKeuanganKpDate(Timestamp approvalKeuanganKpDate) {
        this.approvalKeuanganKpDate = approvalKeuanganKpDate;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getStatusKeuangan() {
        return statusKeuangan;
    }

    public void setStatusKeuangan(String statusKeuangan) {
        this.statusKeuangan = statusKeuangan;
    }

    public String getStatusUserApproval() {
        return statusUserApproval;
    }

    public void setStatusUserApproval(String statusUserApproval) {
        this.statusUserApproval = statusUserApproval;
    }

    public String getApprovalGmFlag() {
        return approvalGmFlag;
    }

    public void setApprovalGmFlag(String approvalGmFlag) {
        this.approvalGmFlag = approvalGmFlag;
    }

    public String getApprovalGmId() {
        return approvalGmId;
    }

    public void setApprovalGmId(String approvalGmId) {
        this.approvalGmId = approvalGmId;
    }

    public Timestamp getApprovalGmDate() {
        return approvalGmDate;
    }

    public void setApprovalGmDate(Timestamp approvalGmDate) {
        this.approvalGmDate = approvalGmDate;
    }

    public String getPengajuanBiayaDetailId() {
        return pengajuanBiayaDetailId;
    }

    public void setPengajuanBiayaDetailId(String pengajuanBiayaDetailId) {
        this.pengajuanBiayaDetailId = pengajuanBiayaDetailId;
    }

    public String getPengajuanBiayaId() {
        return pengajuanBiayaId;
    }

    public void setPengajuanBiayaId(String pengajuanBiayaId) {
        this.pengajuanBiayaId = pengajuanBiayaId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
    }

    public String getNoBudgeting() {
        return noBudgeting;
    }

    public void setNoBudgeting(String noBudgeting) {
        this.noBudgeting = noBudgeting;
    }

    public String getStJumlah() {
        return stJumlah;
    }

    public void setStJumlah(String stJumlah) {
        this.stJumlah = stJumlah;
    }

    public String getStBudgetBiaya() {
        return stBudgetBiaya;
    }

    public void setStBudgetBiaya(String stBudgetBiaya) {
        this.stBudgetBiaya = stBudgetBiaya;
    }

    public String getStBudgetTerpakai() {
        return stBudgetTerpakai;
    }

    public void setStBudgetTerpakai(String stBudgetTerpakai) {
        this.stBudgetTerpakai = stBudgetTerpakai;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getBudgetBiaya() {
        return budgetBiaya;
    }

    public void setBudgetBiaya(BigDecimal budgetBiaya) {
        this.budgetBiaya = budgetBiaya;
    }

    public BigDecimal getBudgetTerpakai() {
        return budgetTerpakai;
    }

    public void setBudgetTerpakai(BigDecimal budgetTerpakai) {
        this.budgetTerpakai = budgetTerpakai;
    }

    public String getApprovalKasubdivFlag() {
        return approvalKasubdivFlag;
    }

    public void setApprovalKasubdivFlag(String approvalKasubdivFlag) {
        this.approvalKasubdivFlag = approvalKasubdivFlag;
    }

    public String getApprovalKasubdivId() {
        return approvalKasubdivId;
    }

    public void setApprovalKasubdivId(String approvalKasubdivId) {
        this.approvalKasubdivId = approvalKasubdivId;
    }

    public Timestamp getApprovalKasubdivDate() {
        return approvalKasubdivDate;
    }

    public void setApprovalKasubdivDate(Timestamp approvalKasubdivDate) {
        this.approvalKasubdivDate = approvalKasubdivDate;
    }

    public String getApprovalKadivFlag() {
        return approvalKadivFlag;
    }

    public void setApprovalKadivFlag(String approvalKadivFlag) {
        this.approvalKadivFlag = approvalKadivFlag;
    }

    public String getApprovalKadivId() {
        return approvalKadivId;
    }

    public void setApprovalKadivId(String approvalKadivId) {
        this.approvalKadivId = approvalKadivId;
    }

    public Timestamp getApprovalKadivDate() {
        return approvalKadivDate;
    }

    public void setApprovalKadivDate(Timestamp approvalKadivDate) {
        this.approvalKadivDate = approvalKadivDate;
    }

    public String getApprovalKeuanganFlag() {
        return approvalKeuanganFlag;
    }

    public void setApprovalKeuanganFlag(String approvalKeuanganFlag) {
        this.approvalKeuanganFlag = approvalKeuanganFlag;
    }

    public String getApprovalKeuanganId() {
        return approvalKeuanganId;
    }

    public void setApprovalKeuanganId(String approvalKeuanganId) {
        this.approvalKeuanganId = approvalKeuanganId;
    }

    public Timestamp getApprovalKeuanganDate() {
        return approvalKeuanganDate;
    }

    public void setApprovalKeuanganDate(Timestamp approvalKeuanganDate) {
        this.approvalKeuanganDate = approvalKeuanganDate;
    }

    public String getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(String statusApproval) {
        this.statusApproval = statusApproval;
    }
}
