package com.neurix.hris.master.tipePayroll.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisTipePayrollEntity {
    private String tipePayrollId;
    private String tipePayrollName;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getTipePayrollId() {
        return tipePayrollId;
    }

    public void setTipePayrollId(String tipePayrollId) {
        this.tipePayrollId = tipePayrollId;
    }

    public String getTipePayrollName() {
        return tipePayrollName;
    }

    public void setTipePayrollName(String tipePayrollName) {
        this.tipePayrollName = tipePayrollName;
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
        ImHrisTipePayrollEntity that = (ImHrisTipePayrollEntity) o;
        return Objects.equals(tipePayrollId, that.tipePayrollId) &&
                Objects.equals(tipePayrollName, that.tipePayrollName) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipePayrollId, tipePayrollName, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
