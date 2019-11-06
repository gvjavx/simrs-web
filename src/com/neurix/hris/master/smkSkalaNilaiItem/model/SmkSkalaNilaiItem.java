package com.neurix.hris.master.smkSkalaNilaiItem.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkSkalaNilaiItem extends BaseModel {
    private String skalaNilaiItemId;
    private Long persenBatasAtas;
    private Long persenBatasBawah;
    private Long nilaiAtas;
    private Long nilaiBawah;
    private String branchId;
    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSkalaNilaiItemId() {
        return skalaNilaiItemId;
    }

    public void setSkalaNilaiItemId(String skalaNilaiItemId) {
        this.skalaNilaiItemId = skalaNilaiItemId;
    }

    public Long getPersenBatasAtas() {
        return persenBatasAtas;
    }

    public void setPersenBatasAtas(Long persenBatasAtas) {
        this.persenBatasAtas = persenBatasAtas;
    }

    public Long getPersenBatasBawah() {
        return persenBatasBawah;
    }

    public void setPersenBatasBawah(Long persenBatasBawah) {
        this.persenBatasBawah = persenBatasBawah;
    }

    public Long getNilaiAtas() {
        return nilaiAtas;
    }

    public void setNilaiAtas(Long nilaiAtas) {
        this.nilaiAtas = nilaiAtas;
    }

    public Long getNilaiBawah() {
        return nilaiBawah;
    }

    public void setNilaiBawah(Long nilaiBawah) {
        this.nilaiBawah = nilaiBawah;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}