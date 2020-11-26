package com.neurix.simrs.transaksi.icu.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class DetailIcu extends BaseModel {
    private String idDetailIcu;
    private String idHeaderIcu;
    private String nilai;
    private String waktu;
    private String flag;
    private String action;
    private String createdWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String idDetailChekcup;

    public String getIdDetailChekcup() {
        return idDetailChekcup;
    }

    public void setIdDetailChekcup(String idDetailChekcup) {
        this.idDetailChekcup = idDetailChekcup;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIdDetailIcu() {
        return idDetailIcu;
    }

    public void setIdDetailIcu(String idDetailIcu) {
        this.idDetailIcu = idDetailIcu;
    }

    public String getIdHeaderIcu() {
        return idHeaderIcu;
    }

    public void setIdHeaderIcu(String idHeaderIcu) {
        this.idHeaderIcu = idHeaderIcu;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
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
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
