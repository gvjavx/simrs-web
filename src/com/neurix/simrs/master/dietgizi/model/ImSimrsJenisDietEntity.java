package com.neurix.simrs.master.dietgizi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsJenisDietEntity {
    private String idJenisDiet;
    private String namaJenisDiet;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdJenisDiet() {
        return idJenisDiet;
    }

    public void setIdJenisDiet(String idJenisDiet) {
        this.idJenisDiet = idJenisDiet;
    }

    public String getNamaJenisDiet() {
        return namaJenisDiet;
    }

    public void setNamaJenisDiet(String namaJenisDiet) {
        this.namaJenisDiet = namaJenisDiet;
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
