package com.neurix.hris.master.smkKategoriAspekBobot.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.hris.master.tipeAspek.model.ImTipeAspekEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkKategoriAspekBobotEntity implements Serializable {

    private String kategoriAspekBoobotId;
    private String kategoriAspekId;
    private String jabatanSmkId;
    private double bobot;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBranches imBranches;
    private ImTipeAspekEntity imTipeAspekEntity;

    public ImTipeAspekEntity getImTipeAspekEntity() {
        return imTipeAspekEntity;
    }

    public void setImTipeAspekEntity(ImTipeAspekEntity imTipeAspekEntity) {
        this.imTipeAspekEntity = imTipeAspekEntity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
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

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

    public String getKategoriAspekBoobotId() {
        return kategoriAspekBoobotId;
    }

    public void setKategoriAspekBoobotId(String kategoriAspekBoobotId) {
        this.kategoriAspekBoobotId = kategoriAspekBoobotId;
    }

    public String getKategoriAspekId() {
        return kategoriAspekId;
    }

    public void setKategoriAspekId(String kategoriAspekId) {
        this.kategoriAspekId = kategoriAspekId;
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
