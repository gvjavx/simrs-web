package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ItAkunPengajuanBiayaRkEntity {
    private String pengajuanBiayaRkId;
    private String noTransaksi;
    private String branchId;
    private String masterId;
    private BigDecimal jumlah;
    private String status;
    private String rkId;
    private String approveKeuFlag;
    private String aproveKeuId;
    private Timestamp approveKeuDate;
    private Date tanggalPengajuan;

    private String noFaktur;
    private String noInvoice;
    private Date tglFaktur;
    private Date tglInvoice;
    private Date tglDo;
    private String noJurnal;
    private String metodeBayar;

    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public Date getTglFaktur() {
        return tglFaktur;
    }

    public void setTglFaktur(Date tglFaktur) {
        this.tglFaktur = tglFaktur;
    }

    public Date getTglInvoice() {
        return tglInvoice;
    }

    public void setTglInvoice(Date tglInvoice) {
        this.tglInvoice = tglInvoice;
    }

    public Date getTglDo() {
        return tglDo;
    }

    public void setTglDo(Date tglDo) {
        this.tglDo = tglDo;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPengajuanBiayaRkId() {
        return pengajuanBiayaRkId;
    }

    public void setPengajuanBiayaRkId(String pengajuanBiayaRkId) {
        this.pengajuanBiayaRkId = pengajuanBiayaRkId;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRkId() {
        return rkId;
    }

    public void setRkId(String rkId) {
        this.rkId = rkId;
    }

    public String getApproveKeuFlag() {
        return approveKeuFlag;
    }

    public void setApproveKeuFlag(String approveKeuFlag) {
        this.approveKeuFlag = approveKeuFlag;
    }

    public String getAproveKeuId() {
        return aproveKeuId;
    }

    public void setAproveKeuId(String aproveKeuId) {
        this.aproveKeuId = aproveKeuId;
    }

    public Timestamp getApproveKeuDate() {
        return approveKeuDate;
    }

    public void setApproveKeuDate(Timestamp approveKeuDate) {
        this.approveKeuDate = approveKeuDate;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItAkunPengajuanBiayaRkEntity that = (ItAkunPengajuanBiayaRkEntity) o;
        return Objects.equals(pengajuanBiayaRkId, that.pengajuanBiayaRkId) &&
                Objects.equals(noTransaksi, that.noTransaksi) &&
                Objects.equals(masterId, that.masterId) &&
                Objects.equals(jumlah, that.jumlah) &&
                Objects.equals(status, that.status) &&
                Objects.equals(rkId, that.rkId) &&
                Objects.equals(approveKeuFlag, that.approveKeuFlag) &&
                Objects.equals(aproveKeuId, that.aproveKeuId) &&
                Objects.equals(approveKeuDate, that.approveKeuDate) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pengajuanBiayaRkId, noTransaksi, masterId, jumlah, status, rkId, approveKeuFlag, aproveKeuId, approveKeuDate, flag, action, createdWho, lastUpdateWho, createdDate, lastUpdate);
    }
}
