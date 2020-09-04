package com.neurix.akuntansi.transaksi.pengajuanBiaya.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class PengajuanBiayaDetail extends BaseModel {
    private String pengajuanBiayaDetailId;
    private String pengajuanBiayaId;
    private String branchId;
    private String branchName;
    private String divisiId;
    private String divisiName;
    private String stTanggal;
    private String transaksi;
    private String noBudgeting;
    private String stJumlah;

    private String stBudgetBiaya;
    private String stBudgetTerpakai;
    private String stSisaBudget;
    private String stBudgetBiayaSdBulanIni;
    private String stBudgetTerpakaiSdBulanIni;
    private String stSisaBudgetSdBulanIni;

    private String keperluan;
    private String keterangan;

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

    private String diterimaFlag;
    private String diterimaId;
    private Timestamp diterimaDate;

    private String closed;

    private Date tanggalRealisasi;
    private String stTanggalRealisasi;
    private Date tanggal;
    private String statusApproval;
    private String statusUserApproval;
    private String statusKeuangan;
    private String noJurnal;
    private String transaksiName;
    private String keperluanId;
    private String keperluanName;

    private String statusSaatIni;

    private String notApprovalNote;

    private BigDecimal budgetTerpakaiTransaksi;
    private String stBudgetTerpakaiTransaksi;

    private String stTanggalDari;
    private String stTanggalSelesai;
    private String stTanggalDariRealisasi;
    private String stTanggalSelesaiRealisasi;

    private boolean canPrint=false;
    private boolean sudahDibayar=false;
    private boolean canView =true;

    private String coa;
    private String coaName;
    private String coaDivisi;

    private String pembayaran;

    private String noKontrak;
    private String namaKontrak;

    private BigDecimal pph;
    private String stPph;

    private BigDecimal ppn;
    private String stPpn;

    private String urlIpa;
    private String fileName;
    private String flagUpload;

    private BigDecimal uangMuka;
    private String stUangMuka;
    private String nipUangMuka;
    private String buktiUangMuka;

    private String rkDikirim;
    private String coaTarget;
    private String rkId;
    private String branchIdUser;
    private String statusKeuanganName;

    public String getStatusKeuanganName() {
        return statusKeuanganName;
    }

    public void setStatusKeuanganName(String statusKeuanganName) {
        this.statusKeuanganName = statusKeuanganName;
    }

    public String getBranchIdUser() {
        return branchIdUser;
    }

    public void setBranchIdUser(String branchIdUser) {
        this.branchIdUser = branchIdUser;
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

    public String getRkId() {
        return rkId;
    }

    public void setRkId(String rkId) {
        this.rkId = rkId;
    }

    public BigDecimal getUangMuka() {
        return uangMuka;
    }

    public void setUangMuka(BigDecimal uangMuka) {
        this.uangMuka = uangMuka;
    }

    public String getStUangMuka() {
        return stUangMuka;
    }

    public void setStUangMuka(String stUangMuka) {
        this.stUangMuka = stUangMuka;
    }

    public String getNipUangMuka() {
        return nipUangMuka;
    }

    public void setNipUangMuka(String nipUangMuka) {
        this.nipUangMuka = nipUangMuka;
    }

    public String getBuktiUangMuka() {
        return buktiUangMuka;
    }

    public void setBuktiUangMuka(String buktiUangMuka) {
        this.buktiUangMuka = buktiUangMuka;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFlagUpload() {
        return flagUpload;
    }

    public void setFlagUpload(String flagUpload) {
        this.flagUpload = flagUpload;
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

    public String getStPpn() {
        return stPpn;
    }

    public void setStPpn(String stPpn) {
        this.stPpn = stPpn;
    }

    public BigDecimal getPph() {
        return pph;
    }

    public void setPph(BigDecimal pph) {
        this.pph = pph;
    }

    public String getStPph() {
        return stPph;
    }

    public void setStPph(String stPph) {
        this.stPph = stPph;
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

    public boolean isCanView() {
        return canView;
    }

    public void setCanView(boolean canView) {
        this.canView = canView;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }

    public void setSudahDibayar(boolean sudahDibayar) {
        this.sudahDibayar = sudahDibayar;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getCoaDivisi() {
        return coaDivisi;
    }

    public void setCoaDivisi(String coaDivisi) {
        this.coaDivisi = coaDivisi;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    public String getKeperluanId() {
        return keperluanId;
    }

    public void setKeperluanId(String keperluanId) {
        this.keperluanId = keperluanId;
    }

    public boolean isCanPrint() {
        return canPrint;
    }

    public void setCanPrint(boolean canPrint) {
        this.canPrint = canPrint;
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

    public String getStTanggalDariRealisasi() {
        return stTanggalDariRealisasi;
    }

    public void setStTanggalDariRealisasi(String stTanggalDariRealisasi) {
        this.stTanggalDariRealisasi = stTanggalDariRealisasi;
    }

    public String getStTanggalSelesaiRealisasi() {
        return stTanggalSelesaiRealisasi;
    }

    public void setStTanggalSelesaiRealisasi(String stTanggalSelesaiRealisasi) {
        this.stTanggalSelesaiRealisasi = stTanggalSelesaiRealisasi;
    }

    public BigDecimal getBudgetTerpakaiTransaksi() {
        return budgetTerpakaiTransaksi;
    }

    public void setBudgetTerpakaiTransaksi(BigDecimal budgetTerpakaiTransaksi) {
        this.budgetTerpakaiTransaksi = budgetTerpakaiTransaksi;
    }

    public String getStBudgetTerpakaiTransaksi() {
        return stBudgetTerpakaiTransaksi;
    }

    public void setStBudgetTerpakaiTransaksi(String stBudgetTerpakaiTransaksi) {
        this.stBudgetTerpakaiTransaksi = stBudgetTerpakaiTransaksi;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStBudgetBiayaSdBulanIni() {
        return stBudgetBiayaSdBulanIni;
    }

    public void setStBudgetBiayaSdBulanIni(String stBudgetBiayaSdBulanIni) {
        this.stBudgetBiayaSdBulanIni = stBudgetBiayaSdBulanIni;
    }

    public String getStBudgetTerpakaiSdBulanIni() {
        return stBudgetTerpakaiSdBulanIni;
    }

    public void setStBudgetTerpakaiSdBulanIni(String stBudgetTerpakaiSdBulanIni) {
        this.stBudgetTerpakaiSdBulanIni = stBudgetTerpakaiSdBulanIni;
    }

    public String getStSisaBudgetSdBulanIni() {
        return stSisaBudgetSdBulanIni;
    }

    public void setStSisaBudgetSdBulanIni(String stSisaBudgetSdBulanIni) {
        this.stSisaBudgetSdBulanIni = stSisaBudgetSdBulanIni;
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

    public String getStTanggalRealisasi() {
        return stTanggalRealisasi;
    }

    public void setStTanggalRealisasi(String stTanggalRealisasi) {
        this.stTanggalRealisasi = stTanggalRealisasi;
    }

    public Date getTanggalRealisasi() {
        return tanggalRealisasi;
    }

    public void setTanggalRealisasi(Date tanggalRealisasi) {
        this.tanggalRealisasi = tanggalRealisasi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatusSaatIni() {
        return statusSaatIni;
    }

    public void setStatusSaatIni(String statusSaatIni) {
        this.statusSaatIni = statusSaatIni;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getKeperluanName() {
        return keperluanName;
    }

    public void setKeperluanName(String keperluanName) {
        this.keperluanName = keperluanName;
    }

    public String getTransaksiName() {
        return transaksiName;
    }

    public void setTransaksiName(String transaksiName) {
        this.transaksiName = transaksiName;
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

    public String getStSisaBudget() {
        return stSisaBudget;
    }

    public void setStSisaBudget(String stSisaBudget) {
        this.stSisaBudget = stSisaBudget;
    }

    public BigDecimal getSisaBudget() {
        return sisaBudget;
    }

    public void setSisaBudget(BigDecimal sisaBudget) {
        this.sisaBudget = sisaBudget;
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
