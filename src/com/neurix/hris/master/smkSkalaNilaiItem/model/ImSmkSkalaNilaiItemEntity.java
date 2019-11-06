package com.neurix.hris.master.smkSkalaNilaiItem.model;

import com.neurix.authorization.company.model.ImBranches;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImSmkSkalaNilaiItemEntity implements Serializable {

    private String skalaNilaiItemId;
    private Long persenBatasAtas;
    private Long persenBatasBawah;
    private Long nilaiAtas;
    private Long nilaiBawah;
    private String branchId;
    private String branchName;

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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
