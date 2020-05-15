package com.neurix.hris.master.dokterKso.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DokterKso extends BaseModel {

    String dokterKsoId;
    String nip;
    String jenisKso;
    String masterId;
    String tarifIna;
    String branchId;
    String branchUser;
    BigDecimal persenKso;
    BigDecimal persenKs;
    String kodering;
    String branchName;
    String positionId;
    String namaDokter;

    String dokterKsoTindakanId;
    String tindakan;
    String tindakanName;
    BigDecimal persenKsoTindakan;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private String stCreatedDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String lastUpdateWho;

    public String getDokterKsoId() {
        return dokterKsoId;
    }

    public void setDokterKsoId(String dokterKsoId) {
        this.dokterKsoId = dokterKsoId;
    }

    public String getJenisKso() {
        return jenisKso;
    }

    public void setJenisKso(String jenisKso) {
        this.jenisKso = jenisKso;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public BigDecimal getPersenKs() {
        return persenKs;
    }

    public void setPersenKs(BigDecimal persenKs) {
        this.persenKs = persenKs;
    }

    public BigDecimal getPersenKso() {
        return persenKso;
    }

    public void setPersenKso(BigDecimal persenKso) {
        this.persenKso = persenKso;
    }

    public String getTarifIna() {
        return tarifIna;
    }

    public void setTarifIna(String tarifIna) {
        this.tarifIna = tarifIna;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchUser() {
        return branchUser;
    }

    public void setBranchUser(String branchUser) {
        this.branchUser = branchUser;
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
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public BigDecimal getPersenKsoTindakan() {
        return persenKsoTindakan;
    }

    public void setPersenKsoTindakan(BigDecimal persenKsoTindakan) {
        this.persenKsoTindakan = persenKsoTindakan;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getDokterKsoTindakanId() {
        return dokterKsoTindakanId;
    }

    public void setDokterKsoTindakanId(String dokterKsoTindakanId) {
        this.dokterKsoTindakanId = dokterKsoTindakanId;
    }

    public String getTindakanName() {
        return tindakanName;
    }

    public void setTindakanName(String tindakanName) {
        this.tindakanName = tindakanName;
    }
}