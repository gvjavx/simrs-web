package com.neurix.hris.transaksi.payroll.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ImPayrollParamBpjsEntity implements Serializable {
    private String payrollParamBpjsId;
    private String flagGapok;
    private String flagSankhus;
    private String flagPeralihanGapok;
    private String flagPeralihanSankhus;
    private String flagPeralihanTunjangan;
    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

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
}
