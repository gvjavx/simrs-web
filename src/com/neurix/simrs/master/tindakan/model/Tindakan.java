package com.neurix.simrs.master.tindakan.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class Tindakan {
    private String idTindakan;
    private String tindakan;
    private String idKategoriTindakan;
    private String namaKategoriTindakan;
    private String idKategoriTindakanIna;
    private String namaKategoriTindakanIna;
    private BigInteger tarif;
    private BigInteger tarifBpjs;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String stCreatedDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String lastUpdateWho;
    private String branchUser;

    private String idPelayanan;
    private String branchId;
    private String branchName;
    private BigDecimal diskon;
    private String stDiskon;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getDiskon() {
        return diskon;
    }

    public void setDiskon(BigDecimal diskon) {
        this.diskon = diskon;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public BigInteger getTarif() {
        return tarif;
    }

    public void setTarif(BigInteger tarif) {
        this.tarif = tarif;
    }

    public BigInteger getTarifBpjs() {
        return tarifBpjs;
    }

    public void setTarifBpjs(BigInteger tarifBpjs) {
        this.tarifBpjs = tarifBpjs;
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

    public String getBranchUser() {
        return branchUser;
    }

    public void setBranchUser(String branchUser) {
        this.branchUser = branchUser;
    }

    public String getIdKategoriTindakanIna() {
        return idKategoriTindakanIna;
    }

    public void setIdKategoriTindakanIna(String idKategoriTindakanIna) {
        this.idKategoriTindakanIna = idKategoriTindakanIna;
    }

    public String getNamaKategoriTindakan() {
        return namaKategoriTindakan;
    }

    public void setNamaKategoriTindakan(String namaKategoriTindakan) {
        this.namaKategoriTindakan = namaKategoriTindakan;
    }

    public String getNamaKategoriTindakanIna() {
        return namaKategoriTindakanIna;
    }

    public void setNamaKategoriTindakanIna(String namaKategoriTindakanIna) {
        this.namaKategoriTindakanIna = namaKategoriTindakanIna;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getStDiskon() {
        return stDiskon;
    }

    public void setStDiskon(String stDiskon) {
        this.stDiskon = stDiskon;
    }
}
