package com.neurix.akuntansi.transaksi.budgetingperhitungan.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 14/08/20.
 */
public class ParameterBudgeting {
    private String id;
    private String nama;
    private String rekeningId;
    private String idJenisBudgeting;
    private String tipe;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigDecimal nilaiTotal;
    private String tahun;
    private String branchId;
    private String idParameter;
    private String masterId;
    private String divisiId;
    private String idKategoriBudgeting;

    public String getIdKategoriBudgeting() {
        return idKategoriBudgeting;
    }

    public void setIdKategoriBudgeting(String idKategoriBudgeting) {
        this.idKategoriBudgeting = idKategoriBudgeting;
    }

    public BigDecimal getNilaiTotal() {
        return nilaiTotal;
    }

    public void setNilaiTotal(BigDecimal nilaiTotal) {
        this.nilaiTotal = nilaiTotal;
    }

    public String getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(String idParameter) {
        this.idParameter = idParameter;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getIdJenisBudgeting() {
        return idJenisBudgeting;
    }

    public void setIdJenisBudgeting(String idJenisBudgeting) {
        this.idJenisBudgeting = idJenisBudgeting;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
}