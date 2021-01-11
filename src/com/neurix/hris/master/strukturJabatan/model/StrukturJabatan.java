package com.neurix.hris.master.strukturJabatan.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class StrukturJabatan extends BaseModel {
    private String strukturJabatanId;
    private String positionId;
    private String positionKelompokId;
    private String positionKelompokName;
    private Long level;
    private String branchId;
    private String branchName;
    private String parentId;
    private String subParent;
    private String positionName;
    private String uraian;
    private String bagian;

    private String bagianName;
    private Integer noUrutBagian;
    private String nip;
    private String name;
    private String statusPegawai;
    private String cDate;
    private String lUpdate;
    private String flagDefault;
    private String jenisPegawai;
    private String namaPegawai;

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getFlagDefault() {
        return flagDefault;
    }

    public void setFlagDefault(String flagDefault) {
        this.flagDefault = flagDefault;
    }

    public String getJenisPegawai() {
        return jenisPegawai;
    }

    public void setJenisPegawai(String jenisPegawai) {
        this.jenisPegawai = jenisPegawai;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public Integer getNoUrutBagian() {
        return noUrutBagian;
    }

    public void setNoUrutBagian(Integer noUrutBagian) {
        this.noUrutBagian = noUrutBagian;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public String getPositionKelompokId() {
        return positionKelompokId;
    }

    public void setPositionKelompokId(String positionKelompokId) {
        this.positionKelompokId = positionKelompokId;
    }

    public String getPositionKelompokName() {
        return positionKelompokName;
    }

    public void setPositionKelompokName(String positionKelompokName) {
        this.positionKelompokName = positionKelompokName;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getSubParent() {
        return subParent;
    }

    public void setSubParent(String subParent) {
        this.subParent = subParent;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStrukturJabatanId() {
        return strukturJabatanId;
    }

    public void setStrukturJabatanId(String strukturJabatanId) {
        this.strukturJabatanId = strukturJabatanId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getlUpdate() {
        return lUpdate;
    }

    public void setlUpdate(String lUpdate) {
        this.lUpdate = lUpdate;
    }
}