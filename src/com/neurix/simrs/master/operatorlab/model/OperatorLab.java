package com.neurix.simrs.master.operatorlab.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class OperatorLab extends BaseModel {

    private String idOperatorLab;
    private String namaOperator;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdOperatorLab() {
        return idOperatorLab;
    }

    public void setIdOperatorLab(String idOperatorLab) {
        this.idOperatorLab = idOperatorLab;
    }

    public String getNamaOperator() {
        return namaOperator;
    }

    public void setNamaOperator(String namaOperator) {
        this.namaOperator = namaOperator;
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
}