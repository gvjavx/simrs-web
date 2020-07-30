package com.neurix.simrs.transaksi.tindakanrawaticd9.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class TindakanRawatICD9 extends BaseModel {

    private String idTindakanRawatIcd9;
    private String idIcd9;
    private String namaIcd9;
    private String idDetailCheckup;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdTindakanRawatIcd9() {
        return idTindakanRawatIcd9;
    }

    public void setIdTindakanRawatIcd9(String idTindakanRawatIcd9) {
        this.idTindakanRawatIcd9 = idTindakanRawatIcd9;
    }

    public String getIdIcd9() {
        return idIcd9;
    }

    public void setIdIcd9(String idIcd9) {
        this.idIcd9 = idIcd9;
    }

    public String getNamaIcd9() {
        return namaIcd9;
    }

    public void setNamaIcd9(String namaIcd9) {
        this.namaIcd9 = namaIcd9;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
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