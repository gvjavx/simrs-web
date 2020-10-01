package com.neurix.simrs.master.tindakan.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 13/08/20.
 */
public class ImSimrsHeaderTindakanEntity {
    private String idHeaderTindakan;
    private String namaTindakan;
    private BigInteger standardCost;
    private BigDecimal diskon;
    private BigInteger hargaDiskon;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String kategoriInaBpjs;
    private String idKategoriNota;

    public String getIdKategoriNota() {
        return idKategoriNota;
    }

    public void setIdKategoriNota(String idKategoriNota) {
        this.idKategoriNota = idKategoriNota;
    }

    public String getKategoriInaBpjs() {
        return kategoriInaBpjs;
    }

    public void setKategoriInaBpjs(String kategoriInaBpjs) {
        this.kategoriInaBpjs = kategoriInaBpjs;
    }

    public String getIdHeaderTindakan() {
        return idHeaderTindakan;
    }

    public void setIdHeaderTindakan(String idHeaderTindakan) {
        this.idHeaderTindakan = idHeaderTindakan;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }


    public BigInteger getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigInteger standardCost) {
        this.standardCost = standardCost;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public BigInteger getHargaDiskon() {
        return hargaDiskon;
    }

    public void setHargaDiskon(BigInteger hargaDiskon) {
        this.hargaDiskon = hargaDiskon;
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
