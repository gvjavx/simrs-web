package com.neurix.hris.mobileapi.model;

import java.math.BigInteger;

/**
 * @author gondok
 * Thursday, 04/04/19 11:49
 */
public class HistoryCuti {

    private String cutiPegawaiId;
    private String cutiId;
    private String pegawaiPenggantiSementara;
    private String cutiName;
    private BigInteger lamaHariCuti;
    private BigInteger sisaCutiHari;
    private String approvalId;
    private String approvalDate;
    private String approvalFlag;
    private String note;
    private String noteApproval;
    private String cancelFlag;
    private String alamatCuti;
    private String keterangan;
    private String tanggalDari;
    private String tanggalSelesai;

    public String getTanggalDari() {
        return tanggalDari;
    }

    public void setTanggalDari(String tanggalDari) {
        this.tanggalDari = tanggalDari;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getCutiPegawaiId() {
        return cutiPegawaiId;
    }

    public void setCutiPegawaiId(String cutiPegawaiId) {
        this.cutiPegawaiId = cutiPegawaiId;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }

    public String getPegawaiPenggantiSementara() {
        return pegawaiPenggantiSementara;
    }

    public void setPegawaiPenggantiSementara(String pegawaiPenggantiSementara) {
        this.pegawaiPenggantiSementara = pegawaiPenggantiSementara;
    }

    public String getCutiName() {
        return cutiName;
    }

    public void setCutiName(String cutiName) {
        this.cutiName = cutiName;
    }

    public BigInteger getLamaHariCuti() {
        return lamaHariCuti;
    }

    public void setLamaHariCuti(BigInteger lamaHariCuti) {
        this.lamaHariCuti = lamaHariCuti;
    }

    public BigInteger getSisaCutiHari() {
        return sisaCutiHari;
    }

    public void setSisaCutiHari(BigInteger sisaCutiHari) {
        this.sisaCutiHari = sisaCutiHari;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteApproval() {
        return noteApproval;
    }

    public void setNoteApproval(String noteApproval) {
        this.noteApproval = noteApproval;
    }

    public String getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getAlamatCuti() {
        return alamatCuti;
    }

    public void setAlamatCuti(String alamatCuti) {
        this.alamatCuti = alamatCuti;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
