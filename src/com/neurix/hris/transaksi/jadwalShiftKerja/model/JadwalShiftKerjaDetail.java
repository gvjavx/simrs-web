package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class JadwalShiftKerjaDetail extends BaseModel {
    private String jadwalShiftKerjaDetailId;
    private String jadwalShiftKerjaId;
    private String nip;
    private String namaPegawai;
    private String shiftId;
    private String shiftGroupId;
    private String onCall;
    private String flagLibur;

    private String positionName;
    private String shiftName;
    private String kelompokName;
    private String profesiName;
    private String profesiid;
    private String flagPanggil;
    private String panggilWho;
    private Timestamp panggilDate;
    private String keteranganPanggil;

    public String getKeteranganPanggil() {
        return keteranganPanggil;
    }

    public void setKeteranganPanggil(String keteranganPanggil) {
        this.keteranganPanggil = keteranganPanggil;
    }

    public String getFlagLibur() {
        return flagLibur;
    }

    public void setFlagLibur(String flagLibur) {
        this.flagLibur = flagLibur;
    }

    public String getPanggilWho() {
        return panggilWho;
    }

    public void setPanggilWho(String panggilWho) {
        this.panggilWho = panggilWho;
    }

    public Timestamp getPanggilDate() {
        return panggilDate;
    }

    public void setPanggilDate(Timestamp panggilDate) {
        this.panggilDate = panggilDate;
    }

    public String getFlagPanggil() {
        return flagPanggil;
    }

    public void setFlagPanggil(String flagPanggil) {
        this.flagPanggil = flagPanggil;
    }

    public String getOnCall() {
        return onCall;
    }

    public void setOnCall(String onCall) {
        this.onCall = onCall;
    }

    private String jadwalName;
    private String jamAwal;
    private String jamAkhir;
    private String branchId;

    public String getJadwalName() {
        return jadwalName;
    }

    public void setJadwalName(String jadwalName) {
        this.jadwalName = jadwalName;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getProfesiid() {
        return profesiid;
    }

    public void setProfesiid(String profesiid) {
        this.profesiid = profesiid;
    }

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getKelompokName() {
        return kelompokName;
    }

    public void setKelompokName(String kelompokName) {
        this.kelompokName = kelompokName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getJadwalShiftKerjaDetailId() {
        return jadwalShiftKerjaDetailId;
    }

    public void setJadwalShiftKerjaDetailId(String jadwalShiftKerjaDetailId) {
        this.jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailId;
    }

    public String getJadwalShiftKerjaId() {
        return jadwalShiftKerjaId;
    }

    public void setJadwalShiftKerjaId(String jadwalShiftKerjaId) {
        this.jadwalShiftKerjaId = jadwalShiftKerjaId;
    }

    public String getShiftGroupId() {
        return shiftGroupId;
    }

    public void setShiftGroupId(String shiftGroupId) {
        this.shiftGroupId = shiftGroupId;
    }
}
