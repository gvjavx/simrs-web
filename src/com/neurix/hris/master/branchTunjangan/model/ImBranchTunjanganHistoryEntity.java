package com.neurix.hris.master.branchTunjangan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImBranchTunjanganHistoryEntity implements Serializable {

    private String branchTunjanganHistoryId;
    private String branchTunjanganId;
    private String branchId;
    private String tunjanganId;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getBranchTunjanganHistoryId() {
        return branchTunjanganHistoryId;
    }

    public void setBranchTunjanganHistoryId(String branchTunjanganHistoryId) {
        this.branchTunjanganHistoryId = branchTunjanganHistoryId;
    }

    public String getBranchTunjanganId() {
        return branchTunjanganId;
    }

    public void setBranchTunjanganId(String branchTunjanganId) {
        this.branchTunjanganId = branchTunjanganId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTunjanganId() {
        return tunjanganId;
    }

    public void setTunjanganId(String tunjanganId) {
        this.tunjanganId = tunjanganId;
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
