package com.neurix.simrs.master.obat.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ItSimrsReturObatDetailEntity implements Serializable{

    private String idReturDetail;
    private String idObat;
    private String idReturObat;
    private String idBarang;
    private BigInteger qtyRetur;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String noJurnal;

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

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

    public BigInteger getQtyRetur() {
        return qtyRetur;
    }

    public void setQtyRetur(BigInteger qtyRetur) {
        this.qtyRetur = qtyRetur;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
}