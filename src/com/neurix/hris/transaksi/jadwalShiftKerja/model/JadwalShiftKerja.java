package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class JadwalShiftKerja extends BaseModel {
    private String jadwalShiftKerjaId;
    private String jadwalShiftKerjaName;
    private Date tanggal;
    private String branchId;
    private String statusGiling;
    private String keterangan;
    private String stTanggal;


    private String statusGilingName;
    private String branchName;
    private String groupId;
    private String shiftId;
    private String shiftName;
    private String groupName;
    private String stTanggalAwal;
    private String stTanggalAkhir;
    private Date tanggalAwal;
    private Date tanggalAkhir;

    private String nip;
    private String namaPegawai;
    private String positionName;
    private String profesiName;

    private String branchIdUser;
    private String onCall;
    private String jadwalShiftKerjaDetailId;
    private boolean hariIni = false;
    private String flagPanggil;
    private String grupIdUser;
    private boolean adminHcm=false;
    private Integer jumlahJadwal;
    private String statusSave;
    private String shiftName2;
    private String flagLibur;

    public String getFlagLibur() {
        return flagLibur;
    }

    public void setFlagLibur(String flagLibur) {
        this.flagLibur = flagLibur;
    }

    public String getShiftName2() {
        return shiftName2;
    }

    public void setShiftName2(String shiftName2) {
        this.shiftName2 = shiftName2;
    }

    public String getStatusSave() {
        return statusSave;
    }

    public void setStatusSave(String statusSave) {
        this.statusSave = statusSave;
    }

    public Integer getJumlahJadwal() {
        return jumlahJadwal;
    }

    public void setJumlahJadwal(Integer jumlahJadwal) {
        this.jumlahJadwal = jumlahJadwal;
    }

    public boolean isAdminHcm() {
        return adminHcm;
    }

    public void setAdminHcm(boolean adminHcm) {
        this.adminHcm = adminHcm;
    }

    public String getGrupIdUser() {
        return grupIdUser;
    }

    public void setGrupIdUser(String grupIdUser) {
        this.grupIdUser = grupIdUser;
    }

    public String getFlagPanggil() {
        return flagPanggil;
    }

    public void setFlagPanggil(String flagPanggil) {
        this.flagPanggil = flagPanggil;
    }

    public boolean isHariIni() {
        return hariIni;
    }

    public void setHariIni(boolean hariIni) {
        this.hariIni = hariIni;
    }

    public String getJadwalShiftKerjaDetailId() {
        return jadwalShiftKerjaDetailId;
    }

    public void setJadwalShiftKerjaDetailId(String jadwalShiftKerjaDetailId) {
        this.jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailId;
    }

    public String getOnCall() {
        return onCall;
    }

    public void setOnCall(String onCall) {
        this.onCall = onCall;
    }

    public String getBranchIdUser() {
        return branchIdUser;
    }

    public void setBranchIdUser(String branchIdUser) {
        this.branchIdUser = branchIdUser;
    }

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    private boolean tamp=true;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public boolean isTamp() {
        return tamp;
    }

    public void setTamp(boolean tamp) {
        this.tamp = tamp;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStatusGilingName() {
        return statusGilingName;
    }

    public void setStatusGilingName(String statusGilingName) {
        this.statusGilingName = statusGilingName;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJadwalShiftKerjaId() {
        return jadwalShiftKerjaId;
    }

    public void setJadwalShiftKerjaId(String jadwalShiftKerjaId) {
        this.jadwalShiftKerjaId = jadwalShiftKerjaId;
    }

    public String getJadwalShiftKerjaName() {
        return jadwalShiftKerjaName;
    }

    public void setJadwalShiftKerjaName(String jadwalShiftKerjaName) {
        this.jadwalShiftKerjaName = jadwalShiftKerjaName;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
}
