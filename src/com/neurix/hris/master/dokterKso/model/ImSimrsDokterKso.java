package com.neurix.hris.master.dokterKso.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImSimrsDokterKso implements Serializable{

    String dokterKsoId;
    String nip;
    String jenisKso;
    String masterId;
    String tarifIna;
    String branchId;
    BigDecimal persenKso;
    BigDecimal persenKs;
    String kodering;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getKodering() {
        return kodering;
    }

    public void setKodering(String kodering) {
        this.kodering = kodering;
    }
}