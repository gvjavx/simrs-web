package com.neurix.authorization.company.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class Branch extends BaseModel implements Serializable, Comparable<Branch> {
    private String branchId;
    private String branchName;
    private String branchAddress;
    private String enabled;
    private String mt;
    private String statusPabrik;
    private String strBiayaJasprod;
    private BigDecimal biayaJasprod;
    private BigDecimal multifikator;
    private BigDecimal faktorJubileum;
    private BigDecimal faktorJasprod;
    private BigDecimal umr;
    private BigDecimal uangMakan;
    private int maxJamIjinKeluar;
    private String areaId;

    private String logoBranch;

    public String getLogoBranch() {
        return logoBranch;
    }

    public void setLogoBranch(String logoBranch) {
        this.logoBranch = logoBranch;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getStrBiayaJasprod() {
        return strBiayaJasprod;
    }

    public void setStrBiayaJasprod(String strBiayaJasprod) {
        this.strBiayaJasprod = strBiayaJasprod;
    }

    public BigDecimal getBiayaJasprod() {
        return biayaJasprod;
    }

    public void setBiayaJasprod(BigDecimal biayaJasprod) {
        this.biayaJasprod = biayaJasprod;
    }

    public BigDecimal getUangMakan() {
        return uangMakan;
    }

    public void setUangMakan(BigDecimal uangMakan) {
        this.uangMakan = uangMakan;
    }

    public int getMaxJamIjinKeluar() {
        return maxJamIjinKeluar;
    }

    public void setMaxJamIjinKeluar(int maxJamIjinKeluar) {
        this.maxJamIjinKeluar = maxJamIjinKeluar;
    }

    public BigDecimal getMultifikator() {
        return multifikator;
    }

    public BigDecimal getFaktorJasprod() {
        return faktorJasprod;
    }

    public void setFaktorJasprod(BigDecimal faktorJasprod) {
        this.faktorJasprod = faktorJasprod;
    }

    public BigDecimal getFaktorJubileum() {
        return faktorJubileum;
    }

    public void setFaktorJubileum(BigDecimal faktorJubileum) {
        this.faktorJubileum = faktorJubileum;
    }

    public void setMultifikator(BigDecimal multifikator) {
        this.multifikator = multifikator;
    }

    public BigDecimal getUmr() {
        return umr;
    }

    public void setUmr(BigDecimal umr) {
        this.umr = umr;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getStatusPabrik() {
        return statusPabrik;
    }

    public void setStatusPabrik(String statusPabrik) {
        this.statusPabrik = statusPabrik;
    }
    private BigDecimal faktorKali;

    public BigDecimal getFaktorKali() {
        return faktorKali;
    }

    public void setFaktorKali(BigDecimal faktorKali) {
        this.faktorKali = faktorKali;

    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public int compareTo(Branch o) {
        return this.branchName.toLowerCase().compareTo(o.getBranchName().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;

        Branch branch = (Branch) o;

        if (branchAddress != null ? !branchAddress.equals(branch.branchAddress) : branch.branchAddress != null)
            return false;
        if (branchId != null ? !branchId.equals(branch.branchId) : branch.branchId != null) return false;
        if (branchName != null ? !branchName.equals(branch.branchName) : branch.branchName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = branchId != null ? branchId.hashCode() : 0;
        result = 31 * result + (branchName != null ? branchName.hashCode() : 0);
        result = 31 * result + (branchAddress != null ? branchAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                '}';
    }
}