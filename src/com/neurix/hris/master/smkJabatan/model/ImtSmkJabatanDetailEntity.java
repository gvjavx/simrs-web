package com.neurix.hris.master.smkJabatan.model;

import com.neurix.hris.master.smkCheckList.model.ImSmkCheckListEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImtSmkJabatanDetailEntity implements Serializable {

    private String jabatanSmkDetailId;
    private String jabatanSmkId;
    private String indikatorKinerja;
    private String subAspekA;
    private String satuan;
    private BigDecimal bobot;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImtSmkJabatanEntity imtSmkJabatanEntity ;
    private ImSmkCheckListEntity imSmkCheckListEntity ;

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public ImSmkCheckListEntity getImSmkCheckListEntity() {
        return imSmkCheckListEntity;
    }

    public void setImSmkCheckListEntity(ImSmkCheckListEntity imSmkCheckListEntity) {
        this.imSmkCheckListEntity = imSmkCheckListEntity;
    }

    public String getJabatanSmkDetailId() {
        return jabatanSmkDetailId;
    }

    public void setJabatanSmkDetailId(String jabatanSmkDetailId) {
        this.jabatanSmkDetailId = jabatanSmkDetailId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getBobot() {
        return bobot;
    }

    public void setBobot(BigDecimal bobot) {
        this.bobot = bobot;
    }

    public String getSubAspekA() {
        return subAspekA;
    }

    public void setSubAspekA(String subAspekA) {
        this.subAspekA = subAspekA;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ImtSmkJabatanEntity getImtSmkJabatanEntity() {
        return imtSmkJabatanEntity;
    }

    public void setImtSmkJabatanEntity(ImtSmkJabatanEntity imtSmkJabatanEntity) {
        this.imtSmkJabatanEntity = imtSmkJabatanEntity;
    }

    public String getIndikatorKinerja() {
        return indikatorKinerja;
    }

    public void setIndikatorKinerja(String indikatorKinerja) {
        this.indikatorKinerja = indikatorKinerja;
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

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

}
