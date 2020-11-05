package com.neurix.akuntansi.master.settingReportArusKas.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImAkunSettingReportArusKasEntity {
    private String settingReportArusKasId;
    private String kodeRekeningAlias;
    private String namaKodeRekeningAlias;
    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String flagLabel;

    public String getSettingReportArusKasId() {
        return settingReportArusKasId;
    }

    public void setSettingReportArusKasId(String settingReportArusKasId) {
        this.settingReportArusKasId = settingReportArusKasId;
    }

    public String getKodeRekeningAlias() {
        return kodeRekeningAlias;
    }

    public void setKodeRekeningAlias(String kodeRekeningAlias) {
        this.kodeRekeningAlias = kodeRekeningAlias;
    }

    public String getNamaKodeRekeningAlias() {
        return namaKodeRekeningAlias;
    }

    public void setNamaKodeRekeningAlias(String namaKodeRekeningAlias) {
        this.namaKodeRekeningAlias = namaKodeRekeningAlias;
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

    public String getFlagLabel() {
        return flagLabel;
    }

    public void setFlagLabel(String flagLabel) {
        this.flagLabel = flagLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImAkunSettingReportArusKasEntity that = (ImAkunSettingReportArusKasEntity) o;
        return Objects.equals(settingReportArusKasId, that.settingReportArusKasId) &&
                Objects.equals(kodeRekeningAlias, that.kodeRekeningAlias) &&
                Objects.equals(namaKodeRekeningAlias, that.namaKodeRekeningAlias) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(action, that.action) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(flagLabel, that.flagLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingReportArusKasId, kodeRekeningAlias, namaKodeRekeningAlias, flag, action, createdWho, lastUpdateWho, createdDate, lastUpdate, flagLabel);
    }
}
