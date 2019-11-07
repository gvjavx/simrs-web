package com.neurix.simrs.master.penjamin.model;

import java.sql.Timestamp;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class ImSimrsPenjaminEntity {
    private String idPenjamin;
    private String penyelenggarakan;
    private String alamat;
    private String idTipePenjamin;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdPenjamin() {
        return idPenjamin;
    }

    public void setIdPenjamin(String idPenjamin) {
        this.idPenjamin = idPenjamin;
    }

    public String getPenyelenggarakan() {
        return penyelenggarakan;
    }

    public void setPenyelenggarakan(String penyelenggarakan) {
        this.penyelenggarakan = penyelenggarakan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getIdTipePenjamin() {
        return idTipePenjamin;
    }

    public void setIdTipePenjamin(String idTipePenjamin) {
        this.idTipePenjamin = idTipePenjamin;
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

    @Override
    public String toString() {
        return "ImSimrsPenjaminEntity{" +
                "idPenjamin='" + idPenjamin + '\'' +
                ", penyelenggarakan='" + penyelenggarakan + '\'' +
                ", alamat='" + alamat + '\'' +
                ", idTipePenjamin='" + idTipePenjamin + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                '}';
    }


}
