package com.neurix.simrs.master.tindakanmedis.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsTindakanMedisEntity {
    private String id;
    private String name;
    private String type;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private String lastUpdate;
    private String lastUpdateWho;

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

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsTindakanMedisEntity that = (ImSimrsTindakanMedisEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(action, that.action) && Objects.equals(flag, that.flag) && Objects.equals(createdDate, that.createdDate) && Objects.equals(createdWho, that.createdWho) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
