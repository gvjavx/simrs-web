package com.neurix.simrs.master.tindakanmedis.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class TindakanMedis {
    private String id;
    private String name;
    private String type;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

    private List<TindakanMedisDetail> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) { this.type = type; }

    public String getType() { return type; }

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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public void setDetails(List<TindakanMedisDetail> details) {
        this.details = details;
    }

    public List<TindakanMedisDetail> getDetails() {
        return details;
    }
}
