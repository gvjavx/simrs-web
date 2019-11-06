package com.neurix.hris.master.carrerPath.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class CarrerPath extends BaseModel {
    private String carrerPathId;
    private String jabatanId;
    private String jabatanName;
    private String divisiId;
    private String divisiName;
    private String bagianId;
    private String bagianName;
    private String thMinKerja;
    private String thMinBidang;
    private String thMinBagian;
    private String branchId;
    private String branchName;
    private String pendidikan;
    private String pendidikanName;
    private String jurusanIdAdd;
    private String jurusanId;
    private String jurusanName;
    private String kelompokPositionId;
    private String kelompokPositionName;
    private String golonganId;
    private String golonganName;
    private String pelatihanJabatan;
    private String keterangan;

    public String getPendidikanName() {
        return pendidikanName;
    }

    public void setPendidikanName(String pendidikanName) {
        this.pendidikanName = pendidikanName;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getKelompokPositionId() {
        return kelompokPositionId;
    }

    public void setKelompokPositionId(String kelompokPositionId) {
        this.kelompokPositionId = kelompokPositionId;
    }

    public String getKelompokPositionName() {
        return kelompokPositionName;
    }

    public void setKelompokPositionName(String kelompokPositionName) {
        this.kelompokPositionName = kelompokPositionName;
    }

    public String getJurusanIdAdd() {
        return jurusanIdAdd;
    }

    public void setJurusanIdAdd(String jurusanIdAdd) {
        this.jurusanIdAdd = jurusanIdAdd;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getJurusanName() {
        return jurusanName;
    }

    public void setJurusanName(String jurusanName) {
        this.jurusanName = jurusanName;
    }

    public String getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(String jurusanId) {
        this.jurusanId = jurusanId;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getJabatanId() {
        return jabatanId;
    }

    public void setJabatanId(String jabatanId) {
        this.jabatanId = jabatanId;
    }

    public String getCarrerPathId() {
        return carrerPathId;
    }

    public void setCarrerPathId(String carrerPathId) {
        this.carrerPathId = carrerPathId;
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