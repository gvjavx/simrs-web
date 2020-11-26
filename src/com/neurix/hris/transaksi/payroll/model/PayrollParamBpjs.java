package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollParamBpjs extends BaseModel {
    private String payrollParamBpjsId;
    private String flagGapok;
    private String flagSankhus;
    private String flagPeralihanGapok;
    private String flagPeralihanSankhus;
    private String flagPeralihanTunjangan;

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
}