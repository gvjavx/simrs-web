package com.neurix.hris.master.masaTanam.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImMasaTanamEntity implements Serializable {
    private String masaTanamId;
    private String masaTanamName;
    private Date awalGiling;
    private Date akhirGiling;
    private String closed;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public Date getAkhirGiling() {
        return akhirGiling;
    }

    public void setAkhirGiling(Date akhirGiling) {
        this.akhirGiling = akhirGiling;
    }

    public Date getAwalGiling() {
        return awalGiling;
    }

    public void setAwalGiling(Date awalGiling) {
        this.awalGiling = awalGiling;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getMasaTanamId() {
        return masaTanamId;
    }

    public void setMasaTanamId(String masaTanamId) {
        this.masaTanamId = masaTanamId;
    }

    public String getMasaTanamName() {
        return masaTanamName;
    }

    public void setMasaTanamName(String masaTanamName) {
        this.masaTanamName = masaTanamName;
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
