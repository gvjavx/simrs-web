package com.neurix.hris.master.tipelibur.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ImHrisTipeLibur implements Serializable {
    private String tipeLiburId;
    private String tipeLiburName;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;

    public String getTipeLiburId() {
        return tipeLiburId;
    }

    public void setTipeLiburId(String tipeLiburId) {
        this.tipeLiburId = tipeLiburId;
    }

    public String getTipeLiburName() {
        return tipeLiburName;
    }

    public void setTipeLiburName(String tipeLiburName) {
        this.tipeLiburName = tipeLiburName;
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
