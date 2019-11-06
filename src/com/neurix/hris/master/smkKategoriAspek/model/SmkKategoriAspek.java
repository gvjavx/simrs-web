package com.neurix.hris.master.smkKategoriAspek.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkKategoriAspek extends BaseModel {
    private String kategoriAspekId;
    private String kategoriName;
    private BigDecimal bobot;
    private String branchId;
    private String tipeAspekId;
    private String branchName;
    private String tipeAspekName;

    private String subAspekA;

    public String getSubAspekA() {
        return subAspekA;
    }

    public void setSubAspekA(String subAspekA) {
        this.subAspekA = subAspekA;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public BigDecimal getBobot() {
        return bobot;
    }

    public void setBobot(BigDecimal bobot) {
        this.bobot = bobot;
    }

    public String getKategoriName() {
        return kategoriName;
    }

    public void setKategoriName(String kategoriName) {
        this.kategoriName = kategoriName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTipeAspekId() {
        return tipeAspekId;
    }

    public void setTipeAspekId(String tipeAspekId) {
        this.tipeAspekId = tipeAspekId;
    }

    public String getTipeAspekName() {
        return tipeAspekName;
    }

    public void setTipeAspekName(String tipeAspekName) {
        this.tipeAspekName = tipeAspekName;
    }

    public String getKategoriAspekId() {
        return kategoriAspekId;
    }

    public void setKategoriAspekId(String kategoriAspekId) {
        this.kategoriAspekId = kategoriAspekId;
    }
}