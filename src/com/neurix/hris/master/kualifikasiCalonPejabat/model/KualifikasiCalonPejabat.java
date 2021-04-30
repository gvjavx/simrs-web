package com.neurix.hris.master.kualifikasiCalonPejabat.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class KualifikasiCalonPejabat extends BaseModel implements Serializable {
    private String kualifikasiId;
    private String jabatanId;
    private String divisiId;
    private String bagianId;
    private String thMinKerja;
    private String thMinBidang;
    private String thMinBagian;
    private String branchId;
    private String pendidikan;
    private String pelatihanJabatan;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getPelatihanJabatan() {
        return pelatihanJabatan;
    }

    public void setPelatihanJabatan(String pelatihanJabatan) {
        this.pelatihanJabatan = pelatihanJabatan;
    }

    public String getKualifikasiId() {
        return kualifikasiId;
    }

    public void setKualifikasiId(String kualifikasiId) {
        this.kualifikasiId = kualifikasiId;
    }

    public String getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(String jabatanId) {
        this.jabatanId = jabatanId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getThMinKerja() {
        return thMinKerja;
    }

    public void setThMinKerja(String thMinKerja) {
        this.thMinKerja = thMinKerja;
    }

    public String getThMinBidang() {
        return thMinBidang;
    }

    public void setThMinBidang(String thMinBidang) {
        this.thMinBidang = thMinBidang;
    }

    public String getThMinBagian() {
        return thMinBagian;
    }

    public void setThMinBagian(String thMinBagian) {
        this.thMinBagian = thMinBagian;
    }
}
