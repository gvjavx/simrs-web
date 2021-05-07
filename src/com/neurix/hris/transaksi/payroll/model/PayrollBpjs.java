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

public class PayrollBpjs extends BaseModel {
    private String bpjsId;
    private double bpjsKesehatanPersen;
    private double bpjsPensiunPersen;
    private double bpjsJhtPersen;

    //tambahan irfan
    private Double iuranBpjsKsKaryPersen;
    private Double iuranBpjsKsPersPersen;
    private Double iuranBpjsTkKaryPersen;
    private Double iuranBpjsTkPersPersen;
    private Double iuranKary;
    private Double jpkKary;
    private Double jkkPers;
    private Double jhtPers;
    private Double jkmPers;
    private Double iuranPers;
    private Double jpkPers;

    private BigDecimal minBpjsKs;
    private BigDecimal maxBpjsKs;
    private BigDecimal minBpjsTk;
    private BigDecimal maxBpjsTk;
    private String iuranBpjsKsKary;
    private String iuranBpjsKsPers;
    private String iuranBpjsTkKary;
    private String iuranBpjsTkPers;
    private BigDecimal iuranBpjsKsKaryNilai;
    private BigDecimal iuranBpjsKsPersNilai;
    private BigDecimal iuranBpjsTkKaryNilai;
    private BigDecimal iuranBpjsTkPersNilai;

    private String branchId;
    private String branchName;

    private String numMinBpjsKs;
    private String numMaxBpjsKs;
    private String numMinBpjsTk;
    private String numMaxBpjsTk;

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

    public String getIuranBpjsKsKary() {
        return iuranBpjsKsKary;
    }

    public void setIuranBpjsKsKary(String iuranBpjsKsKary) {
        this.iuranBpjsKsKary = iuranBpjsKsKary;
    }

    public BigDecimal getIuranBpjsKsKaryNilai() {
        return iuranBpjsKsKaryNilai;
    }

    public void setIuranBpjsKsKaryNilai(BigDecimal iuranBpjsKsKaryNilai) {
        this.iuranBpjsKsKaryNilai = iuranBpjsKsKaryNilai;
    }

    public Double getIuranBpjsKsKaryPersen() {
        return iuranBpjsKsKaryPersen;
    }

    public void setIuranBpjsKsKaryPersen(Double iuranBpjsKsKaryPersen) {
        this.iuranBpjsKsKaryPersen = iuranBpjsKsKaryPersen;
    }

    public String getIuranBpjsKsPers() {
        return iuranBpjsKsPers;
    }

    public void setIuranBpjsKsPers(String iuranBpjsKsPers) {
        this.iuranBpjsKsPers = iuranBpjsKsPers;
    }

    public BigDecimal getIuranBpjsKsPersNilai() {
        return iuranBpjsKsPersNilai;
    }

    public void setIuranBpjsKsPersNilai(BigDecimal iuranBpjsKsPersNilai) {
        this.iuranBpjsKsPersNilai = iuranBpjsKsPersNilai;
    }

    public Double getIuranBpjsKsPersPersen() {
        return iuranBpjsKsPersPersen;
    }

    public void setIuranBpjsKsPersPersen(Double iuranBpjsKsPersPersen) {
        this.iuranBpjsKsPersPersen = iuranBpjsKsPersPersen;
    }

    public String getIuranBpjsTkKary() {
        return iuranBpjsTkKary;
    }

    public void setIuranBpjsTkKary(String iuranBpjsTkKary) {
        this.iuranBpjsTkKary = iuranBpjsTkKary;
    }

    public BigDecimal getIuranBpjsTkKaryNilai() {
        return iuranBpjsTkKaryNilai;
    }

    public void setIuranBpjsTkKaryNilai(BigDecimal iuranBpjsTkKaryNilai) {
        this.iuranBpjsTkKaryNilai = iuranBpjsTkKaryNilai;
    }

    public Double getIuranBpjsTkKaryPersen() {
        return iuranBpjsTkKaryPersen;
    }

    public void setIuranBpjsTkKaryPersen(Double iuranBpjsTkKaryPersen) {
        this.iuranBpjsTkKaryPersen = iuranBpjsTkKaryPersen;
    }

    public String getIuranBpjsTkPers() {
        return iuranBpjsTkPers;
    }

    public void setIuranBpjsTkPers(String iuranBpjsTkPers) {
        this.iuranBpjsTkPers = iuranBpjsTkPers;
    }

    public BigDecimal getIuranBpjsTkPersNilai() {
        return iuranBpjsTkPersNilai;
    }

    public void setIuranBpjsTkPersNilai(BigDecimal iuranBpjsTkPersNilai) {
        this.iuranBpjsTkPersNilai = iuranBpjsTkPersNilai;
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
        return maxBpjsTk;
    }

    public void setMaxBpjsTk(BigDecimal maxBpjsTk) {
        this.maxBpjsTk = maxBpjsTk;
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

    public double getBpjsKesehatanPersen() {
        return bpjsKesehatanPersen;
    }

    public void setBpjsKesehatanPersen(double bpjsKesehatanPersen) {
        this.bpjsKesehatanPersen = bpjsKesehatanPersen;
    }

    public double getBpjsPensiunPersen() {
        return bpjsPensiunPersen;
    }

    public void setBpjsPensiunPersen(double bpjsPensiunPersen) {
        this.bpjsPensiunPersen = bpjsPensiunPersen;
    }

    public double getBpjsJhtPersen() {
        return bpjsJhtPersen;
    }

    public void setBpjsJhtPersen(double bpjsJhtPersen) {
        this.bpjsJhtPersen = bpjsJhtPersen;
    }

    public String getNumMinBpjsKs() {
        return numMinBpjsKs;
    }

    public void setNumMinBpjsKs(String numMinBpjsKs) {
        this.numMinBpjsKs = numMinBpjsKs;
    }

    public String getNumMaxBpjsKs() {
        return numMaxBpjsKs;
    }

    public void setNumMaxBpjsKs(String numMaxBpjsKs) {
        this.numMaxBpjsKs = numMaxBpjsKs;
    }

    public String getNumMinBpjsTk() {
        return numMinBpjsTk;
    }

    public void setNumMinBpjsTk(String numMinBpjsTk) {
        this.numMinBpjsTk = numMinBpjsTk;
    }

    public String getNumMaxBpjsTk() {
        return numMaxBpjsTk;
    }

    public void setNumMaxBpjsTk(String numMaxBpjsTk) {
        this.numMaxBpjsTk = numMaxBpjsTk;
    }
}