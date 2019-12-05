package com.neurix.hris.master.shift.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 22/03/2018.
 */
public class Shift extends BaseModel implements Serializable {
    private String shiftId;
    private String shiftName;
    private String idBranch;
    private String jamAwal;
    private String jamAkhir;
    private String kelompokPositionId;

    private String jamAwalJam;
    private String jamAkhirJam;
    private String jamAwalMenit;
    private String jamAkhirMenit;

    private String branchName;
    private String kelompokPositionName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getKelompokPositionName() {
        return kelompokPositionName;
    }

    public void setKelompokPositionName(String kelompokPositionName) {
        this.kelompokPositionName = kelompokPositionName;
    }

    public String getKelompokPositionId() {
        return kelompokPositionId;
    }

    public void setKelompokPositionId(String kelompokPositionId) {
        this.kelompokPositionId = kelompokPositionId;
    }

    public String getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(String idBranch) {
        this.idBranch = idBranch;
    }

    public String getJamAwalJam() {
        return jamAwalJam;
    }

    public void setJamAwalJam(String jamAwalJam) {
        this.jamAwalJam = jamAwalJam;
    }

    public String getJamAkhirJam() {
        return jamAkhirJam;
    }

    public void setJamAkhirJam(String jamAkhirJam) {
        this.jamAkhirJam = jamAkhirJam;
    }

    public String getJamAwalMenit() {
        return jamAwalMenit;
    }

    public void setJamAwalMenit(String jamAwalMenit) {
        this.jamAwalMenit = jamAwalMenit;
    }

    public String getJamAkhirMenit() {
        return jamAkhirMenit;
    }

    public void setJamAkhirMenit(String jamAkhirMenit) {
        this.jamAkhirMenit = jamAkhirMenit;
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
}
