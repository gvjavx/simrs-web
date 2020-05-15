package com.neurix.hris.master.dokterKsoTindakan.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DokterKsoTindakan extends BaseModel {

    private String dokterKsoTindakanId;
    private String dokterKsoId;
    private String tindakanId;
    private String tindakanName;
    private BigDecimal persenKso;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getDokterKsoId() {
        return dokterKsoId;
    }

    public void setDokterKsoId(String dokterKsoId) {
        this.dokterKsoId = dokterKsoId;
    }

    public String getDokterKsoTindakanId() {
        return dokterKsoTindakanId;
    }

    public void setDokterKsoTindakanId(String dokterKsoTindakanId) {
        this.dokterKsoTindakanId = dokterKsoTindakanId;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public BigDecimal getPersenKso() {
        return persenKso;
    }

    public void setPersenKso(BigDecimal persenKso) {
        this.persenKso = persenKso;
    }

    public String getTindakanId() {
        return tindakanId;
    }

    public void setTindakanId(String tindakanId) {
        this.tindakanId = tindakanId;
    }

    public String getTindakanName() {
        return tindakanName;
    }

    public void setTindakanName(String tindakanName) {
        this.tindakanName = tindakanName;
    }
}