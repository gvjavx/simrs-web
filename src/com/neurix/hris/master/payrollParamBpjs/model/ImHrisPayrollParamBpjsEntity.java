package com.neurix.hris.master.payrollParamBpjs.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ImHrisPayrollParamBpjsEntity {
    private String payrollParamBpjsId;
    private String flagGapok;
    private String flagSankhus;
    private String flagPeralihanGapok;
    private String flagPeralihanSankhus;
    private String flagPeralihanTunjangan;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;

    public String getPayrollParamBpjsId() {
        return payrollParamBpjsId;
    }

    public void setPayrollParamBpjsId(String payrollParamBpjsId) {
        this.payrollParamBpjsId = payrollParamBpjsId;
    }

    public String getFlagGapok() {
        return flagGapok;
    }

    public void setFlagGapok(String flagGapok) {
        this.flagGapok = flagGapok;
    }

    public String getFlagSankhus() {
        return flagSankhus;
    }

    public void setFlagSankhus(String flagSankhus) {
        this.flagSankhus = flagSankhus;
    }

    public String getFlagPeralihanGapok() {
        return flagPeralihanGapok;
    }

    public void setFlagPeralihanGapok(String flagPeralihanGapok) {
        this.flagPeralihanGapok = flagPeralihanGapok;
    }

    public String getFlagPeralihanSankhus() {
        return flagPeralihanSankhus;
    }

    public void setFlagPeralihanSankhus(String flagPeralihanSankhus) {
        this.flagPeralihanSankhus = flagPeralihanSankhus;
    }

    public String getFlagPeralihanTunjangan() {
        return flagPeralihanTunjangan;
    }

    public void setFlagPeralihanTunjangan(String flagPeralihanTunjangan) {
        this.flagPeralihanTunjangan = flagPeralihanTunjangan;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImHrisPayrollParamBpjsEntity that = (ImHrisPayrollParamBpjsEntity) o;
        return Objects.equals(payrollParamBpjsId, that.payrollParamBpjsId) &&
                Objects.equals(flagGapok, that.flagGapok) &&
                Objects.equals(flagSankhus, that.flagSankhus) &&
                Objects.equals(flagPeralihanGapok, that.flagPeralihanGapok) &&
                Objects.equals(flagPeralihanSankhus, that.flagPeralihanSankhus) &&
                Objects.equals(flagPeralihanTunjangan, that.flagPeralihanTunjangan) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payrollParamBpjsId, flagGapok, flagSankhus, flagPeralihanGapok, flagPeralihanSankhus, flagPeralihanTunjangan, createdDate, createdWho, lastUpdate, lastUpdateWho, action, flag);
    }
}
