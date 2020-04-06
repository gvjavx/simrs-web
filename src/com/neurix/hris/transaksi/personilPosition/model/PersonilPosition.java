package com.neurix.hris.transaksi.personilPosition.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PersonilPosition extends BaseModel {
    private String personilPositionId;
    private String nip;
    private String personName;
    private String branchId;
    private String branchName;
    private String divisiId;
    private String divisiName;
    private String positionId;
    private String positionName;
    private String tanggalAktif;

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

    public String getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(String tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonilPositionId() {
        return personilPositionId;
    }

    public void setPersonilPositionId(String personilPositionId) {
        this.personilPositionId = personilPositionId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

//    public String getDivisiId() {
//        return divisiId;
//    }
//
//    public void setDivisiId(String divisiId) {
//        this.divisiId = divisiId;
//    }
//
//    public String getDivisiName() {
//        return divisiName;
//    }
//
//    public void setDivisiName(String divisiName) {
//        this.divisiName = divisiName;
//    }

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
}