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

    //tambahan irfan
    private Double iuranBpjsKsKaryPersen;
    private Double iuranBpjsKsPersPersen;
    private Double iuranBpjsTkKaryPersen;
    private Double iuranBpjsTkPersPersen;
    private BigDecimal minBpjsKs;
    private BigDecimal maxBpjsKs;
    private BigDecimal minBpjsTk;
    private BigDecimal MaxBpjsTk;

    private Double iuranKary;
    private Double jpkKary;
    private Double jkkPers;
    private Double jhtPers;
    private Double jkmPers;
    private Double iuranPers;
    private Double jpkPers;

    public Double getIuranKary() {
        return iuranKary;
    }

    public void setIuranKary(Double iuranKary) {
        this.iuranKary = iuranKary;
    }

    public Double getIuranPers() {
        return iuranPers;
    }

    public void setIuranPers(Double iuranPers) {
        this.iuranPers = iuranPers;
    }

    public Double getJhtPers() {
        return jhtPers;
    }

    public void setJhtPers(Double jhtPers) {
        this.jhtPers = jhtPers;
    }

    public Double getJkkPers() {
        return jkkPers;
    }

    public void setJkkPers(Double jkkPers) {
        this.jkkPers = jkkPers;
    }

    public Double getJkmPers() {
        return jkmPers;
    }

    public void setJkmPers(Double jkmPers) {
        this.jkmPers = jkmPers;
    }

    public Double getJpkKary() {
        return jpkKary;
    }

    public void setJpkKary(Double jpkKary) {
        this.jpkKary = jpkKary;
    }

    public Double getJpkPers() {
        return jpkPers;
    }

    public void setJpkPers(Double jpkPers) {
        this.jpkPers = jpkPers;
    }

    private String branchId;
    private String branchName;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Double getIuranBpjsKsKaryPersen() {
        return iuranBpjsKsKaryPersen;
    }

    public void setIuranBpjsKsKaryPersen(Double iuranBpjsKsKaryPersen) {
        this.iuranBpjsKsKaryPersen = iuranBpjsKsKaryPersen;
    }

    public Double getIuranBpjsKsPersPersen() {
        return iuranBpjsKsPersPersen;
    }

    public void setIuranBpjsKsPersPersen(Double iuranBpjsKsPersPersen) {
        this.iuranBpjsKsPersPersen = iuranBpjsKsPersPersen;
    }

    public Double getIuranBpjsTkKaryPersen() {
        return iuranBpjsTkKaryPersen;
    }

    public void setIuranBpjsTkKaryPersen(Double iuranBpjsTkKaryPersen) {
        this.iuranBpjsTkKaryPersen = iuranBpjsTkKaryPersen;
    }

    public Double getIuranBpjsTkPersPersen() {
        return iuranBpjsTkPersPersen;
    }

    public void setIuranBpjsTkPersPersen(Double iuranBpjsTkPersPersen) {
        this.iuranBpjsTkPersPersen = iuranBpjsTkPersPersen;
    }

    public BigDecimal getMaxBpjsKs() {
        return maxBpjsKs;
    }

    public void setMaxBpjsKs(BigDecimal maxBpjsKs) {
        this.maxBpjsKs = maxBpjsKs;
    }

    public BigDecimal getMaxBpjsTk() {
        return MaxBpjsTk;
    }

    public void setMaxBpjsTk(BigDecimal maxBpjsTk) {
        MaxBpjsTk = maxBpjsTk;
    }

    public BigDecimal getMinBpjsKs() {
        return minBpjsKs;
    }

    public void setMinBpjsKs(BigDecimal minBpjsKs) {
        this.minBpjsKs = minBpjsKs;
    }

    public BigDecimal getMinBpjsTk() {
        return minBpjsTk;
    }

    public void setMinBpjsTk(BigDecimal minBpjsTk) {
        this.minBpjsTk = minBpjsTk;
    }

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
