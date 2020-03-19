package com.neurix.hris.transaksi.payroll.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ItPayrollPttEntity implements Serializable {
    private String payrollPttId;
    private String payrollId;
    private String idPtt;
    private BigDecimal nilai;
    private String flag;
    private String action;
    private String createdWho;
    private String lastUpdateWho;
    private Timestamp createdDate;
    private Timestamp lastUpdate;

    public String getPayrollPttId() {
        return payrollPttId;
    }

    public void setPayrollPttId(String payrollPttId) {
        this.payrollPttId = payrollPttId;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getIdPtt() {
        return idPtt;
    }

    public void setIdPtt(String idPtt) {
        this.idPtt = idPtt;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
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
