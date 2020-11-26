package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

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
public class ItPengajuanBiayaDetailEntity implements Serializable {
    private String pengajuanBiayaDetailId;
    private String pengajuanBiayaId;
    private String branchId;
    private String divisiId;
    private String transaksi;
    private String noBudgeting;
    private String keperluan;
    private String keperluanId;
    private String keterangan;

    private Date tanggal;
    private BigDecimal jumlah;
    private BigDecimal budgetBiaya;
    private BigDecimal budgetTerpakai;
    private BigDecimal sisaBudget;

    private BigDecimal budgetBiayaSdBulanIni;
    private BigDecimal budgetTerpakaiSdBulanIni;
    private BigDecimal sisaBudgetSdBulanIni;

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

    private Date tanggalRealisasi;

    private String diterimaFlag;
    private String diterimaId;
    private Timestamp diterimaDate;

    private String closed;
    private String noJurnal;

    private String statusApproval;
    private String statusKeuangan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String notApprovalNote;
    private BigDecimal budgetTerpakaiTransaksi;
    private String tipePembayaran;

    private String noKontrak;
    private String namaKontrak;

    private BigDecimal pph;
    private BigDecimal ppn;

    private String urlIpa;

    private String rkDikirim;
    private String coaTarget;
    private String rkId;

    public String getRkId() {
        return rkId;
    }

    public void setRkId(String rkId) {
        this.rkId = rkId;
    }

    public String getRkDikirim() {
        return rkDikirim;
    }

    public void setRkDikirim(String rkDikirim) {
        this.rkDikirim = rkDikirim;
    }

    public String getCoaTarget() {
        return coaTarget;
    }

    public void setCoaTarget(String coaTarget) {
        this.coaTarget = coaTarget;
    }

    public String getUrlIpa() {
        return urlIpa;
    }

    public void setUrlIpa(String urlIpa) {
        this.urlIpa = urlIpa;
    }

    public BigDecimal getPpn() {
        return ppn;
    }

    public void setPpn(BigDecimal ppn) {
        this.ppn = ppn;
    }

    public BigDecimal getPph() {
        return pph;
    }

    public void setPph(BigDecimal pph) {
        this.pph = pph;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public String getNamaKontrak() {
        return namaKontrak;
    }

    public void setNamaKontrak(String namaKontrak) {
        this.namaKontrak = namaKontrak;
    }

    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
    }

    public String getKeperluanId() {
        return keperluanId;
    }

    public void setKeperluanId(String keperluanId) {
        this.keperluanId = keperluanId;
    }

    public BigDecimal getBudgetTerpakaiTransaksi() {
        return budgetTerpakaiTransaksi;
    }

    public void setBudgetTerpakaiTransaksi(BigDecimal budgetTerpakaiTransaksi) {
        this.budgetTerpakaiTransaksi = budgetTerpakaiTransaksi;
    }

    public BigDecimal getSisaBudget() {
        return sisaBudget;
    }

    public void setSisaBudget(BigDecimal sisaBudget) {
        this.sisaBudget = sisaBudget;
    }

    public BigDecimal getBudgetBiayaSdBulanIni() {
        return budgetBiayaSdBulanIni;
    }

    public void setBudgetBiayaSdBulanIni(BigDecimal budgetBiayaSdBulanIni) {
        this.budgetBiayaSdBulanIni = budgetBiayaSdBulanIni;
    }

    public BigDecimal getBudgetTerpakaiSdBulanIni() {
        return budgetTerpakaiSdBulanIni;
    }

    public void setBudgetTerpakaiSdBulanIni(BigDecimal budgetTerpakaiSdBulanIni) {
        this.budgetTerpakaiSdBulanIni = budgetTerpakaiSdBulanIni;
    }

    public BigDecimal getSisaBudgetSdBulanIni() {
        return sisaBudgetSdBulanIni;
    }

    public void setSisaBudgetSdBulanIni(BigDecimal sisaBudgetSdBulanIni) {
        this.sisaBudgetSdBulanIni = sisaBudgetSdBulanIni;
    }

    public Date getTanggalRealisasi() {
        return tanggalRealisasi;
    }

    public void setTanggalRealisasi(Date tanggalRealisasi) {
        this.tanggalRealisasi = tanggalRealisasi;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getDiterimaFlag() {
        return diterimaFlag;
    }

    public void setDiterimaFlag(String diterimaFlag) {
        this.diterimaFlag = diterimaFlag;
    }

    public String getDiterimaId() {
        return diterimaId;
    }

    public void setDiterimaId(String diterimaId) {
        this.diterimaId = diterimaId;
    }

    public Timestamp getDiterimaDate() {
        return diterimaDate;
    }

    public void setDiterimaDate(Timestamp diterimaDate) {
        this.diterimaDate = diterimaDate;
    }

    public String getStatusKeuangan() {
        return statusKeuangan;
    }

    public void setStatusKeuangan(String statusKeuangan) {
        this.statusKeuangan = statusKeuangan;
    }

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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
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
