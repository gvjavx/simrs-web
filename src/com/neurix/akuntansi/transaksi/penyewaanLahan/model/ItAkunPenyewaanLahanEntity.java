package com.neurix.akuntansi.transaksi.penyewaanLahan.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ItAkunPenyewaanLahanEntity {
    private String penyewaanLahanId;
    private String namaPenyewa;
    private String keterangan;
    private Date tanggalBayar;
    private BigDecimal nilai;
    private BigDecimal nilaiPpn;
    private BigDecimal nilaiNetto;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String branchId;
    private String cancelFlag;
    private String cancelWho;
    private Timestamp cancelDate;
    private String approvalFlag;
    private String approvalWho;
    private Timestamp approvalDate;
    private String metodeBayar;
    private String bank;
    private String noJurnal;

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigDecimal getNilaiPpn() {
        return nilaiPpn;
    }

    public void setNilaiPpn(BigDecimal nilaiPpn) {
        this.nilaiPpn = nilaiPpn;
    }

    public BigDecimal getNilaiNetto() {
        return nilaiNetto;
    }

    public void setNilaiNetto(BigDecimal nilaiNetto) {
        this.nilaiNetto = nilaiNetto;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getCancelWho() {
        return cancelWho;
    }

    public void setCancelWho(String cancelWho) {
        this.cancelWho = cancelWho;
    }

    public Timestamp getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalWho() {
        return approvalWho;
    }

    public void setApprovalWho(String approvalWho) {
        this.approvalWho = approvalWho;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getPenyewaanLahanId() {
        return penyewaanLahanId;
    }

    public void setPenyewaanLahanId(String penyewaanLahanId) {
        this.penyewaanLahanId = penyewaanLahanId;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunPenyewaanLahanEntity that = (ItAkunPenyewaanLahanEntity) o;
        return Objects.equals(penyewaanLahanId, that.penyewaanLahanId) &&
                Objects.equals(namaPenyewa, that.namaPenyewa) &&
                Objects.equals(keterangan, that.keterangan) &&
                Objects.equals(tanggalBayar, that.tanggalBayar) &&
                Objects.equals(nilai, that.nilai) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(penyewaanLahanId, namaPenyewa, keterangan, tanggalBayar, nilai, flag, action, createdDate, lastUpdate, createdWho, lastUpdateWho);
    }
}
