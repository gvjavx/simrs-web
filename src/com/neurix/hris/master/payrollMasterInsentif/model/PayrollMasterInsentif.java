package com.neurix.hris.master.payrollMasterInsentif.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollMasterInsentif extends BaseModel {
    private String payrollInsentifId ;
    private BigDecimal potonganInsentif ;
    private BigDecimal smkStandart ;
    private String branchId;
    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPayrollInsentifId() {
        return payrollInsentifId;
    }

    public void setPayrollInsentifId(String payrollInsentifId) {
        this.payrollInsentifId = payrollInsentifId;
    }

    public BigDecimal getPotonganInsentif() {
        return potonganInsentif;
    }

    public void setPotonganInsentif(BigDecimal potonganInsentif) {
        this.potonganInsentif = potonganInsentif;
    }

    public BigDecimal getSmkStandart() {
        return smkStandart;
    }

    public void setSmkStandart(BigDecimal smkStandart) {
        this.smkStandart = smkStandart;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}