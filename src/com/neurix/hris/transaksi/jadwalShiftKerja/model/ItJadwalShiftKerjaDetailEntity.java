package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItJadwalShiftKerjaDetailEntity implements Serializable {
    private String jadwalShiftKerjaDetailId;
    private String jadwalShiftKerjaId;
    private String shiftGroupId;
    private String nip;
    private String shiftId;
    private String positionName;
    private String profesiName;
    private String profesiId;
    private String namaPegawai;
    private String onCall;
    private String flagPanggil;
    private String panggilWho;
    private Timestamp panggilDate;
    private String flagLibur;

    private Timestamp createdDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getFlagLibur() {
        return flagLibur;
    }

    public void setFlagLibur(String flagLibur) {
        this.flagLibur = flagLibur;
    }

    public String getFlagPanggil() {
        return flagPanggil;
    }

    public void setFlagPanggil(String flagPanggil) {
        this.flagPanggil = flagPanggil;
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

    public String getOnCall() {
        return onCall;
    }

    public void setOnCall(String onCall) {
        this.onCall = onCall;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
