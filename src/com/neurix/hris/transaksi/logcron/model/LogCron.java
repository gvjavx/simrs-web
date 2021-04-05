package com.neurix.hris.transaksi.logcron.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

public class LogCron extends BaseModel {
    private String logCronId;
    private String cronName;
    private Timestamp cronDate;
    private String stCronDate;
    private String status;
    private String note;

    private Timestamp createdDate;
    private String stCreatedDate;
    private String flag;
    private String action;
    private Timestamp lastUpdate;
    private String stLastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getLogCronId() {
        return logCronId;
    }

    public void setLogCronId(String logCronId) {
        this.logCronId = logCronId;
    }

    public String getCronName() {
        return cronName;
    }

    public void setCronName(String cronName) {
        this.cronName = cronName;
    }

    public Timestamp getCronDate() {
        return cronDate;
    }

    public void setCronDate(Timestamp cronDate) {
        this.cronDate = cronDate;
    }

    public String getStCronDate() {
        return stCronDate;
    }

    public void setStCronDate(String stCronDate) {
        this.stCronDate = stCronDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getStCreatedDate() {
        return stCreatedDate;
    }

    @Override
    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getStLastUpdate() {
        return stLastUpdate;
    }

    @Override
    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    @Override
    public String getCreatedWho() {
        return createdWho;
    }

    @Override
    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    @Override
    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    @Override
    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
