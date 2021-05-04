package com.neurix.hris.master.carrerPath.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImCarrerPathEntity implements Serializable {
    private String carrerPathId;
    private String jabatanId;
    private String divisiId;
    private String bagianId;
    private String thMinKerja;
    private String thMinBidang;
    private String thMinBagian;
    private String branchId;
    private String pendidikan;
    private String pelatihanJabatan;
    private String keterangan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCarrerPathId() {
        return carrerPathId;
    }

    public void setCarrerPathId(String carrerPathId) {
        this.carrerPathId = carrerPathId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(String jabatanId) {
        this.jabatanId = jabatanId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getPelatihanJabatan() {
        return pelatihanJabatan;
    }

    public void setPelatihanJabatan(String pelatihanJabatan) {
        this.pelatihanJabatan = pelatihanJabatan;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getThMinBagian() {
        return thMinBagian;
    }

    public void setThMinBagian(String thMinBagian) {
        this.thMinBagian = thMinBagian;
    }

    public String getThMinBidang() {
        return thMinBidang;
    }

    public void setThMinBidang(String thMinBidang) {
        this.thMinBidang = thMinBidang;
    }

    public String getThMinKerja() {
        return thMinKerja;
    }

    public void setThMinKerja(String thMinKerja) {
        this.thMinKerja = thMinKerja;
    }
}
