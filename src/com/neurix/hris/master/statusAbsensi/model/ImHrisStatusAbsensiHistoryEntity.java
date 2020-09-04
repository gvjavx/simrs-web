package com.neurix.hris.master.statusAbsensi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisStatusAbsensiHistoryEntity {
    private String id;
    private String statusAbsensiId;
    private String statusAbsensiName;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusAbsensiId() {
        return statusAbsensiId;
    }

    public void setStatusAbsensiId(String statusAbsensiId) {
        this.statusAbsensiId = statusAbsensiId;
    }

    public String getStatusAbsensiName() {
        return statusAbsensiName;
    }

    public void setStatusAbsensiName(String statusAbsensiName) {
        this.statusAbsensiName = statusAbsensiName;
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

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImHrisStatusAbsensiHistoryEntity that = (ImHrisStatusAbsensiHistoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(statusAbsensiId, that.statusAbsensiId) &&
                Objects.equals(statusAbsensiName, that.statusAbsensiName) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusAbsensiId, statusAbsensiName, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
