package com.neurix.hris.master.biayapengobatan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ImHrisBiayaPengobatan implements Serializable {
    private String biayaPengobatanId;
    private String biayaPengobatanName;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    public String getBiayaPengobatanId() {
        return biayaPengobatanId;
    }

    public void setBiayaPengobatanId(String biayaPengobatanId) {
        this.biayaPengobatanId = biayaPengobatanId;
    }

    public String getBiayaPengobatanName() {
        return biayaPengobatanName;
    }

    public void setBiayaPengobatanName(String biayaPengobatanName) {
        this.biayaPengobatanName = biayaPengobatanName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateDateWho() {
        return createDateWho;
    }

    public void setCreateDateWho(String createDateWho) {
        this.createDateWho = createDateWho;
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
}
