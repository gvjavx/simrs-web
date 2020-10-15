package com.neurix.simrs.transaksi.ordergizi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ItSimrsDetailJenisDietEntity {
    private String idDetailJenisDiet;
    private String idOrderGizi;
    private String idJenisDiet;
    private String namaJenisDiet;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getIdDetailJenisDiet() {
        return idDetailJenisDiet;
    }

    public void setIdDetailJenisDiet(String idDetailJenisDiet) {
        this.idDetailJenisDiet = idDetailJenisDiet;
    }

    public String getIdOrderGizi() {
        return idOrderGizi;
    }

    public void setIdOrderGizi(String idOrderGizi) {
        this.idOrderGizi = idOrderGizi;
    }

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

    public void setCreatedWho(String createdDateWho) {
        this.createdWho = createdDateWho;
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
