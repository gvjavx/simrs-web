package com.neurix.simrs.transaksi.permintaanvendor.model;

import java.sql.Timestamp;

/**
 * Created by reza on 15/01/20.
 */
public class BatchPermintaanObat {
    private Integer noBatch;
    private String idApproval;
    private Integer jmlhObat;
    private String status;
    private String statusName;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String stLastUpdateWho;

    public String getStLastUpdateWho() {
        return stLastUpdateWho;
    }

    public void setStLastUpdateWho(String stLastUpdateWho) {
        this.stLastUpdateWho = stLastUpdateWho;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public String getIdApproval() {
        return idApproval;
    }

    public void setIdApproval(String idApproval) {
        this.idApproval = idApproval;
    }

    public Integer getJmlhObat() {
        return jmlhObat;
    }

    public void setJmlhObat(Integer jmlhObat) {
        this.jmlhObat = jmlhObat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
