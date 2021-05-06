/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItHrisPayrollHeaderEntity {
    private String payrollHeaderId;
    private String tipePayroll;
    private String bulan;
    private String tahun;
    private String approvalSdmFlag;
    private Timestamp approvalSdmDate;
    private String approvalSdmName;
    private String approvalAksFlag;
    private String keteranganAks;
    private Timestamp approvalAksDate;
    private String approvalAksName;
    private String noJurnal;
    private String branchId;

    private String createdWho;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getKeteranganAks() {
        return keteranganAks;
    }

    public void setKeteranganAks(String keteranganAks) {
        this.keteranganAks = keteranganAks;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    @Override
    public String toString() {
        return "ItHrisPayrollHeaderEntity{" +
                "payrollHeaderId='" + payrollHeaderId + '\'' +
                ", tipePayroll='" + tipePayroll + '\'' +
                ", bulan='" + bulan + '\'' +
                ", tahun='" + tahun + '\'' +
                ", approvalSdmFlag='" + approvalSdmFlag + '\'' +
                ", approvalSdmDate=" + approvalSdmDate +
                ", approvalSdmName='" + approvalSdmName + '\'' +
                ", approvalAksFlag='" + approvalAksFlag + '\'' +
                ", keteranganAks='" + keteranganAks + '\'' +
                ", approvalAksDate=" + approvalAksDate +
                ", approvalAksName='" + approvalAksName + '\'' +
                ", noJurnal='" + noJurnal + '\'' +
                ", branchId='" + branchId + '\'' +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItHrisPayrollHeaderEntity that = (ItHrisPayrollHeaderEntity) o;
        return Objects.equals(payrollHeaderId, that.payrollHeaderId) &&
                Objects.equals(tipePayroll, that.tipePayroll) &&
                Objects.equals(bulan, that.bulan) &&
                Objects.equals(tahun, that.tahun) &&
                Objects.equals(approvalSdmFlag, that.approvalSdmFlag) &&
                Objects.equals(approvalSdmDate, that.approvalSdmDate) &&
                Objects.equals(approvalSdmName, that.approvalSdmName) &&
                Objects.equals(approvalAksFlag, that.approvalAksFlag) &&
                Objects.equals(keteranganAks, that.keteranganAks) &&
                Objects.equals(approvalAksDate, that.approvalAksDate) &&
                Objects.equals(approvalAksName, that.approvalAksName) &&
                Objects.equals(noJurnal, that.noJurnal) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payrollHeaderId, tipePayroll, bulan, tahun, approvalSdmFlag, approvalSdmDate, approvalSdmName, approvalAksFlag, keteranganAks, approvalAksDate, approvalAksName, noJurnal, branchId, createdWho, lastUpdateWho, flag, action, createdDate, lastUpdate);
    }
}
