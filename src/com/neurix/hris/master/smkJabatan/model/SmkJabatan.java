package com.neurix.hris.master.smkJabatan.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkJabatan extends BaseModel {
    private String jabatanSmkId;
    private String tipeAspekId;
    private String tipeAspekName;
    private String positionId;
    private String positionName;
    private String divisiId;
    private String divisiName;
    private String branchId;
    private String branchName;
    private double bobot;
    private double totalAspekA;
    private double totalAspekB;
    private double totalAspekC;
    private int jumlahBobot;

    public double getTotalAspekA() {
        return totalAspekA;
    }

    public void setTotalAspekA(double totalAspekA) {
        this.totalAspekA = totalAspekA;
    }

    public double getTotalAspekB() {
        return totalAspekB;
    }

    public void setTotalAspekB(double totalAspekB) {
        this.totalAspekB = totalAspekB;
    }

    public double getTotalAspekC() {
        return totalAspekC;
    }

    public void setTotalAspekC(double totalAspekC) {
        this.totalAspekC = totalAspekC;
    }

    public int getJumlahBobot() {
        return jumlahBobot;
    }

    public void setJumlahBobot(int jumlahBobot) {
        this.jumlahBobot = jumlahBobot;
    }

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

    public String getTipeAspekId() {
        return tipeAspekId;
    }

    public void setTipeAspekId(String tipeAspekId) {
        this.tipeAspekId = tipeAspekId;
    }

    public String getTipeAspekName() {
        return tipeAspekName;
    }

    public void setTipeAspekName(String tipeAspekName) {
        this.tipeAspekName = tipeAspekName;
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

}