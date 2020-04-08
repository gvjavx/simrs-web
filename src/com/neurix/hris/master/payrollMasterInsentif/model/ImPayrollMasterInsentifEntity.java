package com.neurix.hris.master.payrollMasterInsentif.model;

import com.neurix.authorization.company.model.ImBranches;

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
public class ImPayrollMasterInsentifEntity implements Serializable {

    private String payrollInsentifId ;
    private BigDecimal potonganInsentif ;
    private BigDecimal smkStandart ;
    private String branchId;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImBranches imBranches;

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public String getPayrollInsentifId() {
        return payrollInsentifId;
    }

    public void setPayrollInsentifId(String payrollInsentifId) {
        this.payrollInsentifId = payrollInsentifId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
}