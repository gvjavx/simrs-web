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
public class JadwalShiftKerjaDetail extends BaseModel {
    private String jadwalShiftKerjaDetailId;
    private String jadwalShiftKerjaId;
    private String nip;
    private String namaPegawai;
    private String shiftId;
    private String shiftGroupId;

    private String positionName;
    private String shiftName;
    private String kelompokName;
    private String profesiName;
    private String profesiid;

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
