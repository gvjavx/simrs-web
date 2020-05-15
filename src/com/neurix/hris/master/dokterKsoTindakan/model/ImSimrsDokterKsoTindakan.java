package com.neurix.hris.master.dokterKsoTindakan.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImSimrsDokterKsoTindakan {

    private String dokterKsoTindakanId;
    private String dokterKsoId;
    private String tindakanId;
    private BigDecimal persenKso;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

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

    public String getDokterKsoId() {
        return dokterKsoId;
    }

    public void setDokterKsoId(String dokterKsoId) {
        this.dokterKsoId = dokterKsoId;
    }

    public String getDokterKsoTindakanId() {
        return dokterKsoTindakanId;
    }

    public void setDokterKsoTindakanId(String dokterKsoTindakanId) {
        this.dokterKsoTindakanId = dokterKsoTindakanId;
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

    public BigDecimal getPersenKso() {
        return persenKso;
    }

    public void setPersenKso(BigDecimal persenKso) {
        this.persenKso = persenKso;
    }

    public String getTindakanId() {
        return tindakanId;
    }

    public void setTindakanId(String tindakanId) {
        this.tindakanId = tindakanId;
    }
}