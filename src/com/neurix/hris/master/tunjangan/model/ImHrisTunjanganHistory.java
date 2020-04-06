package com.neurix.hris.master.tunjangan.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class ImHrisTunjanganHistory implements Serializable {
    private String tunjanganId;
    private String tunjanganName;
    private Timestamp createdDate;
    private String createDateWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTunjanganName() {
        return tunjanganName;
    }

    public void setTunjanganName(String tunjanganName) {
        this.tunjanganName = tunjanganName;
    }

    public String getTunjanganId() {
        return tunjanganId;
    }

    public void setTunjanganId(String tunjanganId) {
        this.tunjanganId = tunjanganId;
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
