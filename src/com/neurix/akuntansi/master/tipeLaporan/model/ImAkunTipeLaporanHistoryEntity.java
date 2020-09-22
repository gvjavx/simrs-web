package com.neurix.akuntansi.master.tipeLaporan.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImAkunTipeLaporanHistoryEntity {
    private String id;
    private String tipeLaporanId;
    private String tipeLaporanName;
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

    public String getTipeLaporanId() {
        return tipeLaporanId;
    }

    public void setTipeLaporanId(String tipeLaporanId) {
        this.tipeLaporanId = tipeLaporanId;
    }

    public String getTipeLaporanName() {
        return tipeLaporanName;
    }

    public void setTipeLaporanName(String tipeLaporanName) {
        this.tipeLaporanName = tipeLaporanName;
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
        ImAkunTipeLaporanHistoryEntity that = (ImAkunTipeLaporanHistoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tipeLaporanId, that.tipeLaporanId) &&
                Objects.equals(tipeLaporanName, that.tipeLaporanName) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipeLaporanId, tipeLaporanName, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
