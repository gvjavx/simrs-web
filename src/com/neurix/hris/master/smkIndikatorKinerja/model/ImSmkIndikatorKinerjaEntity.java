package com.neurix.hris.master.smkIndikatorKinerja.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkIndikatorKinerjaEntity implements Serializable {

    private String indikatorKinerjaId ;
    private String positionId;
    private String indikatorKinerja;
    private Long bobot;
    private Long target ;
    private String satuanTarget;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getIndikatorKinerjaId() {
        return indikatorKinerjaId;
    }

    public void setIndikatorKinerjaId(String indikatorKinerjaId) {
        this.indikatorKinerjaId = indikatorKinerjaId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getIndikatorKinerja() {
        return indikatorKinerja;
    }

    public void setIndikatorKinerja(String indikatorKinerja) {
        this.indikatorKinerja = indikatorKinerja;
    }

    public Long getBobot() {
        return bobot;
    }

    public void setBobot(Long bobot) {
        this.bobot = bobot;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getSatuanTarget() {
        return satuanTarget;
    }

    public void setSatuanTarget(String satuanTarget) {
        this.satuanTarget = satuanTarget;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
