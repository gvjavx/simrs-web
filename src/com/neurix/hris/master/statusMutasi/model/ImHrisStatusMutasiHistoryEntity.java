package com.neurix.hris.master.statusMutasi.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisStatusMutasiHistoryEntity {
    private String id;
    private String statusMutasiId;
    private String statusMutasiName;
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

    public String getStatusMutasiId() {
        return statusMutasiId;
    }

    public void setStatusMutasiId(String statusMutasiId) {
        this.statusMutasiId = statusMutasiId;
    }

    public String getStatusMutasiName() {
        return statusMutasiName;
    }

    public void setStatusMutasiName(String statusMutasiName) {
        this.statusMutasiName = statusMutasiName;
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
        ImHrisStatusMutasiHistoryEntity that = (ImHrisStatusMutasiHistoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(statusMutasiId, that.statusMutasiId) &&
                Objects.equals(statusMutasiName, that.statusMutasiName) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusMutasiId, statusMutasiName, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
