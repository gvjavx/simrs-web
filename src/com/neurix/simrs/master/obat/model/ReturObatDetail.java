package com.neurix.simrs.master.obat.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class ReturObatDetail extends BaseModel {

    private String idReturDetail;
    private String idObat;
    private String idReturObat;
    private String idBarang;
    private String qtyRetur;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdReturDetail() {
        return idReturDetail;
    }

    public void setIdReturDetail(String idReturDetail) {
        this.idReturDetail = idReturDetail;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdReturObat() {
        return idReturObat;
    }

    public void setIdReturObat(String idReturObat) {
        this.idReturObat = idReturObat;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getQtyRetur() {
        return qtyRetur;
    }

    public void setQtyRetur(String qtyRetur) {
        this.qtyRetur = qtyRetur;
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