package com.neurix.simrs.mobileapi.model;

/**
 * @author gondok
 * Thursday, 30/01/20 19:15
 */
public class BatchPermintaanObatMobile {
    private String noBatch;
    private String idApproval;
    private String jmlhObat;
    private String status;
    private String statusName;
    private String lastUpdateWho;
    private String stLastUpdateWho;
    private String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String isApprove;

    public String getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(String noBatch) {
        this.noBatch = noBatch;
    }

    public String getIdApproval() {
        return idApproval;
    }

    public void setIdApproval(String idApproval) {
        this.idApproval = idApproval;
    }

    public String getJmlhObat() {
        return jmlhObat;
    }

    public void setJmlhObat(String jmlhObat) {
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

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public String getStLastUpdateWho() {
        return stLastUpdateWho;
    }

    public void setStLastUpdateWho(String stLastUpdateWho) {
        this.stLastUpdateWho = stLastUpdateWho;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }
}
