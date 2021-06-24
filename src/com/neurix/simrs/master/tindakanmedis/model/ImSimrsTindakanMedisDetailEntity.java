package com.neurix.simrs.master.tindakanmedis.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsTindakanMedisDetailEntity {
    private String parentid;
    private String idDetail;
    private String infoName;
    private String infoType;
    private String infoValue;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    public String getIdDetail() { return idDetail; }

    public void setIdDetail(String idDetail) { this.idDetail = idDetail; }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getInfoName() { return infoName; }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(String infoValue) {
        this.infoValue = infoValue;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsTindakanMedisDetailEntity that = (ImSimrsTindakanMedisDetailEntity) o;
        return Objects.equals(parentid, that.parentid) && Objects.equals(idDetail, that.idDetail) && Objects.equals(infoName, that.infoName) && Objects.equals(infoType, that.infoType) && Objects.equals(infoValue, that.infoValue) && Objects.equals(action, that.action) && Objects.equals(flag, that.flag) && Objects.equals(createdDate, that.createdDate) && Objects.equals(createdWho, that.createdWho) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentid, idDetail, infoName, infoType, infoValue, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
