package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class PengajuanBiayaRk extends BaseModel {
    private String pengajuanBiayaRkId;
    private String branchId;
    private String noTransaksi;
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

    private String stJumlah;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String stTanggalPengajuan;
    private String stTanggalFaktur;
    private String stTanggalInvoice;
    private String stTanggalDo;
    private String branchName;
    private String statusName;
    private String masterName;
    private String noJurnal;
    private String metodeBayar;
    private String metodeBayarName;

    //tipe add / search
    private String tipe;
    private String tipeCetak;

    //DO
    private String noRekening;

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getTipeCetak() {
        return tipeCetak;
    }

    public void setTipeCetak(String tipeCetak) {
        this.tipeCetak = tipeCetak;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getMetodeBayarName() {
        return metodeBayarName;
    }

    public void setMetodeBayarName(String metodeBayarName) {
        this.metodeBayarName = metodeBayarName;
    }

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

    public String getStTanggalPengajuan() {
        return stTanggalPengajuan;
    }

    public void setStTanggalPengajuan(String stTanggalPengajuan) {
        this.stTanggalPengajuan = stTanggalPengajuan;
    }

    public String getStTanggalFaktur() {
        return stTanggalFaktur;
    }

    public void setStTanggalFaktur(String stTanggalFaktur) {
        this.stTanggalFaktur = stTanggalFaktur;
    }

    public String getStTanggalInvoice() {
        return stTanggalInvoice;
    }

    public void setStTanggalInvoice(String stTanggalInvoice) {
        this.stTanggalInvoice = stTanggalInvoice;
    }

    public String getStTanggalDo() {
        return stTanggalDo;
    }

    public void setStTanggalDo(String stTanggalDo) {
        this.stTanggalDo = stTanggalDo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTanggalSelesai() {
        return stTanggalSelesai;
    }

    public void setStTanggalSelesai(String stTanggalSelesai) {
        this.stTanggalSelesai = stTanggalSelesai;
    }

    private boolean save=false;

    public String getStJumlah() {
        return stJumlah;
    }

    public void setStJumlah(String stJumlah) {
        this.stJumlah = stJumlah;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
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

    private String branchIdUser;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchIdUser() {
        return branchIdUser;
    }

    public void setBranchIdUser(String branchIdUser) {
        this.branchIdUser = branchIdUser;
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
}
