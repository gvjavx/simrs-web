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
