package com.neurix.akuntansi.master.trans.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Trans extends BaseModel {
    private String transId;
    private String transName;
    private String tipePembayaran;
    private String tipePembayaranName;
    private String flagSumberBaru;
//    private String master;
    private String masterName;

    private String stCreatedDate;
    private String stLastUpdate;

    private String master;
    private String divisiId;
    private String noNota;
    private String masterId;
    private String biaya;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getFlagSumberBaru() {
        return flagSumberBaru;
    }

    public void setFlagSumberBaru(String flagSumberBaru) {
        this.flagSumberBaru = flagSumberBaru;
    }

    public String getTipePembayaran() {
        return tipePembayaran;
    }

    public void setTipePembayaran(String tipePembayaran) {
        this.tipePembayaran = tipePembayaran;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

//    public String getMaster() {
//        return master;
//    }
//
//    public void setMaster(String master) {
//        this.master = master;
//    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getTipePembayaranName() {
        return tipePembayaranName;
    }

    public void setTipePembayaranName(String tipePembayaranName) {
        this.tipePembayaranName = tipePembayaranName;
    }
}