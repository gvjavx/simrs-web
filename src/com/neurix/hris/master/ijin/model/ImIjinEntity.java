package com.neurix.hris.master.ijin.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImIjinEntity implements Serializable {

    private String ijinId;
    private String ijinName;
    private Long jumlahIjin;
    private String gender;
    private String tipeHari;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIjinId() {
        return ijinId;
    }

    public void setIjinId(String ijinId) {
        this.ijinId = ijinId;
    }

    public String getIjinName() {
        return ijinName;
    }

    public void setIjinName(String ijinName) {
        this.ijinName = ijinName;
    }

    public Long getJumlahIjin() {
        return jumlahIjin;
    }

    public void setJumlahIjin(Long jumlahIjin) {
        this.jumlahIjin = jumlahIjin;
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
