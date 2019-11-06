package com.neurix.hris.transaksi.rekruitmenPabrik.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.provinsi.model.ImDesaEntity;
import com.neurix.hris.master.provinsi.model.ImKecamatanEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ItRekruitmenPabrikEntity implements Serializable {
    private String rekruitmenPabrikId;
    private String branchId;
    private String divisiId;
    private String mt;
    private Integer kuota;
    private String bagianId;
    private String closed;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBiodataEntity imBiodataEntity;
    private ImPosition imPosition;
    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public Integer getKuota() {
        return kuota;
    }

    public void setKuota(Integer kuota) {
        this.kuota = kuota;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
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

    public String getRekruitmenPabrikId() {
        return rekruitmenPabrikId;
    }

    public void setRekruitmenPabrikId(String rekruitmenPabrikId) {
        this.rekruitmenPabrikId = rekruitmenPabrikId;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
