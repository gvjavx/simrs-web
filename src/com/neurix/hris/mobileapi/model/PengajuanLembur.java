package com.neurix.hris.mobileapi.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author gondok
 * Wednesday, 08/01/20 9:56
 */
public class PengajuanLembur implements Serializable {
    private String lemburId;
    private String nip;
    private String pegawaiName;
    private String divisiId;
    private String divisiName;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private String tipePegawaiId;
    private String statusGiling;
    private Date tanggalAwal;
    private String stTanggalAwal;
    private String stTanggalAkhir;
    private Date tanggalAkhir;
    private String jamAwal;
    private String jamAkhir;
    private Double lamaJam;
    private String approvalId;
    private String approvalName;
    private String approvalFlag;
    private Timestamp approvalDate;
    private String keterangan;
    private String tipeLembur;
    private String branchId;
    private String self;
    private Date tanggalAwalSetuju;
    private Date tanggalAkhirSetuju;
    private String notApprovalNote;
    private Double jamRealisasi;

    private String tmpApprove;
    private String statusGilingName;
    private String tipeLemburName;
    private String changeDate;
    private String bagian;
    private String bulan;
    private String tahun;

    private String channelId;
    private String os;

    private String actionError;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActionError() {
        return actionError;
    }

    public void setActionError(String actionError) {
        this.actionError = actionError;
    }

    public String getLemburId() {
        return lemburId;
    }

    public void setLemburId(String lemburId) {
        this.lemburId = lemburId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiName() {
        return pegawaiName;
    }

    public void setPegawaiName(String pegawaiName) {
        this.pegawaiName = pegawaiName;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getStTanggalAwal() {
        return stTanggalAwal;
    }

    public void setStTanggalAwal(String stTanggalAwal) {
        this.stTanggalAwal = stTanggalAwal;
    }

    public String getStTanggalAkhir() {
        return stTanggalAkhir;
    }

    public void setStTanggalAkhir(String stTanggalAkhir) {
        this.stTanggalAkhir = stTanggalAkhir;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public Double getLamaJam() {
        return lamaJam;
    }

    public void setLamaJam(Double lamaJam) {
        this.lamaJam = lamaJam;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeLembur() {
        return tipeLembur;
    }

    public void setTipeLembur(String tipeLembur) {
        this.tipeLembur = tipeLembur;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Date getTanggalAwalSetuju() {
        return tanggalAwalSetuju;
    }

    public void setTanggalAwalSetuju(Date tanggalAwalSetuju) {
        this.tanggalAwalSetuju = tanggalAwalSetuju;
    }

    public Date getTanggalAkhirSetuju() {
        return tanggalAkhirSetuju;
    }

    public void setTanggalAkhirSetuju(Date tanggalAkhirSetuju) {
        this.tanggalAkhirSetuju = tanggalAkhirSetuju;
    }

    public String getNotApprovalNote() {
        return notApprovalNote;
    }

    public void setNotApprovalNote(String notApprovalNote) {
        this.notApprovalNote = notApprovalNote;
    }

    public Double getJamRealisasi() {
        return jamRealisasi;
    }

    public void setJamRealisasi(Double jamRealisasi) {
        this.jamRealisasi = jamRealisasi;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public String getStatusGilingName() {
        return statusGilingName;
    }

    public void setStatusGilingName(String statusGilingName) {
        this.statusGilingName = statusGilingName;
    }

    public String getTipeLemburName() {
        return tipeLemburName;
    }

    public void setTipeLemburName(String tipeLemburName) {
        this.tipeLemburName = tipeLemburName;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
