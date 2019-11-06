package com.neurix.hris.master.kualifikasiCalonPejabat.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.studyJurusan.model.ImStudyJurusanEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ImHrisKualifikasiCalonPejabatEntity implements Serializable {
    private String kualifikasiId;
    private String jabatanId;
    private String divisiId;
    private String bagianId;
    private String thMinKerja;
    private String thMinBidang;
    private String thMinBagian;
    private String branchId;
    private String pendidikan;
    private String jurusanId;
    private String kelompokPositionId;
    private String kelompokPositionName;
    private String golonganId;
    private String golonganName;
    private String jurusanName;
    private String pelatihanJabatan;
    private String keterangan;

    private Timestamp createdDate;
    private String createWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;
    private ImPositionBagianEntity imPositionBagianEntity;
    private ImPosition imPosition;
    private ImStudyJurusanEntity imStudyJurusanEntity;
    private ImKelompokPositionEntity imKelompokPositionEntity;
    private ImGolonganEntity imGolonganEntity;

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

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
    }

    public ImKelompokPositionEntity getImKelompokPositionEntity() {
        return imKelompokPositionEntity;
    }

    public void setImKelompokPositionEntity(ImKelompokPositionEntity imKelompokPositionEntity) {
        this.imKelompokPositionEntity = imKelompokPositionEntity;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(String jurusanId) {
        this.jurusanId = jurusanId;
    }

    public String getJurusanName() {
        return jurusanName;
    }

    public void setJurusanName(String jurusanName) {
        this.jurusanName = jurusanName;
    }

    public ImStudyJurusanEntity getImStudyJurusanEntity() {
        return imStudyJurusanEntity;
    }

    public void setImStudyJurusanEntity(ImStudyJurusanEntity imStudyJurusanEntity) {
        this.imStudyJurusanEntity = imStudyJurusanEntity;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImPositionBagianEntity getImPositionBagianEntity() {
        return imPositionBagianEntity;
    }

    public void setImPositionBagianEntity(ImPositionBagianEntity imPositionBagianEntity) {
        this.imPositionBagianEntity = imPositionBagianEntity;
    }

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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateWho() {
        return createWho;
    }

    public void setCreateWho(String createWho) {
        this.createWho = createWho;
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
}
