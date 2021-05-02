/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PayrollHeader extends BaseModel {
    private String payrollHeaderId;
    private String tipePayroll;
    private String tipePayrollName;
    private String bulan;
    private String tahun;
    private String bulansd;
    private String tahunsd;
    private String approvalSdmFlag;
    private Timestamp approvalSdmDate;
    private String stApprovalSdmDate;
    private String approvalSdmName;
    private String approvalAksFlag;
    private Timestamp approvalAksDate;
    private String stApprovalAksDate;
    private String approvalAksName;
    private String noJurnal;
    private String branchId;
    private String branchName;
    private boolean flagEdit;
    private boolean flagRefresh;
    private boolean flagView;
    private boolean flagSlip;
    private boolean enableApprovalSdm;
    private boolean enableApprovalAks;
    private String statusPayroll;
    private int jumlahPegawai;
    private String stJumlahPegawai;
    private BigDecimal gajiBersihNilai;
    private BigDecimal gajiKotorNilai;
    private String gajiBersih;
    private String gajiKotor;
    private String keteranganAks;
    private boolean sdm;
    private boolean keuangan;
    private boolean kantorPusat;
    private boolean afterSave;

    private boolean editPphBulan12;
    private String nip;
    private String pegawaiName;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiName() {
        return pegawaiName;
    }

    public void setPegawaiName(String pegawaiName) {
        this.pegawaiName = pegawaiName;
    }

    public boolean isEditPphBulan12() {
        return editPphBulan12;
    }

    public void setEditPphBulan12(boolean editPphBulan12) {
        this.editPphBulan12 = editPphBulan12;
    }

    public boolean isAfterSave() {
        return afterSave;
    }

    public void setAfterSave(boolean afterSave) {
        this.afterSave = afterSave;
    }

    public boolean isKantorPusat() {
        return kantorPusat;
    }

    public void setKantorPusat(boolean kantorPusat) {
        this.kantorPusat = kantorPusat;
    }

    public String getTipePayrollName() {
        return tipePayrollName;
    }

    public void setTipePayrollName(String tipePayrollName) {
        this.tipePayrollName = tipePayrollName;
    }

    public boolean isSdm() {
        return sdm;
    }

    public void setSdm(boolean sdm) {
        this.sdm = sdm;
    }

    public boolean isKeuangan() {
        return keuangan;
    }

    public void setKeuangan(boolean keuangan) {
        this.keuangan = keuangan;
    }

    public String getBulansd() {
        return bulansd;
    }

    public void setBulansd(String bulansd) {
        this.bulansd = bulansd;
    }

    public String getTahunsd() {
        return tahunsd;
    }

    public void setTahunsd(String tahunsd) {
        this.tahunsd = tahunsd;
    }

    public String getKeteranganAks() {
        return keteranganAks;
    }

    public void setKeteranganAks(String keteranganAks) {
        this.keteranganAks = keteranganAks;
    }

    public String getStApprovalSdmDate() {
        return stApprovalSdmDate;
    }

    public void setStApprovalSdmDate(String stApprovalSdmDate) {
        this.stApprovalSdmDate = stApprovalSdmDate;
    }

    public String getStApprovalAksDate() {
        return stApprovalAksDate;
    }

    public void setStApprovalAksDate(String stApprovalAksDate) {
        this.stApprovalAksDate = stApprovalAksDate;
    }

    public int getJumlahPegawai() {
        return jumlahPegawai;
    }

    public void setJumlahPegawai(int jumlahPegawai) {
        this.jumlahPegawai = jumlahPegawai;
    }

    public String getStJumlahPegawai() {
        return stJumlahPegawai;
    }

    public void setStJumlahPegawai(String stJumlahPegawai) {
        this.stJumlahPegawai = stJumlahPegawai;
    }

    public BigDecimal getGajiBersihNilai() {
        return gajiBersihNilai;
    }

    public void setGajiBersihNilai(BigDecimal gajiBersihNilai) {
        this.gajiBersihNilai = gajiBersihNilai;
    }

    public BigDecimal getGajiKotorNilai() {
        return gajiKotorNilai;
    }

    public void setGajiKotorNilai(BigDecimal gajiKotorNilai) {
        this.gajiKotorNilai = gajiKotorNilai;
    }

    public String getGajiBersih() {
        return gajiBersih;
    }

    public void setGajiBersih(String gajiBersih) {
        this.gajiBersih = gajiBersih;
    }

    public String getGajiKotor() {
        return gajiKotor;
    }

    public void setGajiKotor(String gajiKotor) {
        this.gajiKotor = gajiKotor;
    }

    public boolean isFlagSlip() {
        return flagSlip;
    }

    public void setFlagSlip(boolean flagSlip) {
        this.flagSlip = flagSlip;
    }

    public boolean isFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(boolean flagEdit) {
        this.flagEdit = flagEdit;
    }

    public boolean isFlagRefresh() {
        return flagRefresh;
    }

    public void setFlagRefresh(boolean flagRefresh) {
        this.flagRefresh = flagRefresh;
    }

    public boolean isFlagView() {
        return flagView;
    }

    public void setFlagView(boolean flagView) {
        this.flagView = flagView;
    }

    public boolean isEnableApprovalSdm() {
        return enableApprovalSdm;
    }

    public void setEnableApprovalSdm(boolean enableApprovalSdm) {
        this.enableApprovalSdm = enableApprovalSdm;
    }

    public boolean isEnableApprovalAks() {
        return enableApprovalAks;
    }

    public void setEnableApprovalAks(boolean enableApprovalAks) {
        this.enableApprovalAks = enableApprovalAks;
    }

    public String getStatusPayroll() {
        return statusPayroll;
    }

    public void setStatusPayroll(String statusPayroll) {
        this.statusPayroll = statusPayroll;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPayrollHeaderId() {
        return payrollHeaderId;
    }

    public void setPayrollHeaderId(String payrollHeaderId) {
        this.payrollHeaderId = payrollHeaderId;
    }

    public String getTipePayroll() {
        return tipePayroll;
    }

    public void setTipePayroll(String tipePayroll) {
        this.tipePayroll = tipePayroll;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getApprovalSdmFlag() {
        return approvalSdmFlag;
    }

    public void setApprovalSdmFlag(String approvalSdmFlag) {
        this.approvalSdmFlag = approvalSdmFlag;
    }

    public Timestamp getApprovalSdmDate() {
        return approvalSdmDate;
    }

    public void setApprovalSdmDate(Timestamp approvalSdmDate) {
        this.approvalSdmDate = approvalSdmDate;
    }

    public String getApprovalSdmName() {
        return approvalSdmName;
    }

    public void setApprovalSdmName(String approvalSdmName) {
        this.approvalSdmName = approvalSdmName;
    }

    public String getApprovalAksFlag() {
        return approvalAksFlag;
    }

    public void setApprovalAksFlag(String approvalAksFlag) {
        this.approvalAksFlag = approvalAksFlag;
    }

    public Timestamp getApprovalAksDate() {
        return approvalAksDate;
    }

    public void setApprovalAksDate(Timestamp approvalAksDate) {
        this.approvalAksDate = approvalAksDate;
    }

    public String getApprovalAksName() {
        return approvalAksName;
    }

    public void setApprovalAksName(String approvalAksName) {
        this.approvalAksName = approvalAksName;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    @Override
    public String toString() {
        return "PayrollHeader{" +
                "payrollHeaderId='" + payrollHeaderId + '\'' +
                ", tipePayroll='" + tipePayroll + '\'' +
                ", bulan='" + bulan + '\'' +
                ", tahun='" + tahun + '\'' +
                ", bulansd='" + bulansd + '\'' +
                ", tahunsd='" + tahunsd + '\'' +
                ", approvalSdmFlag='" + approvalSdmFlag + '\'' +
                ", approvalSdmDate=" + approvalSdmDate +
                ", stApprovalSdmDate='" + stApprovalSdmDate + '\'' +
                ", approvalSdmName='" + approvalSdmName + '\'' +
                ", approvalAksFlag='" + approvalAksFlag + '\'' +
                ", approvalAksDate=" + approvalAksDate +
                ", stApprovalAksDate='" + stApprovalAksDate + '\'' +
                ", approvalAksName='" + approvalAksName + '\'' +
                ", noJurnal='" + noJurnal + '\'' +
                ", branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", flagEdit='" + flagEdit + '\'' +
                ", flagRefresh='" + flagRefresh + '\'' +
                ", flagView='" + flagView + '\'' +
                ", enableApprovalSdm='" + enableApprovalSdm + '\'' +
                ", enableApprovalAks='" + enableApprovalAks + '\'' +
                ", statusPayroll='" + statusPayroll + '\'' +
                ", jumlahPegawai=" + jumlahPegawai +
                ", stJumlahPegawai='" + stJumlahPegawai + '\'' +
                ", gajiBersihNilai=" + gajiBersihNilai +
                ", gajiKotorNilai=" + gajiKotorNilai +
                ", gajiBersih='" + gajiBersih + '\'' +
                ", gajiKotor='" + gajiKotor + '\'' +
                ", keteranganAks='" + keteranganAks + '\'' +
                '}';
    }
}
