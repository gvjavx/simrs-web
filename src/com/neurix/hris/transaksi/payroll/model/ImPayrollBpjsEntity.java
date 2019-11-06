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

public class ImPayrollBpjsEntity implements Serializable {
    private String bpjsId;
    private BigDecimal bpjsKesehatanPersen;
    private BigDecimal bpjsPensiunPersen;
    private BigDecimal bpjsJhtPersen;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getBpjsId() {
        return bpjsId;
    }

    public void setBpjsId(String bpjsId) {
        this.bpjsId = bpjsId;
    }

    public BigDecimal getBpjsKesehatanPersen() {
        return bpjsKesehatanPersen;
    }

    public void setBpjsKesehatanPersen(BigDecimal bpjsKesehatanPersen) {
        this.bpjsKesehatanPersen = bpjsKesehatanPersen;
    }

    public BigDecimal getBpjsPensiunPersen() {
        return bpjsPensiunPersen;
    }

    public void setBpjsPensiunPersen(BigDecimal bpjsPensiunPersen) {
        this.bpjsPensiunPersen = bpjsPensiunPersen;
    }

    public BigDecimal getBpjsJhtPersen() {
        return bpjsJhtPersen;
    }

    public void setBpjsJhtPersen(BigDecimal bpjsJhtPersen) {
        this.bpjsJhtPersen = bpjsJhtPersen;
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
}
