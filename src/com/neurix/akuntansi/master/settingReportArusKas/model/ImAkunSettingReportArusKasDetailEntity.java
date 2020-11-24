package com.neurix.akuntansi.master.settingReportArusKas.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImAkunSettingReportArusKasDetailEntity {
    private String settingReportArusKasDetailId;
    private String settingReportArusKasId;
    private String rekeningId;
    private String operator;
    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getSettingReportArusKasDetailId() {
        return settingReportArusKasDetailId;
    }

    public void setSettingReportArusKasDetailId(String settingReportArusKasDetailId) {
        this.settingReportArusKasDetailId = settingReportArusKasDetailId;
    }

    public String getSettingReportArusKasId() {
        return settingReportArusKasId;
    }

    public void setSettingReportArusKasId(String settingReportArusKasId) {
        this.settingReportArusKasId = settingReportArusKasId;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImAkunSettingReportArusKasDetailEntity that = (ImAkunSettingReportArusKasDetailEntity) o;
        return Objects.equals(settingReportArusKasDetailId, that.settingReportArusKasDetailId) &&
                Objects.equals(settingReportArusKasId, that.settingReportArusKasId) &&
                Objects.equals(rekeningId, that.rekeningId) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingReportArusKasDetailId, settingReportArusKasId, rekeningId, operator, flag, action, createdWho, lastUpdateWho, createdDate, lastUpdate);
    }
}
