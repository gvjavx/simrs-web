package com.neurix.hris.transaksi.indisipliner.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

public class Indisipliner extends BaseModel {
    private String indisiplinerId;
    private String nip;
    private String nama;
    private String bagianId;
    private String bagianName;
    private String branchId;
    private String tipeIndisipliner;
    private String keteranganPelanggaran;
    private Date tanggalAwalPantau;
    private Date tanggalAkhirPantau;
    private Date tanggalAwalBlokirAbsensi;
    private Date tanggalAkhirBlokirAbsensi;
    private Date tanggalAkhirBlokirSetuju;
    private Timestamp approvalDate;
    private String approvalPersonId;
    private String approvalPersonName;
    private String approvalNote;
    private String approvalFlag;
    private String notApprovalNote;
    private String closedFlag;
    private String closedNote;
    private Timestamp closedDate;
    private String dampak;
    private Date tanggal;

    private boolean notApprove;
    private boolean indisiplinerApprove;
    private boolean closed;
    private String stTanggalAwalPantau;
    private String stTanggalAkhirPantau;
    private String stTanggalAwalBlokirAbsensi;
    private String stTanggalAkhirBlokirAbsensi;
    private String golongan;
    private String golonganId;
    private String branchName;
    private String positionId;
    private String tmpApprove;
    private String positionName;
    private String stTanggal;
    private Date tanggalDari;
    private Date tanggalSelesai;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public Date getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(Date tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public boolean isNotApprove() {
        return notApprove;
    }

    public void setNotApprove(boolean notApprove) {
        this.notApprove = notApprove;
    }

    public boolean isIndisiplinerApprove() {
        return indisiplinerApprove;
    }

    public void setIndisiplinerApprove(boolean indisiplinerApprove) {
        this.indisiplinerApprove = indisiplinerApprove;
    }

    public String getStTanggalAwalPantau() {
        return stTanggalAwalPantau;
    }

    public void setStTanggalAwalPantau(String stTanggalAwalPantau) {
        this.stTanggalAwalPantau = stTanggalAwalPantau;
    }

    public String getStTanggalAkhirPantau() {
        return stTanggalAkhirPantau;
    }

    public void setStTanggalAkhirPantau(String stTanggalAkhirPantau) {
        this.stTanggalAkhirPantau = stTanggalAkhirPantau;
    }

    public String getStTanggalAwalBlokirAbsensi() {
        return stTanggalAwalBlokirAbsensi;
    }

    public void setStTanggalAwalBlokirAbsensi(String stTanggalAwalBlokirAbsensi) {
        this.stTanggalAwalBlokirAbsensi = stTanggalAwalBlokirAbsensi;
    }

    public String getStTanggalAkhirBlokirAbsensi() {
        return stTanggalAkhirBlokirAbsensi;
    }

    public void setStTanggalAkhirBlokirAbsensi(String stTanggalAkhirBlokirAbsensi) {
        this.stTanggalAkhirBlokirAbsensi = stTanggalAkhirBlokirAbsensi;
    }

    public String getIndisiplinerId() {
        return indisiplinerId;
    }

    public void setIndisiplinerId(String indisiplinerId) {
        this.indisiplinerId = indisiplinerId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDivisiId() {
        return bagianId;
    }

    public void setDivisiId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getDivisiName() {
        return bagianName;
    }

    public void setDivisiName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTipeIndisipliner() {
        return tipeIndisipliner;
    }

    public void setTipeIndisipliner(String tipeIndisipliner) {
        this.tipeIndisipliner = tipeIndisipliner;
    }

    public String getKeteranganPelanggaran() {
        return keteranganPelanggaran;
    }

    public void setKeteranganPelanggaran(String keteranganPelanggaran) {
        this.keteranganPelanggaran = keteranganPelanggaran;
    }

    public java.sql.Date getTanggalAwalPantau() {
        return tanggalAwalPantau;
    }

    public void setTanggalAwalPantau(java.sql.Date tanggalAwalPantau) {
        this.tanggalAwalPantau = tanggalAwalPantau;
    }

    public java.sql.Date getTanggalAkhirPantau() {
        return tanggalAkhirPantau;
    }

    public void setTanggalAkhirPantau(java.sql.Date tanggalAkhirPantau) {
        this.tanggalAkhirPantau = tanggalAkhirPantau;
    }

    public java.sql.Date getTanggalAwalBlokirAbsensi() {
        return tanggalAwalBlokirAbsensi;
    }

    public void setTanggalAwalBlokirAbsensi(java.sql.Date tanggalAwalBlokirAbsensi) {
        this.tanggalAwalBlokirAbsensi = tanggalAwalBlokirAbsensi;
    }

    public java.sql.Date getTanggalAkhirBlokirAbsensi() {
        return tanggalAkhirBlokirAbsensi;
    }

    public void setTanggalAkhirBlokirAbsensi(java.sql.Date tanggalAkhirBlokirAbsensi) {
        this.tanggalAkhirBlokirAbsensi = tanggalAkhirBlokirAbsensi;
    }

    public java.sql.Date getTanggalAkhirBlokirSetuju() {
        return tanggalAkhirBlokirSetuju;
    }

    public void setTanggalAkhirBlokirSetuju(java.sql.Date tanggalAkhirBlokirSetuju) {
        this.tanggalAkhirBlokirSetuju = tanggalAkhirBlokirSetuju;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalPersonId() {
        return approvalPersonId;
    }

    public void setApprovalPersonId(String approvalPersonId) {
        this.approvalPersonId = approvalPersonId;
    }

    public String getApprovalPersonName() {
        return approvalPersonName;
    }

    public void setApprovalPersonName(String approvalPersonName) {
        this.approvalPersonName = approvalPersonName;
    }

    public String getApprovalNote() {
        return approvalNote;
    }

    public void setApprovalNote(String approvalNote) {
        this.approvalNote = approvalNote;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public String getClosedFlag() {
        return closedFlag;
    }

    public void setClosedFlag(String closedFlag) {
        this.closedFlag = closedFlag;
    }

    public String getClosedNote() {
        return closedNote;
    }

    public void setClosedNote(String closedNote) {
        this.closedNote = closedNote;
    }

    public Timestamp getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Timestamp closedDate) {
        this.closedDate = closedDate;
    }

    public String getDampak() {
        return dampak;
    }

    public void setDampak(String dampak) {
        this.dampak = dampak;
    }
}
