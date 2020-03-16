package com.neurix.simrs.master.labdetail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImSimrsLabDetailEntity implements Serializable{

    private String idLabDetail;
    private String idLab;
    private String namaDetailPeriksa;
    private String satuan;
    private String ketentuanAcuan;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdLabDetail() {
        return idLabDetail;
    }

    public void setIdLabDetail(String idLabDetail) {
        this.idLabDetail = idLabDetail;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getNamaDetailPeriksa() {
        return namaDetailPeriksa;
    }

    public void setNamaDetailPeriksa(String namaDetailPeriksa) {
        this.namaDetailPeriksa = namaDetailPeriksa;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKetentuanAcuan() {
        return ketentuanAcuan;
    }

    public void setKetentuanAcuan(String ketentuanAcuan) {
        this.ketentuanAcuan = ketentuanAcuan;
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