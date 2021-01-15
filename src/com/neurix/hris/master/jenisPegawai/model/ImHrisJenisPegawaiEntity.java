package com.neurix.hris.master.jenisPegawai.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisJenisPegawaiEntity {
    private String jenisPegawaiId;
    private String jenisPegawaiName;
    private String action;
    private String flag;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigDecimal persenGaji;
    private String flagDefault;

    public BigDecimal getPersenGaji() {
        return persenGaji;
    }

    public void setPersenGaji(BigDecimal persenGaji) {
        this.persenGaji = persenGaji;
    }

    public String getFlagDefault() {
        return flagDefault;
    }

    public void setFlagDefault(String flagDefault) {
        this.flagDefault = flagDefault;
    }

    public String getJenisPegawaiId() {
        return jenisPegawaiId;
    }

    public void setJenisPegawaiId(String jenisPegawaiId) {
        this.jenisPegawaiId = jenisPegawaiId;
    }

    public String getJenisPegawaiName() {
        return jenisPegawaiName;
    }

    public void setJenisPegawaiName(String jenisPegawaiName) {
        this.jenisPegawaiName = jenisPegawaiName;
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
        ImHrisJenisPegawaiEntity that = (ImHrisJenisPegawaiEntity) o;
        return Objects.equals(jenisPegawaiId, that.jenisPegawaiId) &&
                Objects.equals(jenisPegawaiName, that.jenisPegawaiName) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jenisPegawaiId, jenisPegawaiName, action, flag, createdDate, createdWho, lastUpdate, lastUpdateWho);
    }
}
