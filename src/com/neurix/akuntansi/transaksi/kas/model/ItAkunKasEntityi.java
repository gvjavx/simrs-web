package com.neurix.akuntansi.transaksi.kas.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Aji Noor on 03/03/2021
 */
public class ItAkunKasEntityi {
    private String kasId;
    private String tipeTransaksi;
    private Date tanggal;
    private String coaKas;
    private BigDecimal bayar;
    private String keterangan;
    private String noSlipBank;
    private String flag;
    private String action;
    private String branchId;
    private String noJurnal;
    private String registeredFlag;
    private Date registeredDate;
    private String registeredWho;
    private String tipeKas;
    private String approvalKeuanganFlag;
    private String approvalKeuanganId;
    private String approvalKeuanganName;
    private Timestamp approvalKeuanganDate;
    private String approvalKasubKeuanganFlag;
    private String approvalKasubKeuanganId;
    private String approvalKasubKeuanganName;
    private Timestamp approvalKasubKeuanganDate;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getKasId() {
        return kasId;
    }

    public void setKasId(String kasId) {
        this.kasId = kasId;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getCoaKas() {
        return coaKas;
    }

    public void setCoaKas(String coaKas) {
        this.coaKas = coaKas;
    }

    public BigDecimal getBayar() {
        return bayar;
    }

    public void setBayar(BigDecimal bayar) {
        this.bayar = bayar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoSlipBank() {
        return noSlipBank;
    }

    public void setNoSlipBank(String noSlipBank) {
        this.noSlipBank = noSlipBank;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getRegisteredFlag() {
        return registeredFlag;
    }

    public void setRegisteredFlag(String registeredFlag) {
        this.registeredFlag = registeredFlag;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getRegisteredWho() {
        return registeredWho;
    }

    public void setRegisteredWho(String registeredWho) {
        this.registeredWho = registeredWho;
    }

    public String getTipeKas() {
        return tipeKas;
    }

    public void setTipeKas(String tipeKas) {
        this.tipeKas = tipeKas;
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

    public String getApprovalKeuanganName() {
        return approvalKeuanganName;
    }

    public void setApprovalKeuanganName(String approvalKeuanganName) {
        this.approvalKeuanganName = approvalKeuanganName;
    }

    public Timestamp getApprovalKeuanganDate() {
        return approvalKeuanganDate;
    }

    public void setApprovalKeuanganDate(Timestamp approvalKeuanganDate) {
        this.approvalKeuanganDate = approvalKeuanganDate;
    }

    public String getApprovalKasubKeuanganFlag() {
        return approvalKasubKeuanganFlag;
    }

    public void setApprovalKasubKeuanganFlag(String approvalKasubKeuanganFlag) {
        this.approvalKasubKeuanganFlag = approvalKasubKeuanganFlag;
    }

    public String getApprovalKasubKeuanganId() {
        return approvalKasubKeuanganId;
    }

    public void setApprovalKasubKeuanganId(String approvalKasubKeuanganId) {
        this.approvalKasubKeuanganId = approvalKasubKeuanganId;
    }

    public String getApprovalKasubKeuanganName() {
        return approvalKasubKeuanganName;
    }

    public void setApprovalKasubKeuanganName(String approvalKasubKeuanganName) {
        this.approvalKasubKeuanganName = approvalKasubKeuanganName;
    }

    public Timestamp getApprovalKasubKeuanganDate() {
        return approvalKasubKeuanganDate;
    }

    public void setApprovalKasubKeuanganDate(Timestamp approvalKasubKeuanganDate) {
        this.approvalKasubKeuanganDate = approvalKasubKeuanganDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunKasEntityi that = (ItAkunKasEntityi) o;
        return Objects.equals(kasId, that.kasId) &&
                Objects.equals(tipeTransaksi, that.tipeTransaksi) &&
                Objects.equals(tanggal, that.tanggal) &&
                Objects.equals(coaKas, that.coaKas) &&
                Objects.equals(bayar, that.bayar) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(noSlipBank, that.noSlipBank) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(noJurnal, that.noJurnal) &&
                Objects.equals(registeredFlag, that.registeredFlag) &&
                Objects.equals(registeredDate, that.registeredDate) &&
                Objects.equals(registeredWho, that.registeredWho) &&
                Objects.equals(tipeKas, that.tipeKas) &&
                Objects.equals(approvalKeuanganFlag, that.approvalKeuanganFlag) &&
                Objects.equals(approvalKeuanganId, that.approvalKeuanganId) &&
                Objects.equals(approvalKeuanganName, that.approvalKeuanganName) &&
                Objects.equals(approvalKeuanganDate, that.approvalKeuanganDate) &&
                Objects.equals(approvalKasubKeuanganFlag, that.approvalKasubKeuanganFlag) &&
                Objects.equals(approvalKasubKeuanganId, that.approvalKasubKeuanganId) &&
                Objects.equals(approvalKasubKeuanganName, that.approvalKasubKeuanganName) &&
                Objects.equals(approvalKasubKeuanganDate, that.approvalKasubKeuanganDate) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kasId, tipeTransaksi, tanggal, coaKas, bayar, keterangan, noSlipBank, flag, action, branchId, noJurnal, registeredFlag, registeredDate, registeredWho, tipeKas, approvalKeuanganFlag, approvalKeuanganId, approvalKeuanganName, approvalKeuanganDate, approvalKasubKeuanganFlag, approvalKasubKeuanganId, approvalKasubKeuanganName, approvalKasubKeuanganDate, createdDate, lastUpdate, createdWho, lastUpdateWho);
    }
}
