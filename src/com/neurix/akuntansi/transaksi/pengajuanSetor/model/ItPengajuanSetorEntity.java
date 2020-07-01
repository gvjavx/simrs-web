package com.neurix.akuntansi.transaksi.pengajuanSetor.model;

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
public class ItPengajuanSetorEntity implements Serializable {

    private String pengajuanSetorId;
    private String branchId;
    private String periode;
    private String tahun;
    private String bulan;
    private Date registeredDate;
    private String tipePengajuanSetor;

    private BigDecimal jumlahPph21Payroll;
    private BigDecimal jumlahPph21Kso;
    private BigDecimal jumlahPph21Pengajuan;
    private BigDecimal jumlahSeluruhnya;

    private String noJurnal;
    private String approvalId;
    private Timestamp approvalDate;
    private String approvalFlag;

    private String cancelId;
    private Timestamp cancelDate;
    private String cancelFlag;
    private String keterangan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    //ppn
    private BigDecimal jumlahPpnKeluaran;
    private BigDecimal jumlahPpnMasukan;
    private BigDecimal jumlahPpnAset;
    private String stJumlahPpnKeluaran;
    private String stJumlahPpnMasukan;
    private String stJumlahPpnAset;

    public BigDecimal getJumlahPpnKeluaran() {
        return jumlahPpnKeluaran;
    }

    public void setJumlahPpnKeluaran(BigDecimal jumlahPpnKeluaran) {
        this.jumlahPpnKeluaran = jumlahPpnKeluaran;
    }

    public BigDecimal getJumlahPpnMasukan() {
        return jumlahPpnMasukan;
    }

    public void setJumlahPpnMasukan(BigDecimal jumlahPpnMasukan) {
        this.jumlahPpnMasukan = jumlahPpnMasukan;
    }

    public BigDecimal getJumlahPpnAset() {
        return jumlahPpnAset;
    }

    public void setJumlahPpnAset(BigDecimal jumlahPpnAset) {
        this.jumlahPpnAset = jumlahPpnAset;
    }

    public String getStJumlahPpnKeluaran() {
        return stJumlahPpnKeluaran;
    }

    public void setStJumlahPpnKeluaran(String stJumlahPpnKeluaran) {
        this.stJumlahPpnKeluaran = stJumlahPpnKeluaran;
    }

    public String getStJumlahPpnMasukan() {
        return stJumlahPpnMasukan;
    }

    public void setStJumlahPpnMasukan(String stJumlahPpnMasukan) {
        this.stJumlahPpnMasukan = stJumlahPpnMasukan;
    }

    public String getStJumlahPpnAset() {
        return stJumlahPpnAset;
    }

    public void setStJumlahPpnAset(String stJumlahPpnAset) {
        this.stJumlahPpnAset = stJumlahPpnAset;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipePengajuanSetor() {
        return tipePengajuanSetor;
    }

    public void setTipePengajuanSetor(String tipePengajuanSetor) {
        this.tipePengajuanSetor = tipePengajuanSetor;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public BigDecimal getJumlahPph21Payroll() {
        return jumlahPph21Payroll;
    }

    public void setJumlahPph21Payroll(BigDecimal jumlahPph21Payroll) {
        this.jumlahPph21Payroll = jumlahPph21Payroll;
    }

    public BigDecimal getJumlahPph21Kso() {
        return jumlahPph21Kso;
    }

    public void setJumlahPph21Kso(BigDecimal jumlahPph21Kso) {
        this.jumlahPph21Kso = jumlahPph21Kso;
    }

    public BigDecimal getJumlahPph21Pengajuan() {
        return jumlahPph21Pengajuan;
    }

    public void setJumlahPph21Pengajuan(BigDecimal jumlahPph21Pengajuan) {
        this.jumlahPph21Pengajuan = jumlahPph21Pengajuan;
    }

    public BigDecimal getJumlahSeluruhnya() {
        return jumlahSeluruhnya;
    }

    public void setJumlahSeluruhnya(BigDecimal jumlahSeluruhnya) {
        this.jumlahSeluruhnya = jumlahSeluruhnya;
    }

    public String getPengajuanSetorId() {
        return pengajuanSetorId;
    }

    public void setPengajuanSetorId(String pengajuanSetorId) {
        this.pengajuanSetorId = pengajuanSetorId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
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
